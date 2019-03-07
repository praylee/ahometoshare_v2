package app.withyou.ahometoshare.model;

import java.util.Date;

public class LoginRecord {
    private Integer id;

    private String username;

    private Integer usertype;

    private Date time;

    public LoginRecord(Integer id, String username, Integer usertype, Date time) {
        this.id = id;
        this.username = username;
        this.usertype = usertype;
        this.time = time;
    }

    public LoginRecord() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}