package inventory;
import java.util.*;

/*
 * 
 * 
 * 
 * BuyerService
 *    -- map String Buyer
 *    -- add_buyer() return str
 *    -- get_buyer() | buyer
 * 
 *  buyer
 *     -- id
 *     -- name
 *     -- address(-)
 *     
 *     --- getid
 *     --- getaddress()
 */
public class BuyerService implements IBuyerService {
    HashMap<String,Buyer> buyers;

    public BuyerService()
    {
        buyers = new HashMap<String,Buyer>();
    }
    String generateUUID()
    {
        
        return UUID.randomUUID().toString();
    }

    @Override
    public String addBuyer(String name, Address address)
    {
        String id = generateUUID();
        Buyer buyer = new Buyer(id,name,address);
        buyers.put(id,buyer);
        return id;
    }

    @Override
    public Buyer getBuyer(String id)
    {
        return buyers.get(id);
    }

} 