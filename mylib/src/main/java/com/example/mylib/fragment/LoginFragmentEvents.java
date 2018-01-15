package com.example.mylib.fragment;

import android.view.View;

import com.example.mylib.R;

/**
 * Created by tay on 21/11/17.
 */

public class LoginFragmentEvents implements View.OnClickListener {
    private LoginFragment loginFragment;

    public LoginFragmentEvents(LoginFragment loginFragment) {
        //Mediante el constructor seteamos el LoginFragment para poder usarlo para llamar al listener
        this.loginFragment = loginFragment;
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnRegister){
            if(this.loginFragment.getLoginFragmentListener()!=null)
                System.out.println("click registro");
                this.loginFragment.getLoginFragmentListener().OnRegisteredClicked();
        }
        else if(view.getId() == R.id.btnLogin){
            if(this.loginFragment.getLoginFragmentListener()!=null)
                this.loginFragment.getLoginFragmentListener().OnLoginClicked();
        }
    }


}

