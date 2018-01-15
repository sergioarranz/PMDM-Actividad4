package com.example.entrega2.firebase;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.DatePicker;


import com.example.entrega2.DataHolder;
import com.example.entrega2.MainActivity;
import com.example.entrega2.SecondActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by tay on 25/11/17.
 */

public class FirebaseAdmin {

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAdminListener firebaseAdminListener;
    private  DatabaseReference myChildRef;
    private ValueEventListener valueEventListene=null;


    public FirebaseAdmin() {

        this.onCreate();
        this.initDataBase();

    }

    public void onCreate() {
        this.setmAuth(FirebaseAuth.getInstance());
        DataHolder.MyDataHolder.setFirebaseAdmin(this);

    }

    public void initDataBase() {
        this.setDatabase(FirebaseDatabase.getInstance());
        this.setMyRef(database.getReference()); // se puede especificar la subraiz, si no ponemos nada entonces hacemos referencia a la raiz.
    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public void setmAuth(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }


    public FirebaseDatabase getDatabase() {
        return database;
    }

    public void setDatabase(FirebaseDatabase database) {
        this.database = database;
    }

    public DatabaseReference getMyRef() {
        return myRef;
    }

    public void setMyRef(DatabaseReference myRef) {
        this.myRef = myRef;
    }

    public FirebaseAdminListener getFirebaseAdminListener() {
        return firebaseAdminListener;
    }

    public void setFirebaseAdminListener(FirebaseAdminListener firebaseAdminListener) {
        this.firebaseAdminListener = firebaseAdminListener;
    }

    public DatabaseReference getMyChildRef() {
        return myChildRef;
    }

    public void setMyChildRef(DatabaseReference myChildRef) {
        this.myChildRef = myChildRef;
    }

    public ValueEventListener getValueEventListene() {
        return valueEventListene;
    }

    public void setValueEventListene(ValueEventListener valueEventListene) {
        this.valueEventListene = valueEventListene;
    }

    public void createUserWithEmailAndPassword(String emailAddress, String password) {
        System.out.println("Email: " + emailAddress + " Pass: " + password);
        this.getmAuth().createUserWithEmailAndPassword(emailAddress, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            getmAuth().signOut();
                            firebaseAdminListener.registerOk(true);

                        }
                    }
                });
    }


    public void signInWithEmailAndPassword(String emailAddress, String password) {
        System.out.println("EmailAcceso: " + emailAddress + " PassAcceso: " + password);
        if (this.getmAuth().getCurrentUser() != null) {
            // Already signed in
            // Do nothing
            System.out.println("    ESTOY LOGUEADO!!!!!!!!!");
            firebaseAdminListener.loginIsOk(true);
        } else {
            this.getmAuth().signInWithEmailAndPassword(emailAddress, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            System.out.println("ACABO DE LOGUEARME!!!!!!!!");
                            if (task.isSuccessful()) {

                                System.out.println("dentroDelOnComplete");

                                firebaseAdminListener.loginIsOk(true);
                               // initDataBase();

                            }
                        }
                    });
        }
    }


    public void logOut() {
        myChildRef.removeEventListener(valueEventListene);
        this.getmAuth().signOut();
        firebaseAdminListener.signOutOk(true);
        System.out.println(firebaseAdminListener);


    }

    public void downloadDataAndObserveBranchChanges(final String branch){ // este método observa cambios y los actualiza es decir vuelve a descargar el elemento que ha sufrido un cambio en la bbdd
     myChildRef = this.getMyRef().child(branch);
    /*
    Cuando hemos inicializado el DatabaseReference myRef hemos puesto por defecto que se observe a la raíz del proyecto
    firebase. También hemos dicho que s epuede especificar la subrais escribiendola como string de parámetro de "getReference()",
    pero para nosotros será mejor recibir una rama por parámetro que será hija de la raíz de esta manera podemos recibir deistintas
    ramas en vez de tener que depender siempre de la declarada inicialmente.

     */
        // Read from the database
        // en vez de hacer myRef.addValueListener que seríoa la raíz pasamos la rama hija que hemos recibido por parámetro
         valueEventListene = myChildRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                /*String value = dataSnapshot.getValue(String.class);
                System.out.println("xxxxxx" + value + "xxxxxxx");
                */

                firebaseAdminListener.downloadBranch(branch, dataSnapshot);
                /*
                En vez de coger aquí el value del snapshot y castearlo a un objeto con el que se pueda trabajar,
                lo que haremos es llamar a todos los que implementen el método dwnloadBranch y les pasaremos
                este snapshot para que trabajen y lo castean como ellos quieras.
                Esto sirve para optimizar nuestro programa dado que si el casteo lo hiciesemos aquí, estaríamos
                obligados a usar siempre un objeto de un mismo tipo.
                En nuestro caso como la la lista está en el secondActivity, entonces será a quí donde recibiremos
                tanto la rama como el snapshot y trabajaremos con él.
                 */


            }

            @Override
            public void onCancelled(DatabaseError error) {
                firebaseAdminListener.downloadBranch(branch, null);

            }
        });
    }

}
