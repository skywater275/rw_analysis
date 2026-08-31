# PingAction — 方法目录

**源文件**: `03-deobfuscated\com\corrodinggames\rts\game\units\a\PingAction.java`
**方法总数**: 17

---

## `PingAction` — public PingAction()

- **行号**: 24-26 (3 行)
- **返回**: `public`
- **参数**: ``

### 方法体 (前 10 行)
```java
        this(k.a);
    }

    public PingAction(PingType k2) {
        super("c_6_" + k2.name());
        this.a = k2;
    }

    @Override
    public int b(UnitInstance am2, boolean bl) {
```

---

## `PingAction` — public PingAction(PingType k2)

- **行号**: 28-31 (4 行)
- **返回**: `public`
- **参数**: `PingType k2`

### 字符串常量 (语义锚点)
- `"c_6_"`

### 调用的方法
- `k2.name()`

### 访问的字段
- `a`

### 方法体 (前 10 行)
```java
        super("c_6_" + k2.name());
        this.a = k2;
    }

    @Override
    public int b(UnitInstance am2, boolean bl) {
        return -1;
    }

    @Override
```

---

## `b` — int b(UnitInstance am2, boolean bl)

- **行号**: 34-36 (3 行)
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

    public ar w() {
        return null;
```

---

## `c` — int c()

- **行号**: 39-41 (3 行)
- **返回**: `int`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return 0;
    }

    public ar w() {
        return null;
    }

    @Override
    public u e() {
        return u.j;
```

---

## `w` — ar w()

- **行号**: 43-45 (3 行)
- **返回**: `ar`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return null;
    }

    @Override
    public u e() {
        return u.j;
    }

    @Override
    public t f() {
```

---

## `e` — u e()

- **行号**: 48-50 (3 行)
- **返回**: `u`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return u.j;
    }

    @Override
    public t f() {
        return t.a;
    }

    @Override
    public boolean g() {
```

---

## `f` — t f()

- **行号**: 53-55 (3 行)
- **返回**: `t`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return t.a;
    }

    @Override
    public boolean g() {
        return false;
    }

    @Override
    public String a() {
```

---

## `g` — boolean g()

- **行号**: 58-60 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return false;
    }

    @Override
    public String a() {
        return "Ping Map" + this.a.a();
    }

    @Override
    public String b() {
```

---

## `a` — String a()

- **行号**: 63-65 (3 行)
- **返回**: `String`
- **参数**: ``

### 字符串常量 (语义锚点)
- `"Ping Map"`

### 调用的方法
- `a.a()`

### 访问的字段
- `a`

### 方法体 (前 10 行)
```java
        return "Ping Map" + this.a.a();
    }

    @Override
    public String b() {
        return this.a.b();
    }

    public String K() {
        return this.a.c();
```

---

## `b` — String b()

- **行号**: 68-70 (3 行)
- **返回**: `String`
- **参数**: ``

### 调用的方法
- `a.b()`

### 访问的字段
- `a`

### 方法体 (前 10 行)
```java
        return this.a.b();
    }

    public String K() {
        return this.a.c();
    }

    @Override
    public boolean h_() {
        return false;
```

---

## `K` — String K()

- **行号**: 72-74 (3 行)
- **返回**: `String`
- **参数**: ``

### 调用的方法
- `a.c()`

### 访问的字段
- `a`

### 方法体 (前 10 行)
```java
        return this.a.c();
    }

    @Override
    public boolean h_() {
        return false;
    }

    @Override
    public boolean s() {
```

---

## `h_` — boolean h_()

- **行号**: 77-79 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return false;
    }

    @Override
    public boolean s() {
        return true;
    }

    public static j a(ActionId c2) {
        for (GameAction s2 : b) {
```

---

## `s` — boolean s()

- **行号**: 82-84 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return true;
    }

    public static j a(ActionId c2) {
        for (GameAction s2 : b) {
            if (!s2.d(c2)) continue;
            return (PingAction) s2;
        }
        return null;
    }
```

---

## `a` — j a(ActionId c2)

- **行号**: 86-92 (7 行)
- **返回**: `j`
- **参数**: `ActionId c2`

### 调用的方法
- `s2.d()`

### 方法体 (前 10 行)
```java
        for (GameAction s2 : b) {
            if (!s2.d(c2)) continue;
            return (PingAction) s2;
        }
        return null;
    }

    @Override
    public ArrayList q(UnitInstance am2) {
        return b;
```

---

## `q` — ArrayList q(UnitInstance am2)

- **行号**: 95-97 (3 行)
- **返回**: `ArrayList`
- **参数**: `UnitInstance am2`

### 方法体 (前 10 行)
```java
        return b;
    }

    @Override
    public e j() {
        return com.corrodinggames.rts.gameFramework.d.c.s[9].i;
    }

    @Override
    public Rect v() {
```

---

## `j` — e j()

- **行号**: 100-102 (3 行)
- **返回**: `e`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return com.corrodinggames.rts.gameFramework.d.c.s[9].i;
    }

    @Override
    public Rect v() {
        int n2 = 7 + this.a.ordinal();
        c.a(29 * n2, 0, 29 * n2 + 28, 28);
        return c;
    }

```

---

## `v` — Rect v()

- **行号**: 105-109 (5 行)
- **返回**: `Rect`
- **参数**: ``

### 调用的方法
- `a.ordinal()`
- `c.a()`

### 访问的字段
- `a`

### 方法体 (前 10 行)
```java
        int n2 = 7 + this.a.ordinal();
        c.a(29 * n2, 0, 29 * n2 + 28, 28);
        return c;
    }

    @Override
    public /* synthetic */ as i() {
        return this.w();
    }

```

---
