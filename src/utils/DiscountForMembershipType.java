package utils;

import models.MembershipType;

public class DiscountForMembershipType {
    public static double getDiscountFor(MembershipType membershipType){
        return switch (membershipType) {
            case standard -> 0;
            case gold -> 0.1;
            case premium -> 1;
        };
    }
}
