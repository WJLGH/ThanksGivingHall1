package com.example.sxd.thanksgivinghall.finance;

import android.content.Context;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenterImpl;
import com.example.sxd.thanksgivinghall.bean.FinAccSumListEntity;

import java.util.List;

class AccSumPresenterImpl extends BasePresenterImpl implements AccSumContract.Presenter  {

    AccSumContract.Model mModel;
    Context context;
    AccSumContract.View mView;


    public AccSumPresenterImpl(Context context, AccSumActivity mView) {
        this.context = context;
        this.mView = mView;
        this.mModel = new AccSumModelImpl(getBaseUrl(context));
    }

    @Override
    public void request(String id) {
        this.mModel.request(id, new ResultListener<FinAccSumListEntity>() {
            @Override
            public void onEnd() {

            }

            @Override
            public void onFailure(String paramString) {
                mView.showMessage(paramString);
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(FinAccSumListEntity paramT) {
                List<FinAccSumListEntity.Data> data = paramT.getData();
                if (paramT == null || data == null || data.size() == 0) {
                    mView.showMessage("暂无数据");
                    return;
                }
                mView.requestSuccess(data);
            }
        });
    }

    @Override
    public void attachView(AccSumContract.View paramT) {

    }

    @Override
    public void detachView() {

    }
}
