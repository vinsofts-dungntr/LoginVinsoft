package com.login.tridung.loginvinsofts;



import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.login.tridung.loginvinsofts.adapter.CustomRecyclerViewAdapter;
import com.login.tridung.loginvinsofts.model.CountryModel;
import com.login.tridung.loginvinsofts.utils.DialogShowIcon;
import com.login.tridung.loginvinsofts.utils.ToastUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CountryActivity extends AppCompatActivity implements CustomRecyclerViewAdapter.OnClickItemRecylerView {
    public static final int TIME_DELAYED = 2500;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.imAdd)
    ImageView imAdd;
    @BindView(R.id.btSwitch)
    Button btSwitch;
    SwipeRefreshLayout refreshLayout;
    CustomRecyclerViewAdapter adapter;
    List<CountryModel> mList=new ArrayList<>();
    boolean switchView=true;
    LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        ButterKnife.bind(this);
        initView();
        onItemMove();
        onListRefresh();
        loadMoreData();
    }

    public void initView(){
        if(switchView){
            manager = new LinearLayoutManager(getApplicationContext());
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(manager);
        }else {
            GridLayoutManager manager=new GridLayoutManager(getApplicationContext(),2);
            manager.setSpanCount(2);
            recyclerView.setLayoutManager(manager);
            adapter.notifyDataSetChanged();
        }
        adapter = new CustomRecyclerViewAdapter(recyclerView,getDataFake(), CountryActivity.this);
        recyclerView.setAdapter(adapter);
        adapter.setmOnClickItemRecylerView(this);

        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.custom_divinder);
        divider.setDrawable(drawable);
        recyclerView.addItemDecoration(divider);
    }

    private List<CountryModel> getDataFake() {

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
    }

    public void showDialogAdd() {
        final Dialog dialog = new Dialog(this, R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.layout_dialog);
        dialog.setTitle("Điền thông tin");
        Button btOK = dialog.findViewById(R.id.btChoseIm);
        btOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText edEn = dialog.findViewById(R.id.edNameEns);
                EditText edTran = dialog.findViewById(R.id.edNameTrans);
                String en = edEn.getText().toString();
                String vn = edTran.getText().toString();

                CountryModel model = new CountryModel(en, vn, R.drawable.vietnam);

                adapter.addItem(model, mList.size(), 0);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void onListRefresh() {
        refreshLayout = findViewById(R.id.swapRV);
        refreshLayout.setColorSchemeResources(R.color.background, R.color.backSuccess);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                        adapter.removeData(mList);
                        getDataFake();
                        adapter.notifyDataSetChanged();

                    }
                }, TIME_DELAYED);
            }
        });

    }

    public void onItemMove() {
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder dragged,
                                  @NonNull RecyclerView.ViewHolder target) {
                int position_draged = dragged.getAdapterPosition();
                int position_target = target.getAdapterPosition();

                Collections.swap(mList, position_draged, position_target);
                adapter.notifyItemMoved(position_draged, position_target);
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                removeItem(viewHolder.getAdapterPosition());
                ToastUtils.showToast(getApplicationContext(), "Đã xóa" );
            }
        });

        helper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onItemClick(int pos) {
        ToastUtils.showToast(CountryActivity.this, "Item Click");
    }

    @Override
    public void onClickImage() {
        ToastUtils.showToast(CountryActivity.this, "click image");

    }

    @Override
    public void onClickNameEn(String nameEn) {
        ToastUtils.showToast(CountryActivity.this, nameEn);
    }

    @Override
    public void onClickNameTrans(String nameTrans) {
        ToastUtils.showToast(CountryActivity.this, nameTrans);
    }


    public void loadMoreData(){
        adapter.setLoadMore(new ILoadMore() {
            @Override
            public void onLoadMore() {
                if (mList.size() <= 50) {
                    mList.add(null);
                    adapter.notifyItemInserted(mList.size()-1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mList.remove(mList.size()-1);
                            adapter.notifyItemRemoved(mList.size());
                            int index=mList.size();
                            int end=index+8;
                            for(int i=index;i<=end;i++){
                                mList.add(new CountryModel("Afghanistan", "Cộng hòa Hồi giáo Afghanistan",
                                        R.drawable.afghanistan));
                            }
                            adapter.notifyDataSetChanged();
                            adapter.setLoading();
                        }
                    },3000);
                }else {
                    ToastUtils.showToast(CountryActivity.this,"Load Data Completed");
                }
            }
        });
    }

    @OnClick({R.id.btSwitch,R.id.imAdd})
    public void onClickSwitch(View view){
        switch (view.getId()){
            case R.id.btSwitch:{
                switchView=!switchView;
                initView();
                break;
            }
            case R.id.imAdd:{
                showDialogAdd();
                break;
            }
        }
    }
}
