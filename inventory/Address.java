package inventory;


public class Address{
    String street;
    String city;
    String pinCode;

    public Address(String city, String street, String pinCode)
    {
        this.street = street;
        this.city = city;
        this.pinCode = pinCode;
    }

    public String getPinCode(){return pinCode;}
}