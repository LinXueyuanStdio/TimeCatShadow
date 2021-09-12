package com.timecat.plugin.picturebed.github.pojo;

public final class Tree {

    private final String url;

    private final String sha;


    public final String getUrl() {
        return this.url;
    }


    public final String getSha() {
        return this.sha;
    }

    public Tree( String url,  String sha) {
        super();
        this.url = url;
        this.sha = sha;
    }


    public String toString() {
        return "Tree(url=" + this.url + ", sha=" + this.sha + ")";
    }

    public int hashCode() {
        String var10000 = this.url;
        int var1 = (var10000 != null ? var10000.hashCode() : 0) * 31;
        String var10001 = this.sha;
        return var1 + (var10001 != null ? var10001.hashCode() : 0);
    }
}
