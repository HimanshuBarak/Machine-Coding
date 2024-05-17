/*

Requirement -

This rental system will have some stations where car will be parked.
Every station will have some type of cars (example - SUV, Hatchback, Bike, Sedan etc) and will have some fixed price for each type of car ($11/hour for SUV, 12$/per hour for Sedan etc.)
User can book car from any station and drop at same or any other station
When user searches for a car - list all stations with that type of available car + station with cheapest price should be on top followed by second cheapest and so on..(order by increasing per hour price)
When two stations have same price, list station nearest to the customer
Supported operations

Onboard station - (station_name, List)
Search Vehicle
Book vehicle
Drop vehicle
Station Report - available car of each type, booked car of each type etc.


search a vehicle 

User
-- name
-- location
-- booked null


Vehicle
-- Type 
-- hour rate
-- isavail
-- vehicle_id
-- station_id
-- location


sedan 
SUV 
hatchback
Bike



Station
-- name
-- available vehicle list
-- booked vehicle list
-- location


book_vehicle(type)
return vehicle_id

drop vehicle()
update location
update station_id


available vehicles
array list ,sort them by price


get_Report()
loop through each booked vehicle type 
loop through each vehicle 

get_available_vehicle()
return vehicle_id, station_id


priority_queue<perhour price>

Rental system
-- station list

-- onboard station
-- book vehicle
-- drop vehicle()


onboard_Station(name, location, list<vehicle>)
new statation 

search_vehicle
book_vehicle()
booked_vehicle


search_vehicle(type)


*/
package car_rental;
import java.util.*;

class Main{
    public static void main(String[] args)
    {
        System.out.println("Hello World!");
        Vehicle vehicle1 = new Vehicle(VehicleType.SEDAN,10,"s001");
        Vehicle vehicle2 = new Vehicle(VehicleType.SUV,14,"suv001");
        Vehicle vehicle3 = new Vehicle(VehicleType.BIKE,4,"bub001");
        ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
        vehicleList.add(vehicle1);
        vehicleList.add(vehicle2);
        vehicleList.add(vehicle3);
        Station station = new Station("station1",vehicleList,5.5);
        ArrayList<Station> stations = new ArrayList<Station>();
        stations.add(station);
        RentalService service = new RentalService(stations);
        Vehicle vehicle4 = new Vehicle(VehicleType.SEDAN,10,"s001");
        Vehicle vehicle5 = new Vehicle(VehicleType.SUV,14,"suv001");
        Vehicle vehicle6 = new Vehicle(VehicleType.SUV,12,"bub001");
        vehicleList = new ArrayList<Vehicle>();
        vehicleList.add(vehicle4);
        vehicleList.add(vehicle5);
        vehicleList.add(vehicle6);
        service.addStation("station2",3.3,vehicleList);
        service.searchVehicles(VehicleType.SUV);
        service.bookVehicle(VehicleType.BIKE, "station1");
        Vehicle vehicle8 = new Vehicle(VehicleType.BIKE, 15,"bb001");
        //service.getReports();
        //service.dropVehicle("station2",vehicle8);
        //service.getReports();
    }
}