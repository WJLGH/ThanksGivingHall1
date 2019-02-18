package com.example.sxd.thanksgivinghall.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.bean.Constants;
import com.example.sxd.thanksgivinghall.bean.FinRecordListEntity;

import java.util.List;

public class FinRecordListAdapter extends BaseQuickAdapter<FinRecordListEntity.Data,BaseViewHolder> {

    public FinRecordListAdapter(int layoutResId, @Nullable List<FinRecordListEntity.Data> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FinRecordListEntity.Data item) {

        helper.setText(R.id.tv_fin_record_desc,item.getDescription())
                .setText(R.id.tv_fin_record_note_date,item.getNoteDate());
        String type = item.getReType();
        int f = 1;
        if(Constants.FIN_REOCRD_TYPE_IN.equals(type)) {
            helper.setImageResource(R.id.iv_fin_record_amount_re_type,R.mipmap.in);
        } else if(Constants.FIN_REOCRD_TYPE_OUT.equals(type)) {
            helper.setImageResource(R.id.iv_fin_record_amount_re_type,R.mipmap.out);
            f = -1;
        } else if(Constants.FIN_REOCRD_TYPE_CH.equals(type)) {
            helper.setImageResource(R.id.iv_fin_record_amount_re_type,R.mipmap.exchange);
        }
        helper.setText(R.id.tv_fin_record_amount,String.format("%.2f",f*Math.abs(item.getAmount())));
    }
}
