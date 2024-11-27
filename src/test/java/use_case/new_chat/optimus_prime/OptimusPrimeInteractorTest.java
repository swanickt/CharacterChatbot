package use_case.new_chat.optimus_prime;

import entity.bot.Bot;
import entity.bot.BotFactory;
import entity.bot.OptimusPrime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OptimusPrimeInteractorTest {

    private OptimusPrimeOutputBoundary mockPresenter;
    private BotFactory mockFactory;

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

            public boolean isBeginChatCalled() {
                return beginChatCalled;
            }

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

    @Test
    void testNullInputData() {
        // Arrange
        OptimusPrimeInteractor interactor = new OptimusPrimeInteractor(mockPresenter, mockFactory);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> interactor.execute(null), "Null input data should throw NullPointerException.");
    }

    @Test
    void testOptimusPrimeOutputData() {
        // Arrange
        OptimusPrimeOutputData outputData = new OptimusPrimeOutputData("Alice", "We are here. We are waiting.");

        // Act & Assert
        assertEquals("Alice", outputData.getUsername(), "Username should match the constructor value.");
        assertEquals("We are here. We are waiting.", outputData.getPrompt(), "Prompt should match the constructor value.");
    }
}

