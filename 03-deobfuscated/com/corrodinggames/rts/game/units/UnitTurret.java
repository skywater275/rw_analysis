/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.UnitInstance;

public strictfp final class UnitTurret {
    public float turretAngle;  // v19.113l U() 铁证: 02b ap.a "Dir was:" 炮塔角度 (旧无源猜测 turretAngle)
    public float turretOffsetY;
    public float turretPivotX;
    public float lockDelay;  // v19.113l U() 铁证: 02b ap.d "lockDelay:" 锁定延迟 (旧无源猜测 lockDelay)
    public float shootCooldown;  // v19.113l U() 铁证: 02b ap.e "shootCooldown:" 射击冷却 (旧无源猜测 shootCooldown)
    public float e;  // 02b ap.e (字节码保序: maxRotationAngle(d) 之后)
    public float f;  // 02b ap.f
    public float maxRotationAngle;
    public boolean hasLimitedArc;
    public float minAngleLimit;
    public float maxAngleLimit;
    public UnitInstance targetUnit;
    public float currentAngle;
    public float targetAngle;
    public boolean isTurning;
    public float h;  // 02b ap.h (后坐力 x 偏移, M(int) 重置)
    public float i;  // 02b ap.i (后坐力 y 偏移)

    public void a(float f) {
        this.turretOffsetY = this.turretAngle = maxRotationAngle;
        this.turretPivotX = 0.0f;
        this.lockDelay = 0.0f;
        this.shootCooldown = 0.0f;
        this.maxRotationAngle = 0.0f;
        this.hasLimitedArc = false;
        this.minAngleLimit = 0.0f;
        this.maxAngleLimit = 0.0f;
        this.targetUnit = null;
        this.currentAngle = 0.0f;
        this.targetAngle = 0.0f;
        this.isTurning = false;
    }

    public final void a(int n) {
        if (this.lockDelay < (float)n && this.lockDelay >= 0.0f) {
            this.lockDelay = n;
        }
    }

    public final void b(int n) {
        if (this.lockDelay > (float)(-n)) {
            this.lockDelay = -n;
        }
    }

    public final boolean a() {
        return this.lockDelay == 0.0f;
    }

    public final boolean b() {
        return this.lockDelay < 0.0f;
    }
}
