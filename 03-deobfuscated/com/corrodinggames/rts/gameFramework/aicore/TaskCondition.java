/*
 * v19.133f52 补建: 02b gameFramework/n/a/a.java 直译 (任务条件基类, abstract)
 * 子类 b(teamTagDetect)/c(unitTypeDetect) 见 02b n/a/b.java·c.java (03 未引用暂缓)
 */
package com.corrodinggames.rts.gameFramework.aicore;

public abstract class TaskCondition {

    public boolean a(AITask var1) {
        return true;
    }

    public abstract boolean b(AITask var1);

    public boolean c(AITask var1) {
        return false;
    }
}
