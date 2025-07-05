public class TV extends Product implements Shippable {
    public TV(int quantity) {
        super("TV", quantity);
        setWeight("10kg");
        setPrice(15000);
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
