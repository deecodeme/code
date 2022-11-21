# Intent
1. Define an interface for creating an object, but let subclass decide which class to instantiate.
2. Factory methods lets a class differ instantiation to subclasses.

# Implementation
1. Two major varieties 
   1. Creator class is abstract with abstract factoryMethod(). Subclass is to provide the implementation.
   2. Creator class is a concrete class and has a default implementation of factoryMethod(). 
   This is to provide flexibility and extensibility to override the object creation process.
2. Parameterized Factory Method
   1. Creator has a parametrised factoryMethod(Identifier id) which is called to create object of appropriate subclass looking at the Identifier.