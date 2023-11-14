package lastpencil;
import java.util.Scanner;
import java.util.Random;

public class Main {

    public static int gamePlayer(int pencils) {
        Scanner scanner = new Scanner(System.in);
        int temp;
        do {
            int inputTemp; // number of pencils to be picked
            String inputTempString = scanner.nextLine();
            try {
                Integer.parseInt(inputTempString);
            } catch (Exception e) {
                System.out.println("Possible values: '1', '2' or '3'");
                continue;
            } // checking if the player's input is a number
            inputTemp = Integer.parseInt(inputTempString); // assigning player's input (when it is a number)
            if (inputTemp > 3 || inputTemp <= 0) { // conditions for picking the allowed number of pencils
                System.out.println("Possible values: '1', '2' or '3'");
                continue;
            } else if (inputTemp > pencils) {
                System.out.println("Too many pencils were taken");
                continue;
            } else {
                temp = inputTemp;
            }
            break;
        } while (true);
        for (int i = 0; i < pencils - temp; i++) {
            System.out.print("|");
        } // printing pencils[graphic representation] left on the table
        return pencils - temp; // returning the remaining number of pencils on the table
    } // the player turn method

    public static int gameBot(int pencils) {
        Random random = new Random();
        int temp = 0; // the number of pencils picked
        if (pencils == 1) { // conditions for setting the number of pencils picked by the bot
            temp = 1;
        } else if (pencils % 4 == 1) {
            temp = random.nextInt(3) + 1;
        } else if (pencils % 4 == 0) {
            temp = 3;
        } else if ((pencils - 3) % 4 == 0) {
            temp = 2;
        } else if ((pencils - 2) % 4 == 0) {
            temp = 1;
        }
        System.out.println(temp); // printing the number[digit] of pencils picked by the bot
        for (int i = 0; i < pencils - temp; i++) {
            System.out.print("|");
        } // printing pencils[graphic representation] left on the table
        return pencils - temp;
    } // the bot turn method

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many pencils would you like to use:");
        int pencils; // to be the starting number of pencils
        do {
            String pencilsInput = scanner.nextLine();
            try {
                Integer.parseInt(pencilsInput);
            } catch (Exception e) {
                System.out.println("The number of pencils should be numeric");
                continue;
            } // checking of input is numeric
            pencils = Integer.parseInt(pencilsInput); // assigning when input is numeric
            if (pencils == 0) { // other conditions when input is numeric
                System.out.println("The number of pencils should be positive");
                continue;
            } else if (pencils < 0) {
                System.out.println("The number of pencils should be numeric");
                continue;
            }
            break;
        } while (true); // setting the number of pencils

        String name1 = "John"; // the player
        String name2 = "Jack"; // the bot
        String cluster = name1 + name2;
        System.out.printf("Who will be the first (%s, %s):\n", name1, name2);
        String firstPlayer;
        do {
            String nameInput = scanner.nextLine();
            if (nameInput.equals(name1) || nameInput.equals(name2))  {
                firstPlayer = nameInput;
            } else {
                System.out.printf("Choose between %s and %s\n", name1, name2);
                continue;
            }
            break;
        } while (true); // picking who starts the game : the player or the bot

        String secondPlayer = cluster.replace(firstPlayer, "");
        for (int i = 0; i < pencils; i++) {
            System.out.print("|");
        } // printing the initial number of pencils on the table

        do {
            System.out.printf("\n%s's turn!\n", firstPlayer);
            if (firstPlayer.equals(name1)) {
                pencils = gamePlayer(pencils);
            } else {
                pencils = gameBot(pencils);
            } // condition for who goes 1st : the player or the bot

            if (pencils == 0) {
                System.out.printf("%s won!", secondPlayer);
                break;
            } // condition for winning - stops the game
            System.out.printf("\n%s's turn!\n", secondPlayer);

            if (firstPlayer.equals(name1)) {
                pencils = gameBot(pencils);
            } else {
                pencils = gamePlayer(pencils);
            } // condition for who goes 2nd : the player or the bot

            if (pencils == 0) {
                System.out.printf("%s won!", firstPlayer);
                break;
            } // condition for winning - stops the game
        } while (true); // the game


    }
}
