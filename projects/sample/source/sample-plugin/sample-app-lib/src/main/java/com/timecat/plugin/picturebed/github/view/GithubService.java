package com.timecat.plugin.picturebed.github.view;

import com.timecat.plugin.picturebed.github.pojo.GitFile;
import com.timecat.plugin.picturebed.github.pojo.GitFileResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author zby
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020-02-13
 * @description null
 * @usage null
 */
public interface GithubService {
    String GithubToken = "5345a4e1d5a1647574b67730a871f48d97f7b2c0";
    String owner = "LinXueyuanStdio";
    String email = "761516186@qq.com";
    String repo = "TimeCatOSS";
    String imagePathPrefix = "image/";
    String urlPathPrefix = "http://xichen.pub/TimeCatOSS";

    @Headers({"Content-UsageType: application/json", "Accept: application/json"})
    @PUT("/repos/{owner}/{repo}/contents/{path}")
    Observable<GitFileResponse> upload(
            @Path("owner") String owner,
            @Path("repo") String repo,
            @Path("path") String path,
            @Query("access_token") String access_token,
            @Body GitFile gitFile
    );
}
