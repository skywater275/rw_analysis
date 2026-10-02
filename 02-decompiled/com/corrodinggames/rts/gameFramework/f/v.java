/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.ComponentName
 *  android.content.Intent
 *  android.view.Menu
 *  android.view.MenuItem
 *  android.view.SubMenu
 */
package com.corrodinggames.rts.gameFramework.f;

import android.content.ComponentName;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import com.corrodinggames.rts.gameFramework.f.w;
import java.util.ArrayList;

public class v
implements Menu {
    public ArrayList a = new ArrayList();

    public MenuItem add(CharSequence charSequence) {
        w w2 = new w();
        w2.setTitle(charSequence);
        this.a.add(w2);
        return w2;
    }

    public MenuItem add(int n) {
        throw new RuntimeException("not Implemented");
    }

    public MenuItem add(int n, int n2, int n3, CharSequence charSequence) {
        w w2 = new w();
        w2.setTitle(charSequence);
        w2.a(n2);
        this.a.add(w2);
        return w2;
    }

    public MenuItem add(int n, int n2, int n3, int n4) {
        throw new RuntimeException("not Implemented");
    }

    public int addIntentOptions(int n, int n2, int n3, ComponentName componentName, Intent[] intentArray, Intent intent, int n4, MenuItem[] menuItemArray) {
        throw new RuntimeException("not Implemented");
    }

    public SubMenu addSubMenu(CharSequence charSequence) {
        throw new RuntimeException("not Implemented");
    }

    public SubMenu addSubMenu(int n) {
        throw new RuntimeException("not Implemented");
    }

    public SubMenu addSubMenu(int n, int n2, int n3, CharSequence charSequence) {
        throw new RuntimeException("not Implemented");
    }

    public SubMenu addSubMenu(int n, int n2, int n3, int n4) {
        throw new RuntimeException("not Implemented");
    }

    public void clear() {
        this.a.clear();
    }

    public MenuItem getItem(int n) {
        return (MenuItem)this.a.get(n);
    }

    public void close() {
        throw new RuntimeException("not Implemented");
    }

    public MenuItem findItem(int n) {
        throw new RuntimeException("not Implemented");
    }

    public boolean hasVisibleItems() {
        throw new RuntimeException("not Implemented");
    }

    public boolean isShortcutKey(int n, KeyEvent keyEvent) {
        throw new RuntimeException("not Implemented");
    }

    public boolean performIdentifierAction(int n, int n2) {
        throw new RuntimeException("not Implemented");
    }

    public boolean performShortcut(int n, KeyEvent keyEvent, int n2) {
        throw new RuntimeException("not Implemented");
    }

    public void removeGroup(int n) {
        throw new RuntimeException("not Implemented");
    }

    public void removeItem(int n) {
        throw new RuntimeException("not Implemented");
    }

    public void setGroupCheckable(int n, boolean bl, boolean bl2) {
        throw new RuntimeException("not Implemented");
    }

    public void setGroupEnabled(int n, boolean bl) {
        throw new RuntimeException("not Implemented");
    }

    public void setGroupVisible(int n, boolean bl) {
        throw new RuntimeException("not Implemented");
    }

    public void setQwertyMode(boolean bl) {
        throw new RuntimeException("not Implemented");
    }

    public int size() {
        return this.a.size();
    }
}
