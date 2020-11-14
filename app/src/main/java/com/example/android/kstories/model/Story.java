package com.example.android.kstories.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Story  {

//    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
//        public Story createFromParcel(Parcel in) {
//            return new Story(in);
//        }
//
//        public Story[] newArray(int size) {
//            return new Story[0];
//        }
//    };

    @PrimaryKey(autoGenerate = true)
    private int userId;
    private String audiotitle;
    private String storycity;
    private String storycounty;
    private String storystate;
    private String ancestorfirstname;
    private String ancestorlastname;
    private String familyname;
    @ColumnInfo(name = "updated_at")
    private String audioUrl;
    private Date updatedAt;


//    /**
//     * No args constructor for use in serialization
//     *
//     * @param story
//     */
//    public Story(String story) {
//    }

    //Regular Constructor
    @Ignore
    public Story(String audiotitle, String storycity, String storycounty, String storystate, String ancestorfirstname, String ancestorlastname, String familyname, Date updatedAt) {
        this.audiotitle = audiotitle;
        this.storycity = storycity;
        this.storycounty = storycounty;
        this.storystate= storystate;
        this.ancestorfirstname = ancestorfirstname;
        this.ancestorlastname = ancestorlastname;
        this.familyname = familyname;
        this.updatedAt = updatedAt;
    }

    //Id Constructor

    public Story(int userId, String audiotitle, String storycity, String storycounty, String storystate, String ancestorfirstname, String ancestorlastname, String familyname, Date updatedAt) {
        this.userId = userId;
        this.audiotitle = audiotitle;
        this.storycity = storycity;
        this.storycounty = storycounty;
        this.storystate = storystate;
        this.ancestorfirstname = ancestorfirstname;
        this.ancestorlastname = ancestorlastname;
        this.familyname = familyname;
        this.updatedAt = updatedAt;
    }


    public Story(String audiotitlew, String audioUrlw, String storystatew, Date updatedAtw){

        this.audiotitle = audiotitlew;
        this.audioUrl = audioUrlw;
        this.storystate = storystatew;
        this.updatedAt = updatedAtw;
    }

    public Story() {

    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAudiotitle() {
        return audiotitle;
    }

    public void setAudiotitle(String audiotitle) {
        this.audiotitle= audiotitle;
    }

    public String getStorycity() {
        return storycity;
    }

    public void setStorycity(String storycity) {
        this.storycity = storycity;
    }

    public String getStorycounty() {
        return storycounty;
    }

    public void setStorycounty(String storycounty) {
        this.storycounty = storycounty;
    }

    public String getStorystate() {
        return storystate;
    }

    public void setStorystate(String storystate) {
        this.storystate = storystate;
    }

    public String getAncestorfirstname() {
        return ancestorfirstname;
    }

    public void setAncestorfirstname(String ancestorfirstname) {
        this.ancestorfirstname = ancestorfirstname;
    }

    public String getAncestorlastname() {
        return ancestorlastname;
    }

    public void setAncestorlastname( String ancestorlastname) {
        this.ancestorlastname = ancestorlastname;
    }

    public String getFamilyname(){
        return familyname;
    }

    public void setFamilyname(String familyname){
        this.familyname = familyname;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }



    //Parceling constructor
//    public Story(Parcel in) {
//        this.userId = in.readInt();
//        this.audiotitle = in.readString();
//        this.storycity = in.readString();
//        this.storycounty = in.readString();
//        this.storystate= in.readString();
//        this.ancestorfirstname= in.readString();
//        this.ancestorlastname= in.readString();
//        this.familyname= in.readString();
//        this.updatedAt= new Date (in.readLong());
//    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(this.userId);
//        dest.writeString(this.audiotitle);
//        dest.writeString(this.storycity);
//        dest.writeString(this.storycounty);
//        dest.writeString(this.storystate);
//        dest.writeString(this.ancestorfirstname);
//        dest.writeString(this.ancestorlastname);
//        dest.writeString(this.familyname);
//        dest.writeLong(updatedAt.getTime());
//
//    }
}
