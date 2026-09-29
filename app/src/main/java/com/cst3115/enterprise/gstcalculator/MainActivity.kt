package com.cst3115.enterprise.gstcalculator

import android.os.Bundle
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged

class MainActivity : AppCompatActivity() {
    private lateinit var salesAmount: EditText
    private lateinit var fedBar: SeekBar
    private lateinit var provBar: SeekBar
    private lateinit var fedPercent: TextView
    private lateinit var provPercent: TextView
    private lateinit var fedTax: TextView
    private lateinit var provTax: TextView
    private lateinit var totalTax: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        salesAmount = findViewById(R.id.etSalesAmount)
        fedBar = findViewById(R.id.seekFedPercent)
        provBar = findViewById(R.id.seekProvPercent)
        fedPercent = findViewById(R.id.textFedPercent)
        provPercent = findViewById(R.id.textProvPercent)
        fedTax = findViewById(R.id.textFedAmount)
        provTax = findViewById(R.id.textProvAmount)
        totalTax = findViewById(R.id.textTotalTax)

        // Sales Amount Listener
        salesAmount.doAfterTextChanged {
            calculateTax()
        }

        // SeekBar code

        fedBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener{

                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {

                    fedPercent.text="$progress%"
                    calculateTax()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?){

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?){

                }

            }
        )

        provBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener{

                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    provPercent.text="$progress%"
                    calculateTax()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {

                }
            }
        )


    }


    private fun calculateTax(){
        val amountText = salesAmount.text.toString()
        val amount = amountText.toDoubleOrNull()


        if (amount == null){
            fedTax.text="\$0.00"
            provTax.text="\$0.00"
            totalTax.text="\$0.00"
            return
        }

        val federalRate = fedBar.progress
        val provincialRate = provBar.progress

        val fedTaxAmount = amount * (federalRate.toDouble() / 100)
        val provTaxAmount = amount * (provincialRate.toDouble() / 100)
        val totalTaxAmount = fedTaxAmount + provTaxAmount

        fedTax.text="\$${String.format("%.2f", fedTaxAmount)}"
        provTax.text="\$${String.format("%.2f", provTaxAmount)}"
        totalTax.text="\$${String.format("%.2f", totalTaxAmount)}"
    }
}