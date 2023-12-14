package com.naty.farmathory;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.naty.farmathory.adapter.PillDateAdapter;
import com.naty.farmathory.controller.PillController;
import com.naty.farmathory.model.Pill;
import com.naty.farmathory.model.PillDate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class DatePillActivity extends AppCompatActivity implements PillDateAdapter.OnGetPillDateAdapter {
    Context myContext = this;
    PillDateAdapter adPillDates;
    RecyclerView rvPillDates;

    PillController pillController;

    Pill pill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill_date);

        adPillDates = new PillDateAdapter(myContext);

        int numberOfColumns = 1;
        RecyclerView.LayoutManager lm_products = new GridLayoutManager(getApplicationContext(), numberOfColumns);

        rvPillDates = this.findViewById(R.id.rvPillDates);
        rvPillDates.setHasFixedSize(true);
        rvPillDates.setLayoutManager(lm_products);
        rvPillDates.setItemAnimator(new DefaultItemAnimator());
        rvPillDates.setAdapter(adPillDates);

        pillController = new PillController();

        Bundle extrasIn = getIntent().getExtras();
        if (extrasIn != null) {
            pill = (Pill) getIntent().getSerializableExtra("pill");
            List<PillDate> pillDates = pill.getPillDates();

            // Calendar myCalendar = Calendar.getInstance();
            // Date current_date = myCalendar.getTime();

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            String current_date_str = dateFormat.format(date);
            Date current_date = null;
            try {
                current_date = dateFormat.parse(current_date_str);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            boolean isChange = false;
            for (PillDate pillDate : pillDates) {
                try {
                    Date pill_date = new SimpleDateFormat("dd/MM/yyyy").parse(pillDate.getDateDosage());
                    if (pillDate.getState().equals(PillDate.STATE_PENDING)) {
                        if (pill_date.compareTo(current_date) < 0) {
                            pillDate.setState(PillDate.STATE_NO_TAKEN);
                            isChange = true;
                        } else if (pill_date.compareTo(current_date) == 0) {
                            pillDate.setState(PillDate.STATE_CURRENT);
                            isChange = true;
                        }
                    }
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }

            if (isChange) {
                pill.setPillDates(pillDates);
                pillController.editPill(pill);
            }

            adPillDates.setItems(pillDates);
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEditPill() {
        List<PillDate> pillDates = adPillDates.getItems();
        pill.setPillDates(pillDates);
        pillController.editPill(pill);
    }
}

