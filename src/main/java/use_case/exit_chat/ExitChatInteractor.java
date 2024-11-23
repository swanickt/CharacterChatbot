package use_case.exit_chat;

import data_access.MongoDBDataAccessObject;
import entity.chat.CommonUserChat;
import entity.message.Message;
import use_case.send_message.SendMessageInputData;
import use_case.send_message.SendMessageOutputBoundary;

import java.util.List;

public class ExitChatInteractor implements ExitChatInputBoundary{
    private final CommonUserChat chat;

    public ExitChatInteractor(CommonUserChat commonUserChat) {
        this.chat = commonUserChat;
    }

    public void execute(ExitChatInputData inputData) {
        final List<Message> lst = inputData.getChat().getUserInputs();
        final List<Message> lst2 = inputData.getChat().getBotResponses();
        final MongoDBDataAccessObject database = new MongoDBDataAccessObject();
        for (int i = 0; i < lst.size(); i++) {
            System.out.println(inputData.getUsername());
            if (!"".equals(inputData.getUsername())) {
                database.saveHistory(inputData.getUsername(), lst.get(i).getContent());
                database.saveHistory("assistant", lst2.get(i).getContent());
            }
            System.out.println(lst.get(i).getContent());
            System.out.println(lst2.get(i).getContent());
        }
        System.out.println(database.get(inputData.getUsername()));
    }
}
