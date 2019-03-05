package com.example.sxd.thanksgivinghall.finance;

import android.content.Context;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenterImpl;
import com.example.sxd.thanksgivinghall.bean.FinAccountListEntity;

import java.util.List;

public class FinAccountListPresenterImpl extends BasePresenterImpl implements  FinAccountListContract.Presenter {

    FinAccountListContract.View mView;
    FinAccountListContract.Model mModel;
    Context context;
    MyResultLisener mResultLisener = new MyResultLisener();




    @Override
    public void requestAccountList() {
        this.mModel.requestAccountList(mResultLisener);
    }

    @Override
    public void deleteById(String id) {
        this.mModel.deleteById(id,mResultLisener);
    }

    private class MyResultLisener implements ResultListener<FinAccountListEntity> {
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
        public void onSuccess(FinAccountListEntity value) {
            List<FinAccountListEntity.Data> data = null;
            if(value != null) {
                data = value.getData();
            }
            if(data == null || data.size() == 0 ) {
                //mView.showMessage("暂无数据");
                return;
            }
            mView.requestSuccess(value);
        }
    }

    public FinAccountListPresenterImpl(Context context, FinAccountListActivity view) {
        this.context = context;
        this.mView = view;
        this.mModel = new FinAccountListModelImpl(getBaseUrl(context));
    }

    @Override
    public void setPresenter(FinAccountListContract.View paramT) {

    }
}
