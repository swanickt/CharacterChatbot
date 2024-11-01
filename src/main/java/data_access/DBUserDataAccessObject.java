package data_access;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.User;
import entity.UserFactory;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * The DAO for user data.
 */
public class DBUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface {
    private static final String API_KEY = "your-openai-api-key-here";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String AUTHORIZATION_LABEL = "Authorization";
    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String MESSAGE = "message";
    private static String api_toUse = "gpt-3.5-turbo";
    private static final String CHATGPT_URL = "https://api.openai.com/v1/chat/completions";
    private static List<String> conversationHistory = new ArrayList<>();
    private static final String CONVERSATION_HISTORY_FILE = "conversation_history.txt";
    private final UserFactory userFactory;

    public DBUserDataAccessObject(UserFactory userFactory) {
        this.userFactory = userFactory;
        // No need to do anything to reinitialize a user list! The data is the cloud that may be miles away.
    }

    public void setApi(String api) {
        api_toUse = api;
    }

    @Override
    public User get(String username) {
        // Make an API call to get the user object.
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", username))
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                final JSONObject userJSONObject = responseBody.getJSONObject("user");
                final String name = userJSONObject.getString(USERNAME);
                final String password = userJSONObject.getString(PASSWORD);

                return userFactory.create(name, password);
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Loads the conversation history from a file.
     * @throws IOException if code goes wrong.
     * @throws RuntimeException as required.
     */
    private void loadConversationHistory() throws IOException {
        try {
            if (Files.exists(Paths.get(CONVERSATION_HISTORY_FILE))) {
                conversationHistory = Files.readAllLines(Paths.get(CONVERSATION_HISTORY_FILE));
            }
        }
        catch (IOException ex) {
            throw new RuntimeException("Failed to load conversation history", ex);
        }
    }

    /**
     * Saves the conversation history to a file.
     * @throws IOException if code goes wrong.
     * @throws RuntimeException as required.
     */
    private void saveConversationHistory() {
        try (FileWriter writer = new FileWriter(new File(CONVERSATION_HISTORY_FILE))) {
            for (String line : conversationHistory) {
                writer.write(line + "\n");
            }
        }
        catch (IOException ex) {
            throw new RuntimeException("Failed to save conversation history", ex);
        }
    }

    private void storeConversation(String userMessage, String chatgptResponse) {
        conversationHistory.add("User: " + userMessage);
        conversationHistory.add("ChatGPT: " + chatgptResponse);
        saveConversationHistory();
    }

    /**
     * Summarizes the conversation history.
     *
     * @return A summary of the conversation history.
     */
    private String summarizeConversationHistory() {
        StringBuilder summaryBuilder = new StringBuilder();
        try {
            loadConversationHistory();
            summaryBuilder.append("Summary of previous conversation:\n");
            for (int i = 0; i < conversationHistory.size(); i++) {
                if (i >= 10) {
                    break;
                    // Limit summary to the last 5 exchanges
                }
                summaryBuilder.append(conversationHistory.get(i)).append("\n");
            }
        }
        catch (IOException ex) {
            // If loading conversation history fails, return an empty summary
            summaryBuilder = new StringBuilder();
        }
        return summaryBuilder.toString();
    }

    // this function is used to let gpt response before the user based on the
    // chat history
    public String getChatGPTSummaryResponse() {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();

        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONArray messages = new JSONArray();

        // Add summary of previous conversation history
        String summary = summarizeConversationHistory();
        if (!summary.isEmpty()) {
            JSONObject summaryMessage = new JSONObject();
            summaryMessage.put("role", "user");
            summaryMessage.put("content", summary);
            messages.put(summaryMessage);
        }

        final JSONObject requestBody = new JSONObject();
        requestBody.put("model", api_toUse);
        requestBody.put("messages", messages);

        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url(CHATGPT_URL)
                .method("POST", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .addHeader(AUTHORIZATION_LABEL, BEARER_PREFIX + API_KEY)
                .build();

        try {
            final Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                final JSONObject responseBody = new JSONObject(response.body().string());
                final JSONArray choices = responseBody.getJSONArray("choices");
                return choices.getJSONObject(0).getJSONObject("message").getString("content");
            }
            else {
                throw new RuntimeException("Failed to get response from ChatGPT: " + response.body().string());
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Used to send user input into chatgpt.
     * @param message user input
     * @return a message from chatgpt
     * @throws RuntimeException if it has an error
     * @throws JSONException as checkstyle required
     */
    public String sendMessage(String message) throws JSONException {
        // Get the summary response from ChatGPT before proceeding
        String summaryResponse = getChatGPTSummaryResponse();
        System.out.println("ChatGPT Summary: " + summaryResponse);

        final OkHttpClient client = new OkHttpClient().newBuilder().build();

        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONArray messages = new JSONArray();

        // Add summary of previous conversation history
        String summary = summarizeConversationHistory();
        if (!conversationHistory.isEmpty()) {
            JSONObject summaryMessage = new JSONObject();
            summaryMessage.put("role", "system");
            summaryMessage.put("content", summary);
            messages.put(summaryMessage);
        }

        // Add user message
        JSONObject userMessageObj = new JSONObject();
        userMessageObj.put("role", "user");
        userMessageObj.put("content", message);
        messages.put(userMessageObj);

        final JSONObject requestBody = new JSONObject();
        requestBody.put("model", api_toUse);
        requestBody.put("messages", messages);

        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url(CHATGPT_URL)
                .method("POST", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .addHeader(AUTHORIZATION_LABEL, BEARER_PREFIX + API_KEY)
                .build();

        // Execute the request and handle the response
        try {
            final Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                final JSONObject responseBody = new JSONObject(response.body().string());
                final JSONArray choices = responseBody.getJSONArray("choices");
                final String chatgptResponse = choices.getJSONObject(0).getJSONObject("message").getString("content");

                // Store the conversation
                storeConversation(message, chatgptResponse);

                return chatgptResponse;
            }
            else {
                throw new RuntimeException("Failed to get response from ChatGPT: " + response.body().string());
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void setCurrentUsername(String name) {
        // this isn't implemented for the lab
    }

    @Override
    public boolean existsByName(String username) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/checkIfUserExists?username=%s", username))
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            return responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE;
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void save(User user) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // POST METHOD
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(USERNAME, user.getName());
        requestBody.put(PASSWORD, user.getPassword());
        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/user")
                .method("POST", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // success!
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void changePassword(User user) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // POST METHOD
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(USERNAME, user.getName());
        requestBody.put(PASSWORD, user.getPassword());
        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/user")
                .method("PUT", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // success!
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String getCurrentUsername() {
        return null;
    }
}
