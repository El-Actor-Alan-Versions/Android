package mx.edu.utez.example.qr2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import mx.edu.utez.example.qr2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var valorQr = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnScanner.setOnClickListener {
            val options = ScanOptions()
            options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES)
            options.setPrompt("Scan a barcode")
            options.setCameraId(0) // Use a specific camera of the device
            options.setBeepEnabled(false)
            options.setBarcodeImageEnabled(true)
            barcodeLauncher.launch(options)


        }


    }

    private val barcodeLauncher = registerForActivityResult<ScanOptions, ScanIntentResult>(
        ScanContract()
    ) { result: ScanIntentResult ->
        if (result.contents == null) {
            Toast.makeText(this@MainActivity, "Cancelled", Toast.LENGTH_LONG).show()
        } else {



            Toast.makeText(
                this@MainActivity,
                "Scanned: " + result.contents,
                Toast.LENGTH_LONG
            ).show()

            valorQr = result.contents;
            val intent = Intent(this,MainActivity2::class.java)
            intent.putExtra("qr_result",valorQr)
            startActivity(intent)

        }
    }


}
