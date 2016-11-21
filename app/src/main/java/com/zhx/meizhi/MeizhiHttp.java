package com.zhx.meizhi;


public class MeizhiHttp {

    private static MeizhiService mService;

    protected static final Object monitor = new Object();

    public static MeizhiService getServiceInstance(){
        synchronized (monitor){
            if(mService==null){
                mService = new MeizhiRetrofit().getService();
            }
            return mService;
        }
    }
}
