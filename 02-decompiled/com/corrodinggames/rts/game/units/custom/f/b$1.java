/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.f;

final class b$1 {
    int a = -1;
    int b;
    final /* synthetic */ String c;

    b$1(String string) {
        this.c = string;
    }

    void a() {
        this.b = ++this.a < this.c.length() ? (int)this.c.charAt(this.a) : -1;
    }

    boolean a(int n) {
        while (this.b == 32) {
            this.a();
        }
        if (this.b == n) {
            this.a();
            return true;
        }
        return false;
    }

    double b() {
        this.a();
        double d = this.c();
        if (this.a < this.c.length()) {
            throw new RuntimeException("Unexpected: " + (char)this.b);
        }
        return d;
    }

    double c() {
        double d = this.d();
        while (true) {
            if (this.a(43)) {
                d += this.d();
                continue;
            }
            if (!this.a(45)) break;
            d -= this.d();
        }
        return d;
    }

    double d() {
        double d = this.e();
        while (true) {
            if (this.a(42)) {
                d *= this.e();
                continue;
            }
            if (this.a(47)) {
                d /= this.e();
                continue;
            }
            if (!this.a(37)) break;
            d %= this.e();
        }
        return d;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    double e() {
        double d;
        if (this.a(43)) {
            return this.e();
        }
        if (this.a(45)) {
            return -this.e();
        }
        int n = this.a;
        if (this.a(40)) {
            d = this.c();
            this.a(41);
        } else if (this.b >= 48 && this.b <= 57 || this.b == 46) {
            while (this.b >= 48 && this.b <= 57 || this.b == 46) {
                this.a();
            }
            d = Double.parseDouble(this.c.substring(n, this.a));
        } else {
            if (this.b < 97) throw new RuntimeException("Unexpected: " + (char)this.b);
            if (this.b > 122) throw new RuntimeException("Unexpected: " + (char)this.b);
            while (this.b >= 97 && this.b <= 122) {
                this.a();
            }
            String string = this.c.substring(n, this.a);
            d = this.e();
            if (string.equals("sqrt")) {
                d = Math.sqrt(d);
            } else if (string.equals("sin")) {
                d = Math.sin(Math.toRadians(d));
            } else if (string.equals("cos")) {
                d = Math.cos(Math.toRadians(d));
            } else if (string.equals("tan")) {
                d = Math.tan(Math.toRadians(d));
            } else {
                if (!string.equals("int")) throw new RuntimeException("Unknown function: " + string);
                d = (int)d;
            }
        }
        if (!this.a(94)) return d;
        return Math.pow(d, this.e());
    }
}
