
package com.example.agendaandroid;

import static android.text.TextUtils.isEmpty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agendaandroid.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.btnConsultar.setOnClickListener(v -> abrirTelaConsulta());

        binding.btnGravar.setOnClickListener(v -> inserirRegistro());

        BancoDados.abrirDB(this);
        BancoDados.abrirOuCriarTabela(this);
        BancoDados.fecharDB();


    }
    //método para inserir registro
    public void inserirRegistro() {
        if (isEmpty(binding.etNome.getText())) {//verificando se está vazia o campo
            binding.etNome.setError("Nome não pode estar em branco");
        } else if (isEmpty(binding.etTelefone.getText())) {//verificando se está vazia o campo
            binding.etTelefone.setError("Telefone não pode estar vazio");
        } else {
                BancoDados.inserirRegistro(
                        binding.etNome.getText().toString(),
                        binding.etTelefone.getText().toString(),
                        this);
        }
        limparCampos();
    }

    public void abrirTelaConsulta() {
        Intent it_tela_consulta = new Intent(this, TelaConsulta.class);
        startActivity(it_tela_consulta);
        finish();
    }
    // método limpar campos
    public void limparCampos(){
        binding.etNome.setText(null);
        binding.etTelefone.setText(null);
    }

}