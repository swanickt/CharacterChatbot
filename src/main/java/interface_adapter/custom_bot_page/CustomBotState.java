package interface_adapter.custom_bot_page;

public class CustomBotState {
    private final String state = "CustomBotState";
    private String name = "";
    private String occupation = "";

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getOccupation() {
        return occupation;
    }
}
