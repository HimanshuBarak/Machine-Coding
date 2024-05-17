package inventory;

public interface IServiceabilityService {
    void addServiceability(String pincode, Servicability servicability);
    boolean isServiceable(Address srcAddress, Address destAddress, PaymentMode mode);
}