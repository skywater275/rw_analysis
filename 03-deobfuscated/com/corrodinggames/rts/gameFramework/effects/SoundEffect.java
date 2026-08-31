/*
 * v19.117 新建: 02b gameFramework.d.d.java 直译 (粒子音效类型枚举)
 * 引用点: MovementController.bR.b(...)/DrawEffect 第4参 (02b d.d.a 铁证)
 */
package com.corrodinggames.rts.gameFramework.effects;

public strictfp enum SoundEffect {
    a("custom", 0),
    b("smoke", 1),
    c("teleport", 2),
    d("hitGround", 3),
    e("playerLand", 4),
    f("playerJump", 5),
    g("gemCollect", 6),
    h("keyDoorOpen", 7),
    i("blood", 8);

    private SoundEffect(String string, int n) {
    }
}
