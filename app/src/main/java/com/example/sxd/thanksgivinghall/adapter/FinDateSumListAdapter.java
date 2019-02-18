package com.example.sxd.thanksgivinghall.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.bean.FinDateSumListEntity;

import java.util.List;


public class FinDateSumListAdapter extends BaseQuickAdapter<FinDateSumListEntity.Data,BaseViewHolder> {

    String mainDate;
    public FinDateSumListAdapter(int layoutResId, @Nullable List<FinDateSumListEntity.Data> data,String mainDate) {
        super(layoutResId, data);
        this.mainDate = mainDate;
    }

    @Override
    protected void convert(BaseViewHolder helper, FinDateSumListEntity.Data item) {
        helper.setText(R.id.fin_date_sum_item_amount,String.format("%.2f",item.getAmount()))
                .setText(R.id.fin_date_sum_item_in_amount,String.format("%.2f",item.getInAmount()))
                .setText(R.id.fin_date_sum_item_out_amount,String.format("%.2f",-Math.abs(item.getOutAmount())))
                .setText(R.id.fin_date_sum_item_date,item.getTime(mainDate))
                .setText(R.id.fin_date_sum_item_year,item.getYear());

    }
}
