import java.util.ArrayList;
import java.util.Scanner;

public class Task1 {

    // Lists to store user details
    private static ArrayList<String> users = new ArrayList<>();
    private static ArrayList<String> emails = new ArrayList<>();
    private static ArrayList<String> phoneNumbers = new ArrayList<>();
    private static ArrayList<String> passwords = new ArrayList<>();
    private static ArrayList<Integer> balances = new ArrayList<>();

    public static void main(String[] args) {
        // Initializing with sample data
        users.add("a");
        emails.add("a@gmail.com");
        phoneNumbers.add("9848485773");
        passwords.add("123");
        balances.add(100);

        users.add("b");
        emails.add("b@gmail.com");
        phoneNumbers.add("3859274644");
        passwords.add("456");
        balances.add(200);

        users.add("c");
        emails.add("c@gmail.com");
        phoneNumbers.add("2937593758");
        passwords.add("789");
        balances.add(300);

        users.add("d");
        emails.add("d@gmail.com");
        phoneNumbers.add("9346226370");
        passwords.add("101");
        balances.add(400);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Do you want to login or signup? (Type 'exit' to quit): ");
            String choice = scanner.nextLine().toLowerCase();
            if (choice.equals("login")) {
                login(scanner);
            } else if (choice.equals("signup")) {
                signup(scanner);
            } else if (choice.equals("exit")) {
                System.out.println("Exiting the program. Goodbye!");
                break;
            } else {
                System.out.println("Invalid option. Please type 'login', 'signup', or 'exit'.");
            }
        }
        scanner.close();
    }

    public static void signup(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if (users.contains(username)) {
            System.out.println("Username already taken, please try another.");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Confirm password: ");
        String confirmPassword = scanner.nextLine();
        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match, please try again.");
            return;
        }

        System.out.print("Enter email ID: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter deposit amount: ");
        int amount;
        try {
            amount = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount entered. Please enter a number.");
            return;
        }

        // Adding new user details to respective lists
        users.add(username);
        emails.add(email);
        passwords.add(password);
        phoneNumbers.add(phoneNumber);
        balances.add(amount);

        System.out.println("Signup successful. Welcome, " + username + "!");
    }

    public static void login(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        if (!users.contains(username)) {
            System.out.println("Username not found. Please sign up first.");
            return;
        }

        int index = users.indexOf(username);
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        if (!password.equals(passwords.get(index))) {
            System.out.println("Incorrect password. Please try again.");
            return;
        }

        System.out.println("Login successful.");
        while (true) {
            System.out.print("Enter your choice: 1) Withdraw 2) Deposit 3) Check Balance 4) Logout: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    // Withdraw
                    System.out.print("Enter amount to withdraw: ");
                    int withdrawAmount;
                    try {
                        withdrawAmount = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount entered. Please enter a number.");
                        continue;
                    }

                    if (withdrawAmount > balances.get(index)) {
                        System.out.println("Insufficient balance.");
                    } else {
                        balances.set(index, balances.get(index) - withdrawAmount);
                        System.out.println("Withdrawal successful. New balance: " + balances.get(index));
                    }
                    break;

                case 2:
                    // Deposit
                    System.out.print("Enter amount to deposit: ");
                    int depositAmount;
                    try {
                        depositAmount = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount entered. Please enter a number.");
                        continue;
                    }

                    balances.set(index, balances.get(index) + depositAmount);
                    System.out.println("Deposit successful. New balance: " + balances.get(index));
                    break;

                case 3:
                    // Check balance
                    System.out.println("Your current balance is: " + balances.get(index));
                    break;

                case 4:
                    // Logout
                    System.out.println("Logging out.");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
