package Tracker;

import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private List<Object> transactions = new ArrayList<>();
    private float balance = 0.0f;

    public void addIncome(float amount, String source){
        try {
                if(amount <= 0){
                    throw new IllegalArgumentException("Income amount must be greater than zero.");
                }
                if(source == null || source.isEmpty()){
                    throw new IllegalArgumentException("Income source cannot be null or empty.");
                }

            Income income = new Income(amount, source);
            transactions.add(income);
            balance += amount;
            System.out.println("Income added:-  ");
            System.out.println("Source: " + income.getSource() + "    Amount: " + income.getAmount());
        }catch(IllegalArgumentException e){ //catches known issues like invalid amount
            System.out.println("Error adding income: "+ e.getMessage());
        }catch (Exception e){ //catches anything else
            System.out.println("An unexpected error occurred while adding income.");
        }
    }

    public void addExpense(float amount, String category) {
        try {
            if(amount <= 0){
                throw new IllegalArgumentException("Expense amount must be greater than zero.");
            }
            if(category == null || category.isEmpty()){
                throw new IllegalArgumentException("Expense category cannot be null or empty.");
            }
            if (amount > balance) {
                System.out.println("Insufficient Balance for this expense!!");
                return;
            }
            Expense expense = new Expense(amount, category);
            transactions.add(expense);
            balance -= amount;
            System.out.println("Expense added:- ");
            System.out.println("Category: " + expense.getCategory() + "    Amount: " + expense.getAmount());
        }catch (IllegalArgumentException e){
            System.out.println("Error adding expense: "+ e.getMessage());
        }catch (Exception e){
            System.out.println("An unexpected error occurred while adding expense.");
        }
    }

    public void viewTransactions() {
        try {
            if (transactions.isEmpty()) {
                System.out.println("No transactions found.");
                return;
            }

            System.out.println("====== Transaction History =====");
            for (int i = 0; i <transactions.size(); i++)
            //declares a variable "transaction" of type "object" and iterates the already present transactions
            {
                Object transaction = transactions.get(i); //retrieves element at index i
                //checks if object is of type "Income" or "Expense"
                if (transaction instanceof Income) {
                    Income income = (Income) transaction; //typecasting the object back to original type to access its methods

                    System.out.println(i+ ". Income:- Source: " + income.getSource() + "    Amount: " + income.getAmount());
                } else if (transaction instanceof Expense) {
                    Expense expense = (Expense) transaction;

                    System.out.println(i+ ". Expense:- Category: " + expense.getCategory() + "    Amount: " + expense.getAmount());
                }

            }
            System.out.println("=================================");
        }catch (Exception e){
            System.out.println("An unexpected error occurred while viewing transactions.");
        }
    }

    public void editTransaction(int index, float newAmount, String newDetail) {
        try {
            if (index < 0 || index >= transactions.size()) {
                throw new IndexOutOfBoundsException("Invalid transaction index.");
            }
            if(newAmount<=0 || newDetail == null || newDetail.trim().isEmpty()){
                throw new IllegalArgumentException("Invalid new transaction details.");
            }

            Object transaction = transactions.get(index); //retrieves either Income or Expense object
            if(transaction instanceof Income){
                Income income = (Income) transaction;
                balance -= income.getAmount(); //balancing the amount
                income = new Income(newAmount, newDetail);
                transactions.set(index, income);
                balance += newAmount; //adding new income
                System.out.println("Income updated.");
                System.out.println("New Income:- Source: "+newDetail+" , Amount: "+newAmount);
            }else if(transaction instanceof Expense){
                Expense expense = (Expense) transaction;
                balance += expense.getAmount(); //refunding old expense
                expense = new Expense(newAmount, newDetail);
                transactions.set(index, expense);
                balance -= newAmount; //deducting new expense
                System.out.println("Expense updated.");
                System.out.println("New Expense:- Category: "+newDetail+" , Amount: "+newAmount);
            }else {
                throw new IllegalArgumentException("Unknown transaction type.");
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println("Error: "+e.getMessage());
        }catch (IllegalArgumentException e){
            System.out.println("Failed to edit transaction: "+e.getMessage());
        }catch (Exception e){
            System.out.println("Unexpected error while editing transaction: "+e.getMessage());
        }
    }

    public void deleteTransaction(int index){
        try{
            if(index < 0 || index >= transactions.size()){
                throw new IndexOutOfBoundsException("Invalid transaction index.");
            }

            Object transaction = transactions.remove(index);
            if(transaction instanceof Income){
                Income income = (Income) transaction;
                balance -= income.getAmount();
                System.out.println("Income deleted.");
            }else if(transaction instanceof Expense){
                Expense expense = (Expense) transaction;
                balance += expense.getAmount();
                System.out.println("Expense deleted.");
            }else {
                throw new IllegalArgumentException("Unknown transaction type.");
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println("Error: "+ e.getMessage());
        }catch (IllegalArgumentException e){
            System.out.println("Failed to delete transaction: "+ e.getMessage());
        }catch (Exception e){
            System.out.println("Unexpected error while deleting transaction: "+ e.getMessage());
        }
    }

    public float getBalance(){
        return balance;
    }
}
