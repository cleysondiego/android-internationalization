package br.com.fatec.idiomas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class SelectLanguageActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_language);

        Button btnCancel = findViewById(R.id.btnCancel);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnSetPT = findViewById(R.id.btnSetPT);
        Button btnSetEN = findViewById(R.id.btnSetEN);
        Button btnSetES = findViewById(R.id.btnSetES);


        btnCancel.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnSetPT.setOnClickListener(this);
        btnSetEN.setOnClickListener(this);
        btnSetES.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btnCancel) {
            finish();
        } else if (id == R.id.btnSave) {
            finish();
        } else if (id == R.id.btnSetPT) {
            setLanguage("pt");
        } else if (id == R.id.btnSetEN) {
            setLanguage("en");
        } else if (id == R.id.btnSetES) {
            setLanguage("es");
        }
    }

    private void setLanguage(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Context context = this;
        Resources resources = context.getResources();
        Configuration configuration = new Configuration(resources.getConfiguration());

        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        SharedPreferences.Editor sharedPreferences = getSharedPreferences("fatec", MODE_PRIVATE).edit();
        sharedPreferences.putString("idioma", language);
        sharedPreferences.apply();

        recreate();
    }
}
