package com.corrodinggames.rts.game.units.custom.b;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.bp;
import com.corrodinggames.rts.game.units.custom.b.a;
import com.corrodinggames.rts.game.units.custom.b.n;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.gameFramework.f.an;
import com.corrodinggames.rts.gameFramework.utility.ab;
import java.util.ArrayList;
import java.util.Iterator;

public final class m extends a {

   public static final m a = new m();


   public static strictfp void a(com.corrodinggames.rts.game.units.custom.l var0, ab var1) {
      String var2 = "attachment_";
      com.corrodinggames.rts.gameFramework.utility.m var3 = var1.e(var2);
      if(var3.size() > 0) {
         var0.a((a)a);
         short var4 = 0;
         Iterator var5 = var3.iterator();

         while(var5.hasNext()) {
            String var6 = (String)var5.next();
            String var7 = var6.substring(var2.length());
            n var8 = new n();
            a(var8, var0, var1, var6, var7);
            var8.b = var7;
            var8.a = var4++;
            var0.aA.add(var8);
         }
      }

   }

   public static strictfp void a(n var0, com.corrodinggames.rts.game.units.custom.l var1, ab var2, String var3, String var4) {
      var0.c = var2.i(var3, "x");
      var0.d = var2.i(var3, "y");
      var0.e = var2.a(var3, "height", Float.valueOf(var0.e)).floatValue();
      var0.i = var2.a(var3, "lockDir", Boolean.valueOf(var0.i)).booleanValue();
      var0.j = var2.a(var3, "redirectDamageToParent", Boolean.valueOf(var0.j)).booleanValue();
      var0.k = var2.a(var3, "redirectDamageToParent_shieldOnly", Boolean.valueOf(var0.k)).booleanValue();
      if(!var0.j && var0.k) {
         throw new bo("[" + var3 + "] redirectDamageToParent_shieldOnly requires redirectDamageToParent");
      } else {
         var0.l = var2.a(var3, "canBeAttackedAndDamaged", Boolean.valueOf(var0.l)).booleanValue();
         var0.m = var2.a(var3, "isUnselectable", Boolean.valueOf(var0.m)).booleanValue();
         var0.n = var2.a(var3, "isUnselectableAsTarget", Boolean.valueOf(var0.m)).booleanValue();
         var0.o = var2.a(var3, "isVisible", Boolean.valueOf(var0.o)).booleanValue();
         var0.p = var2.a(var3, "showMiniHp", Boolean.valueOf(var0.p)).booleanValue();
         var0.q = var2.a(var3, "hideHp", Boolean.valueOf(var0.q)).booleanValue();
         var0.N = var2.a(var1, var3, "showAllActionsFrom", (LogicBoolean)null);
         if(LogicBoolean.isStaticFalse(var0.N)) {
            var0.N = null;
         }

         Float var5 = var2.a(var3, "idleDir", (Float)null);
         Float var6 = var2.a(var3, "idleDirReversing", (Float)null);
         if(var5 != null) {
            var0.f = var5.floatValue();
            var0.g = var5.floatValue();
         }

         if(var6 != null) {
            var0.g = var6.floatValue();
         } else {
            var0.g = var0.f;
         }

         var0.h = var2.a(var3, "resetRotationWhenNotAttacking", Boolean.valueOf(false)).booleanValue();
         var0.r = var2.a(var3, "rotateWithParent", Boolean.valueOf(var0.r)).booleanValue();
         var0.s = var2.a(var3, "lockLegMovement", Boolean.valueOf(var0.s)).booleanValue();
         var0.t = var2.a(var3, "freezeLegMovement", Boolean.valueOf(var0.t)).booleanValue();
         var0.u = var2.a(var3, "lockRotation", Boolean.valueOf(var0.u)).booleanValue();
         if(var0.u && var0.h) {
            throw new bo("[" + var3 + "] Cannot use lockRotation and resetRotationWhenIdle at same time");
         } else {
            var0.v = var2.a(var3, "keepAliveWhenParentDies", Boolean.valueOf(var0.v)).booleanValue();
            var0.w = bp.b(var1, var2, var3, "onCreateSpawnUnitOf");
            if(var0.w.b()) {
               var0.w = null;
            }

            var0.x = var2.a(var3, "createIncompleteIfParentIs", Boolean.valueOf(var0.x)).booleanValue();
            var0.y = var2.a(var3, "onConvertKeepExistingUnitInSameSlot", Boolean.valueOf(var0.y)).booleanValue();
            var0.z = var2.a(var3, "onParentTeamChangeKeepCurrentTeam", Boolean.valueOf(var0.z)).booleanValue();
            var0.B = var2.a(var3, "setDrawLayerOnBottom", Boolean.valueOf(var0.B)).booleanValue();
            if(var0.B) {
               var0.A = false;
            }

            var0.A = var2.a(var3, "setDrawLayerOnTop", Boolean.valueOf(var0.A)).booleanValue();
            if(var0.A && var0.B) {
               throw new bo("[" + var3 + "] Cannot use setDrawLayerOnTop and setDrawLayerOnBottom at same time");
            } else {
               var0.D = var2.a(var3, "addTransportedUnits", Boolean.valueOf(var0.D)).booleanValue();
               var0.E = var2.a(var3, "unloadInCurrentPosition", Boolean.valueOf(var0.E)).booleanValue();
               var0.F = var2.a(var3, "smoothlyBlendPositionWhenExistingUnitAdded", Boolean.valueOf(var0.F)).booleanValue();
               if(var0.F) {
                  var0.G = 500.0F;
               } else {
                  var0.G = 0.0F;
               }

               var0.H = var2.a(var3, "deattachIfWantingToMove", Boolean.valueOf(var0.H)).booleanValue();
               var0.I = var2.a(var3, "hidden", Boolean.valueOf(var0.I)).booleanValue();
               var0.J = var2.a(var3, "prioritizeParentsMainTarget", Boolean.valueOf(var0.J)).booleanValue();
               var0.K = var2.a(var3, "onlyAttackParentsMainTarget", Boolean.valueOf(var0.K)).booleanValue();
               var0.L = var2.a(var3, "alwaysAllowedToAttackParentsMainTarget", Boolean.valueOf(var0.L)).booleanValue();
               var0.M = var2.a(var3, "canAttack", Boolean.valueOf(var0.M)).booleanValue();
               var0.O = var2.a(var3, "keepWaypointsNeedingMovement", Boolean.valueOf(var0.O)).booleanValue();
               if(var0.D) {
                  var1.aB = true;
               }

            }
         }
      }
   }

   public strictfp void a(com.corrodinggames.rts.game.units.custom.j var1, float var2) {
      this.b(var1, var2);
   }

   public strictfp void b(com.corrodinggames.rts.game.units.custom.j var1, float var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.game.units.custom.l var4 = var1.x;
      com.corrodinggames.rts.gameFramework.utility.m var5 = var4.aA;
      if(var5.a != 0) {
         if(var4.aB) {
            Object[] var6 = var5.a();

            for(int var7 = 0; var7 < var5.a; ++var7) {
               n var8 = (n)var6[var7];
               if(var8.D && var1.B.a > 0) {
                  y var9 = a(var1, var8);
                  if(var9 == null) {
                     Iterator var10 = var1.B.iterator();

                     while(var10.hasNext()) {
                        am var11 = (am)var10.next();
                        if(var11 instanceof y && var11.cO == null && var1.a((y)var11, var8)) {
                           var11.cN = null;
                           break;
                        }
                     }
                  }
               }
            }
         }

         com.corrodinggames.rts.gameFramework.utility.m var19 = var1.C;
         if(var19 != null) {
            float var20 = var1.cg - var1.D;
            var1.D = var1.cg;
            Object[] var21 = var19.a();

            for(int var22 = var19.a - 1; var22 >= 0; --var22) {
               y var23 = (y)var21[var22];
               if(var23 != null) {
                  if(var23.bV) {
                     var23.bx();
                     var21[var22] = null;
                  } else {
                     if(var1.cN != null) {
                        if(var23.cN == null) {
                           var23.cN = var1.cN;
                           var3.bS.l(var23);
                        }
                     } else if(var23.cN != null && var23.cN != var1) {
                        var23.cN = null;
                     }

                     n var24 = (n)var5.get(var22);
                     float var12 = com.corrodinggames.rts.gameFramework.f.k(var1.cg);
                     float var13 = com.corrodinggames.rts.gameFramework.f.j(var1.cg);
                     float var14 = var12 * var24.d - var13 * var24.c;
                     float var15 = var13 * var24.d + var12 * var24.c;
                     var14 += var1.eo;
                     var15 += var1.ep;
                     float var16 = var1.eq + var24.e;
                     float var17;
                     if(com.corrodinggames.rts.gameFramework.utility.y.b(var23.cQ, (int)var24.G)) {
                        var17 = 0.05F;
                        var23.eo += (var14 - var23.eo) * var17;
                        var23.ep += (var15 - var23.ep) * var17;
                        var23.eq += (var16 - var23.eq) * var17;
                     } else {
                        var23.eo = var14;
                        var23.ep = var15;
                        var23.eq = var16;
                     }

                     if(var23.cm < 1.0F && var24.x) {
                        var23.r(var1.cm);
                        var23.cn = var1.cm;
                     }

                     if(var24.A) {
                        if(var23.em <= var1.em) {
                           int var25 = 0;
                           if(var23 instanceof com.corrodinggames.rts.game.units.custom.j) {
                              var25 = ((com.corrodinggames.rts.game.units.custom.j)var23).x.cI;
                           }

                           var23.em = var1.em;
                           var23.en = var1.en + 1 + var25;
                        }
                     } else if(var24.B && var23.em >= var1.em) {
                        var23.em = var1.em;
                        var23.en = var1.en - 1;
                     }

                     if(var1.ci) {
                        var17 = var1.cg + var24.g;
                     } else {
                        var17 = var1.cg + var24.f;
                     }

                     if(!var23.bI()) {
                        if(var24.u) {
                           var23.h(var17);
                        } else {
                           if(var20 != 0.0F && var24.r) {
                              var23.i(var20);
                           }

                           if(var24.h && var23.R == null) {
                              var23.c(var2, var17);
                           }
                        }
                     }

                     if(var24.K) {
                        var23.R = var1.R;
                        var23.S = 5.0F;
                     }

                     if(var24.L && var23.R == null) {
                        var23.R = var1.R;
                     }

                     if(var24.J && var1.R != null && var23.R != var1.R) {
                        boolean var18 = false;
                        if(var24.L) {
                           var18 = true;
                        }

                        if(var23.a(var1.R, var18)) {
                           var23.R = var1.R;
                           var23.S = 5.0F;
                        }
                     }

                     if(var23 instanceof com.corrodinggames.rts.game.units.custom.j) {
                        com.corrodinggames.rts.game.units.custom.j var26 = (com.corrodinggames.rts.game.units.custom.j)var23;
                        if(var24.s) {
                           var26.dP = var26.eo;
                           var26.dP = var26.ep;
                           var26.dR = var26.eq;
                        }
                     }
                  }
               }
            }

         }
      }
   }

   public strictfp void a(com.corrodinggames.rts.game.units.custom.j var1, boolean var2) {
      com.corrodinggames.rts.gameFramework.utility.m var3 = var1.C;
      if(var3 != null) {
         com.corrodinggames.rts.gameFramework.utility.m var4 = var1.x.aA;
         Object[] var5 = var3.a();

         for(int var6 = var3.a - 1; var6 >= 0; --var6) {
            y var7 = (y)var5[var6];
            if(var7 != null) {
               n var8 = (n)var4.get(var6);
               var7.bx();
               var5[var6] = null;
               if(var2 && !var8.v) {
                  var7.ci();
               }
            }
         }

      }
   }

   public strictfp void b(com.corrodinggames.rts.game.units.custom.j var1) {
      this.a(var1, true);
   }

   public strictfp void c(com.corrodinggames.rts.game.units.custom.j var1) {
      this.a(var1, true);
   }

   public strictfp void a(com.corrodinggames.rts.game.units.custom.j var1) {
      boolean var2 = false;
      com.corrodinggames.rts.gameFramework.utility.m var3 = var1.x.aA;
      Object[] var4 = var3.a();

      for(int var5 = var3.a - 1; var5 >= 0; --var5) {
         n var6 = (n)var4[var5];
         if(var6.w != null) {
            y var7 = a(var1, var6);
            if(var7 != null) {
               if(var6.y) {
                  continue;
               }

               var7.ci();
            }

            com.corrodinggames.rts.gameFramework.utility.m var8 = new com.corrodinggames.rts.gameFramework.utility.m();
            var6.w.a(var8, var1.bX, var1, true);
            if(var8.size() > 1) {
               com.corrodinggames.rts.gameFramework.l.b("onCreateSpawnUnitOf: created an extra " + (var8.size() - 1) + " units");

               for(int var9 = 1; var9 < var8.size(); ++var9) {
                  ((am)var8.get(var9)).ci();
               }
            }

            if(var8.size() == 0) {
               com.corrodinggames.rts.gameFramework.l.b("onCreateSpawnUnitOf: Warning no units created");
            } else {
               am var11 = (am)var8.get(0);
               if(!(var11 instanceof y)) {
                  com.corrodinggames.rts.gameFramework.l.b("onCreateSpawnUnitOf: Warning " + var11.r().i() + " not an orderable unit type, cannot attach");
                  var11.ci();
               } else {
                  y var10 = (y)var11;
                  if(var1.a(var10, var6)) {
                     var10.cQ = -9999;
                     if(var1.cm < 1.0F && var6.x) {
                        var10.r(var1.cm);
                        var10.cn = var1.cm;
                     }

                     var2 = true;
                  }
               }
            }
         }
      }

      if(var2) {
         this.b(var1, 0.0F);
      }

   }

   public strictfp void a(com.corrodinggames.rts.game.units.custom.j var1, com.corrodinggames.rts.game.units.custom.l var2) {
      com.corrodinggames.rts.gameFramework.utility.m var3 = var1.C;
      com.corrodinggames.rts.gameFramework.utility.m var4 = var1.x.aA;
      if(var4.size() == 0) {
         var1.C = null;
      } else if(var3 != null) {
         int var5;
         y var6;
         for(var5 = var3.size() - 1; var5 >= 0; --var5) {
            var6 = (y)var3.get(var5);
            if(var6 != null && var5 >= var4.size()) {
               var6.ci();
               var3.remove(var5);
            }
         }

         for(var5 = var3.size() - 1; var5 >= 0; --var5) {
            var6 = (y)var3.get(var5);
            if(var6 != null) {
               var6.cP = (n)var4.get(var5);
            }
         }

      }
   }

   public static strictfp n a(com.corrodinggames.rts.game.units.custom.j var0, short var1) {
      com.corrodinggames.rts.gameFramework.utility.m var2 = var0.x.aA;
      return var2.a <= var1?null:(n)var2.get(var1);
   }

   public static strictfp y a(com.corrodinggames.rts.game.units.custom.j var0, n var1) {
      com.corrodinggames.rts.gameFramework.utility.m var2 = var0.C;
      if(var2 == null) {
         return null;
      } else {
         short var3 = var1.a;
         return var2.a <= var3?null:(y)var2.get(var3);
      }
   }

   public static strictfp boolean a(com.corrodinggames.rts.game.units.custom.j var0, n var1, y var2) {
      com.corrodinggames.rts.game.units.custom.l var3 = var0.x;
      short var4 = var1.a;
      if(var3.aA.a <= var4 && var2 != null) {
         com.corrodinggames.rts.gameFramework.l.b("setAttachedUnitLookup: slot:" + var4 + " larger than max slot size:" + var3.aA.a);
         return false;
      } else {
         if(var0.C == null) {
            var0.C = new com.corrodinggames.rts.gameFramework.utility.m();
         }

         com.corrodinggames.rts.gameFramework.utility.m var5 = var0.C;
         if(var5.size() == 0) {
            var0.D = var0.cg;
         }

         if(var2 == null && var4 >= var5.size()) {
            return true;
         } else {
            while(var5.size() <= var4) {
               var5.add((Object)null);
            }

            var5.set(var4, var2);
            return true;
         }
      }
   }

   public static strictfp void a(com.corrodinggames.rts.game.units.custom.j var0, com.corrodinggames.rts.gameFramework.utility.m var1, boolean var2) {
      com.corrodinggames.rts.gameFramework.utility.m var3 = var0.C;
      if(var3 != null) {
         Iterator var4 = var3.iterator();

         while(var4.hasNext()) {
            am var5 = (am)var4.next();
            if(var5 != null && var5 instanceof y) {
               n var6 = var5.dn();
               if(var6 != null && var6.N != null) {
                  ArrayList var7 = var5.N();
                  Iterator var8 = var7.iterator();

                  while(var8.hasNext()) {
                     s var9 = (s)var8.next();
                     boolean var10;
                     if(var2) {
                        var10 = an.a(var6.N, (y)var0);
                     } else {
                        var10 = var6.N.read(var0);
                     }

                     if(var10) {
                        com.corrodinggames.rts.game.units.a.g var11 = new com.corrodinggames.rts.game.units.a.g(var9, (y)var5, var9.N());
                        var1.add(var11);
                     }
                  }
               }
            }
         }
      }

   }

}
