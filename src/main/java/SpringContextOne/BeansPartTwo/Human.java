package SpringContextOne.BeansPartTwo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Human {
    private String name = "Human";
    private final Parrot ParrotOne;
    private final Parrot ParrotTwo;
    private final Cat cat;
    private final Dog dog;

    @Autowired
    public Human(Parrot ParrotOne, Parrot ParrotTwo, Cat cat, Dog dog) {
        this.ParrotOne = ParrotOne;
        this.ParrotTwo = ParrotTwo;
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
