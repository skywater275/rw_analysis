/* 02b gameFramework/d (CommandPathPart) 直译重建 — v19.115h
 * 字段: a=k.k(PathCostCalculator) b=long c-f=float g=int h=ao(MovementTypeEnum)
 * 方法: a(as)=序列化 / a(k)=反序列化 */
package com.corrodinggames.rts.gameFramework;

import java.io.IOException;

public class CommandPathPart {

    public com.corrodinggames.rts.gameFramework.pathfinding.PathCostCalculator a;
    public long b;
    public float c;
    public float d;
    public float e;
    public float f;
    public int g;
    public com.corrodinggames.rts.game.units.MovementTypeEnum h;

    /* 02b gameFramework/d: this.a.a 抛 IOException */
    public strictfp void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream var1) throws IOException {
        var1.a(this.b);
        var1.a(this.c);
        var1.a(this.d);
        var1.a(this.e);
        var1.a(this.f);
        var1.a(this.g);
        var1.a((Enum)this.h);
        var1.a(this.a != null);
        if (this.a != null) {
            this.a.a(var1);
        }
    }

    public strictfp void a(com.corrodinggames.rts.gameFramework.network.InputNetStream var1) {
        this.b = var1.i();
        this.c = var1.readFloat();
        this.d = var1.readFloat();
        this.e = var1.readFloat();
        this.f = var1.readFloat();
        this.g = var1.f();
        this.h = (com.corrodinggames.rts.game.units.MovementTypeEnum)var1.b(com.corrodinggames.rts.game.units.MovementTypeEnum.class);
        boolean var2 = var1.e();
        if (var2) {
            boolean var3 = false;
            this.a = new com.corrodinggames.rts.gameFramework.pathfinding.PathCostCalculator((com.corrodinggames.rts.gameFramework.pathfinding.PathFinder)null, var3);
            this.a.a(var1);
        }
    }
}
