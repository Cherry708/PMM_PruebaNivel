package com.example.pmm_pruebanivel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import com.example.pmm_pruebanivel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalcular.isEnabled = false

        var campoA = false
        var campoB = false
        var campoC = false

        binding.etNumber0.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                campoA = p0.toString().isNotEmpty()
                binding.btnCalcular.isEnabled = datosIntroducidos(campoA,campoB,campoC)
            }
        })

        binding.etNumber1.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                campoB = p0.toString().isNotEmpty()
                binding.btnCalcular.isEnabled = datosIntroducidos(campoA,campoB,campoC)
            }
        })

        binding.etNumber2.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                campoC = p0.toString().isNotEmpty()
                binding.btnCalcular.isEnabled = datosIntroducidos(campoA,campoB,campoC)
            }
        })


        binding.btnCalcular.setOnClickListener {
            val numero0 = binding.etNumber0.text.toString().toDouble()
            val numero1 = binding.etNumber1.text.toString().toDouble()
            val numero2 = binding.etNumber2.text.toString().toDouble()

            val resultadoEcuacion = EcuacionSegundoGrado.obtenerResultado(numero0,numero1,numero2)

            val intent = Intent(this, Resultado::class.java)
            intent.putExtra("resultado", resultadoEcuacion)
            startActivity(intent)
        }

    }

    /**
     * Obtiene los campos y devuelve si tienen o no los datos
     */
    fun datosIntroducidos(a: Boolean, b: Boolean, c: Boolean): Boolean{
        return a && b && c
    }

}

class EcuacionSegundoGrado() {
    companion object{
        fun obtenerResultado(a: Double, b: Double, c: Double): String {
            var resultado = ""

            if (a == 0.0 && b == 0.0 && c == 0.0)
                resultado = "La ecuacion tiene infinitas soluciones"

            if (a == 0.0 && b == 0.0 && c != 0.0)
                resultado = "La ecuacion no tiene solucion"

            if (a != 0.0 && b != 0.0 && c == 0.0)
                resultado = "X1 = 0\n\nX2 = ${(-b / a)}"

            if (a == 0.0 && b != 0.0 && c != 0.0)
                resultado = "X1 = X2 = ${-c / b}"

            if (a != 0.0 && b != 0.0 && c != 0.0) {
                var discriminante = b * b - (4 * a * c)

                //Raiz negativa, numero imaginario
                if (discriminante < 0) {
                    discriminante = -discriminante;
                    resultado = Math.pow(discriminante, 0.5).toString();
                    resultado = "Número imaginario: $resultado"+"i\n"
                } else
                    resultado = "X1 = ${-b + Math.sqrt(discriminante) / (2 * a)}\n\n" +
                            "X2 = ${-b - Math.sqrt(discriminante) / (2 * a)}"
            }
            return resultado
        }
    }

}
