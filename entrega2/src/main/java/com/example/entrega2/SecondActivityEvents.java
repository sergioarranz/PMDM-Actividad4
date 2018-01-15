package com.example.entrega2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.entrega2.adapter.ListAdapter;
import com.example.entrega2.adapter.ListAdapterListener;
import com.example.entrega2.adapter.MyViewHolder;
import com.example.entrega2.entity.Coche;
import com.example.entrega2.firebase.FirebaseAdminListener;
import com.example.mylib.fragment.ListFragment;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;

/**
 * Created by tay on 25/11/17.
 */

public class SecondActivityEvents implements View.OnClickListener, FirebaseAdminListener,ListAdapterListener {

    private SecondActivity secondActivity;

    public SecondActivityEvents(SecondActivity secondActivity) {
        this.secondActivity = secondActivity;
    }

    public SecondActivity getSecondActivity() {
        return secondActivity;
    }

    public void setSecondActivity(SecondActivity secondActivity) {
        this.secondActivity = secondActivity;
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnLogOut) {
            System.out.println(DataHolder.MyDataHolder.getFirebaseAdmin().getmAuth());
            DataHolder.MyDataHolder.getFirebaseAdmin().logOut();

        }
    }

    @Override
    public void loginIsOk(boolean ok) {

    }

    @Override
    public void registerOk(boolean ok) {

    }

    @Override
    public void signOutOk(boolean ok) {
        System.out.println("eeebuehhhKitipasa: " + ok);
        if (ok) {
            Intent intent = new Intent(secondActivity, MainActivity.class);
            secondActivity.startActivity(intent);
            secondActivity.finish();
        }
    }

    @Override
    public void downloadBranch(String branch, DataSnapshot dataSnapshot) {
        System.out.println(dataSnapshot);
        System.out.println("la rama es : " + branch);
        /*
        Por aquí pasaran todas las ramas y snapshots que obseervemos. Para elegir entre una rama u otra,
        basta con usar un if/else
         */
        if(branch.equals("Coches")){
            System.out.println("ldentro : " + branch);
            //Tenemos que usar un GenericTypeIndicator dado que firebase devuelve los datos utlizando esta clase abstracta
            GenericTypeIndicator<ArrayList<Coche>> indicator = new GenericTypeIndicator<ArrayList<Coche>>(){};
            ArrayList<Coche> arrCoches = dataSnapshot.getValue(indicator);//desde el value podemos castearlo al tipo que queramos, en este caso lo casteamos al genericTypeIndicator
           ListAdapter listAdapter = new ListAdapter(arrCoches,this.getSecondActivity());
            this.secondActivity.getListFragment().getMyLista().setAdapter(listAdapter);
            listAdapter.setListAdapterListener(this);

        }

    }

    @Override
    public void listAdapterCellClicked(MyViewHolder cell) {
        System.out.println("estoy pinchando la celda: " + cell.getAdapterPosition());
       /*
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.secondActivity.getWindow().getDecorView().getWidth() , 1000);
        layoutParams.width=this.secondActivity.getWindow().getDecorView().getWidth();
        layoutParams.height=this.secondActivity.getWindow().getDecorView().getHeight();
        ImageView imageView = cell.getImageViewCoche();
        imageView.setLayoutParams(layoutParams);
        */
    }
}
