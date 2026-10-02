/*
 * v19.115i 新建: 02b gameFramework.f.an.java 最小版 (动作调试记录器; Command L685 调用)
 * 02b 签名 a(am, custom.d.b=CustomActionBase); 03 用 (UnitInstance, GameAction) 适配 Command 调用点
 * (02b FF 参数类型缺陷: units.a.s 传给 custom.d.b 参数; 完整体依赖 f.ao/f.x/NetEngine.B 待战役)
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.actions.GameAction;

public class ActionDebugRecorder {
   public static void a(UnitInstance var0, GameAction var1) {
      // 02b f/an.java L41-51: 调试模式下记录单位动作 (依赖 l.B().bX.B + ao 条目列表, 待战役完整化)
   }
}
