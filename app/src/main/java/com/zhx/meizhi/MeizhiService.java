package com.zhx.meizhi;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


public interface MeizhiService {

    @GET("{pagesize}/{page}")
    Observable<MeizhiEntity> getMeizhiData(@Path("pagesize") int pagesize, @Path("page") int page);
}
