package chucknorris;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Please input operation (encode/decode/exit):");
            String input = scanner.nextLine();
            switch (input) {
                case "encode":
                    encode();
                    break;
                case "decode":
                    decode();
                    break;
                case "exit":
                    System.out.println("Bye!");
                    System.exit(0);
                    break;
                default:
                    System.out.printf("There is no '%s' operation\n", input);
            }
        }
    }

    public static void encode() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input string:");
        String input = scanner.nextLine();
        char[] chars = input.toCharArray();
        System.out.println("Encoded string:");
        char currentBit = ' ';
        for (char c : chars) {
            String binary = String.format("%7s", Integer.toBinaryString(c)).replace(' ', '0');
            char[] bits = binary.toCharArray();
            for (char b : bits) {
                if (b != currentBit) {
                    System.out.print(b == '1' ? " 0 " : " 00 ");
                    currentBit = b;
                }
                System.out.print("0");
            }
        }
        System.out.println();
    }

    public static void decode() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input encoded string:");
        String input = scanner.nextLine();
        String[] inputParts = input.split(" ");
        StringBuilder binary = new StringBuilder();
        char currentBit = ' ';
        char lastBit = ' ';
        for (String part : inputParts) {
            if (currentBit == ' ') {
                currentBit = part.equals("0") ? '1' : part.equals("00") ? '0' : ' ';
                if (currentBit == lastBit) {
                    System.out.println("Encoded string is not valid.");
                    return;
                }
            } else {
                binary.append(String.valueOf(currentBit).repeat(part.length()));
                lastBit = currentBit;
                currentBit = ' ';
            }
        }
        if (binary.length() % 7 != 0) {
            System.out.println("Encoded string is not valid.");
            return;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < binary.length() / 7; ++i) {
            result.append((char) Integer.parseInt(binary.substring(i * 7, (i + 1) * 7), 2));
        }
        System.out.println(result.isEmpty() ? "Encoded string is not valid." : "Decoded string:\n" + result);
    }
}