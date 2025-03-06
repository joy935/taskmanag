class TaskManager {
    var tasks = mutableListOf<Task>()

    fun addTask(title: String) {
        tasks.add(Task(title, false))

        println("\nAdded task: $title ")
    }

    fun listTasks() {
        if (tasks.isEmpty()) {
            println("No tasks.\n")
        } else {
            tasks.forEachIndexed { index, task -> println("${index + 1}. ${task.title}") }
        }
    }
}