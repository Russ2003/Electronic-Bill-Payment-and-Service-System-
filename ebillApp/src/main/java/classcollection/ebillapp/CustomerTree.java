package classcollection.ebillapp;

public class CustomerTree {
    private CustomerNode root;

    public CustomerTree() {
        root = null;
    }

    public void insert(Customer customer) {
        root = insertRec(root, customer);
    }

    private CustomerNode insertRec(CustomerNode root, Customer customer) {
        if (root == null) {
            root = new CustomerNode(customer);
            return root;
        }

        if (customer.getUsername().compareTo(root.getCustomer().getUsername()) < 0) {
            root.setLeft(insertRec(root.getLeft(), customer));
        } else if (customer.getUsername().compareTo(root.getCustomer().getUsername()) > 0) {
            root.setRight(insertRec(root.getRight(), customer));
        }

        return root;
    }

    public Customer search(String username) {
        return searchRec(root, username);
    }

    private Customer searchRec(CustomerNode root, String username) {
        if (root == null || root.getCustomer().getUsername().equals(username)) {
            return root != null ? root.getCustomer() : null;
        }

        if (username.compareTo(root.getCustomer().getUsername()) < 0) {
            return searchRec(root.getLeft(), username);
        }

        return searchRec(root.getRight(), username);
    }
}
