package use_case.new_chat.optimus_prime;

import entity.bot.Bot;
import entity.bot.BotFactory;
import entity.bot.OptimusPrime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link OptimusPrimeInteractor}.
 * <p>
 * This test class covers the interactor's behavior, including successful execution,
 * handling of null input data, and validation of {@link OptimusPrimeOutputData}.
 */
public class OptimusPrimeInteractorTest {

    private OptimusPrimeOutputBoundary mockPresenter;
    private BotFactory mockFactory;

    /**
     * Sets up the mock dependencies required for the test cases.
     * <p>
     * The presenter captures the output of the interactor, and the factory provides
     * a mock Optimus Prime bot instance. This setup method is executed before each test.
     */
    @BeforeEach
    void setup() {
        // Mock Presenter to capture outputs
        mockPresenter = new OptimusPrimeOutputBoundary() {
            private boolean beginChatCalled = false;
            private OptimusPrimeOutputData capturedOutputData;

            @Override
            public void beginChat(OptimusPrimeOutputData optimusPrimeOutputData) {
                beginChatCalled = true;
                capturedOutputData = optimusPrimeOutputData;
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
             * Retrieves the {@link OptimusPrimeOutputData} captured during the test.
             *
             * @return the captured {@link OptimusPrimeOutputData}.
             */
            public OptimusPrimeOutputData getCapturedOutputData() {
                return capturedOutputData;
            }
        };

        // Mock Factory to generate a bot with a static prompt
        mockFactory = new BotFactory() {
            @Override
            public OptimusPrime create() {
                return new OptimusPrime() {
                    @Override
                    public String getPrompt() {
                        return "Autobots, roll out!";
                    }
                };
            }
        };
    }

    /**
     * Tests the successful execution of the {@link OptimusPrimeInteractor}.
     * <p>
     * Ensures the interactor correctly retrieves input data, generates an Optimus Prime bot via the factory,
     * and calls the presenter's {@code beginChat} method with the expected output data.
     */
    @Test
    void testSuccessfulExecution() {
        // Arrange
        OptimusPrimeInputData inputData = new OptimusPrimeInputData("Sam");
        OptimusPrimeInteractor interactor = new OptimusPrimeInteractor(mockPresenter, mockFactory);

        // Act
        interactor.execute(inputData);

        // Assert
        assertTrue(((OptimusPrimeOutputBoundary) mockPresenter).isBeginChatCalled(), "The beginChat method should have been called.");
        OptimusPrimeOutputData outputData = ((OptimusPrimeOutputBoundary) mockPresenter).getCapturedOutputData();
        assertNotNull(outputData, "Output data should not be null.");
        assertEquals("Sam", outputData.getUsername(), "Username should match the input data.");
        assertEquals("Autobots, roll out!", outputData.getPrompt(), "Prompt should match the bot's prompt.");
    }

    /**
     * Tests the behavior of the {@link OptimusPrimeInteractor} when null input data is provided.
     * <p>
     * Expects a {@link NullPointerException} to be thrown.
     */
    @Test
    void testNullInputData() {
        // Arrange
        OptimusPrimeInteractor interactor = new OptimusPrimeInteractor(mockPresenter, mockFactory);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> interactor.execute(null), "Null input data should throw NullPointerException.");
    }

    /**
     * Tests the {@link OptimusPrimeOutputData} class to ensure correct initialization and retrieval of fields.
     */
    @Test
    void testOptimusPrimeOutputData() {
        // Arrange
        OptimusPrimeOutputData outputData = new OptimusPrimeOutputData("Alice", "We are here. We are waiting.");

        // Act & Assert
        assertEquals("Alice", outputData.getUsername(), "Username should match the constructor value.");
        assertEquals("We are here. We are waiting.", outputData.getPrompt(), "Prompt should match the constructor value.");
    }
}
