package com.example.sxd.thanksgivinghall.api;

import com.example.sxd.thanksgivinghall.api.service.DeviceService;
import com.example.sxd.thanksgivinghall.api.service.FinAccountService;
import com.example.sxd.thanksgivinghall.api.service.FinRecordService;
import com.example.sxd.thanksgivinghall.api.service.LoginService;
import com.example.sxd.thanksgivinghall.api.service.NotifyService;
import com.example.sxd.thanksgivinghall.api.service.OfficeUserService;
import com.example.sxd.thanksgivinghall.api.service.TaskService;
import com.example.sxd.thanksgivinghall.api.service.UpLoadService;

public class AppMainService
{
    private static LoginService loginService;
    private static DeviceService deviceService;
    private static OfficeUserService officeUserService;
    private static TaskService taskService;
    private static NotifyService notifyService;
    private static UpLoadService upLoadService;
    private static FinRecordService finRecordService;
    private static FinAccountService finAccountService;


    public static FinAccountService getFinAccountService(String paramString) {
        finAccountService = (FinAccountService)BaseApi.retrofit(paramString).create(FinAccountService.class);
        return finAccountService;
    }

    public static FinRecordService getFinRecordService(String paramString) {
        finRecordService = (FinRecordService)BaseApi.retrofit(paramString).create(FinRecordService.class);
        return finRecordService;
    }
    public static LoginService getLoginService(String paramString)
    {
        loginService = (LoginService)BaseApi.retrofit(paramString).create(LoginService.class);
        return loginService;
    }
    public static DeviceService getDeviceService(String paramString)
    {
        deviceService = (DeviceService)BaseApi.retrofit(paramString).create(DeviceService.class);
        return deviceService;
    }
    public static OfficeUserService getOfficeUserService(String paramString)
    {
        officeUserService = (OfficeUserService)BaseApi.retrofit(paramString).create(OfficeUserService.class);
        return officeUserService;
    }
    public static TaskService getTaskService(String paramString)
    {
        taskService = (TaskService)BaseApi.retrofit(paramString).create(TaskService.class);
        return taskService;
    }
    public static NotifyService getNotifyService(String paramString)
    {
        notifyService = (NotifyService)BaseApi.retrofit(paramString).create(NotifyService.class);
        return notifyService;
    }
    public static UpLoadService getUpLoadService(String paramString)
    {
        upLoadService = (UpLoadService)BaseApi.retrofit(paramString).create(UpLoadService.class);
        return upLoadService;
    }
}