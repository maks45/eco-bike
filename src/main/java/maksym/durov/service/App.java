package maksym.durov.service;

import java.util.Scanner;
import maksym.durov.controller.IndexController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class App {
    private final Scanner scanner;
    private final IndexController indexController;

    @Autowired
    public App(Scanner scanner, IndexController indexController) {
        this.scanner = scanner;
        this.indexController = indexController;
    }

    public void run() {
        indexController.run();
    }
}
