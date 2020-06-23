package maksym.durov;

public enum BikeType {
    FOLDING_BIKE("FOLDING BIKE"),
    E_BIKE("E-BIKE"),
    SPEEDELEC("SPEEDELEC");

    public final String value;

    BikeType(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
