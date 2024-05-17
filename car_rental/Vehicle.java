package car_rental;
import java.util.*;

public class Vehicle {
    VehicleType type;
    double hourRate;
    String vehicle_id;
    

    Vehicle(VehicleType type, double hourRate, String vehicle_id)
    {
        this.type = type;
        this.hourRate = hourRate;
        this.vehicle_id = vehicle_id;
    }

    // getters
    public VehicleType getType() { return type; }
    public double getHourRate() { return hourRate; }
    public String getVehicle_id() { return vehicle_id; }
   
    
}