package com.corrodinggames.rts.gameFramework;

import android.graphics.PointF;
import com.corrodinggames.rts.gameFramework.ProjectileManager;
import com.corrodinggames.rts.gameFramework.CommandController;
import com.corrodinggames.rts.gameFramework.CommandPathPart;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.GameObject;
import java.io.IOException;
import java.util.AbstractList;
import java.util.Iterator;

public class Command {  // 02 原稿: gameFramework.e (v19.112d 重建, 02b 直译)

   public boolean a;
   public String b;
   public int c;
   public int d;
   public boolean e;
   public boolean f;
   public boolean g;
   public boolean h;
   public com.corrodinggames.rts.game.PlayerState i;
   public com.corrodinggames.rts.game.units.WeaponAction j;
   public com.corrodinggames.rts.game.units.actions.ActionId k;
   public PointF l;
   public com.corrodinggames.rts.game.units.UnitInstance m;
   public com.corrodinggames.rts.game.units.UnitFlag n;
   private PointF z;
   public boolean o;
   public com.corrodinggames.rts.game.PlayerState p;
   public short q;
   public boolean r;
   public float s;
   public float t;
   public int u;
   private com.corrodinggames.rts.gameFramework.utility.CustomArrayList A;
   com.corrodinggames.rts.gameFramework.utility.CustomArrayList v;
   com.corrodinggames.rts.gameFramework.utility.CustomArrayList w;
   public boolean x;
   // $FF: synthetic field
   final CommandController y;


   public Command(CommandController var1) {
      this.y = var1;
      this.e = false;
      this.f = false;
      this.g = false;
      this.h = false;
      this.k = com.corrodinggames.rts.game.units.actions.GameAction.i;
      this.o = false;
      this.A = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
      this.v = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
      this.w = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
      this.x = false;
   }

   public strictfp boolean a() {
      Iterator var1 = this.w.iterator();

      CommandPathPart var2;
      do {
         if(!var1.hasNext()) {
            return true;
         }

         var2 = (CommandPathPart)var1.next();
      } while(var2.a.a() != null);

      return false;
   }

   public strictfp void b() {
      GlobalState var1 = GlobalState.B();
      this.x = true;
      ProjectileManager var2 = var1.bV.c();
      Iterator var3 = this.v.iterator();

      while(var3.hasNext()) {
         com.corrodinggames.rts.game.units.UnitType var4 = (com.corrodinggames.rts.game.units.UnitType)var3.next();
         var2.a.add(var4);
      }

      if(this.j != null) {
         float var15 = this.j.g();
         float var16 = this.j.h();
         if(this.j.d() != com.corrodinggames.rts.game.units.WeaponTypeEnum.a && this.j.d() != com.corrodinggames.rts.game.units.WeaponTypeEnum.h && this.j.d() != com.corrodinggames.rts.game.units.WeaponTypeEnum.b) {
            return;
         }

         com.corrodinggames.rts.gameFramework.utility.CustomArrayList var5 = var2.a(var15, var16, this.j.j);
         Iterator var6 = var5.iterator();

         while(var6.hasNext()) {
            com.corrodinggames.rts.game.units.UnitType var7 = (com.corrodinggames.rts.game.units.UnitType)var6.next();
            if(!var7.isOnWalkableTile() && var7.I() && (!this.e || var7.ar() == null)) {
               int var10 = 0;
               if(this.j.d() == com.corrodinggames.rts.game.units.WeaponTypeEnum.b) {
                  var10 = var7.q(this.j.i());
               }

               boolean var11 = true;
               CommandPathPart var12 = new CommandPathPart();
               var12.b = var7.eh;
               var12.c = var7.eo;
               var12.d = var7.ep;
               var12.e = var15;
               var12.f = var16;
               var12.g = var1.bx;
               var12.h = var7.h();
               boolean var13 = false;
               boolean var14 = false;
               var12.a = var7.a(var15, var16, var10, var11, var13, var14);
               var12.a.t = 120.0F;
               var12.a.s = var12.a.t;
               var12.a.u = true;
               this.w.add(var12);
            }
         }
      }

   }

   public strictfp com.corrodinggames.rts.game.PlayerState c() {
      return this.i;
   }

   public strictfp int d() {
      return this.A.size() + this.v.size();
   }

   public strictfp boolean e() {
      return com.corrodinggames.rts.game.units.actions.GameAction.getResourceCost(this.k)?false:this.d() == 0;
   }

   /* 02b e.java L137: 无 throws; 方法体已有 try-catch(IOException) 内部消化 → 去冗余 throws */
   public synchronized strictfp Command f() {
      try {
         com.corrodinggames.rts.gameFramework.network.OutputNetStream var1 = new com.corrodinggames.rts.gameFramework.network.OutputNetStream();
         this.a(var1);
         com.corrodinggames.rts.gameFramework.network.InputNetStream var2 = new com.corrodinggames.rts.gameFramework.network.InputNetStream(var1.d());
         Command var3 = new Command(this.y);
         var3.c = this.c;
         var3.a(var2);
         return var3;
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public strictfp void g() {
      if(this.j != null) {
         Iterator var1 = this.v.iterator();

         while(var1.hasNext()) {
            com.corrodinggames.rts.game.units.UnitType var2 = (com.corrodinggames.rts.game.units.UnitType)var1.next();
            this.A.add(Long.valueOf(var2.eh));
         }

         this.v.clear();
         this.j.k();
      }

   }

   /* 02b e.java L166: var1.e 抛 IOException (R8 移除 throws) */
   public synchronized strictfp void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream var1) throws IOException {
      var1.e("c");
      var1.c(this.i.k);
      var1.a(this.j != null);
      if(this.j != null) {
         this.j.a(var1);
      }

      var1.a(this.e);
      var1.a(this.g);
      var1.a((int)-1);
      var1.a((Enum)this.n);
      var1.a(this.z != null);
      if(this.z != null) {
         var1.a(this.z.a);
         var1.a(this.z.b);
      }

      var1.a(this.o);
      var1.a(this.v.size() + this.A.size());
      Iterator var2 = this.v.iterator();

      while(var2.hasNext()) {
         com.corrodinggames.rts.game.units.UnitType var3 = (com.corrodinggames.rts.game.units.UnitType)var2.next();
         var1.a(var3.eh);
      }

      var2 = this.A.iterator();

      while(var2.hasNext()) {
         long var6 = ((Long)var2.next()).longValue();
         var1.a(var6);
      }

      var1.a(this.p != null);
      if(this.p != null) {
         var1.a(this.p);
      }

      var1.a(this.l != null);
      if(this.l != null) {
         var1.a(this.l.a);
         var1.a(this.l.b);
      }

      var1.a(this.m);
      var1.c(this.k.a());
      var1.a(this.f);
      var1.a(this.q);
      var1.a(this.r);
      if(this.r) {
         var1.c(0);
         var1.a(this.s);
         var1.a(this.t);
         var1.a(this.u);
      }

      var1.a(this.w.size());

      for(int i = 0; i < this.w.size(); ++i) {
         CommandPathPart var7 = (CommandPathPart)this.w.get(i);
         var7.a(var1);
      }

      var1.a(this.h);
      var1.a("c");
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.network.InputNetStream var1) throws IOException {
      var1.b("c");
      this.i = com.corrodinggames.rts.game.PlayerState.k(var1.d());
      if(this.i == null) {
         throw new IOException("team==null");
      } else {
         boolean var2 = var1.e();
         if(var2) {
            this.j = new com.corrodinggames.rts.game.units.WeaponAction();
            this.j.a(var1);
         }

         this.e = var1.e();
         this.g = var1.e();
         this.k = com.corrodinggames.rts.game.units.actions.ActionId.a(String.valueOf(var1.f()));
         this.n = (com.corrodinggames.rts.game.units.UnitFlag)var1.b(com.corrodinggames.rts.game.units.UnitFlag.class);
         boolean var3 = var1.e();
         if(var3) {
            this.z = new PointF();
            this.z.a = var1.readFloat();
            this.z.b = var1.readFloat();
         }

         this.o = var1.e();
         int var4 = var1.f();

         int var5;
         for(var5 = 0; var5 < var4; ++var5) {
            this.A.add(Long.valueOf(var1.n()));
         }

         if(var1.b() >= 16) {
            this.p = null;
            if(var1.e()) {
               this.p = var1.s();
            }
         }

         if(var1.b() >= 29) {
            boolean var8 = var1.e();
            if(var8) {
               this.l = new PointF();
               this.l.a = var1.readFloat();
               this.l.b = var1.readFloat();
            }

            this.m = var1.o();
         }

         if(var1.b() >= 33) {
            this.k = com.corrodinggames.rts.game.units.actions.ActionId.a(var1.readString());
         }

         if(var1.b() >= 37) {
            this.f = var1.e();
         }

         if(var1.b() >= 52) {
            this.q = var1.v();
         }

         if(var1.b() >= 53) {
            this.r = var1.e();
            if(this.r) {
               var1.d();
               this.s = var1.readFloat();
               this.t = var1.readFloat();
               this.u = var1.f();
            }

            var5 = var1.f();
            this.w.clear();

            for(int i = 0; i < var5; ++i) {
               CommandPathPart var7 = new CommandPathPart();
               var7.a(var1);
               this.w.add(var7);
            }
         }

         if(var1.b() >= 80) {
            this.h = var1.e();
         }

         var1.d("c");
      }
   }

   public strictfp void a(AbstractList var1) {
      Iterator var2 = var1.iterator();

      while(var2.hasNext()) {
         com.corrodinggames.rts.game.units.UnitType var3 = (com.corrodinggames.rts.game.units.UnitType)var2.next();
         this.a(var3);
      }

   }

   public strictfp void a(com.corrodinggames.rts.game.units.UnitType var1) {
      if(var1 == null) {
         throw new RuntimeException("unit cannot be null");
      } else {
         if(var1.player != this.i) {
            ;
         }

         if(this.i.w) {
            if(var1.player != this.i && GlobalState.B().bs != this.i) {
               GlobalState.b("CommandController", "Warning AI: " + this.i.k + " gave an order to unit with team:" + var1.player.k + " type:" + var1.r().i());
               GlobalState.g("");
            }

            if(var1.cW()) {
               GlobalState.b("CommandController", "Warning AI: " + this.i.k + " gave an order to unit with canNotBeGivenOrdersByPlayer: " + var1.r().i());
            }
         }

         this.v.add(var1);
      }
   }

   public strictfp void h() {
      this.o = true;
   }

   public strictfp void a(float var1, float var2) {
      this.j = new com.corrodinggames.rts.game.units.WeaponAction();
      this.j.a(var1, var2);
   }

   public strictfp void b(float var1, float var2) {
      this.j = new com.corrodinggames.rts.game.units.WeaponAction();
      this.j.b(var1, var2);
   }

   public strictfp void a(com.corrodinggames.rts.game.units.UnitInstance var1) {
      this.j = new com.corrodinggames.rts.game.units.WeaponAction();
      this.j.a(var1);
   }

   public strictfp void a(float var1, float var2, boolean var3) {
      this.j = new com.corrodinggames.rts.game.units.WeaponAction();
      this.j.b(var1, var2);
      this.j.j = var3;
   }

   public strictfp void a(com.corrodinggames.rts.game.units.UnitInstance var1, boolean var2) {
      this.j = new com.corrodinggames.rts.game.units.WeaponAction();
      this.j.a(var1);
      this.j.j = var2;
   }

   public strictfp void a(float var1, float var2, com.corrodinggames.rts.game.units.UnitTypeHandle var3, int var4) {
      this.j = new com.corrodinggames.rts.game.units.WeaponAction();
      this.j.a(var1, var2, var3, var4);
   }

   public strictfp void b(com.corrodinggames.rts.game.units.UnitInstance var1) {
      this.j = new com.corrodinggames.rts.game.units.WeaponAction();
      this.j.b(var1);
   }

   public strictfp void c(com.corrodinggames.rts.game.units.UnitInstance var1) {
      this.j = new com.corrodinggames.rts.game.units.WeaponAction();
      this.j.c(var1);
   }

   public strictfp void c(float var1, float var2) {
      this.j = new com.corrodinggames.rts.game.units.WeaponAction();
      this.j.c(var1, var2);
   }

   public strictfp void d(com.corrodinggames.rts.game.units.UnitInstance var1) {
      this.j = new com.corrodinggames.rts.game.units.WeaponAction();
      this.j.f(var1);
   }

   public strictfp void e(com.corrodinggames.rts.game.units.UnitInstance var1) {
      this.j = new com.corrodinggames.rts.game.units.WeaponAction();
      this.j.g(var1);
   }

   public strictfp void f(com.corrodinggames.rts.game.units.UnitInstance var1) {
      this.j = new com.corrodinggames.rts.game.units.WeaponAction();
      this.j.h(var1);
   }

   public strictfp void a(com.corrodinggames.rts.game.units.actions.ActionId var1) {
      this.k = var1;
   }

   public strictfp void a(com.corrodinggames.rts.game.units.actions.ActionId var1, PointF var2, com.corrodinggames.rts.game.units.UnitInstance var3) {
      this.k = var1;
      this.l = var2;
      this.m = var3;
   }

   public strictfp void a(com.corrodinggames.rts.game.units.UnitFlag var1) {
      this.n = var1;
   }

   public strictfp void a(PointF var1) {
      this.z = var1;
   }

   public synchronized strictfp void i() {
      Iterator var1 = this.A.iterator();

      while(var1.hasNext()) {
         Long var2 = (Long)var1.next();
         com.corrodinggames.rts.game.units.UnitType var3 = com.corrodinggames.rts.gameFramework.GameObject.b(var2.longValue(), true);  // 02b w.b(long,boolean); EffectConfig 幻觉名修正
         if(var3 != null) {
            this.v.add(var3);
         }
      }

      this.A.clear();
      var1 = this.v.iterator();

      while(var1.hasNext()) {
         com.corrodinggames.rts.game.units.UnitType var4 = (com.corrodinggames.rts.game.units.UnitType)var1.next();
         if(var4.isDead) {
            var1.remove();
         }
      }

   }

   public strictfp void j() {
      if(com.corrodinggames.rts.game.units.actions.GameAction.getResourceCost(this.k)) {
         Iterator var1 = this.v.iterator();

         while(var1.hasNext()) {
            com.corrodinggames.rts.game.units.UnitType var2 = (com.corrodinggames.rts.game.units.UnitType)var1.next();
            var2.b(var2.a(this.k), this.g);
         }
      }

   }

   public strictfp void k() {
      GlobalState var1 = GlobalState.B();
      if(!var1.cb.j() || this.a) {
         this.i();
         com.corrodinggames.rts.game.units.UnitType var6;
         Iterator var8;
         boolean var13;
         com.corrodinggames.rts.game.units.UnitType var16;
         if(this.r) {
            if(this.s != 0.0F) {
               GlobalState.e("issueCommand: changeStepRate:" + this.s);
               var1.bX.registerRelayServer(this.s, "command");  // 02b: bX.a(float,String)
            } else if(this.u != 0) {
               GlobalState.e("system action:" + this.u);
               com.corrodinggames.rts.game.units.DebugDesyncDetector var20;
               if(this.u == 1) {
                  GlobalState.e("new DebugDesyncDetector");
                  var20 = new com.corrodinggames.rts.game.units.DebugDesyncDetector(false);
                  var20.b(com.corrodinggames.rts.game.PlayerState.i);
               } else if(this.u == 2) {
                  GlobalState.e("new DebugDesyncDetector (stress test)");
                  var20 = new com.corrodinggames.rts.game.units.DebugDesyncDetector(false);
                  var20.b(com.corrodinggames.rts.game.PlayerState.i);
                  var20.a = true;
               } else if(this.u == 100) {
                  GlobalState.e("team surrender");
                  if(this.i == null) {
                     GlobalState.e("team not found");
                  } else {
                     if(var1.bX.C) {
                        var1.bX.j("\'" + this.i.v + "\' has surrendered");
                     }

                     this.i.E = true;
                     var8 = com.corrodinggames.rts.game.units.UnitInstance.bE.iterator();

                     while(var8.hasNext()) {
                        com.corrodinggames.rts.game.units.UnitInstance var22 = (com.corrodinggames.rts.game.units.UnitInstance)var8.next();
                        if(var22.player == this.i && var22 instanceof com.corrodinggames.rts.game.units.UnitType) {
                           var16 = (com.corrodinggames.rts.game.units.UnitType)var22;
                           var16.c(false);
                        }
                     }

                  }
               } else if(this.u == 200) {
                  GlobalState.e("queue quick resync");
                  var1.bX.N = true;
               } else if(this.u == 5) {
                  GlobalState.e("system command spawn");
                  if(this.j != null && this.j.d() == com.corrodinggames.rts.game.units.WeaponTypeEnum.c && this.j.a() != null) {
                     int var18 = this.j.b();
                     com.corrodinggames.rts.game.units.UnitTypeHandle var19 = this.j.a();
                     var13 = false;
                     if(this.i != null && this.i == var1.bs && var1.bs.a(false, false) == 0) {
                        var13 = true;
                     }

                     com.corrodinggames.rts.game.units.UnitInstance var24 = var19.a();
                     var24.eo = this.j.g();
                     var24.ep = this.j.h();
                     if(this.i != null) {
                        var24.f(this.i);
                     } else {
                        var24.f(com.corrodinggames.rts.game.PlayerState.i);
                     }

                     var24.B((com.corrodinggames.rts.game.units.UnitInstance)null);
                     if(var18 != 1 && var24 instanceof com.corrodinggames.rts.game.units.UnitType) {
                        ((com.corrodinggames.rts.game.units.UnitType)var24).a(var18);
                     }

                     var24.cP();
                     if(var24 instanceof com.corrodinggames.rts.game.units.UnitType) {
                        var6 = (com.corrodinggames.rts.game.units.UnitType)var24;
                        var6.br();
                        if(var24.bI()) {
                           var1.bU.a(var6);
                        }
                     }

                     com.corrodinggames.rts.game.PlayerState.c(var24);
                     if(var1.bs == var24.player && var24.player != com.corrodinggames.rts.game.PlayerState.i && !var24.u() && var13) {
                        var1.b(var24.eo, var24.ep);
                        var1.bS.j(var24);
                     }

                  } else {
                     GlobalState.e("system command spawn - failed");
                  }
               } else {
                  GlobalState.e("issueCommand: unknown system action:" + this.u);
               }
            } else {
               GlobalState.e("issueCommand: Null System action");
            }
         } else {
            if(this.p != null) {
               this.p.Y = System.currentTimeMillis();
               this.p.Z = var1.by;
            }

            com.corrodinggames.rts.game.units.UnitType var3;
            String var15;
            if(this.p != null) {
               String var2 = null;
               var3 = null;
               Iterator var4 = this.v.iterator();

               while(var4.hasNext()) {
                  com.corrodinggames.rts.game.units.UnitType var5 = (com.corrodinggames.rts.game.units.UnitType)var4.next();
                  if(var5.player != this.p && !this.a(this.p, var5.player)) {
                     if(var2 == null) {
                        var2 = "";
                     } else {
                        var2 = var2 + ", ";
                     }

                     if(var3 == null) {
                        var3 = var5;
                     }

                     var2 = var2 + var5.eh;
                     var4.remove();
                  } else if(var5.cW()) {
                     CommandController.a("Warning unit: " + var5.eh + " has canNotBeGivenOrdersByPlayer set");
                     var4.remove();
                  }
               }

               if(var2 != null) {
                  com.corrodinggames.rts.gameFramework.network.NetEngine.registerRelayServer("Player(" + this.p.k + ") " + this.p.v + " cannot control units: " + var2, true);
                  if(var3 != null) {
                     var15 = "";
                     if(var3.player != null) {
                        var15 = var15 + " targetUnitTeamId: " + var3.player.k + " targetUnitTeamName: " + var3.player.v;
                     }

                     CommandController.a(var15);
                  }
               }
            }

            if(this.o) {
               for(var8 = this.v.iterator(); var8.hasNext(); var3.R = null) {
                  var3 = (com.corrodinggames.rts.game.units.UnitType)var8.next();
                  var3.az();
               }
            }

            if(this.j != null) {
               this.j.c();
               ProjectileManager var14 = var1.bV.b();
               var14.g = this.w;

               for(int i = 0; i <= 1; ++i) {
                  var13 = i == 1;
                  Iterator it = this.v.iterator();

                  while(it.hasNext()) {
                     var6 = (com.corrodinggames.rts.game.units.UnitType)it.next();
                     if(var6.ae == var13) {
                        if(this.f) {
                           var6.aA();
                        } else if(!this.e) {
                           var6.az();
                        } else if(this.h && this.j != null) {
                           com.corrodinggames.rts.game.units.WeaponAction var7 = var6.at();
                           if(var7 != null && this.j.a(var7) && (var7.d() == com.corrodinggames.rts.game.units.WeaponTypeEnum.h || var7.d() == com.corrodinggames.rts.game.units.WeaponTypeEnum.a) && (this.j.d() == com.corrodinggames.rts.game.units.WeaponTypeEnum.h || this.j.d() == com.corrodinggames.rts.game.units.WeaponTypeEnum.a)) {
                              var6.removeFirstWaypoint();
                           }
                        }
                     }
                  }
               }

               Iterator var17 = this.v.iterator();

               while(var17.hasNext()) {
                  var16 = (com.corrodinggames.rts.game.units.UnitType)var17.next();
                  if(!var16.a(this.j, CommandController.e < 5)) {
                     var15 = "";
                     if(this.p != null) {
                        var15 = "Player(" + this.p.k + ") " + this.p.v + ": ";
                     }

                     CommandController.a(var15 + "isValidNewWaypoint==false on: " + var16.c());
                  } else {
                     com.corrodinggames.rts.game.units.WeaponAction var23 = var16.d(this.j);
                     var14.a(var16, var23);
                     var16.a(var23);
                  }
               }

               var14.b();
            } else {
               if(com.corrodinggames.rts.game.units.actions.GameAction.getResourceCost(this.k)) {
                  var8 = this.v.iterator();

                  while(var8.hasNext()) {
                     var3 = (com.corrodinggames.rts.game.units.UnitType)var8.next();
                     com.corrodinggames.rts.game.units.actions.GameAction var10 = var3.a(this.k);
                     if(var10 == null) {
                        CommandController.a("Could not find specialAction:" + this.k.a() + " on " + var3.r().i());
                     } else if(!var10.getLabel((com.corrodinggames.rts.game.units.UnitInstance)var3)) {
                        CommandController.a("!isAvailable specialAction:" + this.k.a() + " on " + var3.r().i() + " (action being skipped)");
                        if(CommandController.a) {
                           CommandController.a("Command source:" + this.b);
                        }
                     } else {
                        var3.a(var10);
                        com.corrodinggames.rts.gameFramework.ActionDebugRecorder.a(var3, var10);
                        var3.a(var10, this.g, this.l, this.m);
                     }
                  }

                  com.corrodinggames.rts.game.units.actions.MapPingAction var9 = com.corrodinggames.rts.game.units.actions.MapPingAction.a(this.k);
                  if(var9 != null) {
                     if(var1.bs != null && this.i != null) {
                        if(this.i.d(var1.bs)) {
                           var1.bS.a(this.l.a, this.l.b, this.i, var9);
                        }
                     } else {
                        CommandController.a("PingMapAction failed: game.playerTeam==null or this.team==null");
                     }
                  }
               }

               if(this.n != null) {
                  for(var8 = this.v.iterator(); var8.hasNext(); var3.P = this.n) {
                     var3 = (com.corrodinggames.rts.game.units.UnitType)var8.next();
                  }
               }

               if(this.z != null) {
                  var8 = this.v.iterator();

                  while(var8.hasNext()) {
                     var3 = (com.corrodinggames.rts.game.units.UnitType)var8.next();
                     if(var3 instanceof com.corrodinggames.rts.game.units.commands.CarrierUnit) {
                        com.corrodinggames.rts.game.units.commands.CarrierUnit var11 = (com.corrodinggames.rts.game.units.commands.CarrierUnit)var3;
                        var11.a(this.z);
                     }
                  }
               }

            }
         }
      }
   }

   public strictfp boolean a(com.corrodinggames.rts.game.PlayerState var1, com.corrodinggames.rts.game.PlayerState var2) {
      return var1 != null && var2 != null?(!var2.d(var1)?false:(this.q & 1 << var2.k) != 0):false;
   }

   public strictfp boolean l() {
      this.q = 0;

      for(int i = 0; i < com.corrodinggames.rts.game.PlayerState.c; ++i) {
         com.corrodinggames.rts.game.PlayerState var2 = com.corrodinggames.rts.game.PlayerState.k(i);
         if(var2 != null && var2.p()) {
            this.q = (short)(this.q | 1 << i);
         }
      }

      GlobalState var4 = GlobalState.B();
      if(var4.c(true) < 127 && this.j != null && this.j.d() == com.corrodinggames.rts.game.units.WeaponTypeEnum.c) {
         com.corrodinggames.rts.game.units.UnitTypeHandle var5 = this.j.a();
         if(var5 != null) {
            com.corrodinggames.rts.game.units.UnitInstance var3 = com.corrodinggames.rts.game.units.UnitInstance.a(var5);
            if(var3 != null && !(var3 instanceof com.corrodinggames.rts.game.units.UnitType)) {
               GlobalState.e("Rejecting non OrderableUnit build order: " + var5.i());
               return false;
            }
         }
      }

      if(this.j != null && this.j.n) {
         GlobalState.e("Rejecting waypoint with addedByAction true");
         return false;
      } else {
         return true;
      }
   }
}
