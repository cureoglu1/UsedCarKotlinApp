package com.ctis487.workerjsondatabase
import android.media.MediaPlayer
import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ctis487.workerjsondatabase.databinding.ActivityCarDetailBinding
import com.ctis487.workerjsondatabase.model.Cars
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

class CarDetailActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var binding: ActivityCarDetailBinding
    private lateinit var gestureDetector: GestureDetector
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mediaPlayer = MediaPlayer.create(this, R.raw.audio)
        binding.btnBack.setOnClickListener{
            if(!mediaPlayer.isPlaying){
                mediaPlayer.start()
            }
        }

        gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onDoubleTap(e: MotionEvent): Boolean {
                Toast.makeText(this@CarDetailActivity, "Double Tap Detected!", Toast.LENGTH_SHORT).show()
                finish()
                return true // Indicates the double-tap was handled
            }
        })


        // Set up button animations
        val colorAnimator = ObjectAnimator.ofObject(
            binding.btnBack,
            "textColor",
            ArgbEvaluator(),
            Color.RED,
            Color.BLUE
        ).apply {
            duration = 1000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }

        val blinkAnimation = AlphaAnimation(1.0f, 0.0f).apply {
            duration = 500
            repeatMode = AlphaAnimation.REVERSE
            repeatCount = AlphaAnimation.INFINITE
        }

        binding.btnBack.startAnimation(blinkAnimation)
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(colorAnimator)
        animatorSet.start()

        // Set up click listener
            binding.btnBack.setOnTouchListener { _, event ->
                gestureDetector.onTouchEvent(event)
                true // Return true to indicate the touch was handled
            }




        // Get and display car data
        val car = intent.getParcelableExtra<Cars>("car_data")
        
        car?.let {
            with(binding) {
                tvModel.text = "${it.make} ${it.model}"
                tvYear.text = "Year: ${it.year}"
                tvPrice.text = "Price: $${it.price}"
                tvMileage.text = "Mileage: ${it.mileage}"
                tvColor.text = "Color: ${it.color}"
                tvFuelType.text = "Fuel Type: ${it.fuelType}"
                tvTransmission.text = "Transmission: ${it.transmission}"
                tvEngine.text = "Engine: ${it.engine}"
                tvHorsepower.text = "Horsepower: ${it.horsepower}"
                tvOwners.text = "Previous Owners: ${it.owners}"
                tvFeatures.text = "Features: ${it.features.joinToString(", ")}"
                ivCarImage.setImageResource(R.drawable.bestseller)
            }
        }
    }
    override fun onDestroy(){
        super.onDestroy()
        if(::mediaPlayer.isInitialized){
            mediaPlayer.release()
        }
    }


} 