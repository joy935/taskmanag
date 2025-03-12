package src

import java.time.LocalDate
// import java.time.format.DateTimeFormatter

// the Task data class with a title, a category, a due date (if specified) and  a completed status (set false by default)
data class Task(var title: String, var category: TaskCategory, var dueDate: LocalDate?=null, var completed:Boolean) {
    fun markCompleted() {
        completed = true
    }
//    fun formatDueDate(): String? {
//        return dueDate?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
//    }
}