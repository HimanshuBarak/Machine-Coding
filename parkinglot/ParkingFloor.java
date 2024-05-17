
package parkinglot;
import java.util.*;
import java.util.SortedSet;
import java.util.TreeSet;


public class ParkingFloor {
    String parkingLotId;
    int floorId;
    HashMap<VehicleType,SortedSet<Slot>> freeSlots;
    HashMap<String, Slot> occupiedSlots;
    int totalSlots;

    public ParkingFloor(String parkingLotId, int floorId,int totalSlots)
    {
        this.parkingLotId = parkingLotId;
        this.floorId = floorId;
        this.totalSlots = totalSlots;
        this.freeSlots = new HashMap<VehicleType,SortedSet<Slot>>();
        this.occupiedSlots = new HashMap<String,Slot>();
        for (VehicleType type : VehicleType.values()) {
            freeSlots.put(type, new TreeSet<>());
        }

        for(int j=1;j<=this.totalSlots;j++)
        {   
            Slot slot;
            if(j==1)
            {
                slot = new Slot(this.parkingLotId, this.floorId, j, VehicleType.TRUCK,null);
                freeSlots.get(VehicleType.TRUCK).add(slot);
            }else if(j==2 || j==3)
            {
                slot = new Slot(this.parkingLotId, this.floorId, j, VehicleType.BIKE,null);
                freeSlots.get(VehicleType.BIKE).add(slot);
            }else{
                slot = new Slot(this.parkingLotId, this.floorId, j, VehicleType.CAR,null);
                freeSlots.get(VehicleType.CAR).add(slot);
            }
        }
    }
    
    public synchronized int getFreeSlots(VehicleType type)
    {
        return this.freeSlots.get(type).size();
    }
    
    public synchronized boolean canPark(VehicleType type)
    {
        return this.freeSlots.get(type).size()!=0;
    }

    public synchronized String parkVehicle(Vehicle v, VehicleType type)
    {    
        SortedSet<Slot> slots = this.freeSlots.get(type);
        Slot slot = slots.first();
        String id = slot.parkVehicle(v);
        this.occupiedSlots.put(id, slot);
        slots.remove(slot);
        return id;
    }

    public synchronized boolean unparkVehicle(String ticket_id)
    {
        if(occupiedSlots.containsKey(ticket_id))
        {  
            
            Slot slot = occupiedSlots.get(ticket_id);
            VehicleType type = slot.getType();
            slot.unparkVehicle();
            this.freeSlots.get(type).add(slot);
            this.occupiedSlots.remove(ticket_id);
            return true;
        }else{
            System.out.println("Invalid ticket ");
            return false;
        }
    }
}




