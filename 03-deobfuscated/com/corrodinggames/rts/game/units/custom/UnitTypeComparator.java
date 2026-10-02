/*
 * Decompiled with CFR 0.152.
 * v19.133f6 整写: 02b game/q.java 铁证 — 阵营关系枚举 (原 implements Comparator 为错误类)
 */
package com.corrodinggames.rts.game.units.custom;

public enum UnitTypeComparator {
    own("own", 0),
    ally("ally", 1),
    allyNotOwn("allyNotOwn", 2),
    enemy("enemy", 3),
    neutral("neutral", 4),
    any("any", 5),
    notOwn("notOwn", 6);

    private UnitTypeComparator(String string, int n2) {
    }
}
