package com.example.sxd.thanksgivinghall.api.service;

import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.FinAccSumListEntity;
import com.example.sxd.thanksgivinghall.bean.FinAccountDetailEntity;
import com.example.sxd.thanksgivinghall.bean.FinAccountListEntity;
import com.example.sxd.thanksgivinghall.bean.FinRecordListEntity;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FinAccountService {

    @GET("infc/infcFinAccount/requestStartToEndList")
    Call<FinRecordListEntity> listFinRecord(@Query("id") String id, @Query("acName") String acName);

    @GET("infc/infcFinAccount/allAccount")
    Call<FinAccountListEntity> allAccount();

    @GET("infc/infcFinAccount/deleteById")
    Call<FinAccountListEntity> deleteById(@Query("id") String id);

    @GET("infc/infcFinAccount/detailFinAccount")
    Call<FinAccountDetailEntity> detailFinAccount(@Query("id") String id);

    @GET("infc/infcFinAccount/listSumAccount")
    Call<FinAccSumListEntity> listSumAccount(@Query("id") String id, @Query("dept") String dept);

    @POST("infc/infcFinAccount/addFinAccount")
    Call<Base> addFinAccount(@Body RequestBody body);


}
