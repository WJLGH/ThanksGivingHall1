package com.example.sxd.thanksgivinghall.finance;

import com.example.sxd.thanksgivinghall.api.AppMainService;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.bean.FinAccountDetailEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinAccountDetailModelImpl implements FinAccountDetailContract.Model {
    String baseUrl = "";

    public FinAccountDetailModelImpl(String baseUrl){
        this.baseUrl = baseUrl;
    }
    @Override
    public void request(String accountId, final ResultListener<FinAccountDetailEntity> result) {
        Call<FinAccountDetailEntity> call = AppMainService.getFinAccountService(baseUrl).detailFinAccount(accountId);
        result.onStart();
        call.enqueue(new Callback<FinAccountDetailEntity>() {
            @Override
            public void onResponse(Call<FinAccountDetailEntity> call, Response<FinAccountDetailEntity> response) {
                //请求成功
                result.onSuccess(response.body());
                //请求结束
                result.onEnd();
            }

            @Override
            public void onFailure(Call<FinAccountDetailEntity> call, Throwable t) {
                //请求失败
                result.onFailure(t.getMessage());
                //请求结束
                result.onEnd();

            }
        });
    }
}
