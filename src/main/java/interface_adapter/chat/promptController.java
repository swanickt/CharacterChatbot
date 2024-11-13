package interface_adapter.chat;

import use_case.ChatService.PrompInteractor;

import javax.swing.*;

public class promptController {
    public String prompt1;
    public String prompt2;
    public String prompt3;
    public String prompt4;

    public promptController(){
        final PrompInteractor prompInteractor = new PrompInteractor();
        this.prompt1 = prompInteractor.getPrompt1();
        this.prompt2 = prompInteractor.getPrompt2();
        this.prompt3 = prompInteractor.getPrompt3();
        this.prompt4 = prompInteractor.getPrompt4();
    }
    @SuppressWarnings({"checkstyle:ParameterName", "checkstyle:SuppressWarnings", "checkstyle:WhitespaceAround", "checkstyle:ReturnCount"})
    public String getPrompt(JRadioButton J1, JRadioButton J2, JRadioButton J3, JRadioButton J4){
        if (J1.isSelected()) {
            return prompt1;
        }
        else if (J2.isSelected()) {
            return prompt2;
        }
        else if (J3.isSelected()) {
            return prompt3;
        }
        else if (J4.isSelected()) {
            return prompt4;
        }
        return "";
    }
}
