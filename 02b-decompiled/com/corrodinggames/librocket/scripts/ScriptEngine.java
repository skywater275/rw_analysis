package com.corrodinggames.librocket.scripts;

import com.corrodinggames.librocket.b;
import com.corrodinggames.librocket.scripts.Debug;
import com.corrodinggames.librocket.scripts.Mods;
import com.corrodinggames.librocket.scripts.Multiplayer;
import com.corrodinggames.librocket.scripts.Root;
import com.corrodinggames.librocket.scripts.ScriptContext;
import com.corrodinggames.librocket.scripts.ScriptEngine$1;
import com.corrodinggames.librocket.scripts.ScriptEngine$Action;
import com.corrodinggames.librocket.scripts.ScriptEngine$RunnableAction;
import com.corrodinggames.rts.a.a;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.al;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScriptEngine {

   b slickLibRocket;
   private Root root;
   static ScriptEngine scriptEngine;
   public static boolean inDebugScript;
   static boolean mainScriptThreadMarked;
   static ThreadLocal isMainScriptThread = new ScriptEngine$1();
   ArrayList queuedScripts = new ArrayList();
   ArrayList runningScripts = new ArrayList();
   static Throwable scriptError;
   static String scriptErrorMessage;
   HashMap globals = new HashMap();


   public static boolean isStrict() {
      return a.a();
   }

   public static void checkThreadAccess() {
      if(!((Boolean)isMainScriptThread.get()).booleanValue()) {
         l.b("ScriptEngine: thread is not marked as main script thread!");
         l.T();
      }

   }

   public Root getRoot() {
      checkThreadAccess();
      return this.root;
   }

   public Root getRootNoCheck() {
      return this.root;
   }

   public static ScriptEngine getInstance() {
      return scriptEngine;
   }

   public static ScriptEngine createScriptEngine(b var0) {
      if(scriptEngine != null) {
         throw new RuntimeException("scriptEngine already exists");
      } else {
         scriptEngine = new ScriptEngine(var0);
         return scriptEngine;
      }
   }

   private ScriptEngine(b var1) {
      this.slickLibRocket = var1;
      this.root = new Root();
      this.setupScriptContext(this.root);
      this.setGlobalVariable("root", this.root);
      Multiplayer var2 = new Multiplayer(this.root);
      this.setupScriptContext(var2);
      this.setGlobalVariable("multiplayer", var2);
      this.setGlobalVariable("mp", var2);
      this.root.multiplayer = var2;
      Mods var3 = new Mods(this.root);
      this.setupScriptContext(var3);
      this.setGlobalVariable("mods", var3);
      this.root.mods = var3;
      if(a.a()) {
         Debug var4 = new Debug(this.root);
         this.setupScriptContext(var4);
         this.setGlobalVariable("debug", var4);
      }

   }

   public void setupScriptContext(ScriptContext var1) {
      var1.libRocket = this.slickLibRocket;
      var1.guiEngine = com.corrodinggames.librocket.a.a();
      var1.scriptEngine = this;
      Method[] var2 = var1.getClass().getMethods();
      Method[] var3 = var2;
      int var4 = var2.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         Method var6 = var3[var5];
         String var7 = var6.getName();
         if(!var7.equals("wait") && !var7.equals("getClass")) {
            if(var1.methods.get(var7) != null) {
               logError("method: " + var7 + " already exists");
            }

            var1.methods.put(var7, var6);
         }
      }

   }

   public void update(float var1) {
      if(!mainScriptThreadMarked) {
         mainScriptThreadMarked = true;
         isMainScriptThread.set(Boolean.valueOf(true));
      }

      if(this.queuedScripts.size() != 0) {
         ArrayList var2 = this.queuedScripts;
         synchronized(this.queuedScripts) {
            Iterator var3 = this.queuedScripts.iterator();

            while(true) {
               if(!var3.hasNext()) {
                  break;
               }

               ScriptEngine$Action var4 = (ScriptEngine$Action)var3.next();
               if(var4.framesDelay > 0) {
                  --var4.framesDelay;
               } else {
                  this.runningScripts.add(var4);
                  var3.remove();
               }
            }
         }

         Iterator var7 = this.runningScripts.iterator();

         while(var7.hasNext()) {
            ScriptEngine$Action var8 = (ScriptEngine$Action)var7.next();
            var8.run(this);
         }

         this.runningScripts.clear();
      }

      this.root.onFrameUpdate(var1);
   }

   public ScriptEngine$Action addScriptToQueue(String var1, boolean var2) {
      ArrayList var3 = this.queuedScripts;
      synchronized(this.queuedScripts) {
         ScriptEngine$Action var4 = new ScriptEngine$Action();
         var4.script = var1;
         var4.tryToCatchCrash = var2;
         this.queuedScripts.add(var4);
         return var4;
      }
   }

   public ScriptEngine$Action addScriptToQueueIfNotAlreadyQueued(String var1) {
      ArrayList var2 = this.queuedScripts;
      synchronized(this.queuedScripts) {
         Iterator var3 = this.queuedScripts.iterator();

         ScriptEngine$Action var4;
         do {
            if(!var3.hasNext()) {
               return this.addScriptToQueue(var1, false);
            }

            var4 = (ScriptEngine$Action)var3.next();
         } while(!var1.equals(var4.script));

         return null;
      }
   }

   public ScriptEngine$Action addScriptToQueue(String var1) {
      return this.addScriptToQueue(var1, false);
   }

   public ScriptEngine$Action addRunnableToQueue(Runnable var1) {
      ArrayList var2 = this.queuedScripts;
      synchronized(this.queuedScripts) {
         ScriptEngine$RunnableAction var3 = new ScriptEngine$RunnableAction(var1);
         this.queuedScripts.add(var3);
         return var3;
      }
   }

   public void processScript(String var1) {
      if(!"mp.refreshUI()".equals(var1)) {
         System.out.println("ScriptEngine:HandleEvent:" + var1);
      }

      try {
         String[] var2 = al.a(var1, ';');
         String[] var3 = var2;
         int var4 = var2.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            String var6 = var3[var5];
            this.processArg(var6);
         }

      } catch (Exception var7) {
         throwDelayedException("Found error running:" + var1, var7);
         throw new RuntimeException(var7);
      }
   }

   public static void throwDelayedException(String var0, Throwable var1) {
      l.a("throwDelayedException", var1);
      if(scriptError == null) {
         scriptError = var1;
         scriptErrorMessage = var0;
      }

   }

   public void checkForErrors() {
      if(scriptError != null) {
         throw new RuntimeException(scriptErrorMessage, scriptError);
      }
   }

   public Matcher match(String var1, String var2) {
      Pattern var3 = Pattern.compile(var1);
      Matcher var4 = var3.matcher(var2);
      return var4.matches()?var4:null;
   }

   public Object processArg(String var1) {
      var1 = var1.trim();
      if(var1.length() == 0) {
         return null;
      } else if(var1.equals("null")) {
         return null;
      } else {
         Matcher var2 = this.match("\'(.*)\'", var1);
         if(var2 != null) {
            return f.o(var2.group(1));
         } else {
            var2 = this.match("(-?\\d*)", var1);
            if(var2 != null) {
               return Integer.valueOf(Integer.parseInt(var2.group(1)));
            } else {
               var2 = this.match("(-?\\d*\\.\\d*)", var1);
               if(var2 != null) {
                  return Float.valueOf(Float.parseFloat(var2.group(1)));
               } else {
                  var2 = this.match("\\s*([^\\s\"\']*)\\s*=(.*)", var1);
                  if(var2 != null) {
                     String var6 = var2.group(1);
                     String var4 = var2.group(2);
                     System.out.println("processArg: setting: " + var6 + "=" + var4);
                     Object var5 = this.processArg(var4);
                     this.setLocalVariable(var6, var5);
                     return var5;
                  } else {
                     var2 = this.match("\\s*([\\w\\.]+)\\((.*)\\)\\s*", var1);
                     if(var2 != null) {
                        return this.processFunction(var1, var2);
                     } else if("false".equalsIgnoreCase(var1)) {
                        return Boolean.FALSE;
                     } else if("true".equalsIgnoreCase(var1)) {
                        return Boolean.TRUE;
                     } else {
                        Object var3 = this.getScriptVariable(var1, false);
                        if(var3 != null) {
                           return var3;
                        } else {
                           System.out.println("processArg: no variable:" + var1);
                           this.getScriptVariable(var1, true);
                           System.out.println("SlickLibRocket:HandleEvent: failed to match:" + var1);
                           return null;
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public void printMetadata(HashMap var1) {
      if(var1 == null) {
         System.out.println("No metadata");
      } else {
         String var2 = "";

         String var4;
         for(Iterator var3 = var1.keySet().iterator(); var3.hasNext(); var2 = var2 + var4 + ",") {
            var4 = (String)var3.next();
         }

         System.out.println("metadata:" + var2);
      }

   }

   public Object getScriptVariable(String var1, boolean var2) {
      Object var3;
      if(this.slickLibRocket.d() != null) {
         var3 = this.slickLibRocket.d().getMetadata(var1);
         if(var3 != null) {
            return var3;
         }

         if(var2) {
            System.out.println("getScriptVariable: alert");
            this.printMetadata(this.slickLibRocket.d().metadata);
         }
      }

      if(this.slickLibRocket.c() != null) {
         var3 = this.slickLibRocket.c().getMetadata(var1);
         if(var3 != null) {
            return var3;
         }

         if(var2) {
            System.out.println("getScriptVariable: popup");
            this.printMetadata(this.slickLibRocket.c().metadata);
         }
      }

      var3 = this.slickLibRocket.b(var1);
      if(var3 != null) {
         return var3;
      } else {
         if(var2) {
            System.out.println("getScriptVariable: document");
            this.printMetadata(this.slickLibRocket.getActiveDocumentMetadata());
         }

         var3 = this.globals.get(var1);
         if(var3 != null) {
            return var3;
         } else {
            if(var2) {
               System.out.println("getScriptVariable: globals");
               this.printMetadata(this.globals);
            }

            return null;
         }
      }
   }

   public void setLocalVariable(String var1, Object var2) {
      HashMap var3 = this.slickLibRocket.getActiveDocumentMetadata();
      var3.put(var1, var2);
   }

   public void setGlobalVariable(String var1, Object var2) {
      HashMap var3 = this.globals;
      var3.put(var1, var2);
   }

   public Object processFunction(String var1, Matcher var2) {
      l var3 = l.B();
      String var4 = var2.group(1);
      String var5 = var2.group(2);
      String[] var6;
      if(var5.equals("")) {
         var6 = new String[0];
      } else {
         var6 = al.a(var5, ',');
      }

      Object[] var7 = new Object[var6.length];

      for(int var8 = 0; var8 < var7.length; ++var8) {
         var7[var8] = this.processArg(var6[var8]);
      }

      return this.runFunction(var4, var7);
   }

   public Object runFunction(String var1, Object[] var2) {
      String[] var3 = var1.split("\\.");
      Object var4 = this.root;
      if(var3.length > 2) {
         logCritical("Unsupported nameParts: " + var1);
         return null;
      } else {
         if(var3.length == 2) {
            Object var5 = this.getScriptVariable(var3[0], false);
            if(!(var5 instanceof ScriptContext)) {
               logCritical("Could not find context for: " + var1);
               return null;
            }

            var4 = (ScriptContext)var5;
            var1 = var3[1];
         }

         Method var14 = (Method)((ScriptContext)var4).methods.get(var1);
         if(var14 == null) {
            logCritical("Could not find function: " + var1);
            return null;
         } else {
            Class[] var6 = var14.getParameterTypes();
            ArrayList var7 = new ArrayList();
            if(var2.length > var6.length) {
               logCritical("function: " + var1 + " does not accept " + var2.length + " parameters");
            }

            Object var10;
            for(int var8 = 0; var8 < var6.length; ++var8) {
               Class var9 = var6[var8];
               var10 = null;
               if(var8 < var2.length) {
                  var10 = var2[var8];
               }

               if(var10 != null && !var9.isInstance(var10) && var9.equals(Float.class)) {
                  ;
               }

               var7.add(var10);
            }

            try {
               Object var15 = var14.invoke(var4, var7.toArray());
               return var15;
            } catch (IllegalAccessException var11) {
               var11.printStackTrace();
               throw new RuntimeException(var11);
            } catch (IllegalArgumentException var12) {
               l.b("convertedParameters:");
               Iterator var16 = var7.iterator();

               while(var16.hasNext()) {
                  var10 = var16.next();
                  if(var10 == null) {
                     l.b("=null");
                  } else {
                     l.b("=" + var10.getClass().getName());
                  }
               }

               l.b("-----");
               var12.printStackTrace();
               throw new RuntimeException(var12);
            } catch (InvocationTargetException var13) {
               var13.printStackTrace();
               throw new RuntimeException(var13);
            }
         }
      }
   }

   public static void logError(String var0) {
      l.e("ScriptEngine - error: " + var0);
   }

   public static void logCritical(String var0) {
      l.e("ScriptEngine - critical: " + var0);
      if(isStrict()) {
         throw new RuntimeException("ScriptEngine - critical:" + var0);
      }
   }

}
