package com.example.agendaandroid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agendaandroid.databinding.ActivityTelaConsultaBinding;

public class TelaConsulta extends AppCompatActivity {

    ActivityTelaConsultaBinding binding;

    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTelaConsultaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        cursor = BancoDados.buscarTodosDados(this);
        if (cursor.getCount() != 0) {
            mostraDados();
        } else {
            CxMsg.msg(this,"Nenhum dados encontrado!");
        }
        binding.btnVoltarConsulta.setOnClickListener(v -> voltarTelaMain());
        binding.btnAnteriorConsulta.setOnClickListener(v -> registroAnterior());
        binding.btnProximoConsulta.setOnClickListener(v -> proximoRegistro());

    }

    @SuppressLint("Range")
    public void mostraDados() {
        binding.etNomeConsulta.setText(cursor.getString(cursor.getColumnIndex("nome")));
        binding.etTelefoneConsulta.setText(cursor.getString(cursor.getColumnIndex("fone")));
    }

    //Buscra proximo registro
    public void proximoRegistro() {
        try {
            cursor.moveToNext();
            mostraDados();
        } catch (Exception e) {
            if (cursor.isAfterLast()) {
                CxMsg.msg(this,"Não existem mais regidtros");
            } else {
                CxMsg.msg(this,"Erro ao navegar pelos registros");
            }
        }
    }

    //buscar registro anterior
    public void registroAnterior() {
        try {
            cursor.moveToPrevious();
            mostraDados();
        } catch (Exception e) {
            if (cursor.isBeforeFirst()) {
                CxMsg.msg(this,"Não existem mais regidtros");
            } else {
                CxMsg.msg(this,"Erro ao navegar pelos registros");
            }
        }

    }

    public void voltarTelaMain() {
        Intent it_tela_main = new Intent(this, MainActivity.class);
        startActivity(it_tela_main);
        finish();
    }
}