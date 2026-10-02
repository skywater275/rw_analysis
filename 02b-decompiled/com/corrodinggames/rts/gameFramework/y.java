package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.bs;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.s;
import com.corrodinggames.rts.gameFramework.w;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

public class y {

   public static boolean a = false;
   final boolean b;
   int c;
   int d;


   public strictfp y() {
      if(!l.as) {
         ;
      }

      this.b = false;
      this.c = -9999;
      this.d = -9999;
   }

   public strictfp File a(String var1, boolean var2) {
      return a(var1, "saves/", var2);
   }

   public static strictfp File a(String var0, String var1, boolean var2) {
      return com.corrodinggames.rts.gameFramework.e.a.a(var0, var1, var2);
   }

   public strictfp void b(String var1, boolean var2) {
      l var3 = l.B();
      String var4 = var1;
      if(var1 != null && !var1.endsWith(".rwsave")) {
         var4 = var1 + ".rwsave";
      }

      String var5 = "SD card";
      File var6 = null;
      boolean var7 = false;

      String var9;
      try {
         var6 = this.a(var4 + ".tmp", true);
         if(var6.exists()) {
            var6 = this.a(var4 + ".tmp2", true);
         }

         File var8 = this.a(var4, true);
         var5 = var8.getAbsolutePath();
         l.e("Saving game to: " + var5);
         OutputStream var27 = com.corrodinggames.rts.gameFramework.e.a.a(var6, false);
         BufferedOutputStream var28 = new BufferedOutputStream(var27);
         if(!a) {
            DataOutputStream var11 = new DataOutputStream(var28);
            com.corrodinggames.rts.gameFramework.j.as var12 = new com.corrodinggames.rts.gameFramework.j.as(var11);

            try {
               this.a(var12);
            } finally {
               var11.close();
               var28.close();
               var27.close();
            }
         } else {
            PrintStream var29 = new PrintStream(var28);
            com.corrodinggames.rts.gameFramework.j.aw var31 = new com.corrodinggames.rts.gameFramework.j.aw(var29);

            try {
               this.a((com.corrodinggames.rts.gameFramework.j.as)var31);
            } finally {
               var29.close();
               var28.close();
               var27.close();
            }

            l.n("DEBUG plain text save created");
         }

         boolean var30;
         if(var2 && l.at() && com.corrodinggames.rts.gameFramework.e.a.i(var8.getAbsolutePath())) {
            l.e("Autosave file already exists: " + var8.getAbsolutePath());
            var30 = com.corrodinggames.rts.gameFramework.e.a.b(var8);
            if(!var30) {
               l.e("Old autosave failed to delete");
            }
         }

         l.e("Finished writing save, renaming to final filename");
         var30 = com.corrodinggames.rts.gameFramework.e.a.b(var6, var8);
         if(!var30) {
            l.e("Failed to rename to final file");
            throw new IOException("Failed to rename to final file. Check file permissions of storage.");
         }

         com.corrodinggames.rts.gameFramework.e.a.c(var8);
         var7 = true;
      } catch (Exception var25) {
         if(var2) {
            l.e("Auto save failed: " + var25.getMessage());
            return;
         }

         var25.printStackTrace();
         var9 = f.b(var25);
         String var10 = "Error saving game, please check permissions, disk space, etc. (" + var9 + ")";
         var3.a(var10, 1);
         if(var6 != null && com.corrodinggames.rts.gameFramework.e.a.i(var6.getAbsolutePath())) {
            l.e("saveGame: Removing temp save file after crash");
            com.corrodinggames.rts.gameFramework.e.a.b(var6);
         }
      } catch (OutOfMemoryError var26) {
         var26.printStackTrace();
         var9 = "Error. Run out of memory error while saving game to " + var5 + ".";
         var3.a(var9, 1);
         if(var6 != null && com.corrodinggames.rts.gameFramework.e.a.i(var6.getAbsolutePath())) {
            l.e("saveGame: Removing temp save file after crash");
            com.corrodinggames.rts.gameFramework.e.a.b(var6);
         }
      }

      if(var7) {
         if(var2) {
            var3.bS.i.a("Auto Saved", 1000);
         } else {
            var3.bS.h.a((String)null, "Game saved");
         }
      }

   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.as var1) {
      l var2 = l.B();
      long var3 = System.currentTimeMillis();
      l.b("GameSaver", "saveCurrentMap took:" + (System.currentTimeMillis() - var3));
      long var5 = System.currentTimeMillis();

      try {
         var1.c("rustedWarfareSave");
         int var7 = var2.c(true);
         var1.a(var7);
         var1.a((int)96);
         var1.a(var2.ar);
         var1.a("saveCompression", true);
         var1.e("customUnitsBlock");
         com.corrodinggames.rts.game.units.custom.l.a(var1);
         var1.a("customUnitsBlock");
         var1.e("gameSetup");
         boolean var8 = var2.bX.B || var2.bX.F;
         var1.a(var2.bX.B);
         var1.a(var2.bX.F);
         var1.a(var8);
         if(var8) {
            var2.bX.a(var1);
         }

         var1.a("gameSetup");
         var1.c(var2.dl);
         boolean var9 = var2.dm != null;
         var1.a(var9);
         if(var9) {
            l.e("Writing remote map steam into save");
            var1.a(var2.dm);
         }

         var1.a(var2.by);
         var1.a(var2.cy + var2.cI);
         var1.a(var2.cz + var2.cJ);
         var1.a(var2.cV);
         var1.a(var2.bV.a);
         var1.a((int)0);
         var1.e();
         var2.bL.a(var1);
         var1.a(var2.bv);
         var1.a(var2.bL.E);
         var1.a(var2.bL.F);
         var1.a(var2.bL.G);
         var1.a(var2.ce != null);
         if(var2.ce != null) {
            var2.ce.a(var1);
         }

         var1.e();
         int var10 = -1;
         if(var2.bs != null) {
            var10 = var2.bs.k;
         }

         var1.a(var10);
         var1.a(com.corrodinggames.rts.game.n.c);

         int var11;
         com.corrodinggames.rts.game.n var12;
         for(var11 = 0; var11 < com.corrodinggames.rts.game.n.c; ++var11) {
            var12 = com.corrodinggames.rts.game.n.k(var11);
            var1.a(var12 instanceof com.corrodinggames.rts.game.a.a);
            var1.a(var12 instanceof com.corrodinggames.rts.game.c);
            var1.a(var12 != null);
            if(var12 != null) {
               var12.b(var1);
            }
         }

         if(!var2.bS.e) {
            ;
         }

         var1.d("Section: unit shells");
         var1.a(w.er.size());

         Iterator var16;
         w var17;
         String var18;
         for(var16 = w.er.iterator(); var16.hasNext(); var1.a(var17.eh)) {
            var17 = (w)var16.next();
            if(var17 == null) {
               throw new RuntimeException("Found null in fastGameObjectList");
            }

            if(var17 instanceof com.corrodinggames.rts.game.units.am) {
               com.corrodinggames.rts.game.units.am var13 = (com.corrodinggames.rts.game.units.am)var17;
               if(var13.r() instanceof com.corrodinggames.rts.game.units.ar) {
                  var1.c(1);
                  var1.a((Enum)((com.corrodinggames.rts.game.units.ar)var13.r()));
               } else {
                  if(!(var13.r() instanceof com.corrodinggames.rts.game.units.custom.l)) {
                     throw new IOException("Unhandled getUnitType on save:" + var13.r().getClass().toString());
                  }

                  var1.c(3);
                  String var14 = ((com.corrodinggames.rts.game.units.custom.l)var13.r()).M;
                  var1.c(var14);
               }
            } else {
               var1.c(2);
               if(var17 instanceof com.corrodinggames.rts.game.l) {
                  var1.c(1);
               } else if(var17 instanceof com.corrodinggames.rts.game.f) {
                  var1.c(2);
               } else {
                  if(!(var17 instanceof com.corrodinggames.rts.gameFramework.d.f)) {
                     var18 = null;
                     if(var17.getClass() != null) {
                        var18 = var17.getClass().toString();
                     }

                     throw new IOException("Unhandled class on save: " + var18);
                  }

                  var1.c(3);
               }
            }
         }

         var1.d("Section: CurrentUnitId");
         var1.a(var2.bX.z());
         var2.bV.a(var1);
         var2.bS.a(var1);
         var2.bY.a(var1);

         for(var11 = 0; var11 < com.corrodinggames.rts.game.n.c; ++var11) {
            var12 = com.corrodinggames.rts.game.n.k(var11);
            if(var12 != null) {
               var12.a(var1);
            }
         }

         var1.e();
         var16 = w.er.iterator();

         while(true) {
            if(!var16.hasNext()) {
               var1.a("saveCompression");
               var1.e();
               var1.c("<SAVE END>");
               break;
            }

            var17 = (w)var16.next();
            if(var1.f()) {
               var18 = var17.getClass().getSimpleName();
               if(var17 instanceof com.corrodinggames.rts.game.units.am) {
                  var18 = ((com.corrodinggames.rts.game.units.am)var17).r().i();
               }

               var1.d("Saving unit:" + var18 + " (id" + var17.eh + ")");
            }

            var17.a(var1);
            var1.e();
         }
      } catch (IOException var15) {
         var15.printStackTrace();
         throw var15;
      }

      l.b("GameSaver", "saveGame took:" + (System.currentTimeMillis() - var5));
   }

   public strictfp String a(String var1) {
      return var1 == null?null:(var1.equals("maps/normal/l010;mission_1__-__Dividing_River.tmx")?"maps/normal/l010;[demo]mission_1__-__Dividing_River.tmx":(var1.equals("maps/normal/l030;mission_3__-__Crossfire.tmx")?"maps/normal/l030;[demo]mission_3__-__Crossfire.tmx":var1));
   }

   public strictfp boolean c(String var1, boolean var2) {
      l var3 = l.B();

      try {
         File var4 = this.a(var1, false);
         if(var4.isDirectory()) {
            var3.a("Could not load, is a directory", 1);
            return false;
         } else {
            com.corrodinggames.rts.gameFramework.utility.j var5 = com.corrodinggames.rts.gameFramework.e.a.k(var4.getAbsolutePath());
            if(var5 == null) {
               var3.a("Could not load, failed to open: " + com.corrodinggames.rts.gameFramework.e.a.d(var4.getAbsolutePath()), 1);
               return false;
            } else {
               BufferedInputStream var6 = new BufferedInputStream(var5);
               DataInputStream var7 = new DataInputStream(var6);
               com.corrodinggames.rts.gameFramework.j.k var8 = new com.corrodinggames.rts.gameFramework.j.k(var7);

               boolean var9;
               try {
                  var9 = this.a(var8, var2, false, false);
               } finally {
                  var7.close();
                  var6.close();
                  var5.close();
               }

               return var9;
            }
         }
      } catch (Exception var14) {
         throw new RuntimeException(var14);
      }
   }

   public strictfp void a(String var1, com.corrodinggames.rts.gameFramework.j.as var2) {
      File var3 = this.a(var1, false);
      if(var3 == null) {
         throw new IOException("Failed to get game save: " + var1);
      } else {
         var2.a(var3);
      }
   }

   public synchronized strictfp boolean a(com.corrodinggames.rts.gameFramework.j.k var1, boolean var2, boolean var3, boolean var4) {
      l var5 = l.B();

      Iterator var8;
      try {
         br var6 = var5.cd;
         if(this.b) {
            var6.a(bs.y);
         }

         ArrayList var41 = null;
         if(var4) {
            var41 = new ArrayList();
            var8 = var5.bS.bZ.iterator();

            while(var8.hasNext()) {
               com.corrodinggames.rts.game.units.am var43 = (com.corrodinggames.rts.game.units.am)var8.next();
               var41.add(Long.valueOf(var43.eh));
            }
         }

         String var10;
         String var42;
         try {
            var42 = var1.l();
         } catch (EOFException var37) {
            var37.printStackTrace();
            var10 = "Failed to load save. (End of file trying to read header)";
            l.b(var10);
            var5.a(var10, 1);
            return false;
         } catch (IOException var38) {
            var38.printStackTrace();
            var10 = "Failed to load save. (Failed to read header: " + var38.getMessage() + ")";
            l.b(var10);
            var5.a(var10, 1);
            return false;
         }

         if(!var42.equals("rustedWarfareSave")) {
            l.b("Map Load: Header is not correct:" + var42.substring(0, Math.min(var42.length(), 50)));
            String var45 = "Failed to load save. (Could not find correct header)";
            if(var42.equals("rustedWarfareReplay")) {
               var45 = "Failed to load save. (This file appears to be a replay file, not a save file)";
            }

            l.b(var45);
            var5.a(var45, 1);
            return false;
         } else {
            var1.f();
            int var44 = var1.f();
            l.b("gameSaver", "Loading save from version: " + var44);
            var1.a(var44);
            if(var44 > 96) {
               var5.a("Cannot load: This save was made with a newer game", 1);
               return false;
            } else {
               if(var44 >= 5) {
                  var1.e();
               }

               if(var44 >= 23) {
                  var6.a(bs.B);
                  var1.a("saveCompression", true);
                  var6.b(bs.B);
               }

               if(var44 >= 54) {
                  var1.b("customUnitsBlock");
                  if(var5.cb.j() && !var4) {
                     l.e("Loading mods from replay");

                     try {
                        com.corrodinggames.rts.game.units.custom.l.a(var1);
                        com.corrodinggames.rts.game.units.custom.ag.d();
                     } catch (com.corrodinggames.rts.game.units.custom.bd var36) {
                        l.e("Replay load: Missing unit:" + var36.getMessage() + " d:" + var36.b);
                        var5.i(var36.getMessage() + ", this is likely to cause the replay to desync (reverting to default units & mods)");
                        com.corrodinggames.rts.game.units.custom.ag.b(true);
                     }
                  }

                  var1.d("customUnitsBlock");
               }

               Integer var46 = null;
               Integer var11 = null;
               if(var5.cb.j() && var4) {
                  var46 = Integer.valueOf(var5.bB);
                  var11 = Integer.valueOf(var5.bC);
               }

               boolean var13;
               if(var44 >= 56) {
                  var1.b("gameSetup");
                  boolean var12 = var1.e();
                  var13 = var12;
                  boolean var14 = false;
                  if(var44 >= 94) {
                     var14 = var1.e();
                     var13 = var1.e();
                  }

                  boolean var15 = var5.cb.j() || !var5.bX.B;
                  if(var15 && !var4 && var13) {
                     l.e("Using game rules from save");
                     var5.cb.O = true;
                     var5.bX.a(var1);
                     var46 = Integer.valueOf(var5.bB);
                     var11 = Integer.valueOf(var5.bC);
                     if((var12 || var14) && !var5.bX.F && !var5.bX.B && !var5.cb.j()) {
                        l.e("Enabling use of singlePlayer rules from saved game.");
                        var5.bX.F = true;
                     }
                  }

                  var1.d("gameSetup");
               }

               var5.dm = null;
               String var47 = var1.l();
               var47 = com.corrodinggames.rts.gameFramework.e.a.o(var47);
               var5.dl = this.a(var47);
               var13 = false;
               if(var44 >= 72) {
                  var13 = var1.e();
                  if(var13) {
                     l.e("Reading remote map stream");
                     var5.dm = var1.u();
                  }
               }

               if(var5.bX.B && !var5.bX.C && var4 && var5.bX.aB != null && !var13) {
                  var5.dl = "";
                  var5.dm = var5.bX.aB;
               }

               var6.a(bs.z);
               if(var4) {
                  var5.a(true, true, s.c);
                  if(l.at()) {
                     var5.dv = true;
                  }
               } else {
                  var5.a(true, s.c);
               }

               if(!var5.bL.W) {
                  l.e("Not loading save because map failed to load");
                  return false;
               } else {
                  if(var46 != null) {
                     var5.bB = var46.intValue();
                  }

                  if(var11 != null) {
                     var11 = Integer.valueOf(var5.bC);
                  }

                  synchronized(var5) {
                     var6.b(bs.z);
                     var5.by = var1.f();
                     float var48 = var1.g();
                     float var16 = var1.g();
                     float var17 = var1.g();
                     if(!var4) {
                        var5.b(var48, var16);
                        var5.cV = var17;
                     }

                     if(var44 >= 18) {
                        var5.bV.a = var1.f();
                     }

                     var1.f();
                     if(var44 >= 19) {
                        var1.a("end of setup");
                     }

                     var5.bL.a(var1);
                     boolean var18;
                     if(var44 >= 86) {
                        var18 = var1.e();
                        boolean var19 = var1.e();
                        boolean var20 = var1.e();
                        boolean var21 = var1.e();
                        if(!var2 && !var18) {
                           var5.bL.E = var19;
                           var5.bL.F = var20;
                           var5.bL.G = var21;
                        }
                     }

                     var18 = var1.e();
                     if(var18) {
                        if(var5.ce == null) {
                           l.b("gameSaver", "making new mission engine on load, this shouldn\'t happen");
                           var5.ce = new com.corrodinggames.rts.gameFramework.n.f();
                           var5.ce.a(false);
                        }

                        var5.ce.a(var1);
                     }

                     if(var44 >= 19) {
                        var1.a("start of teams");
                     }

                     l.b("gameSaver", "loading teams");
                     com.corrodinggames.rts.game.n[] var49 = new com.corrodinggames.rts.game.n[com.corrodinggames.rts.game.n.e];
                     int var50 = -1;
                     if(var44 >= 36) {
                        var50 = var1.f();
                     }

                     int var51 = 8;
                     int var22;
                     if(var44 >= 49) {
                        var51 = var1.f();
                        com.corrodinggames.rts.game.n.b(var51, false);

                        for(var22 = 0; var22 < com.corrodinggames.rts.game.n.c; ++var22) {
                           if(var22 >= var51 && !var2) {
                              com.corrodinggames.rts.game.n var23 = com.corrodinggames.rts.game.n.k(var22);
                              if(var23 != null) {
                                 var23.I();
                              }
                           }
                        }
                     }

                     Integer var28;
                     for(var22 = 0; var22 < var51; ++var22) {
                        Object var53 = com.corrodinggames.rts.game.n.k(var22);
                        Object var24 = var53;
                        boolean var25 = var1.e();
                        boolean var26 = false;
                        if(var44 >= 7) {
                           var26 = var1.e();
                        }

                        boolean var27 = var1.e();
                        if(var27) {
                           if(var25) {
                              if(var53 == null || !(var53 instanceof com.corrodinggames.rts.game.a.a)) {
                                 if(var2 && !var4 && var53 != null) {
                                    l.b("Would replace team:" + var22 + " with AI, writing to dummy AI");
                                    var53 = new com.corrodinggames.rts.game.a.a(var22, false);
                                    var49[var22] = (com.corrodinggames.rts.game.n)var53;
                                 } else {
                                    if(var4) {
                                       l.b("Adding new AI " + var22 + " on resync");
                                    }

                                    var53 = new com.corrodinggames.rts.game.a.a(var22);
                                 }
                              }
                           } else if(var26) {
                              if(var53 == null || !(var53 instanceof com.corrodinggames.rts.game.c)) {
                                 if(var2) {
                                    l.b("Replacing team:" + var22 + " with NetworkedPlayer");
                                 }

                                 var53 = new com.corrodinggames.rts.game.c(var22);
                              }
                           } else if(var53 == null || !(var53 instanceof com.corrodinggames.rts.game.e)) {
                              if(var2) {
                                 l.b("Replacing team:" + var22 + " with Player");
                                 if(var53 != null) {
                                    ((com.corrodinggames.rts.game.n)var53).c("Existing");
                                 }
                              }

                              var53 = new com.corrodinggames.rts.game.e(var22);
                           }

                           var28 = ((com.corrodinggames.rts.game.n)var53).z;
                           if(var44 >= 2) {
                              ((com.corrodinggames.rts.game.n)var53).b(var1);
                           } else {
                              ((com.corrodinggames.rts.game.n)var53).c(var1);
                           }

                           if(!var4) {
                              ((com.corrodinggames.rts.game.n)var53).i();
                              if(var2) {
                                 ((com.corrodinggames.rts.game.n)var53).z = var28;
                                 ((com.corrodinggames.rts.game.n)var53).c("networkLoad aiDifficultyOverride=" + var28);
                                 var5.bX.a((com.corrodinggames.rts.game.n)var53);
                                 var5.bX.b((com.corrodinggames.rts.game.n)var53);
                              }

                              if(var24 != null && var53 != var24) {
                                 ((com.corrodinggames.rts.game.n)var24).c("Transfering team stats");
                                 ((com.corrodinggames.rts.game.n)var24).o = ((com.corrodinggames.rts.game.n)var53).o;
                                 ((com.corrodinggames.rts.game.n)var24).V().a(((com.corrodinggames.rts.game.n)var53).V());
                              }
                           }
                        } else if(var2 && !var5.cb.j()) {
                           l.b("GameSaver: Would normally remove team:" + var22 + "");
                           var49[var22] = com.corrodinggames.rts.game.n.g;
                        } else {
                           com.corrodinggames.rts.game.n var64 = com.corrodinggames.rts.game.n.k(var22);
                           if(var64 != null) {
                              var64.I();
                           }
                        }
                     }

                     boolean var52 = false;
                     boolean var55 = false;
                     var5.bX.aq();
                     if(var5.cb.j()) {
                        var5.bs = com.corrodinggames.rts.game.n.i;
                     } else {
                        int var54;
                        if(var5.bX.B) {
                           if(var5.bX.z != null) {
                              var54 = var5.bX.z.k;
                              if(var54 != -3) {
                                 com.corrodinggames.rts.game.n var57 = com.corrodinggames.rts.game.n.k(var54);
                                 if(var57 == null) {
                                    throw new RuntimeException("GameSaver: Cannot relink player team: " + var54);
                                 }

                                 var5.bs = var57;
                              }
                           }
                        } else if(var50 != -1 && var50 != -3) {
                           var5.bs = com.corrodinggames.rts.game.n.k(var50);
                        } else {
                           for(var54 = 0; var54 < com.corrodinggames.rts.game.n.c; ++var54) {
                              if(com.corrodinggames.rts.game.n.k(var54) instanceof com.corrodinggames.rts.game.e) {
                                 var5.bs = com.corrodinggames.rts.game.n.k(var54);
                              }
                           }
                        }
                     }

                     com.corrodinggames.rts.gameFramework.utility.o var56 = w.dK();
                     Iterator var58 = var56.iterator();

                     w var61;
                     while(var58.hasNext()) {
                        var61 = (w)var58.next();
                        var61.a();
                     }

                     if(var5.aa()) {
                        var56 = w.dK();
                        var58 = var56.iterator();

                        while(var58.hasNext()) {
                           var61 = (w)var58.next();
                           if(var61.eh == 0L) {
                              if(var61 instanceof com.corrodinggames.rts.game.units.am) {
                                 l.e("object: " + ((com.corrodinggames.rts.game.units.am)var61).c());
                              }

                              throw new RuntimeException("GameLoad preload: Found object in list with id:0");
                           }
                        }
                     }

                     boolean var59 = false;
                     int var60 = var1.f();

                     int var63;
                     for(var63 = 0; var63 < var60; ++var63) {
                        Object var62 = null;
                        byte var66 = var1.d();
                        if(var66 == 1) {
                           com.corrodinggames.rts.game.units.ar var29 = (com.corrodinggames.rts.game.units.ar)var1.b(com.corrodinggames.rts.game.units.ar.class);
                           if(var29 == com.corrodinggames.rts.game.units.ar.Y) {
                              if(!var5.cb.j() && !var5.bv) {
                                 l.e("Creating DebugEditorBuilder for load");
                                 var62 = new com.corrodinggames.rts.game.units.h(false);
                                 var52 = true;
                              } else {
                                 l.e("Creating DebugEditorBuilder for replay");
                                 var62 = new com.corrodinggames.rts.game.units.h(false);
                                 com.corrodinggames.rts.game.units.h var30 = var5.bS.i();
                                 if(var30 == null || var30.ej) {
                                    l.e("Relinking editor");
                                    var5.bS.a((com.corrodinggames.rts.game.units.h)var62);
                                 }
                              }
                           } else {
                              var62 = var29.a();
                           }
                        } else if(var66 == 3) {
                           String var67 = var1.l();
                           com.corrodinggames.rts.game.units.custom.l var74 = com.corrodinggames.rts.game.units.custom.l.n(var67);
                           if(var74 == null) {
                              String var31 = "Could not find custom unit:" + var67;
                              l.e(var31);
                              if(!var59) {
                                 var59 = true;
                                 com.corrodinggames.rts.gameFramework.j.ad.g(var31);
                              }

                              var74 = com.corrodinggames.rts.game.units.custom.l.b;
                              if(var74 == null) {
                                 throw new RuntimeException("Could not find custom unit:" + var67 + " and missingPlaceHolder is null");
                              }
                           }

                           com.corrodinggames.rts.game.units.as var79 = com.corrodinggames.rts.game.units.custom.l.c((com.corrodinggames.rts.game.units.as)var74);
                           if(var79 != null) {
                              if(var79 instanceof com.corrodinggames.rts.game.units.custom.l) {
                                 var74 = (com.corrodinggames.rts.game.units.custom.l)var79;
                              } else {
                                 l.b("replacement not a custom unit:" + var79.i());
                              }
                           }

                           var62 = var74.a();
                        } else {
                           if(var66 != 2) {
                              throw new IOException("Unhandled basic type on load:" + var66);
                           }

                           byte var69 = var1.d();
                           if(var69 == 1) {
                              var62 = new com.corrodinggames.rts.game.l();
                           } else if(var69 == 2) {
                              var62 = new com.corrodinggames.rts.game.f(false);
                           } else {
                              if(var69 != 3) {
                                 throw new IOException("Unhandled gameType on load:" + var69);
                              }

                              var62 = new com.corrodinggames.rts.gameFramework.d.f(var5.bR);
                           }
                        }

                        ((w)var62).eh = var1.i();
                        if(((w)var62).eh == 0L) {
                           l.b("GameSaver: Adding object with id==0");
                           if(var62 instanceof com.corrodinggames.rts.game.units.am) {
                              l.b(((com.corrodinggames.rts.game.units.am)var62).cC());
                           }

                           var55 = true;
                        }

                        w.dL();
                     }

                     if(var44 >= 3) {
                        long var65 = var1.i();
                        if(var65 <= 0L) {
                           l.a("GameLoad: Trying to set next unit id <= 0: " + var65);
                           var65 = 100000L;
                        }

                        var5.bX.a(var65);
                     } else {
                        var5.bX.a(100000L);
                     }

                     if(var44 >= 24) {
                        var5.bV.a(var1);
                     }

                     if(var44 >= 4) {
                        var5.bS.a(var1, var4);
                     }

                     if(var44 >= 57) {
                        var5.bY.a(var1, var4);
                     }

                     if(var44 >= 7) {
                        for(var63 = 0; var63 < var51; ++var63) {
                           com.corrodinggames.rts.game.n var75 = com.corrodinggames.rts.game.n.k(var63);
                           if(var49[var63] != null) {
                              var75 = var49[var63];
                              if(var75 == com.corrodinggames.rts.game.n.g) {
                                 var75 = null;
                              }
                           }

                           if(var75 != null) {
                              var28 = var75.z;
                              var75.c(var1);
                              if(!var4) {
                                 if(var2) {
                                    var75.z = var28;
                                    var75.c("networkLoad2 aiDifficultyOverride=" + var28);
                                 }

                                 var5.bX.a(var75);
                                 var5.bX.b(var75);
                              }
                           }
                        }
                     } else if(var44 >= 2) {
                        ;
                     }

                     if(var44 >= 10) {
                        var1.a("Pre-unit data");
                     }

                     var6.a(bs.A);
                     com.corrodinggames.rts.gameFramework.utility.o var71 = w.dK();
                     l.b("gameSaver", "Loading unit data for " + var71.size() + " objects.");
                     Iterator var80 = var71.iterator();

                     while(var80.hasNext()) {
                        w var68 = (w)var80.next();
                        var68.a(var1);
                        if(var44 >= 10) {
                           var1.a("post unit: " + var68.getClass().toString() + " with id:" + var68.eh);
                        }
                     }

                     l.b("gameSaver", "Loading unit data done.");
                     var6.b(bs.A);
                     com.corrodinggames.rts.game.units.am var83;
                     if(var52) {
                        com.corrodinggames.rts.gameFramework.utility.o var82 = w.dK();
                        Iterator var70 = var82.iterator();

                        while(var70.hasNext()) {
                           w var72 = (w)var70.next();
                           if(var72 instanceof com.corrodinggames.rts.game.units.am) {
                              var83 = (com.corrodinggames.rts.game.units.am)var72;
                              if(!var5.cb.j() && !var5.bv && var83.r() == com.corrodinggames.rts.game.units.ar.Y) {
                                 var83.ci();
                              }
                           }
                        }
                     }

                     if(var44 >= 23) {
                        var1.d("saveCompression");
                     }

                     if(var44 >= 19) {
                        var1.a("End of Save");
                        var1.l();
                     }

                     l.b("gameSaver", "Checking for ID overlaps");
                     int var84 = 0;
                     boolean var73 = true;
                     if(var73) {
                        w[] var76 = w.er.a();
                        int var85 = w.er.size();

                        for(int var81 = 0; var81 < var85; ++var81) {
                           w var32 = var76[var81];
                           if(var32.eh == 0L) {
                              l.b("GameSaver: Fixing object with zero id.");
                              var32.eh = var5.bX.y();
                           }

                           for(int var33 = var81 + 1; var33 < var85; ++var33) {
                              w var34 = var76[var33];
                              if(var32 != var34 && var32.eh == var34.eh) {
                                 ++var84;
                                 var34.eh = var5.bX.y();
                              }
                           }
                        }
                     }

                     l.b("gameSaver", "clearing out dead units.");
                     l.e("Unit.fastLiveUnitList before:" + com.corrodinggames.rts.game.units.am.bE.size());
                     Iterator var77 = com.corrodinggames.rts.game.units.am.bE.iterator();

                     while(var77.hasNext()) {
                        var83 = (com.corrodinggames.rts.game.units.am)var77.next();
                        if(var83.bV) {
                           var77.remove();
                        }
                     }

                     l.e("Unit.fastLiveUnitList after:" + com.corrodinggames.rts.game.units.am.bE.size());
                     if(var84 > 0) {
                        if(var44 <= 2) {
                           var5.a("Warning: " + var84 + " errors were found in this save, this is due to a bug in the old version", 1);
                        } else {
                           var5.a("Warning: " + var84 + " errors were found in this save", 1);
                        }
                     }

                     l.b("gameSaver", "Fixing map cost.");
                     var5.bU.a((com.corrodinggames.rts.game.units.y)null);
                     var5.bU.b();
                     l.b("gameSaver", "Fixing map cost done.");
                     com.corrodinggames.rts.game.n.O();

                     int var78;
                     com.corrodinggames.rts.game.n var90;
                     for(var78 = 0; var78 < com.corrodinggames.rts.game.n.c; ++var78) {
                        var90 = com.corrodinggames.rts.game.n.k(var78);
                        if(var90 != null) {
                           var90.d(false);
                        }
                     }

                     com.corrodinggames.rts.game.n.e();
                     l.b("gameSaver", "Rebuilt unit caches");
                     com.corrodinggames.rts.game.n.O();
                     com.corrodinggames.rts.game.n.Y();
                     com.corrodinggames.rts.game.n.i.d(false);
                     com.corrodinggames.rts.game.n.h.d(false);

                     for(var78 = 0; var78 < com.corrodinggames.rts.game.n.c; ++var78) {
                        var90 = com.corrodinggames.rts.game.n.k(var78);
                        if(var90 != null && var90 instanceof com.corrodinggames.rts.game.a.a) {
                           com.corrodinggames.rts.game.a.a var86 = (com.corrodinggames.rts.game.a.a)var90;
                           var86.aq();
                        }
                     }

                     if(var41 != null) {
                        var5.bS.y();
                        var77 = var41.iterator();

                        while(var77.hasNext()) {
                           long var91 = ((Long)var77.next()).longValue();
                           com.corrodinggames.rts.game.units.am var87 = w.a(var91, true);
                           if(var87 != null) {
                              var5.bS.k(var87);
                           }
                        }
                     }

                     if(var5.aa()) {
                        com.corrodinggames.rts.gameFramework.utility.o var88 = w.dK();
                        Iterator var92 = var88.iterator();

                        while(var92.hasNext()) {
                           w var89 = (w)var92.next();
                           if(var89.eh == 0L) {
                              if(var89 instanceof com.corrodinggames.rts.game.units.am) {
                                 l.e("object: " + ((com.corrodinggames.rts.game.units.am)var89).c());
                              }

                              throw new RuntimeException("GameLoad postload: Found object in list with id:0");
                           }
                        }
                     }

                     l.e("--- Save file load complete ---");
                     l.e("GameObject.fastGameObjectList:" + w.er.size());
                     l.e("Unit.fastLiveUnitList:" + com.corrodinggames.rts.game.units.am.bE.size());
                     if(!var4) {
                        var5.cb.a(var4);
                     }

                     if(this.b) {
                        var6.b(bs.y);
                        var6.a(true, true);
                     }

                     return true;
                  }
               }
            }
         }
      } catch (Exception var40) {
         var40.printStackTrace();
         l.e("Save load error, clearing all units");
         com.corrodinggames.rts.gameFramework.utility.o var7 = w.dK();

         w var9;
         for(var8 = var7.iterator(); var8.hasNext(); var9.a()) {
            var9 = (w)var8.next();
            if(var9.eh == 0L) {
               var9.eh = var5.bX.y();
            }
         }

         throw new RuntimeException(var40);
      }
   }

   public strictfp boolean b(String var1) {
      l.e("Deleting: " + var1);
      String var2 = com.corrodinggames.rts.gameFramework.e.a.o(var1);
      if(!var2.contains("\\") && !var2.contains("/")) {
         File var4 = this.a(var1, true);
         boolean var3 = com.corrodinggames.rts.gameFramework.e.a.b(var4);
         File var5 = this.a(var1 + ".map", true);
         com.corrodinggames.rts.gameFramework.e.a.b(var5);
         if(!var3) {
            l.e("Failed to delete: " + var4.getAbsolutePath());
            l.B().i("Failed to delete: " + var4.getAbsolutePath());
         }

         return true;
      } else {
         l.e("Cannot get save with path: " + var1);
         return false;
      }
   }

   public strictfp void a(boolean var1) {
      l var2 = l.B();
      if(!var1) {
         this.c = -9999;
         this.d = -9999;
      }

   }

   public strictfp boolean a() {
      l var1 = l.B();
      return !var1.bQ.autosaving?false:(l.ax()?false:(var1.bG && !var1.bH && !var1.cb.j()?!var1.M():false));
   }

   public strictfp void b() {
      int var1 = 300000;
      l var2 = l.B();
      if(this.a()) {
         if(this.d == -9999) {
            this.c = var2.by;
            this.d = var2.by;
         }

         if(this.d + var1 < var2.by) {
            this.d = var2.by;
            long var3 = br.a();
            this.c();
            double var5 = (double)br.a(var3);
            l.e("Autosaved (" + br.a(var5) + ")");
         }

      }
   }

   public strictfp void c() {
      this.b("autosave", true);
   }

}
