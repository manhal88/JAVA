package inf1010.assignment;

import inf1010.lib.two.IfiCollection;

public class Student extends Person{

	IfiCollection<Group> groups = new OrderedSet<Group>();

	Student(String email, String name, String username) {
		super(email, name, username);
	}

	public Group[] getGroups() {//<<   ferdig  >>>
		return groups.toArray(new Group[groups.size()]);
		
	}
	public Subject[] getSubjects() {//<<   ferdig  >>>

		Subject[] s = new Subject[groups.size()];
		int i = 0;
		for(Group g : groups) {
			s[i++] = g.getSubject();
		}
		return s;
	}
	public Teacher[] getTeachers() {//<<   ferdig  >>>
		Teacher[] t = new Teacher[groups.size() * 2];
		int i = 0;
		for(Group g : groups) {
			t[i++] = g.GetTeachingAssistant();
			t[i++] = g.subject.getLecturer();
		}
		return t;
	}
}
