package inf1010.assignment;

public abstract class Person implements Comparable<Person> {
    String email;
    String name;
    String username;
    Person (String name, String username, String email) {
    	this.email = email;
    	this.name = name;
    	this.username = username;
    }
    public int compareTo(Person o){
    	return name.compareTo(o.name);
  
    }
    public String getEmail() {
		return email;
    	
    }
    public String getName() {
		return name;
    	
    }
    public String getUsername() {
		return username;
    	
    }
    public String toString () {
    	return name;
    }
}
