package com.example.sxd.thanksgivinghall.finance;

import android.content.Context;
import android.view.View;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenterImpl;
import com.example.sxd.thanksgivinghall.bean.FinRecordListEntity;

import java.util.List;

public class FinRecordListPresenterImpl  extends BasePresenterImpl implements  FinRecordListContract.Presenter {

    FinRecordListContract.View mView;
    FinRecordListContract.Model mModel;
    Context context;

    public FinRecordListPresenterImpl(Context context, FinRecordListContract.View view) {
        this.context = context;
        this.mView = view;
        this.mModel = new FinRecordListModelImpl(getBaseUrl(context));
    }

    @Override
    public void request(String startDate, String endDate) {
        if(startDate  == null || endDate == null) {
            this.mView.showMessage("请输入正确的日期");
            return ;
        }
        this.mModel.request(startDate, endDate, new ResultListener<FinRecordListEntity>() {
            @Override
            public void onEnd() {

            }

            @Override
            public void onFailure(String paramString) {

            }

            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(FinRecordListEntity value) {
                List<FinRecordListEntity.Data> data = null;
                if(value != null) {
                    data = value.getData();
                }
                if(data == null || data.size() == 0 ) {
                    mView.showMessage("暂无数据");
                    return;
                }
                mView.requestSuccess(value);
            }
        });
    }

    @Override
    public void setPresenter(FinRecordListContract.View paramT) {

    }
}
