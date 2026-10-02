/*
 * Decompiled with CFR 0.152.
 * v19.117: 02b az.java extends w 铁证 — 父类 EffectConfig(枚举)为幻觉名, 应为 GameObject
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GameObject;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import java.io.IOException;

public strictfp abstract class SpriteBase
extends GameObject {
    public int ex = 0;

    protected SpriteBase(boolean bl) {
        super(bl);
    }


    /* 02b az.java L14: 子类 MobileBuilderBase.a 调 UnitFactoryHelper.a 抛 IOException → 父类声明 */
    public void a(OutputNetStream as2) throws IOException {  // 02b az.java L14: a(j.as) (PacketBuilder 为幻觉名)
        as2.d("xy is:");
        as2.a(this.eo);
        as2.a(this.ep);
        as2.a(this.eq);
        as2.a(this.ex);
        super.a(as2);
    }


    public void a(InputNetStream k2) {
        this.eo = k2.readFloat();
        this.ep = k2.readFloat();
        this.eq = k2.readFloat();
        this.ex = k2.readInt();
        super.a(k2);
    }
}
