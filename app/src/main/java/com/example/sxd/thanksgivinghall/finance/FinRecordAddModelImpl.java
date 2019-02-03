package com.example.sxd.thanksgivinghall.finance;

import com.example.sxd.thanksgivinghall.api.AppMainService;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.bean.Base;

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
}
