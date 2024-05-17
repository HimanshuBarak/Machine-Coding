package inventory;


import java.util.*;
/*
 * 
 * ProductService
 *    -- map String Product
 *    -- add_product()  str 
 *    -- get_product() | product
 *    -- process_order() | 
 *    -- can_order() | bool
 * 
 *   Producut
 *     -- id
 *     -- quantity
 *     -- address
 *     -- name
 *     --- setQuantity
 *     --- getQuantity
 *     --- getaddress
 */

public class ProductService implements IProductService{
    private HashMap<String,Product> products;

    public ProductService()
    {
        products = new HashMap<String,Product>();
    }

    String generateUUID()
    {
        
        return UUID.randomUUID().toString();
    }

    @Override
    public String addProduct(String name,int quantity,  Address address)
    {     
        String id = Utils.generateUUID();
        Product product = new Product(id,name,quantity, address);
        products.put(id,product);
        System.out.println("Product added with ID: " + id); 
        return id;
    }

    @Override
    public Product getProduct(String id)
    {
        return products.get(id);
    }

    @Override
    public boolean canOrder(String productId, int quantity)
    {
        Product p = products.get(productId);
        
        int available = p.getQuantity();

        return available >= quantity;

    }

    @Override
    public void processOrder(String productId, int quantity){
        Product p = products.get(productId);
        int available = p.getQuantity();
        int newQuantity = available - quantity;
        p.setQuantity(newQuantity);
    }


}