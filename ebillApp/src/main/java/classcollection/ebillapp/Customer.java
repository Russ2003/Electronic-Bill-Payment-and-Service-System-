package classcollection.ebillapp;

public class Customer {

    private String username;
    private String password;
    private String fullName;
    private String address;
    private int customerId;


    Customer(String fullName, String username, String password, int customerId, String address) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.customerId = customerId;
        this.address = address;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public int getCustomerId() {
        return customerId;
    }
}
