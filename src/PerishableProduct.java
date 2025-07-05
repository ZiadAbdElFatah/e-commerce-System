import java.time.LocalDate;

public class PerishableProduct extends Product {
    private LocalDate expirationDate;

    public PerishableProduct(String name, int quantity, LocalDate expirationDate) {
        super(name, quantity);
        this.expirationDate = expirationDate;
    }
    public LocalDate getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }
}
