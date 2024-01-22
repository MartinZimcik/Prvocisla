import org.junit.Test;
import org.mockito.Mockito;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * The class contains unit tests for the UserInterface class.
 * It utilizes JUnit and Mockito for testing and mocking, ensuring the correctness of the functionality.
 * This test suite covers the isPrime method and verifies the handling of errors when attempting to read numbers from a non-existing file using the printPrimesFromFile method.
 */
public class UserInterfaceTest {

    /**
     * Tests the isPrime method for correctly identifying prime numbers.
     */
    @Test
        public void testIsPrime() {
            assertTrue(UserInterface.isPrime(2));
            assertTrue(UserInterface.isPrime(7));
            assertFalse(UserInterface.isPrime(10));
            assertFalse(UserInterface.isPrime(1));
            assertFalse(UserInterface.isPrime(0));
            assertFalse(UserInterface.isPrime(-5));
        }

    /**
     * Tests the printPrimesFromFile method when given an incorrect file path.
     * It uses Mockito to create a mock Logger to simulate logging errors and verifies that the logger's error method is called with the expected message and IOException.
     */
    @Test
    public void testWrongFilePath() {
        // Create a mock Logger
        Logger mockLogger = Mockito.mock(Logger.class);

        // Create an instance of UserInterface using the mock Logger
        UserInterface userInterface = new UserInterface(mockLogger);

        // Set the path to a non-existing file
        String nonExistingFile = "neexistujiciSoubor.xlsx";

        // Simulate an error in the method that triggers logger.error
        userInterface.printPrimesFromFile(nonExistingFile);

        // Verify that the logger.error method was called with the expected message and IOException
        verify(mockLogger, times(1)).error(eq("Chyba při čtení souboru"), any(IOException.class));
    }
}
