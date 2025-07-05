import java.util.ArrayList;

public class Shipping {
    private static ArrayList<String> shippingDetails = new ArrayList<>();
    private double allWeight = 0;
    private String finalWeight;

    public boolean checkShipping() {
        boolean shippingStatus = false;
        if (Cart.getProducts().isEmpty()) {
            System.out.println("Cart is empty. Cannot proceed with shipping.");
            return false;
        }

        for (Product product : Cart.getProducts()) {
            Product stockProduct = Stock.getProducts().get(product.getName());
            if (stockProduct instanceof Shippable) {
                Shippable shippableProduct = (Shippable) stockProduct;
                shippingStatus = true;
                String weight = shippableProduct.getWeight();
                int quantity = product.getQuantity();
                weight = getTotalWeight(weight, quantity);
                shippingDetails.add(quantity + "x " + product.getName() + " " + weight);
            }
        }
        return shippingStatus;
    }

    public String getTotalWeight(String value, int quantity) {
        double totalWeight = 0;
        value = value.trim().toLowerCase();
        String numberPart = value.replaceAll("[^0-9.]", "");
        String unitPart = value.replaceAll("[0-9.\\s]", "");
        double weightValue = Double.parseDouble(numberPart);
        if (unitPart.equals("kg")) {
            weightValue *= 1000;
        }
        totalWeight += weightValue * quantity;
        allWeight += totalWeight;
        if (totalWeight >= 1000) {
            totalWeight /= 1000;
            return String.format("%.2f kg", totalWeight);
        }
        else {
            return String.format("%.0f g", totalWeight);
        }
    }

    public static ArrayList<String> getShippingDetails() {
        return shippingDetails;
    }

    public static void clearShippingDetails() {
        shippingDetails.clear();
    }

    public void printShippingDetails() {
        if (shippingDetails.isEmpty()) {
            System.out.println("No shippable products in the cart.");
            return;
        }
        System.out.println("Shipping Details:");
        for (String detail : shippingDetails) {
            System.out.println(detail);
        }
    }

    public double getTotalWeight() {
        return allWeight;
    }

    public void setTotalWeight(int totalWeight) {
        allWeight = totalWeight;
    }

    public String getFinalWeight() {
        if (allWeight >= 1000) {
            return String.format("%.2f kg", allWeight / 1000);
        } else {
            return String.format("%f g", allWeight);
        }
    }
}
