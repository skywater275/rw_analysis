# ReclaimAction — 方法目录

**源文件**: `03-deobfuscated\com\corrodinggames\rts\game\units\a\ReclaimAction.java`
**方法总数**: 12

---

## `ReclaimAction` — public ReclaimAction(boolean bl)

- **行号**: 19-22 (4 行)
- **返回**: `public`
- **参数**: `boolean bl`

### 访问的字段
- `isBuildingReclaim`

### 方法体 (前 10 行)
```java
        super("c_2");
        this.isBuildingReclaim = bl;
    }

    @Override
    public String a() {
        if (!this.isBuildingReclaim) {
            return com.corrodinggames.rts.gameFramework.h.a.a("gui.actions.reclaimBuildingTarget.description", new Object[0]);
        }
        return com.corrodinggames.rts.gameFramework.h.a.a("gui.actions.reclaimTarget.description", new Object[0]);
```

---

## `a` — String a()

- **行号**: 25-30 (6 行)
- **返回**: `String`
- **参数**: ``

### 字符串常量 (语义锚点)
- `"gui.actions.reclaimBuildingTarget.description"`
- `"gui.actions.reclaimTarget.description"`

### 调用的方法
- `a.a()`

### 访问的字段
- `isBuildingReclaim`

### 方法体 (前 10 行)
```java
        if (!this.isBuildingReclaim) {
            return com.corrodinggames.rts.gameFramework.h.a.a("gui.actions.reclaimBuildingTarget.description", new Object[0]);
        }
        return com.corrodinggames.rts.gameFramework.h.a.a("gui.actions.reclaimTarget.description", new Object[0]);
    }

    @Override
    public String b() {
        if (!this.isBuildingReclaim) {
            return com.corrodinggames.rts.gameFramework.h.a.a("gui.actions.reclaimBuildingTarget", new Object[0]);
```

---

## `b` — String b()

- **行号**: 33-38 (6 行)
- **返回**: `String`
- **参数**: ``

### 字符串常量 (语义锚点)
- `"gui.actions.reclaimBuildingTarget"`
- `"gui.actions.reclaimTarget"`

### 调用的方法
- `a.a()`

### 访问的字段
- `isBuildingReclaim`

### 方法体 (前 10 行)
```java
        if (!this.isBuildingReclaim) {
            return com.corrodinggames.rts.gameFramework.h.a.a("gui.actions.reclaimBuildingTarget", new Object[0]);
        }
        return com.corrodinggames.rts.gameFramework.h.a.a("gui.actions.reclaimTarget", new Object[0]);
    }

    @Override
    public int c() {
        return 0;
    }
```

---

## `c` — int c()

- **行号**: 41-43 (3 行)
- **返回**: `int`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return 0;
    }

    @Override
    public int b(UnitInstance am2, boolean bl) {
        return -1;
    }

    public ar K() {
        return null;
```

---

## `b` — int b(UnitInstance am2, boolean bl)

- **行号**: 46-48 (3 行)
- **返回**: `int`
- **参数**: `UnitInstance am2, boolean bl`

### 方法体 (前 10 行)
```java
        return -1;
    }

    public ar K() {
        return null;
    }

    @Override
    public u e() {
        return u.e;
```

---

## `K` — ar K()

- **行号**: 50-52 (3 行)
- **返回**: `ar`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return null;
    }

    @Override
    public u e() {
        return u.e;
    }

    @Override
    public t f() {
```

---

## `e` — u e()

- **行号**: 55-57 (3 行)
- **返回**: `u`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return u.e;
    }

    @Override
    public t f() {
        return t.f;
    }

    @Override
    public boolean g() {
```

---

## `f` — t f()

- **行号**: 60-62 (3 行)
- **返回**: `t`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return t.f;
    }

    @Override
    public boolean g() {
        return false;
    }

    @Override
    public boolean h() {
```

---

## `g` — boolean g()

- **行号**: 65-67 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return false;
    }

    @Override
    public boolean h() {
        return true;
    }

    @Override
    public boolean o(UnitInstance am2) {
```

---

## `h` — boolean h()

- **行号**: 70-72 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return true;
    }

    @Override
    public boolean o(UnitInstance am2) {
        if (am2 == null) {
            return true;
        }
        if (!this.isBuildingReclaim) {
            return am2.bI();
```

---

## `o` — boolean o(UnitInstance am2)

- **行号**: 75-83 (9 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2`

### 调用的方法
- `am2.bI()`

### 访问的字段
- `isBuildingReclaim`

### 方法体 (前 10 行)
```java
        if (am2 == null) {
            return true;
        }
        if (!this.isBuildingReclaim) {
            return am2.bI();
        }
        return true;
    }

    @Override
```

---

## `l` — float l()

- **行号**: 86-91 (6 行)
- **返回**: `float`
- **参数**: ``

### 方法体 (前 10 行)
```java
        if (!com.corrodinggames.rts.gameFramework.f.g.bP) {
            return 0.6f;
        }
        return 1.0f;
    }

    @Override
    public /* synthetic */ as i() {
        return this.K();
    }
```

---
