package com.example.conversordemoneda;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.*;

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

}
