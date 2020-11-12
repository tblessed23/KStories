package com.example.android.kstories.user;

import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
//
//public class DownloadFirebaseUrl {
//    private FirebaseStorage mFirebaseStorage;
//    // Create a storage reference from our app
//    StorageReference storageRef = mFirebaseStorage.getReference().child("k_audio");
//
//    public void includesForDownloadFiles() throws IOException {
//        storageRef.child("k_audio").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                // Got the download URL for 'users/me/profile.png'
//                Toast.makeText(this, "sucess" + DownloadFirebaseUrl.this.getClass(), Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                // Handle any errors
//            }
//        });
//    }
//}
