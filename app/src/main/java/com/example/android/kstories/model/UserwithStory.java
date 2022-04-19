package com.example.android.kstories.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserwithStory {

    @Embedded
    public User user;
    @Relation(
            parentColumn = "userId",
            entityColumn = "userStoryId"
    )
    public List<Story> story;

}
