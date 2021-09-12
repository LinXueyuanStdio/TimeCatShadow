package com.timecat.plugin.picturebed.github.pojo;

/**
 * @author zby
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/4/12
 * @description null
 * @usage null
 */
public final class SmallCommitter {

    private final String name;

    private final String email;


    public final String getName() {
        return this.name;
    }


    public final String getEmail() {
        return this.email;
    }

    public SmallCommitter( String name,  String email) {
        super();
        this.name = name;
        this.email = email;
    }


    public String toString() {
        return "SmallCommitter(name=" + this.name + ", email=" + this.email + ")";
    }

    public int hashCode() {
        int var1 = this.name.hashCode() * 31;
        return var1 + this.email.hashCode();
    }
}
