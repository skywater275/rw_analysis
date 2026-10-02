package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.t;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.v;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$ActiveWaypointTargetReference;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$AttachmentUnitReference;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$AttackingReference;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$ChainedUnitReference;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$EventSourceReference;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$FirstUnitReference;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$GetAsMarker;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$GetOffsetAbsolute;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$GetOffsetRelative;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$LastDamagedByUnitReference;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$Memory1UnitReference;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$Memory2UnitReference;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$NearestUnitReference;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$NullUnitReference;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$ParentUnitReference;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$SelfUnitReference;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$ThisActionTargetReference;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$TransportingUnitReference;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$UnitContextChangingBooleanByLogic;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$UnitContextChangingContext;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$UnitReferenceOrUnitType;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.al;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public abstract class UnitReference extends LogicBoolean implements Cloneable {

   public static final UnitReference$SelfUnitReference selfUnitReference = new UnitReference$SelfUnitReference();
   static HashMap referenceTypes = new HashMap();
   static final LogicBooleanLoader$LogicBooleanContext unitContextChangingContext;
   static final LogicBooleanLoader$LogicBooleanContext placeholderUnitContext;


   public final am get(am var1) {
      return !(var1 instanceof y)?null:this.getSingleRaw((y)var1);
   }

   public final am get(y var1) {
      return this.getSingleRaw(var1);
   }

   public final am getRealUnitOnly(y var1) {
      am var2 = this.getSingleRaw(var1);
      return var2 instanceof t?null:var2;
   }

   public abstract am getSingleRaw(y var1);

   public void forMeta(l var1) {}

   static void addUnitReferenceType(UnitReference var0, String ... var1) {
      String[] var2 = var1;
      int var3 = var1.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         String var5 = var2[var4];
         var5 = var5.toLowerCase(Locale.ROOT);
         referenceTypes.put(var5, var0);
         String var6 = var5.replace("self.", "subject.");
         if(!var6.equals(var5)) {
            ;
         }
      }

   }

   public static UnitReference$UnitReferenceOrUnitType parseUnitTypeOrReferenceFromConf(l var0, ab var1, String var2, String var3, UnitReference$UnitReferenceOrUnitType var4) {
      String var5 = var1.b(var2, var3, (String)null);
      return parseUnitTypeOrReference(var0, var5, var2, var3, var4);
   }

   public static UnitReference$UnitReferenceOrUnitType parseUnitTypeOrReference(l var0, String var1, String var2, String var3, UnitReference$UnitReferenceOrUnitType var4) {
      if(var1 == null) {
         return var4;
      } else {
         var1 = var1.trim();
         if(!"".equals(var1) && !"NONE".equalsIgnoreCase(var1)) {
            if(var1.toLowerCase(Locale.ROOT).startsWith("unitref ")) {
               UnitReference var6 = parseUnitReference(var0, var1, var2, var3, (UnitReference)null, true);
               return new UnitReference$UnitReferenceOrUnitType(var6);
            } else {
               v var5 = var0.a(var1, var3, var2);
               if(var5 != null) {
                  var5.f = true;
               }

               return new UnitReference$UnitReferenceOrUnitType(var5);
            }
         } else {
            return var4;
         }
      }
   }

   public static UnitReference parseUnitReferenceFromConf(l var0, ab var1, String var2, String var3, UnitReference var4) {
      String var5 = var1.b(var2, var3, (String)null);
      return parseUnitReference(var0, var5, var2, var3, var4, false);
   }

   public static UnitReference parseUnitReference(l var0, String var1, String var2, String var3, UnitReference var4, boolean var5) {
      if(var1 == null) {
         return var4;
      } else {
         var1 = var1.trim();
         if(!"".equals(var1) && !"NONE".equalsIgnoreCase(var1)) {
            var1 = var1.toLowerCase(Locale.ROOT);
            if(var1.startsWith("unitref ")) {
               var1 = var1.substring("unitref ".length());
               var1 = var1.trim();
            }

            if(var1.equals("self")) {
               return selfUnitReference;
            } else {
               int var6 = var1.indexOf("(");
               if(var6 == -1) {
                  var6 = var1.length();
               } else if(var1.indexOf(")") != var1.length() - 1) {
                  throw new bo("[" + var2 + "]" + var3 + " UnitReference: Unexpected format for: \'" + var1 + "\'");
               }

               try {
                  UnitReference var7 = parseSingleUnitReferenceBlock(var0, var1);
                  if(var7 == null) {
                     throw new RuntimeException("Unknown function:\'" + var1 + "\'");
                  } else {
                     return var7;
                  }
               } catch (RuntimeException var8) {
                  throw new RuntimeException("[" + var2 + "]" + var3 + " UnitReference error: " + var8.getMessage() + ", [parsing: \'" + var1 + "\']", var8);
               }
            }
         } else {
            return var4;
         }
      }
   }

   public static UnitReference parseSingleUnitReferenceElement(l var0, String var1) {
      String var2 = var1.split("\\(")[0];
      var2 = var2.trim().toLowerCase(Locale.ROOT);
      UnitReference var3 = (UnitReference)referenceTypes.get(var2);
      if(var3 == null) {
         return null;
      } else {
         String var4 = var1.substring(var2.length());
         var4 = var4.trim();
         if(var4.equals("")) {
            var4 = "()";
         }

         if(var4.startsWith("(") && var4.endsWith(")")) {
            var4 = var4.substring(1, var4.length() - 1);
            var4 = var4.trim();
            UnitReference var5 = var3.with(var0, var4, var2);
            if(var5 instanceof UnitReference$NullUnitReference) {
               ;
            }

            return var5;
         } else {
            throw new RuntimeException("Failed to parse unit reference arguments for:\'" + var1 + "\'");
         }
      }
   }

   public static UnitReference parseSingleUnitReferenceBlock(l var0, String var1) {
      int var2 = al.b(var1);
      if(var2 != 0) {
         if(var2 > 0) {
            throw new RuntimeException("Brackets unbalanced for: \'" + var1 + "\'. A \'(\' was not closed.");
         }

         if(var2 < 0) {
            throw new RuntimeException("Brackets unbalanced for: \'" + var1 + "\'. Too many \')\'.");
         }
      }

      var1 = var1.trim();
      var1 = LogicBooleanLoader.breakOuterLayerBrackets(var1);
      String[] var3 = al.b(var1, ".", false);
      ArrayList var4 = new ArrayList();
      boolean var5 = false;

      for(int var6 = 0; var6 < var3.length; ++var6) {
         String var7 = var3[var6];
         if(var7.equalsIgnoreCase("self")) {
            var5 = true;
         } else {
            UnitReference var8 = parseSingleUnitReferenceElement(var0, var7);
            if(var8 == null) {
               throw new RuntimeException("Unknown unit reference:\'" + var7 + "\'");
            }

            var4.add(var8);
         }
      }

      if(var4.size() == 0) {
         if(var5) {
            return selfUnitReference;
         } else {
            throw new RuntimeException("Unexpected unit reference:\'" + var1 + "\'");
         }
      } else if(var4.size() == 1) {
         return (UnitReference)var4.get(0);
      } else {
         UnitReference[] var9 = (UnitReference[])var4.toArray(new UnitReference[0]);
         return new UnitReference$ChainedUnitReference(var9);
      }
   }

   public UnitReference with(String var1) {
      return this.with((l)null, var1, (String)null);
   }

   public UnitReference with(l var1, String var2, String var3) {
      UnitReference var4;
      try {
         var4 = (UnitReference)this.clone();
      } catch (CloneNotSupportedException var6) {
         throw new RuntimeException(var6);
      }

      var4.forMeta(var1);
      var4.setArgumentsRaw(var2, var1, var3);
      return var4;
   }

   public boolean read(y var1) {
      return false;
   }

   public am readUnit(y var1) {
      return this.getSingleRaw(var1);
   }

   public LogicBoolean$ReturnType getReturnType() {
      return LogicBoolean$ReturnType.unit;
   }

   public String getClassDebugName() {
      return "<unit reference>";
   }

   public String getMatchFailReasonForPlayer(y var1) {
      return this.getClassDebugName() + "(" + am.A(this.getSingleRaw(var1)) + ")";
   }

   public LogicBooleanLoader$LogicBooleanContext createContext() {
      return unitContextChangingContext;
   }

   public LogicBoolean setChild(LogicBoolean var1) {
      UnitReference$UnitContextChangingBooleanByLogic var2 = UnitReference$UnitContextChangingBooleanByLogic.create(this, var1);
      return var2;
   }

   static {
      addUnitReferenceType(new UnitReference$AttachmentUnitReference(), new String[]{"attachment"});
      addUnitReferenceType(new UnitReference$ParentUnitReference(), new String[]{"parent"});
      addUnitReferenceType(new UnitReference$TransportingUnitReference(), new String[]{"transporting"});
      addUnitReferenceType(new UnitReference$ActiveWaypointTargetReference(), new String[]{"activeWaypointTarget"});
      addUnitReferenceType(new UnitReference$AttackingReference(), new String[]{"attacking"});
      addUnitReferenceType(new UnitReference$Memory1UnitReference(), new String[]{"customTarget1"});
      addUnitReferenceType(new UnitReference$Memory2UnitReference(), new String[]{"customTarget2"});
      addUnitReferenceType(new UnitReference$LastDamagedByUnitReference(), new String[]{"lastDamagedBy"});
      addUnitReferenceType(new UnitReference$NearestUnitReference(), new String[]{"nearestUnit"});
      addUnitReferenceType(new UnitReference$FirstUnitReference(), new String[]{"globalSearchForFirstUnit"});
      addUnitReferenceType(new UnitReference$NullUnitReference(), new String[]{"nullUnit"});
      addUnitReferenceType(new UnitReference$NullUnitReference(), new String[]{"null"});
      addUnitReferenceType(new UnitReference$GetOffsetAbsolute(), new String[]{"getOffsetAbsolute"});
      addUnitReferenceType(new UnitReference$GetOffsetRelative(), new String[]{"getOffsetRelative"});
      addUnitReferenceType(new UnitReference$GetAsMarker(), new String[]{"getAsMarker"});
      addUnitReferenceType(new UnitReference$ThisActionTargetReference(), new String[]{"thisActionTarget"});
      addUnitReferenceType(new UnitReference$EventSourceReference(), new String[]{"eventSource"});
      unitContextChangingContext = new UnitReference$UnitContextChangingContext();
      placeholderUnitContext = new UnitReference$UnitContextChangingContext();
   }
}
