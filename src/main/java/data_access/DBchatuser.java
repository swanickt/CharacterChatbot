package data_access;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.client.model.Filters;
import entity.user.User;
import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import entity.user.CommonUser;

/**
 * Just a class that people can access to store the data.
 */
@SuppressWarnings({"checkstyle:AbbreviationAsWordInName", "checkstyle:SuppressWarnings"})
public class DBchatuser implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface {
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String HISTORY = "history";
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoDatabase databaseForHis;
    private MongoCollection<Document> chatCollection;
    private MongoCollection<Document> userCollection;
    private MongoCollection<Document> chatbot;
    private String getUsername;
    private static Boolean chatStatus;

    public DBchatuser() {
        String connectionString = "mongodb+srv://jda1234112:dcJSlP8XfESt9FED@cluster0."
                + "2mrsi.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
        mongoClient = MongoClients.create(connectionString);
        databaseForHis = mongoClient.getDatabase("ChatHistory");
        database = mongoClient.getDatabase("chatbotDB");
        chatCollection = databaseForHis.getCollection("chathistory");
        userCollection = database.getCollection("userAccounts");
        chatbot = database.getCollection("character");
        chatStatus = Boolean.TRUE;
    }

    public void setUp(String username) {
        Document foundDocument = chatCollection.find(Filters.eq("title", username+"'s history")).first();
        if (foundDocument != null) {
            chatCollection = databaseForHis.getCollection(username+"'s history");
        }
        else {
            chatCollection = databaseForHis.getCollection(username+"'s history");
        }
    }

    // TODO: need to modify due to different condition
    public void saveHistory(String user,String Response) {
        Document document;
        if (chatStatus) {
            document = new Document("user:", user)
                    .append("response", Response);
            chatStatus = Boolean.FALSE;
        }
        else {
            document = new Document("chat:", user)
                    .append("response", Response);
            chatStatus = Boolean.TRUE;
        }
        chatCollection.insertOne(document);
    }

    public void setCurrentUsername(String name) {
        // this isn't implemented for the lab
    }

    public String getCurrentUsername() {
        return null;
    }

    public User get(String username) {
        Document query = new Document(USERNAME, username);
        Document found = userCollection.find(query).first();
        if (found != null) {
            // Extract the embedded User object from the document
            String rusername = found.getString(USERNAME);
            String rpassowrd = found.getString(PASSWORD);
            return new CommonUser(rusername, rpassowrd);
        }
        else {
            throw new RuntimeException("Not found");
        }
    }

    // TODO: Might need some edition due to the condition i.e. else part
    public void save(User user) {
        String name = user.getName();
        Document userDocument = new Document(USERNAME, name)
                .append(PASSWORD, user.getPassword());
        userCollection.insertOne(userDocument);
    }

    public void changePassword(User user) {
        // Create a query to find the user by username
        Document query = new Document(USERNAME, user.getName());

        // Create an update operation to set the new password
        Document update = new Document("$set", new Document(PASSWORD, user.getPassword()));

        // Update the user's password in the collection
        userCollection.updateOne(query, update);
    }

    public Map<String, String> loadHistory(User user) {
        Map<String, String> chatMap = new HashMap<>();
        for (Document doc : chatCollection.find()) {
            String userMessage = doc.getString(user.getName());
            String chatbotResponse = doc.getString("chatbot");
            if (userMessage != null && chatbotResponse != null) {
                chatMap.put("user:", userMessage);
                chatMap.put("chatbot:", chatbotResponse);
            }
        }
        return chatMap;
    }

    public boolean existsByName(String identifier) {
        // Create a query to search for a user with the given username
        Document query = new Document(USERNAME, identifier);

        // Find the first matching document
        Document found = userCollection.find(query).first();

        // If a document is found, it means the username is already taken
        return found != null;
    }

    public void saveCharacter(String character,String usage, String reply) {
        Document query = new Document(USERNAME, character).append("usage", usage);
        Document found = chatbot.find(query).first();
        if (found != null) {
            Document newdoc = new Document(USERNAME, character).append("usage", usage).append("reply", reply);
            chatbot.updateOne(query, newdoc);
        }
        else {
            Document userDocument = new Document(USERNAME, character).append("usage", usage).append("reply", reply);
            chatbot.insertOne(userDocument);
        }
    }
}