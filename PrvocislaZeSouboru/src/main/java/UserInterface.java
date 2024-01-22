import java.io.FileInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * The class provides functionality for reading numbers from an Excel file and printing the prime numbers to a logging system.
 */
public class UserInterface {
    private Logger logger = LogManager.getLogger(UserInterface.class);
    private DecimalFormat decimalFormat = new DecimalFormat("#");
    private double number;
    private String value;

    /**
     * Constructs a new UserInterface with default settings.
     */
    public UserInterface(){}

    /**
     * Constructs a new UserInterface with a specified logger.
     *
     * @param logger The logger to be used for logging messages.
     */
    public UserInterface(Logger logger) {
        this.logger = logger;
    }

    /**
     * Checks if a given number is a prime number.
     *
     * @param number The number to be checked for primality.
     * @return the number if it is prime
     */
    public static boolean isPrime(double number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Reads numbers from an Excel file specified by the sourceFile parameter and prints the prime numbers to the logging system.
     *
     * @param sourceFile The path to the Excel file.
     */
    public void printPrimesFromFile(String sourceFile) {
        try (FileInputStream file = new FileInputStream(sourceFile)) {
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0); // Works with the first sheet (index 0)
            for (Row row : sheet) {
                for (Cell cell : row) {
                    value = cell.toString();
                        try {
                            number = Double.parseDouble(value);
                            if(UserInterface.isPrime(number)){
                                logger.info(decimalFormat.format(number) + "\t");
                            }
                        } catch (NumberFormatException e){}
                    }
            }
            file.close();
        } catch (IOException ex) {
            logger.error("Chyba při čtení souboru", ex);
        }
    }
}
