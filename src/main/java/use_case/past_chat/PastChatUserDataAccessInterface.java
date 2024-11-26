package use_case.past_chat;

import entity.message.Message;

import java.util.List;

/**
 * The interface for retrieving chat history from the MongoDB.
 */
public interface PastChatUserDataAccessInterface {

    /**
     * Retrieves the past chat for this user from the MongoDB.
     * @param user the user for which the past chat is being retrieved.
     */
    List<Message> mixedHistory(String user);

}
