package use_case.new_chat.master_yoda;

import entity.bot.Bot;
import entity.bot.BotFactory;
import entity.bot.MasterYoda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link MasterYodaInteractor}.
 * <p>
 * This test class covers the interactor's behavior, including successful execution,
 * handling of null input data, and validation of {@link MasterYodaOutputData}.
 */
public class MasterYodaInteractorTest {

    private MasterYodaOutputBoundary mockPresenter;
    private BotFactory mockFactory;

    /**
     * Sets up the mock dependencies required for the test cases.
     * <p>
     * The presenter captures the output of the interactor, and the factory provides
     * a mock Master Yoda bot instance. This setup method is executed before each test.
     */
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

            /**
             * Checks whether the {@code beginChat} method was called during the test.
             *
             * @return true if {@code beginChat} was called, false otherwise.
             */
            public boolean isBeginChatCalled() {
                return beginChatCalled;
            }

            /**
             * Retrieves the {@link MasterYodaOutputData} captured during the test.
             *
             * @return the captured {@link MasterYodaOutputData}.
             */
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

    /**
     * Tests the successful execution of the {@link MasterYodaInteractor}.
     * <p>
     * Ensures the interactor correctly retrieves input data, generates a Master Yoda bot via the factory,
     * and calls the presenter's {@code beginChat} method with the expected output data.
     */
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

    /**
     * Tests the behavior of the {@link MasterYodaInteractor} when null input data is provided.
     * <p>
     * Expects a {@link NullPointerException} to be thrown.
     */
    @Test
    void testNullInputData() {
        // Arrange
        MasterYodaInteractor interactor = new MasterYodaInteractor(mockPresenter, mockFactory);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> interactor.execute(null));
    }

    /**
     * Tests the {@link MasterYodaOutputData} class to ensure correct initialization and retrieval of fields.
     */
    @Test
    void testMasterYodaOutputData() {
        // Arrange
        MasterYodaOutputData outputData = new MasterYodaOutputData("Anakin", "Train yourself to let go of everything you fear to lose.");

        // Act & Assert
        assertEquals("Anakin", outputData.getUsername());
        assertEquals("Train yourself to let go of everything you fear to lose.", outputData.getPrompt());
    }
}
