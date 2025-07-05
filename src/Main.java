import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Product.addperishableProduct("Biscuits");
        Product.addperishableProduct("Cheese");
        Product.addShippable("Biscuits");
        Product.addShippable("Cheese");
        Product.addShippable("Mobile");
        Product.addShippable("TV");
        Stock.addProduct(new Product("Biscuits", 200, 50, LocalDate.of(2026, 12, 31), "400g"));
        Stock.addProduct(new Product("TV",10000, 20, "10Kg"));
        Stock.addProduct(new Product("TV", 10000, 10, "10Kg"));
        Stock.addProduct(new Product("Mobile", 30000, 50, "200g"));
        Stock.addProduct(new Product("Cheese", 150, 60, LocalDate.of(2025, 8, 30), "200g"));
        Stock.addProduct(new Product("Mobile scratch card", 10, 60));
        Stock.addProduct(new Product("Mobile scratch card", 10, 60, "100g"));
        Cart cart = new Cart();
        cart.add("Biscuits", 5);
        cart.add("TV", 2);
        cart.add("TV", 29);
        cart.add("Mobile", 2);
        cart.add("Cheese", 5);
        cart.add("Mobile", 100);
        Customer customer = new Customer("Zeyad", "Street", "01010101010", 100000);
        checkout(customer, cart);
    }

    static void checkout(Customer costumer, Cart cart) {
        double subTotal = 0;
        boolean shippingStatus = false;
        Shipping shipping = new Shipping();
        ArrayList<String> checkoutDetails = new ArrayList<>();
        if (cart.getProducts().isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        checkoutDetails.add("** Checkout receipt **");
        for (Product product : cart.getProducts()) {
            String key = product.getName();
            Product stockProduct = Stock.getProducts().get(key);
            if (product.isPerishable()) {
                if (product.isExpired()) {
                    System.out.println("Cannot checkout " + product.getName() + " as it is expired.");
                    return;
                }
            }
            checkoutDetails.add(product.getQuantity() + "x " + product.getName() + " = "
                                + (stockProduct.getPrice() * product.getQuantity()));
            subTotal += stockProduct.getPrice() * product.getQuantity();
        }
        checkoutDetails.add("Subtotal " + subTotal);
        checkoutDetails.add("----------------------");
        if (shipping.checkShipping(cart)) {
            shippingStatus = true;
            checkoutDetails.add("Shipping details:");
            checkoutDetails.addAll(shipping.getShippingDetails());
            checkoutDetails.add("Total weight " + shipping.getFinalWeight());
            checkoutDetails.add("----------------------");
            shipping.clearShippingDetails();
        } else {
            System.out.println("No shippable products in the cart.");
        }
        checkoutDetails.add("Subtotal: " + subTotal);
        if (shippingStatus) {
            checkoutDetails.add("Shipping : 50");
            subTotal += 50;
        }
        checkoutDetails.add("Total: " + subTotal);
        if (costumer.getBalance() >= subTotal) {
            costumer.setBalance(costumer.getBalance() - subTotal);
            System.out.println("Checkout successful! Remaining balance: " + costumer.getBalance());
            for (String detail : checkoutDetails) {
                System.out.println(detail);
            }
            System.out.println("----------------------");
        } else {
            System.out.println("Insufficient balance for checkout. Total: " + subTotal + ", Balance: " + costumer.getBalance());
        }
    }
}
