// create a class of Task with a title and a completed status
data class Task(var title: String, var completed:Boolean) {
    fun markCompleted() {
        completed = true
    }
}