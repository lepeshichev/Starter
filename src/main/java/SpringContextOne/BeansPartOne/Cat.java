package SpringContextOne.BeansPartOne;

public class Cat {
    private String name = "Cat";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cat : " + name;
    }
}
