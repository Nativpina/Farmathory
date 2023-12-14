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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pill);

        pillNameEditText = findViewById(R.id.editText_pill_name);
        dosageEditText = findViewById(R.id.editText_dosage);
        addButton = findViewById(R.id.button_add);

        pillController = new PillController();

        addButton.setOnClickListener(view -> addPill());
    }

    private void addPill() {
        Boolean isError = false;

        String pillName = pillNameEditText.getText().toString().trim();
        int dosage = 0;
        try {
            dosage = Integer.parseInt(dosageEditText.getText().toString().trim());
        } catch (Exception e) {
            dosageEditText.setError("Valor Invalido");
        }

        if (pillName.equals("")) {
            pillNameEditText.setError("Campo Obligatorio");
        } else if (dosage <= 0) {
            dosageEditText.setError("Valor Invalido");
        } else {
            Pill pill = new Pill(pillName, dosage);
            pillController.addPill(pill);

            Toast.makeText(getApplicationContext(), "Pill Created", Toast.LENGTH_LONG).show();
            // setContentView(R.layout.activity_menu);
            finish();
        }
    }
}
