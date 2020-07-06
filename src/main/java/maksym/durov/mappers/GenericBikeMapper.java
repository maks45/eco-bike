package maksym.durov.mappers;

import maksym.durov.model.abstractbike.Bike;

public interface GenericBikeMapper<T extends Bike> {
    int BRAND_FIELD = 0;
    int MAX_SPEED_FIELD = 1;
    int WEIGHT_FIELD = 2;
    int LIGHTS_FIELD = 3;
    int CAPACITY_FIELD = 4;
    int COLOR_FIELD = 5;
    int PRICE_FIELD = 6;

    T getObject(String line);

    String getStringInVcs(T t);
}
