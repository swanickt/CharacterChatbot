package entity.message;

/**
 * A chat message sent from a User.
 */
public class CommonUserMessage implements Message {

    private final String contents;
    private final String userName;

    public CommonUserMessage(String contents, String userName) {
        this.contents = contents;
        this.userName = userName;
    }

    @Override
    public String getContents() {
        return contents;
    }

    @Override
    public String getSender() {
        return userName;
    }

}
