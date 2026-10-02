/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.opengl;

public class IntStack {
    private int[] stackData;
    private int stackPointer;

    public void a(int n) {
        if (this.stackData.length == this.stackPointer) {
            int[] nArray = new int[this.stackPointer + this.stackPointer];
            System.arraycopy(this.stackData, 0, nArray, 0, this.stackPointer);
            this.stackData = nArray;
        }
        this.stackData[this.stackPointer++] = n;
    }
}
