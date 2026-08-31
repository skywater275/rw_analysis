/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.b;

import com.corrodinggames.rts.game.b.b;
import java.io.ByteArrayInputStream;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

strictfp class b$2
implements EntityResolver {
    final /* synthetic */ b a;

    b$2(b b2) {
        this.a = b2;
    }

    @Override
    public InputSource resolveEntity(String string, String string2) {
        return new InputSource(new ByteArrayInputStream(new byte[0]));
    }
}
