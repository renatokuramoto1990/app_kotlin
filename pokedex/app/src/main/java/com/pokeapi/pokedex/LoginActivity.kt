package com.pokeapi.pokedex

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import java.util.prefs.PreferenceChangeEvent

class LoginActivity : AppCompatActivity() {

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_login)

        inputLogin.setText(Prefs.getString("usuario"))
        inputPassword.setText(Prefs.getString("senha"))
        checkBoxLembrar.isChecked = Prefs.getBoolean("lembrar")

        btnLogin.setOnClickListener{
            val usuario = inputLogin.text.toString()
            val senha = inputPassword.text.toString()

            Prefs.setBoolean("lembrar", checkBoxLembrar.isChecked)

            if (usuario == "aluno" && senha == "impacta") {
                val intent = Intent(context, AppActivity::class.java)
                val params = Bundle()

                if (checkBoxLembrar.isChecked) {
                    Prefs.setString("usuario", usuario)
                    Prefs.setString("senha", senha)
                } else {
                    Prefs.setString("usuario", "")
                    Prefs.setString("senha", "")
                }

                params.putString("username", usuario)
                intent.putExtras(params)
                startActivityForResult(intent, 1)
            }
            else {
                Toast.makeText(this, "Dados inválidos", Toast.LENGTH_LONG).show()
            }
        }


    }
}