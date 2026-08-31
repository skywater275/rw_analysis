/*
 * Decompiled with CFR 0.152.
 * 02b 对应: j/ai.java (enum ai a("skirmishMap")/b("customMap")/c("savedGame"))
 * 常量 a() 实现: ai$1/2/3 -> "Skirmish Map"/"Custom Map"/"Saved Game" (T0 javap 铁证)
 */
package com.corrodinggames.rts.gameFramework.network;

public strictfp enum GameModeEnum {
    a("skirmishMap") {
        @Override
        public String a() {
            return "Skirmish Map";
        }
    },
    b("customMap") {
        @Override
        public String a() {
            return "Custom Map";
        }
    },
    c("savedGame") {
        @Override
        public String a() {
            return "Saved Game";
        }
    };

    private GameModeEnum(String string) {
    }

    public abstract String a();
}
