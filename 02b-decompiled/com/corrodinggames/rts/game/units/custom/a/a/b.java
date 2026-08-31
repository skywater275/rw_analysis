package com.corrodinggames.rts.game.units.custom.a.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.q;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.au;
import com.corrodinggames.rts.game.units.av;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.custom.u;
import com.corrodinggames.rts.game.units.custom.v;
import com.corrodinggames.rts.game.units.custom.a.a.c;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.gameFramework.utility.ab;

public class b extends com.corrodinggames.rts.game.units.custom.a.a {

   public boolean a;
   public boolean b;
   public av c;
   public v d;
   public boolean e;
   public com.corrodinggames.rts.game.units.custom.h f;
   public q g;
   public float h;
   public boolean i;
   public com.corrodinggames.rts.game.units.custom.h j;
   public q k;
   public float l;
   public boolean m;
   public boolean n;
   public PointF o;
   public PointF p;
   public PointF q;
   public LogicBoolean r;
   public float s = -1.0F;
   public u t;
   public u u;
   public static au v = new au();
   public static final c w = new c();


   public static void a(com.corrodinggames.rts.game.units.custom.l var0, ab var1, String var2, String var3, com.corrodinggames.rts.game.units.custom.a.d var4, String var5, boolean var6) {
      boolean var7 = false;
      boolean var8 = var1.a(var2, var3 + "clearAllWaypoints", Boolean.valueOf(false)).booleanValue();
      if(var8) {
         var7 = true;
      }

      boolean var9 = var1.a(var2, var3 + "clearActiveWaypoint", Boolean.valueOf(false)).booleanValue();
      if(var9) {
         var7 = true;
      }

      av var10 = (av)var1.a(var2, "addWaypoint_type", (Enum)null, av.class);
      boolean var11 = var1.a(var2, var3 + "addWaypoint_prepend", Boolean.valueOf(false)).booleanValue();
      com.corrodinggames.rts.game.units.custom.h var12 = var1.a(var0, var2, var3 + "addWaypoint_target_nearestUnit_tagged", (com.corrodinggames.rts.game.units.custom.h)null);
      q var13 = (q)var1.a(var2, "addWaypoint_target_nearestUnit_team", (Enum)q.a, q.class);
      float var14 = var1.a(var2, var3 + "addWaypoint_target_nearestUnit_maxRange", Float.valueOf(10000.0F)).floatValue();
      com.corrodinggames.rts.game.units.custom.h var15 = var1.a(var0, var2, var3 + "addWaypoint_target_randomUnit_tagged", (com.corrodinggames.rts.game.units.custom.h)null);
      q var16 = (q)var1.a(var2, "addWaypoint_target_randomUnit_team", (Enum)q.a, q.class);
      float var17 = var1.a(var2, var3 + "addWaypoint_target_randomUnit_maxRange", Float.valueOf(10000.0F)).floatValue();
      float var18 = var1.b(var2, var3 + "addWaypoint_maxTime", Float.valueOf(-1.0F)).floatValue();
      u var19 = var1.a(var0, var2, var3 + "addWaypoint_triggerActionIfFailed", (u)null);
      u var20 = var1.a(var0, var2, var3 + "addWaypoint_triggerActionIfMatched", (u)null);
      PointF var21 = var1.a(var2, var3 + "addWaypoint_position_offsetFromSelf", (PointF)null);
      PointF var22 = var1.a(var2, var3 + "addWaypoint_position_relativeOffsetFromSelf", (PointF)null);
      PointF var23 = var1.a(var2, var3 + "addWaypoint_position_randomOffsetFromSelf", (PointF)null);
      boolean var24 = var21 != null || var22 != null || var23 != null;
      boolean var25 = var12 != null;
      boolean var26 = var15 != null;
      boolean var27 = var1.a(var2, var3 + "addWaypoint_position_fromAction", Boolean.valueOf(false)).booleanValue();
      LogicBoolean var28 = var1.b(var0, var2, var3 + "addWaypoint_target_fromReference", (LogicBoolean)null);
      if(var28 != null) {
         var7 = true;
         if(var25 || var26) {
            throw new RuntimeException("[" + var2 + "] addWaypoint_target_nearestUnit/randomUnit and addWaypoint_target_fromReference cannot be used together");
         }

         if(var24) {
            throw new RuntimeException("[" + var2 + "] addWaypoint_position_offset* and addWaypoint_target_fromReference cannot be used together");
         }

         if(var27) {
            throw new RuntimeException("[" + var2 + "] addWaypoint_position_fromAction and addWaypoint_target_fromReference cannot be used together");
         }
      }

      if(var27) {
         var7 = true;
         if(var25 || var26) {
            throw new RuntimeException("[" + var2 + "] addWaypoint_target_* and addWaypoint_position_fromAction cannot be used together");
         }

         if(var24) {
            throw new RuntimeException("[" + var2 + "] addWaypoint_position_offset* and addWaypoint_position_fromAction cannot be used together");
         }
      }

      if((var25 || var26 || var24 || var28 != null) && var10 == null) {
         throw new RuntimeException("[" + var2 + "] addWaypoint_type is required when using addWaypoint_*");
      } else {
         if(var10 != null) {
            var7 = true;
            if(!var25 && !var26 && !var24 && !var27 && var28 == null) {
               throw new RuntimeException("[" + var2 + "] addWaypoint_target_nearestUnit_tagged, addWaypoint_position_offsetFromSelf or addWaypoint_target_fromReference is required when using addWaypoint_*");
            }
         }

         if(var24) {
            var7 = true;
            if(var25 || var26) {
               throw new RuntimeException("[" + var2 + "] addWaypoint_target_* and addWaypoint_position_* cannot be used together");
            }

            if(var10 != av.a && var10 != av.h) {
               throw new RuntimeException("[" + var2 + "] addWaypoint_position_* only supports position based waypoints (eg: move, attackMove)");
            }
         }

         if(var25 && var26) {
            throw new RuntimeException("[" + var2 + "] addWaypoint_target_nearestUnit_* and addWaypoint_target_randomUnit_* cannot be used together");
         } else {
            if(var7) {
               b var29 = new b();
               var29.a = var8;
               var29.b = var9;
               if(var10 != null) {
                  var29.c = var10;
                  if(var29.c == av.c) {
                     var29.d = var0.a(var1.b(var2, var3 + "addWaypoint_unitType", (String)null), var3 + "addWaypoint_unitType", var2);
                     if(var29.d == null) {
                        throw new RuntimeException("[" + var2 + "] addWaypoint_type: build requires addWaypoint_unitType");
                     }
                  }

                  var29.e = var11;
                  var29.f = var12;
                  var29.g = var13;
                  var29.h = var14;
                  var29.j = var15;
                  var29.k = var16;
                  var29.l = var17;
                  if(var26) {
                     var29.i = true;
                  }

                  var29.m = var1.a(var2, var3 + "addWaypoint_target_mapMustBeReachable", Boolean.valueOf(true)).booleanValue();
                  var29.o = var21;
                  var29.p = var22;
                  var29.q = var23;
                  var29.n = var27;
                  var29.r = var28;
                  var29.s = var18;
                  var29.t = var19;
                  var29.u = var20;
               }

               var4.ac.add(var29);
            }

         }
      }
   }

   public au a(com.corrodinggames.rts.game.units.custom.j var1, float var2, float var3, am var4, int var5) {
      au var6;
      if(this.s == 0.0F) {
         var6 = v;
         var6.e();
      } else if(this.e) {
         var6 = var1.ao();
         var1.aD();
         var1.aB();
      } else {
         var6 = var1.ap();
      }

      if(this.c == av.a) {
         var6.a(var2, var3);
      } else if(this.c == av.h) {
         var6.b(var2, var3);
      } else if(this.c == av.k && var4 != null) {
         var6.c(var4);
      } else if(this.c == av.n && var4 != null) {
         var6.e(var4);
      } else if(this.c == av.e && var4 != null) {
         var6.g(var4);
      } else if(this.c == av.i && var4 != null) {
         var6.h(var4);
      } else if(this.c == av.b && var4 != null) {
         var6.a(var4);
      } else if(this.c == av.g && var4 != null) {
         var6.f(var4);
      } else if(this.c == av.d && var4 != null) {
         var6.b(var4);
      } else if(this.c == av.m && var4 != null) {
         var6.d(var4);
      } else if(this.c == av.c) {
         var6.a(var2, var3, this.d.c(), 1);
      } else {
         var1.ay();
      }

      var6.l = this.s;
      var6.n = true;
      if(this.u != null) {
         PointF var7 = new PointF(var6.g(), var6.h());
         this.u.a(var1, var7, var6.i(), var5 + 1, 0);
      }

      return var6;
   }

   public boolean a(com.corrodinggames.rts.game.units.custom.j var1, s var2, PointF var3, am var4, int var5) {
      if(this.a) {
         var1.az();
      } else if(this.b) {
         var1.ay();
      }

      if(this.c != null) {
         if(this.r != null) {
            am var6 = this.r.readUnit(var1);
            if(var6 != null) {
               this.a(var1, var6.eo, var6.ep, var6, var5);
            } else if(this.t != null) {
               this.t.a(var1, var3, var4, var5 + 1, 0);
            }
         } else if(this.n) {
            if(var3 == null) {
               if(this.t != null) {
                  this.t.a(var1, var3, var4, var5 + 1, 0);
               }
            } else {
               this.a(var1, var3.a, var3.b, (am)null, var5);
            }
         } else if(this.o == null && this.p == null && this.q == null) {
            com.corrodinggames.rts.gameFramework.l var16;
            if(this.i) {
               w.c = this.l * this.l;
               w.b = this.j;
               w.d = false;
               w.h = null;
               w.e = this.k;
               w.a = this.m;
               w.f = true;
               w.g.clear();
               var16 = com.corrodinggames.rts.gameFramework.l.B();
               var16.cc.a(var1.eo, var1.ep, this.l, var1, 0.0F, w);
               if(w.g.size() == 0) {
                  if(this.t != null) {
                     this.t.a(var1, var3, var4, var5 + 1, 0);
                  }
               } else {
                  com.corrodinggames.rts.gameFramework.utility.m var17 = w.g;
                  int var15 = com.corrodinggames.rts.gameFramework.f.a(var1, 0, var17.size(), 0);
                  ++var1.bC;
                  if(var15 > var17.size() - 1) {
                     var15 = var17.size() - 1;
                  }

                  am var18 = (am)var17.get(var15);
                  this.a(var1, var18.eo, var18.ep, var18, var5);
               }
            } else {
               w.c = this.h * this.h;
               w.b = this.f;
               w.d = false;
               w.h = null;
               w.e = this.g;
               w.a = this.m;
               w.f = false;
               var16 = com.corrodinggames.rts.gameFramework.l.B();
               var16.cc.a(var1.eo, var1.ep, this.h, var1, 0.0F, w);
               if(w.h == null) {
                  if(this.t != null) {
                     this.t.a(var1, var3, var4, var5 + 1, 0);
                  }
               } else {
                  am var19 = w.h;
                  this.a(var1, var19.eo, var19.ep, var19, var5);
               }
            }
         } else {
            float var14 = var1.eo;
            float var7 = var1.ep;
            if(this.o != null) {
               var14 += this.o.a;
               var7 += this.o.b;
            }

            if(this.p != null) {
               float var8 = this.p.a;
               float var9 = this.p.b;
               float var10 = com.corrodinggames.rts.gameFramework.f.k(var1.cg);
               float var11 = com.corrodinggames.rts.gameFramework.f.j(var1.cg);
               float var12 = var10 * var9 - var11 * var8;
               float var13 = var11 * var9 + var10 * var8;
               var14 += var12;
               var7 += var13;
            }

            if(this.q != null) {
               var14 += (float)com.corrodinggames.rts.gameFramework.f.a(var1, -((int)this.q.a), (int)this.q.a, var5 + 1);
               var7 += (float)com.corrodinggames.rts.gameFramework.f.a(var1, -((int)this.q.b), (int)this.q.b, var5 + 2);
            }

            this.a(var1, var14, var7, (am)null, var5);
         }
      }

      return true;
   }

}
