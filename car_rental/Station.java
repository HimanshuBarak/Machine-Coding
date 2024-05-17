
package car_rental;
import java.util.*;
/*
 * 
 * Station
-- name
-- available vehicle list
-- booked vehicle list
-- location

Vehicle
-- Type 
-- hour rate
-- isavail
-- vehicle_id
-- station_name
-- location

book_vehicle(type)
return vehicle_id

drop vehicle

available vehicles
array list ,sort them by price


get_Report()
loop through each booked vehicle type 
loop through each vehicle 

get_available_vehicle()
return vehicle_id, station_id
 */

public class Station{
   String name;
   ArrayList<Vehicle> availableVehicles;
   ArrayList<Vehicle> bookedVehicles;
   double location;

   Station(String name, ArrayList<Vehicle> availableVehicles, double location) {
        this.name = name;
        this.availableVehicles = availableVehicles;
        this.location = location;
        this.bookedVehicles = new ArrayList<Vehicle>();
        
   }

   public synchronized Vehicle bookVehicle(VehicleType type){
        for(Vehicle vehicle : availableVehicles)
        {
            if (vehicle.getType() == type)
            {
                bookedVehicles.add(vehicle);
                availableVehicles.remove(vehicle);
                return vehicle;
            }
        }
        System.out.println("No available vehicle");
        return null;
   }
   public double getLocation(){
          return location;
   }
   public String getName(){
      return name;
   }
   public void dropVehicle(Vehicle vehicle){
        availableVehicles.add(vehicle);
        if(bookedVehicles.contains(vehicle)){
            bookedVehicles.remove(vehicle);
        }
   }
   
   void display(HashMap<VehicleType, Integer> report)
   {
       for(VehicleType type: report.keySet())
       {
           System.out.println("Total Vehicles of Type "+ type + "are :"+report.get(type));  
       }
   }

   public void getReport()
   {
       HashMap<VehicleType, Integer> report= new HashMap<VehicleType, Integer>();
       for(Vehicle vehicle : availableVehicles)
       {
          report.merge(vehicle.getType(),1, Integer::sum);
       }
       System.out.println("Available vehicles details are as following ");
       display(report);
       report = new HashMap<VehicleType, Integer>();
       System.out.println("Booked vehicles details are as following ");
       for(Vehicle vehicle : bookedVehicles)
       {
          report.merge(vehicle.getType(),1, Integer::sum);
       }
       display(report);
    }

    public ArrayList<Vehicle> getCheapestVehicle(VehicleType type)
    {     
          ArrayList<Vehicle> list = new ArrayList<Vehicle>();
          for(Vehicle vehicle : availableVehicles)
          {
               if(vehicle.getType().equals(type))
                list.add(vehicle);
          }  
          return list;
    }

}