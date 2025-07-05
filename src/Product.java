import java.time.LocalDate;
import java.util.ArrayList;

public class Product {
    private String name;
    private double price;
    private int quantity;
    private LocalDate expirationDate = null;
    private String weight = null;
    private static final ArrayList<String> pershiableProducts = new ArrayList<>();
    private static final ArrayList<String> shippable = new ArrayList<>();

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(String name, double price, int quantity, LocalDate expirationDate) {
        if (!Product.pershiableProducts.contains(name)) {
            System.out.println("Product " + name + "can't have expiration date it is not a perishable product.");
            return;
        }
        if (expirationDate == null) {
            System.out.println("Expiration date cannot be null for perishable products.");
            return;
        }
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }

    public Product(String name, double price, int quantity, LocalDate expirationDate, String weight) {
        if (!Product.shippable.contains(name)) {
            System.out.println("Product " + name + " can't have weight it is not a shippable product.");
            return;
        }
        if (!Product.pershiableProducts.contains(name)) {
            System.out.println("Product " + name + " can't have expiration date it is not a perishable product.");
            return;
        }
        if (expirationDate == null) {
            System.out.println("Expiration date cannot be null for perishable products.");
            return;
        }
        if (weight == null || weight.isEmpty()) {
            System.out.println("Weight cannot be null or empty for shippable products.");
            return;
        }
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.weight = weight;
    }

    public Product(String name, double price, int quantity, String weight) {
        if (!Product.shippable.contains(name)) {
            System.out.println("Product " + name + " can't have weight it is not a shippable product.");
            return;
        }
        if (weight == null || weight.isEmpty()) {
            System.out.println("Weight cannot be null or empty for shippable products.");
            return;
        }
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.weight = weight;
    }

    public Product(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public static ArrayList<String> getPershiableProducts() {
        return pershiableProducts;
    }

    public static void addPershiableProduct(String product) {
        pershiableProducts.add(product);
    }

    public static ArrayList<String> getShippable() {
        return shippable;
    }

    public static void addShippable(String product) {
        shippable.add(product);
    }

    public boolean isPershiable() {
        return pershiableProducts.contains(name);
    }

    public boolean isShippable() {
        return shippable.contains(name);
    }

    public void addToPershiableProducts() {
        if (!pershiableProducts.contains(name)) {
            pershiableProducts.add(name);
        }
    }

    public void addToShippable() {
        if (!shippable.contains(name)) {
            shippable.add(name);
        }
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public boolean isExpired() {
        if (expirationDate == null) {
            return false;
        }
        return LocalDate.now().isAfter(expirationDate);
    }
}
