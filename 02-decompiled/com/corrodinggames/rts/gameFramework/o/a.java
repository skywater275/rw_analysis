/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.o;

import com.corrodinggames.rts.gameFramework.i.b;
import com.corrodinggames.rts.gameFramework.l;

public class a {
    public static a a = new a();

    public static a a() {
        return a;
    }

    public void b() {
        l.e("SteamEngine - blank init");
    }

    public void a(float f2) {
    }

    public String c() {
        return null;
    }

    public void d() {
        l.e("SteamEngine - disableSteam - already disabled");
    }

    public boolean e() {
        return !this.f();
    }

    public boolean f() {
        return true;
    }

    public void g() {
        l.e("disabledSteam - showInviteDialog");
        l.B().i("steam API not connected");
    }

    public void h() {
        l.e("Steam: alertNotEnabled");
        l l2 = l.B();
        if (l2 != null) {
            l2.i("steam API not connected");
        }
    }

    public void i() {
    }

    public void j() {
    }

    public void k() {
    }

    public void l() {
        l.e("disabledSteam - loadWorkshopMods");
    }

    public void m() {
        l.e("disabledSteam - showWorkshop");
    }

    public void a(b b2) {
        l.e("disabledSteam - showWorkshopMod");
    }

    public void b(b b2) {
        l.e("disabledSteam - publishWorkshopMod");
    }

    public void a(b b2, boolean bl, String string) {
        l.e("disabledSteam - uploadWorkshopMod");
    }
}
