package com.corrodinggames.rts.game.units.custom;

import android.graphics.Color;
import android.graphics.PointF;
import com.corrodinggames.rts.game.units.custom.bi;
import com.corrodinggames.rts.game.units.custom.bp;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.z;
import java.util.Iterator;

public class bh extends com.corrodinggames.rts.game.g {

   public String bh;
   public int bi;
   public l bj;


   public static void a(bh var0, l var1, com.corrodinggames.rts.gameFramework.utility.ab var2, String var3) {
      String var4 = var3;
      Integer var5 = var2.b(var3, "directDamage", (Integer)null);
      Integer var6 = var2.b(var3, "areaDamage", (Integer)null);
      if(var5 == null && var6 == null) {
         throw new RuntimeException("[" + var3 + "]: directDamage or areaDamage must be set");
      } else {
         var0.s = var2.a(var3, "targetGround", Boolean.valueOf(var0.s)).booleanValue();
         var0.t = var2.a(var3, "targetGround_includeTargetHeight", Boolean.valueOf(var0.t)).booleanValue();
         Integer var7 = var2.b(var3, "areaRadius", (Integer)null);
         if(var7 != null) {
            var0.i = var7.intValue();
         }

         var0.b = var2.b(var3, "directDamage", Integer.valueOf(var0.b)).intValue();
         var0.c = var2.b(var3, "areaDamage", Integer.valueOf(var0.c)).intValue();
         var0.d = var2.a(var3, "interceptProjectile_removeTargetLifeOnly", Boolean.valueOf(var0.d)).booleanValue();
         var0.g = var2.a(var3, "areaDamageNoFalloff", Boolean.valueOf(var0.g)).booleanValue();
         var0.j = var2.a(var3, "areaIgnoreUnitsCloserThan", Float.valueOf(var0.j)).floatValue();
         var0.h = var2.a(var3, "areaRadiusFromEdge", Boolean.valueOf(var0.h)).booleanValue();
         if("only-ignoreEnemy".equalsIgnoreCase(var2.b(var3, "friendlyFire", (String)null))) {
            var0.l = true;
         } else {
            Boolean var8 = var2.a(var3, "friendlyFire", (Boolean)null);
            if(var8 != null) {
               var0.l = false;
               var0.k = var8.booleanValue();
            }
         }

         var0.m = var2.a(var3, "areaHitAirAndLandAtSameTime", Boolean.valueOf(var0.m)).booleanValue();
         var0.n = var2.a(var3, "areaHitUnderwaterAlways", Boolean.valueOf(var0.n)).booleanValue();
         var0.o = var2.a(var3, "deflectionPower", Float.valueOf(var0.o)).floatValue();
         var0.p = var2.a(var3, "nukeWeapon", Boolean.valueOf(var0.p)).booleanValue();
         var0.q = var2.a(var3, "shouldRevealFog", Boolean.valueOf(var0.q)).booleanValue();
         var0.r = var2.a(var3, "alwaysVisibleInFog", Boolean.valueOf(var0.r)).booleanValue();
         var0.v = var2.h(var3, "life").floatValue();
         var0.u = var2.b(var3, "delayedStartTimer", Float.valueOf(0.0F)).floatValue();
         var0.w = var2.a(var3, "speed", Float.valueOf(var0.w)).floatValue();
         var0.x = var2.a(var3, "frame", Short.valueOf(var0.x)).shortValue();
         var0.y = var2.a(var3, "drawType", Short.valueOf(var0.y)).shortValue();
         var0.z = var2.a(var3, "shadowFrame", Short.valueOf(var0.z)).shortValue();
         com.corrodinggames.rts.gameFramework.m.e var28 = var1.a(var2, var3, "image");
         if(var28 != null) {
            var0.B = var28;
         }

         com.corrodinggames.rts.gameFramework.m.e var9 = var1.a(var2, var3, "shadowImage");
         if(var9 != null) {
            var0.C = var9;
         }

         var0.ad = var2.a(var3, "beamImageOffsetRate", Float.valueOf(var0.ad)).floatValue();
         com.corrodinggames.rts.gameFramework.m.e var10 = var1.a(var2, var3, "beamImage");
         if(var10 != null) {
            var0.Y = var10;
            var0.X = true;
            if(var10.q < 20 && !com.corrodinggames.rts.gameFramework.l.ax()) {
               throw new RuntimeException("beamImage height must currently be 20 pixels or greater (performance when tiling)");
            }
         }

         com.corrodinggames.rts.gameFramework.m.e var11 = var1.a(var2, var3, "beamImageStart");
         if(var11 != null) {
            var0.Z = var11;
            if(var10 == null) {
               throw new RuntimeException("beamImageStart requires beamImage to be set");
            }
         }

         var0.aa = var2.a(var3, "beamImageStartRotated", Boolean.valueOf(false)).booleanValue();
         com.corrodinggames.rts.gameFramework.m.e var12 = var1.a(var2, var3, "beamImageEnd");
         if(var12 != null) {
            var0.ab = var12;
            if(var10 == null) {
               throw new RuntimeException("beamImageEnd requires beamImage to be set");
            }
         }

         var0.ac = var2.a(var3, "beamImageEndRotated", Boolean.valueOf(false)).booleanValue();
         var0.A = var2.a(var3, "invisible", Boolean.valueOf(var0.A)).booleanValue();
         var0.D = var2.a(var3, "initialUnguidedSpeedHeight", Float.valueOf(var0.D)).floatValue();
         var0.E = var2.a(var3, "initialUnguidedSpeedX", Float.valueOf(var0.E)).floatValue();
         var0.F = var2.a(var3, "initialUnguidedSpeedY", Float.valueOf(var0.F)).floatValue();
         var0.G = var2.a(var3, "gravity", Float.valueOf(var0.G)).floatValue();
         var0.H = var2.a(var3, "trueGravity", Float.valueOf(var0.H)).floatValue();
         var0.I = var2.a(var3, "instant", Boolean.valueOf(var0.I)).booleanValue();
         var0.L = var2.a(var3, "instantReuseLast", Boolean.valueOf(var0.L)).booleanValue();
         var0.M = var2.a(var3, "instantReuseLast_alsoChangeTurretAim", Boolean.valueOf(var0.M)).booleanValue();
         if(var0.M) {
            if(!var0.L) {
               throw new RuntimeException("[" + var3 + "]instantReuseLast_alsoChangeTurretAim also requires instantReuseLast");
            }

            var1.eA = true;
         }

         var0.N = var2.a(var3, "instantReuseLast_keepAreaDamageList", Boolean.valueOf(var0.N)).booleanValue();
         var0.T = var2.a(var3, "moveWithParent", Boolean.valueOf(var0.T)).booleanValue();
         var0.J = var2.a(var3, "disableLeadTargeting", Boolean.valueOf(var0.J)).booleanValue();
         var0.K = var2.a(var3, "leadTargetingSpeedCalculation", Float.valueOf(var0.K)).floatValue();
         var0.ae = var2.a(var3, "ballistic", Boolean.valueOf(var0.ae)).booleanValue();
         String var13 = var2.b(var3, "trailEffect", (String)null);
         if(var13 != null) {
            if(var13.equalsIgnoreCase("true")) {
               var0.af = true;
            } else if(var13.equalsIgnoreCase("false")) {
               var0.af = false;
            } else {
               var0.af = false;
               var0.ah = var1.a(var13, (z)null);
            }
         }

         String var14 = var2.b(var3, "effectOnCreate", (String)null);
         if(var14 != null) {
            var0.ai = var1.a(var14, (z)null);
         }

         var0.ag = var2.a(var3, "trailEffectRate", Float.valueOf(var0.ag)).floatValue();
         if(var0.af) {
            var0.ao = -1118720;
         }

         var0.am = var2.a(var3, "wobbleAmplitude", Float.valueOf(var0.am)).floatValue();
         var0.an = var2.b(var3, "wobbleFrequency", Float.valueOf(var0.an)).floatValue();
         if(var0.an <= 0.0F) {
            throw new RuntimeException("wobbleFrequency must be greater than 0");
         } else {
            var0.ak = bi.a(var1, var2, var3, "spawnProjectilesOnEndOfLife", (bi)null);
            var0.aj = bi.a(var1, var2, var3, "spawnProjectilesOnExplode", (bi)null);
            var0.al = bi.a(var1, var2, var3, "spawnProjectilesOnCreate", (bi)null);
            var0.ao = var2.a(var3, "lightColor", Integer.valueOf(var0.ao)).intValue();
            var0.ap = var2.a(var3, "lightSize", Float.valueOf(var0.ap)).floatValue();
            var0.aq = var2.a(var3, "lightCastOnGround", Boolean.valueOf(var0.aq)).booleanValue();
            var0.ar = var2.a(var3, "largeHitEffect", Boolean.valueOf(var0.ar)).booleanValue();
            var0.O = var2.a(var3, "turnSpeed", Float.valueOf(var0.O)).floatValue();
            var0.P = var2.a(var3, "turnSpeedWhenNear", Float.valueOf(var0.P)).floatValue();
            var0.Q = var2.a(var3, "sweepSpeed", Float.valueOf(var0.Q)).floatValue();
            var0.R = var2.a(var3, "sweepOffset", Float.valueOf(var0.R)).floatValue();
            var0.S = var2.a(var3, "sweepOffsetFromTargetRadius", Float.valueOf(var0.S)).floatValue();
            var0.U = var2.a(var3, "drawUnderUnits", Boolean.valueOf(var0.U)).booleanValue();
            var0.V = var2.a(var3, "lightingEffect", Boolean.valueOf(var0.V)).booleanValue();
            var0.W = var2.a(var3, "laserEffect", Boolean.valueOf(var0.W)).booleanValue();
            if(var0.W && var0.Y == null) {
               var0.aE = Color.a(80, 255, 0, 0);
            }

            if(var0.V && var0.s) {
               throw new RuntimeException("lightingEffect must be targeted, cannot be targetGround");
            } else if(var0.W && var0.s) {
               throw new RuntimeException("laserEffect must be targeted, cannot be targetGround");
            } else {
               var0.as = var2.a(var3, "ballistic_delaymove_height", Float.valueOf(var0.as)).floatValue();
               var0.at = var2.a(var3, "ballistic_height", Float.valueOf(var0.at)).floatValue();
               var0.au = var2.a(var3, "targetSpeed", Float.valueOf(var0.au)).floatValue();
               var0.av = var2.a(var3, "targetSpeedAcceleration", Float.valueOf(var0.av)).floatValue();
               var0.aw = var2.a(var3, "autoTargetingOnDeadTarget", Boolean.valueOf(var0.aw)).booleanValue();
               var0.ax = var2.a(var3, "autoTargetingOnDeadTargetRange", Float.valueOf(var0.ax)).floatValue();
               var0.ay = var2.a(var3, "autoTargetingOnDeadTargetLead", Float.valueOf(var0.ay)).floatValue();
               var0.az = var2.a(var3, "retargetingInFlight", Boolean.valueOf(var0.az)).booleanValue();
               var0.aA = var2.a(var3, "retargetingInFlightSearchDelay", Float.valueOf(var0.aA)).floatValue();
               var0.aB = var2.a(var3, "retargetingInFlightSearchRange", Float.valueOf(var0.aB)).floatValue();
               var0.aC = var2.a(var3, "retargetingInFlightSearchLead", Float.valueOf(var0.aC)).floatValue();
               var0.aD = var2.a(var1, var3, "retargetingInFlightSearchOnlyTags", (h)null);
               if(var0.ax > 1500.0F) {
                  throw new RuntimeException("for performance autoTargetingOnDeadTargetRange cannot be >1500");
               } else if(var0.aB > 1500.0F) {
                  throw new RuntimeException("for performance retargetingInFlightSearchRange cannot be >1500");
               } else {
                  var0.aE = var2.a(var3, "color", Integer.valueOf(var0.aE)).intValue();
                  var0.aG = var2.a(var3, "teamColorRatio", Float.valueOf(var0.aG)).floatValue();
                  if(var0.aG >= 0.0F && var0.aG <= 1.0F) {
                     var0.aH = var2.a(var3, "teamColorRatio_sourceRatio", Float.valueOf(1.0F - var0.aG)).floatValue();
                     if(var0.aH >= 0.0F && var0.aH <= 1.0F) {
                        if(var0.aG == 0.0F && var0.aH != 1.0F) {
                           throw new RuntimeException("teamColorRatio_sourceRatio requires teamColorRatio");
                        } else {
                           var0.aF = var2.a(var3, "drawSize", Float.valueOf(var0.aF)).floatValue();
                           var0.aI = var2.a(var3, "flameWeapon", Boolean.valueOf(var0.aI)).booleanValue();
                           var0.aJ = var2.a(var3, "hitSound", Boolean.valueOf(var0.aJ)).booleanValue();
                           var0.aL = var2.a(var3, "targetGroundHeightOffset", Float.valueOf(var0.aL)).floatValue();
                           var0.aK = var2.a(var3, "targetGroundSpread", Float.valueOf(var0.aK)).floatValue();
                           var0.aM = var2.a(var3, "speedSpread", Float.valueOf(var0.aM)).floatValue();
                           var0.aO = var2.a(var3, "explodeOnEndOfLife", Boolean.valueOf(var0.aO)).booleanValue();
                           var0.aN = var2.a(var3, "ignoreParentShootDamageMultiplier", Boolean.valueOf(var0.aN)).booleanValue();
                           var0.aP = var2.a(var3, "pushForce", Float.valueOf(var0.aP)).floatValue();
                           var0.aQ = var2.a(var3, "pushVelocity", Float.valueOf(var0.aQ)).floatValue();
                           var0.aR = var2.a(var3, "buildingDamageMultiplier", Float.valueOf(var0.aR)).floatValue();
                           var0.aS = var2.a(var3, "shieldDamageMultiplier", Float.valueOf(var0.aS)).floatValue();
                           var0.aT = var2.a(var3, "shieldDefectionMultiplier", Float.valueOf(var0.aT)).floatValue();
                           var0.aU = var2.a(var3, "hullDamageMultiplier", Float.valueOf(var0.aU)).floatValue();
                           var0.aV = var2.a(var3, "armourIgnoreAmount", Float.valueOf(var0.aV)).floatValue();
                           var0.aW = var2.a(var3, "areaExpandTime", Float.valueOf(var0.aW)).floatValue();
                           String var15 = var2.b(var3, "explodeEffect", (String)null);
                           if(var15 != null) {
                              var0.aX = var1.a(var15, (z)null);
                           }

                           String var16 = var2.b(var3, "explodeEffectOnShield", (String)null);
                           if(var16 != null) {
                              var0.aY = var1.a(var16, (z)null);
                           }

                           bp var17 = bp.a(var1, var2, var3, "spawnUnit");
                           if(var17 != null && !var17.b()) {
                              var0.aZ = var17;
                           }

                           var0.ba = var2.b(var3, "unloadUpToXUnitsFromSource", Integer.valueOf(var0.ba)).intValue();
                           var0.bb = var2.a(var3, "teleportSource", Boolean.valueOf(var0.bb)).booleanValue();
                           var0.bc = var2.a(var3, "convertHitToSourceTeam", Boolean.valueOf(var0.bc)).booleanValue();
                           var0.bd = g.a(var2.b(var3, "tags", (String)null));
                           com.corrodinggames.rts.gameFramework.utility.m var18 = var2.k(var3, "mutator");
                           com.corrodinggames.rts.gameFramework.utility.m var19 = new com.corrodinggames.rts.gameFramework.utility.m();
                           Iterator var20 = var18.iterator();

                           String var21;
                           while(var20.hasNext()) {
                              var21 = (String)var20.next();
                              String[] var22 = var21.split("_");
                              if(var22.length > 1) {
                                 String var23 = var22[0];
                                 String var24 = var23 + "_";
                                 if(!var19.contains(var24) && var23.length() > "mutator".length()) {
                                    var19.add(var24);
                                 }
                              }
                           }

                           var20 = var19.iterator();

                           while(var20.hasNext()) {
                              var21 = (String)var20.next();
                              com.corrodinggames.rts.game.h var29 = new com.corrodinggames.rts.game.h();
                              var29.a = g.a(var2.b(var4, var21 + "ifUnitWithTags", (String)null));
                              var29.b = g.a(var2.b(var4, var21 + "ifUnitWithoutTags", (String)null));
                              if(var29.a == null && var29.b == null) {
                                 throw new RuntimeException("[" + var4 + "]" + var21 + " requires: unitWithTags and/or unitWithoutTags");
                              }

                              var29.c = var2.a(var4, var21 + "directDamageMultiplier", Float.valueOf(1.0F)).floatValue();
                              var29.d = var2.a(var4, var21 + "areaDamageMultiplier", Float.valueOf(1.0F)).floatValue();
                              com.corrodinggames.rts.game.units.custom.d.b var30 = com.corrodinggames.rts.game.units.custom.d.b.a(var1, var2, var3, var21 + "addResourcesDirectHit", true);
                              if(var30 != null && var30.d()) {
                                 var29.e = var30;
                                 if(var0.s) {
                                    throw new RuntimeException("[" + var4 + "]" + var21 + "addResourcesDirectHit doesn\'t work with targetGround, as it will never get direct hits (use addResourcesAreaHit)");
                                 }
                              }

                              com.corrodinggames.rts.game.units.custom.d.b var31 = com.corrodinggames.rts.game.units.custom.d.b.a(var1, var2, var3, var21 + "addResourcesAreaHit", true);
                              if(var31 != null && var31.d()) {
                                 var29.f = var31;
                                 if(var7 == null) {
                                    throw new RuntimeException("[" + var4 + "]" + var21 + "addResourcesAreaHit requires areaRadius to be set");
                                 }
                              }

                              String var25 = var2.b(var4, var21 + "changedExplodeEffect", (String)null);
                              if(var25 != null) {
                                 var29.g = var1.a(var25, (z)null);
                                 if(var29.g != null && !var29.g.a()) {
                                    var29.g = null;
                                 }
                              }

                              boolean var26 = false;
                              boolean var27 = false;
                              if(!com.corrodinggames.rts.gameFramework.f.k(var29.c, 1.0F)) {
                                 var26 = true;
                              }

                              if(!com.corrodinggames.rts.gameFramework.f.k(var29.d, 1.0F) && var0.c != 0 && var0.i > 0) {
                                 var27 = true;
                              }

                              if(var29.e != null) {
                                 var26 = true;
                              }

                              if(var29.f != null) {
                                 var27 = true;
                              }

                              if(var26) {
                                 if(var0.be == null) {
                                    var0.be = new com.corrodinggames.rts.gameFramework.utility.m();
                                 }

                                 var0.be.add(var29);
                              }

                              if(var27) {
                                 if(var0.bf == null) {
                                    var0.bf = new com.corrodinggames.rts.gameFramework.utility.m();
                                 }

                                 var0.e = true;
                                 var0.bf.add(var29);
                              }

                              if(var29.g != null) {
                                 if(var0.bg == null) {
                                    var0.bg = new com.corrodinggames.rts.gameFramework.utility.m();
                                 }

                                 var0.bg.add(var29);
                              }
                           }

                           if(var0.c != 0 && var0.i > 0) {
                              var0.e = true;
                           }

                           if((var0.aP != 0.0F || var0.aQ != 0.0F) && var0.i > 0) {
                              var0.e = true;
                           }

                           var0.f = !var0.e;
                           var1.fT.add(var0);
                        }
                     } else {
                        throw new RuntimeException("teamColorRatio_sourceRatio should be between 0-1 got:" + var0.aH);
                     }
                  } else {
                     throw new RuntimeException("teamColorRatio should be between 0-1 got:" + var0.aG);
                  }
               }
            }
         }
      }
   }

   public static void a(bh var0, com.corrodinggames.rts.gameFramework.j.as var1) {
      var1.a((com.corrodinggames.rts.game.units.as)var0.bj);
      var1.c(var0.bh);
   }

   public static com.corrodinggames.rts.game.g b(com.corrodinggames.rts.gameFramework.j.k var0) {
      com.corrodinggames.rts.game.units.as var1 = var0.q();
      String var2 = var0.l();
      if(var1 == null) {
         return null;
      } else if(!(var1 instanceof l)) {
         com.corrodinggames.rts.gameFramework.l.b("ProjectileTemplate:readInLinkCustom: Got non CustomUnitMetadata object of:" + var1.i() + " loading real_meta");
         return null;
      } else {
         l var3 = (l)var1;
         bh var4 = var3.f(var2);
         if(var4 == null) {
            com.corrodinggames.rts.gameFramework.l.b("ProjectileTemplate:readInLinkCustom: Could not find projectile with name:" + var2);
            return null;
         } else {
            return var4;
         }
      }
   }

   public void a(com.corrodinggames.rts.game.units.am var1, com.corrodinggames.rts.game.f var2, com.corrodinggames.rts.game.units.am var3, float var4, float var5, float var6) {
      if(var3 == null) {
         var2.aC = true;
         var2.n = var4;
         var2.o = var5;
         if(this.aK != 0.0F) {
            var2.n += (float)com.corrodinggames.rts.gameFramework.f.a(var1, (int)(-this.aK * 100.0F), (int)(this.aK * 100.0F), 2) / 100.0F;
            var1.bC = (int)((float)var1.bC + var2.n);
            var2.o += (float)com.corrodinggames.rts.gameFramework.f.a(var1, (int)(-this.aK * 100.0F), (int)(this.aK * 100.0F), 3) / 100.0F;
            var1.bC = (int)((float)var1.bC + var2.o);
         }

         var2.p = 0.0F;
         var2.p += this.aL;
      } else if(var2.m) {
         var2.aC = true;
         if(!this.J) {
            float var8 = var2.t;
            if(this.au != -1.0F) {
               var8 = this.au;
            }

            if(this.K >= 0.0F) {
               var8 = this.K;
            }

            PointF var9 = var3.a(var2.eo, var2.ep, var8, var2.h, var6);
            var2.n = var9.a;
            var2.o = var9.b;
         } else {
            var2.n = var3.eo;
            var2.o = var3.ep;
         }

         if(this.t) {
            var2.p = var3.eq;
         } else {
            var2.p = 0.0F;
         }

         var2.p += this.aL;
         if(this.aK != 0.0F) {
            var2.n += (float)com.corrodinggames.rts.gameFramework.f.a(var1, (int)(-this.aK * 100.0F), (int)(this.aK * 100.0F), 2) / 100.0F;
            var2.o += (float)com.corrodinggames.rts.gameFramework.f.a(var1, (int)(-this.aK * 100.0F), (int)(this.aK * 100.0F), 7) / 100.0F;
         }
      } else {
         var2.l = var3;
      }

   }
}
