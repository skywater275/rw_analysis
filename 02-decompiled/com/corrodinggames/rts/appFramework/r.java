/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.appFramework;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class r
implements Comparator {
    Pattern a = Pattern.compile(".*\\((.*)\\).*");

    r() {
    }

    public int a(String string, String string2) {
        Date date = this.a(string);
        Date date2 = this.a(string2);
        if (date == null && date2 == null) {
            return string.compareTo(string2);
        }
        if (date != null && date2 != null) {
            return date2.compareTo(date);
        }
        if (date == null && date2 != null) {
            return -1;
        }
        if (date != null && date2 == null) {
            return 1;
        }
        return 0;
    }

    public Date a(String string) {
        Matcher matcher = this.a.matcher(string);
        if (matcher.matches()) {
            String string2 = matcher.group(1);
            try {
                return new SimpleDateFormat("d MMM yyyy HH.mm.ss", Locale.ENGLISH).parse(string2);
            }
            catch (ParseException parseException) {
                try {
                    return new SimpleDateFormat("d MMM yyyy HH:mm:ss", Locale.ENGLISH).parse(string2);
                }
                catch (ParseException parseException2) {
                    try {
                        return new SimpleDateFormat("d MMM yyyy HH_mm_ss", Locale.ENGLISH).parse(string2);
                    }
                    catch (ParseException parseException3) {
                        try {
                            return new SimpleDateFormat("d MMM yyyy HH-mm-ss", Locale.ENGLISH).parse(string2);
                        }
                        catch (ParseException parseException4) {
                            try {
                                return new SimpleDateFormat("d MMM. yyyy HH.mm.ss", Locale.ENGLISH).parse(string2);
                            }
                            catch (ParseException parseException5) {
                                // empty catch block
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public /* synthetic */ int compare(Object object, Object object2) {
        return this.a((String)object, (String)object2);
    }
}
