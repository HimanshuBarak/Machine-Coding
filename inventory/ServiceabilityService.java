package inventory;
import java.util.*;

/*
 * 
 * ServiceabilityService
 *    -- HashMap<String,HashMap<String,Payment>>
 *    -- add serviceability | void
 *    -- check serviceability(source_pin, dest_pin, PaymentMode)
 * 
 * 
 */

public class ServiceabilityService implements IServiceabilityService{
    HashMap<String,HashMap<String,PaymentMode>> pincodes;

    public ServiceabilityService()
    {
        pincodes = new HashMap<String,HashMap<String,PaymentMode>>();
    }
    @Override
    public void addServiceability(String srcPinCode, Servicability serv)
    {
        HashMap<String,PaymentMode> destMode = new HashMap<String,PaymentMode>();
        destMode.put(serv.getDestinationPinCode(), serv.getPaymentMode());
        pincodes.put(srcPinCode, destMode);
    }

    @Override
    public boolean isServiceable(Address srcAdd, Address destAdd, PaymentMode mode)
    {   
        String srcPinCode = srcAdd.getPinCode();
        String destPinCode = destAdd.getPinCode();
        HashMap<String, PaymentMode> destMode = pincodes.get(srcPinCode);
        
        if(destMode.containsKey(destPinCode) && (destMode.get(destPinCode) == mode || destMode.get(destPinCode)==PaymentMode.BOTH))
           return true;
        return false;      
    }

}