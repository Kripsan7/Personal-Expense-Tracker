package Tracker;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Transaction manager = new Transaction();

        while (true){
            System.out.println("\n===== Personal Expense Tracker =====");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Transactions");
            System.out.println("4. Check Balance");
            System.out.println("5. Edit Transaction");
            System.out.println("6. Delete Transaction");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            sc.nextLine(); // reads the leftover newline instead of waiting for new input (clears input buffer)

            switch (choice){
                case 1:
                    System.out.print("Enter income amount:  ");
                    float incomeAmount = sc.nextFloat();
                    sc.nextLine();
                    System.out.print("Enter income source: ");
                    String incomeSource =sc.nextLine();
                    manager.addIncome(incomeAmount, incomeSource);
                    break;

                case 2:
                    System.out.print("Enter expense amount: ");
                    float expenseAmount = sc.nextFloat();
                    sc.nextLine();
                    System.out.print("Enter expense category: ");
                    String expenseCategory = sc.nextLine();
                    manager.addExpense(expenseAmount, expenseCategory);
                    break;

                case 3:
                    manager.viewTransactions();
                    break;

                case 4:
                    System.out.println("Current Balance: "+ manager.getBalance());
                    break;

                case 5:
                    System.out.print("Enter the index of transaction to edit: ");
                    int trans_index = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter the new amount: ");
                    float trans_newAmount = sc.nextFloat();
                    sc.nextLine();
                    System.out.print("Enter the detail about this edit: ");
                    String trans_newDetail = sc.nextLine();
                    manager.editTransaction(trans_index, trans_newAmount, trans_newDetail);
                    break;

                case 6:
                    System.out.println("Enter the index of transaction to delete: ");
                    int delete_index = sc.nextInt();
                    sc.nextLine();
                    manager.deleteTransaction(delete_index);
                    break;

                case 7:
                    System.out.println("Exiting... Have a great day!");
                    sc.close(); // release resources associated with scanner object
                    System.exit(0); //terminates the running program, 0 indicates normal termination
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}