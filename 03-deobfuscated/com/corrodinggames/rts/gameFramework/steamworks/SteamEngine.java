/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.steamworks;
import com.corrodinggames.rts.gameFramework.ByteIndexedMap;

import com.corrodinggames.rts.gameFramework.mods.ModInfo;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class SteamEngine {
    public static SteamEngine a = new SteamEngine();

    public static SteamEngine a() {
        return a;
    }

    public void b() {
        GlobalState.e("SteamEngine - blank init");
    }

    public void a(float f2) {
    }

    public String c() {
        return null;
    }

    public void d() {
        GlobalState.e("SteamEngine - disableSteam - already disabled");
    }

    public boolean e() {
        return !this.f();
    }

    public boolean f() {
        return true;
    }

    public void g() {
        GlobalState.e("disabledSteam - showInviteDialog");
        GlobalState.B().i("steam API not connected");
    }

    public void h() {
        GlobalState.e("Steam: alertNotEnabled");
        GlobalState l2 = GlobalState.B();
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
        GlobalState.e("disabledSteam - loadWorkshopMods");
    }

    public void m() {
        GlobalState.e("disabledSteam - showWorkshop");
    }

    public void a(ModInfo b2) {
        GlobalState.e("disabledSteam - showWorkshopMod");
    }

    public void b(ModInfo b2) {
        GlobalState.e("disabledSteam - publishWorkshopMod");
    }

    public void a(ModInfo b2, boolean bl, String string) {
        GlobalState.e("disabledSteam - uploadWorkshopMod");
    }
}
