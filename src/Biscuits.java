import java.time.LocalDate;

public class Biscuits extends PerishableProduct implements Shippable {
    public Biscuits(int quantity, LocalDate expirationDate) {
        super("Biscuits", quantity, expirationDate);
        setWeight("500g");
        setPrice(150);
        setExpirationDate(expirationDate);
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
