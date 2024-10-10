import java.util.ArrayList;
import java.util.Scanner;

public class BoyerMooreSearch {

    private static final String[] states = {
        "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", 
        "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", 
        "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", 
        "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", 
        "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", 
        "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", 
        "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", 
        "Wisconsin", "Wyoming"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Display the text");
            System.out.println("2. Search");
            System.out.println("3. Exit program");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    displayStates();
                    break;
                case 2:
                    System.out.print("Enter a pattern to search: ");
                    String pattern = scanner.nextLine();
                    searchPattern(pattern);
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void displayStates() {
        for (String state : states) {
            System.out.println(state);
        }
    }


    private static void searchPattern(String pattern) {
        ArrayList<Integer> occurrences = new ArrayList<>();
        for (int i = 0; i < states.length; i++) {
            int pos = boyerMooreSearch(states[i].toLowerCase(), pattern.toLowerCase());
            if (pos != -1) {
                occurrences.add(i);
            }
        }

        if (occurrences.isEmpty()) {
            System.out.println("No matches found.");
        } else {
            System.out.println("Pattern found in the following states:");
            for (int index : occurrences) {
                System.out.println(states[index] + " (Index: " + index + ")");
            }
        }
    }


    private static int[] badCharHeuristic(String pattern) {
        final int ALPHABET_SIZE = 256;
        int[] badChar = new int[ALPHABET_SIZE];

        for (int i = 0; i < ALPHABET_SIZE; i++) {
            badChar[i] = -1;
        }


        for (int i = 0; i < pattern.length(); i++) {
            badChar[pattern.charAt(i)] = i;
        }

        return badChar;
    }


    private static int boyerMooreSearch(String text, String pattern) {
        int m = pattern.length();
        int n = text.length();
        int[] badChar = badCharHeuristic(pattern);

        int shift = 0;

        while (shift <= (n - m)) {
            int j = m - 1;

            while (j >= 0 && pattern.charAt(j) == text.charAt(shift + j)) {
                j--;
            }

            if (j < 0) {
                return shift;
            } else {
                shift += Math.max(1, j - badChar[text.charAt(shift + j)]);
            }
        }

        return -1;
    }
}
