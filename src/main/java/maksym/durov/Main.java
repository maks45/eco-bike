package maksym.durov;

import maksym.durov.config.AppConfig;
import maksym.durov.service.App;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        App app = context.getBean(App.class);
        app.run();
    }
}
