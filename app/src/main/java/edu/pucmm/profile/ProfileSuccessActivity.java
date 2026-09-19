package edu.pucmm.profile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_success);

        // Relacionar elementos de la interfaz visual
        TextView lblGreeting = findViewById(R.id.lblSuccessGreeting);
        TextView lblName = findViewById(R.id.lblSuccessName);
        TextView lblId = findViewById(R.id.lblSuccessId);
        TextView lblCareer = findViewById(R.id.lblSuccessCareer);
        Button btnEdit = findViewById(R.id.btnEdit);

        // Obtener los datos enviados desde la primera pantalla
        Intent intent = getIntent();
        String name = intent.getStringExtra("USER_NAME");
        String id = intent.getStringExtra("USER_ID");
        String career = intent.getStringExtra("USER_CAREER");

        // Mostrar los datos recibidos
        lblGreeting.setText("¡Hola, " + name + "!");
        lblName.setText(name);
        lblId.setText(id);
        lblCareer.setText(career);

        // Accion "Editar" para regresar a la pantalla anterior
        btnEdit.setOnClickListener(v -> finish());
    }
}