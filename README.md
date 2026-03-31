# Conway's Game Of Life
## Concept

Conway's Game Of Life is a cellular automaton invented by John Horton Conway in 1970.
It is a zero-player game, meaning that its evolution is entirely determined by its initial state.

This project aims to create a convenient interface for simulating
GOL, making it easy to set up and interact with.

## Features

* Game Of Life simulation inside a 800x600 window
* Press Space to pause / resume simulation
* Custom Slider to adjust simulation speed
* While paused, click on a cell to activate it (a line grid is shown for convenience)
* Drag mouse while pressing shift to activate many cells quickly

## Stack

For this project, I decided to go with JavaFX as the rendering
backend, because I wanted to practice Object-Oriented design
and interfacing, and JavaFX allows for more control than swing.

## Codebase

I decided for this project to have a stricter codebase than
any of my previous projects. My goal was to focus on abstractions,
both in private helper methods and the public-facing API of my classes.

In order to keep the codebase clean and readable, I followed the following guidelines:

### Abstracting procedures away
Whenever I wanted to add some sort of functionality, I asked myself a few questions:

In words, what does the implementation need to do?
How many steps are in the implementation?
What should the public-facing API disclose?

If there were multiple steps to implementing functionality, I abstracted those steps away
as much as possible, until any more abstractions needed enough input variables that it was
simpler to just write the code inline.

The purpose of this is to make code as readable as possible. You will not find yourself
looking at the code in this repository and asking yourself "What does this do?",
because almost certainly that code is inside a tiny method that does only one thing, and
it's easy to tell what it does by the name of the method.

In Java, especially in small projects, these abstractions are essentially free.

### No warnings

I went through every compiler warning in the code, fixed what I found to be worth fixing
 / unintentional, and used @SuppressWarnings for the rest, always making sure to only suppress the specific
warning I want suppressed, in order to be as explicit as possible as to why a warning suppression is there.

### Explicit exceptions

Any try / catch block must only catch the specific exception that might be thrown, and deal with it appropriately.

## API And Implementation

There are no static classes in this project. Everything should be instantiated.

To make the codebase more modular, I created three interfaces for simple management
of the game.

### Artist

This interface is for classes that are responsible for drawing elements to the screen.
An Artist implementation must have a 
```Java
void draw(GraphicsContext ctx);
```
method that draws the element
it is responsible for to the screen.

### MouseObserver

There are multiple elements of the game that need to be able to react to mouse events, and instead
of creating clutter by having to call all of them whenever an event is fired, the Game class keeps
a List with an instance of every class that implements MouseObserver (For now this is done manually)
and calls each of them whenever a mouse event is fired.

A MouseObserver must implement three methods:

```Java
void onMousePressed(MouseEvent event);
void onMouseDragged(MouseEvent event);
void onMouseReleased(MouseEvent event);
```

### KeyboardObserver

Same purpose and idea as MouseObserver, but for keyboard inputs.

A KeyboardObserver must implement three methods:

```Java
void onKeyPressed(KeyEvent event);
void onKeyReleased(KeyEvent event);
void onKeyTyped(KeyEvent event);
```