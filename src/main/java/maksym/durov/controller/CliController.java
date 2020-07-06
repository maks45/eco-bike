package maksym.durov.controller;

import java.util.Objects;
import java.util.Scanner;
import org.springframework.stereotype.Component;

@Component
public class CliController {
    private final Scanner scanner;

    public CliController(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getInput() {
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public boolean getBooleanFromInput(String message) {
        showMessage(message);
        String input = getInput();
        if (!Objects.equals(input, "y") && !Objects.equals(input, "n")) {
            System.out.println(String.format("%s - wrong input", input));
            return getBooleanFromInput(message);
        }
        return input.equals("y");
    }

    public String getNotEmptyString(String fieldName) {
        System.out.println(String.format("Please enter %s: ", fieldName));
        String input = scanner.nextLine();
        if (input == null || input.trim().length() == 0) {
            System.out.println(String.format("%s can't be empty", fieldName));
            return getNotEmptyString(fieldName);
        }
        return input;
    }

    public int getPositiveInt(String fieldName) {
        System.out.println(String.format("Please enter %s: ", fieldName));
        String input = scanner.nextLine();
        Integer result = getParseInt(input);
        if (result == null) {
            System.out.println(String.format("%s should be a positive number", fieldName));
            return getPositiveInt(fieldName);
        }
        return result;
    }

    public boolean getBoolean(String fieldName) {
        System.out.println(String.format("Please enter %s (y/n)", fieldName));
        String input = scanner.nextLine();
        if (!input.equals("y") && !input.equals("n")) {
            System.out.println(String.format("%s -  not valid input", input));
            return getBoolean(fieldName);
        }
        return input.equals("y");
    }

    public Integer getParseInt(String input) {
        if (!input.matches("[0-9]+")) {
            return null;
        }
        return Integer.parseInt(input);
    }

    public Boolean getParsedBool(String input) {
        if ("".equals(input)) {
            return null;
        }
        return input.equals("y");
    }

    public String getStringOrNull(String input) {
        if ("".equals(input)) {
            return null;
        }
        return input;
    }
}
