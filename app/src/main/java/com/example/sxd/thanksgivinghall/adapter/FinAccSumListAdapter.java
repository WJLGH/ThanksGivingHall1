package com.example.sxd.thanksgivinghall.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.bean.FinAccSumListEntity;

import java.util.List;

public class FinAccSumListAdapter extends BaseQuickAdapter<FinAccSumListEntity.Data ,BaseViewHolder> {
    public FinAccSumListAdapter(int layoutResId, @Nullable List<FinAccSumListEntity.Data> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FinAccSumListEntity.Data item) {
        helper.setText(R.id.fin_acc_sum_ac_name,item.getAcName())
                .setText(R.id.fin_acc_sum_in_amount,String.format("%.2f",item.getInAmount()))
                .setText(R.id.fin_acc_sum_out_amount,String.format("%.2f",-Math.abs(item.getOutAmount())))
                .setText(R.id.fin_acc_sum_amount,String.format("%.2f",item.getAmount()));
    }
}
