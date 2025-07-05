public class Mobile extends Product implements Shippable {
    public Mobile(int quantity) {
        super("Mobile", quantity);
        setWeight("100g");
        setPrice(30000);
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
