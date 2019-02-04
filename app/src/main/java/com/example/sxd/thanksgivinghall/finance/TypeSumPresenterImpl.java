package com.example.sxd.thanksgivinghall.finance;

import android.content.Context;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenterImpl;
import com.example.sxd.thanksgivinghall.bean.FinTypeSumListEntity;
import com.example.sxd.thanksgivinghall.utils.StringUtils;

class TypeSumPresenterImpl extends BasePresenterImpl implements TypeSumContract.Presenter {
    TypeSumContract.View mView;
    TypeSumContract.Model mModel;
    Context context;
    public TypeSumPresenterImpl(Context context, TypeSumContract.View view) {
        this.mView = view;
        this.context = context;
        this.mModel = new TypeSumModelImpl( getBaseUrl(context));
    }

    @Override
    public void request(String reType) {
        if(!StringUtils.notIsBlankAndEmpty(reType)) {
            mView.showMessage("记录类型不能为空");
            return;
        }
        mModel.request(reType, new ResultListener<FinTypeSumListEntity>() {
            @Override
            public void onEnd() {

            }

            @Override
            public void onFailure(String paramString) {
                mView.showMessage("访问服务器异常");
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(FinTypeSumListEntity value) {
                if(!"true".equals(value.getSuccess())||!"ok".equals(value.getStatusMessage())) {
                    mView.showMessage("数据获取失败");
                    return;
                }
                mView.requestSuccess(value);
            }
        });
    }

    @Override
    public void attachView(TypeSumContract.View paramT) {

    }

    @Override
    public void detachView() {

    }
}
