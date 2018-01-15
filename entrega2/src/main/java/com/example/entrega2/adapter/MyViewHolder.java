package com.example.entrega2.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.entrega2.R;

/**
 * Created by tay on 17/12/17.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {
    private TextView txtMarca;
    public TextView txtModelo;
    private ImageView imageViewCoche;
    private ListViewHolderEvents listViewHolderEvents;
    private ListAdapterListener listAdapterListener;
    private View itemView;

    public MyViewHolder(View itemView) {
        super(itemView);
        this.itemView=itemView;
        this.txtMarca = (TextView) itemView.findViewById(R.id.txtMarca);
        this.txtModelo = (TextView) itemView.findViewById(R.id.txtModelo);
        this.imageViewCoche = (ImageView) itemView.findViewById(R.id.imgCoche);
        this.listViewHolderEvents = new ListViewHolderEvents(this);
        itemView.setOnClickListener(listViewHolderEvents);
    }

    public TextView getTxtMarca() {
        return txtMarca;
    }

    public void setTxtMarca(TextView txtMarca) {
        this.txtMarca = txtMarca;
    }

    public TextView getTxtModelo() {
        return txtModelo;
    }

    public void setTxtModelo(TextView txtModelo) {
        this.txtModelo = txtModelo;
    }

    public ImageView getImageViewCoche() {
        return imageViewCoche;
    }

    public void setImageViewCoche(ImageView imageViewCoche) {
        this.imageViewCoche = imageViewCoche;
    }

    public View getItemView() {
        return itemView;
    }

    public void setItemView(View itemView) {
        this.itemView = itemView;
    }

    public ListViewHolderEvents getListViewHolderEvents() {
        return listViewHolderEvents;
    }

    public void setListViewHolderEvents(ListViewHolderEvents listViewHolderEvents) {
        this.listViewHolderEvents = listViewHolderEvents;
    }

    public ListAdapterListener getListAdapterListener() {
        return listAdapterListener;
    }

    public void setListAdapterListener(ListAdapterListener listAdapterListener) {
        this.listAdapterListener = listAdapterListener;
    }


}
