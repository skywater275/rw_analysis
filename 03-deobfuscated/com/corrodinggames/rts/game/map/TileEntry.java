/*
 * Decompiled with CFR 0.152.
 * 02 原稿: com.corrodinggames.rts.game.b.k (19行) — 单瓦片条目 (v19.107 重建)
 * 03 曾错误容纳 b/b(MapEngine) 的完整副本 — 已剥离, MapEngine 是 b/b 唯一宿主
 */
package com.corrodinggames.rts.game.map;

import com.corrodinggames.rts.gameFramework.rendering.Texture;

public strictfp class TileEntry {
    static int a = 1;  // 02b b/k.java L12: static int a 计数器 (03 曾误与 d 字段同名)
    public boolean textureLoaded;  // v19.113u: 02b b — 图像已加载标志 (加载后 true, 清空循环 false)
    public String path;  // v19.113u: 02b c — 路径 (tilesets/bitmaps/ 前缀)
    public String tileId;  // v19.113u: 02b d — id (t+k.a 递增计数)
    public Texture texture;  // v19.113u: 02b e — 加载的图像 (m.e)
    public String tileName;  // v19.113u: 02b f — 名字 (equalsIgnoreCase 匹配)
    public String src;  // v19.113u: 02b g — base64 源字符串

    TileEntry() {
    }
}
