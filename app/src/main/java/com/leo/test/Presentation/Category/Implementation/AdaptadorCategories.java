package com.leo.test.Presentation.Category.Implementation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leo.test.R;

import java.util.ArrayList;

public class AdaptadorCategories
        extends RecyclerView.Adapter<AdaptadorCategories.CategoriesViewHolder> implements View.OnClickListener{
    private ArrayList<String> datos;
    private View.OnClickListener listener;
    //...

    public AdaptadorCategories(ArrayList<String> datos) {
        this.datos = datos;
    }

    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_category, viewGroup, false);

        CategoriesViewHolder cvh = new CategoriesViewHolder(itemView);

        itemView.setOnClickListener(this);

        return cvh;
    }

    @Override
    public void onBindViewHolder(CategoriesViewHolder viewHolder, int pos) {
        String item = datos.get(pos);

        viewHolder.bindTitular(item);
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

    public static class CategoriesViewHolder
            extends RecyclerView.ViewHolder {

        private TextView txtTitulo;


        public CategoriesViewHolder(View itemView) {
            super(itemView);

            txtTitulo = (TextView)itemView.findViewById(R.id.txt_category);

        }

        public void bindTitular(String item) {
            txtTitulo.setText(item);

        }
    }

    //...
}