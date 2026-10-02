package com.corrodinggames.rts.game.units;

// 02b units/r.java 直译: a("grass")/b("sea")/c("sand")/d("dust")
// 抽象 a()=getRidgeTexturePath (ridges/*.tsx), b()=getTerrainTexturePath (terrain/*.tsx)
public enum UnitActionEnum {
    a("grass") {
        @Override
        public String getRidgeTexturePath() {
            return null;
        }

        @Override
        public String getTerrainTexturePath() {
            return "terrain/Long Grass.tsx";
        }
    },
    b("sea") {
        @Override
        public String getRidgeTexturePath() {
            return null;
        }

        @Override
        public String getTerrainTexturePath() {
            return "terrain/Water.tsx";
        }
    },
    c("sand") {
        @Override
        public String getRidgeTexturePath() {
            return "ridges/Sand Nothing - Flat.tsx";
        }

        @Override
        public String getTerrainTexturePath() {
            return "terrain/Sand.tsx";
        }
    },
    d("dust") {
        @Override
        public String getRidgeTexturePath() {
            return "ridges/Nothing Dust - Flat.tsx";
        }

        @Override
        public String getTerrainTexturePath() {
            return "terrain/Dust.tsx";
        }
    };

    private UnitActionEnum(String string) {
    }

    public abstract String getRidgeTexturePath();

    public abstract String getTerrainTexturePath();
}
