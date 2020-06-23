package maksym.durov.model.abstractbike;

import maksym.durov.BikeType;

public abstract class Bike {
    private String brand;
    private int weight;
    private boolean backAndFrontLights;
    private String color;
    private int price;


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
