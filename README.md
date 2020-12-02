![Mirror](https://i.imgur.com/GLhRe4R.png)
# Overview
Java Reflection utility library.
## Features
* Access classes, methods, and fields easily.
* Cache said classes, methods, and fields.
## Example
```java
ClassInstance clazz = Mirror.getClassInst("Test", "test argument");
FieldInstance field = Mirror.getFieldInst(clazz, "testField");
System.out.println(field.getValue());
```
