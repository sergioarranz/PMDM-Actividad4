package com.example.mylib.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mylib.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {
    private EditText txtEmail, txtPass;
    private Button btnRegistrar;
    private RegisterFragmentEvents registerFragmentEvents;
    private RegisterFragmentListener registerFragmentListener;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRegisterFragment = inflater.inflate(R.layout.fragment_register, container, false);

        this.registerFragmentEvents = new RegisterFragmentEvents(this);
        this.txtEmail = viewRegisterFragment.findViewById(R.id.txtEmailRegistro);
        this.txtPass = viewRegisterFragment.findViewById(R.id.txtPassRegistro);
        this.btnRegistrar = viewRegisterFragment.findViewById(R.id.btnRegistrar);
        this.btnRegistrar.setOnClickListener(registerFragmentEvents);
        this.btnRegistrar.setText(R.string.btnRegistrar);


        return viewRegisterFragment;
    }

    public EditText getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(EditText txtEmail) {
        this.txtEmail = txtEmail;
    }


    public EditText getTxtPass() {
        return txtPass;
    }

    public void setTxtPass(EditText txtPass) {
        this.txtPass = txtPass;
    }


    public Button getBtnRegistrar() {
        return btnRegistrar;
    }

    public void setBtnRegistrar(Button btnRegistrar) {
        this.btnRegistrar = btnRegistrar;
    }

    public RegisterFragmentEvents getRegisterFragmentEvents() {
        return registerFragmentEvents;
    }

    public void setRegisterFragmentEvents(RegisterFragmentEvents registerFragmentEvents) {
        this.registerFragmentEvents = registerFragmentEvents;
    }

    public RegisterFragmentListener getRegisterFragmentListener() {
        return registerFragmentListener;
    }

    public void setRegisterFragmentListener(RegisterFragmentListener registerFragmentListener) {
        this.registerFragmentListener = registerFragmentListener;
    }

}
