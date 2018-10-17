package com.login.tridung.loginvinsofts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.login.tridung.loginvinsofts.utils.ConstantUtils;
import com.login.tridung.loginvinsofts.utils.DialogUtils;
import com.login.tridung.loginvinsofts.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SightUpActivity extends AppCompatActivity {

    @BindView(R.id.edName)
    EditText mEdt_name;
    @BindView(R.id.edEmail)
    EditText mEdt_email;
    @BindView(R.id.edPassSighUp)
    EditText mEdt_pass;
    @BindView(R.id.edPhone)
    EditText mEdt_phone;
    @BindView(R.id.tvAccout)
    TextView mTv_haveAcout;
    @BindView(R.id.btSucces)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sight_up);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btSucces,R.id.tvAccout})
    public void onClickView(View view){
        switch (view.getId()){
            case R.id.btSucces:{
                sighUp();
                break;
            }
            case R.id.tvAccout:{
                finish();
                break;
            }
        }
    }

    public void sighUp(){
        String name = mEdt_name.getText().toString().trim();
        String mail = mEdt_email.getText().toString().trim();
        String pass = mEdt_pass.getText().toString().trim();
        String phone = mEdt_phone.getText().toString().trim();

        if (name.equals("") || mail.equals("") || pass.equals("") || phone.equals("")) {
            DialogUtils.showDialog(this,getString(R.string.error),getString(R.string.title));
        } else {

            ToastUtils.showToast(this,getString(R.string.sightup_succes));
            Intent intent=new Intent();
            intent.putExtra(ConstantUtils.EMAIL,mail);
            intent.putExtra(ConstantUtils.PASSWORLD,pass);
            setResult(RESULT_OK,intent);
            finish();
        }
    }
}
