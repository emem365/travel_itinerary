import exceptions.ActivityCapacityReached;
import exceptions.TravelPackageCapacityReached;
import models.*;
import repository.IRepository;
import repository.Repository;
import utils.ActivityCostForPassenger;
import utils.PrintToScreen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static IRepository repository;
    static BufferedReader bufferedReader;
    public static void main(String[] args){
        InputStreamReader isr = new InputStreamReader(System.in);
        bufferedReader = new BufferedReader(isr);
        repository = Repository.getInstance();
        boolean exit = false;
        while(!exit){
            if(repository.getTravelPackage() == null){
                try{
                    addTravelPackage();
                } catch (IOException | NumberFormatException exception){
                    PrintToScreen.printInvalidInput();
                }
            }
            PrintToScreen.printMenu();
            try{
                int input = Integer.parseInt(bufferedReader.readLine().trim());
                if(input == 0){
                    exit = true;
                } else {
                    processRequest(input);
                }
            } catch (IOException | NumberFormatException exception){
                PrintToScreen.printInvalidInput();
            }
        }
    }

    static void processRequest(int input) throws IOException, NumberFormatException{
        switch (input) {
            case 1 -> displayItinerary();
            case 2 -> displayPassengersForTravelPackage();
            case 3 -> displayPassengerDetail();
            case 4 -> showAllAvailableActivities();
            case 5 -> signUpForActivity();
            case 6 -> addPassenger();
            default -> PrintToScreen.printInvalidInput();
        }
    }

    static void addPassenger() throws IOException, NumberFormatException {
        System.out.println("\nInitialise a passenger\n");
        System.out.print("Enter name for this passenger: ");
        String name = bufferedReader.readLine();
        System.out.println("Choose membership type:");
        System.out.println("1. Standard");
        System.out.println("2. Gold");
        System.out.println("3. Premium");
        int i = Integer.parseInt(bufferedReader.readLine().trim());
        if(i>0 && i<=3){
            MembershipType type = switch (i){
                case 1 -> MembershipType.standard;
                case 2 -> MembershipType.gold;
                case 3 -> MembershipType.premium;
                default -> null;
            };
            int balance;
            if(type == MembershipType.premium){
                balance = 9999999;
            } else {
                System.out.print("Balance: Rs. ");
                balance = Integer.parseInt(bufferedReader.readLine().trim());
            }
            Passenger passenger = new Passenger(repository.getNewPassengerId(), name, type, balance);
            try{
                repository.addPassenger(passenger);
                System.out.println("Added Passenger: ");
                PrintToScreen.printPassenger(passenger);
            } catch(TravelPackageCapacityReached exception){
                System.out.println(exception.getMessage());
            }
        } else {
            PrintToScreen.printInvalidInput();
        }
    }

    static void addTravelPackage() throws IOException, NumberFormatException {
        System.out.println("\nInitialise travel package\n");
        System.out.print("Enter name for this package: ");
        String name = bufferedReader.readLine();
        System.out.print("Enter capacity in numbers for this package: ");
        int capacity = Integer.parseInt(bufferedReader.readLine().trim());
        System.out.println("Creating itinerary for this package");
        ArrayList<Destination> destinations = new ArrayList<>();
        boolean exitDestination = false;
        while(!exitDestination){
            System.out.print("Destination name: ");
            String destinationName = bufferedReader.readLine();
            Destination destination = new Destination(destinationName);
            boolean exitActivities = false;
            while(!exitActivities){
                System.out.println("For destination "+destinationName+":");
                System.out.print("Activity name: ");
                String activityName = bufferedReader.readLine().trim();
                System.out.print("Description: ");
                String description = bufferedReader.readLine().trim();
                System.out.print("Cost: ");
                int cost = Integer.parseInt(bufferedReader.readLine().trim());
                System.out.print("Capacity: ");
                int activityCapacity = Integer.parseInt(bufferedReader.readLine().trim());

                Activity activity = new Activity(repository.getNewActivityId(), activityName, description, cost, activityCapacity, destination);
                destination.addActivity(activity);
                repository.addToMap(activity);
                System.out.println("\nAdded Activity: "+activity.getName());
                System.out.print("Add more activities? (Y\\N): ");
                String in = bufferedReader.readLine().trim();
                exitActivities = !in.equalsIgnoreCase("y") && !in.equalsIgnoreCase("yes");
            }

            destinations.add(destination);
            System.out.println("\nAdded Destination: "+destination.getName());
            System.out.print("Add more destinations? (Y\\N): ");
            String in = bufferedReader.readLine().trim();
            exitDestination = !in.equalsIgnoreCase("y") && !in.equalsIgnoreCase("yes");
        }
        System.out.println("\nTravel Package designed. Adding to memory");

        TravelPackage travelPackage = new TravelPackage(name, capacity, destinations.toArray(Destination[]::new));
        repository.setTravelPackage(travelPackage);
        System.out.println("DONE\n");
    }

    static void signUpForActivity() throws IOException, NumberFormatException{
        System.out.println("Sign up for an activity");
        System.out.print("Activity number: ");
        int activityNumber = Integer.parseInt(bufferedReader.readLine().trim());
        Activity activity = repository.getActivityFromId(activityNumber);
        if(activity == null){
            PrintToScreen.printInvalidInput();
            return;
        }
        System.out.print("Passenger number: ");
        int passengerNumber = Integer.parseInt(bufferedReader.readLine().trim());
        Passenger passenger = repository.getPassengerFromId(passengerNumber);
        if(passenger == null){
            PrintToScreen.printInvalidInput();
            return;
        }
        try{
            activity.signUpForActivity(passenger);
            passenger.setBalance(passenger.getBalance() - ActivityCostForPassenger.calculate(activity, passenger));
            passenger.addActivity(activity);
        } catch (ActivityCapacityReached exception){
            System.out.println(exception.getMessage());
        }
    }

    static void displayItinerary(){
        PrintToScreen.printItineraryOf(repository.getTravelPackage());
    }

    static void displayPassengersForTravelPackage(){
        PrintToScreen.printPassengersOf(repository.getTravelPackage());
    }

    static void displayPassengerDetail() throws IOException, NumberFormatException{
        System.out.println("See details for passenger");
        System.out.print("Passenger number: ");
        int passengerNumber = Integer.parseInt(bufferedReader.readLine().trim());
        Passenger passenger = repository.getPassengerFromId(passengerNumber);
        if(passenger == null){
            PrintToScreen.printInvalidInput();
            return;
        }
        PrintToScreen.printPassengerDetail(passenger);
    }

    static void showAllAvailableActivities(){
        System.out.println("Available activities");
        for (Destination destination: repository.getAllDestinations()) {
            if(destination == null){
                break;
            }
            for(Activity activity: destination.getActivities()){
                if(activity == null){
                    break;
                }
                if(activity.getCapacity()> activity.getCurrentlyEnrolled()){
                    PrintToScreen.printActivity(activity, 1);
                    PrintToScreen.printIndent(1);
                    System.out.println(" - Spaces available: "+(activity.getCapacity()- activity.getCurrentlyEnrolled()));
                }
            }
        }
    }
}