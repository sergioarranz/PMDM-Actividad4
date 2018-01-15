package com.example.entrega2;

import com.example.entrega2.firebase.FirebaseAdmin;
import com.example.mylib.fragment.ListFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by tay on 25/11/17.
 */

public class DataHolder {

    public static class MyDataHolder {
        public static FirebaseAdmin firebaseAdmin;
        public static ListFragment listFragment;

        public static FirebaseAdmin getFirebaseAdmin() {
            return firebaseAdmin;
        }

        public static void setFirebaseAdmin(FirebaseAdmin firebaseAdmin) {
            MyDataHolder.firebaseAdmin = firebaseAdmin;
        }
     /*Para pdoer reciclar el fragmenList podemos llamar ald ataholder desde otro activity y llamarlo,
        si no existe instancia porque previamente no lo guardamos o nunca se ha creado se hará una nueva
        instancia. En este momento no hago uso de ello pero lo dejo para tenerlo para futuras entregas en las
        que si pueda hacer uso de ello.
      */
        public static ListFragment getListFragment() {
            if(listFragment==null){
                listFragment= new ListFragment();
            }
            return listFragment;
        }

        public static void setListFragment(ListFragment listFragment) {
            MyDataHolder.listFragment = listFragment;
        }
    }


}