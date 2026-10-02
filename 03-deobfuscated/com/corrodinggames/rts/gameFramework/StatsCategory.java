/*
 * 02b bj.java 直译 (g/f=DataFieldProvider javap 铁证; GameRenderer 为幻觉名)
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.audio.DataFieldProvider;

public strictfp enum StatsCategory {
    a(com.corrodinggames.rts.gameFramework.audio.DataFieldProvider.b),
    b(com.corrodinggames.rts.gameFramework.audio.DataFieldProvider.c),
    c(com.corrodinggames.rts.gameFramework.audio.DataFieldProvider.d),
    d(com.corrodinggames.rts.gameFramework.audio.DataFieldProvider.e);

    DataFieldProvider e;  // 02b bj.java: g.f e

    private StatsCategory(DataFieldProvider dataFieldProvider) {
        this.e = dataFieldProvider;
    }

    public DataFieldProvider a() {
        return this.e;
    }
}
