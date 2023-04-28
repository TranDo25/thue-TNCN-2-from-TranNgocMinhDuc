package com.sqa.models.dtos;

public class SystemAdminDTO {
    private Integer id;
    private String hoten;
    private String username;
    private String pasword;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHoten() {
        return this.hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasword() {
        return this.pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }
}
