package com.example.sxd.thanksgivinghall.finance;

import com.example.sxd.thanksgivinghall.api.AppMainService;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.FinAccountListEntity;
import com.github.mikephil.charting.data.BaseEntry;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinAccountListModelImpl implements FinAccountListContract.Model {
    private String baseUrl;

    @Override
    public void requestAccountList(ResultListener<FinAccountListEntity> result) {
        Call<FinAccountListEntity> call = AppMainService.getFinAccountService(baseUrl).allAccount();
        result.onStart();
        call.enqueue(new MyCallBack(result));
    }

    @Override
    public void deleteById(String id,ResultListener<FinAccountListEntity> result) {
        Call<FinAccountListEntity> call = AppMainService.getFinAccountService(baseUrl).deleteById(id);
        result.onStart();
        call.enqueue(new MyCallBack(result));
    }


    class MyCallBack implements  Callback<FinAccountListEntity> {
        private  ResultListener<FinAccountListEntity> resultTmp;
        MyCallBack( ResultListener<FinAccountListEntity> resultTmp) { this.resultTmp = resultTmp;}

        @Override
        public void onResponse(Call<FinAccountListEntity> call, Response<FinAccountListEntity> response) {
            resultTmp.onSuccess(response.body());
            resultTmp.onEnd();
        }
        @Override
        public void onFailure(Call<FinAccountListEntity> call, Throwable t) {
            resultTmp.onFailure(t.getMessage());
            resultTmp.onEnd();
        }
    }

    public FinAccountListModelImpl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

}
