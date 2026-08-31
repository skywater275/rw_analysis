/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.appFramework.ContextMenuActivity;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class GameServerInfo {
    public boolean a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public int g;
    public boolean h;
    public long i = -1L;
    public String j;
    public String k;
    public int l;
    public boolean m;
    public String n;
    public long o;
    public int p;  // 02b j/g.p (琛?
    public int serverPort;
    public String q;
    public String r;
    public String s;
    public String t;
    public String u;
    public int v = -1;
    public int protocolVersion = 8;
    public boolean x;
    public boolean y;
    public String z;
    public int A;

    public String getString2() {
        return this.e;
    }

    public boolean isEnabled2() {
        return this.e != null;
    }

    // ---- 浠ヤ笅 02b j/g.java 鏂规硶鐩磋瘧 (v19.132x 琛ュ叏) ----
    public boolean a() {  // 02b g.a() L35: 褰撳墠杩炴帴鍒ゆ柇
        GlobalState var1 = GlobalState.B();
        String var2 = var1.bX.bw;
        return var2 != null ? var2.equals(this.b) : false;
    }

    public String b() {  // 02b g.b() L41-85: 鏈嶅姟鍣ㄨ鎯呯粍瑁?
        String var1 = this.c();
        String var2;
        String var3;
        if (var1 != null) {
            var2 = "";
            var3 = this.f;
            if (var3 != null) {
                var3 = var3.replace("\\n", "\n");
            }
            var2 = var2 + var3 + "\n";
            var2 = var2 + "Url: " + var1 + "\n";
            return var2;
        }
        var2 = "";
        if (this.a) {
            var2 = var2 + "Lan: " + this.d + ":" + this.g + "\n";
        }
        var2 = var2 + "User: " + this.n + "\n";
        var3 = com.corrodinggames.rts.appFramework.ContextMenuActivity.getString2(this.q);
        var2 = var2 + "Map: " + var3 + "\n";
        if (this.m) {
            var2 = var2 + "Password Required\n";
        }
        if (!this.h && !this.a) {
            var2 = var2 + "Port: not open (Connecting over the internet may fail)\n";
        }
        if ("ANY".equalsIgnoreCase(this.k)) {
            var2 = var2 + "Version: " + this.k + "\n";
        } else {
            var2 = var2 + "Version: v" + this.k + (this.g() ? "" : " (different game version!)") + "\n";
        }
        if (this.z != null && !this.z.equals("")) {
            var2 = var2 + "Mods Needed: " + this.z + "\n";
        }
        return var2;
    }

    public String c() {  // 02b g.c() L86
        return this.e;
    }

    public String e() {  // 02b g.e() L94: 杩炴帴鍦板潃
        return this.A == 0 ? this.c + ":" + this.g : "get|" + this.b.replace("|", ".") + "|" + this.A + "|" + this.m + "|" + this.g;
    }

    public String f() {  // 02b g.f() L98: LAN 鍦板潃
        return this.d + ":" + this.g;
    }



    public boolean d() {  // 02b j/g.java L90: 地址是否可用 (LicenseValidator$1 排序链)
        return this.e != null;
    }
    public boolean g() {  // 02b g.g() L102: 鐗堟湰鍖归厤
        GlobalState var1 = GlobalState.B();
        return this.x && "ANY".equals(this.k) ? true : (this.x && this.k != null && this.k.contains("+") && var1.c(true) >= this.l ? true : var1.c(true) == this.l);
    }
}
