/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket.scripts;

import com.corrodinggames.rts.gameFramework.GameUtils;

public class MainUIController$TableCell {
    public String text;
    public String classes;
    public String librocketOnClick;
    public Integer color;

    public void setLibrocketOnClick(String string) {
        this.librocketOnClick = string;
    }

    public MainUIController$TableCell(String string) {
        this.text = string;
    }

    public void addClass(String string) {
        this.classes = this.classes != null ? this.classes + " " + string : string;
    }

    public boolean same(MainUIController$TableCell root$TableCell, boolean bl) {
        if (!(GameUtils.d(this.classes, root$TableCell.classes) && GameUtils.d(this.librocketOnClick, root$TableCell.librocketOnClick) && GameUtils.a(this.color, root$TableCell.color))) {  // 02b: f.d/f.a
            return false;
        }
        return bl || GameUtils.d(this.text, root$TableCell.text);
    }
}
