package maksym.durov.mappers;

import maksym.durov.model.Speedelec;
import maksym.durov.model.abstractbike.Bike;
import org.springframework.stereotype.Component;

@Component
public class SpeedelecMapper implements GenericBikeMapper<Speedelec> {

    @Override
    public Speedelec getObject(String line) {
        String[] data = line.replaceFirst(Speedelec.PREFIX + " ", "").split(";");
        return new Speedelec(
                data[BRAND_FIELD].trim(),
                Integer.parseInt(data[MAX_SPEED_FIELD].trim()),
                Integer.parseInt(data[WEIGHT_FIELD].trim()),
                data[LIGHTS_FIELD].trim().equals("TRUE"),
                Integer.parseInt(data[CAPACITY_FIELD].trim()),
                data[COLOR_FIELD].trim(),
                Integer.parseInt(data[PRICE_FIELD].trim())
        );
    }

    @Override
    public String getStringInVcs(Speedelec speedelec) {
        return String.format(Bike.STRING_REPRESENTATION_PATTERN,
                speedelec.getPrefix(), speedelec.getBrand(), speedelec.getMaxSpeed(),
                speedelec.getWeight(), String.valueOf(speedelec
                        .isBackAndFrontLights()).toUpperCase(),
                speedelec.getBatteryCapacity(), speedelec.getColor(), speedelec.getPrice());
    }
}
