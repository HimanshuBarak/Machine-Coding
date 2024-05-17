/*
 * 
 * product 
 *    -- id  str
 *    -- name str
 *    -- quantity int
 * 
 *    get product id 
 *    setquantity
 *    setquantity
 * 
 *    
 */
package inventory;

public class Product{
    String id;
    String name;
    int quantity;
    Address address;

    public Product(String id, String name, int quantity, Address address)
    {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.address = address;
    }

    int getQuantity(){return quantity;}
    Address getAddress(){return address;}

    void setQuantity(int quantity){this.quantity = quantity;}

}