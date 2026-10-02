/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.f;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.e.b;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.m.e;

public strictfp abstract class x
extends y {
    @Override
    public e v() {
        return null;
    }

    @Override
    public e d() {
        return b.b;
    }

    @Override
    public e k() {
        return null;
    }

    @Override
    public e d(int n) {
        return null;
    }

    @Override
    public boolean e() {
        return false;
    }

    public x(boolean bl) {
        super(bl);
        this.T(20);
        this.U(20);
        this.ck = this.cj = 1.0f;
        this.bT = false;
        this.cu = this.cv = 100.0f;
        this.M = b.b;
    }

    @Override
    public void f_() {
        this.bT = false;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
    }

    @Override
    public void a(float f2, boolean bl) {
    }

    @Override
    public float e(int n2) {
        return 0.0f;
    }

    @Override
    public float f(int n2) {
        return 0.0f;
    }

    @Override
    public boolean c(float f2) {
        return false;
    }

    @Override
    public void a(am am2, int n2) {
    }

    @Override
    public float m() {
        return 30.0f;
    }

    @Override
    public float b(int n2) {
        return 100.0f;
    }

    @Override
    public float z() {
        return 0.0f;
    }

    @Override
    public float A() {
        return 4.8f;
    }

    @Override
    public float B() {
        return 0.35f;
    }

    @Override
    public float c(int n2) {
        return 99.0f;
    }

    @Override
    public boolean l() {
        return false;
    }

    @Override
    public float C() {
        return 0.04f;
    }

    @Override
    public float D() {
        return 0.1f;
    }

    @Override
    public boolean E() {
        return true;
    }

    @Override
    public float g(int n2) {
        return 10.0f;
    }

    @Override
    public boolean F() {
        return false;
    }

    @Override
    public float G() {
        return 1.0f;
    }

    @Override
    public float H() {
        return 1.0f;
    }

    @Override
    public boolean u() {
        return true;
    }

    @Override
    public boolean d(am am2) {
        return false;
    }

    @Override
    public boolean I() {
        return false;
    }

    @Override
    public boolean J() {
        return true;
    }

    @Override
    public float a(am am2, float f2, f f3) {
        f2 = 0.0f;
        return super.a(am2, f2, f3);
    }

    @Override
    public boolean P() {
        return true;
    }

    @Override
    public boolean Q() {
        return false;
    }

    @Override
    public boolean i() {
        return true;
    }

    @Override
    public ao h() {
        return com.corrodinggames.rts.game.units.ao.d;
    }
}
