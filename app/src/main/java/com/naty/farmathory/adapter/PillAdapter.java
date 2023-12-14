package com.naty.farmathory.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.naty.farmathory.R;
import com.naty.farmathory.model.Pill;

import java.util.ArrayList;
import java.util.List;

public class PillAdapter extends RecyclerView.Adapter<PillAdapter.MyViewHolder> {
    Context myContext = null;
    protected List<Pill> items;
    Dialog dialog;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_pill, parent, false);

        return new MyViewHolder(itemView);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public View view;
        TextView pillName;
        TextView pillDosage;
        TextView btnPlus;
        TextView btnPill;

        public MyViewHolder(View view) {
            super(view);
            this.view = view;
            pillName = view.findViewById(R.id.pillName);
            pillDosage = view.findViewById(R.id.pillDosage);
            btnPlus = view.findViewById(R.id.btnPlus);
            btnPill = view.findViewById(R.id.btnPill);
        }
    }

    public PillAdapter(Context myContext) {
        this.myContext = myContext;
        this.items = new ArrayList<>();
    }

    public void setItems (List<Pill> items) {
        this.items.clear();
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Pill item = items.get(position);
        holder.pillName.setText(item.getPillName());
        holder.pillDosage.setText(String.valueOf(item.getDosage()));
        holder.btnPlus.setOnClickListener(view -> {
            dialog = new Dialog(myContext, R.style.Theme_Dialog_Translucent);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.adapter_pill_menu);

            Button btnDismiss = dialog.findViewById(R.id.btnDismiss);
            btnDismiss.setOnClickListener(v1 -> dialog.dismiss());

            Button btnEditar = dialog.findViewById(R.id.btnEditar);
            btnEditar.setOnClickListener(v2 -> {
                ((OnGetPillAdapter) myContext).onEditPill(item);
                dialog.dismiss();
            });

            Button btnEliminar = dialog.findViewById(R.id.btnEliminar);
            btnEliminar.setOnClickListener(v3 -> {
                ((OnGetPillAdapter) myContext).onDeletePill(item);
                dialog.dismiss();
            });

            dialog.show();
        });

        holder.btnPill.setOnClickListener(view -> {
            ((OnGetPillAdapter) myContext).onSelPill(item);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<Pill> getItems() {
        return items;
    }

    public interface OnGetPillAdapter {
        void onEditPill(Pill pill);
        void onDeletePill(Pill pill);
        void onSelPill(Pill pill);
    }
}
