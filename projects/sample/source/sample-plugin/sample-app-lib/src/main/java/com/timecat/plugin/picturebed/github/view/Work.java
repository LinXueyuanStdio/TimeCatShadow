package com.timecat.plugin.picturebed.github.view;

import android.view.View;

/**
 * @author zby
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/4/12
 * @description 上传图片到图床
 * @usage null
 */
public interface Work {
    /**
     * 准备上传
     * @param view 自定义
     */
    void beforeUpload(View view);

    /**
     * 上传中
     */
    void uploading();

    /**
     * 上传完毕
     * @param url 远程图片的 url
     */
    void uploadDone(String url);

    /**
     * 上传失败，发生错误
     * @param msg 错误信息
     */
    void uploadFail(String msg);
}
