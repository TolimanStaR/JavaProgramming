import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileHandler {
    private final File fileReader;
    private final StringBuilder buffer;
    HashMap<String, Integer> counter;

    public FileHandler(String fileName) throws FileNotFoundException {
        this.fileReader = new File(fileName);
        buffer = new StringBuilder();
    }

    public void getDataFromFile() throws FileNotFoundException {
        if (fileReader.exists()) {
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()) {
                buffer.append(scanner.nextLine()).append("\n");
            }
        }
    }

    public void getData() {
        counter = new HashMap<String, Integer>();
        for (char s : buffer.toString().toCharArray()) {
            if (!counter.containsKey(String.valueOf(s))) {
                counter.put(String.valueOf(s), 1);
            } else {
                int cnt = counter.get(String.valueOf(s));
                counter.put(String.valueOf(s), cnt + 1);
            }
        }
    }

    public String getResult() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry element : counter.entrySet()) {
            result.append("Symbol: ").append(element.getKey()).append(" - ").append(element.getValue()).append(" times\n");
        }
        return result.toString();
    }

    public void saveData() throws IOException {
        String fileResult = fileReader.getName() + "_result.txt";
        BufferedWriter output = new BufferedWriter(new FileWriter(fileResult));
        output.write(getResult());
        output.close();
    }

    public String toString() {
        return buffer.toString();
    }
}
