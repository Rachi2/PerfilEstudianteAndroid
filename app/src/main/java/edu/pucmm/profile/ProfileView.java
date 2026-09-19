package edu.pucmm.profile;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.atomic.AtomicBoolean;

import edu.pucmm.profile.databinding.ActivityProfileBinding;

public class ProfileView extends AppCompatActivity {

    ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String[] arrays = {null, "Telemática", "Computación", "Derecho"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrays);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.cbmCareer.setAdapter(adapter);

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validationAndSave();
            }
        });
    }

    private void validationAndSave() {
        AtomicBoolean validated = new AtomicBoolean(true);

        if (binding.txtname.getText().toString().trim().isEmpty()) {
            binding.txtname.setError("El campo no puede estar vacío");
            validated.set(false);
        }

        if (binding.txtId.getText().toString().trim().isEmpty()) {
            binding.txtId.setError("El campo no puede estar vacío");
            validated.set(false);
        }

        if (binding.cbmCareer.getSelectedItem() == null) {
            TextView errorText = (TextView) binding.cbmCareer.getSelectedView();
            if (errorText != null) {
                errorText.setTextColor(android.graphics.Color.RED);
                errorText.setText("Debes seleccionar una carrera");
            }
            validated.set(false);
        }

        if (validated.get()) {
            //Toast de confirmacion
            Toast.makeText(this, "Perfil guardado correctamente", Toast.LENGTH_SHORT).show();

            //Extraer los datos ingresados
            String name = binding.txtname.getText().toString().trim();
            String studentId = binding.txtId.getText().toString().trim();
            String career = binding.cbmCareer.getSelectedItem().toString();

            //Crear el Intent y pasar los datos a la tercera pantalla
            Intent intent = new Intent(ProfileView.this, ProfileSuccessActivity.class);
            intent.putExtra("USER_NAME", name);
            intent.putExtra("USER_ID", studentId);
            intent.putExtra("USER_CAREER", career);
            startActivity(intent);
        }
    }
}