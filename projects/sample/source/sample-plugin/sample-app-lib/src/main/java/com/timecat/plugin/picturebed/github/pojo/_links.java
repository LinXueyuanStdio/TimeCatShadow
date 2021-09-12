package com.timecat.plugin.picturebed.github.pojo;

public final class _links {

    private final String self;

    private final String git;

    private final String html;


    public final String getSelf() {
        return this.self;
    }


    public final String getGit() {
        return this.git;
    }


    public final String getHtml() {
        return this.html;
    }

    public _links( String self,  String git,  String html) {
        super();
        this.self = self;
        this.git = git;
        this.html = html;
    }


    public String toString() {
        return "_links(self=" + this.self + ", git=" + this.git + ", html=" + this.html + ")";
    }

    public int hashCode() {
        String var10000 = this.self;
        int var1 = (var10000 != null ? var10000.hashCode() : 0) * 31;
        String var10001 = this.git;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.html;
        return var1 + (var10001 != null ? var10001.hashCode() : 0);
    }
}
