package com.corrodinggames.rts.gameFramework;

import android.content.Context;
import com.corrodinggames.rts.gameFramework.bb;
import com.corrodinggames.rts.gameFramework.bc;
import com.corrodinggames.rts.gameFramework.bd;
import com.corrodinggames.rts.gameFramework.e;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.w;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

public class ba {

   String a = "replays/";
   public static boolean b = true;
   public static boolean c = true;
   public static boolean d = false;
   public static boolean e = true;
   public static boolean f = false;
   public com.corrodinggames.rts.gameFramework.j.ak g = new com.corrodinggames.rts.gameFramework.j.ak();
   public boolean h;
   int i;
   int j;
   boolean k;
   int l;
   boolean m;
   public boolean n = false;
   public int o;
   public int p;
   public int q;
   public String r;
   boolean s;
   private volatile boolean P;
   String t;
   boolean u;
   public int v = 1;
   bd w;
   bd x;
   int y;
   int z;
   int A;
   int B;
   InputStream C;
   BufferedInputStream D;
   DataInputStream E;
   com.corrodinggames.rts.gameFramework.j.k F;
   OutputStream G;
   BufferedOutputStream H;
   DataOutputStream I;
   com.corrodinggames.rts.gameFramework.j.as J;
   bb K;
   Thread L;
   Object M = new Object();
   public boolean N = false;
   public boolean O;


   public static strictfp void a(String var0) {
      l.e("Replay: " + var0);
   }

   public static strictfp void b(String var0) {
      l.b("Replay: " + var0);
   }

   public static strictfp void a(String var0, Exception var1) {
      l.a("Replay: " + var0, (Throwable)var1);
   }

   public strictfp File a(String var1, boolean var2) {
      File var3 = com.corrodinggames.rts.gameFramework.e.a.a(var1, this.a, var2);
      return var3;
   }

   public strictfp void a(Context var1) {}

   public strictfp void a() {
      l var1 = l.B();
      if(var1.bt != 0.0F) {
         var1.bt = 0.0F;
      } else {
         var1.bt = 1.0F;
      }

   }

   public strictfp void b() {
      l var1 = l.B();
      if(var1.bt == 1.0F) {
         var1.bt = 2.0F;
      } else if(var1.bt == 2.0F) {
         var1.bt = 4.0F;
      } else if(var1.bt == 4.0F) {
         var1.bt = 8.0F;
      } else if(var1.bt == 8.0F) {
         var1.bt = 16.0F;
      } else if(var1.bt == 16.0F) {
         var1.bt = 32.0F;
      } else if(var1.bt == 32.0F) {
         var1.bt = 64.0F;
      } else if(var1.bt == 64.0F) {
         var1.bt = 1.0F;
      } else {
         var1.bt = 1.0F;
      }

   }

   public strictfp void a(int var1, String var2, String var3, int var4) {
      bb var5 = this.K;
      if(this.P && !this.u) {
         if(var3.startsWith("-t ")) {
            ;
         }

         bd var6 = new bd();
         var6.a = var4;
         var6.g = new bc();
         var6.g.a = var1;
         var6.g.b = var2;
         var6.g.c = var3;
         if(var5 == null) {
            l.g("Failed to record chat message, replay might have already stopped");
            return;
         }

         var5.a(var6);
      }

   }

   public strictfp void a(byte[] var1, int var2, int var3, int var4, float var5, float var6) {
      bb var7 = this.K;
      if(this.P && !this.u) {
         bd var8 = new bd();
         var8.a = var2;
         var8.f = var1;
         var8.h = var3;
         var8.i = var4;
         var8.j = var5;
         var8.k = var6;
         if(var7 == null) {
            l.g("Failed to save resync, replay might have already stopped");
            return;
         }

         var7.a(var8);
      }

   }

   public strictfp void c() {
      if(f) {
         this.d();
      }

   }

   public strictfp void a(e var1, int var2) {
      bb var3 = this.K;
      if(this.P && !this.u) {
         if(var3 == null) {
            l.g("Failed to record command, replay might have already stopped");
            return;
         }

         bd var4 = new bd();
         var4.e = var1.f();
         var4.a = var2;
         var3.a(var4);
         ++this.j;
         if(this.j > 5) {
            this.j = 0;
            l var6 = l.B();
            bd var5 = new bd();
            var5.c = Long.valueOf(this.f());
            var5.a = var6.bx;
            var3.a(var5);
         }
      }

   }

   public strictfp void d() {
      if(this.P && !this.u) {
         this.g.b();
         this.a(this.g, true);
      }

   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.ak var1) {
      this.a(var1, false);
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.ak var1, boolean var2) {
      if(this.P && !this.u) {
         l var3 = l.B();
         bd var4 = new bd();
         com.corrodinggames.rts.gameFramework.j.as var5 = new com.corrodinggames.rts.gameFramework.j.as();

         try {
            int var6 = 0;
            if(var2) {
               ++var6;
            }

            var5.c(var6);
            var5.a(var1.b.size());
            Iterator var7 = var1.b.iterator();

            while(var7.hasNext()) {
               com.corrodinggames.rts.gameFramework.j.al var8 = (com.corrodinggames.rts.gameFramework.j.al)var7.next();
               var5.a(var8.b);
            }
         } catch (IOException var9) {
            throw new RuntimeException(var9);
         }

         var4.d = var5.d();
         var4.a = var3.bx;
         this.K.a(var4);
      }

   }

   public strictfp void e() {
      Object var1 = this.M;
      synchronized(this.M) {
         try {
            if(this.K != null) {
               this.K.a();

               try {
                  this.L.join();
               } catch (InterruptedException var20) {
                  var20.printStackTrace();
               }

               this.P = false;
               this.K = null;
               this.L = null;
            }

            if(this.G != null) {
               this.I.flush();
               this.I.close();
               this.H.flush();
               this.H.close();
               this.G.flush();
               this.G.close();
            }
         } catch (IOException var21) {
            var21.printStackTrace();
         } finally {
            this.G = null;
            this.H = null;
            this.I = null;
            this.J = null;
         }

         this.s = false;
         this.P = false;
         this.u = false;
         this.t = null;
         this.i = 0;
         this.j = 0;
         this.k = false;
         this.l = 0;
         this.m = false;
         this.y = 0;
         this.v = 1;
         this.z = 0;
         this.A = 0;
         this.B = 0;
         this.o = -1;
         this.p = 0;
         this.q = -1;
         this.r = null;

         try {
            if(this.C != null) {
               this.E.close();
               this.D.close();
               this.C.close();
            }
         } catch (IOException var18) {
            var18.printStackTrace();
         } finally {
            this.C = null;
            this.D = null;
            this.E = null;
            this.F = null;
         }

      }
   }

   public strictfp long f() {
      long var1 = 0L;
      Iterator var3 = w.er.iterator();

      while(var3.hasNext()) {
         w var4 = (w)var3.next();
         if(var4 instanceof com.corrodinggames.rts.game.units.y) {
            com.corrodinggames.rts.game.units.y var5 = (com.corrodinggames.rts.game.units.y)var4;
            var1 = (long)((float)var1 + var5.eo * 1000.0F);
            var1 = (long)((float)var1 + var5.ep * 1000.0F);
            var1 = (long)((float)var1 + var5.cu * 1.0F);
            var1 += var5.eh;
         }
      }

      return var1;
   }

   public strictfp void g() {
      if(!this.N) {
         this.e();
      }

   }

   public strictfp boolean c(String var1) {
      File var2 = this.a(var1, false);
      return this.a(var1, var2);
   }

   private strictfp void l() {
      for(int var1 = 0; var1 < com.corrodinggames.rts.game.n.c; ++var1) {
         com.corrodinggames.rts.game.n var2 = com.corrodinggames.rts.game.n.k(var1);
         if(var2 != null && var2 instanceof com.corrodinggames.rts.game.a.a) {
            ((com.corrodinggames.rts.game.a.a)var2).aX = true;
         }
      }

   }

   public strictfp boolean a(String var1, File var2) {
      if(this.P) {
         if(this.u) {
            l.b("startReplayingFile: A replay is already playing");
         } else {
            l.b("startReplayingFile: A replay is already saving");
         }
      }

      this.e();
      l var3 = l.B();
      var3.e();
      var3.bX.q();
      this.w = null;
      this.s = false;
      this.P = true;
      this.u = true;
      this.t = var1;

      try {
         String var4;
         if(var2.isDirectory()) {
            l.e("File is a directory: " + var2.getAbsolutePath());
            var4 = "Cannot load replay: Target is a folder, instead of a file";
            l.e(var4);
            var3.a(var4, 1);
            return false;
         } else {
            this.C = com.corrodinggames.rts.gameFramework.e.a.a(var2);
            if(this.C == null) {
               var4 = "Cannot load replay: Failed to read replay file";
               l.e(var4);
               var3.a(var4, 1);
               return false;
            } else {
               this.D = new BufferedInputStream(this.C);
               this.E = new DataInputStream(this.D);
               this.F = new com.corrodinggames.rts.gameFramework.j.k(this.E);
               var4 = this.F.l();
               if(!var4.equals("rustedWarfareReplay")) {
                  l.e("Header is not correct:" + var4);
                  String var12 = "Cannot load replay: File is missing header (check if this file is a replay)";
                  l.e(var12);
                  var3.a(var12, 1);
                  return false;
               } else {
                  int var5 = this.F.f();
                  int var6 = this.F.f();
                  a("Loading save from version: " + var6);
                  this.F.a(var6);
                  String var7 = this.F.l();
                  if((var6 != 96 || var5 != var3.c(true)) && !this.n) {
                     String var8 = "Cannot load replay: This replay was recording with a different version: " + var7;
                     if(l.av()) {
                        var8 = var8 + " (You can use the beta tab in steam to switch to old versions)";
                     }

                     var3.a(var8, 1);
                     a("Replay version: " + var6 + " (" + var5 + ")");
                     a("GameSaver.thisSaveVersion: 96 (" + var3.c(true) + ")");
                     if(!l.aG) {
                        this.P = false;
                        return false;
                     }
                  }

                  this.q = var6;
                  this.r = var7;
                  this.F.e();
                  this.F.b("gamesave");
                  this.O = false;
                  this.N = true;
                  a("Loading replay initial save");
                  var3.ca.a(this.F, false, false, false);
                  this.N = false;
                  this.F.d("gamesave");
                  if(!this.O) {
                     a("ReplayEngine: --- No game setup read ----");
                     var3.bX.ay.i = true;
                     var3.bC = var3.bQ.teamUnitCapHostedGame;
                     var3.bB = var3.bC;
                  }

                  if(!this.h) {
                     this.l();
                  }

                  a("--- Reply settings ---");
                  a("Unit cap: " + var3.bC);
                  a(var3.bX.ay.b());
                  a("Starting frame:" + var3.bx);
                  if(!this.h) {
                     for(int var13 = 0; var13 < com.corrodinggames.rts.game.n.c; ++var13) {
                        com.corrodinggames.rts.game.n var9 = com.corrodinggames.rts.game.n.k(var13);
                        if(var9 != null && var9.v != null) {
                           String var10 = "Player \'" + var9.v + "\' playing as " + var9.N().toLowerCase() + " (team:" + var9.h() + ")";
                           var3.bS.h.a("", var10);
                        }
                     }
                  }

                  if(l.aw) {
                     com.corrodinggames.rts.gameFramework.j.ad.g("Warning: editor will desync checksums.");
                     var3.bv = true;
                     var3.bl = true;
                     var3.bn = true;
                  }

                  return true;
               }
            }
         }
      } catch (IOException var11) {
         throw new RuntimeException(var11);
      }
   }

   public strictfp void a(boolean var1) {
      if(l.aW) {
         if(!l.bd) {
            return;
         }
      } else if(!l.bc) {
         return;
      }

      l var2 = l.B();
      if(var2.bX.B && !var1 && !this.N && var2.bQ.saveMultiplayerReplays) {
         String var3 = var2.al() + " [v" + var2.v() + "] (" + f.a("d MMM yyyy HH.mm.ss") + ").replay";
         this.d(var3);
      }

   }

   public strictfp void d(String var1) {
      a("Recording replay to: " + var1);
      if(this.P) {
         if(this.u) {
            b("startSaving: A replay is already playing");
         } else {
            b("startSaving: A replay is already saving");
         }
      }

      this.e();
      l var2 = l.B();
      f = var2.bQ.replayTracing;
      if(f) {
         var2.bX.j("Warning traceChecksumsWriting is on. Large replay file size will be created.");
      }

      this.s = false;
      this.P = true;
      this.u = false;
      this.t = var1;

      try {
         File var3 = this.a(var1, true);
         this.G = com.corrodinggames.rts.gameFramework.e.a.a(var3, false);
         if(this.G == null) {
            b("Failed to create replay file at:" + var3.getAbsolutePath());
            l.B().i("Failed to create replay file (Replay recording will be disabled)");
            this.e();
            return;
         }

         this.H = new BufferedOutputStream(this.G);
         this.I = new DataOutputStream(this.H);
         this.J = new com.corrodinggames.rts.gameFramework.j.as(this.I);
         this.J.c("rustedWarfareReplay");
         int var4 = var2.c(true);
         this.J.a(var4);
         this.J.a((int)96);
         this.J.c(var2.v());
         this.J.a(var2.ar);
         this.J.e("gamesave");
         var2.ca.a(this.J);
         this.J.a("gamesave");
         this.I.flush();
         this.K = new bb(this);
         this.L = new Thread(this.K);
         this.L.start();
      } catch (IOException var5) {
         a("Failed to start recording replay", (Exception)var5);
         l.B().i("Failed to start recording replay: " + var5.getMessage());
         this.e();
      } catch (Exception var6) {
         a("Failed to start recording replay (Non IOException)", var6);
         l.B().i("Failed to start recording replay (Non IOException): " + var6.getMessage());
         this.e();
      }

   }

   public strictfp boolean h() {
      l var1 = l.B();
      String var2 = this.F.x();
      bd var3;
      if("rc".equals(var2)) {
         ++this.y;
         var3 = new bd();
         var3.a = this.F.f();
         e var4 = var1.cf.b();
         var4.a(this.F);
         var4.a = true;
         var3.e = var4;
         this.F.d("rc");
         this.w = var3;
         ++this.p;
         this.o = var3.a;
         if(c) {
            a("updateGameFrame: Command: " + var4.i.v + " (" + var4.i.k + ") count:" + var4.d() + " id:" + this.y);
            if(var4.j != null) {
               a("updateGameFrame: Waypoint: " + var4.j.d().name());
               if(var4.j.a() != null) {
                  a("updateGameFrame: Build Type: " + var4.j.a().i());
               }
            }

            if(com.corrodinggames.rts.game.units.a.s.c(var4.k)) {
               a("updateGameFrame: SpecialAction: " + var4.k.a());
            }

            if(var4.n != null) {
               a("updateGameFrame: SetAttackMode: " + var4.n);
            }

            if(var4.g) {
               a("updateGameFrame: stopOrUndo is set");
            }

            if(var4.r) {
               if(var4.s != 0.0F) {
                  a("updateGameFrame: changeStepRate:" + var4.s);
               }

               if(var4.u != 0) {
                  a("updateGameFrame: systemAction_action:" + var4.u);
               }
            }

            a("updateGameFrame: ------");
         }
      } else if("wait".equals(var2)) {
         var3 = new bd();
         var3.a = this.F.f();
         var3.b = true;
         this.w = var3;
         this.F.d("wait");
      } else {
         int var12;
         if("cs".equals(var2)) {
            var12 = this.F.f();
            long var13 = this.F.i();
            if(!this.n) {
               if(var1.bx != var12) {
                  l.b("replay:updateGameFrame", "expected:" + var12 + " got:" + var1.bx);
               }

               if(this.f() != var13) {
                  b("checksum: checksums don\'t match!!");
                  b("checksum: game frameNumber:" + var1.bx);
                  b("checksum: Replay checksum:" + var13);
                  b("checksum: Game checksum  :" + this.f());
                  ++this.l;
                  if(!this.k) {
                     this.k = true;
                     var1.bS.h.a("", "Error: This replay might be out of sync");
                  }
               } else {
                  a("checksum: checksums are matching frameNumber:" + var1.bx);
               }
            }

            this.F.d("cs");
         } else if("es".equals(var2)) {
            var12 = this.F.f();
            if(!this.n) {
               if(var1.bx != var12) {
                  l.b("replay.updateGameFrame: expected:" + var12 + " got:" + var1.bx);
               }

               com.corrodinggames.rts.gameFramework.j.k var14 = new com.corrodinggames.rts.gameFramework.j.k(this.F.t());
               byte var5 = var14.d();
               boolean var6 = false;
               if(com.corrodinggames.rts.game.units.custom.d.b.a(var5, 1)) {
                  var6 = true;
               }

               if(var6) {
                  l.e("replay: -trace checksum-");
               } else {
                  l.e("replay: -long checksum-");
               }

               var1.bX.d();
               int var7 = var14.f();
               Iterator var8 = var1.bX.am.b.iterator();

               while(var8.hasNext()) {
                  com.corrodinggames.rts.gameFramework.j.al var9 = (com.corrodinggames.rts.gameFramework.j.al)var8.next();
                  long var10 = var14.i();
                  if(!this.m && var10 == var9.b) {
                     a("extraChecksum: " + var9.a + " Checksum [" + var12 + "]. " + var10 + " == " + var9.b + " (ok)");
                  }

                  if(var10 != var9.b) {
                     if(this.l < 150) {
                        b("extraChecksum: " + var9.a + " Checksum [" + var12 + "]. " + var10 + " != " + var9.b + " (failed)");
                     }

                     ++this.l;
                  }
               }
            }

            this.m = true;
            this.F.d("es");
         } else if("resync".equals(var2)) {
            var12 = this.F.f();
            l.e("Loading resync from replay");
            if(var1.bx != var12) {
               l.b("replay:resync", "expected:" + var12 + " got:" + var1.bx);
            }

            int var15 = this.F.f();
            int var17 = this.F.f();
            float var18 = this.F.g();
            float var19 = this.F.g();
            com.corrodinggames.rts.gameFramework.j.k var20 = new com.corrodinggames.rts.gameFramework.j.k(this.F.t());
            var1.ca.a(var20, true, true, true);
            this.l();
            var1.bx = var15;
            var1.by = var17;
            var1.bX.am.a = 0L;
            if((double)var18 < 0.1D) {
               com.corrodinggames.rts.gameFramework.j.ad.a("replay setCurrentStepRate:" + var18 + " is too small", true);
            }

            var1.bX.a(var18, "replay");
            var1.bX.J = var19;
            this.F.d("resync");
         } else if("chat".equals(var2)) {
            var3 = new bd();
            var3.a = this.F.f();
            var3.g = new bc();
            var3.g.a = this.F.f();
            var3.g.b = this.F.j();
            var3.g.c = this.F.j();
            this.w = var3;
            this.F.d("chat");
         } else {
            if("end".equals(var2)) {
               l.b("replay:updateGameFrame", "end of replay block found");
               var1.bS.h.a("", "Replay has ended");
               if(!var1.bv) {
                  this.s = true;
                  var1.bt = 0.25F;
                  l.B().bS.G();
               } else {
                  this.s = false;
                  this.P = false;
                  this.u = false;
                  com.corrodinggames.rts.game.units.h var16 = var1.bS.i();
                  if(var16 != null) {
                     var1.bs = var16.bX;
                  }
               }

               this.F.d("end");
               l.e("number of replay commands issued:" + this.z);
               return false;
            }

            if("endReplayMetaData".equals(var2)) {
               this.F.d("endReplayMetaData");
            } else {
               l.b("updateGameFrame", "Unknown command block:" + var2);
               this.F.d(var2);
            }
         }
      }

      return true;
   }

   public strictfp void a(float var1) {
      l var2 = l.B();
      if(!this.s) {
         if(this.P && this.u) {
            while(true) {
               if(this.w == null) {
                  try {
                     boolean var3 = this.h();
                     if(!var3) {
                        return;
                     }
                  } catch (IOException var5) {
                     l.b("updateGameFrame", "IOException, read of replay?");
                     var5.printStackTrace();
                     var2.bt = 0.25F;
                     if(!this.s && this.P) {
                        var2.bS.h.a("", "Replay ended (unexpected)");
                     }

                     this.s = true;
                     break;
                  }
               }

               if(this.w != null) {
                  if(this.n) {
                     this.w = null;
                     continue;
                  }

                  if(b && this.w != null && this.x != this.w) {
                     this.x = this.w;
                     l.e("replay: upcoming in " + (this.w.a - var2.bx) + " command:" + (this.w.e != null));
                  }

                  if(this.w.b && this.z == 0) {
                     l.e("updateGameFrame: replay: Skipping wait on first resync without commands to avoid delay");
                     this.w = null;
                     continue;
                  }

                  if(var2.bx >= this.w.a) {
                     if(this.w.e != null) {
                        if(var2.bx > this.w.a) {
                           l.b("updateGameFrame: replay incorrect frameNumber, skipping command:" + var2.bx + " vs " + this.w.a);
                        } else {
                           com.corrodinggames.rts.game.n var6;
                           if(d) {
                              var6 = this.w.e.p;
                              if(var6 == null) {
                                 l.e("Precommand Team: commandingPlayer==null");
                                 if(this.w.e.i != null) {
                                    l.e("Precommand Team id:" + this.w.e.i.k + " credits:" + this.w.e.i.o);
                                 }
                              } else {
                                 l.e("Precommand Team id:" + this.w.e.p.k + " credits:" + this.w.e.p.o + " count:" + this.w.e.p.w() + " max:" + this.w.e.p.x());
                              }
                           }

                           if(this.w.e.r && this.w.e.u != 0) {
                              l.b("replay:issueCommand", "systemAction_action:" + this.w.e.u);
                           }

                           this.w.e.k();
                           if(d) {
                              var6 = this.w.e.p;
                              if(var6 != null) {
                                 l.e("Postcommand credits:" + this.w.e.p.o + " count:" + this.w.e.p.w() + " max:" + this.w.e.p.x());
                              } else if(this.w.e.i != null) {
                                 l.e("Postcommand Team id:" + this.w.e.i.k + " credits:" + this.w.e.i.o);
                              }
                           }

                           ++this.z;
                        }
                     } else if(this.w.g != null) {
                        bc var7 = this.w.g;
                        boolean var4 = false;
                        if(var7.c == null) {
                           var4 = true;
                        } else {
                           if(var7.c.startsWith("-i ")) {
                              var4 = true;
                           }

                           if(var7.c.equals("<All players ready>")) {
                              var4 = true;
                           }

                           if(var7.c.equals("--too many desync errors, suppressing output--")) {
                              var4 = true;
                           }

                           if(var7.c.startsWith("desync:")) {
                              var4 = true;
                           }
                        }

                        if(!var2.bQ.replaysShowRecordedChat) {
                           var4 = true;
                        }

                        if(var4) {
                           l.b("replay:updateGameFrame", "Skipping message: " + var7.b + ":" + var7.c);
                        } else {
                           l.b("replay:updateGameFrame", "message: " + var7.b + ":" + var7.c);
                           var2.bS.h.a(var7.b, var7.c);
                        }
                     } else if(this.w.b) {
                        if(c) {
                           ;
                        }
                     } else {
                        l.b("updateGameFrame", "error: lastReadCommand null action");
                     }

                     this.w = null;
                     continue;
                  }
               }

               if(this.w != null) {
                  break;
               }
            }
         }

      }
   }

   public strictfp void e(String var1) {
      l.e("ReplayEngine deleteGame: " + var1);
      String var2 = com.corrodinggames.rts.gameFramework.e.a.o(var1);
      if(!var2.contains("\\") && !var2.contains("/")) {
         File var3 = this.a(var1, true);
         l.e("ReplayEngine path: " + var3.getAbsolutePath());
         if(!var3.exists()) {
            l.e("ReplayEngine deleteGame: file doesn\'t exist");
         }

         boolean var4 = com.corrodinggames.rts.gameFramework.e.a.b(var3);
         if(!var4) {
            l.e("ReplayEngine deleteGame: failed to delete: " + var3.getAbsolutePath());
         }

         File var5 = this.a(var1 + ".map", true);
         if(var5.exists()) {
            com.corrodinggames.rts.gameFramework.e.a.b(var5);
         }

      } else {
         l.e("Cannot get replay with path: " + var1);
      }
   }

   public strictfp boolean i() {
      return this.P;
   }

   public strictfp boolean j() {
      return this.P && this.u;
   }

   public strictfp boolean k() {
      return this.P && !this.u;
   }

   // $FF: synthetic method
   static boolean a(ba var0) {
      return var0.P;
   }

   // $FF: synthetic method
   static boolean a(ba var0, boolean var1) {
      return var0.P = var1;
   }

}
