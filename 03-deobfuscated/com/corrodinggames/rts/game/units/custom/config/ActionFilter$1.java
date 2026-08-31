/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.config;

final class ActionFilter$1 {
    int parseIndex = -1;
    int currentChar;
    final /* synthetic */ String expression;

    ActionFilter$1(String string) {
        this.expression = string;
    }

    void advance() {
        this.currentChar = ++this.parseIndex < this.expression.length() ? (int)this.expression.charAt(this.parseIndex) : -1;
    }

    boolean advance(int n) {
        while (this.currentChar == 32) {
            this.advance();
        }
        if (this.currentChar == n) {
            this.advance();
            return true;
        }
        return false;
    }

    double parseDouble() {
        this.advance();
        double d = this.parseAdditive();
        if (this.parseIndex < this.expression.length()) {
            throw new RuntimeException("Unexpected: " + (char)this.currentChar);
        }
        return d;
    }

    double parseAdditive() {
        double d = this.parseMultiplicative();
        while (true) {
            if (this.advance(43)) {
                d += this.parseMultiplicative();
                continue;
            }
            if (!this.advance(45)) break;
            d -= this.parseMultiplicative();
        }
        return d;
    }

    double parseMultiplicative() {
        double d = this.parseUnaryOrPrimary();
        while (true) {
            if (this.advance(42)) {
                d *= this.parseUnaryOrPrimary();
                continue;
            }
            if (this.advance(47)) {
                d /= this.parseUnaryOrPrimary();
                continue;
            }
            if (!this.advance(37)) break;
            d %= this.parseUnaryOrPrimary();
        }
        return d;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    double parseUnaryOrPrimary() {
        double d;
        if (this.advance(43)) {
            return this.parseUnaryOrPrimary();
        }
        if (this.advance(45)) {
            return -this.parseUnaryOrPrimary();
        }
        int n = this.parseIndex;
        if (this.advance(40)) {
            d = this.parseAdditive();
            this.advance(41);
        } else if (this.currentChar >= 48 && this.currentChar <= 57 || this.currentChar == 46) {
            while (this.currentChar >= 48 && this.currentChar <= 57 || this.currentChar == 46) {
                this.advance();
            }
            d = Double.parseDouble(this.expression.substring(n, this.parseIndex));
        } else {
            if (this.currentChar < 97) throw new RuntimeException("Unexpected: " + (char)this.currentChar);
            if (this.currentChar > 122) throw new RuntimeException("Unexpected: " + (char)this.currentChar);
            while (this.currentChar >= 97 && this.currentChar <= 122) {
                this.advance();
            }
            String string = this.expression.substring(n, this.parseIndex);
            d = this.parseUnaryOrPrimary();
            if (string.equals("sqrt")) {
                d = Math.sqrt(d);
            } else if (string.equals("sin")) {
                d = Math.sin(Math.toRadians(d));
            } else if (string.equals("cos")) {
                d = Math.cos(Math.toRadians(d));
            } else if (string.equals("tan")) {
                d = Math.tan(Math.toRadians(d));
            } else {
                if (!string.equals("int")) throw new RuntimeException("Unknown function: " + string);
                d = (int)d;
            }
        }
        if (!this.advance(94)) return d;
        return Math.pow(d, this.parseUnaryOrPrimary());
    }
}
