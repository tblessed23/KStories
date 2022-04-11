package com.example.android.kstories.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName  = "story", indices = {@Index(value = {"userId"},
        unique = true)})
public class Story  {

    @PrimaryKey(autoGenerate =true)
    public int primaryId;
    @NonNull
    public String userId;
    @NonNull
    private String audiotitle;
    private String storycity;
    private String storycounty;
    @NonNull
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
    public Story(@NonNull String userId, @NonNull String audiotitle, String storycity, String storycounty,  @NonNull String storystate, String ancestorfirstname, String ancestorlastname, String familyname, String audioUrl, Date updatedAt) {
       this.userId = userId;
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

    public Story(int primaryId, String userId, @NonNull String audiotitle, String storycity, String storycounty, @NonNull String storystate, String ancestorfirstname, String ancestorlastname, String familyname, String audioUrl, Date updatedAt) {
        this.primaryId = primaryId;
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

    /***primaryId***/
    public int getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(int primaryId) {
        this.primaryId = primaryId;
    }

/***UserId**/
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

 /**Audio Title**/

    public @NonNull String getAudiotitle() {
        return audiotitle;
    }

    public void setAudiotitle(@NonNull String audiotitle) {
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
    public @NonNull String getStorystate() {
        return storystate;
    }

    public void setStorystate(@NonNull String storystate) {
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

    public class StoryMinimal {

    }
}
