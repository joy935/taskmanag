fun main() {
    // println("Hello World!\n")

    // print the title of the application
    println("**************************")
    println(" Task Manager Application ")
    println("**************************")

    // create a new instance of task manager
    val taskManager = TaskManager()

    // use a while loop to display the menu until the
    // user chooses to quit
    while (true) {
        println("\n\nMenu")
        println("1. Add a task")
        println("2. List tasks")
        println("3. Modify a task")
        println("4. Complete a task")
        println("5. Delete a task")
        println("6. Exit")
        print("Enter your choice: ")

        val choice = readLine()?.toIntOrNull()
        // use when to direct the options chosen
        when (choice) {
            // if the user chooses to add a task
            1 -> {
                print("Enter task title: ")
                val title = readLine() ?: ""
                // if the title isn't null or empty, add
                // a new task
                if (title.isNotEmpty()) {
                    taskManager.addTask(title)
                }
                // otherwise, prompt again to enter a title
                else {
                    print("Enter task title: ")
                }
            }
            // if the user chooses to list the tasks
            2 -> {
                println("\n*******************")
                println("Tasks: ")
                // list the tasks
                taskManager.listTasks()
            }
            // if the user chooses to modify a task
            3 -> {
                //print the list of tasks
                println("\n*******************")
                println("Tasks: ")
                taskManager.listTasks()

                // prompt the user to choose a task to modify
                print("\nEnter task index to modify: ")
                val index = readLine()?.toIntOrNull()
                // if the index is valid, modify the task
                if (index != null) {
                    taskManager.modifyTask(index.toInt())
                }
                // otherwise, prompt the user to choose
                // a valid index
                else {
                    print("Invalid task index ")
                }
            }
            // if the user chooses to complete a task
            4 -> {
                // print the list of tasks
                println("\n*******************")
                println("Tasks: ")
                taskManager.listTasks()

                print("\nEnter task index to complete: ")
                val index = readLine() ?: ""
                // if the index chosen is correct, complete the task
                if (index.isNotEmpty()) {
                    taskManager.completeTask(index.toInt())
                } else {
                    print("Enter a valid task index: ")
                }

            }
            // if the user chooses to delete a task
            5 -> {
                // print the list of tasks
                println("\n*******************")
                println("Tasks: ")
                taskManager.listTasks()

                print("\nEnter task index to delete: ")
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
            6 -> {
                println("Thank you for using the Task Manager App!")
                println("Goodbye!")
                return
            }

            else -> {
                println("Invalid choice. Try again!")
            }
        }
    }
}