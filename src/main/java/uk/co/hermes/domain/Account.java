package uk.co.hermes.domain;

public class Account implements Identifiable {
    private final int id;
    private final String email;
    private final String password;

    public Account(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
