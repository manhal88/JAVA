package inf1010.assignment;

import inf1010.lib.two.IfiCollection;

public class Group implements Comparable<Group> {
	int number;
	IfiCollection<Student> students;
	Subject subject;
	TeachingAssistant teachingAssistant;

	public Group(Subject subject, TeachingAssistant teachingAssistant) {
		this.students = new OrderedSet<Student>();
		this.teachingAssistant = teachingAssistant;
		this.subject = subject;

	}
	public int compareTo(Group g){
		return teachingAssistant.compareTo(g.teachingAssistant);

	}
	public boolean addStudent(Student s) {
		s.groups.add(this);
		students.add(s);
		return true;

	}
	 void setNember (int numer) {
		this.number = numer;
	}
	public int getNumber() {
		return number;
		
	}
	public int getStudentBodySize() {
		return students.size();
		
	}
	public Student[] getStudent() {//<<   ferdig  >>>
		return students.toArray(new Student[students.size()]);
	}
	public Subject getSubject() {
		return subject;
		
	}
	public TeachingAssistant GetTeachingAssistant() {
		return teachingAssistant;
		
	}

}
