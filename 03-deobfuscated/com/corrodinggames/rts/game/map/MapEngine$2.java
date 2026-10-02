/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.map;

import com.corrodinggames.rts.game.map.MapEngine;
import java.io.ByteArrayInputStream;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

strictfp class MapEngine$2
implements EntityResolver {
    final /* synthetic */ MapEngine a;

    MapEngine$2(MapEngine b2) {
        this.a = b2;
    }

    @Override
    public InputSource resolveEntity(String string, String string2) {
        return new InputSource(new ByteArrayInputStream(new byte[0]));
    }
}
