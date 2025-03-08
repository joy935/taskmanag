// TaskManager class to manage tasks:
// add, display, modify, complete and delete tasks
class TaskManager {
    // mutable list to store tasks
    var tasks = mutableListOf<Task>()

    // function to add a new task
    // composed by a title and a default
    // not completed status
    fun addTask(title: String) {
        tasks.add(Task(title, false))
        // display the task newly added
        println("\nAdded task: $title ")
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
                // use non-mutable variable to store the status
                val status: String
                // mark if the task is completed
                if (task.completed) {
                    status = "[✓]"
                }
                // otherwise, uncheck it
                else {
                    status = "[ ]"
                }
                // list all tasks with their adjusted index
                println("${index + 1}. $status ${task.title}") }
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
            // prompt the user to enter a new title
            print("Enter a new title: ")
            val newTitle = readLine()?.trim()
            // modify the task if the input is valid
            if (!newTitle.isNullOrBlank()) {
                tasks[indexAdjusted].title = newTitle
                println("Task updated: $newTitle")
            }
            // otherwise, print an invalid message
            else {
                println("Task title can't be null or empty.")
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

    // function to return true
    // if the task list is empty
    fun isEmpty(): Boolean {
        return tasks.isEmpty()
    }
}