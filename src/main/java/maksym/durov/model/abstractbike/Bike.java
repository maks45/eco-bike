package maksym.durov.model.abstractbike;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public abstract class Bike {
    public static final String STRING_REPRESENTATION_PATTERN = "%s %s; %d; %d; %s; %d; %s; %d";
    private String brand;
    private int weight;
    private boolean backAndFrontLights;
    private String color;
    private int price;

    public Bike(String brand,
                int weight,
                boolean backAndFrontLights,
                String color,
                int price) {
        this.brand = brand;
        this.weight = weight;
        this.backAndFrontLights = backAndFrontLights;
        this.color = color;
        this.price = price;
    }

    public abstract String getPrefix();

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isBackAndFrontLights() {
        return backAndFrontLights;
    }

    public void setBackAndFrontLights(boolean backAndFrontLights) {
        this.backAndFrontLights = backAndFrontLights;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
