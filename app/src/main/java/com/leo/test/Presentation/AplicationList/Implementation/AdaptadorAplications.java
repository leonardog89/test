package com.leo.test.Presentation.AplicationList.Implementation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leo.test.Models.ItunesEntry;
import com.leo.test.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class AdaptadorAplications
        extends RecyclerView.Adapter<AdaptadorAplications.AplicationViewHolder> implements View.OnClickListener{
    private ArrayList<ItunesEntry> datos;
    private View.OnClickListener listener;
    private Context context;
    //...

    public AdaptadorAplications(ArrayList<ItunesEntry> datos,Context context) {

        this.datos = datos;
        this.context=context;
    }

    @Override
    public AplicationViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);

        AplicationViewHolder cvh = new AplicationViewHolder(itemView);

        itemView.setOnClickListener(this);

        return cvh;
    }

    @Override
    public void onBindViewHolder(AplicationViewHolder viewHolder, int pos) {
        ItunesEntry item = datos.get(pos);

        viewHolder.bindTitular(item,context);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }
    //...


    //...

    public static class AplicationViewHolder
            extends RecyclerView.ViewHolder {


        private  TextView nameView;
        private  TextView priceView;
        private  TextView autorView;
        private  ImageView img;
        private  TextView autoView;


        public AplicationViewHolder(View itemView) {
            super(itemView);

            nameView = (TextView)itemView.findViewById(R.id.name);
            priceView = (TextView)itemView.findViewById(R.id.price);
            autorView= (TextView)itemView.findViewById(R.id.autor);
            autorView= (TextView)itemView.findViewById(R.id.autor);
            img=(ImageView)itemView.findViewById(R.id.ImgFoto);

        }

        public void bindTitular(ItunesEntry item,Context context) {

            String name =item.getTitle().getLabel();
            nameView.setText(name);


            String price =item.getPrice().getAttributes().getAmount();
            if(price.equals("0.00000")){
                price="Gratis";
            }
            priceView.setText(price);


            String autor =item.getArtist().getLabel();

            autorView.setText(autor);


            Picasso.with(context).load(item.getImages().get(0).getLabel()).into(img);

        }
    }

    //...
}