package com.onurdemir.projectf

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.onurdemir.projectf.databinding.ActivityMainBinding
import kotlinx.coroutines.NonCancellable.start
import kotlinx.coroutines.Runnable
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var score = 0
    var imageArray = ArrayList<ImageView>()

    var runnable = Runnable {}
    var handler = Handler(Looper.getMainLooper())

    val list = listOf("ELMA = APPLE", "MUZ = BANANA", "PORTAKAL = ORANGE","KAYISI = APRICOT", "KİRAZ = CHERRY", "İNCİR = FİG","ÜZÜM = GRAPE","KAVUN = MELON", "KARPUZ = WATERMELON", "ÇİLEK = STRAWBERRY", "MAVİ = BLUE", "PEMBE = PINK","SARI = YELLOW", "BEYAZ = WHITE", "SİYAH = BLACK", "KIRMIZI = RED", "TURUNCU = ORANGE", "YEŞİL = GREEN", "KAHVERENGİ = BROWN", "GRİ = GREY")
    val randomItem = list.random()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)




        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)
        imageArray.add(binding.imageView10)
        imageArray.add(binding.imageView11)
        imageArray.add(binding.imageView12)
        imageArray.add(binding.imageView13)
        imageArray.add(binding.imageView14)
        imageArray.add(binding.imageView15)
        imageArray.add(binding.imageView16)
        imageArray.add(binding.imageView)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)

        hideImages()

        object : CountDownTimer(17500, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.timeText.text = "ZAMAN : ${millisUntilFinished/1000}"
            }

            override fun onFinish() {



                binding.timeText.text = "ZAMAN : BİTTİ"
                handler.removeCallbacks(runnable)

                for (image in imageArray) {
                    image.visibility = View.INVISIBLE


                }

                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("TÜRKÇE - ENGLISH")
                alert.setMessage("${randomItem}")
                alert.setPositiveButton("TEKRAR OYNA", DialogInterface.OnClickListener { dialog, which ->

                    val intentFromMain = intent
                    finish()
                    startActivity(intentFromMain)

                })

                alert.setNegativeButton("OYUNDAN ÇIK", DialogInterface.OnClickListener { dialog, which ->
                    Toast.makeText(this@MainActivity, "OYUN BİTTİ", Toast.LENGTH_LONG).show()

                })

                alert.show()



            }

        }.start()


    }





    fun hideImages(){

        runnable = object : Runnable {
            override fun run() {

                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }

                val random = java.util.Random()
                val randomIndex = random.nextInt(12)
                imageArray[randomIndex].visibility = View.VISIBLE

                handler.postDelayed(runnable, 500)

            }

        }

        handler.post(runnable)


        }

    fun increaseScore(view : View){

        score = score +1
        binding.scoreText.text = "SKOR : ${score}"

    }

}