package com.naty.farmathory;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.naty.farmathory.controller.PillController;
import com.naty.farmathory.model.Pill;
import com.naty.farmathory.model.PillDate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class AddPillActivity extends AppCompatActivity {
    Context myContext = this;

    private EditText pillNameEditText;
    private EditText dosageEditText;
    private EditText editText_date_from;
    private Button addButton;

    private PillController pillController;

    private String pill_key = "";

    Calendar myCalendar = Calendar.getInstance();
    String dateFormat = "dd/MM/yyyy";
    DatePickerDialog.OnDateSetListener date;
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.GERMAN);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pill);

        String FechaEmis = sdf.format(myCalendar.getTime());

        pillNameEditText = findViewById(R.id.editText_pill_name);
        dosageEditText = findViewById(R.id.editText_dosage);
        editText_date_from = findViewById(R.id.editText_date_from);
        addButton = findViewById(R.id.button_add);

        pillController = new PillController();

        editText_date_from.setText(FechaEmis);
        editText_date_from.setOnClickListener(view -> new DatePickerDialog(myContext, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show());

        addButton.setOnClickListener(view -> addPill());

        Bundle extrasIn = getIntent().getExtras();
        if (extrasIn != null) {
            pill_key = extrasIn.getString("pill_key");
            String pill_name = extrasIn.getString("pill_name");
            int pill_dosage = extrasIn.getInt("pill_dosage");

            pillNameEditText.setText(pill_name);
            dosageEditText.setText(String.valueOf(pill_dosage));
        }

        // set calendar date and update editDate
        date = (view, year, monthOfYear, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            editText_date_from.setText(sdf.format(myCalendar.getTime()));
        };
    }

    private List<PillDate> addPillDate(int pill_dosage) {
        List<PillDate> pillDates = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, myCalendar.get(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.MONTH, myCalendar.get(Calendar.MONTH));
        calendar.set(Calendar.YEAR, myCalendar.get(Calendar.YEAR));

        for(int i = 0; i < pill_dosage; i += 1) {
            if (i != 0)
                calendar.add(Calendar.DAY_OF_MONTH, 1);

            PillDate pillDate = new PillDate(sdf.format(calendar.getTime()), PillDate.STATE_PENDING);
            pillDates.add(pillDate);
        }
        Collections.reverse(pillDates);

        return pillDates;
    }

    private void addPill() {
        String pill_name = pillNameEditText.getText().toString().trim();
        int pill_dosage = 0;
        try {
            pill_dosage = Integer.parseInt(dosageEditText.getText().toString().trim());
        } catch (Exception e) {
            dosageEditText.setError("Valor Invalido");
        }

        if (pill_name.equals("")) {
            pillNameEditText.setError("Campo Obligatorio");
        } else if (pill_dosage <= 0) {
            dosageEditText.setError("Valor Invalido");
        } else {
            List<PillDate> pill_dates = addPillDate(pill_dosage);

            Pill pill = new Pill(pill_name, pill_dosage, pill_key, pill_dates);

            if (pill_key.equals("")) {
                pillController.addPill(pill);
            } else {
                pillController.editPill(pill);
            }

            Toast.makeText(getApplicationContext(), "Pill Created", Toast.LENGTH_LONG).show();
            // setContentView(R.layout.activity_menu);
            finish();
        }
    }
}
