import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PoliticalSurvey {

    private static int republicanScore = 0;
    private static int democratScore = 0;
    private static int greenPartyScore = 0;
    private static int libertarianScore = 0;

    // Questions on survey
    private static String[][] questions = {
        {"What should the government do about taxation?",
         "A. Reduce taxes, the government already takes too much money away from hard working individuals.",
         "B. Increase taxes for the wealthy.",
         "C. Taxation should be based on carbon footprint.",
         "D. Stay out of it, the government has caused enough damage already."},
         
        {"What should the government do about healthcare?",
         "A. Leave it to private companies.",
         "B. Provide universal healthcare for all.",
         "C. Focus on preventative care to reduce costs.",
         "D. Let the free market handle it."},
         
        {"What is your stance on climate change?",
         "A. It's not a major concern.",
         "B. It's the most pressing issue.",
         "C. We should have strict regulations to control it.",
         "D. The government shouldn't interfere."},
         
        // More questions can be added in the format listed above
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Survey with score
        for (String[] question : questions) {
            System.out.println(question[0]);
            for (int i = 1; i < question.length; i++) {
                System.out.println(question[i]);
            }
            String response = scanner.nextLine().toUpperCase();
            updateScores(response);
        }

        // Determine political party based on survey
        String predictedParty = determinePoliticalAffiliation();
        System.out.println("Your predicted political party is: " + predictedParty);

        // Save responses to text file
        saveResponsesToFile(predictedParty);

        scanner.close();
    }

    // Update scores based on user input
    private static void updateScores(String answer) {
        switch (answer) {
            case "A":
                republicanScore += 2;   // Strong alignment
                libertarianScore += 1;  // Moderate alignment
                break;
            case "B":
                democratScore += 2;     // Strong alignment
                greenPartyScore += 1;   // Moderate alignment
                break;
            case "C":
                libertarianScore += 2;  // Strong alignment
                republicanScore += 1;   // Moderate alignment
                break;
            case "D":
                libertarianScore++;
                break;
            default:
                System.out.println("Invalid response. No points awarded.");
        }
    }

    // Determine political party based on survey scores and comparisons between scores6
    private static String determinePoliticalAffiliation() {
        if (republicanScore > democratScore && republicanScore > greenPartyScore && republicanScore > libertarianScore) {
            return "Republican";
        } else if (democratScore > republicanScore && democratScore > greenPartyScore && democratScore > libertarianScore) {
            return "Democrat";
        } else if (greenPartyScore > republicanScore && greenPartyScore > democratScore && greenPartyScore > libertarianScore) {
            return "Green Party";
        } else {
            return "Libertarian";
        }
    }

    // Save user responses to a file based on predicted party
    private static void saveResponsesToFile(String party) {
        try (FileWriter writer = new FileWriter(party + "_responses.txt", true)) {
            writer.write("User responses:\n");
            for (String[] question : questions) {
                writer.write(question[0] + "\n");
            }
            writer.write("Predicted party: " + party + "\n");
            writer.write("Republican Score: " + republicanScore + "\n");
            writer.write("Democrat Score: " + democratScore + "\n");
            writer.write("Green Party Score: " + greenPartyScore + "\n");
            writer.write("Libertarian Score: " + libertarianScore + "\n");
            System.out.println("Responses saved to " + party + "_responses.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the file.");
        }
    }
}
