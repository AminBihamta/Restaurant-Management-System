import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String FILE_NAME = "menu.txt";

    public static List<MenuItem> loadMenu() throws IOException {
        List<MenuItem> menu = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0];
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                if (type.equals("Food")) {
                    menu.add(new Food(name, price));
                } else if (type.equals("Beverage")) {
                    menu.add(new Beverage(name, price));
                }
            }
        }
        return menu;
    }

    public static void saveOrder(Order order, String customerName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(customerName + "_order.txt"))) {
            for (MenuItem item : order.items) {
                writer.write(item.getName() + ": $" + item.getPrice());
                writer.newLine();
            }
            writer.write("Total: $" + order.getTotalPrice());
        }
    }
}
