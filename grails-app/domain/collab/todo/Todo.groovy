package collab.todo

class Todo {

    String name
    String note
    Date createdDate
    Date dueDate
    Date completedDate
    String priority
    String status

    static constraints = {
        name unique:true
        createdDate()
        priority()
        status()
        note(maxSize:1000, nullable:true)
        completedDate(nullable:true)
        dueDate(nullable:true)
    }

    String toString() {
        return name
    }
}
