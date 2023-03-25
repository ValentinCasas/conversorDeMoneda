package com.example.conversordemoneda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.conversordemoneda.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        mv.getResultado().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvResultado.setText(s);
            }
        });

        binding.rbEurosADolares.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    binding.etEuros.setEnabled(true);
                    binding.etDolares.setEnabled(false);
                } else {
                    binding.etEuros.setEnabled(false);
                    binding.etDolares.setEnabled(true);
                }
            }
        });

        binding.rbDolaresAEuros.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    binding.etDolares.setEnabled(true);
                    binding.etEuros.setEnabled(false);
                } else {
                    binding.etDolares.setEnabled(false);
                    binding.etEuros.setEnabled(true);
                }
            }
        });

        binding.buttonConversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.rbEurosADolares.isChecked()) {
                    String cantidadStr = binding.etEuros.getText().toString();
                    if (cantidadStr.isEmpty()) {
                        binding.etEuros.setError("Ingrese una cantidad");
                        return;
                    }
                    Double cantidad = Double.parseDouble(cantidadStr);
                    mv.convertirEurosADolares(cantidad);
                } else if (binding.rbDolaresAEuros.isChecked()) {
                    String cantidadStr = binding.etDolares.getText().toString();
                    if (cantidadStr.isEmpty()) {
                        binding.etDolares.setError("Ingrese una cantidad");
                        return;
                    }
                    Double cantidad = Double.parseDouble(cantidadStr);
                    mv.convertirDolaresAEuros(cantidad);
                } else {
                    Toast.makeText(MainActivity.this, "Seleccione una opción de conversión", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}