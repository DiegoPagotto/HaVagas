package br.edu.ifsp.scl.ads.havagas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import br.edu.ifsp.scl.ads.havagas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val activityMainBinding: ActivityMainBinding by lazy{ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)


        activityMainBinding.cellNumberSw.setOnCheckedChangeListener { buttonView, isChecked ->
            activityMainBinding.cellNumberPt.visibility = (if (isChecked) View.VISIBLE else View.GONE);
        }

        activityMainBinding.formacaoSp.onItemSelectedListener =
            object : OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if(position == 0 || position == 1){
                        activityMainBinding.funMedLL.visibility = View.VISIBLE
                        activityMainBinding.graEspLL.visibility = View.GONE
                        activityMainBinding.mesDouLL.visibility = View.GONE
                    } else if(position == 2 || position == 3){
                        activityMainBinding.funMedLL.visibility = View.GONE
                        activityMainBinding.graEspLL.visibility = View.VISIBLE
                        activityMainBinding.mesDouLL.visibility = View.GONE
                    } else{
                        activityMainBinding.funMedLL.visibility = View.GONE
                        activityMainBinding.graEspLL.visibility = View.VISIBLE
                        activityMainBinding.mesDouLL.visibility = View.VISIBLE
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //NSA
                }
            }
    }
}