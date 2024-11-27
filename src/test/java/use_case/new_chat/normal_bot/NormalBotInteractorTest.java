package use_case.new_chat.normal_bot;

import entity.bot.BotFactory;
import entity.bot.NormalAI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NormalBotInteractorTest {

    private NormalBotOutputBoundary mockPresenter;
    private BotFactory mockFactory;

    @BeforeEach
    void setup() {
        // Mock Presenter to capture outputs
        mockPresenter = new NormalBotOutputBoundary() {
            private boolean beginChatCalled = false;
            private NormalBotOutputData capturedOutputData;

            @Override
            public void beginChat(NormalBotOutputData normalBotOutputData) {
                beginChatCalled = true;
                capturedOutputData = normalBotOutputData;
            }

            public boolean isBeginChatCalled() {
                return beginChatCalled;
            }

            public NormalBotOutputData getCapturedOutputData() {
                return capturedOutputData;
            }
        };

        // Mock Factory to generate a bot with a static prompt
        mockFactory = new BotFactory() {
            @Override
            public NormalAI create() {
                return new NormalAI() {
                    @Override
                    public String getPrompt() {
                        return "Hello! I'm your normal bot assistant.";
                    }
                };
            }
        };
    }

    @Test
    void testSuccessfulExecution() {
        // Arrange
        NormalBotInputData inputData = new NormalBotInputData("John");
        NormalBotInteractor interactor = new NormalBotInteractor(mockPresenter, mockFactory);

        // Act
        interactor.execute(inputData);

        // Assert
        assertTrue(((NormalBotOutputBoundary) mockPresenter).isBeginChatCalled(), "The beginChat method should have been called.");
        NormalBotOutputData outputData = ((NormalBotOutputBoundary) mockPresenter).getCapturedOutputData();
        assertNotNull(outputData, "Output data should not be null.");
        assertEquals("John", outputData.getUsername(), "Username should match the input data.");
        assertEquals("Hello! I'm your normal bot assistant.", outputData.getPrompt(), "Prompt should match the bot's prompt.");
    }

    @Test
    void testNullInputData() {
        // Arrange
        NormalBotInteractor interactor = new NormalBotInteractor(mockPresenter, mockFactory);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> interactor.execute(null), "Null input data should throw NullPointerException.");
    }

    @Test
    void testNormalBotOutputData() {
        // Arrange
        NormalBotOutputData outputData = new NormalBotOutputData("Alice", "Welcome to the chat with Normal Bot!");

        // Act & Assert
        assertEquals("Alice", outputData.getUsername(), "Username should match the constructor value.");
        assertEquals("Welcome to the chat with Normal Bot!", outputData.getPrompt(), "Prompt should match the constructor value.");
    }
}
