package models;

import java.util.ArrayList;

public class Passenger{

    // Each passenger has a name and a passenger number.

    private final int passengerNumber;
    private final String name;
    private final MembershipType membershipType;
    private double balance;
    private final ArrayList<Activity> activitiesEnrolled;

    public Passenger(int passengerNumber, String name, MembershipType membershipType, double balance){
        this.passengerNumber = passengerNumber;
        this.name = name;
        this.membershipType = membershipType;
        this.balance = balance;
        this.activitiesEnrolled = new ArrayList<>();
    }

    public int getPassengerNumber() {
        return passengerNumber;
    }

    public String getName() {
        return name;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void addActivity(Activity activity){
        activitiesEnrolled.add(activity);
    }

    public Activity[] getActivitiesEnrolled() {
        return activitiesEnrolled.toArray(Activity[]::new);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "passengerNumber=" + passengerNumber +
                ", name='" + name + '\'' +
                '}';
    }
}
