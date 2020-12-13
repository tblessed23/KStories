package com.example.android.kstories.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Story  {

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


    /**
     * No args constructor for use in serialization
     *
     * @param story
     */
    public Story(String story) {
    }

    //Regular Constructor
    @Ignore
    public Story(String audiotitle, String storycity, String storycounty,  String storystate, String ancestorfirstname, String ancestorlastname, String familyname, String audioUrl, Date updatedAt) {
        this.audiotitle = audiotitle;
        this.storycity = storycity;
        this.storycounty = storycounty;
        this.storystate= storystate;
        this.ancestorfirstname = ancestorfirstname;
        this.ancestorlastname = ancestorlastname;
        this.familyname = familyname;
        this.audioUrl = audioUrl;
        this.updatedAt = updatedAt;
    }

    //Id Constructor

    public Story(int userId, String audiotitle, String storycity, String storycounty, String storystate, String ancestorfirstname, String ancestorlastname, String familyname, String audioUrl, Date updatedAt) {
        this.userId = userId;
        this.audiotitle = audiotitle;
        this.storycity = storycity;
        this.storycounty = storycounty;
        this.storystate = storystate;
        this.ancestorfirstname = ancestorfirstname;
        this.ancestorlastname = ancestorlastname;
        this.familyname = familyname;
        this.audioUrl = audioUrl;
        this.updatedAt = updatedAt;
    }




    public Story() {

    }

/***UserId**/
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

 /**Audio Title**/

    public String getAudiotitle() {
        return audiotitle;
    }

    public void setAudiotitle(String audiotitle) {
        this.audiotitle= audiotitle;
    }

    /**Story City***/
    public String getStorycity() {
        return storycity;
    }

    public void setStorycity(String storycity) {
        this.storycity = storycity;
    }


    /***Story County***/
    public String getStorycounty() {
        return storycounty;
    }

    public void setStorycounty(String storycounty) {
        this.storycounty = storycounty;
    }

    /***Story State***/
    public String getStorystate() {
        return storystate;
    }

    public void setStorystate( String storystate) {
        this.storystate = storystate;
    }

    //**Ancestor Firstname***/

    public String getAncestorfirstname() {
        return ancestorfirstname;
    }

    public void setAncestorfirstname(String ancestorfirstname) {
        this.ancestorfirstname = ancestorfirstname;
    }


    /***Ancestor Lastname***/
    public String getAncestorlastname() {
        return ancestorlastname;
    }

    public void setAncestorlastname( String ancestorlastname) {
        this.ancestorlastname = ancestorlastname;
    }

    /***Family Name**/

    public String getFamilyname(){
        return familyname;
    }

    public void setFamilyname(String familyname){
        this.familyname = familyname;
    }

    /**Audio URL**/

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    /***DATE/Time***/

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static DiffUtil.ItemCallback<Story> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Story>() {

                // FoodObject details may have changed if reloaded
                // from the database, but ID is fixed.
                @Override
                public boolean areItemsTheSame(@NonNull Story oldItem, @NonNull Story newItem) {
                    return oldItem.getUserId() == newItem.getUserId();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull Story oldItem, @NonNull Story newItem) {
                    return oldItem.equals(newItem);
                }
            };

}
