package car_rental;
import java.util.*;

public class RentalService{
    List<Station> stations = new ArrayList<Station>();

    public RentalService(ArrayList<Station> stationList){
        stations = stationList;
    }

    public void addStation(String name,double location, ArrayList<Vehicle> vehicles){
          Station station = new Station(name,vehicles, location);
          stations.add(station);
    }

    public void searchVehicles(VehicleType type){
        List<SearchResponse> responses = new ArrayList<SearchResponse>();

        for(Station station : stations)
        {  System.out.println(station.getLocation());
            for (Vehicle vehicle : station.getCheapestVehicle(type)) { 
                SearchResponse response = new SearchResponse(
                    vehicle.getVehicle_id(),
                    vehicle.getHourRate(),
                    station.getName(),
                    station.getLocation()
                );
                responses.add(response);
            }

        }
        Collections.sort(responses);
        responses.forEach(System.out::println);
    }


    public void dropVehicle(String stationName, Vehicle vehicle){
          for(Station station :stations)
          {
              if(station.getName() == stationName)
              {
                station.dropVehicle(vehicle);
                return ;
              }     
          }
    }
    
    public void getReports()
    {
        for(Station station :stations)
        {
            station.getReport();
        }
    }

    public void  bookVehicle(VehicleType type ,String stationName){
        for(Station station :stations)
        {
            if(station.getName() == stationName)
            {
                Vehicle v = station.bookVehicle(type);
                if(v == null)
                System.out.println("No available vehicle in the station");
                else 
                 System.out.println("Booked the following vehicle  "+v.getVehicle_id()); 
                return;
            }
        }
        System.out.println("Invalid Station");
    }
}