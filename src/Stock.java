import java.util.HashMap;
import java.util.Map;

public class Stock {
    private static final Map<String, Product> products = new HashMap<>();

    public static void addProduct(Product product) {
        String key = product.getName();
        if (product.isPershiable()) {
            if (product.getExpirationDate() == null) {
                System.out.println("Expiration date is required for perishable products.");
                return;
            }
        }
        if (product.isShippable()) {
            if (product.getWeight() == null) {
                System.out.println("Weight is required for shippable products.");
                return;
            }
        }
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
