package com.timecat.plugin.picturebed.github.pojo;

import java.util.List;

public final class Commit {

    private final String sha;

    private final String node_id;

    private final String url;

    private final String html_url;

    private final Author author;

    private final Committer committer;

    private final String message;

    private final Tree tree;

    private final List parents;

    private final Verification verification;


    public final String getSha() {
        return this.sha;
    }


    public final String getNode_id() {
        return this.node_id;
    }


    public final String getUrl() {
        return this.url;
    }


    public final String getHtml_url() {
        return this.html_url;
    }


    public final Author getAuthor() {
        return this.author;
    }


    public final Committer getCommitter() {
        return this.committer;
    }


    public final String getMessage() {
        return this.message;
    }


    public final Tree getTree() {
        return this.tree;
    }


    public final List getParents() {
        return this.parents;
    }


    public final Verification getVerification() {
        return this.verification;
    }

    public Commit( String sha,  String node_id,  String url,  String html_url,  Author author,  Committer committer,  String message,  Tree tree,  List parents,  Verification verification) {
        super();
        this.sha = sha;
        this.node_id = node_id;
        this.url = url;
        this.html_url = html_url;
        this.author = author;
        this.committer = committer;
        this.message = message;
        this.tree = tree;
        this.parents = parents;
        this.verification = verification;
    }


    public String toString() {
        return "Commit(sha=" + this.sha + ", node_id=" + this.node_id + ", url=" + this.url + ", html_url=" + this.html_url + ", author=" + this.author + ", committer=" + this.committer + ", message=" + this.message + ", tree=" + this.tree + ", parents=" + this.parents + ", verification=" + this.verification + ")";
    }

    public int hashCode() {
        String var10000 = this.sha;
        int var1 = (var10000 != null ? var10000.hashCode() : 0) * 31;
        String var10001 = this.node_id;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.url;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.html_url;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        Author var2 = this.author;
        var1 = (var1 + (var2 != null ? var2.hashCode() : 0)) * 31;
        Committer var3 = this.committer;
        var1 = (var1 + (var3 != null ? var3.hashCode() : 0)) * 31;
        var10001 = this.message;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        Tree var4 = this.tree;
        var1 = (var1 + (var4 != null ? var4.hashCode() : 0)) * 31;
        List var5 = this.parents;
        var1 = (var1 + (var5 != null ? var5.hashCode() : 0)) * 31;
        Verification var6 = this.verification;
        return var1 + (var6 != null ? var6.hashCode() : 0);
    }
}
