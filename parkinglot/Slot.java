
package parkinglot;
import java.util.*;


public class Slot implements Comparable<Slot>{
    String parkingLotId;
    int floorId;
    int slotId;
    VehicleType type;
    Vehicle parkedVehicle;
    String ticket_id;

    Slot(String parkingLotId, int floorId, int slotId, VehicleType type , Vehicle v) {
           this.parkingLotId = parkingLotId;
           this.floorId = floorId;
           this.slotId = slotId;
           this.type = type;
           this.parkedVehicle = v;
           this.ticket_id = this.parkingLotId + "_" +   String.valueOf(floorId)+ "_" +String.valueOf(slotId);
    }

    public int getSlotId(){
        return slotId;
    }
    
    public boolean canPark(VehicleType type){
         return this.type ==type;
    }
    
    public VehicleType getType(){
        return this.type;
    }   
   public String parkVehicle(Vehicle v)
   {
        this.parkedVehicle = v;
        return this.ticket_id;
   }

   public void unparkVehicle()
   {
      this.parkedVehicle = null;
   }

   @Override
   public int compareTo(Slot other){
    return Integer.compare(this.slotId,other.slotId);
  }
   
}