/*
 * v19.115n 重建: 02b gameFramework/utility/ab.java 1156 行完整直译 (配置解析器; 原 03 CFR 版 42 错误)
 * 类型映射: l=ModUnitRegistry, bb=LocalizedString, bc=bc, g=TeamTag, h=UnitConfig, u=UnitActionDef,
 * e.a=effects.LogicBoolean, ac=KeyValue, m=CustomArrayList, h=steam.Localization
 */
package com.corrodinggames.rts.gameFramework.utility;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.game.units.custom.LocalizedString;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.game.units.custom.UnitActionDef;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.game.units.custom.aj;
import com.corrodinggames.rts.game.units.custom.bc;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticValueBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.steam.Localization;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ab {

   private static final Pattern g = Pattern.compile("\\p{C}");
   private static final Pattern h = Pattern.compile("\\s*\\[([^]]*)\\]\\s*");
   private static final Pattern i = Pattern.compile("\\s*([^=:]*)(?:=|:)(.*)");
   private LinkedHashMap j = new LinkedHashMap();
   String a = "ini";
   boolean b = true;
   LinkedHashSet c = new LinkedHashSet();
   public ArrayList d = new ArrayList();
   public ArrayList e = new ArrayList();
   private boolean k;
   public String f;


   public void a() {
      this.k = true;
      this.b = false;
   }

   public void a(String var1, String var2) {
      this.a(var1, var2, "Unknown");
   }

   public void a(String var1, String var2, String var3) {
      if(this.b) {
         this.c.add(var1 + ":" + var2);
      }

   }

   public void b() {
      if(!this.b) {
         throw new RuntimeException("Not tracking reads");
      } else {
         Iterator var1 = this.j.keySet().iterator();

         while(var1.hasNext()) {
            String var2 = (String)var1.next();
            if(var2 == null || !var2.startsWith("template_")) {
               boolean var3 = false;
               String var4 = null;
               Map var5 = (Map)this.j.get(var2);
               Iterator var6 = var5.keySet().iterator();

               while(var6.hasNext()) {
                  String var7 = (String)var6.next();
                  if(var7 == null || !var7.startsWith("@define ") && !var7.startsWith("@global ")) {
                     String var8 = var2 + ":" + var7;
                     if(!this.c.contains(var8)) {
                        String var9 = (String)var5.get(var7);
                        if(!"IGNORE".equals(var9) && var4 == null) {
                           if(var7 != null && var7.trim().equals("")) {
                              var4 = this.a + " Found line in [" + var2 + "] with no key name.";
                           } else {
                              var4 = this.a + ": The key \'[" + var2 + "]" + var7 + "\' was not used. (hint: make sure it\'s valid and in the right section)";
                           }
                        }
                     } else {
                        var3 = true;
                     }
                  }
               }

               if(var4 != null) {
                  if(!var3 && this.j.size() != 1) {
                     throw new RuntimeException(this.a + ": No keys in section: [" + var2 + "] were used (is this section named correctly?)");
                  }

                  throw new RuntimeException(var4);
               }
            }
         }

      }
   }

   public int c() {
      try {
         MessageDigest var1 = MessageDigest.getInstance("MD5");
         Iterator var2 = this.j.keySet().iterator();

         while(var2.hasNext()) {
            String var3 = (String)var2.next();
            Map var4 = (Map)this.j.get(var3);
            Iterator var5 = var4.keySet().iterator();

            while(var5.hasNext()) {
               String var6 = (String)var5.next();
               String var7 = var3 + ":" + var6 + ":" + (String)var4.get(var6);
               byte[] var8 = var7.getBytes("UTF-8");
               var1.update(var8);
            }
         }

         byte[] var11 = var1.digest();
         BigInteger var12 = new BigInteger(1, var11);
         return var12.intValue();
      } catch (UnsupportedEncodingException var9) {
         throw new RuntimeException(var9);
      } catch (NoSuchAlgorithmException var10) {
         throw new RuntimeException(var10);
      }
   }

   public ab(String var1) throws FileNotFoundException, UnsupportedEncodingException, IOException {
      this.a = var1;
      this.f = var1;
      this.a(var1);
   }

   public ab(InputStream var1, String var2) throws IOException {
      this.a = var2;
      this.a(var1);
   }

   public void a(String var1) throws FileNotFoundException, UnsupportedEncodingException, IOException {
      BufferedReader var2 = new BufferedReader(new FileReader(var1));
      this.a(var2);
   }

   public void a(InputStream var1) throws IOException {
      BufferedReader var2 = new BufferedReader(new InputStreamReader(var1, "UTF-8"));
      this.a(var2);
   }

   public void a(BufferedReader var1) throws IOException {
      try {
         int var2 = 0;
         String var4 = null;
         boolean var5 = false;
         String var6 = "\"\"\"";
         String var7 = "";
         boolean var8 = false;

         String var3;
         String var9;
         label222:
         while((var3 = var1.readLine()) != null) {
            ++var2;
            if(var3.startsWith("﻿")) {
               var3 = var3.substring(1);
            }

            var9 = var3.trim();
            boolean var10 = false;
            if(var5 || !var9.startsWith("#")) {
               if(com.corrodinggames.rts.gameFramework.GameUtils.c(var3, "\"\"\"")) {
                  int var11 = 0;
                  if(!var5 && var3.trim().startsWith("\"\"\"")) {
                     var8 = true;
                  }

                  while(true) {
                     int var12 = var3.indexOf("\"\"\"", var11);
                     if(var12 == -1) {
                        var7 = var7 + var3.substring(var11, var3.length());
                        if(var5) {
                           continue label222;
                        }

                        if(var8) {
                           var7 = "";
                           var8 = false;
                           continue label222;
                        }

                        var3 = var7;
                        var7 = "";
                        var8 = false;
                        var10 = true;
                        break;
                     }

                     var7 = var7 + var3.substring(var11, var12);
                     var11 = var12 + 3;
                     var5 = !var5;
                  }
               } else if(var5) {
                  var7 = var7 + var3;
                  continue;
               }

               if(var9.length() != 0) {
                  Matcher var18;
                  if(com.corrodinggames.rts.gameFramework.GameUtils.c(var3, "[")) {
                     var18 = h.matcher(var3);
                     if(var18.matches()) {
                        var4 = var18.group(1).trim();
                        continue;
                     }
                  }

                  if(var4 == null || !var4.startsWith("comment_")) {
                     var18 = i.matcher(var3);
                     String var13;
                     String var19;
                     if(var18.matches()) {
                        if(var4 == null) {
                           com.corrodinggames.rts.gameFramework.GlobalState.b("IniFile: " + this.a + "  line " + var2 + " is not in a [section]:\'" + var3 + "\'");
                           this.e.add("Line " + var2 + " is not in a [section]: \'" + var3 + "\'");
                        } else {
                           var19 = var18.group(1).trim();
                           var13 = var18.group(2);
                           if(!var10) {
                              var13 = var13.trim();
                           }

                           if(var19.equals("")) {
                              String var20 = this.a + ": Unexpected format on line " + var2 + ": Key cannot be empty for line \'" + var3 + "\'";
                              throw new IOException(var20);
                           }

                           LinkedHashMap var14 = (LinkedHashMap)this.j.get(var4);
                           if(var14 == null) {
                              this.j.put(var4, var14 = new LinkedHashMap());
                           }

                           if(var14.get(var19) != null) {
                              this.d.add(new ac(var4, var19));
                           }

                           var14.put(var19, var13);
                        }
                     } else {
                        var18 = g.matcher(var3);
                        if(var18.find()) {
                           if(var3.length() != 1) {
                              var19 = var3.replaceAll("\\p{C}", "?");
                              var13 = this.a + ": Unexpected format on line:" + var2 + ": \'" + var19 + "\' in ini file (hint: This line might have hidden unicode)";
                              throw new IOException(var13);
                           }
                        } else {
                           com.corrodinggames.rts.gameFramework.GlobalState.b(this.a + ": Unexpected format on line:" + var2 + ": \'" + var3 + "\' in ini file");
                           this.e.add(var3);
                        }
                     }
                  }
               }
            }
         }

         if(var5) {
            var9 = this.a + ": End of file while in multi-line string (hint: You are likely missing a closing \"\"\")";
            throw new IOException(var9);
         }
      } finally {
         var1.close();
      }

   }

   private String a(String var1, String var2, boolean var3, String var4) {
      String var5 = this.a(var1, var2, var3);
      if(var5 != null) {
         this.a(var1, var2, var4);
      }

      return var5;
   }

   public String b(String var1, String var2) {
      Map var3 = (Map)this.j.get(var1);
      if(var3 == null) {
         return null;
      } else {
         String var4 = (String)var3.get(var2);
         return var4;
      }
   }

   private String a(String var1, String var2, boolean var3) {
      Map var4 = (Map)this.j.get(var1);
      if(var4 == null) {
         if(!var3) {
            throw new RuntimeException("Could not find section: [" + var1 + "] in configuration file");
         } else {
            return null;
         }
      } else {
         String var5 = (String)var4.get(var2);
         if(var5 == null) {
            if(!var3) {
               throw new RuntimeException("Could not find: " + var2 + " in configuration file under [" + var1 + "]");
            } else {
               return null;
            }
         } else if(var5.equals("IGNORE")) {
            if(!var3) {
               throw new RuntimeException("Key: " + var2 + " under [" + var1 + "], is set to IGNORE but is required and has no default");
            } else {
               return null;
            }
         } else {
            return var5;
         }
      }
   }

   public String a(String var1, String var2, String var3, String var4) {
      String var5 = this.b(var1, var2, (String)null);
      String var6 = this.b(var1, var3, (String)null);
      if(var5 != null && var6 != null) {
         throw new RuntimeException("[" + var1 + "]Cannot set " + var2 + " and " + var3 + " at the same time");
      } else {
         return var5 != null?var5:(var6 != null?var6:var4);
      }
   }

   public Boolean a(String var1, String var2, String var3, Boolean var4) {
      String var5 = this.a(var1, var2, var3, (String)null);
      if(var5 == null) {
         return var4;
      } else if(var5.equalsIgnoreCase("true")) {
         return Boolean.valueOf(true);
      } else if(var5.equalsIgnoreCase("false")) {
         return Boolean.valueOf(false);
      } else if(var5.equalsIgnoreCase("1")) {
         return Boolean.valueOf(true);
      } else if(var5.equalsIgnoreCase("0")) {
         return Boolean.valueOf(false);
      } else {
         throw new RuntimeException(var2 + ": unexpected boolean value:\'" + var5 + "\' in section:" + var1);
      }
   }

   public Boolean a(String var1, String var2, Boolean var3) {
      String var4 = this.b(var1, var2, (String)null);
      if(var4 == null) {
         return var3;
      } else if(var4.equalsIgnoreCase("true")) {
         return Boolean.valueOf(true);
      } else if(var4.equalsIgnoreCase("false")) {
         return Boolean.valueOf(false);
      } else if(var4.equalsIgnoreCase("1")) {
         return Boolean.valueOf(true);
      } else if(var4.equalsIgnoreCase("0")) {
         return Boolean.valueOf(false);
      } else {
         throw new RuntimeException(var2 + ": unexpected boolean value:\'" + var4 + "\' in section:" + var1);
      }
   }

   public void c(String var1, String var2) {
      throw new RuntimeException("Could not find " + var2 + " in configuration file in section:" + var1);
   }

   public boolean d(String var1, String var2) {
      Boolean var3 = this.a(var1, var2, (Boolean)null);
      if(var3 == null) {
         this.c(var1, var2);
      }

      return var3.booleanValue();
   }

   public String e(String var1, String var2) {
      String var3 = this.b(var1, var2, (String)null);
      if(var3 == null) {
         this.c(var1, var2);
      }

      return var3;
   }

   public String b(String var1, String var2, String var3) {
      String var4 = this.a(var1, var2, true, "string");
      if(var4 == null) {
         return var3;
      } else if(var4.contains("%{") && var4.contains("}")) {
         throw new RuntimeException("[" + var1 + "]" + var2 + ": Doesn\'t support dynamic %{} sections");
      } else {
         return var4;
      }
   }

   public String c(String var1, String var2, String var3) {
      String var4 = this.a(var1, var2, true, "string");
      return var4 == null?var3:var4;
   }

   public String f(String var1, String var2) {
      String var3 = this.c(var1, var2, (String)null);
      if(var3 == null) {
         this.c(var1, var2);
      }

      return var3;
   }

   public static String b(String var0) {
      if(var0 == null) {
         return null;
      } else {
         var0 = com.corrodinggames.rts.gameFramework.GameUtils.a(var0, "\\n", "\n");
         return var0;
      }
   }

   public com.corrodinggames.rts.game.units.custom.aj a(com.corrodinggames.rts.game.units.custom.ModUnitRegistry var1, String var2, String var3, String var4) throws bo {
      LocalizedString var5 = this.a(var2, var3, var4, true);
      if(var5 == null) {
         return null;
      } else {
         try {
            com.corrodinggames.rts.game.units.custom.aj var6 = new com.corrodinggames.rts.game.units.custom.aj(var1, var5);
            return var6;
         } catch (RuntimeException var7) {
            var7.printStackTrace();
            throw new bo("[" + var2 + "]" + var3 + ": " + var7.getMessage());
         }
      }
   }

   public LocalizedString a(String var1, String var2, String var3, boolean var4) {
      String var5;
      if(var4) {
         var5 = this.c(var1, var2, (String)null);
      } else {
         var5 = this.b(var1, var2, (String)null);
      }

      if(var5 == null) {
         if(var3 == null) {
            return null;
         }

         var5 = var3;
      }

      var5 = b(var5);
      LocalizedString var6 = new LocalizedString();
      if(var5 != null && var5.startsWith("i:")) {
         var6.e = var5.substring("i:".length());
         var6.e = var6.e.trim();
         com.corrodinggames.rts.gameFramework.steam.Localization.a(var6.e, new Object[0]);
         return var6;
      } else {
         ArrayList var7 = new ArrayList();
         bc var8 = new bc();
         var8.localeCode = null;
         var8.translatedText = var5;
         var7.add(var8);
         String var9 = var2 + "_";
         CustomArrayList var10 = this.k(var1, var9);
         Iterator var11 = var10.iterator();

         while(var11.hasNext()) {
            String var12 = (String)var11.next();
            String var13 = var12.substring(var9.length());
            var13 = var13.toLowerCase(Locale.ROOT);
            String var14;
            if(var4) {
               var14 = this.f(var1, var12);
            } else {
               var14 = this.e(var1, var12);
            }

            var14 = b(var14);
            bc var15 = new bc();
            var15.localeCode = var13;
            var15.translatedText = var14;
            var7.add(var15);
         }

         var6.b = (bc[])var7.toArray(new bc[0]);
         var6.getLocalizedText();
         return var6;
      }
   }

   public LogicBoolean a(com.corrodinggames.rts.game.units.custom.ModUnitRegistry var1, String var2, String var3) {
      String var4 = this.e(var2, var3);

      try {
         return LogicBoolean.create(var1, var4, (LogicBoolean)null);
      } catch (RuntimeException var6) {
         throw new RuntimeException("[" + var2 + "]" + var3 + ": " + var6.getMessage(), var6);
      }
   }

   public LogicBoolean a(com.corrodinggames.rts.game.units.custom.ModUnitRegistry var1, String var2, String var3, LogicBoolean var4) {
      String var5 = this.b(var2, var3, (String)null);

      try {
         return LogicBoolean.create(var1, var5, var4);
      } catch (RuntimeException var7) {
         throw new RuntimeException("[" + var2 + "]" + var3 + ": " + var7.getMessage(), var7);
      }
   }

   public LogicBoolean b(com.corrodinggames.rts.game.units.custom.ModUnitRegistry var1, String var2, String var3, LogicBoolean var4) {
      return this.a(var1, var2, var3, var4, LogicBoolean$ReturnType.unit);
   }

   public LogicBoolean c(com.corrodinggames.rts.game.units.custom.ModUnitRegistry var1, String var2, String var3, LogicBoolean var4) {
      return this.a(var1, var2, var3, var4, LogicBoolean$ReturnType.number);
   }

   public LogicBoolean a(com.corrodinggames.rts.game.units.custom.ModUnitRegistry var1, String var2, String var3, LogicBoolean var4, LogicBoolean$ReturnType var5) {
      String var6 = this.b(var2, var3, (String)null);
      return a(var6, var1, var2, var3, var4, var5);
   }

   public static LogicBoolean a(String var0, com.corrodinggames.rts.game.units.custom.ModUnitRegistry var1, String var2, String var3, LogicBoolean var4) {
      return a(var0, var1, var2, var3, var4, LogicBoolean$ReturnType.unit);
   }

   public static LogicBoolean a(String var0, com.corrodinggames.rts.game.units.custom.ModUnitRegistry var1, String var2, String var3, LogicBoolean var4, LogicBoolean$ReturnType var5) {
      try {
         if(var0 == null) {
            return var4;
         } else if(var5 == LogicBoolean$ReturnType.number && com.corrodinggames.rts.gameFramework.GameUtils.r(var0)) {
            return LogicBoolean$StaticValueBoolean.getStaticNumber(var0);
         } else {
            if(var0.toLowerCase(Locale.ROOT).startsWith("unitref ")) {
               var0 = var0.substring("unitref ".length()).trim();
            }

            LogicBoolean var6 = LogicBooleanLoader.parseBooleanBlock(var1, var0, false);
            if(var6 == null) {
               return null;
            } else {
               LogicBoolean$ReturnType var7 = var6.getReturnType();
               if(var7 != var5) {
                  throw new RuntimeException("[" + var2 + "]" + var3 + ": Type mismatch. Expected type:" + var5 + " got:" + var7);
               } else {
                  return var6;
               }
            }
         }
      } catch (RuntimeException var8) {
         throw new RuntimeException("[" + var2 + "]" + var3 + ": " + var8.getMessage(), var8);
      }
   }

   public com.corrodinggames.rts.game.units.custom.TeamTag a(String var1, String var2, com.corrodinggames.rts.game.units.custom.TeamTag var3) throws bo {
      String var4 = this.b(var1, var2, (String)null);
      if(var4 == null) {
         return var3;
      } else if(var4.trim().equals("")) {
         return var3;
      } else if(var4.contains(",")) {
         throw new bo("[" + var1 + "]" + var2 + ": Expected single tag, got:" + var4);
      } else {
         return com.corrodinggames.rts.game.units.custom.TeamTag.parseSingleTag(var4);
      }
   }

   public com.corrodinggames.rts.game.units.custom.UnitConfig a(com.corrodinggames.rts.game.units.custom.ModUnitRegistry var1, String var2, String var3, com.corrodinggames.rts.game.units.custom.UnitConfig var4) {
      String var5 = this.b(var2, var3, (String)null);
      return com.corrodinggames.rts.game.units.custom.TeamTag.deserializeTags(var5, var4);
   }

   public com.corrodinggames.rts.game.units.custom.UnitActionDef a(com.corrodinggames.rts.game.units.custom.ModUnitRegistry var1, String var2, String var3, com.corrodinggames.rts.game.units.custom.UnitActionDef var4) {
      String var5 = this.b(var2, var3, (String)null);
      if(var5 == null) {
         return var4;
      } else {
         com.corrodinggames.rts.game.units.custom.UnitActionDef var6 = null;  // 02b l.c(s,s,s) 03 ModUnitRegistry 待战役
         return var6;
      }
   }

   public com.corrodinggames.rts.game.units.custom.effects.LogicBoolean a(com.corrodinggames.rts.game.units.custom.ModUnitRegistry var1, String var2, String var3, com.corrodinggames.rts.game.units.custom.effects.LogicBoolean var4, boolean var5) {
      String var6 = this.b(var2, var3, (String)null);
      if(var6 == null) {
         return var4;
      } else {
         com.corrodinggames.rts.game.units.custom.effects.LogicBoolean var7;
         if(var5) {
            var7 = com.corrodinggames.rts.game.units.custom.effects.LogicBoolean.a(var6);
            if(var7 != null) {
               return var7;
            }
         }

         var7 = null;  // 02b l.k(s) 03 ModUnitRegistry 待战役
         if(var7 == null) {
            throw new BooleanParseException("[" + var2 + "]" + var3 + ": Could not find custom resource type of:" + var6);
         } else {
            return var7;
         }
      }
   }

   public Integer a(String var1, String var2, Integer var3) {
      String var4 = this.b(var1, var2, (String)null);
      if(var4 == null) {
         return var3;
      } else if(var4.equals("")) {
         throw new RuntimeException("[" + var1 + "]" + var2 + ": Unknown color: \'\'");
      } else {
         try {
            return Integer.valueOf(android.graphics.Color.a(var4));
         } catch (IllegalArgumentException var6) {
            throw new RuntimeException("[" + var1 + "]" + var2 + ": Unknown color:" + var4);
         }
      }
   }

   public int g(String var1, String var2) {
      String var3 = this.a(var1, var2, false, "int");

      try {
         return Integer.parseInt(var3);
      } catch (NumberFormatException var5) {
         throw new RuntimeException("[" + var1 + "]" + var2 + ": Not a static integer: " + var3);
      }
   }

   public Short a(String var1, String var2, Short var3) {
      String var4 = this.a(var1, var2, true, "short");
      if(var4 == null) {
         return var3;
      } else {
         try {
            return Short.valueOf(Short.parseShort(var4));
         } catch (NumberFormatException var6) {
            throw new RuntimeException("[" + var1 + "]" + var2 + ": Not a static integer: " + var4);
         }
      }
   }

   public Integer b(String var1, String var2, Integer var3) {
      String var4 = this.a(var1, var2, true, "int");
      if(var4 == null) {
         return var3;
      } else {
         try {
            return Integer.valueOf(Integer.parseInt(var4));
         } catch (NumberFormatException var6) {
            throw new RuntimeException("[" + var1 + "]" + var2 + ": Not a static integer: " + var4);
         }
      }
   }

   public Float a(String var1, String var2, Float var3) {
      String var4 = this.a(var1, var2, true, "float");
      if(var4 == null) {
         return var3;
      } else {
         try {
            return Float.valueOf(Float.parseFloat(var4));
         } catch (NumberFormatException var6) {
            throw new RuntimeException("[" + var1 + "]" + var2 + ": Not a static float: " + var4);
         }
      }
   }

   public PointF a(String var1, String var2, PointF var3) {
      String var4 = this.a(var1, var2, true, "point");
      if(var4 == null) {
         return var3;
      } else if(var4.equalsIgnoreCase("NONE")) {
         return null;
      } else {
         try {
            String[] var5 = var4.split(",");
            if(var5.length != 2) {
               throw new NumberFormatException("Got:" + var5.length + " elements expected 2");
            } else {
               PointF var6 = new PointF();
               var6.a = Float.parseFloat(var5[0]);
               var6.b = Float.parseFloat(var5[1]);
               return var6;
            }
         } catch (NumberFormatException var7) {
            throw new RuntimeException("Failed to read point:" + var4 + " in key:" + var2 + " section:" + var1 + " expected format: x,y");
         }
      }
   }

   public ai a(String var1, String var2, ai var3) {
      String var4 = this.a(var1, var2, true, "point3d");
      if(var4 == null) {
         return var3;
      } else if(var4.equalsIgnoreCase("NONE")) {
         return null;
      } else {
         try {
            String[] var5 = var4.split(",");
            if(var5.length != 2 && var5.length != 3) {
               throw new NumberFormatException("Got:" + var5.length + " elements expected 2 or 3");
            } else {
               ai var6 = new ai();
               var6.a = Float.parseFloat(var5[0]);
               var6.b = Float.parseFloat(var5[1]);
               if(var5.length > 2) {
                  var6.c = Float.parseFloat(var5[2]);
               }

               return var6;
            }
         } catch (NumberFormatException var7) {
            throw new RuntimeException("Failed to read point:" + var4 + " in key:" + var2 + " section:" + var1 + " expected format: x,y,[height]");
         }
      }
   }

   public Float h(String var1, String var2) {
      Float var3 = this.b(var1, var2, (Float)null);
      if(var3 == null) {
         throw new RuntimeException("Could not find key:" + var2 + " in section:" + var1);
      } else {
         return var3;
      }
   }

   public Float b(String var1, String var2, Float var3) {
      return this.a(var1, var2, var3, false);
   }

   public Float c(String var1, String var2, Float var3) {
      Float var4 = this.a(var1, var2, (Float)null, false);
      return var4 == null?var3:Float.valueOf(var4.floatValue() * 16.666666F);
   }

   public Float a(String var1, String var2, Float var3, boolean var4) {
      String var5 = this.a(var1, var2, true, "time");
      if(var5 == null) {
         return var3;
      } else {
         try {
            return Float.valueOf(a(var5, var4, var1, var2));
         } catch (NumberFormatException var7) {
            throw new RuntimeException("Failed to read time:" + var5 + " in key:" + var2 + " section:" + var1 + " expected a float with optional \'s\' or \'ms\' postfix");
         }
      }
   }

   public Float d(String var1, String var2, Float var3) {
      return this.a(var1, var2, var3, true);
   }

   public static float a(String var0, boolean var1, String var2, String var3) {
      float var4 = 1.0F;
      boolean var5 = false;
      if(var0.endsWith("s")) {
         var0 = var0.substring(0, var0.length() - 1);
         var4 = 60.0F;
         var5 = true;
      } else {
         var4 = 1.0F;
      }

      float var6;
      try {
         var6 = Float.parseFloat(var0) * var4;
      } catch (NumberFormatException var8) {
         throw new RuntimeException("[" + var2 + "]" + var3 + ": Failed to read time:" + var0 + " expected a float with optional \'s\' postfix");
      }

      return var1?(var5?1.0F / var6:var6):var6;
   }

   public float i(String var1, String var2) {
      String var3 = this.a(var1, var2, false, "float");

      try {
         return Float.parseFloat(var3);
      } catch (NumberFormatException var5) {
         throw new RuntimeException("Failed to read float:" + var3 + " in key:" + var2 + " section:" + var1);
      }
   }

   public double j(String var1, String var2) {
      String var3 = this.a(var1, var2, false, "double");

      try {
         return Double.parseDouble(var3);
      } catch (NumberFormatException var5) {
         throw new RuntimeException("Failed to read float:" + var3 + " in key:" + var2 + " section:" + var1);
      }
   }

   public double a(String var1, String var2, double var3) {
      String var5 = this.a(var1, var2, true, "double");
      if(var5 == null) {
         return var3;
      } else {
         try {
            return Double.parseDouble(var5);
         } catch (NumberFormatException var7) {
            throw new RuntimeException("Failed to read float:" + var5 + " in key:" + var2 + " section:" + var1);
         }
      }
   }

   public long a(String var1, String var2, long var3) {
      String var5 = this.a(var1, var2, true, "long");
      if(var5 == null) {
         return var3;
      } else {
         try {
            return Long.parseLong(var5);
         } catch (NumberFormatException var7) {
            throw new RuntimeException("[" + var1 + "]" + var2 + ": Failed to read long:" + var5 + " in key:" + var2 + " section:" + var1);
         }
      }
   }

   public void d(String var1, String var2, String var3) {
      if(this.k) {
         throw new RuntimeException("locked changes");
      } else {
         LinkedHashMap var4 = (LinkedHashMap)this.j.get(var1);
         if(var4 == null) {
            var4 = new LinkedHashMap();
            this.j.put(var1, var4);
         }

         if(var4.get(var2) == null) {
            var4.put(var2, var3);
         }

      }
   }

   public void e(String var1, String var2, String var3) {
      if(this.k) {
         throw new RuntimeException("locked changes");
      } else {
         LinkedHashMap var4 = (LinkedHashMap)this.j.get(var1);
         if(var4 == null) {
            var4 = new LinkedHashMap();
            this.j.put(var1, var4);
         }

         var4.put(var2, var3);
      }
   }

   public void a(ab var1) {
      if(this.k) {
         throw new RuntimeException("locked changes");
      } else {
         Iterator var2 = var1.j.keySet().iterator();

         while(var2.hasNext()) {
            String var3 = (String)var2.next();
            LinkedHashMap var4 = (LinkedHashMap)var1.j.get(var3);
            if(!this.a(var3, "@copyFrom_skipThisSection", Boolean.valueOf(false)).booleanValue()) {
               LinkedHashMap var5 = (LinkedHashMap)this.j.get(var3);
               if(var5 == null) {
                  var5 = new LinkedHashMap();
                  this.j.put(var3, var5);
               }

               Iterator var6 = var4.keySet().iterator();

               while(var6.hasNext()) {
                  String var7 = (String)var6.next();
                  if(var5.get(var7) == null) {
                     var5.put(var7, var4.get(var7));
                  }
               }
            }
         }

      }
   }

   public Rect a(String var1, String var2, Rect var3) {
      String var4 = this.b(var1, var2, (String)null);
      if(var4 == null) {
         return var3;
      } else {
         String[] var5 = var4.split(",");
         if(var5.length != 4) {
            throw new RuntimeException("[" + var1 + "]" + var2 + ": getRect: expected 4 ints, not:" + var5.length);
         } else {
            try {
               return new Rect(Integer.valueOf(var5[0].trim()).intValue(), Integer.valueOf(var5[1].trim()).intValue(), Integer.valueOf(var5[2].trim()).intValue(), Integer.valueOf(var5[3].trim()).intValue());
            } catch (NumberFormatException var7) {
               throw new RuntimeException("[" + var1 + "]" + var2 + ": getRect expected ints got: " + var4);
            }
         }
      }
   }

   public Enum a(String var1, String var2, Enum var3, Class var4) {
      String var5 = this.b(var1, var2, (String)null);

      try {
         return a(var5, var3, var4);
      } catch (bo var7) {
         throw new RuntimeException("[" + var1 + "]" + var2 + ": " + var7.getMessage(), var7);
      }
   }

   public static Enum a(String var0, Enum var1, Class var2) throws bo {
      if(var0 == null) {
         return var1;
      } else {
         var0 = var0.trim();
         Object[] var3 = var2.getEnumConstants();
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            Object var6 = var3[var5];
            Enum var7 = (Enum)var6;
            if(var7.name().equalsIgnoreCase(var0)) {
               return var7;
            }
         }

         throw a(var0, var2);
      }
   }

   private static RuntimeException a(String var0, Class var1) throws bo {
      String var2 = "";
      Object[] var3 = var1.getEnumConstants();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         Object var6 = var3[var5];
         Enum var7 = (Enum)var6;
         if(!var2.equals("")) {
            var2 = var2 + ",";
         }

         var2 = var2 + var7.name();
      }

      throw new bo("Unknown value: " + var0 + " (Expected: " + com.corrodinggames.rts.gameFramework.GameUtils.b(var2, (int)100) + ")");
   }

   public CustomArrayList c(String var1) {
      CustomArrayList var2 = new CustomArrayList();
      Iterator var3 = this.j.keySet().iterator();

      while(var3.hasNext()) {
         String var4 = (String)var3.next();
         Map var5 = (Map)this.j.get(var4);
         if(var5.get(var1) != null) {
            var2.add(var4);
         }
      }

      return var2;
   }

   public CustomArrayList d(String var1) {
      CustomArrayList var2 = new CustomArrayList();
      Iterator var3 = this.j.keySet().iterator();

      while(var3.hasNext()) {
         String var4 = (String)var3.next();
         Map var5 = (Map)this.j.get(var4);
         Iterator var6 = var5.keySet().iterator();

         while(var6.hasNext()) {
            String var7 = (String)var6.next();
            if(var7.startsWith(var1) && !"IGNORE".equals(var5.get(var7))) {
               var2.add(var4);
               break;
            }
         }
      }

      return var2;
   }

   public LinkedHashMap d() {
      return this.j;
   }

   public CustomArrayList k(String var1, String var2) {
      CustomArrayList var3 = new CustomArrayList();
      Map var4 = (Map)this.j.get(var1);
      if(var4 != null) {
         Iterator var5 = var4.keySet().iterator();

         while(var5.hasNext()) {
            String var6 = (String)var5.next();
            if(var6.startsWith(var2) && !"IGNORE".equals(var4.get(var6))) {
               var3.add(var6);
            }
         }
      }

      return var3;
   }

   public CustomArrayList f(String var1, String var2, String var3) {
      CustomArrayList var4 = new CustomArrayList();
      Map var5 = (Map)this.j.get(var1);
      if(var5 != null) {
         Iterator var6 = var5.keySet().iterator();

         while(var6.hasNext()) {
            String var7 = (String)var6.next();
            if(var7.startsWith(var2) || var7.startsWith(var3)) {
               var4.add(var7);
            }
         }
      }

      return var4;
   }

   public boolean l(String var1, String var2) {
      Map var3 = (Map)this.j.get(var1);
      if(var3 != null) {
         Iterator var4 = var3.keySet().iterator();

         while(var4.hasNext()) {
            String var5 = (String)var4.next();
            if(var5.startsWith(var2)) {
               return true;
            }
         }
      }

      return false;
   }

   public CustomArrayList e(String var1) {
      CustomArrayList var2 = new CustomArrayList();
      Set var3 = this.j.keySet();
      Iterator var4 = var3.iterator();

      while(var4.hasNext()) {
         String var5 = (String)var4.next();
         if(var5.startsWith(var1) && this.g(var5)) {
            var2.add(var5);
         }
      }

      return var2;
   }

   public CustomArrayList m(String var1, String var2) {
      CustomArrayList var3 = new CustomArrayList();
      Set var4 = this.j.keySet();
      Iterator var5 = var4.iterator();

      while(var5.hasNext()) {
         String var6 = (String)var5.next();
         if((var6.startsWith(var1) || var6.startsWith(var2)) && this.g(var6)) {
            var3.add(var6);
         }
      }

      return var3;
   }

   public boolean f(String var1) {
      return this.j.get(var1) != null;
   }

   public boolean g(String var1) {
      Map var2 = (Map)this.j.get(var1);
      if(var2 == null) {
         return false;
      } else {
         Iterator var3 = var2.keySet().iterator();

         String var4;
         do {
            if(!var3.hasNext()) {
               return false;
            }

            var4 = (String)var3.next();
         } while(var4 == null || var4.startsWith("@"));

         return true;
      }
   }

   public boolean n(String var1, String var2) {
      String var3 = this.a(var1, var2, true);
      return var3 != null;
   }

   public static boolean g(String var0, String var1, String var2) {
      if(var2.equalsIgnoreCase("true")) {
         return true;
      } else if(var2.equalsIgnoreCase("false")) {
         return false;
      } else {
         throw new RuntimeException("[" + var0 + "]" + var1 + ": Unexpected boolean value:\'" + var2 + "\'");
      }
   }

   public static float h(String var0, String var1, String var2) {
      try {
         return Float.parseFloat(var2);
      } catch (NumberFormatException var4) {
         throw new RuntimeException("[" + var0 + "]" + var1 + ": Failed to read float:" + var2);
      }
   }

   public static int i(String var0, String var1, String var2) {
      try {
         return Integer.parseInt(var2);
      } catch (NumberFormatException var4) {
         throw new RuntimeException("[" + var0 + "]" + var1 + ": Failed to read int:" + var2);
      }
   }

   public static com.corrodinggames.rts.game.units.custom.TeamTag j(String var0, String var1, String var2) throws bo {
      var2 = var2.trim();
      if(var2.contains(",")) {
         throw new bo("[" + var0 + "]" + var1 + ": Unexpected single tag, got:\'" + var2 + "\'");
      } else if(var2.contains("\"")) {
         throw new bo("[" + var0 + "]" + var1 + ": tag cannot contain quote, got:\'" + var2 + "\'");
      } else if(var2.contains("\'")) {
         throw new bo("[" + var0 + "]" + var1 + ": tag cannot contain quote, got:\'" + var2 + "\'");
      } else if(var2.contains(" ")) {
         throw new bo("[" + var0 + "]" + var1 + ": tag cannot contain space, got:\'" + var2 + "\'");
      } else {
         com.corrodinggames.rts.game.units.custom.TeamTag var3 = com.corrodinggames.rts.game.units.custom.TeamTag.parseSingleTag(var2);
         return var3;
      }
   }

}
