package com.example.sxd.thanksgivinghall.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.bean.Constants;
import com.example.sxd.thanksgivinghall.bean.FinAccountListEntity;
import com.example.sxd.thanksgivinghall.bean.FinAccountListEntity;

import java.util.List;

public class FinAccountListAdapter extends BaseQuickAdapter<FinAccountListEntity.Data,BaseViewHolder> {


    public FinAccountListAdapter(int layoutResId, List<FinAccountListEntity.Data> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FinAccountListEntity.Data item) {

        helper.setText(R.id.tv_fin_account_acName,item.getAcName());
    }
}
