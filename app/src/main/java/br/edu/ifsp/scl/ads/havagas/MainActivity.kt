package br.edu.ifsp.scl.ads.havagas

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import br.edu.ifsp.scl.ads.havagas.databinding.ActivityMainBinding
import br.edu.ifsp.scl.ads.havagas.domain.*
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {
    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(
            layoutInflater
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        cellPhoneController()
        formacaoSpinnerController()
        saveButtonController()
        resetButtonController()
    }

    private fun resetButtonController() {
        with(activityMainBinding) {
            limparBt.setOnClickListener {
                fullNamePt.text.clear()
                emailPt.text.clear()
                phonePt.text.clear()
                phoneRg.check(comercialRb.id)
                cellNumberPt.text.clear()
                cellNumberSw.isChecked = false
                sexRg.clearCheck()
                dataNascPt.text.clear()
                anoFormaturaPt.text.clear()
                anoConclusaoPt.text.clear()
                instituicaoPt.text.clear()
                monografiaPt.text.clear()
                orientadorPt.text.clear()
                vagasInteressePt.text.clear()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveButtonController() {
        activityMainBinding.salvarBt.setOnClickListener {
            if (!existsErrors()) {
                val pessoa = getPersonFromView();
                val alertDialogBuilder = AlertDialog.Builder(this@MainActivity)
                alertDialogBuilder.setTitle("Informações Cadastradas")
                alertDialogBuilder.setMessage(pessoa.toString())
                alertDialogBuilder.show()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getPersonFromView(): Pessoa? {
        with(activityMainBinding) {
            val nome = fullNamePt.text.toString()
            val email = emailPt.text.toString()
            val telefone = phonePt.text.toString()
            val tipoTelefone =
                if (comercialRb.isChecked) TipoTelefone.COMERCIAL else TipoTelefone.RESIDENCIAL
            val celular = if (cellNumberSw.isChecked) cellNumberPt.text.toString() else null
            val sexo = if (femRb.isChecked) Sexo.FEMININO else Sexo.MASCULINO
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val dataNasc = if(dataNascPt.text.isNotEmpty()) LocalDate.parse(dataNascPt.text.toString(), formatter) else null
            val vagasInteresse = vagasInteressePt.text.toString()

            var pessoa: Pessoa? = null

            when (formacaoSp.selectedItemId.toInt()) {
                0, 1 -> {
                    val anoFormatura = if(anoFormaturaPt.text.isNotEmpty()) anoFormaturaPt.text.toString().toInt() else 0
                    pessoa = FunMed(
                        nome,
                        email,
                        telefone,
                        tipoTelefone,
                        celular,
                        sexo,
                        dataNasc,
                        vagasInteresse,
                        anoFormatura

                    )
                    print(pessoa.toString())
                }
                2, 3 -> {
                    val anoFormatura = if(anoConclusaoPt.text.isNotEmpty()) anoConclusaoPt.text.toString().toInt() else 0
                    val instituicao = instituicaoPt.text.toString()
                    pessoa = GraEsp(
                        nome,
                        email,
                        telefone,
                        tipoTelefone,
                        celular,
                        sexo,
                        dataNasc,
                        vagasInteresse,
                        anoFormatura,
                        instituicao
                    )
                    print(pessoa.toString())
                }
                4, 5 -> {
                    val anoFormatura = anoConclusaoPt.text.toString().toInt()
                    val instituicao = instituicaoPt.text.toString()
                    val monografia = monografiaPt.text.toString()
                    val orientador = orientadorPt.text.toString()
                    pessoa = MesDoc(
                        nome,
                        email,
                        telefone,
                        tipoTelefone,
                        celular,
                        sexo,
                        dataNasc,
                        vagasInteresse,
                        anoFormatura,
                        instituicao,
                        monografia,
                        orientador
                    )
                    print(pessoa.toString())

                }
            }

            return pessoa
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun existsErrors(): Boolean {
        var foundErrors = false
        with(activityMainBinding) {
            if (fullNamePt.text.isEmpty()) {
                fullNamePt.error = "Preencha antes de continuar"
                foundErrors = true
            }
            if (emailPt.text.isEmpty()) {
                emailPt.error = "Preencha antes de continuar"
                foundErrors = true
            }
            if (phonePt.text.isEmpty()) {
                phonePt.error = "Preencha antes de continuar"
                foundErrors = true
            }
            if (cellNumberPt.isVisible && cellNumberPt.text.isEmpty()) {
                cellNumberPt.error = "Preencha antes de continuar"
                foundErrors = true
            }

            val dateFormat = SimpleDateFormat("dd/MM/yyyy")
            try {
                dateFormat.parse(dataNascPt.text.toString().trim())
            } catch (pe: ParseException) {
                dataNascPt.error = "Data invalida"
                foundErrors = true
            }


            if (vagasInteressePt.text.isEmpty()) {
                vagasInteressePt.error = "Preencha antes de continuar"
                foundErrors = true
            }
            if (anoFormaturaPt.isShown && anoFormaturaPt.text.isEmpty()) {
                anoFormaturaPt.error = "Preencha antes de continuar"
                foundErrors = true
            }
            if (anoConclusaoPt.isShown && anoConclusaoPt.text.isEmpty()) {
                anoConclusaoPt.error = "Preencha antes de continuar"
                foundErrors = true
            }
            if (instituicaoPt.isShown && instituicaoPt.text.isEmpty()) {
                instituicaoPt.error = "Preencha antes de continuar"
                foundErrors = true
            }
            if (monografiaPt.isShown && monografiaPt.text.isEmpty()) {
                monografiaPt.error = "Preencha antes de continuar"
                foundErrors = true
            }
            if (orientadorPt.isShown && orientadorPt.text.isEmpty()) {
                orientadorPt.error = "Preencha antes de continuar"
                foundErrors = true
            }

        }
        return foundErrors
    }

    private fun formacaoSpinnerController() {
        activityMainBinding.formacaoSp.onItemSelectedListener =
            object : OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (position) {
                        0, 1 -> {
                            activityMainBinding.funMedLL.visibility = View.VISIBLE
                            activityMainBinding.graEspLL.visibility = View.GONE
                            activityMainBinding.mesDouLL.visibility = View.GONE
                        }
                        2, 3 -> {
                            activityMainBinding.funMedLL.visibility = View.GONE
                            activityMainBinding.graEspLL.visibility = View.VISIBLE
                            activityMainBinding.mesDouLL.visibility = View.GONE
                        }
                        else -> {
                            activityMainBinding.funMedLL.visibility = View.GONE
                            activityMainBinding.graEspLL.visibility = View.VISIBLE
                            activityMainBinding.mesDouLL.visibility = View.VISIBLE
                        }
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //NSA
                }
            }
    }

    private fun cellPhoneController() {
        activityMainBinding.cellNumberSw.setOnCheckedChangeListener { _, isChecked ->
            activityMainBinding.cellNumberPt.visibility =
                (if (isChecked) View.VISIBLE else View.GONE)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("DEBUG", "onSaveInstanceState called")
        val pessoa = getPersonFromView()
        if (pessoa != null) {
            outState.putString("NAME", pessoa.nome)
            outState.putString("EMAIL", pessoa.email)
            outState.putString("PHONE", pessoa.telefone)
            outState.putString("PHONE_TYPE", pessoa.tipoFone.toString())
            outState.putBoolean("HAS_CELLPHONE", activityMainBinding.cellNumberSw.isChecked)
            outState.putString("CELLPHONE", pessoa.celular)
            outState.putString("GENDER", pessoa.sexo.toString())
            outState.putString("BIRTH_DATE", pessoa.dataNasc.toString())
            with(activityMainBinding){
                outState.putString("JOBS_OF_INTEREST", vagasInteressePt.text.toString())
                outState.putString(
                    "EDUCATION_LEVEL",
                    formacaoSp.selectedItem.toString()
                )

                if (pessoa is FunMed)
                    outState.putString("FINISH_YEAR", anoFormaturaPt.text.toString())

                if (pessoa is GraEsp){
                    outState.putString("FINISH_YEAR", anoConclusaoPt.text.toString())
                    outState.putString("INSTITUTION", instituicaoPt.text.toString())
                }
                if (pessoa is MesDoc){
                    outState.putString("FINISH_YEAR", anoConclusaoPt.text.toString())
                    outState.putString("INSTITUTION", instituicaoPt.text.toString())
                    outState.putString("MONOGRAPH_TITLE", monografiaPt.text.toString())
                    outState.putString("ADVISOR", orientadorPt.text.toString())
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("DEBUG", "onRestoreInstanceState called")

        with(activityMainBinding){
            fullNamePt.setText(savedInstanceState.getString("NAME"))
            emailPt.setText(savedInstanceState.getString("EMAIL"))
            phonePt.setText(savedInstanceState.getString("PHONE"))
            if(savedInstanceState.getString("PHONE_TYPE") == "COMERCIAL")
                phoneRg.check(comercialRb.id)
            else
                phoneRg.check(residencialRb.id)
            cellNumberSw.isChecked = savedInstanceState.getBoolean("HAS_CELLPHONE")
            cellNumberPt.setText(savedInstanceState.getString("CELLPHONE"))

            if(savedInstanceState.getString("GENDER") == "FEMININO")
                femRb.isChecked = true
            else
                mascRb.isChecked = true

            if(savedInstanceState.getString("BIRTH_DATE") != "null"){
                val date = LocalDate.parse(savedInstanceState.getString("BIRTH_DATE"))
                val strDate = ""+ date.dayOfMonth + "/" + date.monthValue + "/" + date.year
                dataNascPt.setText(strDate)
            }


            anoFormaturaPt.setText(savedInstanceState.getString("FINISH_YEAR"))
            instituicaoPt.setText(savedInstanceState.getString("INSTITUTION"))
            monografiaPt.setText(savedInstanceState.getString("MONOGRAPH_TITLE"))
            orientadorPt.setText(savedInstanceState.getString("ADVISOR"))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("DEBUG", "onDestroy called")
    }
}