package maksym.durov.controller;

import maksym.durov.dao.BikeDao;
import maksym.durov.service.FileService;
import org.springframework.stereotype.Component;

@Component
public class GetFileController {
    private final CliController cliController;
    private final FileService fileService;

    public GetFileController(CliController cliController,
                             FileService fileService,
                             BikeDao bikeDao) {
        this.cliController = cliController;
        this.fileService = fileService;
    }

    public String getFileNameFromUser() {
        String input = cliController.getNotEmptyString("file name");
        if (fileService.isFileNameValid(input)) {
            return input;
        }
        System.out.println("wrong file name please try again");
        return getFileNameFromUser();
    }
}
