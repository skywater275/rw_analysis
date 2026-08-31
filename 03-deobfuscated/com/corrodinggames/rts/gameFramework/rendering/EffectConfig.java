/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.rendering;
import com.corrodinggames.rts.gameFramework.AssetLoader;

import com.corrodinggames.rts.gameFramework.rendering.UpdateChecker$1;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$1;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$10;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$11;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$12;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$13;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$14;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$15;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$16;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$17;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$18;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$19;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$2;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$20;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$21;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$22;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$23;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$24;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$25;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$26;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$27;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$28;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$29;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$3;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$30;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$31;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$32;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$33;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$34;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$35;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$36;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$37;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$38;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$39;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$4;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$40;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$41;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$42;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$43;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$44;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$45;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$46;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$47;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$48;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$49;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$5;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$50;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$51;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$52;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$53;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$54;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$55;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$56;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$57;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$58;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$59;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$6;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$60;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$61;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$62;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$63;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$64;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$65;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$66;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$67;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$68;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$69;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$7;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$70;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$71;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$72;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$8;
import com.corrodinggames.rts.gameFramework.rendering.LicenseValidator$9;

public abstract class EffectConfig {
    public static final /* enum */ EffectConfig a = new EffectConfig$1();
    public static final /* enum */ EffectConfig b = new EffectConfig$12();
    public static final /* enum */ EffectConfig c = new EffectConfig$23();
    public static final /* enum */ EffectConfig d = new EffectConfig$34();
    public static final /* enum */ EffectConfig e = new EffectConfig$45();
    public static final /* enum */ EffectConfig f = new EffectConfig$56();
    public static final /* enum */ EffectConfig g = new EffectConfig$67();
    public static final /* enum */ EffectConfig h = new EffectConfig$71();
    public static final /* enum */ EffectConfig i = new EffectConfig$72();
    public static final /* enum */ EffectConfig j = new EffectConfig$2();
    public static final /* enum */ EffectConfig k = new EffectConfig$3();
    public static final /* enum */ EffectConfig l = new EffectConfig$4();
    public static final /* enum */ EffectConfig m = new EffectConfig$5();
    public static final /* enum */ EffectConfig n = new EffectConfig$6();
    public static final /* enum */ EffectConfig o = new EffectConfig$7();
    public static final /* enum */ EffectConfig effectTypeP = new EffectConfig$8();
    public static final /* enum */ EffectConfig q = new EffectConfig$9();
    public static final /* enum */ EffectConfig r = new EffectConfig$10();
    public static final /* enum */ EffectConfig s = new EffectConfig$11();
    public static final /* enum */ EffectConfig t = new EffectConfig$13();
    public static final /* enum */ EffectConfig u = new EffectConfig$14();
    public static final /* enum */ EffectConfig v = new EffectConfig$15();
    public static final /* enum */ EffectConfig effectTypeW = new EffectConfig$16();
    public static final /* enum */ EffectConfig x = new EffectConfig$17();
    public static final /* enum */ EffectConfig y = new EffectConfig$18();
    public static final /* enum */ EffectConfig z = new EffectConfig$19();
    public static final /* enum */ EffectConfig A = new EffectConfig$20();
    public static final /* enum */ EffectConfig B = new EffectConfig$21();
    public static final /* enum */ EffectConfig C = new EffectConfig$22();
    public static final /* enum */ EffectConfig D = new EffectConfig$24();
    public static final /* enum */ EffectConfig E = new EffectConfig$25();
    public static final /* enum */ EffectConfig F = new EffectConfig$26();
    public static final /* enum */ EffectConfig G = new EffectConfig$27();
    public static final /* enum */ EffectConfig H = new EffectConfig$28();
    public static final /* enum */ EffectConfig I = new EffectConfig$29();
    public static final /* enum */ EffectConfig J = new EffectConfig$30();
    public static final /* enum */ EffectConfig K = new EffectConfig$31();
    public static final /* enum */ EffectConfig L = new EffectConfig$32();
    public static final /* enum */ EffectConfig M = new EffectConfig$33();
    public static final /* enum */ EffectConfig N = new EffectConfig$35();
    public static final /* enum */ EffectConfig O = new EffectConfig$36();
    public static final /* enum */ EffectConfig P = new EffectConfig$37();
    public static final /* enum */ EffectConfig Q = new EffectConfig$38();
    public static final /* enum */ EffectConfig R = new EffectConfig$39();
    public static final /* enum */ EffectConfig S = new EffectConfig$40();
    public static final /* enum */ EffectConfig T = new EffectConfig$41();
    public static final /* enum */ EffectConfig U = new EffectConfig$42();
    public static final /* enum */ EffectConfig V = new EffectConfig$43();
    public static final /* enum */ EffectConfig W = new EffectConfig$44();
    public static final /* enum */ EffectConfig X = new EffectConfig$46();
    public static final /* enum */ EffectConfig Y = new EffectConfig$47();
    public static final /* enum */ EffectConfig Z = new EffectConfig$48();
    public static final /* enum */ EffectConfig aa = new EffectConfig$49();
    public static final /* enum */ EffectConfig ab = new EffectConfig$50();
    public static final /* enum */ EffectConfig ac = new EffectConfig$51();
    public static final /* enum */ EffectConfig ad = new EffectConfig$52();
    public static final /* enum */ EffectConfig ae = new EffectConfig$53();
    public static final /* enum */ EffectConfig af = new EffectConfig$54();
    public static final /* enum */ EffectConfig ag = new EffectConfig$55();
    public static final /* enum */ EffectConfig ah = new EffectConfig$57();
    public static final /* enum */ EffectConfig ai = new EffectConfig$58();
    public static final /* enum */ EffectConfig aj = new EffectConfig$59();
    public static final /* enum */ EffectConfig ak = new EffectConfig$60();
    public static final /* enum */ EffectConfig al = new EffectConfig$61();
    public static final /* enum */ EffectConfig am = new EffectConfig$62();
    public static final /* enum */ EffectConfig an = new EffectConfig$63();
    public static final /* enum */ EffectConfig ao = new EffectConfig$64();
    public static final /* enum */ EffectConfig ap = new EffectConfig$65();
    public static final /* enum */ EffectConfig aq = new EffectConfig$66();
    public static final /* enum */ EffectConfig ar = new EffectConfig$68();
    public static final /* enum */ EffectConfig as = new EffectConfig$69();
    public static final /* enum */ EffectConfig at = new EffectConfig$70();
    private static final /* synthetic */ EffectConfig[] au;

    public static EffectConfig[] values() {
        return (EffectConfig[])au.clone();
    }

    public static EffectConfig valueOf(String string) {
        for (EffectConfig effectConfig : au) {
            if (effectConfig.toString().equals(string)) {
                return effectConfig;
            }
        }
        throw new IllegalArgumentException("No enum constant EffectConfig." + string);
    }

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    public EffectConfig() {


    }

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    static {
        au = new EffectConfig[]{a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, effectTypeP, q, r, s, t, u, v, effectTypeW, x, y, z, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, aa, ab, ac, ad, ae, af, ag, ah, ai, aj, ak, al, am, an, ao, ap, aq, ar, as, at};
    }
}
