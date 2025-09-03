# Carpet Aji Addition

Carpet Aji Addition 顾名思义，他是 [Fabric-Carpet](https://github.com/gnembon/fabric-carpet)的扩展

## 规则列表

### 发光的漏斗矿车（glowingHopperMinecart）

漏斗矿车被锁定时会发出红光，反之白光。

- 分类: CAA , 矿车
- 类型: `boolean`
- 默认值: false
- 参考值: `true`, `false`
- 验证器:
    - 严格(不区分大小写)

### 席地而坐（sitOnTheGround）

多次蹲下可以坐到地上。

- 分类: CAA
- 类型: `boolean`
- 默认值: false
- 参考值: `true`, `false`

### 无能的漏斗（lockAllHopper）

所有漏斗都会被锁定，无论他有没有接受到红石信号。

- 分类: CAA
- 类型: `boolean`
- 默认值: false
- 参考值: `true`, `false`
- 验证器:
    - 严格(不区分大小写)
  
### 无尽宝库（keepOpeningVault）

同一个玩家可以一直开启同一个宝库。

- 分类: CAA
- 类型: `boolean`
- 默认值: false
- 参考值: `true`, `false`
- 验证器:
    - 严格(不区分大小写)

### 无能的漏斗矿车（lockAllHopperMinecart）

所有漏斗矿车都会被锁定，无论他下面是不是充能的激活铁轨。

- 分类: CAA , 矿车
- 类型: `boolean`
- 默认值: false
- 参考值: `true`, `false`
- 验证器:
    - 严格(不区分大小写)

### 图腾扳手（totemOfUndyingWrench）

使用不死图腾调整方块朝向，不产生更新，图腾在副手时，放出来的方块为反方向。

- 分类: CAA
- 类型: `boolean`
- 默认值: false
- 参考值: `true`, `false`
- 验证器:
    - 严格(不区分大小写)