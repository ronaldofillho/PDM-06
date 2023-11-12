package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class SenhaAdapter(context: Context, resource: Int, objects: List<Password>) :
    ArrayAdapter<Password>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val convertView =
            convertView ?: LayoutInflater.from(context).inflate(R.layout.listagem, parent, false)

        val descricaoTextView: TextView = convertView.findViewById(R.id.descricaoTextView)
        val tamanhoTextView: TextView = convertView.findViewById(R.id.tamanhoTextView)

        val senha = getItem(position)
        descricaoTextView.text = senha?.descricao
        tamanhoTextView.text = "(${senha?.verificarTamanho()})"
        return convertView
    }
}
