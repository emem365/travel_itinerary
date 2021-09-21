package repository;

import exceptions.TravelPackageCapacityReached;
import models.Activity;
import models.Destination;
import models.Passenger;
import models.TravelPackage;

import java.util.HashMap;

public class Repository implements  IRepository{

    private TravelPackage travelPackage;
    private int passengerIdHelper;
    private int activityIdHelper;
    private final HashMap<Integer, Activity> activityHashMap;
    private final HashMap<Integer, Passenger> passengerHashMap;

    private Repository(){
        travelPackage = null;
        passengerIdHelper = 0;
        activityIdHelper = 0;
        activityHashMap = new HashMap<>();
        passengerHashMap = new HashMap<>();
    }

    private static Repository instance  = null;

    public static Repository getInstance(){
        if(instance==null){
            instance = new Repository();
        }
        return instance;
    }

    @Override
    public TravelPackage getTravelPackage() {
        return travelPackage;
    }

    @Override
    public Destination[] getAllDestinations() {
        return travelPackage.getItinerary();
    }

    @Override
    public void setTravelPackage(TravelPackage travelPackage) {
        this.travelPackage = travelPackage;
    }

    @Override
    public Activity getActivityFromId(int id) {
        return activityHashMap.getOrDefault(id, null);
    }

    @Override
    public Passenger getPassengerFromId(int id) {
        return passengerHashMap.getOrDefault(id, null);
    }

    @Override
    public void addToMap(Activity activity) {
        activityHashMap.put(activity.getActivityNumber(), activity);
    }

    @Override
    public void addPassenger(Passenger passenger) throws TravelPackageCapacityReached {
        passengerHashMap.put(passenger.getPassengerNumber(), passenger);
        travelPackage.addPassenger(passenger);
    }

    @Override
    public int getNewActivityId() {
        return activityIdHelper++;
    }

    @Override
    public int getNewPassengerId() {
        return passengerIdHelper++;
    }
}
