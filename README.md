# Swingy

Swingy is a simple game, where you play as hero, fighting monsters.
After defeating a monster, you get experience and may get an artefact,
which can be a helm, an armor or a weapon. Helms add HP to your hero,
armors add defense, weapons are needed for fighting. When you collect
enough experience, you get new level. Goal of the game is to get to level 8.

There are four classes : warrior, swordsman, assassin and mage.
All classes have only one difference - each has unique weapons, some weapons are shared.

There multiple categories of weapons :
* Hammers - used only by warriors. Deal stable damage, have a strong, but pretty rare attack. 
* Swords - used by warriors and swordsmans. Deal damage with some variations, some have unique attacks.
* Rapiers - used only by sowrdsmans. Deal stable damage and have good chance of critical attack.
* Scimitars - used by swordsmans and assassins. Deal very varying damage (can be very low or very high).
* Daggers - used only by assassins. Deal low damage, but have largest critical attack chance.
* Stuff - special weapons of mages. Most of them have multiple attacks with good damage. However the most
  powerful one is pretty strange and unstable, but deals largest damage in all the game. 


## Building and running

```
mvn clean install
java -jar target/swing.jar [console/gui]
```

### Mandatory flags :

`console` -  launch in console mode

`gui` -  launch in GUI mode

### Optional flags :
`use-database`  -  load heroes from a database, needs MySQL server

`debug`         -  if you want to see, why it crashed...

## Resources

* [Знакомство с паттерном MVC (Model-View-Controller)](https://javarush.ru/groups/posts/2536-chastjh-7-znakomstvo-s-patternom-mvc-model-view-controller)
* [Обобщенный Model-View-Controller](https://www.rsdn.org/article/patterns/generic-mvc.xml#EEGAC)
* [Model–view–controller](https://en.wikipedia.org/wiki/Model–view–controller)
* [How to Switch the Algorithms at Runtime with Strategy Pattern in C#](https://medium.com/net-core/how-to-switch-the-algorithms-at-runtime-with-strategy-pattern-in-c-43fec29a1702)
* [Наблюдатель на Java](https://refactoring.guru/ru/design-patterns/observer/java/example)
* [Java Swing tutorial](http://zetcode.com/javaswing/)
* [FlatLaf - Flat Look and Feel](https://www.formdev.com/flatlaf/)
* [MigLayout - Java Layout Manager for Swing, SWT and JavaFX](http://www.miglayout.com)
* [Чтение и запись YAML файлов на Java с Jackson](https://dev-gang.ru/article/cztenie-i-zapis-yaml-failov-na-java-s-jackson-r81jqcddv0/)
* [@Getter and @Setter](https://projectlombok.org/features/GetterSetter)
* [Java Bean Validation Basics](https://www.baeldung.com/javax-validation)