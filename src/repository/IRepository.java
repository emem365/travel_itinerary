package repository;

import exceptions.TravelPackageCapacityReached;
import models.Activity;
import models.Destination;
import models.Passenger;
import models.TravelPackage;

public interface IRepository{

    // Returns a list of all travel packages available
    TravelPackage getTravelPackage();

    // Returns a list of all destinations
    Destination[] getAllDestinations();

    // Add [TravelPackage] object to the list
    void setTravelPackage(TravelPackage travelPackage);

    Activity getActivityFromId(int id);

    Passenger getPassengerFromId(int id);

    void addToMap(Activity activity);

    // Add [Passenger] object to the list
    void addPassenger(Passenger passenger)  throws TravelPackageCapacityReached;

    int getNewActivityId();

    int getNewPassengerId();
}
