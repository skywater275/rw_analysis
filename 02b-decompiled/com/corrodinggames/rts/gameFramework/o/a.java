package com.corrodinggames.rts.gameFramework.o;

import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.i.b;

public class a {

   public static a a = new a();


   public static a a() {
      return a;
   }

   public void b() {
      l.e("SteamEngine - blank init");
   }

   public void a(float var1) {}

   public String c() {
      return null;
   }

   public void d() {
      l.e("SteamEngine - disableSteam - already disabled");
   }

   public boolean e() {
      return !this.f();
   }

   public boolean f() {
      return true;
   }

   public void g() {
      l.e("disabledSteam - showInviteDialog");
      l.B().i("steam API not connected");
   }

   public void h() {
      l.e("Steam: alertNotEnabled");
      l var1 = l.B();
      if(var1 != null) {
         var1.i("steam API not connected");
      }

   }

   public void i() {}

   public void j() {}

   public void k() {}

   public void l() {
      l.e("disabledSteam - loadWorkshopMods");
   }

   public void m() {
      l.e("disabledSteam - showWorkshop");
   }

   public void a(b var1) {
      l.e("disabledSteam - showWorkshopMod");
   }

   public void b(b var1) {
      l.e("disabledSteam - publishWorkshopMod");
   }

   public void a(b var1, boolean var2, String var3) {
      l.e("disabledSteam - uploadWorkshopMod");
   }

}
