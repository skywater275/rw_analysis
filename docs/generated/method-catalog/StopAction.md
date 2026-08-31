# StopAction — 方法目录

**源文件**: `03-deobfuscated\com\corrodinggames\rts\game\units\a\StopAction.java`
**方法总数**: 18

---

## `StopAction` — public StopAction(boolean bl)

- **行号**: 22-26 (5 行)
- **返回**: `public`
- **参数**: `boolean bl`

### 访问的字段
- `a`, `g`

### 方法体 (前 10 行)
```java
        super("c_5");
        this.g = -9990.0f;
        this.a = bl;
    }

    @Override
    public int b(UnitInstance am2, boolean bl) {
        return -1;
    }

```

---

## `b` — int b(UnitInstance am2, boolean bl)

- **行号**: 29-31 (3 行)
- **返回**: `int`
- **参数**: `UnitInstance am2, boolean bl`

### 方法体 (前 10 行)
```java
        return -1;
    }

    @Override
    public int c() {
        return 0;
    }

    @Override
    public as i() {
```

---

## `c` — int c()

- **行号**: 34-36 (3 行)
- **返回**: `int`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return 0;
    }

    @Override
    public as i() {
        return null;
    }

    @Override
    public u e() {
```

---

## `i` — as i()

- **行号**: 39-41 (3 行)
- **返回**: `as`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return null;
    }

    @Override
    public u e() {
        return u.i;
    }

    @Override
    public t f() {
```

---

## `e` — u e()

- **行号**: 44-46 (3 行)
- **返回**: `u`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return u.i;
    }

    @Override
    public t f() {
        return t.g;
    }

    @Override
    public boolean g() {
```

---

## `f` — t f()

- **行号**: 49-51 (3 行)
- **返回**: `t`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return t.g;
    }

    @Override
    public boolean g() {
        return false;
    }

    public com.corrodinggames.rts.game.units.y K() {
        l l2 = l.B();
```

---

## `g` — boolean g()

- **行号**: 54-56 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return false;
    }

    public com.corrodinggames.rts.game.units.y K() {
        l l2 = l.B();
        am[] amArray = l2.bS.bZ.a();
        int n2 = l2.bS.bZ.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            am am2 = amArray[i2];
            if (!(am2 instanceof com.corrodinggames.rts.game.units.y)) continue;
```

---

## `K` — com.corrodinggames.rts.game.units.y K()

- **行号**: 58-70 (13 行)
- **返回**: `com.corrodinggames.rts.game.units.y`
- **参数**: ``

### 调用的方法
- `bZ.a()`
- `bZ.size()`
- `l.B()`

### 方法体 (前 10 行)
```java
        l l2 = l.B();
        am[] amArray = l2.bS.bZ.a();
        int n2 = l2.bS.bZ.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            am am2 = amArray[i2];
            if (!(am2 instanceof com.corrodinggames.rts.game.units.y)) continue;
            com.corrodinggames.rts.game.units.y y2 = (com.corrodinggames.rts.game.units.y)am2;
            if (!y2.cG) continue;
            return y2;
        }
```

---

## `L` — boolean L()

- **行号**: 72-82 (11 行)
- **返回**: `boolean`
- **参数**: ``

### 调用的方法
- `l.B()`
- `this.K()`

### 访问的字段
- `K`

### 方法体 (前 10 行)
```java
        l l2 = l.B();
        com.corrodinggames.rts.game.units.y y2 = this.K();
        if (y2 != null) {
            if (y2 instanceof Factory) {
                return true;
            }
            return l2.bs == y2.bX;
        }
        return false;
    }
```

---

## `d` — String d()

- **行号**: 85-101 (17 行)
- **返回**: `String`
- **参数**: ``

### 字符串常量 (语义锚点)
- `"UnitInfo"`
- `"Editor"`

### 调用的方法
- `g.a()`
- `l.B()`
- `this.K()`

### 访问的字段
- `K`, `a`

### 方法体 (前 10 行)
```java
        String string = "UnitInfo";
        l l2 = l.B();
        com.corrodinggames.rts.game.units.y y2 = this.K();
        if (y2 != null) {
            if (y2 instanceof Factory) {
                return "Editor";
            }
            if (!this.a) {
                string = l2.bS.g.a((UnitInstance) y2, false);
            } else {
```

---

## `h_` — boolean h_()

- **行号**: 104-106 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return true;
    }

    @Override
    public String b() {
        return "UnitInfo";
    }

    @Override
    public String d(UnitInstance am2) {
```

---

## `b` — String b()

- **行号**: 109-111 (3 行)
- **返回**: `String`
- **参数**: ``

### 字符串常量 (语义锚点)
- `"UnitInfo"`

### 方法体 (前 10 行)
```java
        return "UnitInfo";
    }

    @Override
    public String d(UnitInstance am2) {
        if (this.a) {
            return "";
        }
        if (am2 != null) {
            return am2.r().e();
```

---

## `d` — String d(UnitInstance am2)

- **行号**: 114-122 (9 行)
- **返回**: `String`
- **参数**: `UnitInstance am2`

### 字符串常量 (语义锚点)
- `"UnitInfo"`

### 调用的方法
- `am2.r()`

### 访问的字段
- `a`

### 方法体 (前 10 行)
```java
        if (this.a) {
            return "";
        }
        if (am2 != null) {
            return am2.r().e();
        }
        return "UnitInfo";
    }

    @Override
```

---

## `s` — boolean s()

- **行号**: 125-130 (6 行)
- **返回**: `boolean`
- **参数**: ``

### 调用的方法
- `this.L()`

### 访问的字段
- `L`, `a`

### 方法体 (前 10 行)
```java
        if (this.a) {
            return !this.L();
        }
        return true;
    }

    @Override
    public boolean u() {
        return !this.a;
    }
```

---

## `u` — boolean u()

- **行号**: 133-135 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 访问的字段
- `a`

### 方法体 (前 10 行)
```java
        return !this.a;
    }

    @Override
    public boolean C() {
        return true;
    }

    @Override
    public String a() {
```

---

## `C` — boolean C()

- **行号**: 138-140 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return true;
    }

    @Override
    public String a() {
        if (this.a) {
            return "";
        }
        com.corrodinggames.rts.game.units.y y2 = this.K();
        if (y2 != null) {
```

---

## `a` — String a()

- **行号**: 143-165 (23 行)
- **返回**: `String`
- **参数**: ``

### 调用的方法
- `a.a()`
- `iOException.printStackTrace()`
- `this.K()`
- `y2.a()`

### 访问的字段
- `K`, `a`

### 方法体 (前 10 行)
```java
        if (this.a) {
            return "";
        }
        com.corrodinggames.rts.game.units.y y2 = this.K();
        if (y2 != null) {
            boolean bl = false;
            String string = com.corrodinggames.rts.gameFramework.f.a.a(y2, false, true, bl);
            boolean bl2 = false;
            if (bl2) {
                f f2 = new DebugPacketBuilder();
```

---

## `G` — boolean G()

- **行号**: 168-170 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return true;
    }
}

```

---
