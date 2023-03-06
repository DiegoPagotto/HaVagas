package br.edu.ifsp.scl.ads.havagas

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import br.edu.ifsp.scl.ads.havagas.databinding.ActivityMainBinding
import br.edu.ifsp.scl.ads.havagas.domain.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {
    private val activityMainBinding: ActivityMainBinding by lazy{ActivityMainBinding.inflate(layoutInflater)}

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
        with(activityMainBinding){
            limparBt.setOnClickListener {
                fullNamePt.text.clear()
                emailPt.text.clear()
                phonePt.text.clear()
                phoneRg.check(comercialRb.id)
                cellNumberPt.text.clear()
                cellNumberSw.isChecked = false
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
                with(activityMainBinding) {
                    val nome = fullNamePt.text.toString()
                    val email = emailPt.text.toString()
                    val telefone = phonePt.text.toString()
                    val tipoTelefone =
                        if (comercialRb.isChecked) TipoTelefone.COMERCIAL else TipoTelefone.RESIDENCIAL
                    val celular = if (cellNumberSw.isChecked) cellNumberPt.text.toString() else null
                    val sexo = if (femRb.isChecked) Sexo.FEMININO else Sexo.MASCULINO
                    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                    val dataNasc = LocalDate.parse(dataNascPt.text.toString(), formatter)
                    val vagasInteresse = vagasInteressePt.text.toString()

                    var pessoa: Pessoa? = null

                    when (formacaoSp.selectedItemId.toInt()) {
                        0, 1 -> {
                            val anoFormatura = anoFormaturaPt.text.toString().toInt()
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
                            val anoFormatura = anoConclusaoPt.text.toString().toInt()
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

                    val alertDialogBuilder = AlertDialog.Builder(this@MainActivity)
                    alertDialogBuilder.setTitle("Informações Cadastradas")
                    alertDialogBuilder.setMessage(pessoa.toString())
                    alertDialogBuilder.show()
                }

            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun existsErrors(): Boolean{
        var foundErrors = false
        with(activityMainBinding){
            if(fullNamePt.text.isEmpty()) {
                fullNamePt.error = "Preencha antes de continuar"
                foundErrors = true
            }
            if(emailPt.text.isEmpty()) {
                emailPt.error = "Preencha antes de continuar"
                foundErrors = true
            }
            if(phonePt.text.isEmpty()) {
                phonePt.error = "Preencha antes de continuar"
                foundErrors = true
            }
            if(cellNumberPt.isVisible && cellNumberPt.text.isEmpty()){
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


            if(vagasInteressePt.text.isEmpty()) {
                vagasInteressePt.error = "Preencha antes de continuar"
                foundErrors = true
            }
            if(anoFormaturaPt.isShown && anoFormaturaPt.text.isEmpty()){
                anoFormaturaPt.error = "Preencha antes de continuar"
                foundErrors = true
            }
            if(anoConclusaoPt.isShown && anoConclusaoPt.text.isEmpty()){
                anoConclusaoPt.error = "Preencha antes de continuar"
                foundErrors = true
            }
            if(instituicaoPt.isShown && instituicaoPt.text.isEmpty()){
                instituicaoPt.error = "Preencha antes de continuar"
                foundErrors = true
            }
            if(monografiaPt.isShown && monografiaPt.text.isEmpty()){
                monografiaPt.error = "Preencha antes de continuar"
                foundErrors = true
            }
            if(orientadorPt.isShown && orientadorPt.text.isEmpty()){
                orientadorPt.error = "Preencha antes de continuar"
                foundErrors = true
            }

        }
        return foundErrors
    }

    private fun formacaoSpinnerController() {
        activityMainBinding.formacaoSp.onItemSelectedListener =
            object : OnItemSelectedListener{
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

    private fun cellPhoneController(){
        activityMainBinding.cellNumberSw.setOnCheckedChangeListener { _, isChecked ->
            activityMainBinding.cellNumberPt.visibility = (if (isChecked) View.VISIBLE else View.GONE)
        }
    }
}