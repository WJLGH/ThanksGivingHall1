package com.example.sxd.thanksgivinghall.finance;

import com.example.sxd.thanksgivinghall.api.AppMainService;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.FinAccountListEntity;
import com.example.sxd.thanksgivinghall.bean.FinRecordDetailEntity;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinRecordAddModelImpl implements FinRecordAddContract.Model {
    public String baseUrl = "";

    public FinRecordAddModelImpl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public void request(RequestBody body, final ResultListener<Base> result) {
        Call<Base> call = AppMainService.getFinRecordService(baseUrl).addFinRecord(body);
        result.onStart();
        call.enqueue(new Callback<Base>() {
            @Override
            public void onResponse(Call<Base> call, Response<Base> response) {
                result.onSuccess(response.body());
                result.onEnd();
            }

            @Override
            public void onFailure(Call<Base> call, Throwable t) {
                //请求失败
                result.onFailure(t.getMessage());
                //请求结束
                result.onEnd();
            }
        });
    }

    @Override
    public void requestAcList(final  ResultListener<FinAccountListEntity> result) {
            Call<FinAccountListEntity> call = AppMainService.getFinAccountService(baseUrl).allAccount();
            result.onStart();
            call.enqueue(new Callback<FinAccountListEntity>() {
                @Override
                public void onResponse(Call<FinAccountListEntity> call, Response<FinAccountListEntity> response) {
                    result.onSuccess(response.body());
                    result.onEnd();
                }

                @Override
                public void onFailure(Call<FinAccountListEntity> call, Throwable t) {
                    //请求失败
                    result.onFailure(t.getMessage());
                    //请求结束
                    result.onEnd();
                }
            });
    }

    @Override
    public void request(String recordId, final ResultListener<FinRecordDetailEntity> result) {
        Call<FinRecordDetailEntity> call = AppMainService.getFinRecordService(baseUrl).detailFinRecord(recordId);
        result.onStart();
        call.enqueue(new Callback<FinRecordDetailEntity>() {
            @Override
            public void onResponse(Call<FinRecordDetailEntity> call, Response<FinRecordDetailEntity> response) {
                //请求成功
                result.onSuccess(response.body());
                //请求结束
                result.onEnd();
            }

            @Override
            public void onFailure(Call<FinRecordDetailEntity> call, Throwable t) {
                //请求失败
                result.onFailure(t.getMessage());
                //请求结束
                result.onEnd();

            }
        });

    }
}
