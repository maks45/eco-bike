package maksym.durov.controller;

import java.util.Scanner;
import maksym.durov.model.EBike;
import maksym.durov.model.FoldingBike;
import maksym.durov.model.Speedelec;
import maksym.durov.model.abstractbike.Bike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateNewBikeController {
    private final Scanner scanner;
    private final CliController cliController;

    @Autowired
    public CreateNewBikeController(Scanner scanner, CliController cliController) {
        this.scanner = scanner;
        this.cliController = cliController;
    }

    public Bike createNewBike(String prefix) {
        if (EBike.PREFIX.equals(prefix)) {
            return getEbike();
        }
        if (Speedelec.PREFIX.equals(prefix)) {
            return getSpeedelec();
        }
        if (FoldingBike.PREFIX.equals(prefix)) {
            return getEbike();
        }
        throw new IllegalArgumentException("unsupported bike type");
    }

    private Bike getSpeedelec() {
        return new Speedelec(
                cliController.getNotEmptyString("brand"),
                cliController.getPositiveInt("maximum speed"),
                cliController.getPositiveInt("weight"),
                cliController.getBoolean("head/rail light ability"),
                cliController.getPositiveInt("battery capacity"),
                cliController.getNotEmptyString("color"),
                cliController.getPositiveInt("price"));
    }

    private Bike getEbike() {
        return new EBike(
                cliController.getNotEmptyString("brand"),
                cliController.getPositiveInt("maximum speed"),
                cliController.getPositiveInt("weight"),
                cliController.getBoolean("head/tail light ability"),
                cliController.getPositiveInt("battery capacity"),
                cliController.getNotEmptyString("color"),
                cliController.getPositiveInt("price"));
    }

    private Bike getFoldingBike() {
        return new FoldingBike(
                cliController.getNotEmptyString("brand"),
                cliController.getPositiveInt("wheels size"),
                cliController.getPositiveInt("gears number"),
                cliController.getPositiveInt("weight"),
                cliController.getBoolean("head/tail light ability"),
                cliController.getNotEmptyString("color"),
                cliController.getPositiveInt("price"));
    }
}
