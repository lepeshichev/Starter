package SpringContextOne.BeansPartTwo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "SpringContextOne.BeansPartTwo")
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        Human h = context.getBean(Human.class);
        System.out.println(h.getName());
        System.out.println(h.getParrotOne());
        h.getParrotTwo().setName("Second");
        System.out.println(h.getParrotTwo());
        System.out.println(h.getCat());
        System.out.println(h.getDog());
    }
}
