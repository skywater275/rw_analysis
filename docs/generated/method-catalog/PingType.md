# PingType — 方法目录

**源文件**: `03-deobfuscated\com\corrodinggames\rts\game\units\a\PingType.java`
**方法总数**: 3

---

## `a` — String a()

- **行号**: 22-24 (3 行)
- **返回**: `String`
- **参数**: ``

### 调用的方法
- `this.b()`

### 访问的字段
- `b`

### 方法体 (前 10 行)
```java
        return " - " + this.b();
    }

    public String b() {
        return com.corrodinggames.rts.gameFramework.h.a.a(this.c(), new Object[0]);
    }

    public String c() {
        return "menus.ingame.ping.type." + this.name();
    }
```

---

## `b` — String b()

- **行号**: 26-28 (3 行)
- **返回**: `String`
- **参数**: ``

### 调用的方法
- `a.a()`
- `this.c()`

### 访问的字段
- `c`

### 方法体 (前 10 行)
```java
        return com.corrodinggames.rts.gameFramework.h.a.a(this.c(), new Object[0]);
    }

    public String c() {
        return "menus.ingame.ping.type." + this.name();
    }
}

```

---

## `c` — String c()

- **行号**: 30-32 (3 行)
- **返回**: `String`
- **参数**: ``

### 字符串常量 (语义锚点)
- `"menus.ingame.ping.type."`

### 调用的方法
- `this.name()`

### 访问的字段
- `name`

### 方法体 (前 10 行)
```java
        return "menus.ingame.ping.type." + this.name();
    }
}

```

---
