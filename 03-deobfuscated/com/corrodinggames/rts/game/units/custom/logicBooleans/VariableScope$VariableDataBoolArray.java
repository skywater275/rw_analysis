/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableData;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDataArray;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDataBoolean;

public class VariableScope$VariableDataBoolArray
extends VariableScope$VariableDataArray {
    boolean[] dataArray;

    @Override
    public LogicBoolean$ReturnType getReturnType() {
        return LogicBoolean$ReturnType.boolArray;
    }

    @Override
    public LogicBoolean$ReturnType getElementReturnType() {
        return LogicBoolean$ReturnType.bool;
    }

    @Override
    public boolean readBooleanIndex(int n) {
        if (n < 0 || n >= this.size) {
            return false;
        }
        return this.dataArray[n];
    }

    public void setBooleanIndex(int n, boolean bl) {
        int n2;
        if (n < 0) {
            return;
        }
        if (n > 10000) {
            return;
        }
        if (this.dataArray == null) {
            n2 = n + 1;
            this.dataArray = new boolean[n2];
        }
        if (n >= this.dataArray.length) {
            int n3;
            n2 = 12;
            n3 = this.dataArray.length;  // 02b L36: int var4 = this.dataArray.length
            int n4 = n3 + (n3 < n2 / 2 ? n2 : n3 >> 1);  // 02b L37: var4 + (var4 < var7/2 ? var7 : var4>>1)
            if (n4 < n + 1) {
                n4 = n + 1;
            }
            boolean[] blArray = new boolean[n4];
            System.arraycopy(this.dataArray, 0, blArray, 0, n3);
            this.dataArray = blArray;
        }
        if (this.size < n + 1) {
            this.size = n + 1;
            if (this.size > this.dataArray.length) {
                throw new RuntimeException("size:" + this.size + ", dataArray.length:" + this.dataArray.length);
            }
        }
        this.dataArray[n] = bl;
    }

    @Override
    public void shrink() {
        for (int i = 0; i < this.size; ++i) {
            if (this.dataArray[i]) continue;
            for (int j = i + 1; j < this.size; ++j) {
                this.dataArray[j - 1] = this.dataArray[j];
            }
            this.dataArray[this.size - 1] = false;
            --this.size;
            --i;
        }
    }

    @Override
    public void setDataAtIndex(VariableScope$VariableData variableScope$VariableData, int n) {
        this.setBooleanIndex(n, variableScope$VariableData.read(null));
    }

    @Override
    public VariableScope$VariableData readDataAtIndex(int n) {
        return new VariableScope$VariableDataBoolean(this.readBooleanIndex(n));
    }
}
