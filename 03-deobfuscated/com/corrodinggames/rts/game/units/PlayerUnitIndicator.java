/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.gameFramework.effects.GameHUD;

// 每玩家单位指示器状态 (02 an) — v19.113m T0 修正: 旧名 BuildTask 错译 (建造任务)
// javap 铁证: boolean a / int b / gameFramework.d.a(GameHUD) c
// 语义链: am.java:2032 g(PlayerState) 发现标记 (a=true, b=V() 快照) + am.java:2055 cX() 迷雾指示器 (c=GameHUD)
public strictfp class PlayerUnitIndicator {
    boolean discovered = false;   // a: 该玩家已发现此单位
    int typeValue;                // b: 发现时的 V() 类型值快照
    com.corrodinggames.rts.gameFramework.effects.GameHUD indicator;            // c: 02b 铁证 an.c = gameFramework.d.a (GameHUD) 迷雾指示器
}
