package com.naty.farmathory;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddPillActivity extends AppCompatActivity {

    private EditText pillNameEditText;
    private EditText dosageEditText;
    private Button addButton;

    private PillController pillController;

    private String pill_key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pill);

        pillNameEditText = findViewById(R.id.editText_pill_name);
        dosageEditText = findViewById(R.id.editText_dosage);
        addButton = findViewById(R.id.button_add);

        pillController = new PillController();

        addButton.setOnClickListener(view -> addPill());

        Bundle extrasIn = getIntent().getExtras();
        if (extrasIn != null) {
            pill_key = extrasIn.getString("pill_key");
            String pill_name = extrasIn.getString("pill_name");
            int pill_dosage = extrasIn.getInt("pill_dosage");

            pillNameEditText.setText(pill_name);
            dosageEditText.setText(String.valueOf(pill_dosage));
        }
    }

    private void addPill() {
        Boolean isError = false;

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
            Pill pill = new Pill(pill_name, pill_dosage, pill_key);
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
