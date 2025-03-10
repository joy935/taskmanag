import src.TaskCategory

// Task data class with a title and a completed status
data class Task(var title: String, var category: TaskCategory, var completed:Boolean) {
    fun markCompleted() {
        completed = true
    }
}