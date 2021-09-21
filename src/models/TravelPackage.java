package models;


import exceptions.TravelPackageCapacityReached;

public class TravelPackage {

    // Each travel package has a name, a passenger capacity, an itinerary (list of destinations) and
    // a list of passenger.

    private final String name;
    private final int capacity;
    private final Destination[] itinerary;
    private final Passenger[] passengers;
    private int passengersEnrolled;

    public TravelPackage(String name, int capacity, Destination[] itinerary){
        this.name = name;
        this.capacity = capacity;
        this.itinerary = itinerary;
        this.passengers = new Passenger[capacity];
        this.passengersEnrolled = 0;
    }

    public String getName(){
        return name;
    }

    public int getCapacity(){
        return capacity;
    }

    public Destination[] getItinerary(){
        return itinerary;
    }

    public Passenger[] getPassengers() {
        return passengers;
    }

    public int getPassengersEnrolled() {
        return passengersEnrolled;
    }

    public void addPassenger(Passenger passenger) throws TravelPackageCapacityReached {
        if(passengersEnrolled<capacity){
            passengers[passengersEnrolled] = passenger;
            passengersEnrolled++;
        } else {
            throw new TravelPackageCapacityReached(this);
        }
    }

    @Override
    public String toString() {
        return "TravelPackage{" +
                "name='" + name + '\'' +
                '}';
    }
}
