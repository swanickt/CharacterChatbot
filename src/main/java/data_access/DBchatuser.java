package data_access;

import java.util.HashMap;
import java.util.Map;

import com.mongodb.client.model.Filters;
import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Just a class that people can access to store the data.
 */
@SuppressWarnings({"checkstyle:AbbreviationAsWordInName", "checkstyle:SuppressWarnings"})
public class DBchatuser {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> chatCollection;
    private MongoCollection<Document> userCollection;

    public DBchatuser() {
        String connectionString = "mongodb+srv://jda1234112:dcJSlP8XfESt9FED@cluster0."
                + "2mrsi.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
        mongoClient = MongoClients.create(connectionString);
        database = mongoClient.getDatabase("chatbotDB");
        chatCollection = database.getCollection("chatHistory");
        userCollection = database.getCollection("userAccounts");
    }
    // TODO: need to modify due to different condition
    public void saveHistory(String user, String chatbotResponse) {
        Document document = new Document("user", user)
                .append("chatbot", chatbotResponse);
        chatCollection.insertOne(document);
    }

    // TODO: Might need some edition due to the condition i.e. else part
    public void saveUserAccount(String userId, String userName, String userPassword) {
        if (!checkDuplicate(userId)) {
            Document userDocument = new Document("userId", userId)
                    .append("userName", userName)
                    .append("userPassword", userPassword);
            userCollection.insertOne(userDocument);
        } else {
            System.out.println("User with userId " + userId + " already exists.");
        }
    }

    public void setPassword(String userId, String newPassword) {
        userCollection.updateOne(new Document("userId", userId), new Document("$set", new Document("userPassword", newPassword)));
    }

    public Map<String, String> loadHistory() {
        Map<String, String> chatMap = new HashMap<>();
        for (Document doc : chatCollection.find()) {
            String userMessage = doc.getString("user");
            String chatbotResponse = doc.getString("chatbot");
            if (userMessage != null && chatbotResponse != null) {
                chatMap.put("user:", userMessage);
                chatMap.put("chatbot:", chatbotResponse);
            }
        }
        return chatMap;
    }

    private boolean checkDuplicate(String userId) {
        Document existingUser = userCollection.find(Filters.eq("userId", userId)).first();
        return existingUser != null;
    }

    // TODO: this is used to check the password is correct or not.
    public Map<String, String> getUserNameAndPassword(String userId) {
        Map<String, String> userCredentials = new HashMap<>();
        Document userDoc = userCollection.find(Filters.eq("userId", userId)).first();
        if (userDoc != null) {
            String userName = userDoc.getString("userName");
            String userPassword = userDoc.getString("userPassword");
            userCredentials.put("userName", userName);
            userCredentials.put("userPassword", userPassword);
        }
        return userCredentials;
    }
}