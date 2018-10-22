package com.login.tridung.loginvinsofts.adapter;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.login.tridung.loginvinsofts.R;
import com.login.tridung.loginvinsofts.model.CountryModel;
import com.login.tridung.loginvinsofts.utils.ConstantUtils;
import com.login.tridung.loginvinsofts.utils.DialogShowIcon;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomRecyclerViewAdapter extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CountryModel> mList;
    ILoadMore loadMore;
    boolean isLoading;
    int visiableThread=6;
    Activity activity;

    public static OnClickItemRecylerView mOnClickItemRecylerView;

    public CustomRecyclerViewAdapter(RecyclerView recyclerView,List<CountryModel> mList, Activity activity) {
        this.mList = mList;
        this.activity=activity;
        loadMore= (ILoadMore) activity;
            if(!ConstantUtils.isSwitch){
                final LinearLayoutManager linearLayoutManager= (LinearLayoutManager) recyclerView.getLayoutManager();
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        int totalItemCount = linearLayoutManager.getItemCount();
                        int visiableItem = linearLayoutManager.findLastVisibleItemPosition();
                        if (!isLoading && totalItemCount <= (visiableItem + visiableThread)) {
                            if (loadMore != null) {
                                loadMore.onLoadMore();
                                isLoading = true;
                            }
                        }
                    }
                });
            }else {
                final GridLayoutManager gridLayoutManager= (GridLayoutManager) recyclerView.getLayoutManager();
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        int totalItemCount = gridLayoutManager.getItemCount();
                        int visiableItem = gridLayoutManager.findLastCompletelyVisibleItemPosition();
                        if (!isLoading && totalItemCount <= (visiableItem + visiableThread)) {
                            if (loadMore != null) {
                                loadMore.onLoadMore();
                                isLoading = true;
                            }
                        }
                    }
                });
            }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if(viewType==ConstantUtils.VIEW_TYPE_ITEM){
            View view=LayoutInflater.from(activity).inflate(R.layout.layout_item_recyclerview,viewGroup,false);
            return new RecyclerViewHolder(view,mList);
        }else if(viewType==ConstantUtils.VIEW_TYPE_LOADING){
            View view=LayoutInflater.from(activity).inflate(R.layout.layout_item_loadmore,viewGroup,false);
            return new LoadingHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position)==null ? ConstantUtils.VIEW_TYPE_LOADING : ConstantUtils.VIEW_TYPE_ITEM;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof RecyclerViewHolder){
            CountryModel model=mList.get(i);
            RecyclerViewHolder recyclerViewHolder= (RecyclerViewHolder) viewHolder;
            recyclerViewHolder.bindData(model,i);

        }else if(viewHolder instanceof LoadingHolder){
            LoadingHolder loadingHolder= (LoadingHolder) viewHolder;
            loadingHolder.proLoadMore.setIndeterminate(true);
        }
    }

    public void setLoading() {
        isLoading = false;
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface OnClickItemRecylerView {
        void onItemClick(int pos);
        void onClickImage();
        void onClickNameEn(String nameEn);
        void onClickNameTrans(String nameTrans);
    }


    public void setmOnClickItemRecylerView(OnClickItemRecylerView onClickItemRecylerView) {
        mOnClickItemRecylerView = onClickItemRecylerView;
    }


    public void setLoadMore(ILoadMore loadMore) {
        this.loadMore = loadMore;
    }

    public void addItem(CountryModel model, int positionStart, int positionCount) {
        mList.add(positionCount, model);
        notifyItemRangeInserted(positionStart, positionCount);
    }

    public void removeData(List<CountryModel> models){
        models.clear();
        notifyDataSetChanged();
    }


    public static class LoadingHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.proLoadMore)
        ProgressBar proLoadMore;

        public LoadingHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tvNameEn)
        TextView tvNameEn;
        @BindView(R.id.tvNameTrans)
        TextView tvNameTrans;
        @BindView(R.id.imIcon)
        ImageView imIcon;
        private List<CountryModel> mList;
        @BindView(R.id.lnItem)
        LinearLayout linearLayout;

        public RecyclerViewHolder(@NonNull View itemView,List<CountryModel> modelList) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tvNameEn.setOnClickListener(this);
            tvNameTrans.setOnClickListener(this);
            imIcon.setOnClickListener(this);
            mList = modelList;
            linearLayout.setOnClickListener(this);
        }

        public void bindData(CountryModel countryModel,int pos) {
            tvNameTrans.setText(countryModel.getNameTrans());
            tvNameEn.setText(countryModel.getNameEn());
            imIcon.setImageResource(countryModel.getImage());
            if(pos%2==0){
                linearLayout.setBackgroundResource(R.color.colorAccent);
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tvNameEn: {
                    mOnClickItemRecylerView.onClickNameEn(mList.get(getAdapterPosition()).getNameEn());
                    break;
                }
                case R.id.tvNameTrans:{
                    mOnClickItemRecylerView.onClickNameTrans(mList.get(getAdapterPosition()).getNameTrans());
                    break;
                }
                case R.id.imIcon:{
                    mOnClickItemRecylerView.onClickImage();
                    DialogShowIcon.showIcon(v.getContext(),mList.get(getAdapterPosition()).getImage());
                    break;
                }
                case R.id.lnItem:{
                    mOnClickItemRecylerView.onItemClick(getAdapterPosition());
                }

            }
        }

    }
}
