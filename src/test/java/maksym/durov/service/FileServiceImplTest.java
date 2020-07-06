package maksym.durov.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class FileServiceImplTest {
    private FileService fileService = new FileServiceImpl();

    @Test
    void testIsFileNameValid_incorrectFileName() {
        assertFalse(fileService.isFileNameValid("nonExistingFileName"));
    }

    @Test
    void testIsFileNameValid_correctFileName() {
        assertTrue(fileService.isFileNameValid(".gitignore"));
    }
}