package br.edu.ifsp.scl.ads.havagas.domain

import java.time.LocalDate

interface Pessoa {
    val nome: String
    val email: String
    val telefone: String
    val tipoFone: TipoTelefone
    val celular : String?
    val sexo: Sexo
    val dataNasc: LocalDate
}

class FunMed(
    override val nome: String,
    override val email: String,
    override val telefone: String,
    override val tipoFone: TipoTelefone,
    override val celular: String?,
    override val sexo: Sexo,
    override val dataNasc: LocalDate,
    val anoFormatura: Int
) : Pessoa{
    override fun toString(): String {
        return "FunMed(nome='$nome', email='$email', telefone='$telefone', tipoFone=$tipoFone, celular=$celular, sexo=$sexo, dataNasc=$dataNasc, anoFormatura=$anoFormatura)"
    }
}

class GraEsp(
    override val nome: String,
    override val email: String,
    override val telefone: String,
    override val tipoFone: TipoTelefone,
    override val celular: String?,
    override val sexo: Sexo,
    override val dataNasc: LocalDate,
    val anoConclusao: Int,
    val instituicao: String
) : Pessoa{
    override fun toString(): String {
        return "GraEsp(nome='$nome', email='$email', telefone='$telefone', tipoFone=$tipoFone, celular=$celular, sexo=$sexo, dataNasc=$dataNasc, anoConclusao=$anoConclusao, instituicao='$instituicao')"
    }
}

class MesDoc(
    override val nome: String,
    override val email: String,
    override val telefone: String,
    override val tipoFone: TipoTelefone,
    override val celular: String?,
    override val sexo: Sexo,
    override val dataNasc: LocalDate,
    val anoConclusao: Int,
    val instituicao: String,
    val monografia: String,
    val orientador: String
): Pessoa{
    override fun toString(): String {
        return "MesDoc(nome='$nome', email='$email', telefone='$telefone', tipoFone=$tipoFone, celular=$celular, sexo=$sexo, dataNasc=$dataNasc, anoConclusao=$anoConclusao, instituicao='$instituicao', monografia='$monografia', orientador='$orientador')"
    }
}










//open class Pessoa (
//    nome: String, email: String, telefone: String,
//    tipoFone: TipoTelefone, celular : String? = null, sexo: Sexo
//) {
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//        return true
//    }
//
//    override fun hashCode(): Int {
//        return javaClass.hashCode()
//    }
//
//}
//
//class FunMed()
