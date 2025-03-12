package src

import java.time.LocalDate
import java.time.format.DateTimeFormatter

// src.Task Manager class to manage tasks:
// add, display, modify, complete and delete tasks
class TaskManager {
    // mutable list to store tasks
    private var tasks = mutableListOf<Task>()

    // store the file path
    private val file = "src/tasks.json"
    // initialize the file and load it
    init {
        tasks = TaskStorage.loadTasks(file).toMutableList() }

    // function to add a new task composed by a title,
    // a category and a default not completed status
    fun addTask(task: Task) {
        tasks.add(task)
        // save tasks
        TaskStorage.saveTasks(file, tasks)
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
            print("Enter the due date (YYYY-MM-DD) or Enter 'remove' to clear it: ")
            val input = readlnOrNull() ?:""

            // remove the due date
            if (input.lowercase() == "remove") {
                task.dueDate = null
                println("Due date removed.")
                break // return to menu
            }

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

            // iterate through the options of modifying
            // the title, the category or the due date
            // until the user quit
            while (true) {
                println("\nModify task: ${task.title}")
                println("1. Modify title")
                println("2. Modify category")
                println("3. Modify due date")
                println("4. Quit")
                print("Enter your choice: ")

                val choice = readlnOrNull()?.toIntOrNull()
                when (choice) {
                    // modify the title if the user chooses option 1
                    1 -> {
                        // prompt the user to enter a new title
                        print("Enter a new title: ")
                        val newTitle = readlnOrNull()?.trim()
                        // modify the task if the input is valid
                        if (!newTitle.isNullOrBlank()) {
                            tasks[indexAdjusted].title = newTitle
                            // save tasks that have been modified
                            TaskStorage.saveTasks(file, tasks)
                            println("Task updated: $newTitle")
                            break // return to menu
                        }
                        // otherwise, print an invalid message
                        else {
                            println("Task title can't be null or empty.")
                    }
                    }
                    // modify the category if the user chooses option 2
                    2 -> {
                        val newCategory = chooseCategory()
                        task.category = newCategory
                        // save tasks that have been modified
                        TaskStorage.saveTasks(file, tasks)
                        println("Category updated: $newCategory")
                    }
                    // modify the due date if the user chooses option 3
                    3 -> {
                        dueDate(task)
                        // save tasks that have been modified
                        TaskStorage.saveTasks(file, tasks)
                    }
                    // quit modifying the task for option 4
                    4 -> {
                        println("Quit modifying task.")
                        break // return to menu
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
            // save tasks
            TaskStorage.saveTasks(file, tasks)
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
            // save tasks
            TaskStorage.saveTasks(file, tasks)
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

            // display a message if there's no task in this category
            if (categoryTask.isEmpty()) {
                println("No tasks.")
            }
            // otherwise, display all the tasks
            else {
                categoryTask.forEachIndexed { index, task ->
                    val status = if (task.completed) "[✓]" else "[ ]"
                    // don't display the date if there's no due date
                    if (task.dueDate == null) {
                        println("${index + 1}. $status ${task.title}")
                    }
                    // display the due date if there's any
                    else {
                        println("  ${index + 1}. $status ${task.title} ~ Due Date: ${task.dueDate}")
                    }
                }
            }
        }
    }

    // function to list all tasks by due date (chronologically)
    fun listByDueDate() {

        // two lists: one for tasks with due dates and one without due dates
        val tasksWithDueDate = tasks.filter { it.dueDate != null }.sortedBy { it.dueDate }
        val tasksWithoutDueDate = tasks.filter { it.dueDate == null }

        println("\n***********************")
        println("Tasks Sorted by Due Date")

        // sort the tasks chronologically if they have a due date,
        if (tasksWithDueDate.isNotEmpty()) {
            tasksWithDueDate.forEachIndexed { index, task ->
                val status = if (task.completed) "[✓]" else "[ ]"
                val dueDateText = task.dueDate?.toString() ?: "No Due Date"
                println("${index + 1}. $status ${task.title} ~ ${task.category} ~ Due: $dueDateText")
            }
        }

        println("\n***********************")
        println("Other tasks")

        // display the tasks without due dates as they were added previously
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