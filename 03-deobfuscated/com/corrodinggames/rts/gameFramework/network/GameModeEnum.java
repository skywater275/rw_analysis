/*
 * Decompiled with CFR 0.152.
 * 02b 对应: j/ai.java (enum ai a/b/c) · 常量 a() 实现: ai$1/2/3 -> "Skirmish Map"/"Custom Map"/"Saved Game"
 *
 * ★ 2026-10-01 第 42 轮（PENDING RS-23）：**移除自定义构造器与常量实参**。
 *   原写法声明了 `private GameModeEnum(String string) {}` ⇒ javac 合成的构造器是
 *   `(String,int,String)`，而 stock 的是 **`(String,int)`** ⇒ 结构核对判不符（本族 4 个类）。
 *   依据：第 29 轮最小复现实测（`enum E { a {}, b {} }` 无自定义构造器 ⇒ `E$1/E$2` 的
 *   `<init>` 恰为 `(Ljava/lang/String;I)V` 且 `ACC_ENUM=True`，与 stock 逐字吻合）。
 *   本文件 **0 个 `extends GameModeEnum`** ⇒ 无「枚举不可被继承」风险 ✓
 */
package com.corrodinggames.rts.gameFramework.network;

public strictfp enum GameModeEnum {
    a {
        @Override
        public String a() {
            return "Skirmish Map";
        }
    },
    b {
        @Override
        public String a() {
            return "Custom Map";
        }
    },
    c {
        @Override
        public String a() {
            return "Saved Game";
        }
    };

    public abstract String a();
}
