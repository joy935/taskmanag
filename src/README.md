# Task Management App

# Overview 

This is a simple command-line task management app as a first project written with Kotlin. 
It explores Kotlin's syntax, object-oriented programming principles and basic CRUD operations.

### Purpose
This project serves as a learning activity to explore Kotlin and build a functional application. 
By creating this task management app, users can stay organized and be more productive by keeping track of their tasks.
This application provides a foundation for building more advanced features in the future, such as reminders and user interface.

### Features
- Add, remove, modify and list tasks
- Categorize tasks using predefined categories
- Store tasks in a JSON file
- Load tasks from a JSON file
- User-friendly menu to manage tasks

### Structure
- Task data class: Represents a task with properties such as title, category, due date and status
- TaskCategory enum class: Defines different categories of tasks such as work, family, personal, hobby and other
- TaskManager class: Manages the tasks with CRUD (Create, Read, Update, Delete) functions
- TaskStorage object: Handles loading and saving files
- Main: Provides a simple menu for user interaction through the command-line

[Software Demo Video](http://youtube.link.goes.here)

# Development Environment

### Tools
- Programming Language: Kotlin
- IDE: IntelliJ IDEA
- Version Control: Git and GitHub
- Operating System: macOS

### Libraries
- org.json (JSON-java): Handles loading, reading and writing tasks from a JSON file 
- Kotlin Standard Library: Provides essential functions to work with collections, collections and data structures and more
- Java Standard Library: For reading and writing task data to a JSON file, managing due dates and formatting and parsing date values

# Useful Websites

- [Kotlin Documentation](https://kotlinlang.org/docs/getting-started.html)
- [Classes in Kotlin](https://www.youtube.com/watch?v=KvehHqnEXuc)
- [JSON in Java](https://github.com/stleary/JSON-java?tab=readme-ov-file)

# Future Work

- Create a new file of tasks
- Store the lists of tasks in a database
- Create a mobile app
