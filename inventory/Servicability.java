package inventory;



public class Servicability {

    String destPincode;
    PaymentMode mode;

    public Servicability(String destPincode, PaymentMode mode) {
        this.destPincode = destPincode;
        this.mode = mode;
     }  
     
    public String getDestinationPinCode(){
        return destPincode;
    } 

    public PaymentMode getPaymentMode(){
        return mode;
    }   
}