package exceptions;

class MaximumCapacityReachedException extends Exception {
    public MaximumCapacityReachedException(String calledFrom){
        super(calledFrom+" : Maximum Capacity Reached");
    }
}