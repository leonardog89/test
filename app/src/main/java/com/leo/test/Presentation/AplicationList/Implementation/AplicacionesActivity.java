package com.leo.test.Presentation.AplicationList.Implementation;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.leo.test.Models.ItunesEntry;
import com.leo.test.Presentation.AplicationDetail.Implementation.AplicationActivity;
import com.leo.test.Presentation.AplicationList.Interfaces.IAplicationListPresenter;
import com.leo.test.Presentation.AplicationList.Interfaces.IAplicationsView;
import com.leo.test.Presentation.Components.DialogoAlerta;
import com.leo.test.R;
import com.leo.test.Utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AplicacionesActivity extends AppCompatActivity implements IAplicationsView,DialogoAlerta.IOnAlertDialogListener {

    @InjectView(R.id.rv_aplications)
    RecyclerView rv_category;

    private TextView texto;
    private String categoriaPedida;
    private ProgressDialog pDialog;
    private ArrayList<ItunesEntry> aplicationList;
    private IAplicationListPresenter presenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
        setTitle("Aplicaciones");
        setContentView(R.layout.activity_aplicaciones);
        ButterKnife.inject(this);
        presenter=new AplicationListPresenter(this);
        presenter.onCreate();
        final Intent intent = getIntent();
        categoriaPedida=intent.getStringExtra("categoria");
        presenter.getAplicationByCategory(categoriaPedida,this);
        texto=(TextView) findViewById(R.id.text);

    }
    @Override
    public void onPause()
    {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }

    //este metodo nos devuelve un arreglo de aplicaiones//
    @Override
    public void onGetAplicationSuccess(ArrayList<ItunesEntry> entryArrayList) {

        aplicationList=entryArrayList;
        Collections.sort(aplicationList, new Comparator<ItunesEntry>() {
            public int compare(ItunesEntry obj1, ItunesEntry obj2) {
                return obj1.getName().getLabel().compareTo(obj2.getName().getLabel());
            }
        });
        AdaptadorAplications adaptadorAplications = new AdaptadorAplications(aplicationList,this);
        rv_category.setHasFixedSize(true);

        adaptadorAplications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AplicationActivity.class);
                i.putExtra("title",aplicationList.get(rv_category.getChildAdapterPosition(v)).getTitle().getLabel());
                startActivity(i);
            }
        });

        rv_category.setAdapter(adaptadorAplications);
        rv_category.setLayoutManager(
        new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    //este metodo se usa si no hay internet
    @Override
    public void notifyFromPersistence() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DialogoAlerta dialogo = new DialogoAlerta();
        dialogo.show(fragmentManager, Constants.Tags.aplicaciones);
        dialogo.setListener(this);
    }

    @Override
    public void onDialogOKClik() {
        presenter.getAplicationByCategory(categoriaPedida,this);
    }

}
