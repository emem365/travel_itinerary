package exceptions;

import models.TravelPackage;

public class TravelPackageCapacityReached extends MaximumCapacityReachedException {
    public TravelPackageCapacityReached(TravelPackage travelPackage){
        super(travelPackage.toString());
    }
}
