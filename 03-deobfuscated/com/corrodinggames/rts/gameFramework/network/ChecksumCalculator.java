/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.network;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.WeaponAction;
import com.corrodinggames.rts.game.units.commands.CommandCenter;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.network.ChecksumField;
import com.corrodinggames.rts.gameFramework.GameObject;
import java.util.ArrayList;

public strictfp class ChecksumCalculator {
    public long a;
    public ArrayList<ChecksumField> b = new ArrayList<ChecksumField>();
    public ChecksumField c = new ChecksumField(this, "Unit Pos");
    public ChecksumField d = new ChecksumField(this, "Unit Dir", false);
    public ChecksumField e = new ChecksumField(this, "Unit Hp");
    public ChecksumField f = new ChecksumField(this, "Unit Id");
    public ChecksumField g = new ChecksumField(this, "Waypoints");
    public ChecksumField h = new ChecksumField(this, "Waypoints Pos");
    public ChecksumField i = new ChecksumField(this, "Team Credits");
    public ChecksumField j = new ChecksumField(this, "UnitPaths");
    public ChecksumField k = new ChecksumField(this, "Unit Count");
    public ChecksumField l = new ChecksumField(this, "Team Info", false);
    public ChecksumField m = new ChecksumField(this, "Team 1 Credits", false);
    public ChecksumField n = new ChecksumField(this, "Team 2 Credits", false);
    public ChecksumField o = new ChecksumField(this, "Team 3 Credits", false);
    public ChecksumField fieldCmdCenter2 = new ChecksumField(this, "Command center2", false);
    public ChecksumField q = new ChecksumField(this, "Command center3", false);

    public void a() {
        for (ChecksumField al2 : this.b) {
            al2.b = 0L;
        }
    }

    public void b() {
        this.a = 0L;
        this.a();
        for (GameObject bq2 : GameObject.er) {  // 02b ak L39: gameFramework/w=GameObject (F27)
            Object object;
            if (!(bq2 instanceof UnitType)) continue;  // 02b ak L43: units/y=UnitType
            UnitType y2 = (UnitType) bq2;
            this.a = (long)((float)this.a + y2.eo * 1000.0f);
            this.a = (long)((float)this.a + y2.ep * 1000.0f);
            this.a = (long)((float)this.a + y2.cu * 1.0f);
            this.a += y2.eh;
            this.c.b += (long)Float.floatToRawIntBits(y2.eo);
            this.c.b += (long)Float.floatToRawIntBits(y2.ep);
            this.d.b += (long)Float.floatToRawIntBits(y2.cg);
            this.e.b = (long)((float)this.e.b + y2.cu);
            this.f.b += y2.eh;
            if (bq2 instanceof CommandCenter) {  // 02b ak L54: units/d/e=CommandCenter
                object = (CommandCenter) y2;
                this.fieldCmdCenter2.b = (long)((float)this.fieldCmdCenter2.b + ((CommandCenter) object).f * 2.0f);
                this.q.b += (long)((CommandCenter) object).h;
            }
            if ((object = y2.ar()) != null) {
                this.g.b += ((WeaponAction) object).j();  // 02b ak L62: units/au=WeaponAction
                this.h.b = (long)((float)this.h.b + ((WeaponAction) object).g() * 1000.0f);
            }
            this.j.b += y2.computePathHash();
        }
        for (int i2 = 0; i2 < com.corrodinggames.rts.game.PlayerState.c; ++i2) {
            PlayerState bq2;  // 02b ak L71: game/n=PlayerState
            bq2 = com.corrodinggames.rts.game.PlayerState.u(i2);
            if (bq2 == null) continue;
            this.i.b += (long)((int)((PlayerState) bq2).o);
            if (i2 == 0) {
                this.m.b += (long)((int)((PlayerState) bq2).o);
            }
            if (i2 == 1) {
                this.n.b += (long)((int)((PlayerState) bq2).o);
            }
            if (i2 == 2) {
                this.o.b += (long)((int)((PlayerState) bq2).o);
            }
            this.k.b += (long)((PlayerState) bq2).w();
            this.l.b = this.l.b + (long)(i2 + ((PlayerState) bq2).x * 100 + ((PlayerState) bq2).r * 1000 + (((PlayerState) bq2).w ? i2 : 0) * 10000);
        }
    }
}
