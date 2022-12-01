package com.example.agendaandroid;


import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancoDados {

    static SQLiteDatabase db = null;//chamando banco de dados
    static Cursor cursor;

    //método abrir  o banco de dados
    public static void abrirDB(Activity activity) {

        ContextWrapper contextWrapper = new ContextWrapper(activity);

        try {
            db = contextWrapper.openOrCreateDatabase("bancoAgenda", MODE_PRIVATE, null);
        } catch (Exception e) {
            CxMsg.msg(activity, "Erro ao abrir ou criar o banco de dados!");
        }/* finally {
            msg("Banco de dados aberto");
        }*/

    }

    // método de abrir ou criar tabela
    public static void abrirOuCriarTabela(Activity activity) {
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS contatos(id INTEGER PRIMARY KEY, nome TEXT, fone TEXT);");
        } catch (Exception e) {
            CxMsg.msg(activity, "Erro ao criar tabela!");
        }
    }

    //método de fechar o banco de dados
    public static void fecharDB() {
        db.close();
        // msg("Fechando banco de dados");
    }

    //método para inserir registro
    public static void inserirRegistro(String nome, String fone, Activity activity) {
        abrirDB(activity);
        try {
            db.execSQL("insert into contatos (nome,fone) values('" + nome + "','" + fone + "')");
        } catch (Exception e) {
            CxMsg.msg(activity, "Erro ao inserir Registro");
        } finally {
            CxMsg.msg(activity, "Registro inserido com sucesso");
        }
        fecharDB();

    }

    //método para buscar registro
    public static Cursor buscarTodosDados(Activity activity) {
        abrirDB(activity);
        cursor = db.query("contatos",
                new String[]{"nome", "fone"},
                null,
                null,
                null,
                null,
                null,
                null
        );
        cursor.moveToFirst();
        return cursor;
    }

}
