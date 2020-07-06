package maksym.durov.mappers;

import maksym.durov.model.EBike;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class EBikeMapperTest {
    private final EBikeMapper eBikeMapper = new EBikeMapper();

    @Test
    void getObject_Ok() {
        EBike expected = new EBike("Brand", 200, 100, true,
                1000, "black", 1000);
        EBike actual = eBikeMapper.getObject("E-BIKE Brand; 200; 100; TRUE; 1000; black; 1000");
        assertEquals(expected, actual);
    }

    @Test
    void getStringInVcs() {
        String expected = "E-BIKE Brand; 200; 100; TRUE; 1000; black; 1000";
        String actual = eBikeMapper.getStringInVcs(new EBike("Brand", 200,
                100, true,
                1000, "black", 1000));
        assertEquals(expected, actual);
    }
}