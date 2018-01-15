package com.example.mylib.fragment;

import android.view.View;


/**
 * Created by tay on 21/11/17.
 */

public class RegisterFragmentEvents implements View.OnClickListener{


    private RegisterFragment registerFragment;

    public RegisterFragmentEvents(RegisterFragment registerFragment) {
        this.registerFragment = registerFragment;
    }

    @Override
    public void onClick(View view) {
        if(this.registerFragment.getRegisterFragmentListener()!=null){
            this.registerFragment.getRegisterFragmentListener().OnClickRegistered();
        }

    }

    public RegisterFragment getRegisterFragment() {
        return registerFragment;
    }

    public void setRegisterFragment(RegisterFragment registerFragment) {
        this.registerFragment = registerFragment;
    }


}
