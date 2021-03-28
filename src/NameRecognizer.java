import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;

import static java.time.temporal.ChronoUnit.YEARS;

public class NameRecognizer {
    private String FIO = "";
    private String rawBirthDate = "";
    private static final String sep = ".:/- ";
    private final int[] months = {
            Calendar.JANUARY,
            Calendar.FEBRUARY,
            Calendar.MARCH,
            Calendar.APRIL,
            Calendar.MAY,
            Calendar.JUNE,
            Calendar.JULY,
            Calendar.AUGUST,
            Calendar.SEPTEMBER,
            Calendar.OCTOBER,
            Calendar.NOVEMBER,
            Calendar.DECEMBER,
    };

    NameRecognizer(String fio, String date) {
        FIO = fio;
        rawBirthDate = date;
    }

    NameRecognizer() {

    }

    public void getInput() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your full name, please:");
        FIO = in.nextLine();
        System.out.println("Enter your birth date:");
        System.out.println("(format is 'DD<sep>MM<sep>YYYY', <sep> can be: '.', ':', '/', '-', ' ')");
        rawBirthDate = in.nextLine();
    }

    private List<String> parseName(String rawName) {
        List<String> result = new ArrayList<String>();
        StringBuilder buffer = new StringBuilder();
        for (char s : (rawName + " ").toCharArray()) {
            if (s == ' ') {
                result.add(buffer.toString());
                buffer = new StringBuilder();
            } else {
                buffer.append(s);
            }
        }
        return result;
    }

    public String getFIO() {
        return FIO;
    }

    private void sendErrorMessageToUser() {
        System.out.println("Error in date!");
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getRawBirthDate() {
        return rawBirthDate;
    }

    public void setRawBirthDate(String rawBirthDate) {
        this.rawBirthDate = rawBirthDate;
    }

    public int GetAge() {
        Date d = parseRawDate(rawBirthDate);
        int years = LocalDateTime.now().getYear() - d.getYear();
        if (LocalDateTime.now().getMonth().getValue() - 1 < d.getMonth() ||
                LocalDateTime.now().getMonth().getValue() - 1 == months[d.getMonth()] &&
                        LocalDateTime.now().getDayOfMonth() < d.getDate()) {
            years--;
        }
        return years;
    }

    public String getSex() {
        List<String> name = parseName(FIO);
        char identifier = name.get(2).charAt(name.get(2).length() - 1);
        if (identifier == 'ч') {
            return "Male";
        } else if (identifier == 'а') {
            return "Female";
        }
        return "Unknown";
    }

    public String getInitials() {
        List<String> name = parseName(FIO);
        StringBuilder result = new StringBuilder();
        for (String s : name) {
            result.append(s.charAt(0)).append(". ");
        }
        return result.toString();
    }

    private Date parseRawDate(String rawDate) throws DateTimeParseException, NumberFormatException {
        int day = 0, month = 0, year = 0;
        Date result = new Date();
        int condition = 0;
        StringBuilder buffer = new StringBuilder();
        for (char sym : (rawDate + " ").toCharArray()) {
            boolean isSep = false;
            for (char sep : sep.toCharArray()) {
                if (sym == sep) {
                    isSep = true;
                    int res = Integer.parseInt(buffer.toString());
                    if (condition == 0) {
                        day = res;
                    } else if (condition == 1) {
                        month = res;
                    } else if (condition == 2) {
                        year = res;
                    }
                    buffer = new StringBuilder();
                    condition++;
                    break;
                }
            }
            if (isSep) {
                continue;
            }
            buffer.append(sym);
        }

        if (day > 31 || month > 12 || year > LocalDateTime.now().getYear() || condition != 3) {
            sendErrorMessageToUser();
            throw new DateTimeParseException("Wrong numbers", rawDate, -1);
        }

        result.setDate(day);
        result.setMonth(months[month - 1]);
        result.setYear(year);
        return result;
    }
}
