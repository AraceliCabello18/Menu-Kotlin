package com.example.proyecto1

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build.VERSION_CODES.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class Home : AppCompatActivity() {
    private lateinit var boton: Button
    private lateinit var saludar: Button
    private lateinit var spinnerOpciones: Spinner
    private lateinit var spinnerEstados: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        boton = findViewById(R.id.button)
        saludar = findViewById(R.id.saludar)
        spinnerOpciones = findViewById(R.id.opciones)
        spinnerEstados =  findViewById(R.id.estados)

        val options = listOf("Masculino", "Femenino")
        val estados = listOf("Estado de Mexico", "CDMX", "Aguascalientes", "Oaxaca", "Chiapas")


        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerOpciones.adapter = adapter

        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, estados)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerEstados.adapter = adapter2


        boton.setOnClickListener {
            retornar()
        }

        saludar.setOnClickListener {
            saludar()
        }

        val buttonNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        buttonNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.nav_home->{
                    val intent = Intent(this, Home::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_settings->{
                    val intent = Intent(this, Setting::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_profile->{
                    val intent = Intent(this, Profile::class.java)
                    startActivity(intent)
                    true
                }
                else-> {
                    false
                }
            }
        }
    }

    private fun retornar() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


    private fun saludar() {

        val nombre = findViewById<EditText>(R.id.nombre)
        val palabra = nombre.text.toString()
        val age = findViewById<EditText>(R.id.edad)
        val edad = age.text.toString()


        val selectOpcion = spinnerOpciones.selectedItem.toString()
        val selectEstado = spinnerEstados.selectedItem.toString()

        val dialogView = layoutInflater.inflate(R.layout.custom_dialog, null)

        val dialogTitle = dialogView.findViewById<TextView>(R.id.dialog_title)
        val dialogMessage = dialogView.findViewById<TextView>(R.id.dialog_message)

        dialogTitle.text = "Confirmar"

        dialogMessage.text="¿Quieres saludar?"

        val builder = AlertDialog.Builder(this)

        builder.setView(dialogView)

        val dialog = builder.create()

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.show()

        val btnCancelar = dialogView.findViewById<Button>(R.id.cancelar)
        val btnAceptar = dialogView.findViewById<Button>(R.id.aceptar)

        btnCancelar.setOnClickListener{
            dialog.dismiss()
        }

        btnAceptar.setOnClickListener{
            dialog.dismiss()
            mostrarDatos(palabra,edad,selectOpcion,selectEstado)
        }
    }

    private fun mostrarDatos(nombre: String, edad: String, genero: String, estado: String){
        val dialogView = layoutInflater.inflate(R.layout.custom_dialog, null)

        val dialogTitle = dialogView.findViewById<TextView>(R.id.dialog_title)
        val dialogMessage = dialogView.findViewById<TextView>(R.id.dialog_message)

        dialogTitle.text = "Saludo"

        dialogMessage.text="Nombre: $nombre \nEdad: $edad, \nGénero: $genero \nEstado: $estado"

        val builder = AlertDialog.Builder(this)

        builder.setView(dialogView)

        val dialog = builder.create()

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.show()

        val btnCancelar = dialogView.findViewById<Button>(R.id.cancelar)
        val btnAceptar = dialogView.findViewById<Button>(R.id.aceptar)

        btnCancelar.setOnClickListener{
            dialog.dismiss()
        }

        btnAceptar.setOnClickListener{
            dialog.dismiss()
            Toast.makeText(this, "Accion Completada",Toast.LENGTH_LONG).show()
        }
    }
}





