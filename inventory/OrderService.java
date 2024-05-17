

package inventory;
import java.util.*;


/*
 * 
 * 
 * InventoryService 
 *  
 *  can add product
 *  add buyer 
 *  check inventory status (if we have the quantity right)
 *   
 *  place order
 *  if pincode is valid 
 *  if payment method is valid
 *   (strategy ) 
 * 
 *    ProductService
 *    -- map String Product
 *    -- add_product()  str 
 *    -- get_product() | product
 *    -- process_order() | 
 *    -- can_order() | bool
 *    
 *    
 *     
 *    BuyerService
 *    -- map String Buyer
 *    -- add_buyer() return str
 *    -- get_buyer() | buyer
 * 
 *    ServiceabilityService
 *    -- HashMap<String,HashMap<String,Payment>>
 *    -- add serviceability | void
 *    -- check serviceability(source_pin, dest_pin, PaymentMode)
 *    
 *    
 *    OrderService
 *    -- productservice
 *    -- buyerservice
 *    -- place_order
 *    
 *    
 *    place_order(product_id, quantity,buyer_id, mode)
 *     
 *     --> check inventory
 *     --> checkservicibluty
 *     --> process_order
 *     
 *      
 *     Producut
 *     -- id
 *     -- quantity
 *     -- address
 *     -- name
 *     --- setQuantity
 *     --- getQuantity
 *     --- getaddress
 *     
 *     address
 *     -- street
 *     -- pincode
 *     -- state
 *     -- country  
 *     --- getpincode
 *     
 * 
 *     buyer
 *     -- id
 *     -- name
 *     -- address(-)
 *     
 *     --- getid
 *     --- getaddress()
 *     
 *      serviceiablity
 *      -- destpin
 *      -- paymentmode
 *       
 *      --- getpin
 *      --- getdest 
 *    
 */
public class OrderService  {
      
     IProductService productService;
     IBuyerService buyerService;
     IServiceabilityService servicabilityService; 
     
    public OrderService(IProductService productService, IBuyerService buyerService, IServiceabilityService servicabilityService)
    {
       this.productService = productService;
       this.buyerService = buyerService;
       this.servicabilityService = servicabilityService;
    }

    
    public void placeOrder(String productId,String buyerId, int quantity, PaymentMode mode) 
    {   
        try{
            Product product = this.productService.getProduct(productId);
            Buyer buyer = this.buyerService.getBuyer(buyerId);
            if(product==null){
                System.out.println("Product accessed with ID: " + productId); 
                throw new OrderException("Invalid product");
            }
             
            
            if(buyer==null)
              throw new OrderException("Invalid buyer");  
            if(!this.productService.canOrder(productId,quantity))
            {
                throw new OrderException("Insufficint Quantity");
            }
            Address destAdd = buyer.getAddress();
            Address srcAdd = product.getAddress();
            if(!this.servicabilityService.isServiceable(srcAdd, destAdd, mode))
             throw new OrderException("The product does not support this payment mode for this destination");
            
            this.productService.processOrder(productId, quantity); 

            System.out.println("Successfully Processed your order ");
        }catch(OrderException e)
        {
            System.out.println(e.getMessage());
        }  
    }
   
}