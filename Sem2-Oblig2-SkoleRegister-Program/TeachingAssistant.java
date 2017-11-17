package inf1010.assignment;


class TeachingAssistant extends Student implements Teacher {
    TeachingAssistant(String email, String name, String username) {
		super(email, name, username);
	}
	Group group;

    public Group getGroup() {
		return group;
    	
    }
    public Student[] getStudent() {
    	int i = 0;
    	Student[] ta = new Student[groups.size()];
    	for(Group g : groups) {
    		for ( Student s : g.getStudent()){
    		ta[i++] = s;
    		}
    	}
		return ta;
    	
    }
    public Subject getSubject() {
		return group.getSubject();
    	
    }
    public  void addGroup (Group g) {
 	   group = g;
    }
}
