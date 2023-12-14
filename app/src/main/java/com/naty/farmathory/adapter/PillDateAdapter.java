package com.naty.farmathory.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.naty.farmathory.R;
import com.naty.farmathory.model.PillDate;

import java.util.ArrayList;
import java.util.List;

public class PillDateAdapter extends RecyclerView.Adapter<PillDateAdapter.MyViewHolder> {
    Context myContext = null;
    protected List<PillDate> items;
    Dialog dialog;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_pill_date, parent, false);

        return new MyViewHolder(itemView);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public View view;
        TextView pillDate;
        TextView pillState;
        TextView btnPillMark;

        public MyViewHolder(View view) {
            super(view);
            this.view = view;
            pillDate = view.findViewById(R.id.pillDate);
            pillState = view.findViewById(R.id.pillState);
            btnPillMark = view.findViewById(R.id.btnPillMark);
        }
    }

    public PillDateAdapter(Context myContext) {
        this.myContext = myContext;
        this.items = new ArrayList<>();
    }

    public void setItems (List<PillDate> items) {
        this.items.clear();
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final PillDate item = items.get(position);
        holder.pillDate.setText(item.getDateDosage());
        holder.pillState.setText(item.getValState());

        holder.btnPillMark.setVisibility(item.getState().equals(PillDate.STATE_CURRENT) ? View.VISIBLE : View.GONE);
        holder.btnPillMark.setOnClickListener(view -> {
            item.setState(PillDate.STATE_TAKEN);
            notifyDataSetChanged();
            ((OnGetPillDateAdapter) myContext).onEditPill();
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<PillDate> getItems() {
        return items;
    }

    public interface OnGetPillDateAdapter {
        void onEditPill();
    }
}
