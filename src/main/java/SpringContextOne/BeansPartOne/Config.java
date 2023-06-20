package SpringContextOne.BeansPartOne;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "SpringContextOne.BeansPartOne")
public class Config {
    @Bean
    public Parrot parrotOne() {
        Parrot p1 = new Parrot();
        p1.setName("First");
        return p1;
    }

    @Bean
    public Parrot parrotTwo() {
        Parrot p2 = new Parrot();
        p2.setName("Second");
        return p2;
    }

    @Bean
    public Cat cat() {
        return new Cat();
    }

    @Bean
    public Dog dog() {
        return new Dog();
    }
}
