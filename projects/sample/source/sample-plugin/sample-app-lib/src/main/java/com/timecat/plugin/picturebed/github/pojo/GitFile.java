package com.timecat.plugin.picturebed.github.pojo;


public final class GitFile {
    private String content;

    private String message;

    private SmallCommitter committer;


    public final String getContent() {
        return this.content;
    }

    public final void setContent( String var1) {
        this.content = var1;
    }


    public final String getMessage() {
        return this.message;
    }

    public final void setMessage( String var1) {
        this.message = var1;
    }


    public final SmallCommitter getCommitter() {
        return this.committer;
    }

    public final void setCommitter( SmallCommitter var1) {
        this.committer = var1;
    }

    public GitFile( String content,  String message,  SmallCommitter committer) {
        super();
        this.content = content;
        this.message = message;
        this.committer = committer;
    }


    public String toString() {
        return "GitFile(content=" + this.content + ", message=" + this.message + ", committer=" + this.committer + ")";
    }

    public int hashCode() {
        String var10000 = this.content;
        int var1 = (var10000 != null ? var10000.hashCode() : 0) * 31;
        String var10001 = this.message;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        SmallCommitter var2 = this.committer;
        return var1 + (var2 != null ? var2.hashCode() : 0);
    }
}
