package com.example.projectmvp.helpers;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.projectmvp.MainActivity;
import com.example.projectmvp.R;
import com.example.projectmvp.TestMainActivity;

public class TestCustomDialogClass extends Dialog implements
        View.OnClickListener {
String string;
    public TestMainActivity c;
    public Dialog d;
    public Button buttonTakeSurvey, buttonExitApp;

    public TestCustomDialogClass(TestMainActivity a) {
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
