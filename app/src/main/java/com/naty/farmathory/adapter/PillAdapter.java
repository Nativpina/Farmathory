package com.naty.farmathory.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.naty.farmathory.Pill;
import com.naty.farmathory.R;

import java.util.ArrayList;
import java.util.List;

public class PillAdapter extends RecyclerView.Adapter<PillAdapter.MyViewHolder> {
    protected List<Pill> items;

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

        public MyViewHolder(View view) {
            super(view);
            this.view = view;
            pillName = view.findViewById(R.id.pillName);
            pillDosage = view.findViewById(R.id.pillDosage);
        }
    }

    public PillAdapter() {
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
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<Pill> getItems() {
        return items;
    }
}
