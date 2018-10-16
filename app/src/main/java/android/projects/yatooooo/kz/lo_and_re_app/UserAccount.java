package android.projects.yatooooo.kz.lo_and_re_app;

import java.util.Date;

public class UserAccount {
    private String fullname;
    private String email;
    private String username;
    private String password;
    private Date created;
    private Date updated;

    public UserAccount() {
    }

    public UserAccount(String fullname, String email, String username, String password, Date created, Date updated) {
        this.fullname = fullname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.created = created;
        this.updated = updated;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
