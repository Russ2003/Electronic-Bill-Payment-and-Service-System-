package classcollection.ebillapp;

public class TransactionHistory {

    private int month;
    private String dueDate;
    private int consumption;
    private double amount;
    private double amountDue;
    private String status;

    public TransactionHistory(int month, String dueDate, int consumption, double amount, double amountDue, String status) {
        this.month = month;
        this.dueDate = dueDate;
        this.consumption = consumption;
        this.amount = amount;
        this.amountDue = amountDue;
        this.status = status;
    }

    public int getMonth() {
        return month;
    }


    public String getDueDate() {
        return dueDate;
    }


    public int getConsumption() {
        return consumption;
    }


    public int getAmount() {
        return (int) amount;
    }


    public double getAmountDue() {
        return amountDue;
    }


    public String getStatus() {
        return status;
    }

}
