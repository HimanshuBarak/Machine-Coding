package car_rental;


import java.util.*;
public class SearchResponse implements Comparable<SearchResponse>{
     String vehicleId;
     double price;
     String stationName;
     double stationLocation;

     public SearchResponse(String vehicleId, double price, String stationName, double stationLocation)
     {
        this.vehicleId = vehicleId;
        this.price = price;
        this.stationName = stationName;
        this.stationLocation = stationLocation;
     }

     @Override
     public int compareTo(SearchResponse other)
     {  
        if(this.price != other.price)
        return Double.compare(this.price, other.price);
        else
        return Double.compare(this.stationLocation, other.stationLocation);
     }

     @Override
     public String toString(){
        return "Vehicle ID: " + vehicleId + ", Price: $" + price + 
        ", Station: " + stationName + ", Location: " + stationLocation;
     }
}