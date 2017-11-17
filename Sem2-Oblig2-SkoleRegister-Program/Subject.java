package inf1010.assignment;
import java.util.Iterator;
import inf1010.lib.two.IfiCollection;

public class Subject implements Comparable<Subject> {

   String courseCode;
   Teacher lecturer;
   IfiCollection<Group> groups; 
   int groupeTeller = 0;
   
   public Subject(String coursCode, Teacher professor) {
	   this.groups = new OrderedSet<Group>();
	   this.courseCode = coursCode;
	   this.lecturer = professor;
}

public int compareTo(Subject o) {
	   return courseCode.compareTo(o.courseCode);
   }
   public boolean enrollStudent(Student s, int group) {
	   if (GetGroup(group) == null) {
		   return false;
	   }
	   if (GetGroup(group).addStudent(s)) {
		   return true;
	   }
	   return false;
   }
   public void addGroup(Group g) {
	   if (groups.add(g)) {
		   groupeTeller++;
	   }
   }
   public String getCourseCode() {
	return courseCode;
	   
   }
/**
 * 
 * 
 * @param group
 * group name
 * @return the group if this grup is in
 * 
 * eles return null
 */
   public Group GetGroup(int group){
	   Iterator<Group> itr = groups.iterator();
	   while (itr.hasNext()){
		   Group tmp = itr.next();
		   if (tmp.number == group){
			   return tmp;
		   }
	   }
	   return null;
   }

   public Group[] getGroup() {
	   return groups.toArray(new Group[groups.size()]);
		
   }
   /*public boolean containsGroup(int group){
	   Iterator<Group> itr = groups.iterator();
	   while (itr.hasNext()){
		   Group tmp = itr.next();
		   if (tmp.getNumber() == group){
			   return true;
		   }
	   }
	   return false;
   }*/
   
   public Teacher getLecturer() {
	return lecturer;
	
   }
   public int getStudentBodySize() {
	   int counter=0;
	   for ( Group g : groups) {
		   for ( Student s : g.getStudent()){
			   counter++;
		   }
	   }
	return counter;
	
   }
   public Student[] getStudents() {
	   int counter = 0;
	   Student[] t = new Student[getStudentBodySize()];
	   for ( Group g : groups) {
		   for ( Student s : g.getStudent()){
			   t[counter]=s;
			   counter++;
		   }
	   }
	   return t;
   }
   public Teacher[] getTeachers() {
	   int counter = 0;
	   Teacher[] te = new Teacher[getStudentBodySize()];
	   for ( Group g : groups) {
		   te[counter] = g.GetTeachingAssistant();
		   counter++;
	   }
	   return te;   
   } 
}
