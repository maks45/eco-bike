package maksym.durov.model;

import lombok.EqualsAndHashCode;
import maksym.durov.model.abstractbike.ElectricBike;

@EqualsAndHashCode(callSuper = true)
public class Speedelec extends ElectricBike {
    public static final String PREFIX = "SPEEDELEC";

    public Speedelec(String brand, int maxSpeed,
                     int weight, boolean backAndFrontLights,
                     int batteryCapacity, String color, int price) {
        super(brand, weight, backAndFrontLights, color, price, maxSpeed, batteryCapacity);
    }

    @Override
    public String toString() {
        return PREFIX + " " + getBrand() + " with " + getBatteryCapacity()
                + "mAh battery and " + (isBackAndFrontLights() ? "" : "no ")
                + "head/tail light.\n" + "Price: " + getPrice() + " euros.";
    }

    @Override
    public String getPrefix() {
        return Speedelec.PREFIX;
    }
}
