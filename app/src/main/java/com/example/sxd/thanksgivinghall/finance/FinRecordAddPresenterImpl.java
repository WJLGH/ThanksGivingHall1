package com.example.sxd.thanksgivinghall.finance;

import android.content.Context;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenterImpl;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.FinAccountListEntity;
import com.example.sxd.thanksgivinghall.bean.FinRecordDetailEntity;
import com.example.sxd.thanksgivinghall.notice.NoticeAddModelImpl;
import com.example.sxd.thanksgivinghall.utils.StringUtils;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class FinRecordAddPresenterImpl extends BasePresenterImpl implements FinRecordAddContract.Presenter {
    private FinRecordAddContract.View mView;
    private  FinRecordAddContract.Model mModel;
    private Context context;

    public FinRecordAddPresenterImpl(Context context ,FinRecordAddContract.View view) {
        this.mView = view;
        this.context = context;
        mModel = new FinRecordAddModelImpl(getBaseUrl(context));
    }

    @Override
    public void request(FinRecordDetailEntity.Data entity) {
        if (!checkParams(entity)) return;

        Map<String ,Object> params = new LinkedHashMap<>();
        params.put("reType",entity.getReType());
        params.put("busType",entity.getBusType());
        params.put("amount",entity.getAmount());
        params.put("description",entity.getDescription());
        params.put("inId",entity.getInId());
        params.put("outId",entity.getOutId());
        params.put("dept",entity.getDept());
        //时间字符串
        params.put("dateStr",entity.getNoteDate());
        //entity.setDateStr(entity.getNoteDate());

        JSONObject jsonObj = new JSONObject(params);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObj.toString());
        this.mModel.request(requestBody, new ResultListener<Base>() {
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
            public void onSuccess(Base data) {
                if(null == data) {
                    mView.showMessage("数据获取失败");
                    return;
                }
                if(!"ok".equals(data.getStatusMessage())) {
                    mView.showMessage(data.getStatusMessage());
                    return;
                }
                //数据获取成功
                mView.requestSuccess(data);
            }
        });
    }

    @Override
    public void requestAcList(final String type) {
        this.mModel.requestAcList(new ResultListener<FinAccountListEntity>() {
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
            public void onSuccess(FinAccountListEntity paramT) {
                mView.fillInOutAcSpinner(paramT,type);
            }
        });
    }

    private boolean checkParams(FinRecordDetailEntity.Data entity) {
        if(null == entity) {
            this.mView.showMessage("请填写明细信息");
            return false;
        }
        if(!StringUtils.notIsBlankAndEmpty(entity.getReType())) {
            this.mView.showMessage("明细类型不能为空");
            return false;
        }
        if(!StringUtils.notIsBlankAndEmpty(entity.getBusType())) {
            this.mView.showMessage("交易类型不能为空");
            return false;
        }
        if(!StringUtils.notIsBlankAndEmpty(entity.getDescription())) {
            this.mView.showMessage("摘要不能为空");
            return false;
        }
        if(!StringUtils.notIsBlankAndEmpty(entity.getAmount().toString())) {
            this.mView.showMessage("金额不能为空");
            return false;
        }
        if(!StringUtils.notIsBlankAndEmpty(entity.getNoteDate())) {
            this.mView.showMessage("日期不能为空");
            return false;
        }
        if(!StringUtils.notIsBlankAndEmpty(entity.getDept())) {
            this.mView.showMessage("部门不能为空");
            return false;
        }
        if(entity.getReType().equals("支出")) {
            if(!StringUtils.notIsBlankAndEmpty(entity.getOutId())||StringUtils.notIsBlankAndEmpty(entity.getInId())) {
                this.mView.showMessage("出账账户不能为空且入账账户应该为空");
                return false;
            }
        } else if(entity.getReType().equals("收入")) {
            if(!StringUtils.notIsBlankAndEmpty(entity.getInId())||StringUtils.notIsBlankAndEmpty(entity.getOutId()) ) {
                this.mView.showMessage("入账账户不能为空且出账账户应该为空");
                return false;
            }
        } else if(entity.getReType().equals("转账")){
            if(!StringUtils.notIsBlankAndEmpty(entity.getOutId()) || !StringUtils.notIsBlankAndEmpty(entity.getInId())) {
                this.mView.showMessage("出入账户均不能为空");
                return false;
            }
        }
        return true;
    }

    @Override
    public void attachView(FinRecordAddContract.View paramT) {

    }

    @Override
    public void detachView() {

    }
}
