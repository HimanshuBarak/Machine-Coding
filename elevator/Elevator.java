package elevator;

import java.util.PriorityQueue;



// public class Elevator {

//     int currentFloor;
//     Direction direction;
//     PriorityQueue<Request> upQueue;
//     PriorityQueue<Request> downQueue;

//     public Elevator(int currentFloor) {
//         this.currentFloor = currentFloor;

//         this.direction = Direction.IDLE;

//         // use default, which is a min heap
//         upQueue = new PriorityQueue<>((a, b) -> a.desiredFloor - b.desiredFloor);

//         // use a max heap
//         downQueue =  new PriorityQueue<>((a, b) -> b.desiredFloor - a.desiredFloor);
//     }

//     public void sendUpRequest(Request upRequest) {
//         // If the request is sent from out side of the elevator,
//         // we need to stop at the current floor of the requester
//         // to pick him up, and then go the the desired floor.
//         if (upRequest.location == Location.OUTSIDE_ELEVATOR) {
//             // Go pick up the requester who is outside of the elevator
//             upQueue.offer(new Request(upRequest.currentFloor,
//                 upRequest.currentFloor,
//                 Direction.UP,
//                 Location.OUTSIDE_ELEVATOR));

//             System.out.println("Append up request going to floor " + upRequest.currentFloor + ".");
//         }

//         // Go to the desired floor
//         upQueue.offer(upRequest);

//         System.out.println("Append up request going to floor " + upRequest.desiredFloor + ".");
//     }

//     public void sendDownRequest(Request downRequest) {
//         // Similar to the sendUpRequest logic
//         if (downRequest.location == Location.OUTSIDE_ELEVATOR) {
//             downQueue.offer(new Request(downRequest.currentFloor,
//                 downRequest.currentFloor,
//                 Direction.DOWN,
//                 Location.OUTSIDE_ELEVATOR));

//             System.out.println("Append down request going to floor " + downRequest.currentFloor + ".");
//         }

//         // Go to the desired floor
//         downQueue.offer(downRequest);

//         System.out.println("Append down request going to floor " + downRequest.desiredFloor + ".");
//     }

//     public void run() {
//         while (!upQueue.isEmpty() || !downQueue.isEmpty()) {
//             processRequests();
//         }

//         System.out.println("Finished all requests.");
//         this.direction = Direction.IDLE;
//     }

//     private void processRequests() {
//         if (this.direction == Direction.UP || this.direction == Direction.IDLE) {
//             processUpRequest();
//             processDownRequest();
//         } else {
//             processDownRequest();
//             processUpRequest();
//         }
//     }

//     private void processUpRequest() {
//         while (!upQueue.isEmpty()) {
//             Request upRequest = upQueue.poll();
//             // Communicate with hardware
//             this.currentFloor = upRequest.desiredFloor;
//             System.out.println("Processing up requests. Elevator stopped at floor " + this.currentFloor + ".");
//         }
//         if (!downQueue.isEmpty()) {
//             this.direction = Direction.DOWN;
//         } else {
//             this.direction = Direction.IDLE;
//         }
//     }

//     private void processDownRequest() {
//         while (!downQueue.isEmpty()) {
//             Request downRequest = downQueue.poll();
//             // Communicate with hardware
//             this.currentFloor = downRequest.desiredFloor;
//             System.out.println("Processing down requests. Elevator stopped at floor " + this.currentFloor + ".");
//         }
//         if (!upQueue.isEmpty()) {
//             this.direction = Direction.UP;
//         } else {
//             this.direction = Direction.IDLE;
//         }
//     }


//     public static void main(String[] args) {
//         Elevator elevator = new Elevator(0);

//         Request upRequest1 = new Request(elevator.currentFloor, 5, Direction.UP, Location.INSIDE_ELEVATOR);
//         Request upRequest2 = new Request(elevator.currentFloor, 3, Direction.UP, Location.INSIDE_ELEVATOR);

//         Request downRequest1 = new Request(elevator.currentFloor, 1, Direction.DOWN, Location.INSIDE_ELEVATOR);
//         Request downRequest2 = new Request(elevator.currentFloor, 2, Direction.DOWN, Location.INSIDE_ELEVATOR);
//         Request downRequest3 = new Request(4, 0, Direction.DOWN, Location.OUTSIDE_ELEVATOR);

//         // Two people inside of the elevator pressed button to go up to floor 5 and 3.
//         elevator.sendUpRequest(upRequest1);
//         elevator.sendUpRequest(upRequest2);

//         // One person outside of the elevator at floor 4 pressed button to go down to floor 0
//         elevator.sendDownRequest(downRequest3);

//         // Two person inside of the elevator pressed button to go down to floor 2 and 1.
//         elevator.sendDownRequest(downRequest1);
//         elevator.sendDownRequest(downRequest2);
//         elevator.run();
//     }

// }


public class Elevator {
    private Direction direction;
     int currentFloor;
    private PriorityQueue<Request> upRequests;
    private PriorityQueue<Request> downRequests;
    private PriorityQueue<Request> nextUpRequests;
   

    public Elevator(int currentFloor) {
        this.direction = Direction.IDLE;
        upRequests = new PriorityQueue<>((a, b) -> Integer.compare(a.desiredFloor, b.desiredFloor));
        this.currentFloor = currentFloor;
        downRequests = new PriorityQueue<>((a, b) -> Integer.compare(b.desiredFloor, a.desiredFloor));
        nextUpRequests = new PriorityQueue<>((a, b) -> Integer.compare(a.desiredFloor, b.desiredFloor));
        
    }

    void sendUpRequest(Request request) {

        if (request.location == Location.OUTSIDE_ELEVATOR) {
            // in this case we also need to pick the customer from their respective floor as well
            if (request.currentFloor < this.currentFloor) {
                //we can't satisfy this request in the cycle of going up we will have to do it in the next one
                nextUpRequests
                        .add(new Request(request.currentFloor, request.currentFloor, Direction.UP, request.location));
            } else {
                upRequests.add(new Request(request.currentFloor, request.currentFloor, Direction.UP, request.location));
            }

        }
        if (request.desiredFloor < this.currentFloor) 
            nextUpRequests.add(request);
        else    
        upRequests.add(request);
    }
    
    void sendDownRequest(Request request) {
        if (request.location == Location.OUTSIDE_ELEVATOR) {
            // in this case we also need to pick the customer from their respective floor as well
            
            downRequests.add(
                    new Request(request.currentFloor, request.currentFloor, Direction.DOWN, request.location));
        }
        downRequests.add(request);
    }
    
    private void processUpRequest() {
        while (!upRequests.isEmpty()) {
            Request req = upRequests.poll();
            this.currentFloor = req.desiredFloor;
            System.out.println("Processing up requests. Elevator stopped at floor " + this.currentFloor + ".");
        }
        if (!nextUpRequests.isEmpty()) {
            upRequests = nextUpRequests;
            nextUpRequests = new PriorityQueue<>((a, b) -> Integer.compare(a.desiredFloor, b.desiredFloor));
        }
        if (!downRequests.isEmpty()) {
            this.direction = Direction.DOWN;
        } else {
            this.direction = Direction.IDLE;
        }
    }
    
    private void processDownRequest() {
        while (!downRequests.isEmpty()) {
            Request downRequest = downRequests.poll();
            // Communicate with hardware
            this.currentFloor = downRequest.desiredFloor;
            System.out.println("Processing down requests. Elevator stopped at floor " + this.currentFloor + ".");
        }
        if (!upRequests.isEmpty()) {
            this.direction = Direction.UP;
        } else {
            this.direction = Direction.IDLE;
        }
    }
    
    private void processRequests() {
        if (this.direction == Direction.UP || this.direction == Direction.IDLE) {
            processUpRequest();
            processDownRequest();
        } else {
            processDownRequest();
            processUpRequest();
        }
    }
    
    public void run() {
        while (!upRequests.isEmpty() || !downRequests.isEmpty()) {
              processRequests();
        }
        
        System.out.println("Finished all requests.");
            this.direction = Direction.IDLE;
    }

}