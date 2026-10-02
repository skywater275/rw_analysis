package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.bq;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.v;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import java.util.ArrayList;
import java.util.Iterator;

public class bp {

   com.corrodinggames.rts.gameFramework.utility.m a;


   public static bp a(String var0, String var1, String var2) {
      return b((l)null, var0, var1, var2, false);
   }

   public static bp a(l var0, com.corrodinggames.rts.gameFramework.utility.ab var1, String var2, String var3) {
      String var4 = var1.b(var2, var3, (String)null);
      return a(var0, var4, var2, var3, false);
   }

   public static bp b(l var0, com.corrodinggames.rts.gameFramework.utility.ab var1, String var2, String var3) {
      String var4 = var1.b(var2, var3, (String)null);
      return a(var0, var4, var2, var3, true);
   }

   public static bp a(l var0, String var1, String var2, String var3, boolean var4) {
      if(var0 == null) {
         throw new RuntimeException("meta==null");
      } else {
         return b(var0, var1, var2, var3, var4);
      }
   }

   public static bp b(l var0, String var1, String var2, String var3, boolean var4) {
      bp var5 = new bp();
      if(var1 != null && !"".equals(var1) && !"NONE".equalsIgnoreCase(var1)) {
         ArrayList var6 = com.corrodinggames.rts.gameFramework.utility.al.a(var1, ",", false);
         Iterator var7 = var6.iterator();

         while(var7.hasNext()) {
            String var8 = (String)var7.next();
            var8 = var8.trim();
            if(!"".equals(var8)) {
               String var9 = var8;
               String var10 = null;
               String[] var11;
               if(var8.contains("(") && var8.contains(")")) {
                  var11 = com.corrodinggames.rts.gameFramework.utility.al.b(var8, "(");
                  if(var11 == null) {
                     throw new bo("[" + var2 + "]" + var3 + " UnitList: Unexpected format for \'" + var8 + "\' of " + var1);
                  }

                  var8 = var11[0];
                  var10 = var11[1].trim();
               }

               var11 = var8.split("\\*");
               var8 = var11[0];
               int var12 = 1;
               if(var11.length >= 2) {
                  var12 = Integer.parseInt(var11[1]);
               }

               v var13 = new v();
               var13.a = var3;
               var13.b = var2;
               var13.c = var8;
               if(var0 != null) {
                  var0.p.add(var13);
               } else {
                  var13.a();
               }

               bq var14 = new bq(var13);
               if(var5.a == null) {
                  var5.a = new com.corrodinggames.rts.gameFramework.utility.m();
               }

               var14.d = var12;
               if(var10 != null) {
                  if(!var10.endsWith(")")) {
                     throw new bo("[" + var2 + "]" + var3 + " UnitList: Expected \')\' in \'" + var9 + "\' of " + var1);
                  }

                  var10 = var10.substring(0, var10.length() - 1);
                  ArrayList var15 = com.corrodinggames.rts.gameFramework.utility.al.a(var10, ",", false, false);
                  Iterator var16 = var15.iterator();

                  while(var16.hasNext()) {
                     String var17 = (String)var16.next();
                     if(!var17.trim().equals("")) {
                        String[] var18 = com.corrodinggames.rts.gameFramework.utility.al.b(var17, "=");
                        if(var18 == null) {
                           throw new RuntimeException("[" + var2 + "]" + var3 + " UnitList: Unexpected key format for \'" + var9 + "\' of " + var1);
                        }

                        String var19 = var18[0].trim();
                        String var20 = var18[1].trim();
                        if(var19.equalsIgnoreCase("neutralTeam")) {
                           var14.e = com.corrodinggames.rts.gameFramework.utility.ab.g(var2, var3, var20);
                        } else if(var19.equalsIgnoreCase("setToTeamOfLastAttacker")) {
                           var14.g = com.corrodinggames.rts.gameFramework.utility.ab.g(var2, var3, var20);
                        } else if(var19.equalsIgnoreCase("aggressiveTeam")) {
                           var14.f = com.corrodinggames.rts.gameFramework.utility.ab.g(var2, var3, var20);
                        } else if(var19.equalsIgnoreCase("spawnChance")) {
                           var14.h = com.corrodinggames.rts.gameFramework.utility.ab.h(var2, var3, var20);
                        } else if(var19.equalsIgnoreCase("maxSpawnLimit")) {
                           var14.i = com.corrodinggames.rts.gameFramework.utility.ab.i(var2, var3, var20);
                        } else if(var19.equalsIgnoreCase("techLevel")) {
                           var14.m = com.corrodinggames.rts.gameFramework.utility.ab.i(var2, var3, var20);
                        } else if(var19.equalsIgnoreCase("gridAlign")) {
                           var14.j = com.corrodinggames.rts.gameFramework.utility.ab.g(var2, var3, var20);
                        } else if(var19.equalsIgnoreCase("skipIfOverlapping")) {
                           var14.k = com.corrodinggames.rts.gameFramework.utility.ab.g(var2, var3, var20);
                        } else if(var19.equalsIgnoreCase("falling")) {
                           var14.l = com.corrodinggames.rts.gameFramework.utility.ab.g(var2, var3, var20);
                        } else if(var19.equalsIgnoreCase("transportedUnitsToTransfer")) {
                           var14.w = (short)com.corrodinggames.rts.gameFramework.utility.ab.i(var2, var3, var20);
                        } else if(var19.equalsIgnoreCase("alwaysStartDirAtZero")) {
                           var14.n = com.corrodinggames.rts.gameFramework.utility.ab.g(var2, var3, var20);
                        } else if(var19.equalsIgnoreCase("alwayStartDirAtZero")) {
                           var14.n = com.corrodinggames.rts.gameFramework.utility.ab.g(var2, var3, var20);
                        } else if(var19.equalsIgnoreCase("offsetX")) {
                           var14.o = com.corrodinggames.rts.gameFramework.utility.ab.h(var2, var3, var20);
                        } else if(var19.equalsIgnoreCase("offsetY")) {
                           var14.p = com.corrodinggames.rts.gameFramework.utility.ab.h(var2, var3, var20);
                        } else if(var19.equalsIgnoreCase("offsetRandomXY")) {
                           float var21 = com.corrodinggames.rts.gameFramework.utility.ab.h(var2, var3, var20);
                           var14.s = var21;
                           var14.t = var21;
                        } else if(var19.equalsIgnoreCase("offsetRandomX")) {
                           var14.s = com.corrodinggames.rts.gameFramework.utility.ab.h(var2, var3, var20);
                        } else if(var19.equalsIgnoreCase("offsetRandomY")) {
                           var14.t = com.corrodinggames.rts.gameFramework.utility.ab.h(var2, var3, var20);
                        } else if(var19.equalsIgnoreCase("offsetHeight")) {
                           var14.q = com.corrodinggames.rts.gameFramework.utility.ab.h(var2, var3, var20);
                        } else if(var19.equalsIgnoreCase("offsetRandomDir")) {
                           var14.u = com.corrodinggames.rts.gameFramework.utility.ab.h(var2, var3, var20);
                        } else if(var19.equalsIgnoreCase("offsetDir")) {
                           var14.r = com.corrodinggames.rts.gameFramework.utility.ab.h(var2, var3, var20);
                        } else if(var19.equalsIgnoreCase("addResources")) {
                           if(var0 == null) {
                              throw new bo("[" + var2 + "]" + var3 + " addResources not supported from here");
                           }

                           try {
                              var14.v = com.corrodinggames.rts.game.units.custom.d.b.b(var0, var20);
                           } catch (bo var22) {
                              var22.printStackTrace();
                              throw new bo("[" + var2 + "]" + var3 + " addResources:" + var22.getMessage());
                           }
                        } else if(var19.equalsIgnoreCase("spawnSource")) {
                           var14.b = com.corrodinggames.rts.gameFramework.utility.ab.a(var20, var0, var2, var3, (LogicBoolean)null);
                        } else {
                           if(!var19.equalsIgnoreCase("copyWaypointsFrom")) {
                              throw new bo("[" + var2 + "]" + var3 + " UnitList: Unknown parameter \'" + var19 + "\' for \'" + var9 + "\' of " + var1);
                           }

                           var14.c = com.corrodinggames.rts.gameFramework.utility.ab.a(var20, var0, var2, var3, (LogicBoolean)null);
                        }
                     }
                  }

                  if(var14.g && var14.e) {
                     throw new bo("[" + var2 + "]" + var3 + " Cannot set setToTeamOfLastAttacker and neutralTeam at same time in " + var1);
                  }

                  if(var14.f && var14.e) {
                     throw new bo("[" + var2 + "]" + var3 + " Cannot set aggressiveTeam and neutralTeam at same time in " + var1);
                  }

                  if(var14.f && var14.g) {
                     throw new bo("[" + var2 + "]" + var3 + " Cannot set aggressiveTeam and setToTeamOfLastAttacker at same time in " + var1);
                  }
               }

               var5.a.add(var14);
            }
         }

         if(var4) {
            int var23 = var5.a();
            if(var23 > 1) {
               throw new bo("[" + var2 + "]" + var3 + " Too many units: " + var23 + ", only single unit is allowed here");
            }
         }

         return var5;
      } else {
         return var5;
      }
   }

   public int a() {
      if(this.a != null && this.a.size() != 0) {
         int var1 = 0;

         bq var3;
         for(Iterator var2 = this.a.iterator(); var2.hasNext(); var1 += var3.d) {
            var3 = (bq)var2.next();
         }

         return var1;
      } else {
         return 0;
      }
   }

   public boolean b() {
      return this.a == null || this.a.size() == 0;
   }

   public void a(com.corrodinggames.rts.gameFramework.utility.m var1, com.corrodinggames.rts.game.n var2, com.corrodinggames.rts.game.units.am var3, boolean var4) {
      this.a(0.0F, 0.0F, 0.0F, 0.0F, var2, false, var3, var1, var4);
   }

   public void a(float var1, float var2, float var3, float var4, com.corrodinggames.rts.game.n var5, boolean var6, com.corrodinggames.rts.game.units.am var7) {
      this.a(var1, var2, var3, var4, var5, var6, var7, (com.corrodinggames.rts.gameFramework.utility.m)null, false);
   }

   public void a(float var1, float var2, float var3, float var4, com.corrodinggames.rts.game.n var5, boolean var6, com.corrodinggames.rts.game.units.am var7, com.corrodinggames.rts.gameFramework.utility.m var8, boolean var9) {
      if(this.a != null && this.a.size() != 0) {
         boolean var10 = false;
         com.corrodinggames.rts.gameFramework.l var11 = com.corrodinggames.rts.gameFramework.l.B();
         int var12 = 0;
         int var13 = 0;
         Iterator var14 = this.a.iterator();

         while(var14.hasNext()) {
            bq var15 = (bq)var14.next();
            com.corrodinggames.rts.game.n var16 = var5;
            com.corrodinggames.rts.game.units.am var17 = var7;
            float var18 = var1;
            float var19 = var2;
            float var20 = var3;
            float var21 = var4;
            if(var15.b != null) {
               if(!(var7 instanceof com.corrodinggames.rts.game.units.y)) {
                  com.corrodinggames.rts.gameFramework.l.b("spawnUnitsAt: sourceUnit!=OrderableUnit is:" + com.corrodinggames.rts.game.units.am.A(var7));
                  continue;
               }

               com.corrodinggames.rts.game.units.am var22 = var15.b.readUnit((com.corrodinggames.rts.game.units.y)var7);
               if(var22 == null) {
                  com.corrodinggames.rts.gameFramework.l.b("spawnUnitsAt: spawnSource==null");
                  continue;
               }

               var16 = var22.bX;
               var17 = var22;
               var18 = var22.eo;
               var19 = var22.ep;
               var20 = var22.eq;
               var21 = var22.cg;
               if(var16 == null) {
                  com.corrodinggames.rts.gameFramework.l.b("spawnUnitsAt: newSpawnSource.team==null");
                  continue;
               }
            }

            if(!var9) {
               if(var16.w() > var16.x() + 300) {
                  var10 = true;
               }
            } else if(var16.a(true, false) > var16.x() + 20000) {
               var10 = true;
            }

            String var33;
            if(var10) {
               var33 = "";
               if(var17 != null) {
                  var33 = var33 + "source:" + var17.cB();
               }

               com.corrodinggames.rts.gameFramework.l.b("spawnUnitsAt: Skipping, too many units already on team:" + var16.k + " count:" + var16.w() + " " + var33);
               if(com.corrodinggames.rts.gameFramework.l.B().bl) {
                  var16.W();
               }
            } else if(var16.s() > var16.x() + 25000) {
               var33 = "";
               if(var17 != null) {
                  var33 = var33 + "source:" + var17.cB();
               }

               com.corrodinggames.rts.gameFramework.l.b("spawnUnitsAt: Failsafe, too many units already on team (including ignored):" + var16.k + " total count:" + var16.s() + " " + var33);
               if(com.corrodinggames.rts.gameFramework.l.B().bl) {
                  var16.W();
               }
            } else {
               com.corrodinggames.rts.game.units.as var32 = var15.a.c();
               if(var32 != null) {
                  for(int var23 = 0; var23 < var15.d; ++var23) {
                     ++var13;
                     com.corrodinggames.rts.game.n var24 = var16;
                     if(var15.h < 1.0F) {
                        float var25 = com.corrodinggames.rts.gameFramework.f.a(var17, 0.0F, 1.0F, var13);
                        if(var25 > var15.h) {
                           continue;
                        }
                     }

                     if(var15.g) {
                        if(var17 == null || var17.bt == null) {
                           continue;
                        }

                        var24 = var17.bt.bX;
                        if(var24 == null) {
                           throw new RuntimeException("setToTeamOfLastAttacker targetTeam==null");
                        }
                     }

                     if(var12 < var15.i) {
                        com.corrodinggames.rts.game.units.am var34 = var32.a();
                        if(var15.e) {
                           var24 = com.corrodinggames.rts.game.n.i;
                        }

                        if(var15.f) {
                           var24 = com.corrodinggames.rts.game.n.h;
                        }

                        if(var24 == null) {
                           throw new RuntimeException("Team==null");
                        }

                        var34.f(var24);
                        var34.B(var17);
                        var34.eo = var18;
                        var34.ep = var19;
                        var34.eq = var20;
                        if(!var34.bI() && !var15.n) {
                           var34.cg = var21;
                        }

                        var34.eq += var15.q;
                        if(var15.m != -1 && var34 instanceof com.corrodinggames.rts.game.units.y) {
                           ((com.corrodinggames.rts.game.units.y)var34).a(var15.m);
                        }

                        float var26 = var15.r;
                        if(var15.u != 0.0F) {
                           var26 += com.corrodinggames.rts.gameFramework.f.a(var17, -var15.u, var15.u, var13 * 4 + 3);
                        }

                        if(var26 != 0.0F) {
                           if(var34 instanceof com.corrodinggames.rts.game.units.y) {
                              ((com.corrodinggames.rts.game.units.y)var34).i(var26);
                           } else {
                              var34.cg += var26;
                           }
                        }

                        var34.eo += (float)var23;
                        if(var15.s != 0.0F) {
                           var34.eo += com.corrodinggames.rts.gameFramework.f.a(var17, -var15.s, var15.s, var13 * 2 + 1);
                        }

                        if(var15.t != 0.0F) {
                           var34.ep += com.corrodinggames.rts.gameFramework.f.a(var17, -var15.t, var15.t, var13 * 3 + 2);
                        }

                        if(var15.j) {
                           var11.bL.b(var34.eo, var34.ep);
                           var34.eo = (float)var11.bL.T;
                           var34.ep = (float)var11.bL.U;
                           var34.eo += var34.cZ();
                           var34.ep += var34.da();
                        }

                        var34.eo += var15.o;
                        var34.ep += var15.p;
                        ++var12;
                        if(var15.k && var34 instanceof com.corrodinggames.rts.game.units.y && !((com.corrodinggames.rts.game.units.y)var34).c((com.corrodinggames.rts.game.n)null)) {
                           var34.ci();
                        } else {
                           if(var15.l && var34 instanceof com.corrodinggames.rts.game.units.y) {
                              var34.dc();
                           }

                           if(var15.v != null) {
                              var15.v.h(var34);
                           }

                           if(var15.w > 0 && var17 != null && var17 instanceof j) {
                              j var27 = (j)var17;
                              int var28 = var15.w;
                              if(var27.B != null) {
                                 for(; var28 > 0; --var28) {
                                    int var29 = -1;

                                    for(int var30 = var27.B.size() - 1; var30 >= 0; --var30) {
                                       com.corrodinggames.rts.game.units.am var31 = (com.corrodinggames.rts.game.units.am)var27.B.get(var30);
                                       if(var34.c(var31, true)) {
                                          var29 = var30;
                                          break;
                                       }
                                    }

                                    if(var29 == -1) {
                                       break;
                                    }

                                    com.corrodinggames.rts.game.units.am var36 = (com.corrodinggames.rts.game.units.am)var27.B.remove(var29);
                                    com.corrodinggames.rts.gameFramework.utility.y.a(var36, (com.corrodinggames.rts.game.units.y)var27);
                                    var27.D(var36);
                                    var36.eo = var34.eo;
                                    var36.ep = var34.ep;
                                    var36.cg = var34.cg;
                                    if(var36 instanceof com.corrodinggames.rts.game.units.y) {
                                       com.corrodinggames.rts.game.units.y var37 = (com.corrodinggames.rts.game.units.y)var36;
                                       var37.az();
                                    }

                                    if(!var34.e(var36, true)) {
                                       com.corrodinggames.rts.gameFramework.l.b("transportedUnitsToTransfer failed for: " + var36.cB() + " to: " + var34.cB());
                                       var36.ci();
                                    }
                                 }
                              }
                           }

                           com.corrodinggames.rts.game.n.c(var34);
                           if(var34.bI() && var34 instanceof com.corrodinggames.rts.game.units.y) {
                              var11.bU.a((com.corrodinggames.rts.game.units.y)var34);
                           }

                           if(var6 && !var34.u()) {
                              com.corrodinggames.rts.gameFramework.l.B().bS.k(var34);
                           }

                           if(var15.c != null) {
                              if(!(var34 instanceof com.corrodinggames.rts.game.units.y)) {
                                 com.corrodinggames.rts.gameFramework.l.b("copyWaypointsFrom: spawnedUnit!=OrderableUnit is:" + com.corrodinggames.rts.game.units.am.A(var17));
                              } else {
                                 com.corrodinggames.rts.game.units.am var35 = var15.c.readUnit((com.corrodinggames.rts.game.units.y)var7);
                                 if(var35 != null) {
                                    if(!(var35 instanceof com.corrodinggames.rts.game.units.y)) {
                                       com.corrodinggames.rts.gameFramework.l.b("copyWaypointsFrom: copyWaypointsFrom!=OrderableUnit is:" + com.corrodinggames.rts.game.units.am.A(var17));
                                    } else {
                                       com.corrodinggames.rts.game.units.y.a((com.corrodinggames.rts.game.units.y)var35, (com.corrodinggames.rts.game.units.y)var34);
                                    }
                                 }
                              }
                           }

                           if(var8 != null) {
                              var8.add(var34);
                           }
                        }
                     }
                  }
               }
            }
         }

      }
   }

   @Deprecated
   public static bp a(com.corrodinggames.rts.gameFramework.j.k var0, boolean var1) {
      int var2 = var0.f();
      if(var1 && var2 == 0) {
         return null;
      } else {
         bp var3 = new bp();

         for(int var4 = 0; var4 < var2; ++var4) {
            bq var5 = new bq((v)null);
            com.corrodinggames.rts.game.units.as var6 = var0.q();
            if(var6 != null) {
               if(var3.a == null) {
                  var3.a = new com.corrodinggames.rts.gameFramework.utility.m();
               }

               var5.a = l.a(var6);
            }

            if(var0.b() >= 75) {
               boolean var7 = var0.e();
               if(var7) {
                  var5.d = var0.f();
                  var5.e = var0.e();
                  var5.g = var0.e();
                  if(var0.b() >= 76) {
                     var5.h = var0.g();
                  }
               }
            }

            if(var6 != null) {
               var3.a.add(var5);
            }
         }

         return var3;
      }
   }
}
