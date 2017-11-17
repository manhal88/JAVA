package inf1010.assignment;



public class Professor extends Person implements Teacher {
    Professor(String email, String name, String username) {
		super(email, name, username);
	}
	Subject subject;
    
    public Student[] getStudent() {
		return subject.getStudents();
    	
    }
    public Subject getSubject() {
		return subject;
    	
    }
}
