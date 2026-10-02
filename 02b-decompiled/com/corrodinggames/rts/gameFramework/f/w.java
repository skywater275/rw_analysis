package com.corrodinggames.rts.gameFramework.f;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem.OnActionExpandListener;
import android.view.MenuItem.OnMenuItemClickListener;

public class w implements MenuItem {

   CharSequence a;
   Drawable b;
   int c;


   public MenuItem setTitle(CharSequence var1) {
      this.a = var1;
      return this;
   }

   public CharSequence getTitle() {
      return this.a;
   }

   public MenuItem setIcon(Drawable var1) {
      this.b = var1;
      return this;
   }

   public MenuItem setIcon(int var1) {
      return this;
   }

   public Drawable getIcon() {
      return this.b;
   }

   public int getItemId() {
      return this.c;
   }

   public MenuItem a(int var1) {
      this.c = var1;
      return this;
   }

   public boolean collapseActionView() {
      throw new RuntimeException("not Implemented");
   }

   public boolean expandActionView() {
      throw new RuntimeException("not Implemented");
   }

   public ActionProvider getActionProvider() {
      throw new RuntimeException("not Implemented");
   }

   public View getActionView() {
      throw new RuntimeException("not Implemented");
   }

   public char getAlphabeticShortcut() {
      throw new RuntimeException("not Implemented");
   }

   public int getGroupId() {
      throw new RuntimeException("not Implemented");
   }

   public Intent getIntent() {
      throw new RuntimeException("not Implemented");
   }

   public ContextMenuInfo getMenuInfo() {
      throw new RuntimeException("not Implemented");
   }

   public char getNumericShortcut() {
      throw new RuntimeException("not Implemented");
   }

   public int getOrder() {
      throw new RuntimeException("not Implemented");
   }

   public SubMenu getSubMenu() {
      throw new RuntimeException("not Implemented");
   }

   public CharSequence getTitleCondensed() {
      throw new RuntimeException("not Implemented");
   }

   public boolean hasSubMenu() {
      throw new RuntimeException("not Implemented");
   }

   public boolean isActionViewExpanded() {
      throw new RuntimeException("not Implemented");
   }

   public boolean isCheckable() {
      throw new RuntimeException("not Implemented");
   }

   public boolean isChecked() {
      throw new RuntimeException("not Implemented");
   }

   public boolean isEnabled() {
      throw new RuntimeException("not Implemented");
   }

   public boolean isVisible() {
      throw new RuntimeException("not Implemented");
   }

   public MenuItem setActionProvider(ActionProvider var1) {
      throw new RuntimeException("not Implemented");
   }

   public MenuItem setActionView(View var1) {
      throw new RuntimeException("not Implemented");
   }

   public MenuItem setActionView(int var1) {
      throw new RuntimeException("not Implemented");
   }

   public MenuItem setAlphabeticShortcut(char var1) {
      throw new RuntimeException("not Implemented");
   }

   public MenuItem setCheckable(boolean var1) {
      throw new RuntimeException("not Implemented");
   }

   public MenuItem setChecked(boolean var1) {
      throw new RuntimeException("not Implemented");
   }

   public MenuItem setEnabled(boolean var1) {
      throw new RuntimeException("not Implemented");
   }

   public MenuItem setIntent(Intent var1) {
      throw new RuntimeException("not Implemented");
   }

   public MenuItem setNumericShortcut(char var1) {
      throw new RuntimeException("not Implemented");
   }

   public MenuItem setOnActionExpandListener(OnActionExpandListener var1) {
      throw new RuntimeException("not Implemented");
   }

   public MenuItem setOnMenuItemClickListener(OnMenuItemClickListener var1) {
      throw new RuntimeException("not Implemented");
   }

   public MenuItem setShortcut(char var1, char var2) {
      throw new RuntimeException("not Implemented");
   }

   public void setShowAsAction(int var1) {
      throw new RuntimeException("not Implemented");
   }

   public MenuItem setShowAsActionFlags(int var1) {
      throw new RuntimeException("not Implemented");
   }

   public MenuItem setTitle(int var1) {
      throw new RuntimeException("not Implemented");
   }

   public MenuItem setTitleCondensed(CharSequence var1) {
      throw new RuntimeException("not Implemented");
   }

   public MenuItem setVisible(boolean var1) {
      throw new RuntimeException("not Implemented");
   }
}
