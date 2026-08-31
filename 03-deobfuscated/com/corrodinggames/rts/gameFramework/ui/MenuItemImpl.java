/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.Intent
 *  android.graphics.drawable.Drawable
 *  android.view.ActionProvider
 *  android.view.ContextMenu$ContextMenuInfo
 *  android.view.MenuItem
 *  android.view.MenuItem$OnActionExpandListener
 *  android.view.MenuItem$OnMenuItemClickListener
 *  android.view.SubMenu
 *  android.view.View
 */
package com.corrodinggames.rts.gameFramework.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

public class MenuItemImpl
implements MenuItem {
    CharSequence a;
    Drawable b;
    int c;

    public MenuItem setTitle(CharSequence charSequence) {
        this.a = charSequence;
        return this;
    }

    public CharSequence getTitle() {
        return this.a;
    }

    public MenuItem setIcon(Drawable drawable) {
        this.b = drawable;
        return this;
    }

    public MenuItem setIcon(int n) {
        return this;
    }

    public Drawable getIcon() {
        return this.b;
    }

    public int getItemId() {
        return this.c;
    }

    public MenuItem a(int n) {
        this.c = n;
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

    public ContextMenu.ContextMenuInfo getMenuInfo() {
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

    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new RuntimeException("not Implemented");
    }

    public MenuItem setActionView(View view) {
        throw new RuntimeException("not Implemented");
    }

    public MenuItem setActionView(int n) {
        throw new RuntimeException("not Implemented");
    }

    public MenuItem setAlphabeticShortcut(char c) {
        throw new RuntimeException("not Implemented");
    }

    public MenuItem setCheckable(boolean bl) {
        throw new RuntimeException("not Implemented");
    }

    public MenuItem setChecked(boolean bl) {
        throw new RuntimeException("not Implemented");
    }

    public MenuItem setEnabled(boolean bl) {
        throw new RuntimeException("not Implemented");
    }

    public MenuItem setIntent(Intent intent) {
        throw new RuntimeException("not Implemented");
    }

    public MenuItem setNumericShortcut(char c) {
        throw new RuntimeException("not Implemented");
    }

    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new RuntimeException("not Implemented");
    }

    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        throw new RuntimeException("not Implemented");
    }

    public MenuItem setShortcut(char c, char c2) {
        throw new RuntimeException("not Implemented");
    }

    public void setShowAsAction(int n) {
        throw new RuntimeException("not Implemented");
    }

    public MenuItem setShowAsActionFlags(int n) {
        throw new RuntimeException("not Implemented");
    }

    public MenuItem setTitle(int n) {
        throw new RuntimeException("not Implemented");
    }

    public MenuItem setTitleCondensed(CharSequence charSequence) {
        throw new RuntimeException("not Implemented");
    }

    public MenuItem setVisible(boolean bl) {
        throw new RuntimeException("not Implemented");
    }
}
