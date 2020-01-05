package com.home4u.hotelmanagement.models;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

enum UserType {
    CUSTOMER,
    MANAGER
}

public class AppUser {
    private long id;
    private String username;
    private String password;
    private String email;
    private Date dob;
    @Enumerated(EnumType.STRING)
    private UserType usertype;
    private boolean active;
    private Date joindate;
    private Date updateddate;
    private boolean blocked;

    public AppUser() {
    }

    public AppUser(long id, String username, String password, String email, Date dob, UserType usertype, boolean active, Date joindate, Date updateddate, boolean blocked) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dob = dob;
        this.usertype = usertype;
        this.active = active;
        this.joindate = joindate;
        this.updateddate = updateddate;
        this.blocked = blocked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public UserType getUsertype() {
        return usertype;
    }

    public void setUsertype(UserType usertype) {
        this.usertype = usertype;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getJoindate() {
        return joindate;
    }

    public void setJoindate(Date joindate) {
        this.joindate = joindate;
    }

    public Date getUpdateddate() {
        return updateddate;
    }

    public void setUpdateddate(Date updateddate) {
        this.updateddate = updateddate;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", usertype=" + usertype +
                ", active=" + active +
                ", joindate=" + joindate +
                ", updateddate=" + updateddate +
                ", blocked=" + blocked +
                '}';
    }
}
