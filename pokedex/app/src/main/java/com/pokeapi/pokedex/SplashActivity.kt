package com.pokeapi.pokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView

class SplashActivity : AppCompatActivity() {

    private  lateinit var image:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.splash_screen)

        //Declaração views
        image = findViewById(R.id.SplashView)
        animateSplash()

        //Temporizador SplashScreen
        val maxSplashTime: Long = 5000

        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        },maxSplashTime)
    }
    private fun animateSplash() {
        val rotate = AnimationUtils.loadAnimation(this, R.anim.rotate_splash_screen)
        image.animation = rotate
    }
}