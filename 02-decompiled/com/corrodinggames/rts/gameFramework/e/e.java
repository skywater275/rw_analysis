/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.e;

import com.corrodinggames.rts.gameFramework.e.c;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.j;
import java.io.File;

public class e
extends c {
    c g;
    c h;
    String i;
    String j;

    public e(c c2, String string, c c3, String string2) {
        this.g = c2;
        this.i = string;
        this.h = c3;
        this.j = string2;
    }

    @Override
    public String a() {
        String string = this.g.a();
        String string2 = this.h.a();
        if (string != null) {
            return string;
        }
        return string2;
    }

    @Override
    public void a(String string) {
        this.g.a(string);
        this.h.a(string);
    }

    @Override
    public String a(String string, String string2) {
        return this.s(string).a(string, string2);
    }

    @Override
    public boolean b(String string) {
        return this.s(string).b(this.q(string));
    }

    @Override
    public boolean c(String string) {
        return this.s(string).c(this.q(string));
    }

    @Override
    public String d(String string) {
        return this.s(string).d(this.q(string));
    }

    @Override
    public String f(String string) {
        return this.s(string).f(this.q(string));
    }

    @Override
    public String e(String string) {
        return this.s(string).e(this.q(string));
    }

    @Override
    public boolean a(String string, boolean bl) {
        c c2 = this.r(string);
        string = this.q(string);
        if (c2 != null) {
            return c2.a(this.q(string), bl);
        }
        boolean bl2 = this.g.a(this.q(string), bl);
        if (this.h.a(this.q(string), bl)) {
            bl2 = true;
        }
        return bl2;
    }

    @Override
    public boolean g(String string) {
        return this.s(string).g(this.q(string));
    }

    private String q(String string) {
        if (string == null) {
            return null;
        }
        int n2 = string.indexOf(this.i);
        if (n2 != -1) {
            String string2 = string.substring(0, n2) + string.substring(n2 + this.i.length());
            if (string2.contains(this.i) || string2.contains(this.j)) {
                l.e("fixPath: double tag for: " + string);
            }
            return string2;
        }
        int n3 = string.indexOf(this.j);
        if (n3 != -1) {
            String string3 = string.substring(0, n3) + string.substring(n3 + this.j.length());
            if (string3.contains(this.i) || string3.contains(this.j)) {
                l.e("fixPath: double tag for: " + string);
            }
            return string3;
        }
        return string;
    }

    private c r(String string) {
        if (string == null) {
            return null;
        }
        if (string.contains(this.i)) {
            return this.g;
        }
        if (string.contains(this.j)) {
            return this.h;
        }
        return null;
    }

    private c s(String string) {
        c c2 = this.r(string);
        if (c2 != null) {
            return c2;
        }
        return this.g;
    }

    @Override
    public String[] b(String string, boolean bl) {
        int n2;
        c c2 = this.r(string);
        string = this.q(string);
        if (c2 != null) {
            return c2.b(string, bl);
        }
        String[] stringArray = this.g.b(string, bl);
        String[] stringArray2 = this.h.b(string, bl);
        if (stringArray == null && stringArray2 == null) {
            return null;
        }
        if (stringArray == null) {
            stringArray = new String[]{};
        }
        if (stringArray2 == null) {
            stringArray2 = new String[]{};
        }
        String[] stringArray3 = new String[stringArray.length + stringArray2.length];
        for (n2 = 0; n2 < stringArray.length; ++n2) {
            stringArray3[n2] = this.i + stringArray[n2];
        }
        for (n2 = 0; n2 < stringArray2.length; ++n2) {
            stringArray3[n2 + stringArray.length] = this.j + stringArray2[n2];
        }
        return stringArray3;
    }

    @Override
    public j i(String string) {
        return this.g.i(string);
    }

    @Override
    public j j(String string) {
        return this.s(string).j(this.q(string));
    }

    @Override
    public String b() {
        return this.g.b();
    }

    @Override
    public File a(String string, String string2, boolean bl) {
        return this.s(string).a(this.q(string), string2, bl);
    }

    @Override
    public String m(String string) {
        return this.s(string).m(string);
    }

    @Override
    public String d() {
        String string = this.g.d();
        if (this.h.d() != null) {
            string = string + " and " + this.h.d();
        }
        return string;
    }

    @Override
    public boolean e() {
        return this.g.e() || this.h.e();
    }

    @Override
    public String o(String string) {
        if (string.startsWith("/") && this.i.endsWith("/")) {
            string = string.substring(1);
            return "/" + this.i + string;
        }
        return this.i + string;
    }
}
