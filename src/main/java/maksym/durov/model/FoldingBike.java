package maksym.durov.model;

import lombok.EqualsAndHashCode;
import maksym.durov.model.abstractbike.Bike;

@EqualsAndHashCode(callSuper = true)
public class FoldingBike extends Bike {
    public static final String PREFIX = "FOLDING BIKE";
    public static final String STRING_REPRESENTATION_PATTERN = "%s %s; %d; %d; %d; %s; %s; %d";
    private int wheelsSize;
    private int gearsNumber;

    public FoldingBike(String brand, int wheelsSize,
                       int gearsNumber, int weight,
                       boolean backAndFrontLights,
                       String color, int price) {
        super(brand, weight, backAndFrontLights, color, price);
        this.wheelsSize = wheelsSize;
        this.gearsNumber = gearsNumber;
    }

    public int getWheelsSize() {
        return wheelsSize;
    }

    public void setWheelsSize(int wheelsSize) {
        this.wheelsSize = wheelsSize;
    }

    public int getGearsNumber() {
        return gearsNumber;
    }

    public void setGearsNumber(int gearsNumber) {
        this.gearsNumber = gearsNumber;
    }

    @Override
    public String toString() {
        return PREFIX + " " + getBrand() + " with " + getGearsNumber()
                + "gear(s) and " + (isBackAndFrontLights() ? "" : "no ")
                + "head/tail light.\n" + "Price: " + getPrice() + " euros.";
    }

    @Override
    public String getPrefix() {
        return FoldingBike.PREFIX;
    }
}
