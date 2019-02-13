package com.example.sxd.thanksgivinghall.finance;

import android.content.Context;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenterImpl;
import com.example.sxd.thanksgivinghall.bean.FinDateSumListEntity;

import java.util.List;

public class DateSumPresenterImpl extends BasePresenterImpl implements DateSumContract.Presenter {
    DateSumContract.View mView;
    DateSumContract.Model mModel;
    Context context;

    public DateSumPresenterImpl(Context context , DateSumContract.View view) {
        this.mView = view;
        this.context = context;
        this.mModel = new DateSumModelImpl(getBaseUrl(context));
    }

    @Override
    public void request(String dateStr) {

        if(dateStr == null ||  ! dateStr.matches("^\\d{4}(|-\\d{2}(|-\\d{2}))$") ) {
            mView.showMessage("请输入正确的日期（yyyy-MM-dd) 例如：2019-02-03");
            return;
        }
        mModel.request(dateStr, new ResultListener<FinDateSumListEntity>() {
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
            public void onSuccess(FinDateSumListEntity value) {
                FinDateSumListEntity.Data main = value.getMainData();
                if (main == null) {
                    mView.showMessage("查询的日期数据汇总信息为空");
                    return;
                }

                mView.requestSuccess(value);
            }
        });

    }

    @Override
    public void attachView(DateSumContract.View paramT) {

    }

    @Override
    public void detachView() {

    }
}
