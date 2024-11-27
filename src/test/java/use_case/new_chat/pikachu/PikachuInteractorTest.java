package use_case.new_chat.pikachu;

import entity.bot.Bot;
import entity.bot.BotFactory;
import entity.bot.Pikachu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PikachuInteractorTest {

    private PikachuOutputBoundary mockPresenter;
    private BotFactory mockFactory;

    @BeforeEach
    void setup() {
        // Mock Presenter to capture outputs
        mockPresenter = new PikachuOutputBoundary() {
            private boolean beginChatCalled = false;
            private PikachuOutputData capturedOutputData;

            @Override
            public void beginChat(PikachuOutputData pikachuOutputData) {
                beginChatCalled = true;
                capturedOutputData = pikachuOutputData;
            }

            public boolean isBeginChatCalled() {
                return beginChatCalled;
            }

            public PikachuOutputData getCapturedOutputData() {
                return capturedOutputData;
            }
        };

        // Mock Factory to generate a bot with a static prompt
        mockFactory = new BotFactory() {
            @Override
            public Pikachu create() {
                return new Pikachu() {
                    @Override
                    public String getPrompt() {
                        return "Pika Pika! Let's chat!";
                    }
                };
            }
        };
    }

    @Test
    void testSuccessfulExecution() {
        // Arrange
        PikachuInputData inputData = new PikachuInputData("Ash");
        PikachuInteractor interactor = new PikachuInteractor(mockPresenter, mockFactory);

        // Act
        interactor.execute(inputData);

        // Assert
        assertTrue(((PikachuOutputBoundary) mockPresenter).isBeginChatCalled(), "The beginChat method should have been called.");
        PikachuOutputData outputData = ((PikachuOutputBoundary) mockPresenter).getCapturedOutputData();
        assertNotNull(outputData, "Output data should not be null.");
        assertEquals("Ash", outputData.getUsername(), "Username should match the input data.");
        assertEquals("Pika Pika! Let's chat!", outputData.getPrompt(), "Prompt should match the bot's prompt.");
    }

    @Test
    void testNullInputData() {
        // Arrange
        PikachuInteractor interactor = new PikachuInteractor(mockPresenter, mockFactory);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> interactor.execute(null), "Null input data should throw NullPointerException.");
    }

    @Test
    void testPikachuOutputData() {
        // Arrange
        PikachuOutputData outputData = new PikachuOutputData("Misty", "Pika! Pika!");

        // Act & Assert
        assertEquals("Misty", outputData.getUsername(), "Username should match the constructor value.");
        assertEquals("Pika! Pika!", outputData.getPrompt(), "Prompt should match the constructor value.");
    }
}
