package interface_adapter.chat;

/**
 * The state for the chatting view/app.
 */
public class ChatViewState {
    private final String state = "ChatView";
}

// This class doesn't do anything in terms of functionality, but it is needed
// to implement the ChatViewModel. It also follows clean architecture/our app
// builder design and allows us to later modify the chat view based on its state
// if we desire (open/closed principle of SOLID!).
