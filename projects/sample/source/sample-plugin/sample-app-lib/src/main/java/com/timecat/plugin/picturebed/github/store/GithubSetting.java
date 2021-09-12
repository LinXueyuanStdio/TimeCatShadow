package com.timecat.plugin.picturebed.github.store;

import com.timecat.plugin.picturebed.github.view.GithubService;

/**
 * @author zby
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/4/14
 * @description null
 * @usage null
 */
public enum GithubSetting {
    owner("owner", ""),
    repo("repo", GithubService.repo),
    email("email", GithubService.email),
    path("path", GithubService.imagePathPrefix),
    token("token", "");

    public String key;
    public String defaultValue;

    GithubSetting(String key, String defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }
}
