package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareEqualBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareNotEqualBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$JoinerBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$NotBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticValueBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$ArrayContextReader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$DefaultContextReader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$ParameterMapping;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$ParameterMapping$FieldOrMethod;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$VoidContextReader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString$StaticString;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.utility.al;
import com.corrodinggames.rts.gameFramework.utility.m;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogicBooleanLoader {

   public static final boolean TRACE = false;
   static Pattern patternSingleQuote = Pattern.compile("\'(.*)\'");
   static Pattern patternDoubleQuote = Pattern.compile("\"(.*)\"");
   static Pattern patternInteger = Pattern.compile("(-?\\d*)");
   static Pattern patternFloat = Pattern.compile("(-?\\d*\\.\\d*)");
   static final LogicBooleanLoader$LogicBooleanContext defaultContextReader = new LogicBooleanLoader$DefaultContextReader();
   static final LogicBooleanLoader$LogicBooleanContext voidContextReader = new LogicBooleanLoader$VoidContextReader((String)null);
   static final LogicBooleanLoader$LogicBooleanContext voidNumberContextReader = new LogicBooleanLoader$VoidContextReader("Number");
   static final LogicBooleanLoader$LogicBooleanContext voidBoolContextReader = new LogicBooleanLoader$VoidContextReader("Bool");
   static final LogicBooleanLoader$LogicBooleanContext voidArrayContextReader = new LogicBooleanLoader$VoidContextReader("Array element");
   static final LogicBooleanLoader$LogicBooleanContext numberArrayContextReader = new LogicBooleanLoader$ArrayContextReader(LogicBoolean$ReturnType.numberArray);
   static final LogicBooleanLoader$LogicBooleanContext boolArrayContextReader = new LogicBooleanLoader$ArrayContextReader(LogicBoolean$ReturnType.boolArray);
   static final LogicBooleanLoader$LogicBooleanContext unitArrayContextReader = new LogicBooleanLoader$ArrayContextReader(LogicBoolean$ReturnType.unitArray);


   public static boolean isEmptyIgnoringPlusMinus(String var0) {
      for(int var1 = 0; var1 < var0.length(); ++var1) {
         char var2 = var0.charAt(var1);
         if(var2 != 45 && var2 != 43 && var2 != 32) {
            return false;
         }
      }

      return true;
   }

   public static LogicBoolean parseNumberBlock(l var0, String var1) {
      LogicBoolean var2 = parseBooleanBlock(var0, var1, false);
      if(var2 != null && var2.getReturnType() != LogicBoolean$ReturnType.number) {
         throw new RuntimeException("Expected number for: \'" + var1 + "\' got a " + var2.getReturnType() + " type");
      } else {
         return var2;
      }
   }

   public static LogicBoolean parseBooleanBlock(l var0, String var1, boolean var2) {
      int var3 = al.a(var1);
      if(var3 != 0) {
         if(var3 > 0) {
            throw new RuntimeException("Brackets unbalanced for: \'" + var1 + "\'. A \'(\' was not closed.");
         }

         if(var3 < 0) {
            throw new RuntimeException("Brackets unbalanced for: \'" + var1 + "\'. Too many \')\'.");
         }
      }

      var1 = var1.trim();
      var1 = breakOuterLayerBrackets(var1);
      if(var1.length() > 1 && var1.charAt(0) == 45) {
         boolean var4 = false;

         for(int var5 = 1; var5 < var1.length(); ++var5) {
            char var6 = var1.charAt(var5);
            if(var6 != 32) {
               var4 = !Character.isDigit(var6);
            }
         }

         if(var4) {
            var1 = "0" + var1;
         }
      }

      String var25 = var1.toLowerCase(Locale.ROOT);
      String[] var26 = new String[]{"==", "!=", "<=", ">=", "<", ">"};
      String[] var27 = new String[]{"or", "and", "==", "!=", "<=", ">=", "<", ">", "%", "-", "+", "*", "/", "="};
      String[] var7 = var27;
      int var8 = var27.length;

      int var15;
      String var16;
      boolean var17;
      int var18;
      boolean var19;
      String var50;
      for(int var9 = 0; var9 < var8; ++var9) {
         String var10 = var7[var9];
         if(f.c(var25, var10)) {
            boolean var11 = false;
            boolean var12 = false;
            if(var10.equals("and") || var10.equals("or")) {
               var11 = true;
               var12 = true;
            }

            ArrayList var13;
            if(var10.equals("<>")) {
               int var14 = al.a(var1, 0, var26);
               var13 = new ArrayList();
               var13.add(var1.substring(0, var14));
               var15 = var14 + 2;
               if(var15 > var1.length() - 1) {
                  var15 = var1.length() - 1;
               }

               var16 = var1.substring(var14, var15);
               if(!var16.endsWith("=")) {
                  var16 = var16.substring(0, 1);
               }

               var13.add(var1.substring(var14 + var16.length()));
               var10 = var16;
            } else if(var12) {
               var13 = al.a(var1, var10, var11, true);
            } else {
               var13 = al.a(var1, var10, var11, false);
            }

            if(var13.size() != 1) {
               if(var10.equals("=")) {
                  throw new RuntimeException("Unexpected assignment operator: \'=\', use \'==\' for comparison");
               }

               if(!((String)var13.get(0)).equals("") || var13.size() != 2 || !var10.equals("+") && !var10.equals("-")) {
                  ArrayList var38 = new ArrayList();
                  LogicBoolean$JoinerBoolean var41 = LogicBoolean$JoinerBoolean.getNewJoiner(var10);
                  boolean var42 = var41.requireBooleanChildren();
                  if(var42 && (var41 instanceof CompareJoinerBoolean$CompareNotEqualBoolean || var41 instanceof CompareJoinerBoolean$CompareEqualBoolean)) {
                     com.corrodinggames.rts.gameFramework.l.e(var41.type() + " was set to require boolean. Workaround triggered. requireBooleanChildren:" + var41.requireBooleanChildren());
                     var42 = false;
                  }

                  var17 = false;
                  var18 = -1;
                  if(var10.equals("+") || var10.equals("-")) {
                     var19 = false;
                     Iterator var20 = var13.iterator();

                     String var21;
                     while(var20.hasNext()) {
                        var21 = (String)var20.next();
                        boolean var22 = isEmptyIgnoringPlusMinus(var21);
                        if(var22) {
                           var19 = true;
                           break;
                        }
                     }

                     if(var19) {
                        ArrayList var49 = new ArrayList();
                        var21 = "";
                        Iterator var54 = var13.iterator();

                        while(var54.hasNext()) {
                           String var23 = (String)var54.next();
                           if(isEmptyIgnoringPlusMinus(var23)) {
                              var21 = var21 + var23 + var10;
                           } else {
                              if(!var21.equals("")) {
                                 var23 = var21 + var23;
                                 var21 = "";
                              }

                              var49.add(var23);
                           }
                        }

                        if(!var21.equals("")) {
                           throw new RuntimeException("Unexpected empty last element using: " + var10);
                        }

                        var13 = var49;
                     }
                  }

                  if(var13.size() != 1) {
                     Iterator var47 = var13.iterator();

                     while(var47.hasNext()) {
                        var50 = (String)var47.next();
                        ++var18;
                        if(var50.equals("")) {
                           if(var18 == 0) {
                              throw new RuntimeException("Unexpected empty element before: " + var10);
                           }

                           throw new RuntimeException("Unexpected empty element after: " + var10);
                        }

                        LogicBoolean var55;
                        try {
                           var55 = parseBooleanBlock(var0, var50, var42);
                        } catch (BooleanParseException var24) {
                           throw var24;
                        }

                        if(var55 == null) {
                           throw new RuntimeException("null on:\'" + var1 + "\'");
                        }

                        var38.add(var55);
                     }

                     var41.children = (LogicBoolean[])var38.toArray(new LogicBoolean[0]);
                     return var41.validateAndOptimize(var10, "", var1, (LogicBooleanLoader$LogicBooleanContext)null, var2);
                  }
               }
            }
         }
      }

      String var28;
      if(var25.startsWith("not ")) {
         var28 = var1.substring("not ".length());
         LogicBoolean var32 = parseBooleanBlock(var0, var28, true);
         LogicBoolean$NotBoolean var34 = new LogicBoolean$NotBoolean(var32);
         return var34.validateAndOptimize("not", "", var28, (LogicBooleanLoader$LogicBooleanContext)null, var2);
      } else {
         if(var1.length() > 0) {
            var28 = var1;
            if(var1.startsWith("+")) {
               var28 = var1.substring(1).trim();
            }

            var28 = al.c(var28);
            if(f.r(var28)) {
               if(var2) {
                  throw new RuntimeException("Expected a boolean type here, not number: " + var28);
               }

               return LogicBoolean$StaticValueBoolean.getStaticNumber(var28);
            }

            String var29 = f.p(var1);
            if(var29 != null) {
               if(var2) {
                  throw new RuntimeException("Expected a boolean type here, not string: " + var1);
               }

               return new LogicString$StaticString(var29);
            }
         }

         boolean var30 = false;
         boolean var31 = false;
         if(var1.startsWith("self.")) {
            var1 = var1.substring("self.".length());
            var30 = true;
         }

         String[] var33 = al.b(var1, ".", false);
         m var35 = new m();
         LogicBoolean var36 = null;
         String var37 = null;
         LogicBooleanLoader$LogicBooleanContext var40 = defaultContextReader;
         String var39 = null;

         for(var15 = 0; var15 < var33.length; ++var15) {
            if(var39 != null) {
               var16 = var39;
               var39 = null;
            } else {
               var16 = var33[var15];
            }

            String var46;
            if(f.b(var16, '[')) {
               int var43 = al.b(var16, "[", 0);
               if(var43 != -1) {
                  String var48;
                  if(var43 == 0) {
                     var18 = al.b(var16, "]", 0);
                     if(var18 == -1 || var43 >= var18) {
                        throw new RuntimeException("Unexpected use of square brankets:\'" + var16 + "\'");
                     }

                     if(var18 < var16.length() - 1 && var18 > 0) {
                        var48 = var16.substring(var43, var18 + 1);
                        var50 = var16.substring(var18 + 1);
                        var39 = var50;
                        --var15;
                        var16 = var48;
                     }

                     if(var43 != 0 || var18 != var16.length() - 1 || var16.length() < 2) {
                        throw new RuntimeException("Error reading square brankets:\'" + var16 + "\'");
                     }

                     var16 = var16.substring(1, var16.length() - 1);
                     var16 = "get(" + var16 + ")";
                  } else {
                     var46 = var16.substring(0, var43);
                     var48 = var16.substring(var43);
                     var39 = var48;
                     --var15;
                     var16 = var46;
                  }
               }
            }

            if(var16.equalsIgnoreCase("self")) {
               if(var31) {
                  throw new RuntimeException("No field:\'" + var16 + "\' globals");
               }

               if(var33.length == 1) {
                  return UnitReference.selfUnitReference;
               }

               var30 = true;
            } else if(var15 == 0 && var16.equalsIgnoreCase("game")) {
               var31 = true;
            } else {
               var17 = var15 == var33.length - 1;
               var46 = null;
               if(var30) {
                  var46 = "self.";
               }

               if(var31) {
                  var46 = "game.";
               }

               var19 = var2;
               if(!var17) {
                  var19 = false;
               }

               if(var36 != null) {
                  var40 = var36.createContext();
               }

               LogicBoolean var52 = var40.parseNextElementInChain(var46, var0, var16, var19, var1, var37, var36);
               if(var52 == null) {
                  throw new RuntimeException("Null function or field:\'" + var16 + "\'");
               }

               var36 = var52;
               var37 = var16;
               var30 = true;
               var35.add(var52);
            }
         }

         if(var35.size() == 0) {
            throw new RuntimeException("Unknown function:\'" + var1 + "\'");
         } else {
            LogicBoolean var45 = null;

            for(int var51 = var35.a - 1; var51 >= 0; --var51) {
               LogicBoolean var44 = (LogicBoolean)var35.get(var51);
               if(var45 != null) {
                  var44 = var44.setChild(var45);
               }

               var45 = var44;
            }

            LogicBoolean$ReturnType var53 = var45.getReturnType();
            if(var53 == LogicBoolean$ReturnType.voidReturn) {
               var45.throwVoidReturnError(var1);
               throw new RuntimeException("throwVoidReturnError");
            } else if(var2 && var53 != LogicBoolean$ReturnType.bool) {
               throw new BooleanParseException("Function:\'" + var1 + "\' is expected to return a boolean type but it returns type: " + var53);
            } else {
               return var45;
            }
         }
      }
   }

   public static String fixArguments(String var0) {
      String var1 = var0.trim();
      if(var1.equals("")) {
         return "";
      } else if(var1.startsWith("(") && var1.endsWith(")")) {
         var1 = var1.substring(1, var1.length() - 1);
         var1 = var1.trim();
         return var1;
      } else {
         throw new RuntimeException("Failed to parse function arguments:\'" + var1 + "\'");
      }
   }

   public static Matcher match(Pattern var0, String var1) {
      Matcher var2 = var0.matcher(var1);
      return var2.matches()?var2:null;
   }

   public static void setArgumentsWithMapping(LogicBooleanLoader$ParameterMapping var0, Object var1, String var2, l var3, String var4) {
      if(var4 == null) {
         var4 = var1.getClass().getSimpleName();
      }

      ArrayList var5 = new ArrayList();
      if(var2 != null && !"".equals(var2)) {
         ArrayList var6 = al.a(var2, ",", false);
         int var7 = 0;
         boolean var8 = false;

         for(Iterator var9 = var6.iterator(); var9.hasNext(); ++var7) {
            String var10 = (String)var9.next();
            int var13 = al.a(var10, "=");
            String var11;
            String var12;
            if(var13 > 0) {
               var11 = var10.substring(0, var13);
               var12 = var10.substring(var13 + 1);
               var8 = true;
            } else {
               if(var8) {
                  throw new BooleanParseException(var4 + "(): SyntaxError: Cannot use non-keyword arg after keyword arg");
               }

               if(var0.numberOfPositionalParameters == 0) {
                  throw new BooleanParseException(var4 + "(): Function doesn\'t accept any non-keyword arguments.");
               }

               if(var0.numberOfPositionalParameters <= var7) {
                  throw new BooleanParseException(var4 + "(): Too many non-keyword arguments. Only " + var0.numberOfPositionalParameters + " accepted.");
               }

               var11 = null;
               Iterator var14 = var0.parameters.keySet().iterator();

               while(var14.hasNext()) {
                  String var15 = (String)var14.next();
                  LogicBooleanLoader$ParameterMapping$FieldOrMethod var16 = (LogicBooleanLoader$ParameterMapping$FieldOrMethod)var0.parameters.get(var15);
                  if(var16.positionalOffset == var7) {
                     var11 = var15;
                     break;
                  }
               }

               if(var11 == null) {
                  throw new BooleanParseException("Error failed to find non-keyword argument index: " + var7);
               }

               var12 = var10;
            }

            String var21 = var11;
            var11 = var11.trim();
            var11 = var11.toLowerCase(Locale.ROOT);
            if(var5.contains(var11)) {
               throw new BooleanParseException("SyntaxError: Argument \'" + var21 + "\' has been listed more than once");
            }

            var5.add(var11);

            try {
               setArgumentWithMapping(var0, var1, var11, var12, var3);
            } catch (BooleanParseException var17) {
               throw var17;
            }
         }
      }

      Iterator var18 = var0.parameters.keySet().iterator();

      String var19;
      LogicBooleanLoader$ParameterMapping$FieldOrMethod var20;
      do {
         if(!var18.hasNext()) {
            return;
         }

         var19 = (String)var18.next();
         var20 = (LogicBooleanLoader$ParameterMapping$FieldOrMethod)var0.parameters.get(var19);
      } while(!var20.required || var5.contains(var19));

      throw new BooleanParseException(var4 + "(): SyntaxError: Missing required argument: \'" + var19 + "\'");
   }

   public static Object getArgumentTextWithMapping(LogicBooleanLoader$ParameterMapping$FieldOrMethod var0, Object var1) {
      if(var0 != null && var0.field != null) {
         Object var2;
         try {
            var2 = var0.field.get(var1);
         } catch (IllegalArgumentException var4) {
            var4.printStackTrace();
            return "<error>";
         } catch (IllegalAccessException var5) {
            var5.printStackTrace();
            return "<error>";
         }

         return var2 == null?null:var2;
      } else if(var0 != null && var0.method != null) {
         com.corrodinggames.rts.gameFramework.l.e("getArgumentTextWithMapping: method not supported");
         return "<method>";
      } else {
         com.corrodinggames.rts.gameFramework.l.e("getArgumentTextWithMapping: No method or field");
         return "<error>";
      }
   }

   public static void setArgumentWithMapping(LogicBooleanLoader$ParameterMapping var0, Object var1, String var2, String var3, l var4) {
      LogicBooleanLoader$ParameterMapping$FieldOrMethod var5 = (LogicBooleanLoader$ParameterMapping$FieldOrMethod)var0.parameters.get(var2);
      Class var6;
      Object var7;
      String var16;
      if(var5 != null && var5.field != null) {
         var6 = var5.type;
         var7 = convertParameterData(var3, var6, var4, var5.returnType, var2);
         if(var7 == null && var5.required) {
            throw new BooleanParseException("SyntaxError: Cannot set required argument: \'" + var2 + "\' to null");
         } else {
            try {
               var5.field.set(var1, var7);
            } catch (IllegalArgumentException var11) {
               var11.printStackTrace();
               var16 = var11.getMessage();
               if(var16 == null) {
                  throw new BooleanParseException("Error parameter:\'" + var2 + "\' on " + var1.getClass().getSimpleName(), var11);
               } else {
                  var16 = var16.replace("com.corrodinggames.rts.game.units.custom.logicBooleans.", "");
                  var16 = var16.replace("java.lang.", "");
                  throw new BooleanParseException("Error parameter:\'" + var2 + "\': " + var16);
               }
            } catch (IllegalAccessException var12) {
               throw new BooleanParseException("Error setting parameter:\'" + var2 + "\' on " + var1.getClass().getSimpleName(), var12);
            }
         }
      } else if(var5 != null && var5.method != null) {
         var6 = var5.type;
         var7 = convertParameterData(var3, var6, var4, var5.returnType, var2);

         try {
            var5.method.invoke(var1, new Object[]{var7});
         } catch (IllegalArgumentException var13) {
            var13.printStackTrace();
            var16 = var13.getMessage();
            if(var16 == null) {
               throw new BooleanParseException("Error parameter:\'" + var2 + "\' on " + var1.getClass().getSimpleName(), var13);
            } else {
               var16 = var16.replace("com.corrodinggames.rts.game.units.custom.logicBooleans.", "");
               var16 = var16.replace("java.lang.", "");
               throw new BooleanParseException("Error setting parameter:\'" + var2 + "\': " + var16);
            }
         } catch (IllegalAccessException var14) {
            throw new BooleanParseException("Error setting parameter:\'" + var2 + "\' on " + var1.getClass().getSimpleName(), var14);
         } catch (InvocationTargetException var15) {
            Throwable var9 = var15.getCause();
            String var10 = "";
            if(var9 != null) {
               var10 = " - " + var9.getMessage();
            }

            throw new BooleanParseException("Error setting parameter:\'" + var2 + "\' on " + var1.getClass().getSimpleName() + var10, var15);
         }
      } else {
         throw new BooleanParseException("No parameter:\'" + var2 + "\' on " + var1.getClass().getSimpleName() + " (Possible parameters:" + var0.allParametersString + ")");
      }
   }

   public static List getAllFieldsInherited(List var0, Class var1) {
      var0.addAll(Arrays.asList(var1.getFields()));
      return var0;
   }

   public static Object convertParameterData(String var0, Class var1, l var2, LogicBoolean$ReturnType var3, String var4) {
      if(var0 == null) {
         return null;
      } else {
         var0 = var0.trim();
         if(var0.length() == 0) {
            return null;
         } else if(var0.equals("null")) {
            return null;
         } else if(var1 == LogicBoolean.class) {
            LogicBoolean var9 = parseBooleanBlock(var2, var0, false);
            if(var9 != null && var3 != null) {
               LogicBoolean$ReturnType var10 = var9.getReturnType();
               if(var3 != var10) {
                  throw new BooleanParseException("Wrong type. Expected type: \'" + var3 + "\' for dynamic parameter \'" + var4 + "\' instead got type:\'" + var10 + "\' (parsing: " + var0 + ")");
               }
            }

            return var9;
         } else if(f.s(var0)) {
            return !f.c(var0, ".")?(var1 == String.class?var0:Integer.valueOf(Integer.parseInt(var0))):(var1 == String.class?var0:Float.valueOf(Float.parseFloat(var0)));
         } else {
            String var6 = var0.toLowerCase(Locale.ENGLISH);
            if("false".equals(var6)) {
               return Boolean.FALSE;
            } else if("true".equals(var6)) {
               return Boolean.TRUE;
            } else {
               Matcher var5 = match(patternSingleQuote, var0);
               if(var5 != null) {
                  return f.q(var5.group(1));
               } else {
                  var5 = match(patternDoubleQuote, var0);
                  if(var5 != null) {
                     return f.q(var5.group(1));
                  } else {
                     var5 = match(patternInteger, var0);
                     if(var5 != null) {
                        return var1 == String.class?var5.group(1):Integer.valueOf(Integer.parseInt(var5.group(1)));
                     } else {
                        var5 = match(patternFloat, var0);
                        if(var5 != null) {
                           return var1 == String.class?var5.group(1):Float.valueOf(Float.parseFloat(var5.group(1)));
                        } else {
                           String var7 = "null";
                           if(var1 != null) {
                              var7 = "data of " + var1.getSimpleName();
                              if(var1 == String.class) {
                                 var7 = "string";
                              }

                              if(var1 == Float.TYPE) {
                                 var7 = "number";
                              }

                              if(var1 == Integer.TYPE) {
                                 var7 = "integer";
                              }

                              if(var1 == Boolean.TYPE) {
                                 var7 = "boolean";
                              }
                           }

                           String var8 = "Failed to read parameter \'" + var4 + "\' expected non-dynamic " + var7 + " got: " + var0 + "";
                           if(var1 == String.class) {
                              var8 = var8 + " (A quoted string was expected)";
                           }

                           throw new BooleanParseException(var8);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public static String breakOuterLayerBrackets(String var0) {
      if(var0.startsWith("(") && var0.endsWith(")")) {
         int var1 = al.a(var0, (int)0);
         if(var1 == -1) {
            throw new RuntimeException("Brackets unbalanced. Starting \'(\' in \'" + var0 + "\' was not closed.");
         }

         if(var1 == var0.length() - 1) {
            var0 = var0.substring(1, var0.length() - 1);
            var0 = var0.trim();
            var0 = breakOuterLayerBrackets(var0);
         }
      }

      return var0;
   }

}
