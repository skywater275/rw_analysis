/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.resources.ResourceComponent;
import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.game.units.custom.CustomVisuals;

public class TagFilter {
    public com.corrodinggames.rts.game.units.custom.UnitConfig tagName;  // 02 铁证: game.h.a:Lcustom/h;
    public com.corrodinggames.rts.game.units.custom.UnitConfig tagValue;  // 02 铁证: game.h.b:Lcustom/h;
    public float matchType;
    public float inclusiveFilter;
    public com.corrodinggames.rts.game.units.custom.resources.CustomActionBase excludeFilter;
    public com.corrodinggames.rts.game.units.custom.resources.CustomActionBase fallback;
    public CustomVisuals isActive;

    public boolean a(UnitInstance am2) {
        if (this.tagValue != null && com.corrodinggames.rts.game.units.custom.TeamTag.deserializeTags(this.tagValue, am2.getStatusEffects())) {
            return false;
        }
        return this.tagName == null || com.corrodinggames.rts.game.units.custom.TeamTag.deserializeTags(this.tagName, am2.getStatusEffects());
    }
}
