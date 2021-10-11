package com.example.projectmvp.helpers;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectmvp.MainActivity;
import com.example.projectmvp.R;

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {
String string;
    public MainActivity c;
    public Dialog d;
    public Button buttonTakeSurvey, buttonExitApp;

    public CustomDialogClass(MainActivity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        buttonTakeSurvey = (Button) findViewById(R.id.btn_take_survey);
        buttonExitApp = (Button) findViewById(R.id.btn_exit_app);
        buttonTakeSurvey.setOnClickListener(this);
        buttonExitApp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_take_survey:
                c.finish();
                break;
            case R.id.btn_exit_app:
                dismiss();
                c.finish();
                break;
            default:
                break;
        }
        dismiss();
    }
}
