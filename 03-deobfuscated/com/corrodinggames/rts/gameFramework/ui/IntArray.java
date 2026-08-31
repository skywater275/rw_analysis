/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.ui;

public class IntArray {
    private int arraySize;
    private int[] dataArray;
    private int markPosition = -1;

    public IntArray(int n) {
        this.arraySize = 0;
        this.dataArray = new int[n];
    }

    public IntArray(int n, IntArray ad2) {
        this.arraySize = n;
        this.dataArray = new int[ad2.dataArray.length];
        for (int i = 0; i < this.dataArray.length; ++i) {
            this.dataArray[i] = ad2.dataArray[i];
        }
    }

    public void a(int n, int n2) {
        this.dataArray[n] = n2;
    }

    public float a(int n) {
        if (this.markPosition < 0) {
            this.markPosition = 0;
            for (int i = 0; i < this.dataArray.length; ++i) {
                if (this.dataArray[i] <= 0) continue;
                this.markPosition += this.dataArray[i];
            }
        }
        if (this.markPosition == 0 || this.dataArray[n] <= 0) {
            return 0.0f;
        }
        return (float)this.dataArray[n] / (float)this.markPosition;
    }

    static /* synthetic */ int a(IntArray ad2) {
        return ad2.arraySize;
    }
}
