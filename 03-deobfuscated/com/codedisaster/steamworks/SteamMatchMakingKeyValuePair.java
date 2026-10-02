/*
 * Decompiled with CFR 0.152.
 */
package com.codedisaster.steamworks;

public class SteamMatchMakingKeyValuePair {
    private String key;
    private String value;

    public SteamMatchMakingKeyValuePair() {
    }

    public SteamMatchMakingKeyValuePair(String string, String string2) {
        this.key = string;
        this.value = string2;
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }
}
