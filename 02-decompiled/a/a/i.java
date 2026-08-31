/*
 * Decompiled with CFR 0.152.
 */
package a.a;

import a.a.h;

class i {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;

    public synchronized int a() {
        this.a = h.a(this.a);
        return this.a;
    }

    public synchronized int a(int n) {
        this.a = n;
        return this.a;
    }

    public synchronized int b(int n) {
        this.b = n;
        return this.b;
    }

    public synchronized int b() {
        return this.b;
    }

    public synchronized void c() {
        ++this.c;
    }

    public synchronized int d() {
        return this.c;
    }

    public synchronized int e() {
        int n = this.c;
        this.c = 0;
        return n;
    }

    public synchronized void f() {
        ++this.d;
    }

    public synchronized int g() {
        return this.d;
    }

    public synchronized int h() {
        int n = this.d;
        this.d = 0;
        return n;
    }

    public synchronized void i() {
        ++this.e;
    }

    public synchronized int j() {
        return this.e;
    }

    public synchronized int k() {
        int n = this.e;
        this.e = 0;
        return n;
    }

    public synchronized void l() {
        this.d = 0;
        this.e = 0;
        this.c = 0;
    }
}
