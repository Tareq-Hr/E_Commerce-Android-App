package com.example.e_commerce_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Register extends AppCompatActivity {
    TextInputLayout nom1,prenom1,email1,password1,phone1,adress1;
    Button ins1;
    FirebaseAuth fAuth;
    RadioGroup sexe;
    FirebaseFirestore fStore;
    String userID;

    //private static final String TAG = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_register);

        nom1=findViewById(R.id.nom);
        prenom1=findViewById(R.id.prenom);
        email1=findViewById(R.id.email);
        password1=findViewById(R.id.password);
        phone1=findViewById(R.id.tele);
        adress1=findViewById(R.id.adresse);
        ins1=findViewById(R.id.ins);
        fAuth=FirebaseAuth.getInstance();
        TextView text = (TextView) findViewById(R.id.text);
        //text.setShadowLayer(1, 0, 0, Color.gray);

        fStore= FirebaseFirestore.getInstance();

        if(fAuth.getCurrentUser()!= null){
            startActivity(new Intent(getApplicationContext(),accueil.class));
            finish();
        }




        ins1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nom= nom1.getEditText().getText().toString().trim();
                final String prenom=prenom1.getEditText().getText().toString().trim();
                final String email= email1.getEditText().getText().toString().trim();
                String password=password1.getEditText().getText().toString().trim();
               final String phone=phone1.getEditText().getText().toString().trim();
               final String adresse=adress1.getEditText().getText().toString().trim();
                if (TextUtils.isEmpty(nom)) {
                    nom1.setError("nom is required");
                    return;
                }
                if (TextUtils.isEmpty(prenom)) {
                    prenom1.setError("prenom is required");
                    return;
                }
                if(TextUtils.isEmpty((email))){
                    email1.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    password1.setError("password is required");
                    return;
                }
                if(password.length()<8){
                    password1.setError("password Must be >= 8 characters");
                }
                if (TextUtils.isEmpty(phone)) {
                    phone1.setError("phone is required");
                    return;
                }
                if(phone.isEmpty() || phone.length() < 10){
                    phone1.setError("Enter a valid mobile");
                    phone1.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(adresse)) {
                    adress1.setError("adresse is required");
                    return;
                }

                //register the user in firebase
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            //send verification link
                            FirebaseUser fuser=fAuth.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                   Toast.makeText(Register.this,"verification Email Has been sent.", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("TAG", "onFailure: Email not sent"+e.getMessage());
                                }
                            });
                            Toast.makeText(Register.this,"User created",Toast.LENGTH_SHORT).show();
                            userID= fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference=fStore.collection("users").document(userID);

                            Map<String, Object> user= new HashMap<>();

                            user.put("nom",nom);
                            user.put("prenom",prenom);
                            user.put("phone",phone);
                            user.put("email",email);
                            user.put("Adresse",adresse);


                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                   Log.d("TAG","onSuccess: user profile is  created for"+userID);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("TAG","onFailure: "+e.toString());

                                }
                            });

                            startActivity(new Intent(getApplicationContext(),accueil.class));
                        }else{
                            Toast.makeText(Register.this,"Error!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });




    }

}
