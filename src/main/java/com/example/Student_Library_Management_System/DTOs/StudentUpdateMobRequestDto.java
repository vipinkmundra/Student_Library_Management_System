package com.example.Student_Library_Management_System.DTOs;

public class StudentUpdateMobRequestDto {
    private int id;
    private String mobNo;

    //DTos depent on API called . we can customize it based on requirement

    public StudentUpdateMobRequestDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }
}
