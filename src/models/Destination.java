package models;

import java.util.ArrayList;

public class Destination {

    // Each destination has a name, and a list of the activities available at that destination.

    private final String name;
    private final ArrayList<Activity> activities;

    public Destination(String name){
        this.name = name;
        this.activities = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Activity[] getActivities() {
        return activities.toArray(Activity[]::new);
    }

    public void addActivity(Activity activity){
        activities.add(activity);
    }

    @Override
    public String toString() {
        return "Destination{" +
                "name='" + name + '\'' +
                '}';
    }
}
