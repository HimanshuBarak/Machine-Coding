package inventory;


public interface IBuyerService {
    String addBuyer(String name, Address address);
    Buyer getBuyer(String id);
}