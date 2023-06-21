package SpringContextTwo.Main;

import SpringContextTwo.Config.ProjectConfig;
import SpringContextTwo.Model.Payment;
import SpringContextTwo.Services.Application;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        Application application = context.getBean(Application.class);
        application.SendMessage(new Payment("Alex","Mike", BigDecimal.valueOf(200)));
    }

}
