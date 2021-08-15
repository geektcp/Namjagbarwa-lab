# 设计模式
>  当使用设计模式时，不用考虑太多设计模式的理论，只需要考虑当前的业务情况和怎么使用最简单。
   代码的调用方式确定之后，再想办法用设计写法满足这个写法的规则需求，同时依据设计模式考虑代码的重用性

# 常用设计模式的心法

## abstractfactory
```
工厂模式的优势是代码扩展时,调用方几乎不需要改动
```

## adapter

## bridge

## builder

## chain

## command

- 优点：
```
解耦了调用者和接受者之间的联系。
调用者调用一个操作，通过Command中间层，接受者接受请求执行相应的动作，
因为使用Command模式解耦，调用者无需知道接受者任何接口。
```
- 缺点：
```
造成出现过多的具体命令类
```

```
Invoker是调用者，Light是接收者。Invoker没有直接调用Light的任何方法
Command作为Invoker和Light的中间层，负责衔接也就是传递命令。

本质上Invoker的Command[]里面包含了Command接口的实例如LightOnCommand，而这些实例内部又包含了具体的接收者。
看起来还是在Invoker里面实现。关键是这种写法的好处是好扩展，当有新的接收者出现，代码只需要改动一小部分。
```


## composite

## decorator

## facade

## factoryMethod

## flyweight

## interpreter

## iterator

## mediator

## memento

## nullObject

## observer
```
观察者模式要点在于，通过传入一个Listener对象，来监视某个高频动作，比如消费消息，比如电商业务中的下单
把一些非核心的附带逻辑放到Listener中执行，当这部分逻辑要经常改动时，只需要增加一种Listener即可
```

```
设计模式原则-开放封闭原则
类应该对扩展开放，对修改关闭。
扩展就是添加新功能的意思，因此该原则要求在添加新功能时不需要修改代码。
符合开闭原则最典型的设计模式是装饰者模式，它可以动态地将责任附加到对象上，而不用去修改类的代码。
```

```
观察者模式和装饰者模式相同点都是传入对象到主要方法中，
主要区别装饰者模式不改变对象类型，只是用了一个构造函数进行包装
观察者模式目的是传入这一个监视者到核心对象中，进行监视
```

## prototype

## proxy

## simpleFactory
```
工厂模式的优势是代码扩展时,调用方几乎不需要改动
```

## singleton

## state

## strategy

## template

## visitor
