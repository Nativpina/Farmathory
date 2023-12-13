package com.naty.farmathory;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PillActivity extends AppCompatActivity {

    ArrayAdapter <Medicamento>adapter;
    ArrayList<Medicamento> listado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill);

        //Inicializar Listado
        this.listado = new ArrayList<Medicamento>();

        //Inicializar Adapter
        this.adapter= new ArrayAdapter<Medicamento>( context: this , android.R.layout.simple_list_item_1);

        //Cargar algunos datos iniciales
        Medicamento m1 = new Medicamento(nombre:"ibuprofeno", hora"")





    }
}
