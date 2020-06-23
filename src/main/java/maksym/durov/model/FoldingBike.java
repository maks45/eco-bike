package maksym.durov.model;

import maksym.durov.model.abstractbike.Bike;

public class FoldingBike extends Bike {
    private int wheelsSize;
    private int gearsNumber;

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
}
