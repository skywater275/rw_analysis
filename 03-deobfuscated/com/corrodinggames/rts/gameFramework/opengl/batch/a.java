/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.opengl.batch;

public enum a {
    a(1, "a_Position"),
    b(2, "a_TexCoordinate");

    private int c;
    private String d;




    private a(int var3_1, String var4_2) {


        this.c = var3_1;
        this.d = var4_2;
    }

    public int a() {
        return this.c;
    }

    public String b() {
        return this.d;
    }
}
