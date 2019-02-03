package com.example.sxd.thanksgivinghall.finance;

import android.content.Context;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenterImpl;
import com.example.sxd.thanksgivinghall.bean.FinRecordDetailEntity;
import com.example.sxd.thanksgivinghall.bean.ToDoNotifyDetailEntity;

public class FinRecordDetailPresenterImpl extends BasePresenterImpl implements FinRecordDetailContract.Presenter {
    FinRecordDetailContract.View mView;
    FinRecordDetailContract.Model mModel;
    Context context;

    public FinRecordDetailPresenterImpl(Context context,FinRecordDetailContract.View view) {
        this.mView = view;
        this.context = context;
        this.mModel = new FinRecordDetailModelImpl(getBaseUrl(context));
    }
    @Override
    public void request(String recordId) {
        this.mModel.request(recordId, new ResultListener<FinRecordDetailEntity>() {
            @Override
            public void onEnd() {

            }

            @Override
            public void onSuccess(FinRecordDetailEntity data) {
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
    public void attachView(FinRecordDetailContract.View paramT) {

    }

    @Override
    public void detachView() {

    }
}
