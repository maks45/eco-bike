package maksym.durov.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.stereotype.Component;

@Component
public class FileServiceImpl implements FileService {

    @Override
    public boolean isFileNameValid(String filename) {
        return Files.exists(Paths.get(filename));
    }
}
