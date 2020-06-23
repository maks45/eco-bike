package maksym.durov;

import maksym.durov.factory.BikeFactory;
import maksym.durov.model.abstractbike.Bike;

public class Main {
    public static void main(String[] args) {
        Bike bike = new BikeFactory().getBike(BikeType.E_BIKE);
    }
}
