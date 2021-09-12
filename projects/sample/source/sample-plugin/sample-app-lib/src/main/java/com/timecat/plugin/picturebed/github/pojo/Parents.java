package com.timecat.plugin.picturebed.github.pojo;

public final class Parents {

    private final String url;

    private final String html_url;

    private final String sha;


    public final String getUrl() {
        return this.url;
    }


    public final String getHtml_url() {
        return this.html_url;
    }


    public final String getSha() {
        return this.sha;
    }

    public Parents( String url,  String html_url,  String sha) {
        super();
        this.url = url;
        this.html_url = html_url;
        this.sha = sha;
    }


    public String toString() {
        return "Parents(url=" + this.url + ", html_url=" + this.html_url + ", sha=" + this.sha + ")";
    }

    public int hashCode() {
        String var10000 = this.url;
        int var1 = (var10000 != null ? var10000.hashCode() : 0) * 31;
        String var10001 = this.html_url;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.sha;
        return var1 + (var10001 != null ? var10001.hashCode() : 0);
    }
}

