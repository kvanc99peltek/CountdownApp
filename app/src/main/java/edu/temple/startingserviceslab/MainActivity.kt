package edu.temple.startingserviceslab

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numberEditText = findViewById<EditText>(R.id.numberEditText)
        val startButton = findViewById<Button>(R.id.startButton)

        startButton.setOnClickListener {
            val input = numberEditText.text.toString().trim()

            if (input.isEmpty()) {
                Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val value = input.toIntOrNull()
            if (value == null || value <= 0) {
                Toast.makeText(this, "Please enter a positive integer", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, CountdownService::class.java)
            intent.putExtra("countdown_value", value)
            startService(intent)
        }
    }
}
