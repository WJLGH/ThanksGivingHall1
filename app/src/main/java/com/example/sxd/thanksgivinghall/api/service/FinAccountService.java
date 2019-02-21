package com.example.sxd.thanksgivinghall.api.service;

import com.example.sxd.thanksgivinghall.bean.FinAccSumListEntity;
import com.example.sxd.thanksgivinghall.bean.FinAccountListEntity;
import com.example.sxd.thanksgivinghall.bean.FinRecordListEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FinAccountService {

    @GET("infc/infcFinAccount/requestStartToEndList")
    Call<FinRecordListEntity> listFinRecord(@Query("id") String id ,@Query("acName") String acName);

    @GET("infc/infcFinAccount/allAccount")
    Call<FinAccountListEntity> allAccount();

    @GET("infc/infcFinAccount/listSumAccount")
    Call<FinAccSumListEntity> listSumAccount(@Query("id")String id ,@Query("dept") String dept);
}
