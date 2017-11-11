package models;

public class Player {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public Player() {
        name = "Guest";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
