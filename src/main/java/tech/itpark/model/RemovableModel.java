package tech.itpark.model;

public class RemovableModel {
    private final String login;
    private final String password;

    public RemovableModel(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
