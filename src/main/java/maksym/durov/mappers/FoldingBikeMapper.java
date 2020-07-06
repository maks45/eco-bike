package maksym.durov.mappers;

import maksym.durov.model.FoldingBike;
import org.springframework.stereotype.Component;

@Component
public class FoldingBikeMapper implements GenericBikeMapper<FoldingBike> {
    private static final int SIZE_OF_WHEELS_FIELD = 1;
    private static final int GEARS_NUMBER_FIELD = 2;
    private static final int WEIGHT_FIELD = 3;
    private static final int LIGHTS_FIELD = 4;

    @Override
    public FoldingBike getObject(String line) {
        String[] data = line.replaceFirst(FoldingBike.PREFIX + " ", "")
                .split(";");
        return new FoldingBike(
            data[BRAND_FIELD].trim(),
            Integer.parseInt(data[SIZE_OF_WHEELS_FIELD].trim()),
            Integer.parseInt(data[GEARS_NUMBER_FIELD].trim()),
            Integer.parseInt(data[WEIGHT_FIELD].trim()),
            data[LIGHTS_FIELD].trim().equals("TRUE"),
            data[COLOR_FIELD].trim(),
            Integer.parseInt(data[PRICE_FIELD].trim())
        );
    }

    @Override
    public String getStringInVcs(FoldingBike foldingBike) {
        return String.format(FoldingBike.STRING_REPRESENTATION_PATTERN,
                foldingBike.getPrefix(), foldingBike.getBrand(), foldingBike.getWheelsSize(),
                foldingBike.getGearsNumber(), foldingBike.getWeight(),
                String.valueOf(foldingBike.isBackAndFrontLights()).toUpperCase(),
                foldingBike.getColor(), foldingBike.getPrice());
    }
}
