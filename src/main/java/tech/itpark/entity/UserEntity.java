package tech.itpark.entity;

import java.util.Set;
import java.util.UUID;


public class UserEntity {
    private UUID id;
    private String login;
    private String password;
    private String name;
    private Set<String> roles;
    private boolean removed;
    private long created;
    private String secret;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getSecret() {
        return secret;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public UserEntity() {
    }

    public UserEntity(UUID id, String secret, String login, String password, String name, Set<String> roles, boolean removed, long created) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.roles = roles;
        this.removed = removed;
        this.created = created;
        this.secret = secret;
    }
}
