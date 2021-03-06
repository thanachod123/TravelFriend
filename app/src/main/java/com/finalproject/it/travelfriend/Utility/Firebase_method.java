package com.finalproject.it.travelfriend.Utility;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.finalproject.it.travelfriend.Model.UserData;
import com.finalproject.it.travelfriend.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Firebase_method {

    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;
    DatabaseReference mReference;
    Context mContext;
    String userID;

    public Firebase_method(Context context){
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference();
        mContext = context;
    }
    public void register_new_email(String strEmail,String strPassword){
        mAuth.createUserWithEmailAndPassword(strEmail,strPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                            Toast.makeText(mContext,"Registration Failed...",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            userID = mAuth.getCurrentUser().getUid();
                        }
                    }
                });
    }

    public void send_new_user_data(String email,String name,String phone,String password,String gender,String profile_image){

        UserData userData = new UserData(email,name,phone,password,gender,profile_image);
        mReference.child("Users").child(userID).setValue(userData);
    }


}
