package com.example.sxd.thanksgivinghall.api.service;

import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.FinRecordDetailEntity;
import com.example.sxd.thanksgivinghall.bean.FinRecordListEntity;

import java.util.Date;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
public interface FinRecordService {

    /**
     * 新增明细记录
     * @param Body (json 序列化对象）
     * @return
     */
    @POST("infc/infcFinRecord/addFinRecord")
    Call<Base> addFinRecord(@Body RequestBody Body);

    /**
     * 获取明细记录列表
     */
    @GET("infc/infcFinRecord/listFinRecord")
    Call<FinRecordListEntity> listFinRecord(@Query("createDate")Date createDate);

    /**
     * 获取明细记录详情
     */
    @GET("infc/infcFinRecord/detailFinRecord")
    Call<FinRecordDetailEntity> detailFinRecord(@Query("id")String recordId);
}
