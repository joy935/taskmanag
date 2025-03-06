fun main() {
    println("Hello World!\n")

    // print the title of the application
    println("************************")
    println(" Task Manager Application ")
    println("************************")

    // create a new instance of task manager
    val taskManager = TaskManager()

    // use a while loop to display the menu until the
    // user chooses to quit
    while (true) {
        println("\n\nMenu")
        println("1. Add a task")
        println("2. List tasks")
        println("3. Complete a task")
        println("4. Delete a task")
        println("5. Exit")
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
            // if the user chooses to complete a task
            3 -> {
                // print the list of tasks
                println("\n*******************")
                println("Your tasks: ")
                taskManager.listTasks()

                println("Enter task index to complete: ")
                val index = readLine() ?: ""
                // if the index chosen is correct, complete the task
                if (index.isNotEmpty()) {

                }

            }
            // if the user chooses to delete a task
            4 -> {
                // print the list of tasks
                println("\n*******************")
                println("Your tasks: ")
                taskManager.listTasks()

                println("\nEnter task index to delete: ")
                val index = readLine() ?: ""
                // if the index chosen is correct, delete the task
                if (index.isNotEmpty()) {
                    taskManager.deleteTask(index.toInt())
                }
                // otherwise, print an invalid message
                else {
                    println("Invalid index")
                }
            }
            // if the user chooses to exit the app
            5 -> {
                println("Thank you for using the Task Manager App!")
                println("Goodbye!")
                return
            }
            else -> {
                println("Invalid choice")
            }
        }
    }
}