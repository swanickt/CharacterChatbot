package use_case.new_chat.master_yoda;

import entity.bot.Bot;
import entity.bot.BotFactory;
import entity.bot.MasterYoda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MasterYodaInteractorTest {

    private MasterYodaOutputBoundary mockPresenter;
    private BotFactory mockFactory;

    @BeforeEach
    void setup() {
        // Mock Presenter to capture outputs
        mockPresenter = new MasterYodaOutputBoundary() {
            private boolean beginChatCalled = false;
            private MasterYodaOutputData capturedOutputData;

            @Override
            public void beginChat(MasterYodaOutputData masterYodaOutputData) {
                beginChatCalled = true;
                capturedOutputData = masterYodaOutputData;
            }

            public boolean isBeginChatCalled() {
                return beginChatCalled;
            }

            public MasterYodaOutputData getCapturedOutputData() {
                return capturedOutputData;
            }
        };

        // Mock Factory to generate a bot with a static prompt
        mockFactory = new BotFactory() {
            @Override
            public MasterYoda create() {
                return new MasterYoda() {
                    @Override
                    public String getPrompt() {
                        return "Do or do not, there is no try.";
                    }
                };
            }
        };
    }

    @Test
    void testSuccessfulExecution() {
        // Arrange
        MasterYodaInputData inputData = new MasterYodaInputData("Luke");
        MasterYodaInteractor interactor = new MasterYodaInteractor(mockPresenter, mockFactory);

        // Act
        interactor.execute(inputData);

        // Assert
        assertTrue(((MasterYodaOutputBoundary) mockPresenter).isBeginChatCalled());
        MasterYodaOutputData outputData = ((MasterYodaOutputBoundary) mockPresenter).getCapturedOutputData();
        assertNotNull(outputData);
        assertEquals("Luke", outputData.getUsername());
        assertEquals("Do or do not, there is no try.", outputData.getPrompt());
    }

    @Test
    void testNullInputData() {
        // Arrange
        MasterYodaInteractor interactor = new MasterYodaInteractor(mockPresenter, mockFactory);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> interactor.execute(null));
    }

    @Test
    void testMasterYodaOutputData() {
        // Arrange
        MasterYodaOutputData outputData = new MasterYodaOutputData("Anakin", "Train yourself to let go of everything you fear to lose.");

        // Act & Assert
        assertEquals("Anakin", outputData.getUsername());
        assertEquals("Train yourself to let go of everything you fear to lose.", outputData.getPrompt());
    }
}
