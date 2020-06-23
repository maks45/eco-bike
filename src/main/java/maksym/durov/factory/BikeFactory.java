package maksym.durov.factory;

import maksym.durov.BikeType;
import maksym.durov.model.abstractbike.Bike;

public class BikeFactory {
    public Bike getBike(BikeType type, String... bikeParams) {
        switch (type) {
            case E_BIKE:
                return getEBike(bikeParams);
            case SPEEDELEC:
                return getSpeedelec(bikeParams);
            case FOLDING_BIKE:
                return getFoldingBike(bikeParams);
            default:
                return null;
        }
    }

    private Bike getEBike(String... params){
        return null;
    }
    private Bike getSpeedelec(String... params){
        return null;
    }
    private Bike getFoldingBike(String... params){
        return null;
    }
}
