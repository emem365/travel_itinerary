package models;

import exceptions.ActivityCapacityReached;

public class Activity{

    // Each activity has a name, a description, a cost and a capacity. Each activity is available at
    // one destination only.

    private final int activityNumber;
    private final String name;
    private final String description;
    private final int cost;
    private final int capacity;
    private final Passenger[] enrolledPassengers;
    private int currentlyEnrolled;
    private final Destination destination;

    public Activity(int activityNumber, String name, String description, int cost, int capacity, Destination destination){
        this.activityNumber = activityNumber;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
        this.destination = destination;
        this.currentlyEnrolled = 0;
        this.enrolledPassengers = new Passenger[capacity];
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Destination getDestination() {
        return destination;
    }

    public int getCost() {
        return cost;
    }

    public int getActivityNumber() {
        return activityNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentlyEnrolled() {
        return currentlyEnrolled;
    }

    public void signUpForActivity(Passenger passenger) throws ActivityCapacityReached {
        if(currentlyEnrolled<capacity){
            enrolledPassengers[currentlyEnrolled] = passenger;
            currentlyEnrolled++;
        } else {
            throw new ActivityCapacityReached(this);
        }
    }

    @Override
    public String toString() {
        return "Activity{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                '}';
    }
}
