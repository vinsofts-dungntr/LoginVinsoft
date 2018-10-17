package com.login.tridung.loginvinsofts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SuccessActivity extends AppCompatActivity {

    @BindView(R.id.btShowList)
    Button btShowList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btShowList)
    public void onClickShow(View view){
        Intent intent=new Intent(this,CountryActivity.class);
        startActivity(intent);
    }
}
