package com.example.agendaandroid;

import android.app.Activity;

import androidx.appcompat.app.AlertDialog;

public class CxMsg {

    // configurando mensagens de alerta para melhor identificar,
    // agora pode ser chamadas em outra class colocando CxMsg.msg(this,"A mensagem que desejar")
    public static void msg(Activity activity,String txt) {
        AlertDialog.Builder adb = new AlertDialog.Builder(activity);
        adb.setMessage(txt);
        adb.setNeutralButton("OK", null);
        adb.show();
    }
}
