fun main() {
    println("Hello World!\n")

    // print the title of the application
    println("************************")
    println(" To-Do List Application ")
    println("************************")

    // create a new instance of task manager
    val taskManager = TaskManager()

    // use a while loop to display the menu until the
    // user chooses to quit
    while (true) {
        println("\n\nMenu")
        println("1. Add Task")
        println("2. List Tasks")
        println("Enter your choice: ")

        // use when to direct the options chosen
        when (readLine()?.toInt()) {
            // if the user chooses to add a task
            1 -> {
                println("Enter task title: ")
                val title = readLine() ?: ""
                // if the title isn't null or empty, add
                // a new task
                if (!title.isNullOrEmpty()) {
                    taskManager.addTask(title)
                }
                // otherwise, prompt again to enter a title
                else {
                    println("Enter task title: ")
                }
            }
            // if the user chooses to list the tasks
            2 -> {
                println("\n*******************")
                println("Your tasks: ")
                taskManager.listTasks()
            }

        }
    }
}