package collab.todo

class Category {
	String name
	String description

	static mapping = {
		name index:true
	}

	static constraints = {
		name blank:false
	}

	String toString() {
		name
	}
}
