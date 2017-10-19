package com.leo.test.Presentation.Category.Implementation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.leo.test.Models.Itunes;
import com.leo.test.Presentation.AplicationList.Implementation.AplicacionesActivity;
import com.leo.test.Presentation.Category.Interfaces.ICategoryPresenter;
import com.leo.test.Presentation.Category.Interfaces.ICategoryView;
import com.leo.test.Presentation.Components.DialogoAlerta;
import com.leo.test.R;
import com.leo.test.Utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CategoriesActivity extends AppCompatActivity implements ICategoryView,DialogoAlerta.IOnAlertDialogListener {


    @InjectView(R.id.rv_category)
    RecyclerView rv_category;

    @InjectView(R.id.progress)
    ProgressBar progressBar;

    private ICategoryPresenter presenter;
    private List<String> listaCategorias;
    private AdaptadorCategories adaptadorCategories;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
        setTitle("Categorias");
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        presenter = new CategoryPresenter(this);
        presenter.onCreate();
        progressBar.setVisibility(View.VISIBLE);
        presenter.getCategory(this);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    //este evento se dispara cuando se recibe respuesta del servicio rest//
    @Override
    public void notifyGetTemplateSuccess(Itunes itunes) {
        progressBar.setVisibility(View.GONE);
        Set<String> categorias = new HashSet<>();
        for (int i = 0; i < itunes.getFeed().getEntry().size(); i++) {
            categorias.add(itunes.getFeed().getEntry().get(i).getCategory().getAttributes().getLabel());
        }
        listaCategorias = new ArrayList<String>(categorias);
        Collections.sort(listaCategorias, new Comparator<String>() {
            public int compare(String obj1, String obj2) {
                return obj1.compareTo(obj2);
            }
        });
        adaptadorCategories=new AdaptadorCategories((ArrayList) listaCategorias);
        rv_category.setHasFixedSize(true);

        adaptadorCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AplicacionesActivity.class);
                i.putExtra("categoria", listaCategorias.get(rv_category.getChildAdapterPosition(v)).toString());
                startActivity(i);
            }
        });
        rv_category.setAdapter(adaptadorCategories);
        rv_category.setLayoutManager(
        new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
    //Este evento se dispara si no hay internet//
    @Override
    public void notifyFromPersistence() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DialogoAlerta dialogo = new DialogoAlerta();
        dialogo.show(fragmentManager,  Constants.Tags.categorias);
        dialogo.setListener(this);
    }

    //este evento se dispara al darle ok al dialogo de fallo de conexion//
    @Override
    public void onDialogOKClik() {
        presenter.getCategoryFromPersistence(this);
    }
}
