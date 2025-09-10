# Carpet Aji Addition
## 简介
Carpet Aji Addition 顾名思义，他是 [Fabric-Carpet](https://github.com/gnembon/fabric-carpet)的扩展

默认情况下，他不会改变任何原版规则。

## 问题报告
请在 [Github](https://github.com/aji110905/Carpet-Aji-Addition/issues) 提交问题。

## 适配版本
- 1.21

## 规则列表
### 普通规则
#### glowingHopperMinecart(发光的漏斗矿车)
漏斗矿车被锁定时会发出红光，反之白光。
- 参数类型: boolean
- 默认值: false
- 可选值:
    -  true
    -  false
- 分类
    - CAA 
    - creative
- 存在版本
    - 1.21
#### sitOnTheGround(席地而坐)
多次蹲下可以坐到地上。
- 参数类型: boolean
- 默认值: false
- 可选值:
    -  true
    -  false
- 分类
    - CAA
    - survival
- 存在版本
    - 1.21
#### lockAllHopper(无能的漏斗)
所有漏斗都会被锁定，无论他有没有接受到红石信号。
- 参数类型: boolean
- 默认值: false
- 可选值:
    -  true
    -  false
- 分类
    - CAA
    - feature
- 存在版本
    - 1.21
#### keepOpeningVault(无尽宝库)
同一个玩家可以一直开启同一个宝库。
- 参数类型: boolean
- 默认值: false
- 可选值:
    -  true
    -  false
- 分类
    - CAA
    - survival
    - feature
- 存在版本
    - 1.21
#### lockAllHopperMinecart(无能的漏斗矿车)
所有漏斗矿车都会被锁定，无论他下面是不是充能的激活铁轨。
- 参数类型: boolean
- 默认值: false
- 可选值:
    -  true
    -  false
- 分类
    - CAA
    - feature
- 存在版本
    - 1.21
#### totemOfUndyingWrench(图腾扳手)
使用不死图腾调整方块朝向，不产生更新，图腾在副手时，放出来的方块为反方向。
- 参数类型: boolean
- 默认值: false
- 可选值:
    -  true
    -  false
- 分类
    - CAA
    - feature
- 存在版本
    - 1.21
#### tameHorse(听话的马)
你可以100%驯服马
- 参数类型: boolean
- 默认值: false
- 可选值:
    -  true
    -  false
- 分类
    - CAA
    - survival
    - feature
- 存在版本
    - 1.21
### 配方规则

当配方规则被更改时，游戏会重载所有数据包

一切配方都被添加进合成表中，请在合成表中查看如何合成

配方规则永远都只有CAA和recipe_rule两个分类

#### dragonEggRecipe(可合成龙蛋)
- 参数类型: boolean
- 默认值: false
- 可选值: 
  -  true
  -  false
- 分类
    - CAA
    - recipe
- 存在版本
    - 1.21
#### oreRecipe(矿石逆合成)
- 参数类型: String
- 默认值: null
- 可选值 
  - null
  - ore
  - deepslate
  - nether
  - ore_and_deepslate
  - deepslate_and_nether
  - ore_and_nether
  - ore_and_nether
  - all
- 分类
    - CAA
    - recipe
- 存在版本
    - 1.21
#### dragonBreathRecipe(可合成龙息)
- 参数类型: boolean
- 默认值: false
- 可选值:
    -  true
    -  false
- 分类
    - CAA
    - recipe
- 存在版本
    - 1.21