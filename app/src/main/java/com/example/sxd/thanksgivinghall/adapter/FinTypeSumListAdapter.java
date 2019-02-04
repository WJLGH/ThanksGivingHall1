package com.example.sxd.thanksgivinghall.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.bean.FinTypeSumListEntity;

import java.util.List;

public class FinTypeSumListAdapter extends BaseQuickAdapter<FinTypeSumListEntity.Data,BaseViewHolder> {
    public FinTypeSumListAdapter(int layoutResId, @Nullable List<FinTypeSumListEntity.Data> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FinTypeSumListEntity.Data item) {
        helper.setText(R.id.fin_type_item_bus_type,item.getBusType())
                .setText(R.id.fin_type_item_amount,String.format("%.2f",item.getAmount()));
    }
}
