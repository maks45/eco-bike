package maksym.durov.controller;

import maksym.durov.model.EBike;
import maksym.durov.model.FoldingBike;
import maksym.durov.model.SearchBikeDto;
import maksym.durov.model.Speedelec;
import maksym.durov.service.BikeService;
import org.springframework.stereotype.Component;

@Component
public class SearchBikeController {
    private final BikeService bikeService;
    private final CliController cliController;

    public SearchBikeController(BikeService bikeService,
                                CliController cliController) {
        this.bikeService = bikeService;
        this.cliController = cliController;
    }

    public void search() {
        String input;
        while (true) {
            cliController.showMessage("Input 'q' to exit search");
            input = cliController.getNotEmptyString("Bike type");
            SearchBikeDto searchBikeDto = new SearchBikeDto();
            if (FoldingBike.PREFIX.toLowerCase().equals(input.toLowerCase())) {
                searchBikeDto.setPrefix(FoldingBike.PREFIX);
                buildFoldingBikeSearchDto(searchBikeDto);
            } else if (EBike.PREFIX.toLowerCase().equals(input.toLowerCase())) {
                searchBikeDto.setPrefix(EBike.PREFIX);
                buildElectricBikeSearchDto(searchBikeDto);
            } else if (Speedelec.PREFIX.toLowerCase().equals(input.toLowerCase())) {
                searchBikeDto.setPrefix(Speedelec.PREFIX);
                buildElectricBikeSearchDto(searchBikeDto);
            } else {
                if ("q".equalsIgnoreCase(input)) {
                    break;
                }
                cliController.showMessage("Unknown type of bike");
                continue;
            }
            cliController.showMessage("search results:");
            bikeService.findBike(searchBikeDto).forEach(System.out::println);
        }
    }

    private void buildFoldingBikeSearchDto(SearchBikeDto searchBikeDto) {
        cliController.showMessage("Please enter bike wheels size or press enter to skip");
        searchBikeDto.setWheelsSize(cliController.getParseInt(cliController.getInput()));
        cliController.showMessage("Please enter bike gears number or press enter to skip");
        searchBikeDto.setGearsNumber(cliController.getParseInt(cliController.getInput()));
        buildGeneralSearchBikeDto(searchBikeDto);
    }

    private void buildElectricBikeSearchDto(SearchBikeDto searchBikeDto) {
        cliController.showMessage("Please enter bike maximum speed or press enter to skip");
        searchBikeDto.setMaxSpeed(cliController.getParseInt(cliController.getInput()));
        cliController.showMessage("Please enter battery capacity or press enter to skip");
        searchBikeDto.setBatteryCapacity(cliController.getParseInt(cliController.getInput()));
        buildGeneralSearchBikeDto(searchBikeDto);
    }

    private void buildGeneralSearchBikeDto(SearchBikeDto searchBikeDto) {
        cliController.showMessage("Please enter bike brand or press enter to skip");
        searchBikeDto.setBrand(cliController.getStringOrNull(cliController.getInput()));
        cliController.showMessage("Please enter bike weight or press enter to skip");
        searchBikeDto.setWeight(cliController.getParseInt(cliController.getInput()));
        cliController.showMessage("Please enter bike front/rear lights ability (y /n)"
                + " or press enter to skip");
        searchBikeDto.setBackAndFrontLights(cliController.getParsedBool(cliController.getInput()));
        cliController.showMessage("Please enter bike colour or press enter to skip");
        searchBikeDto.setColor(cliController.getStringOrNull(cliController.getInput()));
        cliController.showMessage("Please enter bike price or press enter to skip");
        searchBikeDto.setPrice(cliController.getParseInt(cliController.getInput()));
    }
}
