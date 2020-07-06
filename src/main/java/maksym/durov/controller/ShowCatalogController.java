package maksym.durov.controller;

import java.util.List;
import java.util.Objects;
import maksym.durov.model.abstractbike.Bike;
import maksym.durov.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowCatalogController {
    private final BikeService bikeService;
    private final CliController cliController;

    @Autowired
    public ShowCatalogController(BikeService bikeService, CliController cliController) {
        this.bikeService = bikeService;
        this.cliController = cliController;
    }

    public void showCatalog() {
        String input = "";
        int currentPage = 1;
        while (!Objects.equals(input, "q")) {
            cliController.showMessage("Catalog of all bikes, page " + (currentPage));
            List<Bike> bikes = bikeService.getPage(currentPage);
            if (bikes.isEmpty()) {
                cliController.showMessage("No more results found");
                return;
            }
            bikes.forEach(System.out::println);
            cliController.showMessage("Press enter to next page or 'q' to exit catalog");
            currentPage++;
            input = cliController.getInput();
        }
    }
}
