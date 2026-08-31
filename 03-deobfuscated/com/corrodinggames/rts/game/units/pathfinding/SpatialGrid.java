/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.pathfinding;
import com.corrodinggames.rts.game.units.actions.StopAction;
import com.corrodinggames.rts.game.units.commands.ExperimentalGroundUnit;
import com.corrodinggames.rts.game.units.projectiles.WallBuilding;
import com.corrodinggames.rts.game.units.ExperimentalLandUnit;
import com.corrodinggames.rts.game.units.projectiles.AbstractSubBuilding;
import com.corrodinggames.rts.game.units.TimedBomb;
import com.corrodinggames.rts.game.units.WaterUnit;
import com.corrodinggames.rts.game.units.commands.CustomGroundUnit;

import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.pathfinding.SpatialGridCell;
import com.corrodinggames.rts.game.units.pathfinding.UnitArrayList;
import com.corrodinggames.rts.game.units.pathfinding.RectFilter;
import com.corrodinggames.rts.game.units.pathfinding.UnitFilter;
import com.corrodinggames.rts.game.units.pathfinding.QueryResult;
import com.corrodinggames.rts.game.units.pathfinding.CircleFilter;
import com.corrodinggames.rts.game.units.pathfinding.LineFilter;
import com.corrodinggames.rts.game.units.pathfinding.SpatialQuery;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.UnitInstanceList;

public final class SpatialGrid {
    int a;
    int b;
    float c;
    float d;
    public SpatialGridCell[][] e = null;
    RectFilter f = new RectFilter();
    CircleFilter g = new CircleFilter();
    LineFilter h = new LineFilter();
    final UnitInstanceList i = new UnitInstanceList();  // 02b f/c.java L84: u var4 (TimedBomb/UnitRegistry 为幻觉名)
    final QueryResult j = new QueryResult();
    final Rect k = new Rect();
    final int l = 32;
    int m;

    public void a(float f2, float f3, float f4, UnitType y2, float f5, SpatialQuery i2) {  // 02b f.c.a(float,float,float,y,float,f.i)
        float f6 = f2 - f4;
        float f7 = f3 - f4;
        float f8 = f2 + f4;
        float f9 = f3 + f4;
        this.g.a(f6, f7, f8, f9);
        this.a(this.g.boundsRect, this.g, y2, f5, i2);
    }

    public final QueryResult a(float f2, float f3, float f4) {
        UnitInstanceList u2 = this.i;
        u2.clear();
        this.a(f2, f3, f4, u2);
        this.j.reset(u2);
        return this.j;
    }

    public final void a(float f2, float f3, float f4, UnitInstanceList u2) {  // 02b L53: a(float,float,float,u)
        SpatialGridCell[][] aArray = this.e;
        float f5 = f2 - f4;
        float f6 = f2 + f4;
        float f7 = f3 - f4;
        float f8 = f3 + f4;
        int n2 = this.a(f5);
        int n3 = this.a(f6);
        int n4 = this.b(f7);
        int n5 = this.b(f8);
        for (int j = n2; j <= n3; ++j) {
            for (int i2 = n4; i2 <= n5; ++i2) {
                UnitArrayList b2 = aArray[j][i2].unitListA;
                UnitInstance[] amArray = b2.a();
                int n6 = b2.b;
                for (int i3 = 0; i3 < n6; ++i3) {
                    UnitInstance am2 = amArray[i3];
                    float f9 = am2.eo;
                    float f10 = am2.ep;
                    if (!(f5 <= f9) || !(f9 <= f6) || !(f7 <= f10) || !(f10 <= f8)) continue;
                    u2.a(am2);
                }
            }
        }
    }

    public final QueryResult b(float f2, float f3, float f4) {
        UnitInstanceList u2 = this.i;
        u2.clear();
        this.b(f2, f3, f4, u2);
        this.j.reset(u2);
        return this.j;
    }

    public final void b(float f2, float f3, float f4, UnitInstanceList u2) {  // 02b L91: b(float,float,float,u)
        SpatialGridCell[][] aArray = this.e;
        float f5 = f2 - f4;
        float f6 = f2 + f4;
        float f7 = f3 - f4;
        float f8 = f3 + f4;
        float f9 = 50.0f;
        int n2 = this.a(f5 - 50.0f);
        int n3 = this.a(f6 + 50.0f);
        int n4 = this.b(f7 - 50.0f);
        int n5 = this.b(f8 + 50.0f);
        for (int j = n2; j <= n3; ++j) {
            for (int i2 = n4; i2 <= n5; ++i2) {
                UnitArrayList b2 = aArray[j][i2].unitListA;
                UnitInstance[] amArray = b2.a();
                int n6 = b2.b;
                for (int i3 = 0; i3 < n6; ++i3) {
                    UnitInstance am2 = amArray[i3];
                    float f10 = am2.eo;
                    float f11 = am2.ep;
                    float f12 = am2.cj;
                    if (!(f5 - f12 <= f10) || !(f10 <= f6 + f12) || !(f7 - f12 <= f11) || !(f11 <= f8 + f12)) continue;
                    u2.b(am2);
                }
            }
        }
    }

    public final void a(PlayerState n2, float f2, float f3, float f4, UnitInstanceList u2) {  // 02b L123
        SpatialGridCell[][] aArray = this.e;
        float f5 = f2 - f4;
        float f6 = f2 + f4;
        float f7 = f3 - f4;
        float f8 = f3 + f4;
        float f9 = 50.0f;
        int n3 = this.a(f5 - 50.0f);
        int n4 = this.a(f6 + 50.0f);
        int n5 = this.b(f7 - 50.0f);
        int n6 = this.b(f8 + 50.0f);
        int n7 = n2.k;
        for (int j = n3; j <= n4; ++j) {
            for (int i2 = n5; i2 <= n6; ++i2) {
                UnitArrayList b2 = aArray[j][i2].unitListByCategory[n7];
                UnitInstance[] amArray = b2.a();
                int n8 = b2.b;
                for (int i3 = 0; i3 < n8; ++i3) {
                    UnitInstance am2 = amArray[i3];
                    float f10 = am2.eo;
                    float f11 = am2.ep;
                    float f12 = am2.cj;
                    if (!(f5 - f12 <= f10) || !(f10 <= f6 + f12) || !(f7 - f12 <= f11) || !(f11 <= f8 + f12)) continue;
                    u2.b(am2);
                }
            }
        }
    }

    public void a(RectF rectF, UnitFilter e2, UnitType y2, float f2, SpatialQuery i2) {  // 02b f.c.a(RectF,f.e,y,float,f.i)
        SpatialGridCell[][] aArray = this.e;
        int n2 = this.a(rectF.a);
        int n3 = this.a(rectF.c);
        int n4 = this.b(rectF.b);
        int n5 = this.b(rectF.d);
        PlayerState n6 = null;
        int n7 = i2.excludeTeam(y2);
        if (n7 != -2 && n7 != -3) {
            n6 = com.corrodinggames.rts.game.PlayerState.k(n7);
        }
        PlayerState n8 = i2.onlyEnemiesOfTeam(y2);
        PlayerState n9 = i2.onlyTeam(y2);
        i2.setup(y2, f2);
        if (n8 == null && n9 == null) {
            for (int i3 = n2; i3 <= n3; ++i3) {
                for (int i4 = n4; i4 <= n5; ++i4) {
                    UnitArrayList b2 = aArray[i3][i4].unitListA;
                    UnitInstance[] amArray = b2.a();
                    int n10 = b2.b;
                    for (int i5 = 0; i5 < n10; ++i5) {
                        UnitInstance am2 = amArray[i5];
                        if (n6 != null && am2.player == n6 || !e2.a(am2)) continue;
                        i2.callback(y2, f2, am2);
                    }
                }
            }
        } else if (n9 != null) {
            int n11 = n9.k;
            if (n11 == -1) {
                for (int i6 = n2; i6 <= n3; ++i6) {
                    for (int i7 = n4; i7 <= n5; ++i7) {
                        UnitArrayList b3 = aArray[i6][i7].unitListDynamic;
                        if (b3.b <= 0) continue;
                        UnitInstance[] amArray = b3.a();
                        int n12 = b3.b;
                        for (int i8 = 0; i8 < n12; ++i8) {
                            UnitInstance am3 = amArray[i8];
                            if (!e2.a(am3)) continue;
                            i2.callback(y2, f2, am3);
                        }
                    }
                }
            } else if (n11 == -2) {
                for (int i9 = n2; i9 <= n3; ++i9) {
                    for (int i10 = n4; i10 <= n5; ++i10) {
                        UnitArrayList b4 = aArray[i9][i10].unitListAll;
                        if (b4.b <= 0) continue;
                        UnitInstance[] amArray = b4.a();
                        int n13 = b4.b;
                        for (int i11 = 0; i11 < n13; ++i11) {
                            UnitInstance am4 = amArray[i11];
                            if (!e2.a(am4)) continue;
                            i2.callback(y2, f2, am4);
                        }
                    }
                }
            } else {
                for (int i12 = n2; i12 <= n3; ++i12) {
                    for (int i13 = n4; i13 <= n5; ++i13) {
                        UnitArrayList b5 = aArray[i12][i13].unitListByCategory[n11];
                        if (b5.b <= 0) continue;
                        UnitInstance[] amArray = b5.a();
                        int n14 = b5.b;
                        for (int i14 = 0; i14 < n14; ++i14) {
                            UnitInstance am5 = amArray[i14];
                            if (!e2.a(am5)) continue;
                            i2.callback(y2, f2, am5);
                        }
                    }
                }
            }
        } else {
            int n15;
            Object object;
            int n16;
            int n17;
            if (n8 != com.corrodinggames.rts.game.PlayerState.h) {
                for (n17 = n2; n17 <= n3; ++n17) {
                    for (n16 = n4; n16 <= n5; ++n16) {
                        object = aArray[n17][n16].unitListAll;
                        if (((UnitArrayList) object).b <= 0) continue;
                        UnitInstance[] amArray = ((UnitArrayList) object).a();
                        int n18 = ((UnitArrayList) object).b;
                        for (n15 = 0; n15 < n18; ++n15) {
                            UnitInstance am6 = amArray[n15];
                            if (!e2.a(am6)) continue;
                            i2.callback(y2, f2, am6);
                        }
                    }
                }
            }
            n17 = this.m;
            for (n16 = 0; n16 <= n17; ++n16) {
                object = com.corrodinggames.rts.game.PlayerState.k(n16);
                if (object == null || n8 == object || !n8.c((PlayerState) object)) continue;
                for (int i15 = n2; i15 <= n3; ++i15) {
                    for (n15 = n4; n15 <= n5; ++n15) {
                        UnitArrayList b6 = aArray[i15][n15].unitListByCategory[n16];
                        int n19 = b6.b;
                        if (n19 <= 0) continue;
                        UnitInstance[] amArray = b6.a();
                        for (int i16 = 0; i16 < n19; ++i16) {
                            UnitInstance am7 = amArray[i16];
                            if (!e2.a(am7)) continue;
                            i2.callback(y2, f2, am7);
                        }
                    }
                }
            }
        }
    }

    public final int a(float f2) {
        int n2 = (int)(f2 * this.c);
        if (n2 < 0) {
            n2 = 0;
        }
        if (n2 >= 32) {
            n2 = 31;
        }
        return n2;
    }

    public final int b(float f2) {
        int n2 = (int)(f2 * this.d);
        if (n2 < 0) {
            n2 = 0;
        }
        if (n2 >= 32) {
            n2 = 31;
        }
        return n2;
    }

    public void a() {
        float f2 = this.c;
        float f3 = this.d;
        UnitInstance[] amArray = com.corrodinggames.rts.game.units.UnitInstance.bE.a();
        int n2 = com.corrodinggames.rts.game.units.UnitInstance.bE.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            UnitInstance am2 = amArray[i2];
            if (!am2.isDead && (int)(am2.eo * f2) == am2.factorySlotIndex2 && (int)(am2.ep * f3) == am2.dm && am2.player != null && am2.dn == am2.player.k) continue;
            this.a(am2);
        }
    }

    public void a(UnitInstance am2) {
        if (this.e == null) {
            if (com.corrodinggames.rts.gameFramework.GlobalState.B().bx != 0) {
                com.corrodinggames.rts.gameFramework.GlobalState.b("updateUnitGeoIndex: areaList not active");
            }
            am2.factorySlotIndex2 = -1;
            am2.dm = -1;
            return;
        }
        if (am2.isDead) {
            if (am2.factorySlotIndex2 != -1 && am2.dm != -1) {
                this.e[am2.factorySlotIndex2][am2.dm].b(am2);
                am2.factorySlotIndex2 = -1;
                am2.dm = -1;
            }
            return;
        }
        int n2 = this.a(am2.eo);
        int n3 = this.b(am2.ep);
        int n4 = -2;
        if (am2.player != null) {
            n4 = am2.player.k;
        }
        if (am2.factorySlotIndex2 == n2 && am2.dm == n3 && am2.dn == n4) {
            return;
        }
        if (am2.factorySlotIndex2 != -1 && am2.dm != -1) {
            this.e[am2.factorySlotIndex2][am2.dm].b(am2);
        }
        am2.factorySlotIndex2 = n2;
        am2.dm = n3;
        am2.dn = n4;
        if (n4 > this.m && this.m < com.corrodinggames.rts.game.PlayerState.c) {
            this.m = n4;
        }
        this.e[am2.factorySlotIndex2][am2.dm].a(am2);
    }

    public void a(com.corrodinggames.rts.game.map.MapEngine b2) {
        this.e = new SpatialGridCell[32][32];
        this.m = 0;
        for (int i2 = 0; i2 < 32; ++i2) {
            for (int i3 = 0; i3 < 32; ++i3) {
                this.e[i2][i3] = new SpatialGridCell();
            }
        }
        this.a = b2.mapHeight * b2.tilePixelWidth / 32;
        this.b = b2.tileWidth * b2.tilePixelHeight / 32;
        this.c = 1.0f / (float)this.a;
        this.d = 1.0f / (float)this.b;
    }

    public void b() {
        this.e = null;
    }

    public void c(float f2) {
    }
}
