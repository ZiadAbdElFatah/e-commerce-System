import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Stock.addProduct(new Biscuits(10, LocalDate.of(2026, 12, 31)));
        Stock.addProduct(new TV(20));
        Stock.addProduct(new TV(5));
        Stock.addProduct(new Mobile(50));
        Stock.addProduct(new Cheese(60, LocalDate.of(2025, 8, 30)));
        Cart cart = new Cart();
        cart.add("Biscuits", 5);
        cart.add("TV", 2);
        cart.add("TV", 29);
        cart.add("Mobile", 2);
        cart.add("Cheese", 5);
        cart.add("Mobile", 100);
        Costumer costumer = new Costumer("Zeyad", "Street", "01010101010", 100000);
        checkout(costumer, cart);
    }

    static void checkout(Costumer costumer, Cart cart) {
        double subTotal = 0;
        boolean shippingStatus = false;
        Shipping shipping = new Shipping();
        ArrayList<String> checkoutDetails = new ArrayList<>();
        if (Cart.getProducts().isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        checkoutDetails.add("** Checkout receipt **");
        for (Product product : Cart.getProducts()) {
            String key = product.getName();
            Product stockProduct = Stock.getProducts().get(key);
            if (stockProduct instanceof PerishableProduct) {
                if (((PerishableProduct) stockProduct).isExpired()) {
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
        if (shipping.checkShipping()) {
            shippingStatus = true;
            checkoutDetails.add("Shipping details:");
            for (String detail : Shipping.getShippingDetails()) {
                checkoutDetails.add(detail);
            }
            checkoutDetails.add("Total weight " + shipping.getFinalWeight());
            checkoutDetails.add("----------------------");
            Shipping.clearShippingDetails();
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