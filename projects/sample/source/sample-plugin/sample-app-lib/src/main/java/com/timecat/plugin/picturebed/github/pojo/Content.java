package com.timecat.plugin.picturebed.github.pojo;

public final class Content {

    private final String name;

    private final String path;

    private final String sha;
    private final int size;

    private final String url;

    private final String html_url;

    private final String git_url;

    private final String download_url;

    private final String type;

    private final _links _links;


    public final String getName() {
        return this.name;
    }


    public final String getPath() {
        return this.path;
    }


    public final String getSha() {
        return this.sha;
    }

    public final int getSize() {
        return this.size;
    }


    public final String getUrl() {
        return this.url;
    }


    public final String getHtml_url() {
        return this.html_url;
    }


    public final String getGit_url() {
        return this.git_url;
    }


    public final String getDownload_url() {
        return this.download_url;
    }


    public final String getType() {
        return this.type;
    }


    public final _links get_links() {
        return this._links;
    }

    public Content( String name,  String path,  String sha, int size,  String url,  String html_url,  String git_url,  String download_url,  String type,  _links _links) {
        super();
        this.name = name;
        this.path = path;
        this.sha = sha;
        this.size = size;
        this.url = url;
        this.html_url = html_url;
        this.git_url = git_url;
        this.download_url = download_url;
        this.type = type;
        this._links = _links;
    }


    public String toString() {
        return "Content(name=" + this.name + ", path=" + this.path + ", sha=" + this.sha + ", size=" + this.size + ", url=" + this.url + ", html_url=" + this.html_url + ", git_url=" + this.git_url + ", download_url=" + this.download_url + ", type=" + this.type + ", _links=" + this._links + ")";
    }

    public int hashCode() {
        String var10000 = this.name;
        int var1 = (var10000 != null ? var10000.hashCode() : 0) * 31;
        String var10001 = this.path;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.sha;
        var1 = ((var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31 + this.size) * 31;
        var10001 = this.url;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.html_url;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.git_url;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.download_url;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.type;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        _links var2 = this._links;
        return var1 + (var2 != null ? var2.hashCode() : 0);
    }
}
