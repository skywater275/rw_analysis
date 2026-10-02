/*
 * v19.133f7c 整写: 02b game/a/a/a.java 铁证 — AI 策略行为抽象类 (原模拟枚举 extends Enum 为错误翻译)
 */
package com.corrodinggames.rts.game.ai.strategies;

import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;

public abstract class AIStrategyResult {

    public abstract AIStrategy a();  // 02b a/a/a.java L10: abstract b a() (b=AIStrategy 枚举)

    public void a(float f2, com.corrodinggames.rts.game.ai.AIStrategy a2) {}

    public void b(float f2, com.corrodinggames.rts.game.ai.AIStrategy a2) {}

    public void a(InputNetStream k2) {}

    public void a(OutputNetStream as2) {}

    public void a(com.corrodinggames.rts.game.ai.AIStrategy a2, UnitType y2) {}

    public void b(com.corrodinggames.rts.game.ai.AIStrategy a2, UnitType y2) {}
}
