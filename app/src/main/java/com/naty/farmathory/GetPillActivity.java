package com.naty.farmathory;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.naty.farmathory.adapter.PillAdapter;
import com.naty.farmathory.controller.PillController;
import com.naty.farmathory.model.Pill;

import java.util.List;

public class GetPillActivity extends AppCompatActivity implements PillController.OnGetPillListener, PillAdapter.OnGetPillAdapter {
    Context myContext = this;
    PillAdapter adPills;
    RecyclerView rvPills;

    PillController pillController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill);

        adPills = new PillAdapter(myContext);

        int numberOfColumns = 1;
        RecyclerView.LayoutManager lm_products = new GridLayoutManager(getApplicationContext(), numberOfColumns);

        rvPills = this.findViewById(R.id.rvPills);
        rvPills.setHasFixedSize(true);
        rvPills.setLayoutManager(lm_products);
        rvPills.setItemAnimator(new DefaultItemAnimator());
        rvPills.setAdapter(adPills);

        pillController = new PillController();
        pillController.setListener(this);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetPillSuccess(List<Pill> pills) {
        adPills.setItems(pills);
    }

    @Override
    public void onGetPillFailure(String errorMessage) {
        showToast("Error al obtener las pastillas: " + errorMessage);
    }

    @Override
    public void onEditPill(Pill pill) {
        Intent intent = new Intent(GetPillActivity.this, AddPillActivity.class);
        intent.putExtra("pill_key", pill.getKey());
        intent.putExtra("pill_name", pill.getPillName());
        intent.putExtra("pill_dosage", pill.getDosage());
        startActivity(intent);
    }

    @Override
    public void onDeletePill(Pill pill) {
        pillController.deletePill(pill);
    }

    @Override
    public void onSelPill(Pill pill) {
        Intent intent = new Intent(GetPillActivity.this, DatePillActivity.class);
        intent.putExtra("pill", pill);
        startActivity(intent);
    }
}

