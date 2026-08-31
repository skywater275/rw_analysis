/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableData;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDataArray;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDataUnit;

public class VariableScope$VariableDataUnitArray
extends VariableScope$VariableDataArray {
    UnitInstance[] dataArray;

    @Override
    public LogicBoolean$ReturnType getReturnType() {
        return LogicBoolean$ReturnType.unitArray;
    }

    @Override
    public LogicBoolean$ReturnType getElementReturnType() {
        return LogicBoolean$ReturnType.unit;
    }

    @Override
    public UnitInstance readUnitIndex(int n) {
        if (n < 0 || n >= this.size) {
            return null;
        }
        return this.dataArray[n];
    }

    public void setUnitIndex(int n, UnitInstance am2) {
        int n2;
        if (n < 0) {
            return;
        }
        if (n > 10000) {
            return;
        }
        if (this.dataArray == null) {
            n2 = n + 1;
            this.dataArray = new UnitInstance[n2];
        }
        if (n >= this.dataArray.length) {
            int n3;
            n2 = 12;
            n3 = this.dataArray.length;  // 02b: int var4 = this.dataArray.length
            int n4 = n3 + (n3 < n2 / 2 ? n2 : n3 >> 1);  // 02b: var4 + (var4 < var7/2 ? var7 : var4>>1)
            if (n4 < n + 1) {
                n4 = n + 1;
            }
            UnitInstance[] amArray = new UnitInstance[n4];
            System.arraycopy(this.dataArray, 0, amArray, 0, n3);
            this.dataArray = amArray;
        }
        if (this.size < n + 1) {
            this.size = n + 1;
            if (this.size > this.dataArray.length) {
                throw new RuntimeException("size:" + this.size + ", dataArray.length:" + this.dataArray.length);
            }
        }
        this.dataArray[n] = am2;
    }

    @Override
    public void setDataAtIndex(VariableScope$VariableData variableScope$VariableData, int n) {
        this.setUnitIndex(n, variableScope$VariableData.readUnit(null));
    }

    @Override
    public VariableScope$VariableData readDataAtIndex(int n) {
        return new VariableScope$VariableDataUnit(this.readUnitIndex(n));
    }

    @Override
    public void shrink() {
        for (int i = 0; i < this.size; ++i) {
            UnitInstance am2 = this.dataArray[i];
            if (am2 != null && (VariableScope.isMarker(am2) || !am2.isDead)) continue;
            for (int i2 = i + 1; i2 < this.size; ++i2) {
                this.dataArray[i2 - 1] = this.dataArray[i2];
            }
            this.dataArray[this.size - 1] = null;
            --this.size;
            --i;
        }
    }
}
