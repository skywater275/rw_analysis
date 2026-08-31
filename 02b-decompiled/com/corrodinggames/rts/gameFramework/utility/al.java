package com.corrodinggames.rts.gameFramework.utility;

import java.util.ArrayList;
import java.util.Locale;

public class al {

   public static ArrayList a(String var0, String var1, String var2, boolean var3) {
      int var4 = 0;
      StringBuffer var5 = new StringBuffer();
      ArrayList var6 = new ArrayList();
      char var7 = var1.charAt(0);
      char var8 = var2.charAt(0);
      int var9 = var1.length();
      int var10 = var2.length();
      int var11 = var0.length();

      for(int var12 = 0; var12 < var11; ++var12) {
         char var13 = var0.charAt(var12);
         if(var13 == 40) {
            ++var4;
         } else if(var13 == 41) {
            --var4;
         }

         if(var4 == 0) {
            if(var7 == var13 && (var9 == 1 || var0.indexOf(var1, var12) == var12) && (!var3 || !b(var0, var12 - 1) && !b(var0, var12 + var1.length()))) {
               var6.add(var5.toString());
               var5 = new StringBuffer();
               var12 += var1.length() - 1;
               continue;
            }

            if(var8 == var13 && (var10 == 1 || var0.indexOf(var2, var12) == var12) && (!var3 || !b(var0, var12 - 1) && !b(var0, var12 + var2.length()))) {
               var6.add(var5.toString());
               var5 = new StringBuffer();
               var12 += var2.length() - 1;
               continue;
            }
         }

         var5.append(var13);
      }

      var6.add(var5.toString());
      return var6;
   }

   public static ArrayList a(String var0, String var1, boolean var2, boolean var3) {
      int var4 = 0;
      StringBuffer var5 = new StringBuffer();
      ArrayList var6 = new ArrayList();
      char var7 = var1.charAt(0);
      int var8 = var1.length();
      int var9 = var0.length();
      boolean var10 = false;
      boolean var11 = false;
      boolean var12 = false;
      boolean var13 = false;
      if(var1.equals("-")) {
         var13 = true;
      }

      char var14 = 0;
      char var15 = 0;
      String var16 = var0;
      if(var3) {
         var16 = var0.toLowerCase(Locale.ROOT);
      }

      for(int var17 = 0; var17 < var9; ++var17) {
         char var18 = var16.charAt(var17);
         char var19 = var0.charAt(var17);
         if(var15 != 32) {
            var14 = var15;
         }

         var15 = var18;
         boolean var20 = var10;
         var10 = false;
         if(!var20) {
            if(var18 == 92) {
               var10 = true;
            }

            if(!var12 && var18 == 39) {
               var11 = !var11;
            }

            if(!var11 && var18 == 34) {
               var12 = !var12;
            }
         }

         boolean var21 = var11 || var12;
         if(!var21) {
            if(var18 == 40) {
               ++var4;
            } else if(var18 == 41) {
               --var4;
            }

            if(var4 == 0 && var7 == var18 && (var8 == 1 || var16.indexOf(var1, var17) == var17) && (!var2 || !b(var0, var17 - 1) && !b(var0, var17 + var1.length())) && (!var13 || var14 != 42 && var14 != 47 && var14 != 43)) {
               var6.add(var5.toString());
               var5.setLength(0);
               var17 += var1.length() - 1;
               continue;
            }
         }

         var5.append(var19);
      }

      var6.add(var5.toString());
      return var6;
   }

   public static ArrayList a(String var0, String var1, boolean var2) {
      int var3 = 0;
      char[] var4 = new char[5];
      StringBuffer var5 = new StringBuffer();
      ArrayList var6 = new ArrayList();
      char var7 = var1.charAt(0);
      int var8 = var1.length();
      int var9 = var0.length();

      for(int var10 = 0; var10 < var9; ++var10) {
         char var11 = var0.charAt(var10);
         byte var12 = 0;
         char var13 = 0;
         if(var11 == 40) {
            var12 = 40;
         } else if(var11 == 41) {
            var13 = 40;
         } else if(var11 == 91) {
            var12 = 91;
         } else if(var11 == 93) {
            var13 = 91;
         }

         if(var12 != 0) {
            ++var3;
            if(var3 >= var4.length) {
               int var14 = var4.length;
               int var15 = var14 + 5;
               char[] var16 = new char[var15];
               System.arraycopy(var4, 0, var16, 0, var14);
               var4 = var16;
            }

            var4[var3] = (char)var12;
         } else if(var13 != 0) {
            if(var4[var3] == var13) {
               --var3;
            } else {
               com.corrodinggames.rts.gameFramework.l.e("Bad bracket order: \'" + var0 + "\' at index:" + var10 + " got " + var13 + " type expected: " + var4[var3]);
            }
         }

         if(var3 == 0) {
            boolean var17 = false;
            if(var7 == var11 && (var8 == 1 || var0.indexOf(var1, var10) == var10)) {
               var17 = true;
            }

            if(var17 && (!var2 || !b(var0, var10 - 1) && !b(var0, var10 + var1.length()))) {
               var6.add(var5.toString());
               var5 = new StringBuffer();
               var10 += var1.length() - 1;
               continue;
            }
         }

         var5.append(var11);
      }

      var6.add(var5.toString());
      return var6;
   }

   public static String[] b(String var0, String var1, boolean var2) {
      return !var0.contains(var1)?new String[]{var0}:(String[])a(var0, var1, var2).toArray(new String[0]);
   }

   public static int a(String var0, int var1) {
      char var2 = var0.charAt(var1);
      if(var2 != 40) {
         com.corrodinggames.rts.gameFramework.l.b("getBracketEnd: Did not start on a bracket");
         return -1;
      } else if(var1 + 1 >= var0.length()) {
         return -1;
      } else {
         int var3 = 1;

         for(int var4 = var1 + 1; var4 < var0.length(); ++var4) {
            char var5 = var0.charAt(var4);
            if(var5 == 40) {
               ++var3;
            } else if(var5 == 41) {
               --var3;
            }

            if(var3 == 0) {
               return var4;
            }
         }

         return -1;
      }
   }

   public static int a(String var0) {
      int var1 = 0;
      boolean var2 = false;
      boolean var3 = false;
      boolean var4 = false;
      char[] var5 = var0.toCharArray();
      int var6 = var5.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         char var8 = var5[var7];
         boolean var9 = var2;
         var2 = false;
         if(!var9) {
            if(var8 == 92) {
               var2 = true;
            }

            if(!var4 && var8 == 39) {
               var3 = !var3;
            }

            if(!var3 && var8 == 34) {
               var4 = !var4;
            }
         }

         if(!var3 && !var4) {
            if(var8 == 40) {
               ++var1;
            } else if(var8 == 41) {
               --var1;
            }
         }
      }

      return var1;
   }

   public static int b(String var0) {
      int var1 = 0;

      for(int var2 = 0; var2 < var0.length(); ++var2) {
         char var3 = var0.charAt(var2);
         if(var3 == 40) {
            ++var1;
         } else if(var3 == 41) {
            --var1;
         }
      }

      return var1;
   }

   public static String[] a(String var0, char var1) {
      ArrayList var2 = new ArrayList();
      boolean var3 = false;
      boolean var4 = false;
      boolean var5 = false;
      StringBuilder var6 = new StringBuilder();
      char[] var7 = var0.toCharArray();
      int var8 = var7.length;

      for(int var9 = 0; var9 < var8; ++var9) {
         char var10 = var7[var9];
         boolean var11 = var3;
         var3 = false;
         if(!var11) {
            if(var10 == 92) {
               var3 = true;
            }

            if(!var5 && var10 == 39) {
               var4 = !var4;
            }

            if(!var4 && var10 == 34) {
               var5 = !var5;
            }
         }

         if(var10 == var1 && !var4 && !var5 && !var4) {
            var2.add(var6.toString());
            var6.setLength(0);
         } else {
            var6.append(var10);
         }
      }

      if(var6.length() != 0) {
         var2.add(var6.toString());
         var6.setLength(0);
      }

      return (String[])var2.toArray(new String[0]);
   }

   public static String[] b(String var0, char var1) {
      ArrayList var2 = new ArrayList();
      boolean var3 = false;
      StringBuilder var4 = new StringBuilder();
      char[] var5 = var0.toCharArray();
      int var6 = var5.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         char var8 = var5[var7];
         boolean var9 = var3;
         var3 = false;
         if(!var9) {
            if(var8 == 92) {
               var3 = true;
               continue;
            }

            if(var8 == var1) {
               var2.add(var4.toString());
               var4.setLength(0);
               continue;
            }
         }

         var4.append(var8);
      }

      if(var4.length() != 0) {
         var2.add(var4.toString());
         var4.setLength(0);
      }

      if(var2.size() == 0) {
         var2.add("");
      }

      return (String[])var2.toArray(new String[0]);
   }

   public static String a(String[] var0) {
      StringBuffer var1 = new StringBuffer();
      boolean var2 = true;
      String[] var3 = var0;
      int var4 = var0.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         String var6 = var3[var5];
         if(var2) {
            var2 = false;
         } else {
            var1.append(",");
         }

         if(var6.contains("\\")) {
            var6 = var6.replace("\\", "\\\\");
         }

         if(var6.contains(",")) {
            var6 = var6.replace(",", "\\,");
         }

         var1.append(var6);
      }

      return var1.toString();
   }

   public static int a(String var0, String var1) {
      return a(var0, var1, 0);
   }

   public static int a(String var0, String var1, int var2) {
      int var3 = 0;
      char var4 = var1.charAt(0);
      int var5 = var1.length();

      for(int var6 = var2; var6 < var0.length(); ++var6) {
         char var7 = var0.charAt(var6);
         if(var7 == 40) {
            ++var3;
         } else if(var7 == 41) {
            --var3;
         }

         if(var3 == 0 && var4 == var7 && (var5 == 1 || var0.indexOf(var1, var6) == var6)) {
            return var6;
         }
      }

      return -1;
   }

   public static int b(String var0, String var1, int var2) {
      int var3 = 0;
      char[] var4 = new char[5];
      boolean var5 = false;
      boolean var6 = false;
      boolean var7 = false;
      char var8 = var1.charAt(0);
      int var9 = var1.length();

      for(int var10 = var2; var10 < var0.length(); ++var10) {
         char var11 = var0.charAt(var10);
         boolean var12 = var5;
         var5 = false;
         if(!var12) {
            if(var11 == 92) {
               var5 = true;
            }

            if(!var7 && var11 == 39) {
               var6 = !var6;
            }

            if(!var6 && var11 == 34) {
               var7 = !var7;
            }
         }

         boolean var13 = var6 || var7;
         if(!var13) {
            byte var15 = 0;
            char var16 = 0;
            if(var11 == 40) {
               var15 = 40;
            } else if(var11 == 41) {
               var16 = 40;
            } else if(var11 == 91) {
               var15 = 91;
            } else if(var11 == 93) {
               var16 = 91;
            }

            if(var15 != 0) {
               ++var3;
               if(var3 >= var4.length) {
                  int var17 = var4.length;
                  int var18 = var17 + 5;
                  char[] var19 = new char[var18];
                  System.arraycopy(var4, 0, var19, 0, var17);
                  var4 = var19;
               }

               var4[var3] = (char)var15;
            } else if(var16 != 0) {
               if(var4[var3] == var16) {
                  --var3;
               } else {
                  com.corrodinggames.rts.gameFramework.l.e("Bad bracket order: \'" + var0 + "\' at index:" + var10 + " got " + var16 + " type expected: " + var4[var3]);
               }
            }

            if((var3 == 0 || var3 == 0) && var8 == var11 && (var9 == 1 || var0.indexOf(var1, var10) == var10)) {
               return var10;
            }
         }
      }

      return -1;
   }

   public static boolean b(String var0, int var1) {
      if(var1 >= 0 && var1 < var0.length()) {
         char var2 = var0.charAt(var1);
         return Character.isLetter(var2) || Character.isDigit(var2);
      } else {
         return false;
      }
   }

   public static int a(String var0, String var1, String var2) {
      int var3 = var0.indexOf(var1);
      int var4 = var0.indexOf(var2);
      return var3 == -1?var4:(var4 == -1?var3:(var3 < var4?var3:var4));
   }

   public static int a(String var0, int var1, String[] var2) {
      int var3 = -1;
      String[] var4 = var2;
      int var5 = var2.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         String var7 = var4[var6];
         int var8 = a(var0, var7, var1);
         if(var8 != -1 && (var3 > var8 || var3 == -1)) {
            var3 = var8;
         }
      }

      return var3;
   }

   public static String c(String var0) {
      boolean var1 = false;

      for(int var2 = 0; var2 < var0.length(); ++var2) {
         char var3 = var0.charAt(var2);
         if(var3 == 45) {
            var1 = !var1;
         } else if(var3 != 43 && var3 != 32) {
            if(var1) {
               return "-" + var0.substring(var2);
            }

            if(var2 == 0) {
               return var0;
            }

            return var0.substring(var2);
         }
      }

      return var0;
   }

   public static String[] b(String var0, String var1) {
      int var2 = var0.indexOf(var1);
      if(var2 == -1) {
         return null;
      } else {
         String var3 = var0.substring(0, var2);
         String var4 = var0.substring(var2 + var1.length());
         return new String[]{var3, var4};
      }
   }

   public static String[] c(String var0, String var1) {
      int var2 = b(var0, var1, 0);
      if(var2 == -1) {
         return null;
      } else {
         String var3 = var0.substring(0, var2);
         String var4 = var0.substring(var2 + var1.length());
         return new String[]{var3, var4};
      }
   }

   public static final String d(String var0) {
      return var0 == null?null:(var0.length() < 1?var0.toUpperCase():var0.substring(0, 1).toUpperCase(Locale.ROOT) + var0.substring(1).toLowerCase(Locale.ROOT));
   }

   public static String[] e(String var0) {
      return b(var0, ',');
   }
}
