package com.login.tridung.loginvinsofts.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.login.tridung.loginvinsofts.R;
import com.login.tridung.loginvinsofts.model.CountryModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomRecyclerViewAdapter extends
        RecyclerView.Adapter<CustomRecyclerViewAdapter.RecyclerViewHolder> {
    private Context context;
    private List<CountryModel> mList;

    public CustomRecyclerViewAdapter(Context context, ArrayList<CountryModel> list) {
        this.context = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.layout_item_recyclerview, viewGroup, false);
        return new RecyclerViewHolder(view, mList);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.tvNameTrans.setText(mList.get(position).getNameTrans());
        holder.tvNameEn.setText(mList.get(position).getNameEn());
        holder.imIcon.setImageResource(mList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        if (mList.size()>0) {
            return mList.size();
        }
        return 0;
    }

    public void addItem(CountryModel model,int positionStart,int positionCount){
        mList.add(positionCount,model);
        notifyItemRangeInserted(positionStart,positionCount);
    }


    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvNameEn)
        TextView tvNameEn;
        @BindView(R.id.tvNameTrans)
        TextView tvNameTrans;
        @BindView(R.id.imIcon)
        ImageView imIcon;

        public RecyclerViewHolder(@NonNull View itemView, final List<CountryModel> modelList) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
