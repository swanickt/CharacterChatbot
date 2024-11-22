package use_case.chat.master_yoda;

public class MasterYodaInputData {
    private final String username;

    public MasterYodaInputData(String username) {
        this.username = username;
    }

    String getUsername() {
        return username;
    }
}
