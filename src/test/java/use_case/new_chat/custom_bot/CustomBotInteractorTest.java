package use_case.new_chat.custom_bot;

import entity.bot.CustomBot;
import entity.bot.CustomBotFactory;
import entity.bot.Bot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomBotInteractorTest {
    private CustomBotOutputBoundary mockPresenter;
    private CustomBotFactory mockFactory;

    @BeforeEach
    void setup() {
        // Mock Presenter to validate output logic
        mockPresenter = new CustomBotOutputBoundary() {
            private boolean beginChatCalled = false;
            private CustomBotOutputData capturedOutputData;

            @Override
            public void beginChat(CustomBotOutputData customBotOutputData) {
                beginChatCalled = true;
                capturedOutputData = customBotOutputData;
            }

            public boolean isBeginChatCalled() {
                return beginChatCalled;
            }

            public CustomBotOutputData getCapturedOutputData() {
                return capturedOutputData;
            }
        };

        // Mock Factory to validate bot creation
        mockFactory = new CustomBotFactory() {
            @Override
            public CustomBot create(String name, String occupation) {
                return new CustomBot(name, occupation) {
                    @Override
                    public String getPrompt() {
                        return "Welcome to chat with " + name + ", the " + occupation + " bot!";
                    }
                };
            }
        };
    }

    @Test
    void testSuccessfulExecution() {
        // Arrange
        CustomBotInputData inputData = new CustomBotInputData("Alice", "HelperBot", "Assistant");
        CustomBotInteractor interactor = new CustomBotInteractor(mockPresenter, mockFactory);

        // Act
        interactor.execute(inputData);

        // Assert
        assertTrue(((CustomBotOutputBoundary) mockPresenter).isBeginChatCalled());
        CustomBotOutputData outputData = ((CustomBotOutputBoundary) mockPresenter).getCapturedOutputData();
        assertNotNull(outputData);
        assertEquals("Alice", outputData.getUsername());
        assertEquals("Welcome to chat with HelperBot, the Assistant bot!", outputData.getPrompt());
    }

    @Test
    void testNullInputData() {
        // Arrange
        CustomBotInteractor interactor = new CustomBotInteractor(mockPresenter, mockFactory);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> interactor.execute(null));
    }

    @Test
    void testEmptyBotNameAndOccupation() {
        // Arrange
        CustomBotInputData inputData = new CustomBotInputData("Alice", "", "");
        CustomBotInteractor interactor = new CustomBotInteractor(mockPresenter, mockFactory);

        // Act
        interactor.execute(inputData);

        // Assert
        assertTrue(((CustomBotOutputBoundary) mockPresenter).isBeginChatCalled());
        CustomBotOutputData outputData = ((CustomBotOutputBoundary) mockPresenter).getCapturedOutputData();
        assertNotNull(outputData);
        assertEquals("Alice", outputData.getUsername());
        assertEquals("Welcome to chat with , the  bot!", outputData.getPrompt());
    }

    @Test
    void testCustomBotOutputData() {
        // Arrange
        CustomBotOutputData outputData = new CustomBotOutputData("Alice", "Welcome to the chat!");

        // Act & Assert
        assertEquals("Alice", outputData.getUsername());
        assertEquals("Welcome to the chat!", outputData.getPrompt());
    }
}
