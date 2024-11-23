package use_case.login;

import entity.bot.MasterYodaFactory;
import entity.bot.NormalAIFactory;
import entity.bot.OptimusPrimeFactory;
import entity.bot.PikachuFactory;

/**
 * Output Data for the Login Use Case.
 */
public class LoginOutputData {

    private final String username;
    private final boolean useCaseFailed;
    public final String prompt1;
    public final String prompt2;
    public final String prompt3;
    public final String prompt4;

    public LoginOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
        this.prompt1 = new NormalAIFactory().create().getPrompt();
        this.prompt2 = new PikachuFactory().create().getPrompt();
        this.prompt3 = new MasterYodaFactory().create().getPrompt();
        this.prompt4 = new OptimusPrimeFactory().create().getPrompt();
    }

    public String getUsername() {
        return username;
    }

    //    public String getPrompt1() {
    //        return prompt1;
    //    }
    //
    //    public String getPrompt2() {
    //        return prompt2;
    //    }
    //
    //    public String getPrompt3() {
    //        return prompt3;
    //    }
    //
    //    public String getPrompt4() {
    //        return prompt4;
    //    }

}
