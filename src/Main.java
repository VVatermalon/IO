import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static final String WORD_STARTS_WITH_VOWEL = "(^|\\s|\"|\\()([eyuioaEYUIOAуеыаоэяиюУЕЫАОЭЯИЮ][а-яА-Яa-zA-z\\-]*)";
    static final String WORD = "(^|\\s|\"|\\()([а-яА-Яa-zA-z\\-]+)";
    static final String DIGITS = "\\d+";
    static final String NO_DIGITS_MSG = "No digits ";
    static final String RESOURCES_PATH = "resources";
    static final Path path = Path.of(RESOURCES_PATH, "text.txt");
    static final String JAVA_CODE_PATH = "java.java";
    static final String REPLACED_JAVA_CODE_PATH = "privateJava.java";
    static final String PUBLIC_RGX = "public";
    static final String PRIVATE_STRING = "private";
    static final String JAVA_FOR_REVERSE_PATH = "javaForReverse.java";
    static final String REVERSED_JAVA = "reversedJava.java";

    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5();
    }

    public static void task1 ()  {
        System.out.println("Начинаются с гласных");
        try (Scanner sc = new Scanner(path)) {
            sc.findAll(WORD_STARTS_WITH_VOWEL)
                    .map(r -> r.group(2)).forEach(s -> System.out.print(s + " "));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void task2 ()  {
        System.out.println("\nПоследняя совпадает с первой");
        try (Scanner sc = new Scanner(path)) {
            var result = sc.findAll(WORD)
                    .map(r -> r.group(2)).toList();
            ArrayList<String> list = new ArrayList<>(result);
            for (int i = 0; i < list.size() - 1; i++) {
                String word1 = list.get(i);
                String word2FirstChar = String.valueOf(list.get(i + 1).charAt(0)).toLowerCase();
                if (word1.endsWith(word2FirstChar) ||
                        word1.endsWith(word2FirstChar.toUpperCase())) {
                    System.out.print(word1 + " ");
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void task3 ()  {
        System.out.println("\nСамая длинная цифра");
        try (Scanner sc = new Scanner(path)) {
            while (sc.hasNextLine()) {
                String digit;
                String largestDigit = "";
                while ((digit = sc.findInLine(DIGITS)) != null) {
                    if (digit.length() > largestDigit.length()) {
                        largestDigit = digit;
                    }
                }
                System.out.print(largestDigit.isEmpty() ? NO_DIGITS_MSG : largestDigit + " ");
                sc.nextLine();
            }
            sc.findAll(WORD_STARTS_WITH_VOWEL)
                    .map(r -> r.group(2)).forEach(s -> System.out.print(s + " "));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void task4 ()  {
        Path javaPath = Path.of(RESOURCES_PATH, JAVA_CODE_PATH);
        Path replacedJavaPath = Path.of(RESOURCES_PATH, REPLACED_JAVA_CODE_PATH);
        try {
            String original = Files.readString(javaPath);
            Files.write(replacedJavaPath, original.replaceAll(PUBLIC_RGX, PRIVATE_STRING).getBytes(), StandardOpenOption.TRUNCATE_EXISTING);

        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void task5 ()  {
        Path javaForReversePath = Path.of(RESOURCES_PATH, JAVA_FOR_REVERSE_PATH);
        Path reversedJavaPath = Path.of(RESOURCES_PATH, REVERSED_JAVA);
        try {
            List<String> lines = new ArrayList<>();
            Files.readAllLines(javaForReversePath)
                    .forEach(s -> {
                        StringBuilder sb = new StringBuilder(s);
                        sb.reverse();
                        lines.add(sb.toString());
                    });
            Files.write(reversedJavaPath, lines, StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}