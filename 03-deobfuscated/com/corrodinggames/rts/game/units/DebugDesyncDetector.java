/*
 * v19.115i 新建: 02b units.g.java 最小版 (调试绘制覆盖层 DebugDesyncDetector; Command L488-497 使用)
 * 02b g extends units.e.j implements units.d; 03 侧 e.j 父类链未映射 (03 LandUnit 错乱),
 * implements MovementPath (02b units.d=b()/e_() PointF[] 接口, 03 铁证)
 * 完整方法体 (f()/v()/d()/k()/e()/a(float)/w()/c(float)/b(am) 等 50+) 待 UnitInstance 链战役
 */
package com.corrodinggames.rts.game.units;

import android.graphics.PointF;
import com.corrodinggames.rts.game.PlayerState;

public class DebugDesyncDetector implements MovementPath {
   public boolean a;
   PointF[] b = new PointF[6];
   PointF[] c;
   static android.graphics.Paint d;
   static android.graphics.Paint e;
   static android.graphics.Paint f;
   int g;
   float h;
   float i;
   int j;

   public DebugDesyncDetector(boolean var1) {
      // 02b L69-97: super(var1) 父类链 03 未映射, 保留本类字段初始化
      this.c = new PointF[this.b.length];
      for(int var2 = 0; var2 < this.b.length; ++var2) {
         this.b[var2] = new PointF();
         this.c[var2] = new PointF();
      }
   }

   // 02b 父类链方法 (e.j/w -> am.b(n)); 03 最小空实现, 语义: 设置队伍
   public void b(PlayerState var1) {
   }

   public PointF[] b() {   // 02b units.d.b()
      return this.b;
   }

   public PointF[] e_() {   // 02b units.d.e_()
      return this.c;
   }
}
