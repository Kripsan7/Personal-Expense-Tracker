package Tracker;

public class Expense {
    private float amount;
    private String category;

    public Expense(float amount, String category){
        if(amount <= 0){
            throw new IllegalArgumentException("Expense must be greater than zero.");
        }
        if(category == null || category.trim().isEmpty()){
            throw new IllegalArgumentException("Expense category cannot be null or empty.");
        }
        this.amount = amount;
        this.category = category;
    }

    public float getAmount(){
        return amount;
    }

    public String getCategory(){
        return category;
    }

}
