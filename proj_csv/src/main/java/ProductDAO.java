import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private final static String CSV_FILE = "E:\\New folder (2)\\Products.csv";
    public static Product createProduct(String[] metadata){
        String productName = metadata[0];
        int price = Integer.parseInt(metadata[1]);
        int quantity = Integer.parseInt(metadata[2]);
        return new Product(productName, price, quantity);
    }

    /**
     *
     * @param product
     */
    public void add(Product product) {
        try {
            FileWriter writer = new FileWriter(CSV_FILE, true);

            writer.append(product.getProductName());
            writer.append(';');
            writer.append(product.getQuantity()+"");
            writer.append(';');
            writer.append(product.getPrice()+"");
            writer.append('\n');

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
