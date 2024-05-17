
package parkinglot;
import java.util.*;

class Vehicle{
    VehicleType type;
    String vehicleNo;
    String color;
   

    public Vehicle(VehicleType type, String vehicleNo,String color)
    {
       this.type = type;
       this.vehicleNo = vehicleNo;
       this.color = color;
    }

    public VehicleType getVehicleType(){
        return this.type;
    }

    public String getVehicleNo(){
        return this.vehicleNo;
    }
    
    
}