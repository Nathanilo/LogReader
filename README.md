Event-Driven Log Processing System (Java)
=========================================

Project Overview
----------------

This project implements an **event-driven log processing system** in Java.It parses structured application logs, converts each log entry into an immutable event, and processes those events through a flexible listener-based architecture.

The goal of this project is to demonstrate:

*   Strong object-oriented design

*   Event-based architecture

*   Proper use of interfaces

*   Application of common design patterns


This project follows a **generator–event–listener** model and is designed to be easily extensible.

Key Concepts & Objectives
-------------------------

*   Event-driven programming

*   Interface-based design

*   Observer (Listener) pattern

*   Factory Method pattern

*   Singleton pattern

*   Immutable domain objects

*   File I/O and log parsing

*   Clean and maintainable project structure


Architecture Summary
--------------------

The system is organized into three main roles:

### Events

*   Represent immutable log entries

*   Extend java.util.EventObject

*   Expose log data through read-only getters


### Generator

*   Responsible for creating LogEvent instances

*   Maintains a list of subscribed listeners

*   Notifies listeners whenever a new event is created

*   Implemented as a Singleton

*   Acts as a Factory for events


### Listeners

*   Subscribe to the generator

*   React to events independently

*   Can filter events or aggregate statistics

*   Do not modify events


Processing Flow
---------------

1.  LogParser reads the application.log file line by line

2.  Each line is parsed into typed fields

3.  LogGenerator creates a LogEvent

4.  The generator notifies all registered listeners

5.  Each listener independently reacts to the event


How to Run
----------

1.  Clone the repository

2.  Open the project in IntelliJ IDEA

3.  Ensure application.log is present

4.  Run Main.java

5.  Check the output/ directory for generated files


Possible Improvements
---------------------

*   Add unit tests (JUnit)

*   Support JSON or CSV output formats

*   Externalize configuration using a properties file

*   Allow listeners to subscribe to multiple filters

*   Improve error handling in log parsing