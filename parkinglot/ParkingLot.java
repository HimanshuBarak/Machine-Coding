
package parkinglot;

import java.util.*;

 public class ParkingLot {
       String parkingLotId;
       int total_floors;
       int slotsPerFloor;
       ParkingFloor [] parking_floors;


       public ParkingLot(String parkingLotId, int total_floors,int slotsPerFloor){
          this.parkingLotId = parkingLotId;
          this.total_floors = total_floors;
          parking_floors = new ParkingFloor[total_floors+1];
          for(int i=1; i<=total_floors; i++){
              parking_floors[i] = new ParkingFloor(parkingLotId,i,slotsPerFloor);
          }
       }


       public int getFreeSlots(VehicleType type)
       {
          int ans=0;
          for(int i=1;i<=total_floors;i++){
             int x= parking_floors[i].getFreeSlots(type);  
             ans = ans +x;
        }
          return ans;
       }


       public String parkVehicle(Vehicle v)
       {
            for(int i=1;i<=this.total_floors;i++)
            {
                if(parking_floors[i].canPark(v.getVehicleType()))
                {
                   String ticket =  parking_floors[i].parkVehicle(v,v.getVehicleType());
                   System.out.println("Vehicle Parket with ticket "+ ticket);
                   return ticket;
                }
            }
            System.out.println("unable to park vehicle");
            return "Failed";
       }
       public void unParkVehicle(String ticket)
       {  
          
          int floor = Integer.parseInt(ticket.split("_")[1]);
          parking_floors[floor].unparkVehicle(ticket);
       }
 }