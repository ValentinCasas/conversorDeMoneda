package com.example.conversordemoneda;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.*;

import com.example.conversordemoneda.databinding.ActivityMainBinding;

import java.util.HashMap;

public class MainActivityViewModel extends AndroidViewModel {

    private Context context;
    private Intent intent;
    private MutableLiveData<String> resultado = new MutableLiveData<>();
    private Double tasaCambio = 1.19;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<String> getResultado() {
        return resultado;
    }
    public void convertirEurosADolares(Double cantidad) {
        Double resultadoConversion = cantidad / tasaCambio;
        resultado.setValue(String.format("%.2f", resultadoConversion));
    }
    public void convertirDolaresAEuros(Double cantidad) {
        Double resultadoConversion = cantidad * tasaCambio;
        resultado.setValue(String.format("%.2f", resultadoConversion));
    }
    public void convertir(Boolean rbEurosADolar, Boolean rbDolaresAEuros, String etEuros, String etDolares) {

        if (rbEurosADolar && !etEuros.isEmpty()) {
            try {
                Double cantidad = Double.parseDouble(etEuros);
                convertirEurosADolares(cantidad);
            } catch (NumberFormatException e) {
                Toast.makeText(context.getApplicationContext(), "Ingrese una cantidad válida", Toast.LENGTH_SHORT).show();
            }

        } else if (rbDolaresAEuros && !etDolares.isEmpty()) {
            try {
                Double cantidad = Double.parseDouble(etDolares);
                convertirDolaresAEuros(cantidad);
            } catch (NumberFormatException e) {
                Toast.makeText(context.getApplicationContext(), "Ingrese una cantidad válida", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context.getApplicationContext(), "Elija una opción válida", Toast.LENGTH_SHORT).show();
        }

    }

}