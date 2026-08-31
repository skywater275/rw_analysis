/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.librocket.scripts;

import com.corrodinggames.rts.gameFramework.f;

public class Root$TableCell {
    public String text;
    public String classes;
    public String librocketOnClick;
    public Integer color;

    public void setLibrocketOnClick(String string) {
        this.librocketOnClick = string;
    }

    public Root$TableCell(String string) {
        this.text = string;
    }

    public void addClass(String string) {
        this.classes = this.classes != null ? this.classes + " " + string : string;
    }

    public boolean same(Root$TableCell root$TableCell, boolean bl) {
        if (!(f.d(this.classes, root$TableCell.classes) && f.d(this.librocketOnClick, root$TableCell.librocketOnClick) && f.a(this.color, root$TableCell.color))) {
            return false;
        }
        return bl || f.d(this.text, root$TableCell.text);
    }
}
