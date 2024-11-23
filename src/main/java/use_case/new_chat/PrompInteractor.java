package use_case.new_chat;

import entity.bot.MasterYodaFactory;
import entity.bot.NormalAIFactory;
import entity.bot.OptimusPrimeFactory;
import entity.bot.PikachuFactory;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class PrompInteractor {
    public final String prompt1;
    public final String prompt2;
    public final String prompt3;
    public final String prompt4;

    public PrompInteractor() {
        this.prompt1 = new NormalAIFactory().create().getPrompt();
        this.prompt2 = new PikachuFactory().create().getPrompt();
        this.prompt3 = new MasterYodaFactory().create().getPrompt();
        this.prompt4 = new OptimusPrimeFactory().create().getPrompt();
    }

    public String getPrompt1() {
        return prompt1;
    }

    public String getPrompt2() {
        return prompt2;
    }

    public String getPrompt3() {
        return prompt3;
    }

    public String getPrompt4() {
        return prompt4;
    }

}
