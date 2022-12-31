# SAO-word-game

## Player System 角色系统

### Base Status Class(基础属性)
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
* Weapon Resist 武器抗性

## Bag System(背包系统)
* generalBag  Bag that contains all other "types" of bags(综合背包)
* totalWeight The total weight of individual bags not including general bag(各个背包总重量)
* totalVal  　The total the value of each individual bags not including general bag(各个背包总财富)
* totalCount   各个背包总物品总数

### Item Bag(道具背包)
* container contains different types of item  (道具背包)
* weight　  contains the weight of the bag (背包总重量)
* val  　   represent the value of each individual items in the bag (背包总财富)
* count     the total number of count in this bag 背包总物品总数

### Amour/Weapon Bag(装备背包)
* container contains different types of amour and weapon (装备背包)
* weight　  contains the weight of each item (背包总重量)
* val  　   contains the value of each item (背包总财富)
* count     represents the count of each individual item (背包总物品总数)

### Crystal Bag (水晶背包)
* container contains different types of crystal (水晶背包)
* weight　  represent the weight of crystals (背包总重量)
* val  　   represent the value of each crystals (背包总财富)
* count     the total count of items in the bag (背包总物品总数)

### Food Bag(食物背包)
* container contains food items (食物背包)
* weight　  the weight of the bag (背包总重量)
* val  　   the value of the items combined(背包总财富)
* count     the item count(背包总物品总数)

### Materials Bag(素材背包)
* container contains food items (素材背包)
* weight　  the weight of the bag (背包总重量)
* val  　   the value of the items combined(背包总财富)
* count     the item count(背包总物品总数)

### Anti-Item Bag (禁止背包)
* container contains food items (禁止背包)
* weight　  the weight of the bag (背包总重量)
* val  　   the value of the items combined(背包总财富)
* count     the item count(背包总物品总数)

## 道具系统　Item system
* it implemented visitor and iterator pattern 
* id          编号
* count       数量
* price       价值
* weight      重量
* useValue    使用数值
* durability  耐久
* description 描述

### Food (食物)
* Quality(品质)

### Crystals 水晶

#### Red Crystal 红水晶
* Regenerate Health
* 回复, 并且增加MAX HP

#### Green Crystal 绿水晶
* Cures all abnormal states 
* 解除所有异常状态

#### Blue Crystal 蓝水晶
* Teleport/Save Previous Spot
* 记录/传送已经记录位置

#### Cloister Crystal 回廊水晶
* Infinite Durability for Teleportation
* 无限耐久传送

### Potion 药水
* Quality
* 品质

### Materials 素材
* Quality
* 品质

### Anti-Item 禁止道具
* Anti-Items, uses in certain stage, where players can stop others from teleportation, regenerate health, etc.
* 跟水晶一类属于特殊道具, 禁止使用回血道具, 传送, 等

## Gear System 装备系统
* The gear(armor, weapon) description will be abstract, for instance, this weapon would increase player's strength, but would decrease the speed of 
* 描述会抽象化, 如这件装备能小幅增幅xxx, 并且附带副作用

### Armour(防具)
* Base Stats 基础属性
* Durability 耐久度
* Quality 品质

#### 饰品(归类防具一种)

### Weapon(武器)

* Base Stats 基础属性
* Sharpness 锋利度
* Durability 耐久度
* Quality 品质

#### Long Sword 长剑 (劈砍)
* Multi-Strike passive skill 连砍

#### Dagger 匕首 (穿刺)
* Bleed passive skill 低频率攻击, 流血

#### Rapier 细剑 (穿刺)
* Weakness penetrate 弱点攻击 (攻击弱点, 暴击)

#### Hammer 锤子 (钝击)
* Defense penetrate 破防攻击 (无视防御)

#### Axe 斧子 (劈砍)
* Endure and waits for X seconds 劈砍 (高攻击蓄力,霸体)

<!-- #### 长枪 (穿刺) -->
<!-- * 控制, 范围远 (击飞) -->

#### 小盾牌 (无)
* Light 轻便 -> (低到中防御)

#### 大盾牌 (无)
* Solid 厚实 -> (高防御, 降低敏捷, 要求主手是重武器)

#### 暗器 (无)
* 可淬毒 -> 虚弱, 中毒, 麻痹

#### 战锤 (钝击)
* 打断施法(如长剑蓄力)

## Map System 地图系统
* Imagine a map that contains stages and stages are structured like doubly linked list, they're linked to each
* 想象它是一个doubly linked list结构
* 地图(区域->区域->区域->区域->区域)
* 地图是super class, 它包含无数个区域
* 一个区域可以跟最多四个区域链接《分别是 ↑,↓,←,→》
* playerlist 区域玩家
* moblist 区域怪物
* buildings 区域建筑物

### Stage 区域
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

* Quality: Regular, Elite, Special, Dungeon Master 品质(普通,精英,特殊,层主)

## 技能系统 Move system(TODO)


## 任务系统 Mission system(TODO)


## 配方系统 Recipe system(TODO)


## 天赋树 Talent Tree System (没想好要不要写)->已经移除

## BattleField
* 战斗模式1：玩家将会使用三种类技巧进行战斗: 力量, 速度, 技巧; 简单来说就是石头剪刀布  
* 武器的自带特性: 劈砍(技巧),穿刺(速度),钝击(力量) 也会形成一种互相克制的关系, 相反的使用互补的技巧能增加武器威力

* 战斗模式2：玩家可直接发送攻击, 但攻击伤害-25%