package com.freelances.viewcustomgeometry

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.freelances.viewcustomgeometry.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var current = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRedo.setOnClickListener {
            binding.signaturePad.redo()
        }

        binding.btnUndo.setOnClickListener {
            binding.signaturePad.undo()
        }

        binding.btnClear.setOnClickListener {
            binding.signaturePad.clear()
        }

    }
}