package src

import java.time.LocalDate
import java.time.format.DateTimeFormatter

// src.Task Manager class to manage tasks:
// add, display, modify, complete and delete tasks
class TaskManager {
    // mutable list to store tasks
    private var tasks = mutableListOf<Task>()

    // function to add a new task composed by a title,
    // a category and a default not completed status
    fun addTask(task: Task) {
        tasks.add(task)
        // display the task newly added
        if (task.dueDate == null ) {
            println("\nAdded task: ${task.title} ~ Category: ${task.category}")
        } else {
            println("\nAdded task: ${task.title} ~ Category: ${task.category} ~ Due Date: ${task.dueDate}")
        }
    }

    // function to choose a category to classify tasks
    fun chooseCategory(): TaskCategory {
        println("Categories: ")
        // display all the categories
        TaskCategory.entries.forEachIndexed { index, category ->
            println("${index + 1}. $category")
        }
        print("Choose your category: ")
        val category = readlnOrNull()?.toIntOrNull()
        // if the category is valid, associate the task with the category
        return if (category != null && category in 1..TaskCategory.entries.size) {
            TaskCategory.entries[category - 1]
        }
        // otherwise, default to OTHER
        else {
            println("Invalid category, default to OTHER.")
            TaskCategory.OTHER
        }
    }

    // function to set a due date for a task
    fun dueDate(task: Task) {
        // iterate until the user enters a valid date
        while (true) {
            print("Enter the due date (YYYY-MM-DD): ")
            val input = readlnOrNull() ?:""
            //  try to parse the date input
            try {
                val parsedDate = LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                // if the date is in the past, print a message
                if (parsedDate.isBefore(LocalDate.now())) {
                    println("The due date must be today or in the future.")
                }
                // otherwise, set the due date
                else {
                    task.dueDate = parsedDate
                    println("Due date set to: ${task.dueDate}")
                    break // return to menu
                }
            } catch (e: Exception) {
                println("Invalid format.")
            }
        }
    }

    // function to display all the tasks or
    // a message of there are no tasks
    fun listTasks() {
        // display a message if the list is empty
        if (tasks.isEmpty()) {
            println("No tasks.")
        }
        // otherwise, list the tasks
        else {
            println("\n*******************")
            println("Tasks: ")
            // iterate through the list of task
            tasks.forEachIndexed { index, task ->
                // mark if the task is completed
                // use non-mutable variable to store the status
                val status: String = if (task.completed) {
                    "[✓]"
                }
                // otherwise, uncheck it
                else {
                    "[ ]"
                }
                // list all tasks with their adjusted index
                if (task.dueDate == null) {
                    println("${index + 1}. $status ${task.title} ~ ${task.category}")
                } else {
                    println("${index + 1}. $status ${task.title} ~ ${task.category} ~ Due Date: ${task.dueDate}") }
                }
        }
    }

    // function to modify an existing task title
    fun modifyTask(index: Int) {
        // adjust the user index (1 based)
        // to match the list index (0 based)
        val indexAdjusted = index - 1
        // prompt the user for a new title
        // if the index is valid
        if (indexAdjusted in tasks.indices) {

            val task = tasks[indexAdjusted]

            while (true) {
                println("Modify task: ${task.title}")
                println("1. Modify title")
                println("2. Modify category")
                println("3. Modify due date")
                println("4. Cancel")
                print("Enter your choice: ")

                val choice = readlnOrNull()?.toIntOrNull()
                when (choice) {
                    1 -> {
                        // prompt the user to enter a new title
                        print("Enter a new title: ")
                        val newTitle = readlnOrNull()?.trim()
                        // modify the task if the input is valid
                        if (!newTitle.isNullOrBlank()) {
                            tasks[indexAdjusted].title = newTitle
                            println("src.Task updated: $newTitle")
                        }
                        // otherwise, print an invalid message
                        else {
                            println("src.Task title can't be null or empty.")
                        }
                    }

                    2 -> {
                        val newCategory = chooseCategory()
                        task.category = newCategory
                        println("Category updated: $newCategory")
                    }

                    3 -> {
                        dueDate(task)
                    }

                    4 -> {
                        println("Modification cancelled.")
                        break
                    }
                }
            }
        }
        // otherwise, print an invalid message
        else {
            println("Invalid index: $index")
        }
    }

    // function to mark a task as completed
    fun completeTask(index: Int) {
        // adjust the user index (1 based)
        // to match the list index (0 based)
        val indexAdjusted = index - 1
        // change the status to completed if the index is valid
        if (indexAdjusted in tasks.indices) {
            tasks[indexAdjusted].markCompleted()
            println("\nCompleted task: ${tasks[indexAdjusted].title} ")
        }
        // otherwise, print an invalid message
        else {
            println("Invalid index: $index")
        }
    }

    // function to delete a task
    fun deleteTask(index: Int) {
        // adjust the user index (1 based)
        // to match the list index (0 based)
        val indexAdjusted = index - 1
        // delete the task if the index is valid
        if (indexAdjusted in tasks.indices) {
            val deletedTask = tasks.removeAt(indexAdjusted)
            println("Tasked removed: ${deletedTask.title}")
        }
        // otherwise, print an invalid message
        else {
            println("Invalid index: $index")
        }
    }

    // function to list by category
    fun listByCategory() {
        println("*****************")
        println("Tasks by Category")

        // group tasks by category
        val groupedTasks = tasks.groupBy { it.category }

        // iterate over the categories
        TaskCategory.entries.forEach { category ->
            val categoryTask = groupedTasks[category]?: emptyList()

            println("\n$category (${categoryTask.size} tasks)")

            if (categoryTask.isEmpty()) {
                println("No tasks.")
            } else {
                categoryTask.forEachIndexed { index, task ->
                    val status = if (task.completed) "[✓]" else "[ ]"
                    if (task.dueDate == null) {
                        println("${index + 1}. $status ${task.title}")
                    }
                    else {
                        println("  ${index + 1}. $status ${task.title} ~ Due Date: ${task.dueDate}")
                    }
                }
            }
        }
    }

    // function to list all tasks by due date (chronologically)
    fun listByDueDate() {
        println("\n***********************")
        println("Tasks Sorted by Due Date")

        // Sort tasks by due date (null values go last)
        val tasksWithDueDate = tasks.filter { it.dueDate != null }.sortedBy { it.dueDate }
        val tasksWithoutDueDate = tasks.filter { it.dueDate == null }

        if (tasksWithDueDate.isNotEmpty()) {
            tasksWithDueDate.forEachIndexed { index, task ->
                val status = if (task.completed) "[✓]" else "[ ]"
                val dueDateText = task.dueDate?.toString() ?: "No Due Date"
                println("${index + 1}. $status ${task.title} ~ ${task.category} ~ Due: $dueDateText")
            }
        }

        println("\n***********************")
        println("Other tasks")

        if (tasksWithoutDueDate.isNotEmpty()) {
            tasksWithoutDueDate.forEachIndexed { index, task ->
                val status = if (task.completed) "[✓]" else "[ ]"
                println("${index + 1}. $status ${task.title} ~ ${task.category}")
            }
        }

    }
    // function to return true
    // if the task list is empty
    fun isEmpty(): Boolean {
        return tasks.isEmpty()
    }
}