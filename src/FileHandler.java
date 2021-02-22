import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class FileHandler {
    private File fileReader;
    private StringBuilder buffer;

    public FileHandler(String fileName) throws FileNotFoundException {
        this.fileReader = new File(fileName);
        buffer = new StringBuilder();
    }

    public void getDataFromFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(fileReader);
        while (scanner.hasNextLine()) {
            buffer.append(scanner.nextLine()).append("\n");
        }
    }

    public String toString() {
        return buffer.toString();
    }
}
