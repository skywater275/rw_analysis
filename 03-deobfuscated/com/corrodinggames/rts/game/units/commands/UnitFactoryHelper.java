package com.corrodinggames.rts.game.units.commands;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.game.units.commands.ExperimentalWaterUnit;
import com.corrodinggames.rts.game.units.commands.BuilderUnit;
import com.corrodinggames.rts.game.units.commands.CarrierUnit;
import com.corrodinggames.rts.gameFramework.BaseGameObject;
import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.io.IOException;

public strictfp class UnitFactoryHelper {  // 02 原稿: units.d.k (v19.112 重建, 02b 直译)

   com.corrodinggames.rts.game.units.UnitType factoryReference;
   public PointF b = null;
   public final com.corrodinggames.rts.gameFramework.utility.CustomArrayList c = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
   final com.corrodinggames.rts.gameFramework.utility.CustomArrayList d = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
   public float e;
   com.corrodinggames.rts.game.units.commands.BuilderUnit f;


   public UnitFactoryHelper(com.corrodinggames.rts.game.units.UnitType var1) {
      this.factoryReference = var1;
   }

   /* 02b d/k.java L31: var3.serializeToStream 抛 IOException */
   public strictfp void a(com.corrodinggames.rts.gameFramework.network.OutputNetStream var1) throws IOException {  // 02: a(j.as) 流
      var1.a(this.e);
      var1.a(this.c.size());
      Iterator var2 = this.c.iterator();

      while(var2.hasNext()) {
         BaseGameObject var3 = (BaseGameObject)var2.next();
         var3.serializeToStream(var1);  // 02: bq.a(as) 抽象
      }

      var1.a(this.b != null);
      if(this.b != null) {
         var1.a(this.b.a);
         var1.a(this.b.b);
      }

   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.network.InputNetStream var1) {
      this.e = var1.readFloat();
      int var2 = var1.readInt();
      this.c.clear();

      for(int i = 0; i < var2; ++i) {
         com.corrodinggames.rts.game.units.commands.BuilderUnit var4 = new BuilderUnit();  // 02b j = BuilderUnit
         var4.a(var1);
         if(com.corrodinggames.rts.game.units.actions.GameAction.c(var4.j)) {
            com.corrodinggames.rts.game.units.actions.GameAction var5 = this.factoryReference.a(var4.j);
            if(var5 == null) {
               com.corrodinggames.rts.gameFramework.GlobalState.b("Factory", this.factoryReference.r() + " no longer has the action:" + var4.j);
            } else {
               this.c.add(var4);
            }
         } else {
            com.corrodinggames.rts.gameFramework.GlobalState.b("Factory", "buildQueue has uIndex of -1, skipping");
         }
      }

      if(var1.b() >= 5) {
         boolean var6 = var1.readBoolean();
         if(var6) {
            if(this.b == null) {
               this.b = new PointF();
            }

            this.b.a = var1.readFloat();
            this.b.b = var1.readFloat();
         } else {
            this.b = null;
         }
      }

   }

   public strictfp com.corrodinggames.rts.game.units.UnitInstance a(com.corrodinggames.rts.game.units.commands.BuilderUnit var1, float var2, boolean var3, float var4) {
      com.corrodinggames.rts.game.units.actions.GameAction var5 = this.factoryReference.a(var1.j);
      if(var5 == null) {
         NetEngine.registerRelayServer("specialAction=null on completeQueueItem for item.uIndex:" + var1.j + " id:" + this.factoryReference.eh, true);
         return null;
      } else {
         com.corrodinggames.rts.game.units.UnitTypeHandle var6 = var5.i();
         if(var6 == null) {
            NetEngine.registerRelayServer("unitType=null on completeQueueItem for item.uIndex:" + var1.j + " id:" + this.factoryReference.eh, false);
            return null;
         } else {
            return this.a(var6, var2, var3, var4);
         }
      }
   }

   public strictfp void a(com.corrodinggames.rts.game.units.UnitInstance var1, float var2, boolean var3) {
      var1.cl = 30.0F;
      if(this.factoryReference instanceof com.corrodinggames.rts.game.units.commands.ExperimentalWaterUnit) {
         var1.cl += 40.0F;
      }

      if(var1 instanceof com.corrodinggames.rts.game.units.UnitType) {
         com.corrodinggames.rts.game.units.UnitType var4 = (com.corrodinggames.rts.game.units.UnitType)var1;
         var4.j(90.0F);
         if((double)var4.z() < 0.75D) {
            var1.cl += 30.0F;
         }

         if((double)var4.z() < 0.55D) {
            var1.cl += 20.0F;
         }

         float var5 = var3?0.0F:1.0F;
         float var7 = this.factoryReference.eo + com.corrodinggames.rts.gameFramework.GameUtils.cosFast(var1.cg) * var2;
         float var8 = this.factoryReference.ep + com.corrodinggames.rts.gameFramework.GameUtils.sinFast(var1.cg) * var2;
         if(this.b != null) {
            if(var2 != 0.0F) {
               var4.d(var7, var8);
            }

            var4.d(this.b.a + var5, this.b.b);
         } else {
            var7 -= com.corrodinggames.rts.gameFramework.GameUtils.sinFast(var1.cg) * var5;
            var8 += com.corrodinggames.rts.gameFramework.GameUtils.cosFast(var1.cg) * var5;
            if(var2 != 0.0F) {
               var4.d(var7, var8);
            }
         }
      }

   }

   public strictfp com.corrodinggames.rts.game.units.UnitInstance a(com.corrodinggames.rts.game.units.UnitTypeHandle var1, float var2, boolean var3, float var4) {
      com.corrodinggames.rts.game.units.UnitInstance var5 = null;
      var5 = var1.a();
      var5.eo = this.factoryReference.eo;
      var5.ep = this.factoryReference.ep + 5.0F;
      var5.cg = 90.0F + var4;
      var5.f(this.factoryReference.player);
      var5.B(this.factoryReference);
      this.a(var5, var2, var3);
      com.corrodinggames.rts.gameFramework.GlobalState var6 = com.corrodinggames.rts.gameFramework.GlobalState.B();
      if(var5.player == var6.bs) {
         var6.bS.i.a(var5);
      }

      return var5;
   }

   public final strictfp boolean a() {
      return this.c.a == 0;
   }

   public strictfp com.corrodinggames.rts.game.units.commands.BuilderUnit a(com.corrodinggames.rts.game.units.actions.AbstractBuildAction var1, boolean var2) {
      return this.a(var1, var2, (PointF)null, (com.corrodinggames.rts.game.units.UnitInstance)null);
   }

   public strictfp com.corrodinggames.rts.game.units.commands.BuilderUnit a(com.corrodinggames.rts.game.units.actions.AbstractBuildAction var1, boolean var2, PointF var3, com.corrodinggames.rts.game.units.UnitInstance var4) {
      com.corrodinggames.rts.game.units.commands.BuilderUnit var5 = new BuilderUnit();  // 02b j = BuilderUnit
      var5.j = var1.N();
      var5.buildPosition = var3;
      var5.targetUnit = var4;
      if(var5.j == null) {
         throw new RuntimeException("item.uIndex==null??");
      } else {
         var5.builderId = 1;
         var5.buildSpeed = var1.K();
         var5.costResource = var1.B();
         var5.bonusResource = var1.r_();
         var5.buildTargetType = var1.P();
         var5.isCurrentlyBuilding = var1.g();
         var5.serializer = var1.i();
         var5.isRepairing = var1.H();
         if(!var2) {
            com.corrodinggames.rts.game.PlayerState.b((com.corrodinggames.rts.game.units.UnitInstance)this.factoryReference);
            if(var5.isRepairing) {
               int var6 = 0;

               for(int i = 0; i < this.c.size() && ((com.corrodinggames.rts.game.units.commands.BuilderUnit)this.c.get(i)).isRepairing; ++i) {
                  var6 = i + 1;
               }

               if(var6 == 0 && this.c.size() != 0) {
                  ;
               }

               this.c.add(var6, var5);
            } else {
               this.c.add(var5);
            }

            com.corrodinggames.rts.game.PlayerState.c((com.corrodinggames.rts.game.units.UnitInstance)this.factoryReference);
         } else {
            this.d.add(var5);
         }

         return var5;
      }
   }

   public strictfp com.corrodinggames.rts.game.units.commands.BuilderUnit b(com.corrodinggames.rts.game.units.actions.AbstractBuildAction var1, boolean var2) {
      if(var2) {
         if(this.a(var1.N(), true) > 0) {
            com.corrodinggames.rts.game.units.commands.BuilderUnit var6 = this.a(var1, true);
            var6.isAssisting = true;
            return var6;
         } else {
            return null;
         }
      } else {
         com.corrodinggames.rts.gameFramework.utility.CustomArrayList var3 = this.c;
         ListIterator var4 = var3.listIterator(var3.size());

         com.corrodinggames.rts.game.units.commands.BuilderUnit var5;
         do {
            if(!var4.hasPrevious()) {
               return null;
            }

            var5 = (BuilderUnit)var4.previous();
         } while(!var5.j.equals(var1.N()));

         com.corrodinggames.rts.game.PlayerState.b((com.corrodinggames.rts.game.units.UnitInstance)this.factoryReference);
         var4.remove();
         com.corrodinggames.rts.game.PlayerState.c((com.corrodinggames.rts.game.units.UnitInstance)this.factoryReference);
         return var5;
      }
   }

   public strictfp void a(com.corrodinggames.rts.game.units.commands.BuilderUnit var1) {
      this.f = var1;
      this.factoryReference.bC();
   }

   public strictfp BuilderUnit b() {  // 02b j = BuilderUnit
      return this.f;
   }

   public strictfp com.corrodinggames.rts.game.units.custom.resources.CustomActionBase c() {
      if(this.f == null) {
         return null;
      } else if(this.f.bonusResource == null) {
         return null;
      } else {
         float var1 = this.f.buildSpeed * this.factoryReference.cx() * 60.0F;
         return com.corrodinggames.rts.game.units.custom.resources.CustomActionBase.a(this.f.bonusResource, -var1);
      }
   }

   public strictfp com.corrodinggames.rts.game.units.actions.GameAction d() {
      if(this.f != null) {
         com.corrodinggames.rts.game.units.actions.GameAction var1 = this.factoryReference.a(this.f.j);
         return var1;
      } else {
         return null;
      }
   }

   public strictfp void a(float var1) {
      com.corrodinggames.rts.game.units.commands.BuilderUnit var2;
      if(!this.a()) {
         var2 = (com.corrodinggames.rts.game.units.commands.BuilderUnit)this.f().get(0);
         if(this.f != var2) {
            if(var2.buildProgress < 0.0F) {
               var2.buildProgress = 0.0F;
               ((com.corrodinggames.rts.game.units.commands.CarrierUnit)this.factoryReference).b(var2);
            }

            if(this.f != null) {
               this.e = var2.buildProgress;
            }

            this.a(var2);
         }

         float var3 = var2.buildSpeed * this.factoryReference.cx() * var1;
         boolean var4 = false;
         if(var2.bonusResource != null) {
            if(this.e + var3 > 1.0F) {
               var3 = 1.0F - this.e;
               var4 = true;
            }

            double var5 = (double)(this.e + var3) - var2.buildTimer;
            double var7 = 0.0D;
            if(var4) {
               var7 = 1.0D - var2.buildTimer;
            } else {
               double var9 = 0.009999999776482582D;
               if(var5 >= var9) {
                  int var11 = (int)(var5 / var9);
                  var7 = (double)var11 * var9;
               }
            }

            boolean var14 = false;
            if(var7 > 0.0D && this.factoryReference.player.am.a(var2.bonusResource)) {  // 02b j.d = bonusResource
               var14 = true;
            }

            if(!var14 && (var7 <= 0.0D || var2.bonusResource.c(this.factoryReference, var7))) {
               var2.buildTimer += var7;
            } else {
               if(!var14) {
                  this.factoryReference.player.am.a(var2.bonusResource, this.factoryReference, var7);  // 02b j.d = bonusResource
               }

               var3 = 0.0F;
               var4 = false;
            }
         }

         this.e += var3;
         if(var4) {
            this.e = 1.0F;
         }

         var2.buildProgress = this.e;
         if(this.e >= 1.0F) {
            if(var2.isCurrentlyBuilding && ((com.corrodinggames.rts.game.units.commands.CarrierUnit)this.factoryReference).dA()) {
               this.e = 1.0F;
            } else {
               com.corrodinggames.rts.game.PlayerState.b((com.corrodinggames.rts.game.units.UnitInstance)this.factoryReference);
               this.e = 0.0F;
               --var2.builderId;
               if(var2.builderId <= 0) {
                  List var13 = this.f();
                  if(var13.size() == 0) {
                     com.corrodinggames.rts.gameFramework.GlobalState.b("-------------buildQueue empty for:" + var2.j);
                     com.corrodinggames.rts.gameFramework.GlobalState.b("-------------");
                  } else {
                     var13.remove(0);
                  }
               }

               com.corrodinggames.rts.game.PlayerState.c((com.corrodinggames.rts.game.units.UnitInstance)this.factoryReference);
               ((com.corrodinggames.rts.game.units.commands.CarrierUnit)this.factoryReference).a(var2);
            }
         }
      } else {
         this.a((BuilderUnit)null);
         this.e = 0.0F;
         if(this.d.a > 0) {
            var2 = (com.corrodinggames.rts.game.units.commands.BuilderUnit)this.d.get(0);
            if(var2.buildSpeed > 10.0F && var2.buildProgress <= 0.0F) {
               var2.buildProgress = 1.0F;
               com.corrodinggames.rts.game.units.actions.GameAction var12 = this.factoryReference.a(var2.j);
               if(var12 != null && var12.Q()) {
                  var12.a(this.factoryReference);
               }
            }
         }
      }

   }

   public strictfp void e() {
      Iterator var1 = this.c.iterator();

      while(var1.hasNext()) {
         com.corrodinggames.rts.game.units.commands.BuilderUnit var2 = (com.corrodinggames.rts.game.units.commands.BuilderUnit)var1.next();
         com.corrodinggames.rts.game.units.actions.GameAction var3 = this.factoryReference.a(var2.j);
         if(var3 == null) {
            this.b(var2);
            this.c(var2);
            var1.remove();
         }
      }

   }

   public strictfp void a(boolean var1) {
      Iterator var2 = this.c.iterator();

      while(var2.hasNext()) {
         com.corrodinggames.rts.game.units.commands.BuilderUnit var3 = (com.corrodinggames.rts.game.units.commands.BuilderUnit)var2.next();
         if(var1) {
            this.b(var3);
         }

         this.c(var3);
         var2.remove();
      }

   }

   private strictfp void b(com.corrodinggames.rts.game.units.commands.BuilderUnit var1) {
      if(((com.corrodinggames.rts.game.units.commands.CarrierUnit)this.factoryReference).c(var1)) {
         if(var1.bonusResource != null && var1.buildTimer > 0.0D) {
            var1.bonusResource.a(this.factoryReference, var1.buildTimer, true);
         }

         var1.costResource.h(this.factoryReference);
      }

   }

   private strictfp void c(com.corrodinggames.rts.game.units.commands.BuilderUnit var1) {}

   public strictfp int a(com.corrodinggames.rts.game.units.UnitTypeHandle var1) {
      int var2 = 0;
      int var3 = this.c.a;
      if(var3 != 0) {
         Object[] var4 = this.c.a();

         for(int i = 0; i < var3; ++i) {
            com.corrodinggames.rts.game.units.commands.BuilderUnit var6 = (com.corrodinggames.rts.game.units.commands.BuilderUnit)var4[i];
            if(var6.isCurrentlyBuilding) {
               com.corrodinggames.rts.game.units.UnitTypeHandle var7 = var6.serializer;
               if(var7 == var1) {
                  var2 += var6.builderId;
               }
            }
         }
      }

      return var2;
   }

   public strictfp int a(com.corrodinggames.rts.game.units.actions.ActionId var1, boolean var2) {
      return this.a(var1, var2, false);
   }

   public strictfp int a(com.corrodinggames.rts.game.units.custom.TeamTag var1) {
      if(var1 == null) {
         return this.c.a;
      } else {
         int var2 = 0;
         Iterator var3 = this.c.iterator();

         while(var3.hasNext()) {
            com.corrodinggames.rts.game.units.commands.BuilderUnit var4 = (com.corrodinggames.rts.game.units.commands.BuilderUnit)var3.next();
            if(com.corrodinggames.rts.game.units.custom.TeamTag.deserializeTags(var1, var4.buildTargetType)) {
               ++var2;
            }
         }

         return var2;
      }
   }

   public strictfp int a(com.corrodinggames.rts.game.units.actions.ActionId var1, boolean var2, boolean var3) {
      int var4 = 0;
      Iterator var5;
      com.corrodinggames.rts.game.units.commands.BuilderUnit var6;
      if(this.c.a != 0) {
         var5 = this.c.iterator();

         while(var5.hasNext()) {
            var6 = (com.corrodinggames.rts.game.units.commands.BuilderUnit)var5.next();
            if((com.corrodinggames.rts.game.units.actions.GameAction.i == var1 || var6.j.equals(var1)) && (!var3 || var6.isCurrentlyBuilding)) {
               var4 += var6.builderId;
            }
         }
      }

      if(var2 && this.d.a != 0) {
         var5 = this.d.iterator();

         while(var5.hasNext()) {
            var6 = (com.corrodinggames.rts.game.units.commands.BuilderUnit)var5.next();
            if((com.corrodinggames.rts.game.units.actions.GameAction.i == var1 || var6.j.equals(var1)) && (!var3 || var6.isCurrentlyBuilding)) {
               if(!var6.isAssisting) {
                  var4 += var6.builderId;
               } else {
                  var4 -= var6.builderId;
               }
            }
         }
      }

      return var4;
   }

   public strictfp com.corrodinggames.rts.game.units.actions.GameAction b(com.corrodinggames.rts.game.units.UnitTypeHandle var1) {
      ArrayList var2 = this.factoryReference.N();
      int var3 = 0;

      for(int var4 = var2.size(); var3 < var4; ++var3) {
         com.corrodinggames.rts.game.units.actions.GameAction var5 = (com.corrodinggames.rts.game.units.actions.GameAction)var2.get(var3);
         if(var5 != null && var5 instanceof com.corrodinggames.rts.game.units.actions.AbstractBuildAction) {
            com.corrodinggames.rts.game.units.actions.AbstractBuildAction var6 = (com.corrodinggames.rts.game.units.actions.AbstractBuildAction)var5;
            if(var6.i() == var1) {
               return var6;
            }
         }
      }

      return null;
   }

   public strictfp BuilderUnit a(com.corrodinggames.rts.game.units.actions.GameAction var1, boolean var2, PointF var3, com.corrodinggames.rts.game.units.UnitInstance var4) {  // 02b k.java L497: 返回 j=BuilderUnit
      if(var1 instanceof com.corrodinggames.rts.game.units.actions.AbstractBuildAction) {
         com.corrodinggames.rts.game.units.actions.AbstractBuildAction var5 = (com.corrodinggames.rts.game.units.actions.AbstractBuildAction)var1;
         if(!var2) {
            if(var1.a(this.factoryReference, false) && var1.getLabel((com.corrodinggames.rts.game.units.UnitInstance)this.factoryReference) && (!var5.g() || this.factoryReference.player.w() < this.factoryReference.player.x()) && var5.B().c(this.factoryReference)) {
               return this.a(var5, false, var3, var4);
            }
         } else {
            com.corrodinggames.rts.game.units.commands.BuilderUnit var6 = this.b(var5, false);
            if(var6 != null) {
               this.b(var6);
               this.c(var6);
               return var6;
            }
         }
      }

      return null;
   }

   public strictfp void a(com.corrodinggames.rts.game.units.actions.GameAction var1, boolean var2) {
      if(var1 instanceof com.corrodinggames.rts.game.units.actions.AbstractBuildAction) {
         com.corrodinggames.rts.game.units.actions.AbstractBuildAction var3 = (com.corrodinggames.rts.game.units.actions.AbstractBuildAction)var1;
         if(!var2) {
            if(var1.a(this.factoryReference, true) && (!var3.g() || this.factoryReference.player.w() < this.factoryReference.player.x()) && var3.B().b(this.factoryReference, var1.Q())) {
               this.a(var3, true);
            }
         } else if(this.b(var3, true) != null) {
            var3.B().e(this.factoryReference, var1.Q());
         }
      }

   }

   public strictfp void a(com.corrodinggames.rts.game.units.actions.GameAction var1) {
      if(this.d.size() != 0) {
         com.corrodinggames.rts.game.units.commands.BuilderUnit var2 = null;
         Iterator var3 = this.d.iterator();

         while(var3.hasNext()) {
            com.corrodinggames.rts.game.units.commands.BuilderUnit var4 = (com.corrodinggames.rts.game.units.commands.BuilderUnit)var3.next();
            if(var4.j.equals(var1.N())) {
               var2 = var4;
            }
         }

         if(var2 != null) {
            if(!var2.isAssisting) {
               var2.costResource.e(this.factoryReference, var1.Q());  // 02b j.c = costResource
            } else {
               var2.costResource.d(this.factoryReference, var1.Q());
            }

            this.d.remove(var2);
         }
      }

   }

   public strictfp List f() {
      return this.c;
   }

   public strictfp com.corrodinggames.rts.gameFramework.utility.CustomArrayList g() {
      return this.c;
   }
}
