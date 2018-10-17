package com.login.tridung.loginvinsofts;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.login.tridung.loginvinsofts.adapter.CustomRecyclerViewAdapter;
import com.login.tridung.loginvinsofts.model.AddItemModel;
import com.login.tridung.loginvinsofts.model.CountryModel;
import com.login.tridung.loginvinsofts.utils.DialogAddItem;
import com.login.tridung.loginvinsofts.utils.DialogUtils;
import com.login.tridung.loginvinsofts.utils.RecyclerItemClickListener;
import com.login.tridung.loginvinsofts.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CountryActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.imAdd)
    ImageView imAdd;
    CustomRecyclerViewAdapter adapter;
    List<CountryModel> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        ButterKnife.bind(this);

        adapter = new CustomRecyclerViewAdapter(CountryActivity.this, (ArrayList<CountryModel>) getDataFake());

        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.custom_divinder);
        divider.setDrawable(drawable);
        recyclerView.addItemDecoration(divider);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(),
                recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.showToast(CountryActivity.this, "chọn" + position);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                removeItem(position);
                DialogUtils.showDialog(CountryActivity.this, "Thông báo", "Đã xóa");
            }
        }));

        imAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAdd();
            }
        });

    }

    private List<CountryModel> getDataFake() {
        mList = new ArrayList<CountryModel>();
        mList.add(new CountryModel("Afghanistan", "Cộng hòa Hồi giáo Afghanistan",
                R.drawable.afghanistan));
        mList.add(new CountryModel("Albania", "Cộng hòa Albania", R.drawable.albania));
        mList.add(new CountryModel("Algérie", "Cộng hòa Dân chủ Nhân dân Algérie", R.drawable.algeria));
        mList.add(new CountryModel("Argentina", "Cộng hòa Argentina", R.drawable.argentina));
        mList.add(new CountryModel("Egypt", "Ai Cập", R.drawable.egypt));
        mList.add(new CountryModel("Angola", "Cộng hòa Angola", R.drawable.angola));
        mList.add(new CountryModel("Andorra", "Công quốc Andorra", R.drawable.andorra));
        mList.add(new CountryModel("Korean", "Hàn Quốc", R.drawable.korea));
        mList.add(new CountryModel("Japan", "Nhật bản", R.drawable.jav));
        mList.add(new CountryModel("Malaysia", "Ma lai xi a", R.drawable.malaysia));
        mList.add(new CountryModel("American", "Mỹ", R.drawable.us));
        mList.add(new CountryModel("VietNam", "Việt Nam", R.drawable.vietnam));
        return mList;
    }

    public void removeItem(int position) {
        mList.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, mList.size());
    }

    public void showDialogAdd(){
        final Dialog dialog=new Dialog(this,R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.layout_dialog);
        dialog.setTitle("Điền thông tin");
        Button btOK=dialog.findViewById(R.id.btChoseIm);
        btOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText edEn=dialog.findViewById(R.id.edNameEns);
                EditText edTran=dialog.findViewById(R.id.edNameTrans);
                String en=edEn.getText().toString();
                String vn=edTran.getText().toString();

                CountryModel model=new CountryModel(en,vn,R.drawable.vietnam);

                adapter.addItem(model,mList.size(),0);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}
