// create a class task manager to add and list tasks
class TaskManager {
    // create a mutable list of tasks
    var tasks = mutableListOf<Task>()

    // add a task composed by a title and a completed status
    fun addTask(title: String) {
        tasks.add(Task(title, false))

        // print the task added
        println("\nAdded task: $title ")
    }

    // list the tasks
    fun listTasks() {
        // if the list of tasks is empty print a message
        if (tasks.isEmpty()) {
            println("No tasks.\n")
        }
        // if there is/are task(s) print them
        else {
            tasks.forEachIndexed { index, task -> println("${index + 1}. ${task.title}") }
        }
    }
}