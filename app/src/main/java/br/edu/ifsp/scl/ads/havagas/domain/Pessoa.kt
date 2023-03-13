package br.edu.ifsp.scl.ads.havagas.domain

import java.time.LocalDate

interface Pessoa {
    val nome: String
    val email: String
    val telefone: String
    val tipoFone: TipoTelefone
    val celular : String?
    val sexo: Sexo
    val dataNasc: LocalDate?
    val vagasInteresse : String
}

class FunMed(
    override val nome: String,
    override val email: String,
    override val telefone: String,
    override val tipoFone: TipoTelefone,
    override val celular: String?,
    override val sexo: Sexo,
    override val dataNasc: LocalDate?,
    override val vagasInteresse : String,
    val anoFormatura: Int
) : Pessoa{
    override fun toString(): String {
        val sb = java.lang.StringBuilder()
        sb.append("Nome: $nome\n")
        sb.append("Email: $email\n")
        sb.append("Telefone: $telefone ($tipoFone)\n")
        sb.append("Celular: $celular\n")
        sb.append("Sexo: $sexo\n")
        sb.append("Nascimento: $dataNasc\n")
        sb.append("Ano Formatura: $anoFormatura\n")
        sb.append("Vagas de Interesse: $vagasInteresse\n")

        return sb.toString()
    }
}

class GraEsp(
    override val nome: String,
    override val email: String,
    override val telefone: String,
    override val tipoFone: TipoTelefone,
    override val celular: String?,
    override val sexo: Sexo,
    override val dataNasc: LocalDate?,
    override val vagasInteresse : String,
    val anoConclusao: Int,
    val instituicao: String
) : Pessoa{
    override fun toString(): String {
        val sb = java.lang.StringBuilder()
        sb.append("Nome: $nome \n")
        sb.append("Email: $email\n")
        sb.append("Telefone: $telefone ($tipoFone)\n")
        sb.append("Celular: $celular\n")
        sb.append("Sexo: $sexo\n")
        sb.append("Nascimento: $dataNasc\n")
        sb.append("Ano Conclusão: $anoConclusao\n")
        sb.append("Instituição: $instituicao\n")
        sb.append("Vagas de Interesse: $vagasInteresse\n")

        return sb.toString()
    }
}

class MesDoc(
    override val nome: String,
    override val email: String,
    override val telefone: String,
    override val tipoFone: TipoTelefone,
    override val celular: String?,
    override val sexo: Sexo,
    override val dataNasc: LocalDate?,
    override val vagasInteresse : String,
    val anoConclusao: Int,
    val instituicao: String,
    val monografia: String,
    val orientador: String
): Pessoa{
    override fun toString(): String {
        val sb = java.lang.StringBuilder()
        sb.append("Nome: $nome\n")
        sb.append("Email: $email\n")
        sb.append("Telefone: $telefone ($tipoFone)\n")
        sb.append("Celular: $celular\n")
        sb.append("Sexo: $sexo\n")
        sb.append("Nascimento: $dataNasc\n")
        sb.append("Ano Conclusão: $anoConclusao\n")
        sb.append("Instituição: $instituicao\n")
        sb.append("Título da Monografia: $monografia\n")
        sb.append("Orientador: $orientador\n")
        sb.append("Vagas de Interesse: $vagasInteresse\n")

        return sb.toString()
    }
}
