// create a class of Task with a title and a completed status
class Task(var title: String, var completed:Boolean) {
    fun isCompleted() {
        completed = true
    }
}