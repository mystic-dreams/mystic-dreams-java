package main.agents;

public class Character {
    String name;

    public Character(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " {\n" +
                "  job: \n" +
                "  stats: {\n" +
                "    str: \n" +
                "    dex: \n" +
                "    luk: \n" +
                "  }\n" +
                "}";
    }
}
