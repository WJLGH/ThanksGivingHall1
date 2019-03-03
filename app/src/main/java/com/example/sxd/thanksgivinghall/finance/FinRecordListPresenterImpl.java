package com.example.sxd.thanksgivinghall.finance;

import android.content.Context;
import android.view.View;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenterImpl;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.FinRecordListEntity;

import java.util.List;

public class FinRecordListPresenterImpl  extends BasePresenterImpl implements  FinRecordListContract.Presenter {

    FinRecordListContract.View mView;
    FinRecordListContract.Model mModel;
    Context context;
    MyResultLisener mResultLisener = new MyResultLisener();

    @Override
    public void requestBusTypeList(String busType) {
        if(busType == null || "".equals(busType)) {
            this.mView.showMessage("请输入正确的交易类型");
            return;
        }
        this.mModel.requestBusTypeList(busType,mResultLisener);
    }

    @Override
    public void requestStartToEndList(String startDate, String endDate) {
        if(startDate  == null || endDate == null) {
            this.mView.showMessage("请输入正确的日期");
            return ;
        }
        this.mModel.requestStartToEndList(startDate, endDate, mResultLisener );
    }

    @Override
    public void requestAccList(String id, String acName) {
        id = id == null ? "":id;
        acName = acName == null ? "":acName;
       if("".equals(id) && "".equals(acName)) {
           this.mView.showMessage("请输入要查询的账户");
           return ;
       }
       this.mModel.requestAccList(id,acName,mResultLisener);
    }

    @Override
    public void requestDateList(String dateStr) {
        if(null == dateStr || "".equals(dateStr)) {
            this.mView.showMessage("请输入正确的日期");
            return ;
        }
        this.mModel.requestDateList(dateStr,mResultLisener);
    }

    @Override
    public void requestDeleteRecord(String id) {
        mModel.requestDeleteRecord(id, new ResultListener<Base>() {
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
            public void onSuccess(Base data) {
                if (data != null) {
                    if(data.getSuccess().equals("true")) {
                        mView.showMessage("删除成功");
                    }else{
                        mView.showMessage(data.getStatusMessage());
                    }
                } else {
                    mView.showMessage("删除失败");
                }
            }
        });
    }

    private class MyResultLisener implements ResultListener<FinRecordListEntity> {
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
    }

    public FinRecordListPresenterImpl(Context context, FinRecordListContract.View view) {
        this.context = context;
        this.mView = view;
        this.mModel = new FinRecordListModelImpl(getBaseUrl(context));
    }

    @Override
    public void setPresenter(FinRecordListContract.View paramT) {

    }
}
