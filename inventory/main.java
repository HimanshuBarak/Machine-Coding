/*
 * 
 *   can add product
 *   add buyer 
 *   check inventory status (if we have the quantity right)
 *   
 *   place order
 *   if pincode is valid 
 *   if payment method is valid
 *   (strategy )
 *    
 * 
 *    product 
 *    -- id  str
 *    -- name str
 *    -- quantity int
 * 
 *    get product id 
 *    setquantity
 *    setquantity
 *    
 *     has_quantity(int q) | bool
 *     
 *    buyer
 *    -- buyer id
 *    -- buyer name
 *    -- buyer_location
 *    -- get_locatrion()
 *    -- get_name()
 *    -- get_id()
 *    
 *      InventoryService 
 *     -- available products hashmap (pid, product)
 *     -- buyers hashmap
 *     -- servicability set
 *     -- add_product() str 
 *     -- place_order() void  
 *     -- add_servicablity
 *    
 *     inventory
 *     check_Serviciablity(int locatoin , buyers location, payment method)
 *    place_order(buyer_id, product_id, quantity,payment_method)
 *     fetch
 *     get buyer location
 *     get inventory
 *     check servicability 
 *     
 * 
 *     servicablity class
 *     -- destinationpincode
 *     -- payment_modes set  enum
 *     
 *     -- ispaymentmodesupported(enum payment_mode)
 *     
 *     
 *      
 *     
 */
package inventory;
import java.util.*;


class Main{
    public static void main(String[] args)
    {   
        /*
         * BuyerRepository buyerRepository = new BuyerRepository();
        OrderRepository orderRepository = new OrderRepository();
        ProductRepository productRepository = new ProductRepository();
        PincodeServiceabilityRepository pincodeServiceabilityRepository = new PincodeServiceabilityRepository();

        BuyerService buyerService = new BuyerServiceImpl(buyerRepository);
        ProductService productService = new ProductServiceImpl(productRepository);
        PincodeServiceabilityService pincodeServiceabilityService = new PincodeServiceabilityServiceImpl(pincodeServiceabilityRepository);
        OrderServiceImpl orderService = new OrderServiceImpl(
                orderRepository, productService, pincodeServiceabilityService, buyerService);

        Address address1 =  new Address("Tag","Vizag","531162");
        Address address2 =  new Address("Somaji","Hyderabad","500082");
        Address address3 =  new Address("Hubili","Bangalore","536264");


        Product product1 = new Product("T-shirt Levis", 10, address1);
        Product product2 = new Product("Casual Shoes", 10, address2);
        Product product3 = new Product("Modern Classic Pants", 10, address3);
        Product product4 = new Product("Kudtha", 10, address1);

        String product1Id = productService.addProduct(product1);
        String product2Id = productService.addProduct(product2);
        String product3Id = productService.addProduct(product3);
        String product4Id = productService.addProduct(product4);


        Buyer buyer1 = new Buyer("LavKumar",address1);
        Buyer buyer2 = new Buyer("Pranva",address2);
        Buyer buyer3 = new Buyer("Khavya",address3);

        String buyer1Id = buyerService.addBuyer(buyer1);
        String buyer2Id = buyerService.addBuyer(buyer2);
        String buyer3Id = buyerService.addBuyer(buyer3);


       
        pincodeServiceabilityService.createPinCodeServiceability(
                "531162","500082", PaymentMode.PREPAID
        );
        pincodeServiceabilityService.createPinCodeServiceability(
                "531162","531162", PaymentMode.PREPAID
        );
        pincodeServiceabilityService.createPinCodeServiceability(
                "500082","536264", PaymentMode.PREPAID
        );
        pincodeServiceabilityService.createPinCodeServiceability(
                "536264","531162", PaymentMode.PREPAID
        );

        Order order1 = new Order(product1Id, buyer1Id, 5, PaymentMode.PREPAID);
        Order order3 = new Order(product1Id, buyer1Id, 6, PaymentMode.PREPAID);
        Order order2 = new Order(product1Id, buyer3Id, 5, PaymentMode.PREPAID);
         * 
         */
        IProductService productService = new ProductService();
        IBuyerService buyerService = new BuyerService();
        Address address1 =  new Address("Tag","Vizag","531162");
        Address address2 =  new Address("Somaji","Hyderabad","500082");
        Address address3 =  new Address("Hubili","Bangalore","536264");

       

        String product1Id = productService.addProduct("T-shirt Levis", 10, address1);
        String product2Id = productService.addProduct("Casual Shoes", 10, address2);
        String product3Id = productService.addProduct("Modern Classic Pants", 10, address3);
        String product4Id = productService.addProduct("Kudtha", 10, address1);

        String buyer1Id = buyerService.addBuyer("LavKumar",address1);
        String buyer2Id = buyerService.addBuyer("Pranva",address2);
        String buyer3Id = buyerService.addBuyer("Khavya",address3);
        Servicability serv1 = new Servicability("500082", PaymentMode.CARD);
        Servicability serv2 = new Servicability("531162", PaymentMode.CARD);
        Servicability serv3 = new Servicability("536264", PaymentMode.CARD);
        Servicability serv4 = new Servicability("531162", PaymentMode.CASH);
        
        IServiceabilityService service = new ServiceabilityService();
        service.addServiceability("531162",serv1);
        service.addServiceability("531162",serv2);
        service.addServiceability("500082",serv3);
        service.addServiceability("536264",serv4);
        OrderService order = new OrderService(productService, buyerService, service);
        
        order.placeOrder(product1Id, buyer1Id, 5, PaymentMode.CARD);
        order.placeOrder(product1Id, buyer1Id, 6, PaymentMode.CARD);
        order.placeOrder(product1Id, buyer3Id, 5, PaymentMode.CASH);
        System.out.println("hello inventory");
    }
}