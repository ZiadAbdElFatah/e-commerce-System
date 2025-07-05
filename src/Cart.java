import java.util.ArrayList;

public class Cart {
    private static final ArrayList<Product> products = new ArrayList<>();

    void add (String name, int quantity) {
        Stock stock = new Stock();
        Product product = stock.getProduct(name);
        if (product != null) {
            if (product.getQuantity() >= quantity) {
                product.setQuantity(product.getQuantity() - quantity);
                Product cartProduct = new Product(name, quantity);
                products.add(cartProduct);
            } else {
                System.out.println("Not enough stock for " + name + ". Available: " + product.getQuantity());
            }
        } else {
            System.out.println(name + " not found in stock.");
        }
    }
    public static ArrayList<Product> getProducts() {
        return products;
    }
}
