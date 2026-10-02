/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

public final class ac {
    String a;
    String b;

    public ac(String string, String string2) {
        this.a = string;
        this.b = string2;
    }

    public String toString() {
        return "[" + this.a + "]" + this.b;
    }

    public boolean equals(Object object) {
        if (!(object instanceof ac)) {
            return false;
        }
        ac ac2 = (ac)object;
        return this.b.equals(ac2.b) && this.a.equals(ac2.a);
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }
}
