package com.login.tridung.loginvinsofts;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edUsername)
    EditText edUsername;
    @BindView(R.id.edPassword)
    EditText edPassword;
    @BindView(R.id.btLogin)
    Button btLogin;
    @BindView(R.id.btSighUp)
    Button btSighUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getData();
    }

    @OnClick({R.id.btLogin,R.id.btSighUp})
    public void onClickView(View view){
        switch (view.getId()){
            case R.id.btLogin:{
                login();
                break;
            }
            case R.id.btSighUp:{
                sightUp();
                break;
            }
        }
    }

    protected void sharePreferent() {
        String email = edUsername.getText().toString().trim();
        String pass = edPassword.getText().toString().trim();
        SharedPreferences sharedPreferences = getSharedPreferences(ConstantUtils.LOGIN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ConstantUtils.USERNAME, email);
        editor.putString(ConstantUtils.PASS, pass);
        editor.apply();
    }

    public void getData() {
        SharedPreferences sharedPreferences = getSharedPreferences(ConstantUtils.LOGIN,
                Context.MODE_PRIVATE);
        String use = sharedPreferences.getString(ConstantUtils.USERNAME, "");
        String pass = sharedPreferences.getString(ConstantUtils.PASS, "");
        if(!use.equals("")||!pass.equals("")){
            edUsername.setText(use);
            edPassword.setText(pass);
        }
        edUsername.setSelection(use.length());
        edPassword.setSelection(pass.length());
    }

    public void login(){
        String user = edUsername.getText().toString().trim();
        String pass = edPassword.getText().toString().trim();
        if (user.equals("") || pass.equals("")) {
            DialogUtils.showDialog(this,getString(R.string.error),getString(R.string.title));
        } else {
            DialogUtils.showDialog(this,getString(R.string.success),getString(R.string.login_success));
            sharePreferent();
        }
    }

    public void sightUp(){
        Intent intent = new Intent(MainActivity.this, SightUpActivity.class);
        startActivityForResult(intent,ConstantUtils.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ConstantUtils.REQUEST_CODE && resultCode == RESULT_OK && data!=null){
            String username =  data.getStringExtra(ConstantUtils.EMAIL);
            String password =  data.getStringExtra(ConstantUtils.PASSWORLD);
            edUsername.setText(username);
            edPassword.setText(password);
            edUsername.setSelection(username.length());
            edPassword.setSelection(password.length());
        }
    }
}
