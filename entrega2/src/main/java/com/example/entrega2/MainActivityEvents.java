package com.example.entrega2;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.example.entrega2.firebase.FirebaseAdminListener;
import com.example.mylib.fragment.LoginFragmentListener;
import com.example.mylib.fragment.RegisterFragmentListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;

/**
 * Created by tay on 21/11/17.
 */

public class MainActivityEvents implements LoginFragmentListener, RegisterFragmentListener, FirebaseAdminListener {


    private MainActivity mainActivity;

    public MainActivityEvents(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void OnRegisteredClicked() {
        android.support.v4.app.FragmentTransaction transition = mainActivity.getSupportFragmentManager().beginTransaction();
        transition.hide(this.mainActivity.getLoginFragment());
        transition.show(this.mainActivity.getRegisterFragment());
        transition.commit();

    }

    @Override
    public void OnLoginClicked() {
        this.mainActivity.getFirebaseAdmin().signInWithEmailAndPassword(this.mainActivity.getLoginFragment().getTxtEmail().getText().toString(), this.mainActivity.getLoginFragment().getTxtPass().getText().toString());

    }

    @Override
    public void OnClickRegistered() {

        this.mainActivity.getFirebaseAdmin().createUserWithEmailAndPassword(this.mainActivity.getRegisterFragment().getTxtEmail().getText().toString(), this.mainActivity.getRegisterFragment().getTxtPass().getText().toString());

    }


    @Override
    public void loginIsOk(boolean ok) {
        System.out.println("llega: " + ok);
        if(ok){
            Intent intent = new Intent(mainActivity, SecondActivity.class);
            mainActivity.startActivity(intent);
            mainActivity.finish();
        }

    }

    @Override
    public void registerOk(boolean ok) {
    if(ok){
        android.support.v4.app.FragmentTransaction transition = mainActivity.getSupportFragmentManager().beginTransaction();
        transition.hide(mainActivity.getRegisterFragment());
        transition.show(mainActivity.getLoginFragment());
        transition.commit();
    }

    }

    @Override
    public void signOutOk(boolean ok) {

    }

    @Override
    public void downloadBranch(String branch, DataSnapshot dataSnapshot) {

    }
}
