package SpringContextOne.BeansPartOne;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Human h = context.getBean(Human.class);
        System.out.println(h.getName());
        System.out.println(h.getParrotOne());
        h.getParrotTwo().setName("Second");
        System.out.println(h.getParrotTwo());
        System.out.println(h.getCat());
        System.out.println(h.getDog());
    }
}
