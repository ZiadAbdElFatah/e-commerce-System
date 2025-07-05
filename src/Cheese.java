import java.time.LocalDate;

public class Cheese extends PerishableProduct implements Shippable {
    public Cheese(int quantity, LocalDate expirationDate) {
        super("Cheese", quantity, expirationDate);
        setWeight("500g");
        setPrice(150);
    }

    @Override
    public String getWeight() {
        return super.getWeight();
    }

    @Override
    public void setWeight(String weight) {
        super.setWeight(weight);
    }
}
