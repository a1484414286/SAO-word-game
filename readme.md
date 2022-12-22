# SAO-word-game

## 角色系统

### 基础属性
* HP 血
* MP 蓝
* STR 力量
* AGI 敏捷
* VIT 耐力
* INT 智力
* DEX 灵巧
* LUK 幸运
* EXP 经验
* LVL 等级
* Resist 武器抗性

## 背包系统 Bag system
* generalBag   综合背包
* totalWeight  各个背包总重量
* totalVal  　 各个背包总财富
* totalCount   各个背包总物品总数

### 道具背包
* container 道具背包
* weight　  背包总重量
* val  　   背包总财富
* count     背包总物品总数

### 装备背包
* container 装备背包
* weight　  背包总重量
* val  　   背包总财富
* count     背包总物品总数

### 水晶背包
* container 水晶背包
* weight　  背包总重量
* val  　   背包总财富
* count     背包总物品总数

### 食物背包
* container 食物背包
* weight　  背包总重量
* val  　   背包总财富
* count     背包总物品总数

### 素材背包
* container 素材背包
* weight　  背包总重量
* val  　   背包总财富
* count     背包总物品总数   

### 禁止背包
* container 物品背包
* weight　  背包总重量
* val  　   背包总财富
* count     背包总物品总数

## 道具系统　Item system
* id          编号
* count       数量
* price       价值
* weight      重量
* useValue    使用数值
* durability  耐久
* description 描述

### 食物
* 品质

### 水晶
#### 红水晶
* 回复, 并且增加MAX HP

#### 绿水晶
* 解除所有异常状态

#### 蓝水晶
* 记录/传送已经记录位置

#### 回廊水晶
* 无限耐久传送

### 药水
* 品质

### 素材
* 品质

### 禁止道具
* 跟水晶一类属于特殊道具, 禁止使用回血道具, 传送, 等

## 装备系统
* 描述会抽象化, 如这件装备能小幅增幅xxx, 并且附带副作用

### 防具
* 基础属性
* 锋利度
* 耐久度
* 品质

#### 饰品(归类防具一种)

### 武器
* 基础属性
* 锋利度
* 耐久度
* 品质

#### 长剑 (劈砍)
* 连砍

#### 匕首 (穿刺)
* 低频率攻击, 流血

#### 细剑 (穿刺)
* 弱点攻击 (攻击弱点, 暴击)

#### 锤子 (钝击)
* 破防攻击 (无视防御)

#### 斧子 (劈砍)
* 劈砍 (高攻击蓄力,霸体)

#### 长枪 (穿刺)
* 控制, 范围远 (击飞)

#### 小盾牌 (无)
* 轻便 -> (低到中防御)

#### 大盾牌 (无)
* 厚实 -> (高防御, 降低敏捷, 要求主手是重武器)

#### 暗器 (无)
* 可淬毒 -> 虚弱, 中毒, 麻痹

#### 战锤 (钝击)
* 打断施法(如长剑蓄力)

## 地图系统
* 想象它是一个doubly linked list结构
* 地图(区域->区域->区域->区域->区域)
* 地图是super class, 它包含无数个区域
* 一个区域可以跟最多四个区域链接《分别是 ↑,↓,←,→》
* playerlist 区域玩家
* moblist 区域怪物
* buildings 区域建筑物

### 区域
* id         编号
* name       名字
* forgeShop  锻造屋
* potionShop 药水屋
* restaurant 餐厅
* foodMarket 超商/商店
* mobs       单/多个怪物
* trap       陷阱/宝藏

## 怪物系统 Mob system

## 怪物 Mob
* id           编号
* lvl          等级
* name         名字
* stats        属性
* gears        装备
* moveSets     技能
* dropItems    掉落篮
* weaponResist 武器抗性
* respawnTime  重生时间
* 品质(普通,精英,特殊,层主)

## 技能系统 Move system(TODO)


## 任务系统 Mission system(TODO)


## 配方系统 Recipe system(TODO)


## 天赋树 Talent Tree System (没想好要不要写)->已经移除

## BattleField
* 战斗模式1：玩家将会使用三种类技巧进行战斗: 力量, 速度, 技巧; 简单来说就是石头剪刀布  
* 武器的自带特性: 劈砍(技巧),穿刺(速度),钝击(力量) 也会形成一种互相克制的关系, 相反的使用互补的技巧能增加武器威力

* 战斗模式2：玩家可直接发送攻击, 但攻击伤害-25%