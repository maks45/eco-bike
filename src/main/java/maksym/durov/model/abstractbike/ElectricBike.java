package maksym.durov.model.abstractbike;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public abstract class ElectricBike extends Bike {
    protected static final String STRING_REPRESENTATION_PATTERN = "%s%s; %d; %d; %s; %d; %s; %d";
    private int maxSpeed;
    private int batteryCapacity;

    public ElectricBike(String brand,
                        int weight,
                        boolean backAndFrontLights,
                        String color,
                        int price,
                        int maxSpeed,
                        int batteryCapacity) {
        super(brand, weight, backAndFrontLights, color, price);
        this.maxSpeed = maxSpeed;
        this.batteryCapacity = batteryCapacity;
    }

    public ElectricBike(String brand, int weight, boolean backAndFrontLights,
                        String color, int price) {
        super(brand, weight, backAndFrontLights, color, price);
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }
}
