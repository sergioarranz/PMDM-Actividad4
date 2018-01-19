package com.example.entrega2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;

/**
 * Created by tay on 25/11/17.
 */

public class SecondActivityEvents implements View.OnClickListener, FirebaseAdminListener,ListAdapterListener,OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    private SecondActivity secondActivity;
    GoogleMap mMap;
    public ArrayList<Coche> arrCoches;

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
            quitarViejosPines();
            //Tenemos que usar un GenericTypeIndicator dado que firebase devuelve los datos utlizando esta clase abstracta
            GenericTypeIndicator<ArrayList<Coche>> indicator = new GenericTypeIndicator<ArrayList<Coche>>(){};
            arrCoches = dataSnapshot.getValue(indicator);//desde el value podemos castearlo al tipo que queramos, en este caso lo casteamos al genericTypeIndicator
           ListAdapter listAdapter = new ListAdapter(arrCoches,this.getSecondActivity());
            this.secondActivity.getListFragment().getMyLista().setAdapter(listAdapter);
            listAdapter.setListAdapterListener(this);
            AgregarPinesCoches();

        }

    }

    public void quitarViejosPines() {
        if (arrCoches!=null) {
            for (int i = 0; i < arrCoches.size(); i++) {
                Coche cocheTemp = arrCoches.get(i);
                if (cocheTemp.getMarker() != null) {
                    cocheTemp.getMarker().remove();
                }
            }
        }
    }

    public void AgregarPinesCoches(){
    for (int i=0;i<arrCoches.size();i++){
        Coche cocheTemp = arrCoches.get(i);
        LatLng cochePos = new LatLng(cocheTemp.lat, cocheTemp.lon);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(cochePos);
        markerOptions.title(cocheTemp.modelo);
            if(mMap!=null){
                Marker marker = mMap.addMarker(markerOptions);
                marker.setTag(cocheTemp);
                cocheTemp.setMarker(marker);
                if (i==0)mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cochePos, 7));
            }
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);
        // Add a marker in Sydney and move the camera
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        DataHolder.MyDataHolder.getFirebaseAdmin().downloadDataAndObserveBranchChanges("Coches"); // llamo al métood de descarga con la rama que quiero que observe a partir de la raíz.

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Coche coche = (Coche)marker.getTag();
        Log.v("SecondActivity","PRESIONADO PIN: "+coche.modelo);

        secondActivity.detallesFragment.txtFabricado.setText(coche.Fabricado+"");
        secondActivity.detallesFragment.txtMarca.setText(coche.marca);
        secondActivity.detallesFragment.txtModelo.setText(coche.modelo);

        FragmentTransaction transaction = secondActivity.getSupportFragmentManager().beginTransaction();
        transaction.show(secondActivity.detallesFragment);
        transaction.commit(); // comiteamos

        return false;
    }
}
