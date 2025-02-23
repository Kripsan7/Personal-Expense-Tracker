package Tracker;


public class Income {
    private float amount;
    private String source;

    public Income(float amount, String source){
        if(amount <= 0){
            throw new IllegalArgumentException("Income amount must be greater than zero.");
        }
        if(source == null || source.trim().isEmpty()){
            throw new IllegalArgumentException("Income source cannot be null or empty.");
        }
        this.amount = amount;
        this.source = source;
    }

    public float getAmount(){

        return amount;
    }

    public String getSource(){

        return source;
    }
}
