/*
 * v19.117 重写: 02b f/t.java 字段保序对齐 (tileX/tileY 等为语义误名, Minimap.java 用混淆名访问)
 */
package com.corrodinggames.rts.gameFramework.ui;

import com.corrodinggames.rts.gameFramework.ui.Minimap;

class MinimapTile {
    public int a;  // 02b t.java L7: 瓦片x
    public int b;  // L8: 瓦片y
    public int c;  // L9: 屏幕x
    public int d;  // L10: 屏幕y
    public boolean e;  // L11: 可见
    final /* synthetic */ Minimap f;  // L13: o f (ChatInputPanel 为幻觉名)

    public MinimapTile(Minimap o2, int n, int n2) {
        this.f = o2;
        this.a = n;
        this.b = n2;
    }
}
