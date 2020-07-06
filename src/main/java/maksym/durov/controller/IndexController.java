package maksym.durov.controller;

import maksym.durov.model.EBike;
import maksym.durov.model.FoldingBike;
import maksym.durov.model.Speedelec;
import maksym.durov.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IndexController {
    private static final String MAIN_MENU = "Please make your choice:\n"
            + "1 - Show the entire EcoBike catalog\n"
            + "2 – Add a new folding bike\n"
            + "3 – Add a new speedelec\n"
            + "4 – Add a new e-bike\n"
            + "5 – Find the first item of a particular brand\n"
            + "6 – Write to file\n"
            + "7 – Stop the program\n";
    private String fileName;
    private final CreateNewBikeController createNewBikeController;
    private final SearchBikeController searchBikeController;
    private final ShowCatalogController showCatalogController;
    private final CliController cliController;
    private final GetFileController getFileController;
    private final BikeService bikeService;

    @Autowired
    public IndexController(CreateNewBikeController createNewBikeController,
                           ShowCatalogController showCatalogController,
                           SearchBikeController searchBikeController,
                           CliController cliController,
                           GetFileController getFileController,
                           BikeService bikeService) {
        this.createNewBikeController = createNewBikeController;
        this.showCatalogController = showCatalogController;
        this.searchBikeController = searchBikeController;
        this.cliController = cliController;
        this.getFileController = getFileController;
        this.bikeService = bikeService;
    }

    public void run() {
        fileName = getFileController.getFileNameFromUser();
        bikeService.getAllBikes(fileName);
        showMenu();
    }

    private void showMenu() {
        cliController.showMessage(MAIN_MENU);
        String input = cliController.getInput();
        int command = 0;
        if (input.matches("[0-9]+")) {
            command = Integer.parseInt(input);
        }
        switch (command) {
            case 1:
                showCatalogController.showCatalog();
                break;
            case 2:
                bikeService.addBike(createNewBikeController.createNewBike(FoldingBike.PREFIX));
                break;
            case 3:
                bikeService.addBike(createNewBikeController.createNewBike(Speedelec.PREFIX));
                break;
            case 4:
                bikeService.addBike(createNewBikeController.createNewBike(EBike.PREFIX));
                break;
            case 5:
                searchBikeController.search();
                break;
            case 6:
                if (bikeService.isNotSavedData()) {
                    bikeService.saveAllBikes();
                    cliController.showMessage("data was saved to file: " + fileName);
                } else {
                    cliController.showMessage("No data added, nothing to save.");
                }
                break;
            case 7:
                if (bikeService.isNotSavedData()) {
                    if (cliController.getBooleanFromInput(
                            "There are not saved data, all changes "
                                    + "will be lost, you sure (y/n)?")) {
                        return;
                    } else {
                        showMenu();
                    }
                }
                return;
            default:
                System.out.println("wrong command");
        }
        showMenu();
    }
}
