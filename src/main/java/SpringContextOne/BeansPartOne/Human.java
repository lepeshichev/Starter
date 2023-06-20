package SpringContextOne.BeansPartOne;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Human {
    private String name = "Human";

    private final Parrot ParrotOne;
    private final Parrot ParrotTwo;
    private final Cat cat;
    private final Dog dog;

    public Human(@Qualifier("parrotOne") Parrot Parrot1, @Qualifier("parrotTwo") Parrot parrot2, @Qualifier("cat") Cat cat,@Qualifier("dog") Dog dog) {
        this.ParrotOne = Parrot1;
        this.ParrotTwo = parrot2;
        this.cat = cat;
        this.dog = dog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Parrot getParrotOne() {
        return ParrotOne;
    }

    public Parrot getParrotTwo() {
        return ParrotTwo;
    }

    public Cat getCat() {
        return cat;
    }

    public Dog getDog() {
        return dog;
    }
}