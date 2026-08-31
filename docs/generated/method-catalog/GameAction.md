# GameAction — 方法目录

**源文件**: `03-deobfuscated\com\corrodinggames\rts\game\units\a\GameAction.java`
**方法总数**: 77

---

## `m_` — float m_()

- **行号**: 34-46 (13 行)
- **返回**: `float`
- **参数**: ``

### 调用的方法
- `as2.g()`
- `this.i()`
- `this.sortPriority()`

### 访问的字段
- `i`, `sortPriority`

### 方法体 (前 10 行)
```java
        if (this instanceof RallyPointAction) {
            return -100.0f;
        }
        if (this.sortPriority != -999.0f) {
            return this.sortPriority;
        }
        as as2 = this.i();
        if (as2 != null && this.sortPriority()) {
            return as2.g();
        }
```

---

## `a` — int a(GameAction s2)

- **行号**: 48-60 (13 行)
- **返回**: `int`
- **参数**: `GameAction s2`

### 调用的方法
- `s2.m_()`
- `this.m_()`

### 访问的字段
- `m_`

### 方法体 (前 10 行)
```java
        if (s2 == null) {
            return 0;
        }
        float f = this.m_() - s2.m_();
        if (f < 0.0f) {
            return -1;
        }
        if (f > 0.0f) {
            return 1;
        }
```

---

## `equals` — boolean equals(Object object)

- **行号**: 62-71 (10 行)
- **返回**: `boolean`
- **参数**: `Object object`

### 调用的方法
- `actionName.equals()`
- `object.getClass()`
- `this.getClass()`

### 访问的字段
- `actionName`, `getClass`

### 方法体 (前 10 行)
```java
        if (this == object) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        s s2 = (GameAction) object;
        return this.actionName.equals(s2.a);
    }

```

---

## `b` — boolean b(ActionId c2)

- **行号**: 73-75 (3 行)
- **返回**: `boolean`
- **参数**: `ActionId c2`

### 方法体 (前 10 行)
```java
        return c2 == null || c2 == i;
    }

    public static final boolean c(ActionId c2) {
        return !s.b(c2);
    }

    public static boolean a(GameAction s2, GameAction s3) {
        return s2 == s3;
    }
```

---

## `c` — boolean c(ActionId c2)

- **行号**: 77-79 (3 行)
- **返回**: `boolean`
- **参数**: `ActionId c2`

### 调用的方法
- `s.b()`

### 方法体 (前 10 行)
```java
        return !s.b(c2);
    }

    public static boolean a(GameAction s2, GameAction s3) {
        return s2 == s3;
    }

    public final boolean d(ActionId c2) {
        return this.actionName == c2;
    }
```

---

## `a` — boolean a(GameAction s2, GameAction s3)

- **行号**: 81-83 (3 行)
- **返回**: `boolean`
- **参数**: `GameAction s2, GameAction s3`

### 方法体 (前 10 行)
```java
        return s2 == s3;
    }

    public final boolean d(ActionId c2) {
        return this.actionName == c2;
    }

    public GameAction(int n2) {
        this.actionName(String.valueOf(n2));
    }
```

---

## `d` — boolean d(ActionId c2)

- **行号**: 85-87 (3 行)
- **返回**: `boolean`
- **参数**: `ActionId c2`

### 访问的字段
- `actionName`

### 方法体 (前 10 行)
```java
        return this.actionName == c2;
    }

    public GameAction(int n2) {
        this.actionName(String.valueOf(n2));
    }

    public GameAction(String string) {
        this.actionName(string);
    }
```

---

## `GameAction` — public GameAction(int n2)

- **行号**: 89-91 (3 行)
- **返回**: `public`
- **参数**: `int n2`

### 调用的方法
- `String.valueOf()`
- `this.actionName()`

### 访问的字段
- `actionName`

### 方法体 (前 10 行)
```java
        this.actionName(String.valueOf(n2));
    }

    public GameAction(String string) {
        this.actionName(string);
    }

    public GameAction(ActionId c2) {
        this.isInstantAction(c2);
    }
```

---

## `GameAction` — public GameAction(String string)

- **行号**: 93-95 (3 行)
- **返回**: `public`
- **参数**: `String string`

### 调用的方法
- `this.actionName()`

### 访问的字段
- `actionName`

### 方法体 (前 10 行)
```java
        this.actionName(string);
    }

    public GameAction(ActionId c2) {
        this.isInstantAction(c2);
    }

    public final void a(String string) {
        this.actionName = c.a(string);
    }
```

---

## `GameAction` — public GameAction(ActionId c2)

- **行号**: 97-99 (3 行)
- **返回**: `public`
- **参数**: `ActionId c2`

### 调用的方法
- `this.isInstantAction()`

### 访问的字段
- `isInstantAction`

### 方法体 (前 10 行)
```java
        this.isInstantAction(c2);
    }

    public final void a(String string) {
        this.actionName = c.a(string);
    }

    public final void e(ActionId c2) {
        this.actionName = c2;
    }
```

---

## `a` — void a(String string)

- **行号**: 101-103 (3 行)
- **返回**: `void`
- **参数**: `String string`

### 调用的方法
- `c.a()`

### 访问的字段
- `actionName`

### 方法体 (前 10 行)
```java
        this.actionName = c.a(string);
    }

    public final void e(ActionId c2) {
        this.actionName = c2;
    }

    public final c getActionId() {
        return this.actionName;
    }
```

---

## `e` — void e(ActionId c2)

- **行号**: 105-107 (3 行)
- **返回**: `void`
- **参数**: `ActionId c2`

### 访问的字段
- `actionName`

### 方法体 (前 10 行)
```java
        this.actionName = c2;
    }

    public final c getActionId() {
        return this.actionName;
    }

    public c getActionIdAlias() {
        return this.getActionId();
    }
```

---

## `getActionId` — c getActionId()

- **行号**: 109-111 (3 行)
- **返回**: `c`
- **参数**: ``

### 访问的字段
- `actionName`

### 方法体 (前 10 行)
```java
        return this.actionName;
    }

    public c getActionIdAlias() {
        return this.getActionId();
    }

    public final String getActionIdString() {
        if (this.actionName == null) {
            return "<null index>";
```

---

## `getActionIdAlias` — c getActionIdAlias()

- **行号**: 113-115 (3 行)
- **返回**: `c`
- **参数**: ``

### 调用的方法
- `this.getActionId()`

### 访问的字段
- `getActionId`

### 方法体 (前 10 行)
```java
        return this.getActionId();
    }

    public final String getActionIdString() {
        if (this.actionName == null) {
            return "<null index>";
        }
        return this.actionName.a();
    }

```

---

## `getActionIdString` — String getActionIdString()

- **行号**: 117-122 (6 行)
- **返回**: `String`
- **参数**: ``

### 字符串常量 (语义锚点)
- `"<null index>"`

### 调用的方法
- `actionName.a()`

### 访问的字段
- `actionName`

### 方法体 (前 10 行)
```java
        if (this.actionName == null) {
            return "<null index>";
        }
        return this.actionName.a();
    }

    public abstract String b();

    public abstract String a();

```

---

## `b` — String b()

- **行号**: 124-130 (7 行)
- **返回**: `String`
- **参数**: ``

### 方法体 (前 10 行)
```java

    public abstract String a();

    public h getKeyBinding() {
        return null;
    }

    public String d(UnitInstance am2) {
        return this.actionIcon();
    }
```

---

## `d` — String d(UnitInstance am2)

- **行号**: 132-134 (3 行)
- **返回**: `String`
- **参数**: `UnitInstance am2`

### 调用的方法
- `this.actionIcon()`

### 访问的字段
- `actionIcon`

### 方法体 (前 10 行)
```java
        return this.actionIcon();
    }

    public String e(UnitInstance am2) {
        return this.actionName();
    }

    public abstract int c();

    public b getResourceComponent() {
```

---

## `e` — String e(UnitInstance am2)

- **行号**: 136-138 (3 行)
- **返回**: `String`
- **参数**: `UnitInstance am2`

### 调用的方法
- `this.actionName()`

### 访问的字段
- `actionName`

### 方法体 (前 10 行)
```java
        return this.actionName();
    }

    public abstract int c();

    public b getResourceComponent() {
        b b2 = this.actionModifier.a();
        if (b2 != null) {
            return b2;
        }
```

---

## `c` — int c()

- **行号**: 140-155 (16 行)
- **返回**: `int`
- **参数**: ``

### 调用的方法
- `actionIcon.a()`
- `actionModifier.a()`
- `b.a()`
- `this.actionCooldown()`

### 访问的字段
- `actionCooldown`, `actionIcon`, `actionModifier`

### 方法体 (前 10 行)
```java

    public b getResourceComponent() {
        b b2 = this.actionModifier.a();
        if (b2 != null) {
            return b2;
        }
        int n2 = this.actionCooldown();
        if (n2 == 0) {
            return com.corrodinggames.rts.game.units.custom.d.b.a;
        }
```

---

## `r_` — b r_()

- **行号**: 157-162 (6 行)
- **返回**: `b`
- **参数**: ``

### 调用的方法
- `actionModifier.b()`

### 访问的字段
- `actionModifier`

### 方法体 (前 10 行)
```java
        if (this.actionModifier.b() != null) {
            return this.actionModifier.b();
        }
        return null;
    }

    public abstract int b(UnitInstance var1, boolean var2);

    public boolean n_() {
        return false;
```

---

## `b` — int b(UnitInstance var1, boolean var2)

- **行号**: 164-168 (5 行)
- **返回**: `int`
- **参数**: `UnitInstance var1, boolean var2`

### 方法体 (前 10 行)
```java

    public boolean n_() {
        return false;
    }

    public boolean g(UnitInstance am2) {
        return this.actionModifier.b(am2);
    }

    public String j(UnitInstance am2) {
```

---

## `g` — boolean g(UnitInstance am2)

- **行号**: 170-172 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2`

### 调用的方法
- `actionModifier.b()`

### 访问的字段
- `actionModifier`

### 方法体 (前 10 行)
```java
        return this.actionModifier.b(am2);
    }

    public String j(UnitInstance am2) {
        return this.actionModifier.c(am2);
    }

    public void a(UnitInstance am2, UnitInstance am3) {
        this.actionModifier.a(am2, am3);
    }
```

---

## `j` — String j(UnitInstance am2)

- **行号**: 174-176 (3 行)
- **返回**: `String`
- **参数**: `UnitInstance am2`

### 调用的方法
- `actionModifier.c()`

### 访问的字段
- `actionModifier`

### 方法体 (前 10 行)
```java
        return this.actionModifier.c(am2);
    }

    public void a(UnitInstance am2, UnitInstance am3) {
        this.actionModifier.a(am2, am3);
    }

    public boolean d(UnitInstance am2, boolean bl) {
        return true;
    }
```

---

## `a` — void a(UnitInstance am2, UnitInstance am3)

- **行号**: 178-180 (3 行)
- **返回**: `void`
- **参数**: `UnitInstance am2, UnitInstance am3`

### 调用的方法
- `actionModifier.a()`

### 访问的字段
- `actionModifier`

### 方法体 (前 10 行)
```java
        this.actionModifier.a(am2, am3);
    }

    public boolean d(UnitInstance am2, boolean bl) {
        return true;
    }

    public boolean k(UnitInstance am2) {
        return false;
    }
```

---

## `d` — boolean d(UnitInstance am2, boolean bl)

- **行号**: 182-184 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2, boolean bl`

### 方法体 (前 10 行)
```java
        return true;
    }

    public boolean k(UnitInstance am2) {
        return false;
    }

    public boolean l(UnitInstance am2) {
        return false;
    }
```

---

## `k` — boolean k(UnitInstance am2)

- **行号**: 186-188 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2`

### 方法体 (前 10 行)
```java
        return false;
    }

    public boolean l(UnitInstance am2) {
        return false;
    }

    public boolean a(UnitInstance am2, boolean bl) {
        if (this.sortPriority(am2)) {
            return false;
```

---

## `l` — boolean l(UnitInstance am2)

- **行号**: 190-192 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2`

### 方法体 (前 10 行)
```java
        return false;
    }

    public boolean a(UnitInstance am2, boolean bl) {
        if (this.sortPriority(am2)) {
            return false;
        }
        if (com.corrodinggames.rts.game.units.g.e.a(am2, this.getActionId()) > 0) {
            return false;
        }
```

---

## `a` — boolean a(UnitInstance am2, boolean bl)

- **行号**: 194-205 (12 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2, boolean bl`

### 调用的方法
- `e.a()`
- `this.getActionId()`
- `this.getResourceComponent()`
- `this.hasSubActions()`
- `this.sortPriority()`

### 访问的字段
- `getActionId`, `getResourceComponent`, `hasSubActions`, `sortPriority`

### 方法体 (前 10 行)
```java
        if (this.sortPriority(am2)) {
            return false;
        }
        if (com.corrodinggames.rts.game.units.g.e.a(am2, this.getActionId()) > 0) {
            return false;
        }
        if (bl) {
            return this.getResourceComponent().c(am2, this.hasSubActions());
        }
        return this.getResourceComponent().b(am2);
```

---

## `r` — boolean r(UnitInstance am2)

- **行号**: 207-209 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2`

### 调用的方法
- `this.actionIcon()`

### 访问的字段
- `actionIcon`

### 方法体 (前 10 行)
```java
        return this.actionIcon(am2);
    }

    public boolean u(UnitInstance am2) {
        return this.actionModifier.a(am2);
    }

    public boolean b(UnitInstance am2) {
        return this.actionModifier.a(am2, false);
    }
```

---

## `u` — boolean u(UnitInstance am2)

- **行号**: 211-213 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2`

### 调用的方法
- `actionModifier.a()`

### 访问的字段
- `actionModifier`

### 方法体 (前 10 行)
```java
        return this.actionModifier.a(am2);
    }

    public boolean b(UnitInstance am2) {
        return this.actionModifier.a(am2, false);
    }

    public boolean a(UnitInstance am2, PlayerState n2) {
        return false;
    }
```

---

## `b` — boolean b(UnitInstance am2)

- **行号**: 215-217 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2`

### 调用的方法
- `actionModifier.a()`

### 访问的字段
- `actionModifier`

### 方法体 (前 10 行)
```java
        return this.actionModifier.a(am2, false);
    }

    public boolean a(UnitInstance am2, PlayerState n2) {
        return false;
    }

    public boolean u() {
        return false;
    }
```

---

## `a` — boolean a(UnitInstance am2, PlayerState n2)

- **行号**: 219-221 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2, PlayerState n2`

### 方法体 (前 10 行)
```java
        return false;
    }

    public boolean u() {
        return false;
    }

    public boolean h() {
        return false;
    }
```

---

## `u` — boolean u()

- **行号**: 223-225 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return false;
    }

    public boolean h() {
        return false;
    }

    public boolean isPassiveAction() {
        return false;
    }
```

---

## `h` — boolean h()

- **行号**: 227-229 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return false;
    }

    public boolean isPassiveAction() {
        return false;
    }

    public boolean isActiveAction() {
        return true;
    }
```

---

## `isPassiveAction` — boolean isPassiveAction()

- **行号**: 231-233 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return false;
    }

    public boolean isActiveAction() {
        return true;
    }

    public boolean requiresTargeting() {
        return false;
    }
```

---

## `isActiveAction` — boolean isActiveAction()

- **行号**: 235-237 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return true;
    }

    public boolean requiresTargeting() {
        return false;
    }

    public abstract as i();

    public as y() {
```

---

## `requiresTargeting` — boolean requiresTargeting()

- **行号**: 239-241 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return false;
    }

    public abstract as i();

    public as y() {
        return null;
    }

    public as getBuildUnitType() {
```

---

## `i` — as i()

- **行号**: 243-247 (5 行)
- **返回**: `as`
- **参数**: ``

### 方法体 (前 10 行)
```java

    public as y() {
        return null;
    }

    public as getBuildUnitType() {
        return null;
    }

    public boolean isBuildActionType() {
```

---

## `getBuildUnitType` — as getBuildUnitType()

- **行号**: 249-251 (3 行)
- **返回**: `as`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return null;
    }

    public boolean isBuildActionType() {
        return false;
    }

    public int t() {
        return 1;
    }
```

---

## `isBuildActionType` — boolean isBuildActionType()

- **行号**: 253-255 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return false;
    }

    public int t() {
        return 1;
    }

    public abstract boolean g();

    public abstract u e();
```

---

## `t` — int t()

- **行号**: 257-259 (3 行)
- **返回**: `int`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return 1;
    }

    public abstract boolean g();

    public abstract u e();

    public boolean o() {
        return false;
    }
```

---

## `g` — boolean g()

- **行号**: 261-267 (7 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java

    public abstract u e();

    public boolean o() {
        return false;
    }

    public abstract t f();

    public boolean m(UnitInstance am2) {
```

---

## `f` — t f()

- **行号**: 269-273 (5 行)
- **返回**: `t`
- **参数**: ``

### 方法体 (前 10 行)
```java

    public boolean m(UnitInstance am2) {
        return false;
    }

    public boolean n(UnitInstance am2) {
        return false;
    }

    public e v(UnitInstance am2) {
```

---

## `n` — boolean n(UnitInstance am2)

- **行号**: 275-277 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2`

### 方法体 (前 10 行)
```java
        return false;
    }

    public e v(UnitInstance am2) {
        return null;
    }

    public String d() {
        String string = null;
        l l2 = l.B();
```

---

## `v` — e v(UnitInstance am2)

- **行号**: 279-281 (3 行)
- **返回**: `e`
- **参数**: `UnitInstance am2`

### 方法体 (前 10 行)
```java
        return null;
    }

    public String d() {
        String string = null;
        l l2 = l.B();
        int n2 = 0;
        am[] amArray = l2.bS.bZ.a();
        int n3 = l2.bS.bZ.size();
        for (int i2 = 0; i2 < n3; ++i2) {
```

---

## `d` — String d()

- **行号**: 283-307 (25 行)
- **返回**: `String`
- **参数**: ``

### 调用的方法
- `bZ.a()`
- `bZ.size()`
- `l.B()`
- `this.actionIcon()`
- `this.requiresTarget()`

### 访问的字段
- `actionIcon`, `requiresTarget`

### 方法体 (前 10 行)
```java
        String string = null;
        l l2 = l.B();
        int n2 = 0;
        am[] amArray = l2.bS.bZ.a();
        int n3 = l2.bS.bZ.size();
        for (int i2 = 0; i2 < n3; ++i2) {
            int n4;
            am am2 = amArray[i2];
            if (!(am2 instanceof UnitType)) continue;
            y y2 = (UnitType) am2;
```

---

## `h_` — boolean h_()

- **行号**: 309-311 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return true;
    }

    public String w(UnitInstance am2) {
        return this.requiresTarget(am2);
    }

    public void a(UnitInstance am2, ThemeColors ae2, Paint paint, Paint paint2) {
        b b2;
        int n2;
```

---

## `w` — String w(UnitInstance am2)

- **行号**: 313-315 (3 行)
- **返回**: `String`
- **参数**: `UnitInstance am2`

### 调用的方法
- `this.requiresTarget()`

### 访问的字段
- `requiresTarget`

### 方法体 (前 10 行)
```java
        return this.requiresTarget(am2);
    }

    public void a(UnitInstance am2, ThemeColors ae2, Paint paint, Paint paint2) {
        b b2;
        int n2;
        Object object;
        Paint paint3 = ae2.g;
        if (paint != null) {
            ae2.a(paint);
```

---

## `a` — void a(UnitInstance am2, ThemeColors ae2, Paint paint, Paint paint2)

- **行号**: 317-352 (36 行)
- **返回**: `void`
- **参数**: `UnitInstance am2, ThemeColors ae2, Paint paint, Paint paint2`

### 调用的方法
- `ae2.a()`
- `ae2.b()`
- `b2.a()`
- `b2.c()`
- `b3.a()`
- `b3.c()`
- `paint2.e()`
- `this.f()`
- `this.getResourceComponent()`
- `this.h_()`

### 访问的字段
- `f`, `getResourceComponent`, `h_`, `r_`, `w`

### 方法体 (前 10 行)
```java
        b b2;
        int n2;
        Object object;
        Paint paint3 = ae2.g;
        if (paint != null) {
            ae2.a(paint);
        }
        if (this.h_() && (object = this.w(am2)) != null && !((String)object).equals("")) {
            ae2.b((String)object);
        }
```

---

## `a` — void a(UnitInstance am2, ThemeColors ae2)

- **行号**: 354-365 (12 行)
- **返回**: `void`
- **参数**: `UnitInstance am2, ThemeColors ae2`

### 调用的方法
- `a.a()`
- `ae2.b()`
- `string.trim()`
- `string2.trim()`
- `this.isInstantAction()`

### 访问的字段
- `isInstantAction`

### 方法体 (前 10 行)
```java
        String string;
        String string2 = com.corrodinggames.rts.gameFramework.f.a.a(this, false);
        if (string2 != null && !"".equals(string2)) {
            string2 = string2.trim();
            ae2.b("\n" + string2);
        }
        if ((string = this.isInstantAction(am2)) != null && !"".equals(string)) {
            string = string.trim();
            ae2.b("\n" + string);
        }
```

---

## `c` — boolean c(UnitInstance am2, boolean bl)

- **行号**: 367-369 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2, boolean bl`

### 方法体 (前 10 行)
```java
        return false;
    }

    public void f(UnitInstance am2) {
    }

    public com.corrodinggames.rts.gameFramework.m.e j() {
        if (this.f() == t.c) {
            return l.B().bS.bk;
        }
```

---

## `f` — void f(UnitInstance am2)

- **行号**: 371-372 (2 行)
- **返回**: `void`
- **参数**: `UnitInstance am2`

### 方法体 (前 10 行)
```java
    }

    public com.corrodinggames.rts.gameFramework.m.e j() {
        if (this.f() == t.c) {
            return l.B().bS.bk;
        }
        return null;
    }

    public com.corrodinggames.rts.gameFramework.m.e h(UnitInstance am2) {
```

---

## `j` — com.corrodinggames.rts.gameFramework.m.e j()

- **行号**: 374-379 (6 行)
- **返回**: `com.corrodinggames.rts.gameFramework.m.e`
- **参数**: ``

### 调用的方法
- `l.B()`
- `this.f()`

### 访问的字段
- `f`

### 方法体 (前 10 行)
```java
        if (this.f() == t.c) {
            return l.B().bS.bk;
        }
        return null;
    }

    public com.corrodinggames.rts.gameFramework.m.e h(UnitInstance am2) {
        return null;
    }

```

---

## `h` — com.corrodinggames.rts.gameFramework.m.e h(UnitInstance am2)

- **行号**: 381-383 (3 行)
- **返回**: `com.corrodinggames.rts.gameFramework.m.e`
- **参数**: `UnitInstance am2`

### 方法体 (前 10 行)
```java
        return null;
    }

    public int getColor() {
        return Color.a(100, 255, 255, 255);
    }

    public Rect v() {
        return null;
    }
```

---

## `getColor` — int getColor()

- **行号**: 385-387 (3 行)
- **返回**: `int`
- **参数**: ``

### 调用的方法
- `Color.a()`

### 方法体 (前 10 行)
```java
        return Color.a(100, 255, 255, 255);
    }

    public Rect v() {
        return null;
    }

    public am i(UnitInstance am2) {
        return null;
    }
```

---

## `v` — Rect v()

- **行号**: 389-391 (3 行)
- **返回**: `Rect`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return null;
    }

    public am i(UnitInstance am2) {
        return null;
    }

    public boolean s(UnitInstance am2) {
        return true;
    }
```

---

## `i` — am i(UnitInstance am2)

- **行号**: 393-395 (3 行)
- **返回**: `am`
- **参数**: `UnitInstance am2`

### 方法体 (前 10 行)
```java
        return null;
    }

    public boolean s(UnitInstance am2) {
        return true;
    }

    public boolean t(UnitInstance am2) {
        return true;
    }
```

---

## `s` — boolean s(UnitInstance am2)

- **行号**: 397-399 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2`

### 方法体 (前 10 行)
```java
        return true;
    }

    public boolean t(UnitInstance am2) {
        return true;
    }

    public boolean a(UnitInstance am2) {
        return this.actionModifier.d(am2);
    }
```

---

## `t` — boolean t(UnitInstance am2)

- **行号**: 401-403 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2`

### 方法体 (前 10 行)
```java
        return true;
    }

    public boolean a(UnitInstance am2) {
        return this.actionModifier.d(am2);
    }

    public boolean s() {
        return false;
    }
```

---

## `a` — boolean a(UnitInstance am2)

- **行号**: 405-407 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2`

### 调用的方法
- `actionModifier.d()`

### 访问的字段
- `actionModifier`

### 方法体 (前 10 行)
```java
        return this.actionModifier.d(am2);
    }

    public boolean s() {
        return false;
    }

    public boolean o(UnitInstance am2) {
        return true;
    }
```

---

## `s` — boolean s()

- **行号**: 409-411 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return false;
    }

    public boolean o(UnitInstance am2) {
        return true;
    }

    public boolean isAggregateAction() {
        return false;
    }
```

---

## `o` — boolean o(UnitInstance am2)

- **行号**: 413-415 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2`

### 方法体 (前 10 行)
```java
        return true;
    }

    public boolean isAggregateAction() {
        return false;
    }

    public void c(UnitInstance am2) {
    }

```

---

## `isAggregateAction` — boolean isAggregateAction()

- **行号**: 417-419 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return false;
    }

    public void c(UnitInstance am2) {
    }

    public float l() {
        return 1.0f;
    }

```

---

## `c` — void c(UnitInstance am2)

- **行号**: 421-422 (2 行)
- **返回**: `void`
- **参数**: `UnitInstance am2`

### 方法体 (前 10 行)
```java
    }

    public float l() {
        return 1.0f;
    }

    public int m() {
        return -1;
    }

```

---

## `l` — float l()

- **行号**: 424-426 (3 行)
- **返回**: `float`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return 1.0f;
    }

    public int m() {
        return -1;
    }

    public boolean isChargedAction() {
        return false;
    }
```

---

## `m` — int m()

- **行号**: 428-430 (3 行)
- **返回**: `int`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return -1;
    }

    public boolean isChargedAction() {
        return false;
    }

    public boolean isChargedActionFor() {
        return false;
    }
```

---

## `isChargedAction` — boolean isChargedAction()

- **行号**: 432-434 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return false;
    }

    public boolean isChargedActionFor() {
        return false;
    }

    public boolean x() {
        return false;
    }
```

---

## `isChargedActionFor` — boolean isChargedActionFor()

- **行号**: 436-438 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return false;
    }

    public boolean x() {
        return false;
    }

    public float p(UnitInstance am2) {
        return -1.0f;
    }
```

---

## `x` — boolean x()

- **行号**: 440-442 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return false;
    }

    public float p(UnitInstance am2) {
        return -1.0f;
    }

    public ArrayList q(UnitInstance am2) {
        return null;
    }
```

---

## `p` — float p(UnitInstance am2)

- **行号**: 444-446 (3 行)
- **返回**: `float`
- **参数**: `UnitInstance am2`

### 方法体 (前 10 行)
```java
        return -1.0f;
    }

    public ArrayList q(UnitInstance am2) {
        return null;
    }

    public ad getKeyBindingRef() {
        return null;
    }
```

---

## `q` — ArrayList q(UnitInstance am2)

- **行号**: 448-450 (3 行)
- **返回**: `ArrayList`
- **参数**: `UnitInstance am2`

### 方法体 (前 10 行)
```java
        return null;
    }

    public ad getKeyBindingRef() {
        return null;
    }

    public boolean o_() {
        return false;
    }
```

---

## `getKeyBindingRef` — ad getKeyBindingRef()

- **行号**: 452-454 (3 行)
- **返回**: `ad`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return null;
    }

    public boolean o_() {
        return false;
    }

    public boolean hasSubActions() {
        return false;
    }
```

---

## `o_` — boolean o_()

- **行号**: 456-458 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return false;
    }

    public boolean hasSubActions() {
        return false;
    }

    public void a(UnitType y2) {
    }

```

---

## `hasSubActions` — boolean hasSubActions()

- **行号**: 460-462 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return false;
    }

    public void a(UnitType y2) {
    }

    public boolean a(float f2, float f3) {
        return false;
    }

```

---

## `a` — void a(UnitType y2)

- **行号**: 464-465 (2 行)
- **返回**: `void`
- **参数**: `UnitType y2`

### 方法体 (前 10 行)
```java
    }

    public boolean a(float f2, float f3) {
        return false;
    }

    public boolean p() {
        return false;
    }

```

---

## `a` — boolean a(float f2, float f3)

- **行号**: 467-469 (3 行)
- **返回**: `boolean`
- **参数**: `float f2, float f3`

### 方法体 (前 10 行)
```java
        return false;
    }

    public boolean p() {
        return false;
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.actionName((GameAction) object);
    }
```

---

## `p` — boolean p()

- **行号**: 471-473 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return false;
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.actionName((GameAction) object);
    }
}

```

---
