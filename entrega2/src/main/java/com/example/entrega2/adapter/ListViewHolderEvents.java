package com.example.entrega2.adapter;

import android.view.View;

/**
 * Created by tay on 11/12/17.
 */

public class ListViewHolderEvents implements View.OnClickListener {
    MyViewHolder myViewHolder;


    public ListViewHolderEvents(MyViewHolder myViewHolder) {
        this.myViewHolder = myViewHolder;
    }

    public MyViewHolder getMyViewHolder() {
        return myViewHolder;
    }

    public void setMyViewHolder(MyViewHolder myViewHolder) {
        this.myViewHolder = myViewHolder;
    }

    @Override
    public void onClick(View view) {
        System.out.println("estoy pinchando la celda :D en el onclick");
        this.getMyViewHolder().getListAdapterListener().listAdapterCellClicked(this.getMyViewHolder());
    }
}