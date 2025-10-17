package com.example.wishlist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var adapter: wishAdapter
    lateinit var rv: RecyclerView
    lateinit var etName: EditText
    lateinit var etPrice: EditText
    lateinit var etUrl: EditText
    lateinit var btnSubmit: Button


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rv = findViewById<RecyclerView>(R.id.rvItems)
        etName = findViewById<EditText>(R.id.etName)
        etPrice = findViewById<EditText>(R.id.etPrice)
        etUrl = findViewById<EditText>(R.id.etUrl)
        btnSubmit = findViewById<Button>(R.id.btnSubmit)
        
        adapter = wishAdapter(mutableListOf())
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)

        btnSubmit.setOnClickListener {
            val name = etName.text.toString().trim()
            val priceText = etPrice.text.toString().trim()
            val url = etUrl.text.toString().trim()

            if (name.isEmpty() || priceText.isEmpty() || url.isEmpty()) {
                Snackbar.make(rv, "Please enter all fields", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val price = priceText.toDoubleOrNull()
            if (price == null) {
                Snackbar.make(rv, "price must be a valid number!", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            adapter.add(WishItem(name, price, url))
            rv.scrollToPosition(adapter.itemCount - 1)

            etName.text.clear()
            etPrice.text.clear()
            etUrl.text.clear()
            etName.requestFocus()
        }
    }
}