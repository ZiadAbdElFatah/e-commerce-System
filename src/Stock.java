import java.util.HashMap;
import java.util.Map;

public class Stock {
    private static final Map<String, Product> products = new HashMap<>();
    public static void addProduct(Product product) {
        String key = product.getName();
        if (!products.containsKey(key)) {
            products.put(key, product);
        }
        else {
            Product existingProduct = products.get(key);
            existingProduct.setQuantity(existingProduct.getQuantity() + product.getQuantity());
        }
    }
    public static Product getProduct(String name) {
        if (products.containsKey(name)) {
            return products.get(name);
        }
        System.out.println(name + " not found in stock.");
        return null;
    }
    public static Map<String, Product> getProducts() {
        return products;
    }
    public static void removeProduct(String name) {
        products.remove(name);
    }
}
