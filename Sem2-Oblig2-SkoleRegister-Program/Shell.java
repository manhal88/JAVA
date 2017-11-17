package inf1010.assignment;

import inf1010.lib.two.IfiCollection;
import inf1010.lib.two.ShellBase;

public class Shell extends ShellBase {

	IfiCollection<Person> people = new OrderedSet<Person>();

	IfiCollection<Subject> subjects = new OrderedSet<Subject>();

	public static void main(String[] args) {
		Shell s = new Shell();
		s.createTestDatabase();
		s.inputLoop();
	}

	public Shell() {
	}
	/**
	 * @param subject
	 * the subject courseCode
	 * 
	 * @param teachingAssistant
	 * the teachingAssistant username
	 * 
	 * @return
	 * if courseCode and username is null
	 * else add a new Group
	 */
	protected void addGroup(String subject, String teachingAssistant) {
		Subject one = null;
		TeachingAssistant ta = null;
		for (Person p : people) {
			if (teachingAssistant.compareTo(p.username) == 0) {
				if (p instanceof TeachingAssistant) {
					ta = (TeachingAssistant) p;
				}
			}
		}
		for (Subject s : subjects) {
			if (subject.compareTo(s.courseCode) == 0) {
				one = s;
			}
		}
		if (ta == null || one == null) {
			return;
		}
		Group groupe1 = new Group(one, ta);
		groupe1.setNember(one.groupeTeller + 1);
		one.addGroup(groupe1);
		ta.addGroup(groupe1);
		System.out.println("New group added:  " + subject
				+ ", Group teachingAssistant is: " + teachingAssistant);
	}
	/**
	 * @param name
	 * The Person's name 
	 * 
	 * @param username
	 * The Person's username
	 * 
	 * @param email
	 * The Person's email
	 * 
	 * - adding new Professor to the list
	 * 
	 */
	protected void addProfessor(String name, String username, String email) {
		people.add(new Professor(name, username, email));
		System.out.println("Professor:  " + name + " is added");
	}
	/**
	 * @param name
	 * The Person's name 
	 * 
	 * @param username
	 * The Person's username
	 * 
	 * @param email
	 * The Person's email
	 */

	protected void addStudent(String name, String username, String email) {
		people.add(new Student(name, username, email));
		System.out.println("Student:  " + name + " is added");
	}

	/**
	 * @param coursCode
	 * Subject's Code
	 * 
	 * @param professor
	 * Person's username
	 * 
	 * 
	 * -adding new Subject to the list if the coursCode and username of person is in subjects list
	 */
	protected void addSubject(String coursCode, String professor) {
		Teacher t = null;
		for (Person p : people) {
			if (p.username.compareTo(professor) == 0) {
				if (p instanceof Professor) {
					t = (Teacher) p;

				}
			}
		}
		for (Subject s : subjects) {
			if (s.courseCode.compareTo(coursCode) == 0) {
				System.out.println("this course is already in the system");
				return;
			}
		}
		if (t == null) {
			return;
		}


		subjects.add(new Subject(coursCode, t));
		System.out.println("New subject:  " + coursCode + " professor name: "
				+ professor);
	}

	/**
	 * @param name
	 * TeachingAssistant's name
	 * 
	 * @param username
	 * TeachingAssistant's username
	 * 
	 * - adding new TeachingAssistant to the list if name and username is in people list
	 */

	protected void addTeachingAssistant(String name, String username,
			String email) {

		for (Person p : people) {
			if (p.name.compareTo(name) == 0) {
				if (p instanceof Professor || p instanceof TeachingAssistant) {
					return;
				}
				people.add(new TeachingAssistant(name, username, email));
				System.out.println("TeachingAssistant:  " + name + " is added");
			} else {
				people.add(new TeachingAssistant(name, username, email));
			}
		}
	}
	/**
	 * @param student
	 * Student's username
	 * 
	 * @param subject
	 * subject's courseCode 
	 * 
	 * @param group
	 * group's number
	 * 
	 * 
	 * - add student in this group in this subjects if courseCode and username of the student is in hte people list
	 */
	protected void enrollStudent(String student, String subject, int group) {
		for (Subject s : subjects) {
			if (subject.compareTo(s.courseCode) == 0) {

				for (Person p : people) {
					if (student.compareTo(p.username) == 0) {
						if (p instanceof Student) {
							Student st = (Student) p;
							s.enrollStudent(st, group);
							System.out.println("EnrollStudent " + student + " "
									+ group);
						}
					}
				}
			}
		}
	}

/**
 * list all people and subjects in the list 
 * 
 */
	protected void list() {
		System.out.println("----------list----------");

		for (Person p : people) {
			System.out.println("-" + p.getName());
		}
		System.out.println("---------subjects----------");
		for (Subject s : subjects) {
			System.out.println("-" + s.getCourseCode());
		}
		System.out.println("there is (" + people.size()
				+ ") Person in the list");
		System.out.println("-------------------------");
	}
/**
 * @param name
 * Person's name
 * 
 * - show Person Name, Username, Email, and if he/she is Student, Professor or TeachingAssistant
 * 
 */
	protected void showPerson(String name) {
		for (Person p : people) {
			if (p.name.startsWith(name)) {
				System.out.print("-Name: " + p.name + " -Username: "
						+ p.username + " -Email: " + p.email + " ");
				if (p instanceof Student) {
					System.out.println(" -and he/she is a Student");
				}
				if (p instanceof Professor) {
					System.out.println(" -and he/she is a Professor");
				}
				if (p instanceof TeachingAssistant) {
					System.out.println(" -and he/she is a TeachingAssistant");
				}
			}
		}
	}
/**
 * @param name
 * Subject's Code
 * 
 * 
 * - show Subject's Code, groups number, Teachers, Students
 */
	protected void showSubject(String name) {
		for (Subject s : subjects) {
			Professor p = (Professor) s.getLecturer();
			if (s.getCourseCode().compareTo(name) == 0) {
				System.out.println("CourseCode: " + s.getCourseCode() + " Teachers: " + s.getLecturer());
				Group[] tr = s.getGroup();
				for (int i = 0; i < tr.length; i++) {
					System.out.println("Group number: " + tr[i].getNumber() + " TeachingAssistant: " + tr[i].GetTeachingAssistant());
					Student[] st = tr[i].getStudent();
					for (int t = 0; t < st.length; t++) {
						System.out.println(" Student: " + st[t].getName());
					}
				}
			}
		}
	}
}
