// create a class task manager to add and list tasks
class TaskManager {
    // create a mutable list of tasks
    var tasks = mutableListOf<Task>()

    // function to add a task composed by a title and a completed status
    fun addTask(title: String) {
        tasks.add(Task(title, false))

        // print the task added
        println("\nAdded task: $title ")
    }

    // function to list the tasks
    fun listTasks() {
        // if the list of tasks is empty print a message
        if (tasks.isEmpty()) {
            println("No tasks.")
        }
        // if there is/are task(s) print them
        else {
            tasks.forEachIndexed { index, task -> println("${index + 1}. ${task.title}") }
        }
    }

    // function to complete a task
    fun completeTask(index: Int) {
        val indexAdjusted = index - 1
        // if the index is valid, change the status to completed
        if (indexAdjusted in tasks.indices) {
            tasks[indexAdjusted].isCompleted()
            println("\nCompleted task: ${tasks[indexAdjusted].title} ")
        }
        // otherwise, print an invalid message
        else {
            println("Invalid index: $index")
        }
    }


    // function to delete a task
    fun deleteTask(index: Int) {
        val indexAdjusted = index - 1
        // if the index is valid, delete the task
        if (indexAdjusted in tasks.indices) {
            val deletedTask = tasks.removeAt(indexAdjusted)
            println("Tasked removed: ${deletedTask.title}")
        }
        // otherwise, print an invalid message
        else {
            println("Invalid index: $index")
        }
    }
}