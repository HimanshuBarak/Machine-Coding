package inventory;

public interface IProductService {
    String addProduct(String name, int quantity, Address address);
    Product getProduct(String id);
    boolean canOrder(String productId, int quantity);
    void processOrder(String productId, int quantity);
}