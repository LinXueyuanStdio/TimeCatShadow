package com.timecat.plugin.picturebed.github.pojo;

public final class GitFileResponse {

    private final Content content;

    private final Commit commit;


    public final Content getContent() {
        return this.content;
    }


    public final Commit getCommit() {
        return this.commit;
    }

    public GitFileResponse( Content content,  Commit commit) {
        super();
        this.content = content;
        this.commit = commit;
    }


    public String toString() {
        return "GitFileResponse(content=" + this.content + ", commit=" + this.commit + ")";
    }

    public int hashCode() {
        Content var10000 = this.content;
        int var1 = (var10000 != null ? var10000.hashCode() : 0) * 31;
        Commit var10001 = this.commit;
        return var1 + (var10001 != null ? var10001.hashCode() : 0);
    }
}
