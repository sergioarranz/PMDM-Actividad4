package com.example.entrega2.firebase;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by tay on 25/11/17.
 */

public interface FirebaseAdminListener {

   public void loginIsOk(boolean ok);
   public void registerOk(boolean ok);
   public void signOutOk(boolean ok);
   public void downloadBranch(String branch, DataSnapshot dataSnapshot); // pasamos por parámetro la rama descargada para saber cuál es y de esta manera poder tener varias ramas de las que descargamos contenido
}
