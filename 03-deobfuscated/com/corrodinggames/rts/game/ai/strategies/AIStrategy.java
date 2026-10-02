/*
 * v19.133f7c 整写: 02b game/a/a/b.java 铁证 — AI 策略枚举 (unknown/nuking, 抽象 a())
 * 常量实现: 02b b$1/b$2 javap — a() 返回 null
 */
package com.corrodinggames.rts.game.ai.strategies;

public enum AIStrategy {
    unknown("unknown", 0) {
        public AIStrategyResult a() {
            return null;  // 02b b$1: a() → null
        }
    },
    nuking("nuking", 1) {
        public AIStrategyResult a() {
            return null;  // 02b b$2: a() → null
        }
    };

    private AIStrategy(String string, int n2) {}

    public abstract AIStrategyResult a();
}
