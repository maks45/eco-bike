package maksym.durov.mappers;

import maksym.durov.model.EBike;
import maksym.durov.model.abstractbike.Bike;
import org.springframework.stereotype.Component;

@Component
public class EBikeMapper implements GenericBikeMapper<EBike> {

    @Override
    public EBike getObject(String line) {
        String[] data = line.replaceFirst(EBike.PREFIX + " ", "")
                .split(";");
        return new EBike(
                data[BRAND_FIELD].trim(),
                Integer.parseInt(data[MAX_SPEED_FIELD].trim()),
                Integer.parseInt(data[WEIGHT_FIELD].trim()),
                data[LIGHTS_FIELD].trim().equals("TRUE"),
                Integer.parseInt(data[CAPACITY_FIELD].trim()),
                data[COLOR_FIELD].trim(),
                Integer.parseInt(data[PRICE_FIELD].trim()));
    }

    @Override
    public String getStringInVcs(EBike bike) {
        return String.format(Bike.STRING_REPRESENTATION_PATTERN,
                bike.getPrefix(), bike.getBrand(), bike.getMaxSpeed(),
                bike.getWeight(), String.valueOf(bike.isBackAndFrontLights()).toUpperCase(),
                bike.getBatteryCapacity(), bike.getColor(), bike.getPrice());
    }
}
