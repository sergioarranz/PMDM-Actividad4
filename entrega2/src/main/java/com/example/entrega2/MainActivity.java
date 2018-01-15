package com.example.entrega2;


import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.entrega2.firebase.FirebaseAdmin;
import com.example.entrega2.firebase.FirebaseAdminListener;
import com.example.mylib.fragment.LoginFragment;
import com.example.mylib.fragment.RegisterFragment;

public class MainActivity extends AppCompatActivity{
    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;
    private MainActivityEvents event;
    private FirebaseAdmin firebaseAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Para poder llamar al findFragmentById que es el equivalente del findViewById hay que usar el método getSupportFragmentManager
        this.firebaseAdmin = new FirebaseAdmin();
        /*
        this.loginFragment = (LoginFragment)getSupportFragmentManager().findFragmentById(R.id.loginFragment);
        this.registerFragment=(RegisterFragment)getSupportFragmentManager().findFragmentById(R.id.registerFragment);
        */
        //Vamos a cambiar la forma de crear los fragments para añadirlos de forma dinámia y poder eliminar o añadir según queramos a un linearLayout
        this.loginFragment = new LoginFragment();
        this.registerFragment = new RegisterFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //Con la operación add especificamos el contenedor donde queremos meter el fragment, el fragment que queremos meter y un identificador para es fragment
        transaction.add(R.id.loginLayout, this.getLoginFragment(), "loginFragment");
        transaction.add(R.id.registerLayout, this.getRegisterFragment(), "registerFragment");


        event = new MainActivityEvents(this);
        firebaseAdmin.setFirebaseAdminListener(event);
        this.loginFragment.setLoginFragmentListener(event);
        this.registerFragment.setRegisterFragmentListener(event);
        transaction.hide(this.getRegisterFragment());
        transaction.show(this.getLoginFragment());
        transaction.commit(); // comiteamos
/*
        android.support.v4.app.FragmentTransaction transition = this.getSupportFragmentManager().beginTransaction();
        transition.hide(this.getRegisterFragment());
        transition.commit();
        */
    }


    public LoginFragment getLoginFragment() {
        return loginFragment;
    }

    public void setLoginFragment(LoginFragment loginFragment) {
        this.loginFragment = loginFragment;
    }

    public RegisterFragment getRegisterFragment() {
        return registerFragment;
    }

    public void setRegisterFragment(RegisterFragment registerFragment) {
        this.registerFragment = registerFragment;
    }

    public MainActivityEvents getEvent() {
        return event;
    }

    public void setEvent(MainActivityEvents event) {
        this.event = event;
    }

    public FirebaseAdmin getFirebaseAdmin() {
        return firebaseAdmin;
    }

    public void setFirebaseAdmin(FirebaseAdmin firebaseAdmin) {
        this.firebaseAdmin = firebaseAdmin;
    }
}
