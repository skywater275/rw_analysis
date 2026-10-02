/*
 * Decompiled with CFR 0.152.
 * 02 鍘熺: com.corrodinggames.rts.game.units.d.j 鈥?寤洪€犳寚浠ゆ暟鎹寘 (v19.112 閲嶅缓)
 * javap 閾佽瘉: 14 瀛楁 (a:int b:float c,d:custom.d.b e:custom.h f:boolean g:as h:PointF
 *   i:am j:a.c k,l:boolean m:float n:double) + 鏋勯€犲櫒 + a(j.as)/a(j.k) 搴忓垪鍖栧弻鏂规硶
 * 鏋勯€犲櫒瀛楄妭鐮? c=d.b.a; d=null; j=a.s.i (Field a/s.i:Lunits/a/c;); m=-1.0f; n=0.0d
 */
package com.corrodinggames.rts.game.units.commands;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.actions.ActionId;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.game.units.custom.resources.CustomActionBase;
import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.gameFramework.BaseGameObject;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;

public strictfp class BuilderUnit
extends BaseGameObject {
    public int builderId;
    public float buildSpeed;
    public CustomActionBase costResource;
    public CustomActionBase bonusResource;
    public UnitConfig buildTargetType;
    public boolean isCurrentlyBuilding;
    public UnitTypeHandle serializer;
    public PointF buildPosition;
    public UnitInstance targetUnit;
    public ActionId j;
    public boolean isAssisting;
    public boolean isRepairing;
    public float buildProgress;
    public double buildTimer;

    public BuilderUnit() {
        // 02 閾佽瘉: 鏋勯€犲櫒 j() { c=d.b.a; d=null; j=a.s.i; m=-1.0F; n=0.0D }
        this.costResource = com.corrodinggames.rts.game.units.custom.resources.CustomActionBase.a;
        this.bonusResource = null;
        this.j = com.corrodinggames.rts.game.units.actions.GameAction.i;
        this.buildProgress = -1.0F;
        this.buildTimer = 0.0;
    }

    @Override
    public void serializeToStream(OutputNetStream as2) {
        as2.a(-1);
        as2.a(this.builderId);
        as2.a(this.buildSpeed);
        as2.a(-1);
        as2.a(this.costResource.a());
        as2.a(this.isCurrentlyBuilding);
        as2.c(this.j.a());  // 02: var1.c(j.a())
        as2.c(this.j.a());  // 02: var1.c(j.a())
        as2.b(this.targetUnit);
        as2.a(this.buildPosition);
        as2.a(this.isRepairing);
        as2.a(this.buildProgress);
        as2.a(this.serializer);
        this.costResource.a(as2);
        com.corrodinggames.rts.game.units.custom.resources.CustomActionBase.a(as2, this.bonusResource);
        com.corrodinggames.rts.game.units.custom.TeamTag.serializeTags(this.buildTargetType, as2);  // 02b custom/g.java L87: g.a(h,as) 写方向 (旧误名 deserializeTags)
    }

    public void a(InputNetStream k2) {
        String.valueOf(k2.readInt());
        this.builderId = k2.readInt();
        this.buildSpeed = k2.readFloat();
        int n2 = 0;
        if (k2.b() >= 4) {
            this.j = com.corrodinggames.rts.game.units.actions.ActionId.a(String.valueOf(k2.readInt()));
        }
        if (k2.b() >= 6) {
            n2 = k2.readInt();
        }
        if (k2.b() >= 25) {
            this.isCurrentlyBuilding = k2.readBoolean();
        }
        if (k2.b() >= 33) {
            k2.readString();
            this.j = com.corrodinggames.rts.game.units.actions.ActionId.a(k2.readString());
        }
        if (k2.b() >= 61) {
            this.targetUnit = k2.o();
            this.buildPosition = k2.y();
        }
        if (k2.b() >= 64) {
            this.isRepairing = k2.readBoolean();
            this.buildProgress = k2.readFloat();
        }
        if (k2.b() >= 71) {
            this.serializer = k2.q();
        }
        this.costResource = k2.b() >= 73 ? com.corrodinggames.rts.game.units.custom.resources.CustomActionBase.b(k2) : com.corrodinggames.rts.game.units.custom.resources.CustomActionBase.a(n2);
        if (k2.b() >= 91) {
            this.bonusResource = com.corrodinggames.rts.game.units.custom.resources.CustomActionBase.a(k2);
        }
        if (k2.b() >= 95) {
            this.buildTargetType = com.corrodinggames.rts.game.units.custom.TeamTag.deserializeTags(k2);
        }
    }
}

