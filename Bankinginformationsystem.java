
import java.util.Scanner;
public class   Bankinginformationsystem{
public static void main(String[] args) 
{

       Scanner sc = new Scanner(System.in);                                                                                // User Registration
       System.out.println("----- User Registration -----");
       System.out.print("Enter your name: ");
       String name = sc.nextLine();
       System.out.print("Enter your account number: ");
       String accountNumber = sc.nextLine();
       System.out.print("Enter your initial balance: ");
       int initialBalance = sc.nextInt();
       sc.nextLine(); 
       System.out.print("Create a password: ");
       String password = sc.nextLine();
       BankAccount bankAccount = new BankAccount(name, accountNumber, initialBalance, password);

       System.out.println("**Registration Successful!**");
       System.out.println();

                                                                                                    // User Login
       System.out.println("----- User Login -----");
       System.out.print("Enter your account number: ");
       String enteredAccountNumber = sc.nextLine();
       System.out.print("Enter your password: ");
       String enteredPassword = sc.nextLine();

       if (bankAccount.authenticate(enteredAccountNumber, enteredPassword)) {
           System.out.println("Login Successful!");
           System.out.println();
           bankAccount.showMenu();
       } else {
           System.out.println("Invalid account number or password. Exiting...");
       }
   }
}

class BankAccount {
   String name;
   String accountNumber;
   int balance;
   int previousTransaction;
   String password;

   public BankAccount(String name, String accountNumber, int initialBalance, String password) {
       this.name = name;
       this.accountNumber = accountNumber;
       this.balance = initialBalance;
       this.previousTransaction = 0;
       this.password = password;
   }

   void deposit(int amount) {
       if (amount > 0) {
           balance += amount;
           previousTransaction = amount;
           System.out.println("Deposited: " + amount);
       }
   }

   void withdraw(int amount) {
       if (amount > 0) {
           balance -= amount;
           previousTransaction = -amount;
           System.out.println("Withdrawn: " + amount);
       }
   }

   void transfer(String recipientAccountNumber, int amount) {
       if (amount > 0 && amount <= balance) {
           balance -= amount;
           System.out.println("Transferred " + amount + " units to account number: " + recipientAccountNumber);
       } else {
           System.out.println("Insufficient balance or invalid transfer amount.");
       }
   }

   void getPreviousTransaction() {
       if (previousTransaction > 0) {
           System.out.println(" Deposited: " + previousTransaction);
       } else if (previousTransaction < 0) {
           System.out.println("Previous Transaction :Withdrawn= " + Math.abs(previousTransaction));
       } else {
           System.out.println("No transaction occurred");
       }
   }

   boolean authenticate(String accountNumber, String password) {
       return this.accountNumber.equals(accountNumber) && this.password.equals(password);
   }

   void showMenu() {
       char option = '\0';
       Scanner sc = new Scanner(System.in);

       System.out.println("Welcome " + name);
       System.out.println("Account number is " + accountNumber);
       System.out.println();
       System.out.println("1. Check balance");
       System.out.println("2. Deposit");
       System.out.println("3. Withdraw");
       System.out.println("4. Previous transaction");
       System.out.println("5. Fund Transfer");
       System.out.println("6. Exit");

       do {
           System.out.println("=====================");
           System.out.println("Enter the option");
           System.out.println("=====================");
           option = sc.next().charAt(0);

           switch (option) {
               case '1':
                   System.out.println("=====================");
                   System.out.println("Balance is " + balance);
                   System.out.println("=====================");
                   System.out.println();
                   break;
               case '2':
                   System.out.println("=====================");
                   System.out.println("Enter the amount to deposit");
                   System.out.println("=====================");
                   int amount = sc.nextInt();
                   deposit(amount);
                   System.out.println();
                   break;
               case '3':
                   System.out.println("=====================");
                   System.out.println("Enter the amount to withdraw");
                   System.out.println("=====================");
                   int amount2 = sc.nextInt();
                   withdraw(amount2);
                   System.out.println();
                   break;
               case '4':
                   System.out.println("=====================");
                   getPreviousTransaction();
                   System.out.println("=====================");
                   System.out.println();
                   break;
               case '5':
                   System.out.println("=====================");
                   System.out.print("Enter recipient's account number: ");
                   String recipientAccountNumber = sc.next();
                   System.out.print("Enter amount to transfer: ");
                   int transferAmount = sc.nextInt();
                   transfer(recipientAccountNumber, transferAmount);
                   System.out.println("=====================");
                   System.out.println();
                   break;
               case '6':
                   System.out.println("========================");
                   break;
               default:
                   System.out.println("Invalid option. Please try again.");
           }
       } while (option != '6');
   }

}