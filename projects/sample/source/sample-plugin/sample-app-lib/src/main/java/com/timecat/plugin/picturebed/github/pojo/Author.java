package com.timecat.plugin.picturebed.github.pojo;

public final class Author {

    private final String date;

    private final String name;

    private final String email;


    public final String getDate() {
        return this.date;
    }


    public final String getName() {
        return this.name;
    }


    public final String getEmail() {
        return this.email;
    }

    public Author( String date,  String name,  String email) {
        super();
        this.date = date;
        this.name = name;
        this.email = email;
    }


    public String toString() {
        return "Author(date=" + this.date + ", name=" + this.name + ", email=" + this.email + ")";
    }

    public int hashCode() {
        String var10000 = this.date;
        int var1 = (var10000 != null ? var10000.hashCode() : 0) * 31;
        String var10001 = this.name;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.email;
        return var1 + (var10001 != null ? var10001.hashCode() : 0);
    }
}
