package com.example.pmm_pruebanivel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pmm_pruebanivel.databinding.ActivityMainBinding
import com.example.pmm_pruebanivel.databinding.ActivityResultadoBinding

class Resultado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        val binding = ActivityResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = getIntent()
        val extras = intent.getExtras()

        val resultado = extras?.getString("resultado")

        binding.etResultadoEcuacion.text = resultado

        binding.btnVolver.setOnClickListener {
            onBackPressed()
        }
    }
}