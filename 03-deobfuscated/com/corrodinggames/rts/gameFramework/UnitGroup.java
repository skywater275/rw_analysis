/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.SaveFileHandler;

public strictfp class UnitGroup {
    public int groupId;
    public String groupName;
    public int unitCount;
    public boolean isActive;
    public boolean isAttacking;
    public boolean isDefending;
    public long creationTime;
    public int targetZoneId;
    final /* synthetic */ SaveFileHandler i;

    public UnitGroup(SaveFileHandler be2) {
        this.i = be2;
    }
}
