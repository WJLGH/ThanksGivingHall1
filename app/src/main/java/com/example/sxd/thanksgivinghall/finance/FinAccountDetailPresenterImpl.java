package com.example.sxd.thanksgivinghall.finance;

import android.content.Context;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenterImpl;
import com.example.sxd.thanksgivinghall.bean.FinAccountDetailEntity;

public class FinAccountDetailPresenterImpl extends BasePresenterImpl implements FinAccountDetailContract.Presenter {
    FinAccountDetailContract.View mView;
    FinAccountDetailContract.Model mModel;
    Context context;

    public FinAccountDetailPresenterImpl(Context context, FinAccountDetailActivity view) {
        this.mView = view;
        this.context = context;
        this.mModel = new FinAccountDetailModelImpl(getBaseUrl(context));
    }
    @Override
    public void request(String recordId) {
        this.mModel.request(recordId, new ResultListener<FinAccountDetailEntity>() {
            @Override
            public void onEnd() {

            }

            @Override
            public void onSuccess(FinAccountDetailEntity data) {
                if (data != null) {
                    if(data.getStatusMessage().equals("ok")) {
                        mView.requestSuccess(data);
                    }else{
                        mView.showMessage(data.getStatusMessage());
                    }
                } else {
                    mView.showMessage(context.getString(R.string.login_activity_loginfail_toast));
                }
            }

            @Override
            public void onFailure(String message) {
                mView.showMessage(context.getString(R.string.login_activity_loginfail_toast));
            }

            @Override
            public void onStart() {

            }
        });
    }

    @Override
    public void attachView(FinAccountDetailContract.View paramT) {

    }

    @Override
    public void detachView() {

    }
}
