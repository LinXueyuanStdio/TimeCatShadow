package com.timecat.plugin.picturebed.github.store;

import com.tencent.mmkv.MMKV;

/**
 * @author zby
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/4/14
 * @description null
 * @usage null
 */
public class DEF {
    public static MMKV githubApp() {
        return MMKV.mmkvWithID("GithubApp");
    }
}
