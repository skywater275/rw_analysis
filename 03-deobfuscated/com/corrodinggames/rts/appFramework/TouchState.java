/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.appFramework;

import com.corrodinggames.rts.appFramework.MultiTouchHandler;

public class TouchState {
    private int activePointer;
    private float[] touchAreaX = new float[10];
    private float[] touchAreaY = new float[10];
    private float[] lastPointerX = new float[10];
    private int[] lastPointerId = new int[10];
    private float downStartX;
    private float downStartY;
    private float currentX;
    private float currentY;
    private float dragDistance;
    private boolean isDown;
    private boolean wasDown;
    private boolean justPressed;
    private int clickCount;
    private boolean isDragging;
    private boolean isLongPress;
    private boolean isDoubleClick;
    private int gestureType;

    public TouchState() {
        int clickCount;
        for (int n = 0; n < this.touchAreaX.length; ++n) {  // 02b appFramework/m var1 (v19.133f2 修正)
            this.touchAreaX[n] = 40.0f;
        }
        for (int n = 0; n < this.touchAreaY.length; ++n) {
            this.touchAreaY[n] = 40.0f;
        }
    }

    public int a() {
        return this.clickCount;
    }

    public boolean b() {
        return this.justPressed;
    }

    public void c() {
        this.justPressed = this.isDown;
        this.clickCount = this.activePointer;
    }

    public void a(float f, float f2) {
        this.touchAreaX[0] = downStartX;
        this.touchAreaY[0] = f2;
        this.downStartX = this.touchAreaX[0];
        this.downStartY = this.touchAreaY[0];
        this.dragDistance = 0.0f;
        this.currentY = 0.0f;
    }

    public void a(float f, float f2, boolean bl, int n) {
        this.gestureType = 0;
        int n2 = this.activePointer = bl ? 1 : 0;
        if (n != -1) {
            MultiTouchHandler.a()[0] = clickCount;
        }
        this.touchAreaX[0] = downStartX;
        this.touchAreaY[0] = f2;
        this.lastPointerX[0] = 0.0f;
        this.lastPointerId[0] = 0;
        this.isDown = bl;
        this.wasDown = false;
        if (this.isDown) {
            this.justPressed = this.isDown;
        }
        if (this.activePointer > 0) {
            this.clickCount = this.activePointer;
        }
        this.downStartX = this.touchAreaX[0];
        this.downStartY = this.touchAreaY[0];
        this.currentX = this.lastPointerX[0];
        this.dragDistance = 0.0f;
        this.currentY = 0.0f;
        this.isDoubleClick = false;
        this.isLongPress = false;
        this.isDragging = false;
    }

    public float[] d() {
        return this.touchAreaX;
    }

    public int[] e() {
        return MultiTouchHandler.a();
    }

    public float[] f() {
        return this.touchAreaY;
    }
}
