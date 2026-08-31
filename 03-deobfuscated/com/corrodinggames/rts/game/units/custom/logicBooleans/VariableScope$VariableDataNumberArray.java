/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableData;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDataArray;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDataNumber;

public class VariableScope$VariableDataNumberArray
extends VariableScope$VariableDataArray {
    float[] dataArray;
    public static final boolean trace = false;

    @Override
    public LogicBoolean$ReturnType getReturnType() {
        return LogicBoolean$ReturnType.numberArray;
    }

    @Override
    public LogicBoolean$ReturnType getElementReturnType() {
        return LogicBoolean$ReturnType.number;
    }

    @Override
    public float readNumberIndex(int n) {
        if (n < 0 || n >= this.size) {
            return 0.0f;
        }
        return this.dataArray[n];
    }

    public void setNumberIndex(int n, float f) {
        int n2;
        if (n < 0) {
            return;
        }
        if (n > 10000) {
            return;
        }
        if (this.dataArray == null) {
            n2 = n + 1;
            this.dataArray = new float[n2];
        }
        if (n >= this.dataArray.length) {
            int n3;
            n2 = 12;
            n3 = this.dataArray.length;  // 02b: int var4 = this.dataArray.length
            int n4 = n3 + (n3 < n2 / 2 ? n2 : n3 >> 1);  // 02b: var4 + (var4 < var7/2 ? var7 : var4>>1)
            if (n4 < n + 1) {
                n4 = n + 1;
            }
            float[] fArray = new float[n4];
            System.arraycopy(this.dataArray, 0, fArray, 0, n3);
            this.dataArray = fArray;
        }
        if (this.size < n + 1) {
            this.size = n + 1;
            if (this.size > this.dataArray.length) {
                throw new RuntimeException("size:" + this.size + ", dataArray.length:" + this.dataArray.length);
            }
        }
        this.dataArray[n] = f;
    }

    @Override
    public void shrink() {
        for (int i = 0; i < this.size; ++i) {
            if (this.dataArray[i] != 0.0f) continue;
            for (int j = i + 1; j < this.size; ++j) {
                this.dataArray[j - 1] = this.dataArray[j];
            }
            this.dataArray[this.size - 1] = 0.0f;
            --this.size;
            --i;
        }
    }

    @Override
    public void setDataAtIndex(VariableScope$VariableData variableScope$VariableData, int n) {
        this.setNumberIndex(n, variableScope$VariableData.readNumber(null));
    }

    @Override
    public VariableScope$VariableData readDataAtIndex(int n) {
        return new VariableScope$VariableDataNumber(this.readNumberIndex(n));
    }
}
