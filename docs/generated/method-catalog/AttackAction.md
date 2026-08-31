# AttackAction — 方法目录

**源文件**: `03-deobfuscated\com\corrodinggames\rts\game\units\a\AttackAction.java`
**方法总数**: 17

---

## `AttackAction` — public AttackAction()

- **行号**: 23-25 (3 行)
- **返回**: `public`
- **参数**: ``

### 方法体 (前 10 行)
```java
        super("c_7");
    }

    @Override
    public int b(UnitInstance am2, boolean bl) {
        return -1;
    }

    @Override
    public int c() {
```

---

## `b` — int b(UnitInstance am2, boolean bl)

- **行号**: 28-30 (3 行)
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

    public ar getUnitType() {
        return null;
```

---

## `c` — int c()

- **行号**: 33-35 (3 行)
- **返回**: `int`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return 0;
    }

    public ar getUnitType() {
        return null;
    }

    @Override
    public u e() {
        return u.k;
```

---

## `getUnitType` — ar getUnitType()

- **行号**: 37-39 (3 行)
- **返回**: `ar`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return null;
    }

    @Override
    public u e() {
        return u.k;
    }

    @Override
    public t f() {
```

---

## `e` — u e()

- **行号**: 42-44 (3 行)
- **返回**: `u`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return u.k;
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

- **行号**: 47-49 (3 行)
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

- **行号**: 52-54 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return false;
    }

    @Override
    public String a() {
        return "Attack Mode";
    }

    @Override
    public String b() {
```

---

## `a` — String a()

- **行号**: 57-59 (3 行)
- **返回**: `String`
- **参数**: ``

### 字符串常量 (语义锚点)
- `"Attack Mode"`

### 方法体 (前 10 行)
```java
        return "Attack Mode";
    }

    @Override
    public String b() {
        a a2 = this.getCurrentAttackMode();
        if (a2 != null) {
            return a2.name();
        }
        return "NA";
```

---

## `b` — String b()

- **行号**: 62-68 (7 行)
- **返回**: `String`
- **参数**: ``

### 调用的方法
- `a2.name()`
- `this.getCurrentAttackMode()`

### 访问的字段
- `getCurrentAttackMode`

### 方法体 (前 10 行)
```java
        a a2 = this.getCurrentAttackMode();
        if (a2 != null) {
            return a2.name();
        }
        return "NA";
    }

    @Override
    public boolean h_() {
        return false;
```

---

## `h_` — boolean h_()

- **行号**: 71-73 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return false;
    }

    @Override
    public void c(UnitInstance am2) {
        l l2 = l.B();
        a a2 = this.getAttackModeFromSelection();
        a a3 = this.lastUpdateTick(a2);
        n n2 = null;
        n2 = am2.bX;
```

---

## `c` — void c(UnitInstance am2)

- **行号**: 76-92 (17 行)
- **返回**: `void`
- **参数**: `UnitInstance am2`

### 调用的方法
- `cf.b()`
- `e2.a()`
- `l.B()`
- `this.getAttackModeFromSelection()`
- `this.lastUpdateTick()`

### 访问的字段
- `currentMode`, `getAttackModeFromSelection`, `lastUpdateTick`

### 方法体 (前 10 行)
```java
        l l2 = l.B();
        a a2 = this.getAttackModeFromSelection();
        a a3 = this.lastUpdateTick(a2);
        n n2 = null;
        n2 = am2.bX;
        e e2 = l2.cf.b(n2);
        for (UnitInstance am3 : am.bE) {
            if (!(am3 instanceof UnitType)) continue;
            y y2 = (UnitType) am3;
            if (!y2.cG) continue;
```

---

## `a` — a a(UnitFlag a2)

- **行号**: 94-102 (9 行)
- **返回**: `a`
- **参数**: `UnitFlag a2`

### 方法体 (前 10 行)
```java
        if (a2 == com.corrodinggames.rts.game.units.a.b) {
            return com.corrodinggames.rts.game.units.a.e;
        }
        if (a2 == com.corrodinggames.rts.game.units.a.b) {
            return com.corrodinggames.rts.game.units.a.f;
        }
        return com.corrodinggames.rts.game.units.a.b;
    }

    public a getCurrentAttackMode() {
```

---

## `getCurrentAttackMode` — a getCurrentAttackMode()

- **行号**: 104-110 (7 行)
- **返回**: `a`
- **参数**: ``

### 调用的方法
- `l.B()`
- `this.getAttackModeFromSelection()`

### 访问的字段
- `currentMode`, `getAttackModeFromSelection`, `lastUpdateTick`

### 方法体 (前 10 行)
```java
        l l2 = l.B();
        a a2 = this.getAttackModeFromSelection();
        this.lastUpdateTick = l2.bS.Y;
        this.currentMode = a2;
        return a2;
    }

    public a getAttackModeFromSelection() {
        l l2 = l.B();
        if (this.lastUpdateTick == l2.bS.Y && this.currentMode != null) {
```

---

## `getAttackModeFromSelection` — a getAttackModeFromSelection()

- **行号**: 112-131 (20 行)
- **返回**: `a`
- **参数**: ``

### 调用的方法
- `l.B()`

### 访问的字段
- `currentMode`, `lastUpdateTick`

### 方法体 (前 10 行)
```java
        l l2 = l.B();
        if (this.lastUpdateTick == l2.bS.Y && this.currentMode != null) {
            return this.currentMode;
        }
        a a2 = null;
        boolean bl = false;
        boolean bl2 = false;
        for (UnitInstance am2 : am.bE) {
            if (!(am2 instanceof UnitType)) continue;
            y y2 = (UnitType) am2;
```

---

## `b` — boolean b(UnitInstance am2)

- **行号**: 134-136 (3 行)
- **返回**: `boolean`
- **参数**: `UnitInstance am2`

### 方法体 (前 10 行)
```java
        return true;
    }

    @Override
    public String d() {
        return this.currentMode();
    }

    @Override
    public boolean s() {
```

---

## `d` — String d()

- **行号**: 139-141 (3 行)
- **返回**: `String`
- **参数**: ``

### 调用的方法
- `this.currentMode()`

### 访问的字段
- `currentMode`

### 方法体 (前 10 行)
```java
        return this.currentMode();
    }

    @Override
    public boolean s() {
        return true;
    }

    @Override
    public /* synthetic */ as i() {
```

---

## `s` — boolean s()

- **行号**: 144-146 (3 行)
- **返回**: `boolean`
- **参数**: ``

### 方法体 (前 10 行)
```java
        return true;
    }

    @Override
    public /* synthetic */ as i() {
        return this.getUnitType();
    }
}

```

---
