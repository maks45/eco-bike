package maksym.durov.mappers;

import maksym.durov.model.Speedelec;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SpeedelecMapperTest {
    private final SpeedelecMapper speedelecMapper = new SpeedelecMapper();

    @Test
    void getObject() {
        Speedelec expected = new Speedelec("Dualtron", 60, 20500,
                false, 15600, "golden", 869);
        Speedelec actual = speedelecMapper.getObject(
                "SPEEDELEC Dualtron; 60; 20500; FALSE; 15600; golden; 869");
        assertEquals(expected, actual);
    }

    @Test
    void getStringInVcs() {
        String expected = "SPEEDELEC Dualtron; 60; 20500; FALSE; 15600; golden; 869";
        String actual = speedelecMapper.getStringInVcs(new Speedelec("Dualtron", 60, 20500,
                false, 15600, "golden", 869));
    }
}