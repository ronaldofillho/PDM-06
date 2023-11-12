package com.example.myapplication

import android.os.Parcel
import android.os.Parcelable
import java.text.SimpleDateFormat

class Password : Parcelable {
    var id: Int
    var descricao: String
    var senha: String
    var dataCri: Long
    var dataAtt: Long

    constructor(descricao: String, senha: String) {
        this.id = -1
        this.descricao = descricao
        this.senha = senha
        this.dataCri = -1
        this.dataAtt = -1
    }

    constructor(id: Int, descricao: String, senha: String, dataCri: Long, dataAtt: Long) {
        this.id = id
        this.descricao = descricao
        this.senha = senha
        this.dataCri = dataCri
        this.dataAtt = dataAtt
    }

    override fun toString(): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        return "Tamanho: ${id}, Descrição: ${descricao}, Senha: ${senha} ${sdf.format(dataCri)} ${sdf.format(dataAtt)}"
    }

    fun gerarSenha(tamanho: Int, maiusculo: Boolean, numero: Boolean, especial: Boolean): String {
        val password = StringBuilder()
        val listaCaracteres = StringBuilder()
        val minuscula = "abcdefghijklmnopqrstuvwxyz"
        val maiuscula = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val numeros = "0123456789"
        val especiais = "!@#/$%^&*()_+-=[]{}|;:,.<>?"
        var digSenha = tamanho

        listaCaracteres.append(minuscula)
        if (maiusculo) {
            listaCaracteres.append(maiuscula)
            val randomIndex = (maiuscula.indices).random()
            val randomCaracteres = maiuscula[randomIndex]
            password.append(randomCaracteres)
            digSenha -= 1
        }

        if (numero) {
            listaCaracteres.append(numeros)
            val randomIndex = (numeros.indices).random()
            val randomCaracteres = numeros[randomIndex]
            password.append(randomCaracteres)
            digSenha -= 1
        }
        if (especial) {
            listaCaracteres.append(especiais)
            val randomIndex = (especiais.indices).random()
            val randomCaracteres = especiais[randomIndex]
            password.append(randomCaracteres)
            digSenha -= 1
        }

        repeat(digSenha) {
            val randomIndex = (listaCaracteres.indices).random()
            val randomCaracteres = listaCaracteres[randomIndex]
            password.append(randomCaracteres)
        }

        val charArray = password.toString().toCharArray()
        charArray.shuffle()
        senha = String(charArray)
        return senha
    }

    fun verificarTamanho(): Int {
        return senha.length
    }

    fun verificarMaiusculo(): Boolean {
        this.senha.forEach {
            if (it.isUpperCase())
                return true
        }
        return false
    }

    fun verificarNumero(): Boolean {
        this.senha.forEach {
            if (it.isDigit())
                return true
        }
        return false
    }

    fun verificarEspecial(): Boolean {
        this.senha.forEach {
            if (!(it.isLetter() || it.isDigit()))
                return true
        }
        return false
    }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readLong(),
        parcel.readLong()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(descricao)
        parcel.writeString(senha)
        parcel.writeLong(dataCri)
        parcel.writeLong(dataAtt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Password> {
        override fun createFromParcel(parcel: Parcel): Password {
            return Password(parcel)
        }

        override fun newArray(size: Int): Array<Password?> {
            return arrayOfNulls(size)
        }
    }
}