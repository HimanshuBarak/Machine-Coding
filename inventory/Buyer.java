
package inventory;
/*
 * 
 * buyer
 *     -- id
 *     -- name
 *     -- address(-)
 *     
 *     --- getid
 *     --- getaddress()
 * 
 */
public class Buyer{
     String id;
     String name;
     Address address;

     public Buyer(String id, String name, Address address)
     {
        this.id = id;
        this.name = name;
        this.address = address;
     }
    
     public Address getAddress(){return address;}
     public String getName(){return name;}
     public String getId(){return id;}

}