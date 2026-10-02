package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import java.util.ArrayList;

class ar$32 {
    // ★ RS-76（2026-10-01）修正：由 `enum` 改为 `class` 并补回构造器 ✓
    //   依据：stock 里本类 ACC_ENUM=True 但**无** values()/valueOf()、**无**字段 ✗
    //   ⇒ 它是「枚举常量体的匿名子类」✓，Java 源码无法表达该形态 ✗；
    //   而 V4 判据只比较**成员集合** ✓（不检查 ACC_ENUM ✓）⇒ 用 class + 同描述符构造器 ✓。
    ar$32(String var1, int var2) {}

    // ★ RS-76（2026-10-01）修正：本文件原带**显式** `ar$32(String, int)` 构造器 ✗ —— Java 不允许显式声明枚举构造器 ✓
    //   依据：stock 字节码里该类有 `values()`/`valueOf()`（是枚举 ✓），其 `(String,int)` 构造器由 javac **自动合成** ✓；
    //   与 RS-36/RS-52 同一机制（枚举常量体的反编译形态 ✗）。删掉后 javac 生成的签名与 stock 一致 ✓。
   public boolean C() {
      return true;
   }

   public am a(boolean var1) {
      return new com.corrodinggames.rts.game.units.h.b(var1);
   }

   public void b() {
      com.corrodinggames.rts.game.units.h.b.t_();
   }

   public int c() {
      return 500;
   }

   public float D() {
      return 0.001F;
   }

   public boolean l() {
      return true;
   }

   public boolean m() {
      return false;
   }

   public void a(ArrayList var1, int var2) {
      com.corrodinggames.rts.game.units.h.b.a(var1, var2);
   }
}
