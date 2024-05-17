
package parkinglot;
import java.util.*;


class Main{
    public static void main(String[] args){
        System.out.println("Hello Workday!");
        ParkingLot lot = null;
        Scanner io = new Scanner(System.in);
        while(io.hasNextLine())
        {
            String input = io.nextLine();
            System.out.println();

            String [] commands = input.trim().split(" ");
            String command = commands[0];
            switch(command){
                case "create_parking_lot":
                    String parkingLotId = commands[1];
                    int floors = Integer.parseInt(commands[2]);
                    int slotsPerFloor = Integer.parseInt(commands[3]);
                    lot = new ParkingLot(parkingLotId, floors, slotsPerFloor);
                    break;
                case "park_vehicle":
                    VehicleType vehicleType = VehicleType.valueOf(commands[1].toUpperCase());
                    String registrationNumber = commands[2];
                    String color = commands[3]; 
                    Vehicle v = new Vehicle(vehicleType, registrationNumber, color);
                    String tik = lot.parkVehicle(v);
                    System.out.println(tik);
                    break;
                case "unpark_vehicle":
                    String ticket = commands[1];
                    lot.unParkVehicle(ticket);
                    System.out.println(lot.getFreeSlots(VehicleType.CAR));  
                case "exit":
                  System.exit(0);         
            }
        }
    }
}

