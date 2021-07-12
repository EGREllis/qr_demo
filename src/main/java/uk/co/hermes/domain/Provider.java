package uk.co.hermes.domain;

public class Provider implements Identifiable {
    private final int id;
    private final String name;

    public Provider(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%1$s(%2$d)", name, id);
    }
}
