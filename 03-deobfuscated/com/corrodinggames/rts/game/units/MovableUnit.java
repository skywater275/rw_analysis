/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.MovementController;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.projectiles.FactoryBuilding;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.rendering.Texture;

public strictfp abstract class MovableUnit
extends UnitType {

    public Texture v() {
        return null;
    }

    public strictfp float m() {  // 02b x.java L65-67
        return 30.0f;
    }


    public Texture d() {
        return BuildingBase.b;
    }


    public Texture k() {
        return null;
    }


    public Texture d(int n) {
        return null;
    }


    public boolean e() {
        return false;
    }

    public MovableUnit(boolean bl) {
        super(bl);
        this.T(20);
        this.U(20);
        this.ck = this.cj = 1.0f;
        this.bT = false;
        this.hp = this.maxHp = 100.0f;
        this.M = BuildingBase.b;
    }


    public void resetInactiveFlag() {
        this.bT = false;
    }


    public void a(float f2) {
        super.a(f2);
    }


    public void a(float f2, boolean bl) {
    }


    public float e(int n2) {
        return 0.0f;
    }


    public float f(int n2) {
        return 0.0f;
    }


    public boolean c(float f2) {
        return false;
    }


    public void a(UnitInstance am2, int n2) {
    }


    public float getMaxMoveDistance() {
        return 30.0f;
    }


    public float b(int n2) {
        return 100.0f;
    }


    public float z() {
        return 0.0f;
    }


    public float A() {
        return 4.8f;
    }


    public float B() {
        return 0.35f;
    }


    public float c(int n2) {
        return 99.0f;
    }


    public boolean l() {
        return false;
    }


    public float C() {
        return 0.04f;
    }


    public float D() {
        return 0.1f;
    }


    public boolean E() {
        return true;
    }


    public float g(int n2) {
        return 10.0f;
    }


    public boolean F() {
        return false;
    }


    public float G() {
        return 1.0f;
    }


    public float getShadowOffsetY() {
        return 1.0f;
    }


    public boolean u() {
        return true;
    }


    public boolean d(UnitInstance am2) {
        return false;
    }


    public boolean I() {
        return false;
    }


    public boolean J() {
        return true;
    }


    public float a(UnitInstance am2, float f2, MovementController f3) {
        f2 = 0.0f;
        return super.a(am2, f2, f3);
    }


    public boolean P() {
        return true;
    }


    public boolean Q() {
        return false;
    }


    public boolean i() {
        return true;
    }


    public MovementTypeEnum h() {
        return com.corrodinggames.rts.game.units.MovementTypeEnum.d;
    }
}
