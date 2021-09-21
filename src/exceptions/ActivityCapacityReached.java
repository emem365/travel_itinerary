package exceptions;

import models.Activity;

public class ActivityCapacityReached extends MaximumCapacityReachedException {
    public ActivityCapacityReached(Activity activity){
        super(activity.toString());
    }
}