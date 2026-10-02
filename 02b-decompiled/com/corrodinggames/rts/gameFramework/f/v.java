package com.corrodinggames.rts.gameFramework.f;

import android.content.ComponentName;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import com.corrodinggames.rts.gameFramework.f.w;
import java.util.ArrayList;

public class v implements Menu {

   public ArrayList a = new ArrayList();


   public MenuItem add(CharSequence var1) {
      w var2 = new w();
      var2.setTitle(var1);
      this.a.add(var2);
      return var2;
   }

   public MenuItem add(int var1) {
      throw new RuntimeException("not Implemented");
   }

   public MenuItem add(int var1, int var2, int var3, CharSequence var4) {
      w var5 = new w();
      var5.setTitle(var4);
      var5.a(var2);
      this.a.add(var5);
      return var5;
   }

   public MenuItem add(int var1, int var2, int var3, int var4) {
      throw new RuntimeException("not Implemented");
   }

   public int addIntentOptions(int var1, int var2, int var3, ComponentName var4, Intent[] var5, Intent var6, int var7, MenuItem[] var8) {
      throw new RuntimeException("not Implemented");
   }

   public SubMenu addSubMenu(CharSequence var1) {
      throw new RuntimeException("not Implemented");
   }

   public SubMenu addSubMenu(int var1) {
      throw new RuntimeException("not Implemented");
   }

   public SubMenu addSubMenu(int var1, int var2, int var3, CharSequence var4) {
      throw new RuntimeException("not Implemented");
   }

   public SubMenu addSubMenu(int var1, int var2, int var3, int var4) {
      throw new RuntimeException("not Implemented");
   }

   public void clear() {
      this.a.clear();
   }

   public MenuItem getItem(int var1) {
      return (MenuItem)this.a.get(var1);
   }

   public void close() {
      throw new RuntimeException("not Implemented");
   }

   public MenuItem findItem(int var1) {
      throw new RuntimeException("not Implemented");
   }

   public boolean hasVisibleItems() {
      throw new RuntimeException("not Implemented");
   }

   public boolean isShortcutKey(int var1, KeyEvent var2) {
      throw new RuntimeException("not Implemented");
   }

   public boolean performIdentifierAction(int var1, int var2) {
      throw new RuntimeException("not Implemented");
   }

   public boolean performShortcut(int var1, KeyEvent var2, int var3) {
      throw new RuntimeException("not Implemented");
   }

   public void removeGroup(int var1) {
      throw new RuntimeException("not Implemented");
   }

   public void removeItem(int var1) {
      throw new RuntimeException("not Implemented");
   }

   public void setGroupCheckable(int var1, boolean var2, boolean var3) {
      throw new RuntimeException("not Implemented");
   }

   public void setGroupEnabled(int var1, boolean var2) {
      throw new RuntimeException("not Implemented");
   }

   public void setGroupVisible(int var1, boolean var2) {
      throw new RuntimeException("not Implemented");
   }

   public void setQwertyMode(boolean var1) {
      throw new RuntimeException("not Implemented");
   }

   public int size() {
      return this.a.size();
   }
}
