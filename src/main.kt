fun main() {
    println("Hello World!\n")
    println("************************")
    println(" To-Do List Application ")
    println("************************")

    val taskManager = TaskManager()

    while (true) {
        println("\n\nMenu")
        println("1. Add Task")
        println("2. List Tasks")
        println("Enter your choice: ")

        when (readLine()?.toInt()) {
            1 -> {
                println("Enter task title: ")
                val title = readLine() ?: ""
                if (!title.isNullOrEmpty()) {
                    taskManager.addTask(title)
                } else {
                    println("Enter task title: ")
                }
            }
            2 -> {
                println("\n*******************")
                println("Your tasks: ")
                taskManager.listTasks()
            }

        }
    }
}