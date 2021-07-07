package com.grippingstories.visionapp;

public class user {
    public Long pno;
    String user_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Long getPno() {
        return pno;
    }

    public void setPno(Long pno) {
        this.pno = pno;
    }

    @Override
    public String toString() {
        return "user{" +
                "pno=" + pno +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}

