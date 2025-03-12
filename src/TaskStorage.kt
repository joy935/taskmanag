package src

import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.time.LocalDate

// TaskStorage is an object to simplify file handling:
// loading and saving
object TaskStorage {
    // function to load tasks from a JSON file
    fun loadTasks(fileName: String): List<Task> {
        return try {
            // read and parse the JSON file
            val jsonText = File(fileName).readText()
            val jsonArray = JSONArray(jsonText)

            // convert JSON object into a Task object
            List(jsonArray.length()) { i ->
                val obj = jsonArray.getJSONObject(i)
                Task(
                    title = obj.getString("title"),
                    category = TaskCategory.valueOf(obj.getString("category")), // Convert String to Enum
                    dueDate = obj.optString("dueDate", "").takeIf { it.isNotEmpty() }
                        ?.let { LocalDate.parse(it) }, // Convert String to LocalDate
                    completed = obj.getBoolean("completed")
                )
            }
        }
        // display an error message if an error occurs
        catch (e: Exception) {
            println("Error reading JSON file: ${e.message}")
            // return an empty list if there's an error
            emptyList()
        }
    }

    // function to save tasks to a JSON file
    fun saveTasks(fileName: String, tasks: List<Task>) {
        try {
            val jsonArray = JSONArray()
            // iterate through the tasks and convert each task into
            // a JSON object
            tasks.forEach { task ->
                val jsonObject = JSONObject()
                jsonObject.put("title", task.title)
                jsonObject.put("category", task.category.name) // Convert Enum to String
                jsonObject.put("dueDate", task.dueDate?.toString()) // Convert LocalDate to String
                jsonObject.put("completed", task.completed)
                jsonArray.put(jsonObject)
            }
            // write the JSON array to the file
            File(fileName).writeText(jsonArray.toString(4)) // Pretty print JSON
            println("Tasks saved successfully!")
        }
        // display a message if there's an error
        catch (e: Exception) {
            println("Error saving tasks: ${e.message}")
        }
    }
}