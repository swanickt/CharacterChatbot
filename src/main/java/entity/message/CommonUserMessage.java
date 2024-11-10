package entity.message;

/**
 * A chat message sent from a User.
 */
public class CommonUserMessage implements MessageInterface {

    private final String contents;
    private final String userName;

    public CommonUserMessage(String contents, String userName) {
        this.contents = contents;
        this.userName = userName;
    }

    @Override
    public String getContent() {
        return contents;
    }

    @Override
    public String getRole() {
        return userName;
    }

}
