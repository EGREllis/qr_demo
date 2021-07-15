package uk.co.hermes.domain;

public class Provider implements Identifiable {
    private final int id;
    private final String name;
    private final String prefix;

    public Provider(int id, String name, String prefix) {
        this.id = id;
        this.name = name;
        this.prefix = prefix;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() { return prefix; }

    @Override
    public String toString() {
        return String.format("%1$s(%2$d)", name, id);
    }
}
