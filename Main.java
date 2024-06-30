
// Main.java
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            List<MenuItem> menu = FileHandler.loadMenu();

            System.out.print("Enter customer name: ");
            String customerName = scanner.nextLine();
            Customer customer = new Customer(customerName);

            System.out.print("\n\nChoose customer category");
            System.out.println(
                    "\n1. Adult (Normal Price)\n2. Above 60 (20% Discount)\n3. Under 5 (Free)\n\n");
            System.out.print("Enter Customer Category: ");
            int category = 0;
            while (category < 1 || category > 3) {
                category = scanner.nextInt();
                if (category < 1 && category > 3)
                    System.out.println("Invalid category. Please try again.");
            }
            switch (category) {
                case 1:
                    customer.setCategoryMultiplier(1);
                    break;
                case 2:
                    customer.setCategoryMultiplier(0.8);
                    break;
                default:
                    customer.setCategoryMultiplier(0);
                    break;
            }
            System.out.println("/////////// Menu /////////// ");
            int itemNumber = 1;
            for (MenuItem item : menu) {
                System.out.println(itemNumber + ". " + item.getName() + ": $" + item.getPrice());
                itemNumber++;
            }
            System.out.println("");

            while (true) {
                System.out.print("Enter item name to order (or 0 to finish): ");
                int itemIndex = scanner.nextInt();
                if (itemIndex == 0) {
                    System.out.println("");
                    break;
                }

                MenuItem selectedItem = null;
                for (int i = 0; i < menu.size(); i++) {
                    if (i == (itemIndex - 1)) {
                        selectedItem = menu.get(i);
                        break;
                    }
                }

                if (selectedItem != null) {
                    customer.getOrder().addItem(selectedItem);
                    System.out.println(itemIndex + " added to order.");
                    System.out.println("");
                } else {
                    System.out.println("Item not found.");
                }
            }

            customer.getOrder().printOrder(customer.getCategoryMultiplier());
            FileHandler.saveOrder(customer.getOrder(), customer.getName());
            System.err.println(
                    "A new text file has been created with your order under the name " + customer.getName()
                            + "_order.txt!");

        } catch (IOException e) {
            System.err.println("Error loading menu or saving order: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
