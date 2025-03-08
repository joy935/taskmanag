fun main() {
    // println("Hello World!\n")

    // display the title of the application
    println("**************************")
    println(" Task Manager Application ")
    println("**************************")

    // create a new instance of task manager
    val taskManager = TaskManager()

    // use a while loop to display the menu until the
    // user chooses to exit
    while (true) {
        println("\nMenu")
        println("1. Add a task")
        println("2. List tasks")
        println("3. Modify a task")
        println("4. Complete a task")
        println("5. Delete a task")
        println("6. Exit")
        print("Enter your choice: ")

        // use toIntOrNull to only accept integer and
        // transforms any other input into null
        val choice = readLine()?.toIntOrNull()
        // use when to direct the options chosen
        when (choice) {
            // if the user chooses to add a task
            1 -> {
                // mutable string to store the title
                var title: String
                // use a do loop to repeat until a valid input
                do {
                    // prompt the user to enter the task title
                    print("Enter task title: ")
                    // ensures input is not null
                    title = readLine()?: ""
                } while (title.isBlank()) // ensures input is not blank

                // add task if input is a valid title
                taskManager.addTask(title)
            }
            // if the user chooses to list the tasks
            2 -> {
                //list all tasks
                taskManager.listTasks()
            }
            // if the user chooses to modify a task
            3 -> {
                // if the task list is empty, print a message
                // and return to the menu
                if (taskManager.isEmpty()) {
                    println("No tasks available to modify.")
                    continue // go back to the menu
                }

                //list all tasks
                taskManager.listTasks()

                // prompt the user to choose a task to modify
                print("\nEnter task index to modify: ")
                // convert valid input to an integer and
                // return null if input is invalid
                val index = readLine()?.toIntOrNull()
                // modify task if input is a valid number
                if (index != null) {
                    taskManager.modifyTask(index.toInt())
                }
                // otherwise, display an error message
                else {
                    println("Invalid input.")
                }
            }
            // if the user chooses to complete a task
            4 -> {
                // if the task list is empty, print a message
                // and return to the menu
                if (taskManager.isEmpty()) {
                    println("No tasks available to complete.")
                    continue // go back to the menu
                }

                //list all tasks
                taskManager.listTasks()

                // prompt the user to enter a task index
                print("\nEnter task index to complete: ")
                // convert valid input to an integer and
                // return null if input is invalid
                val index = readLine() ?.toIntOrNull()
                // complete task if input is a valid number
                if (index != null) {
                    taskManager.completeTask(index.toInt())
                }
                // otherwise, display an error message
                else {
                    println("Invalid input.")
                }

            }
            // if the user chooses to delete a task
            5 -> {
                // if the task list is empty, print a message
                // and return to the menu
                if (taskManager.isEmpty()) {
                    println("No tasks available to delete.")
                    continue // go back to the menu
                }

                //list all tasks
                taskManager.listTasks()

                print("\nEnter task index to delete: ")
                // convert valid input to an integer and
                // return null if input is invalid
                val index = readLine()?.toIntOrNull()
                // delete task if input is a valid number
                if (index != null) {
                    taskManager.deleteTask(index.toInt())
                }
                // otherwise, display an error message
                else {
                    println("Invalid input.")
                }
            }
            // if the user chooses to exit the app
            6 -> {
                // print a message and exit the app
                println("Thank you for using the Task Manager App!")
                println("Goodbye!")
                return // exit
            }
            // if the user chooses an invalid input
            else -> {
                // print an error message
                println("Invalid choice. Try again!")
            }
        }
    }
}