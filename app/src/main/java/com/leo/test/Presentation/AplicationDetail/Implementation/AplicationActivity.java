package com.leo.test.Presentation.AplicationDetail.Implementation;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.leo.test.Models.ItunesEntry;
import com.leo.test.Presentation.AplicationDetail.Interfaces.IAplicationPresenter;
import com.leo.test.Presentation.AplicationDetail.Interfaces.IAplicationView;
import com.leo.test.Presentation.Components.DialogoAlerta;
import com.leo.test.R;
import com.leo.test.Utils.Constants;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AplicationActivity extends AppCompatActivity implements IAplicationView,DialogoAlerta.IOnAlertDialogListener
{
    @InjectView(R.id.sumVIew)
    TextView summary;

    @InjectView(R.id.priceView)
    TextView priceView;

    @InjectView(R.id.contentView)
    TextView contentView;

    @InjectView(R.id.rightView)
    TextView rightView;

    @InjectView(R.id.linkView)
    TextView linkView;

    @InjectView(R.id.releaseView)
    TextView releaseView;

    @InjectView(R.id.autorView)
    TextView autorView;

    @InjectView(R.id.nameView)
    TextView nameView;

    @InjectView(R.id.imageResumen)
    ImageView imgResumen;



    private String nombre;

    private String price;
    private Bitmap imagen;
    private String content;
    private String right;
    private String link;
    private String release;
    private String autor;
    private double pulgadas;
    private IAplicationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
        setTitle("Detalle");
        setContentView(R.layout.activity_resumen);
        ButterKnife.inject(this);
        presenter=new AplicationPresenter(this);
        presenter.onCreate();
        final Intent intent = getIntent();
        nombre=intent.getStringExtra("title");
        presenter.getAplicationByTitle(nombre,this);
  }


    @Override
    public void notifyGetAplicationSuccess(ItunesEntry aplication,Boolean flag) {
        Picasso.with(this).load(aplication.getImages().get(0).getLabel()).into(imgResumen);
        right=aplication.getRight().getLabel().toString();
        release=aplication.getRelease().getAttributes().getLabel().toString();
        nombre=aplication.getName().getLabel().toString();
        summary.setMovementMethod(new ScrollingMovementMethod());
        summary.setText(aplication.getSummary().getLabel());
        price=aplication.getPrice().getAttributes().getAmount();
        if(price.equals("0.00000")) {
            price="Gratis";
        }
//        content=aplication.getContentType().getLabel().toString();
        priceView.setText(price);
        contentView.setText(content);
        rightView.setText(right);
        autor=aplication.getArtist().getLabel().toString();
       // linkView.setText(aplication.getl);
        releaseView.setText(release);
        autorView.setText(autor);
        nameView.setText(nombre);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }
    @Override
    public void notifyFromPersistence() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DialogoAlerta dialogo = new DialogoAlerta();
        dialogo.show(fragmentManager,  Constants.Tags.detalle);
        dialogo.setListener(this);
    }

    @Override
    public void onDialogOKClik() {
        presenter.getAplicationByTitleFromPersistence(nombre,this);
    }
}
