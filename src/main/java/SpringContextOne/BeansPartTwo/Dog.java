package SpringContextOne.BeansPartTwo;

import org.springframework.stereotype.Component;

@Component
public class Dog {
    private String name = "Dog";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog : " + name;
    }
}
