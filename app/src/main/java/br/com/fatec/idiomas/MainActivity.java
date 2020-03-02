package br.com.fatec.idiomas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLanguage();
        setContentView(R.layout.activity_main);

        Button btnChangeLanguage = findViewById(R.id.btnSelectLanguage);
        btnChangeLanguage.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            recreate();
        }
    }

    @Override
    public void onClick(View v) {
        changeLanguage();
    }

    private void changeLanguage() {
        Intent intent = new Intent(this, SelectLanguageActivity.class);
        startActivityForResult(intent, 1);
    }

    private void loadLanguage() {
        SharedPreferences sharedPreferences = getSharedPreferences("fatec", MODE_PRIVATE);
        String language = sharedPreferences.getString("idioma", "pt");

        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Context context = this;
        Resources resources = context.getResources();
        Configuration configuration = new Configuration(resources.getConfiguration());

        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }
}
