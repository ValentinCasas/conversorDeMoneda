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


        binding.rbDolaresAEuros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.etEuros.setEnabled(false);
                binding.etDolares.setEnabled(true);
            }
        });

        binding.rbEurosADolares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.etDolares.setEnabled(false);
                binding.etEuros.setEnabled(true);
            }
        });

        binding.buttonConversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mv.convertir(binding.rbEurosADolares.isChecked(), binding.rbDolaresAEuros.isChecked(),
                        binding.etEuros.getText().toString(), binding.etDolares.getText().toString());
            }
        });


    }
}