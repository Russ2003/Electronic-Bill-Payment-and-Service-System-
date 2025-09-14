package classcollection.ebillapp;

public class CustomerNode {
    Customer customer;
    CustomerNode left;
    CustomerNode right;

    public CustomerNode(Customer customer) {
        this.customer = customer;
        left = right = null;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CustomerNode getLeft() {
        return left;
    }

    public void setLeft(CustomerNode left) {
        this.left = left;
    }

    public CustomerNode getRight() {
        return right;
    }

    public void setRight(CustomerNode right) {
        this.right = right;
    }
}
