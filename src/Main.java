import java.util.Scanner;

interface DiscountRate {
    double getServiceMemberDiscount();
    double getProductMemberDiscount();
}

class Customer implements DiscountRate {
    private String customerName;
    private String customerType;

    public Customer(String customerName, String customerType) {
        this.customerName = customerName;
        this.customerType = customerType;
    }

    public double getServiceMemberDiscount() {
        switch (customerType) {
            case "Premium":
                return 0.20;
            case "Gold":
                return 0.15;
            case "Silver":
                return 0.10;
            default:
                return 0.0;
        }
    }

    public double getProductMemberDiscount() {
        switch (customerType) {
            case "Premium":
                return 0.10;
            case "Gold":
                return 0.10;
            case "Silver":
                return 0.10;
            default:
                return 0.0;
        }
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }
}

class Sale {
    private Customer customer;
    private String date;
    private double serviceExpense;
    private double productExpense;

    public Sale(Customer customer, String date) {
        this.customer = customer;
        this.date = date;
    }

    public double getServiceExpense() {
        return serviceExpense;
    }

    public void setServiceExpense(double serviceExpense) {
        this.serviceExpense = serviceExpense;
    }

    public double getProductExpense() {
        return productExpense;
    }

    public void setProductExpense(double productExpense) {
        this.productExpense = productExpense;
    }

    public double getTotalExpense() {
        double totalServiceExpense = serviceExpense - (serviceExpense * customer.getServiceMemberDiscount());
        double totalProductExpense = productExpense - (productExpense * customer.getProductMemberDiscount());
        return totalServiceExpense + totalProductExpense;
    }

    public void displayInfo() {
        System.out.println("-------------------------------------");
        System.out.println("|           SALE INFORMATION        |");
        System.out.println("-------------------------------------");
        System.out.println("Customer Name: " + customer.getCustomerName());
        System.out.println("Customer Type: " + customer.getCustomerType());
        System.out.println("Date: " + date);
        System.out.println("-------------------------------------");
        System.out.println("|           EXPENSE DETAILS         |");
        System.out.println("-------------------------------------");
        System.out.printf("Service Expense: $%.2f\n", serviceExpense);
        System.out.printf("Product Expense: $%.2f\n", productExpense);
        System.out.println("-------------------------------------");
        System.out.println("|           TOTAL EXPENSE           |");
        System.out.println("-------------------------------------");
        System.out.printf("Total Expense: $%.2f\n", getTotalExpense());
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            System.out.print("Enter customer name: ");
            String customerName = scanner.nextLine();

            System.out.print("Enter customer type (Premium/Gold/Silver/Normal): ");
            String customerType = scanner.nextLine();

            System.out.print("Enter sale date (DD-MM-YYYY): ");
            String saleDate = scanner.nextLine();

            Customer customer = new Customer(customerName, customerType);
            Sale sale = new Sale(customer, saleDate);
            System.out.print("Enter service expense: ");
            double serviceExpense = scanner.nextDouble();

            System.out.print("Enter product expense: ");
            double productExpense = scanner.nextDouble();
            scanner.nextLine(); // consume newline

            sale.setServiceExpense(serviceExpense);
            sale.setProductExpense(productExpense);

            sale.displayInfo();

            System.out.print("Do you want to enter another sale? (yes/no): ");
            choice = scanner.nextLine();
        } while (choice.equalsIgnoreCase("yes"));

        scanner.close();
    }
}