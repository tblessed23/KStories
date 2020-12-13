package com.example.android.kstories.model;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {Story.class, Favorites.class}, version = 2, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "storieslist";
    private static AppDatabase sInstance;

    /**
     * Migrate from:
     * version 1 - using the SQLiteDatabase API
     * to
     * version 2 - using Room
     */
    @VisibleForTesting
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Room uses an own database hash to uniquely identify the database
            // Since version 1 does not use Room, it doesn't have the database hash associated.
            // By implementing a Migration class, we're telling Room that it should use the data
            // from version 1 to version 2.
            // If no migration is provided, then the tables will be dropped and recreated.
            // Since we didn't alter the table, there's nothing else to do here.

            //database.execSQL("ALTER TABLE 'favorites' ADD COLUMN 'id' INTEGER");
            //database.execSQL("INSERT INTO `favorites` ADD COLUMN `id` INTEGER, " + "`name` TEXT, PRIMARY KEY(`id`))");
            database.execSQL("INSERT INTO 'favorites' (id, titleFavorites) " + "SELECT id, titleFavorites FROM Favorites");
            //database.execSQL("CREATE TABLE `Favorites` (`id` INTEGER, "+ "`titleFavorites` TEXT, PRIMARY KEY(`id`))");



        }
    };
//    @VisibleForTesting
//    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            //database.execSQL("INSERT INTO 'profileu' (userId, firstname, lastname, city, state, country, phone, email, displayname, updatedAt) " + "SELECT userId, firstname, lastname, city, state, country, phone, email, displayname, updatedAt FROM Profileu");
//            database.execSQL("INSERT INTO 'favorites' (id, titleFavorites) " + "SELECT id, titleFavorites FROM Favorites");
//        }
//    };


    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {


                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        .addMigrations(MIGRATION_1_2)
                        .allowMainThreadQueries()
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }



    public abstract StoryDao storyDao();
    public abstract FavoritesDao favoritesDao();
    //public abstract ProfileuDao profileuDao();
}
