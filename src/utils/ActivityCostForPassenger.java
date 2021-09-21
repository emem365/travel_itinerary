package utils;

import models.Activity;
import models.Passenger;

public class ActivityCostForPassenger {
    public static double calculate(Activity activity, Passenger passenger) {
        int costOfActivity = activity.getCost();
        double discountPercentage = DiscountForMembershipType.getDiscountFor(passenger.getMembershipType());
        return costOfActivity * (1 - discountPercentage);
    }
}
