/*
 * Decompiled with CFR 0.152.
 * Reconstructed as a genuine enum: javac forbids "extends java.lang.Enum"
 * at source level (the CFR rendering "abstract class ... extends Enum" is
 * uncompilable). Bytecode anchor: javap com.corrodinggames.rts.gameFramework.n.e
 * — real enum, constants a-k, synthetic (String,int) constructor,
 * abstract String a(), static a(String) lookup.
 * The constant bodies correspond to the obfuscated classes n/e$1..n/e$11
 * (aicore DataValue$1..$11), inlined here for compilability.
 */
package com.corrodinggames.rts.gameFramework.aicore;

public enum MissionEvent {
    a {
        @Override
        public String a() {
            return "objective";
        }
    },
    b {
        @Override
        public String a() {
            return "move";
        }
    },
    c {
        @Override
        public String a() {
            return "changeCredits";
        }
    },
    d {
        @Override
        public String a() {
            return "teamTags";
        }
    },
    e {
        @Override
        public String a() {
            return "unitAdd";
        }
    },
    f {
        @Override
        public String a() {
            return "unitRemove";
        }
    },
    g {
        @Override
        public String a() {
            return "mapText";
        }
    },
    h {
        @Override
        public String a() {
            return "moveCamera";
        }
    },
    i {
        @Override
        public String a() {
            return "unitDetect";
        }
    },
    j {
        @Override
        public String a() {
            return "teamTagDetect";
        }
    },
    k {
        @Override
        public String a() {
            return "basic";
        }
    };

    public abstract String a();

    public static MissionEvent a(String string) {
        for (MissionEvent e2 : MissionEvent.values()) {
            if (!e2.a().equalsIgnoreCase(string)) continue;
            return e2;
        }
        return null;
    }
}
