package com.example.android.kstories.model;

import android.os.Parcel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

//@Entity(tableName = "profileu")
//public class Profileu {
//
//    @PrimaryKey(autoGenerate = true)
//    private int userId;
//    private String firstname = null;
//    private String lastname;
//    private String city;
//    private String state;
//    private String country;
//    private String phone;
//    private String email;
//    private String displayname;
//    private String displayemail;
//    @ColumnInfo(name = "updated_at")
//    private Date updatedAt;
//
//
//    /**
//     * No args constructor for use in serialization
//     *
//     * @param profileu
//     */
//    public Profileu(String profileu) {
//    }
//
//    //Regular Constructor
//    @Ignore
//    public Profileu(String firstname, String lastname, String city, String state, String country, String phone, String email, String displayname, String displayemail, Date updatedAt) {
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.city= city;
//        this.state= state;
//        this.country = country;
//        this.phone = phone;
//        this.email = email;
//        this.displayname = displayname;
//        this.displayemail = displayemail;
//        this.updatedAt = updatedAt;
//    }
//
//    //Id Constructor
//
//    public Profileu(int userId, String firstname, String lastname, String city, String state, String country, String phone, String email, String displayname, String displayemail, Date updatedAt) {
//        this.userId = userId;
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.city = city;
//        this.state = state;
//        this.country = country;
//        this.phone = phone;
//        this.email = email;
//        this.displayname = displayname;
//
//        this.updatedAt = updatedAt;
//    }
//
//
//
//
//    public Profileu() {
//
//    }
//
//
//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
//
//    public String getFirstname() {
//        return firstname;
//    }
//
//    public void setFirstname(String firstname) {
//        this.firstname= firstname;
//    }
//
//    public String getLastname() {
//        return lastname;
//    }
//
//    public void setLastname(String lastname) {
//        this.lastname = lastname;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getState() {
//        return state;
//    }
//
//    public void setState(String state) {
//        this.state = state;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone( String phone) {
//        this.phone = phone;
//    }
//
//    public String getEmail(){
//        return email;
//    }
//
//    public void setEmail(String email){
//        this.email = email;
//    }
//
//    public String getDisplayname() {
//        return displayname;
//    }
//
//    public void setDisplayname(String displayname) {
//        this.displayname = displayname;
//    }
//
//    public Date getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(Date updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//
//}
