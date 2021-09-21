package utils;

import models.*;

public class PrintToScreen {

    static String indent = "   ";

    public static  void printMenu(){
        System.out.println("#####################################################\n");
        System.out.println("1. Display itinerary of a travel package");
        System.out.println("2. Display passengers for a travel package");
        System.out.println("3. Display passenger details");
        System.out.println("4. Show available activities");
        System.out.println("5. Sign up for an activity");
        System.out.println("6. Add a passenger");
        System.out.println("0. Exit\n");
        System.out.print("Input option number: ");
    }

    public static void printItineraryOf(TravelPackage travelPackage){
        printBorder(1);
        printIndent(1);
        System.out.println("TravelPackage: "+travelPackage.getName()+"\n");

        for (Destination destination: travelPackage.getItinerary()) {
            if(destination == null){
                break;
            }
            printDestination(destination);
        }
        System.out.println();
        printBorder(1);
    }

    public static void printPassengersOf(TravelPackage travelPackage){
        printBorder(1);
        printIndent(1);
        System.out.println("Package: "+travelPackage.getName());
        printIndent(1);
        System.out.println("Passenger Capacity: "+travelPackage.getCapacity());
        printIndent(1);
        System.out.println("Number of passengers currently enrolled: "+travelPackage.getPassengersEnrolled());
        printIndent(1);
        System.out.println("Enrolled Passengers:");
        for (Passenger passenger: travelPackage.getPassengers()) {
            if(passenger == null){
                break;
            }
            printPassenger(passenger);
        }
        System.out.println();
        printBorder(1);
    }

    public static void printPassengerDetail(Passenger passenger) {
        printBorder(1);
        printIndent(1);
        System.out.println("Name: " + passenger.getName());
        printIndent(1);
        System.out.println("Passenger number: " + passenger.getPassengerNumber());
        if (passenger.getMembershipType() != MembershipType.premium) {
            printIndent(1);
            System.out.println("Balance : Rs. " + passenger.getBalance());
        }
        printIndent(1);
        System.out.println("Activities Enrolled:");
        for (Activity activity : passenger.getActivitiesEnrolled()) {
            if(activity == null){
                break;
            }
            printIndent(2);
            System.out.println("Activity : " + activity.getName());
            printIndent(2);
            System.out.println(" - Destination : " + activity.getDestination().getName());
            printIndent(2);
            System.out.println(" - Price paid : " + formatCurrency(ActivityCostForPassenger.calculate(activity, passenger)));
        }
        printBorder(1);
    }

    public static void printDestination(Destination destination){
        printIndent(2);
        System.out.println("Destination: "+destination.getName()+"\n");
        for (Activity activity: destination.getActivities()) {
            if(activity == null){
                break;
            }
            printActivity(activity);
        }
    }

    public static void printActivity(Activity activity, int indentCount){
        printIndent(indentCount);
        System.out.println("Activity: "+activity.getName());
        printIndent(indentCount);
        System.out.println(" - Activity Number: "+activity.getActivityNumber());
        printIndent(indentCount);
        System.out.println(" - Description: "+activity.getDescription());
        printIndent(indentCount);
        System.out.println(" - Cost: "+formatCurrency(activity.getCost()));
        printIndent(indentCount);
        System.out.println(" - Capacity: "+activity.getCapacity());
    }

    public static void printActivity(Activity activity){
        printActivity(activity, 3);
    }

    public static void printPassenger(Passenger passenger){
        printIndent(2);
        System.out.println("Name: "+passenger.getName());
        printIndent(2);
        System.out.println(" - Passenger Number: "+passenger.getPassengerNumber());
    }

    public static void printIndent(int indentCount){
        for(int i=0; i<indentCount; i++){
            System.out.print(indent);
        }
    }

    public static void printBorder(int indentCount){
        printIndent(indentCount);
        System.out.println("**************************************************");
    }

    public static void printInvalidInput(){
        System.out.println("\n**Invalid Input**\n");
    }

    public static String formatCurrency(double rs){
        return "Rs. "+String.format("%,.2f", rs);
    }
}
