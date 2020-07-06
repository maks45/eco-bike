package maksym.durov.mappers;

import maksym.durov.model.FoldingBike;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class FoldingBikeMapperTest {
    private final FoldingBikeMapper foldingBikeMapper = new FoldingBikeMapper();

    @Test
    void getObject() {
        FoldingBike expected = new FoldingBike("West Bike", 14, 7, 14700,
                false, "black", 1289);
        FoldingBike actual = foldingBikeMapper
                .getObject("FOLDING BIKE West Bike; 14; 7; 14700; FALSE; black; 1289");
        assertEquals(expected, actual);
    }

    @Test
    void getStringInVcs() {
        String expected = "FOLDING BIKE West Bike; 14; 7; 14700; FALSE; black; 1289";
        String actual = foldingBikeMapper.getStringInVcs(new FoldingBike("West Bike",
                14, 7, 14700,
                false, "black", 1289));
        assertEquals(expected, actual);
    }
}
