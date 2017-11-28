import java.util.*;
import java.io.*;


public class tulle {

    public static void main(String[] args)throws Exception {

	    PersonList personlist = new PersonList();
	    Scanner scan = new Scanner (System.in);
	    String command = "";

	    while (!command.equals("exit")) {
		System.out.printf("Type help to see available commands!%n");
		System.out.printf("Chose an option: ");
		command = scan.nextLine();
		try {
		    readInput(command, personlist);
		} catch (NullPointerException npe) {
		    System.out.printf("Something went wrong!%n");
		}
	    }
	}


    /**
     * Takes input from command line or from file, and calls the right functions.
     * @param command String input from command line or from text file.
     * @param personlist Pointer to personlist object.
     */
    static void readInput(String command, PersonList personlist) {
	String[] argument = new String[3];
	StringTokenizer st = new StringTokenizer(command);
	if (st.hasMoreTokens()) argument[0] = st.nextToken();
	if (st.hasMoreTokens()) argument[1] = st.nextToken();
	if (st.hasMoreTokens()) argument[2] = st.nextToken();

	if (argument[0].equals("help")) { // Prints the help menu.
	    System.out.printf("Available commands:%n");
	    System.out.printf("\t%-35s - %s%n", "add <name> <number>", "add a person");
	    System.out.printf("\t%-35s - %s%n", "phone <name> <number>", "change phone number of a person");
	    System.out.printf("\t%-35s - %s%n", "remove <name>", "remove a person");
	    System.out.printf("\t%-35s - %s%n", "friends <name> <name-friend>", "add a friendship between to persons");
	    System.out.printf("\t%-35s - %s%n", "unfriends <name> <non-friend>", "remove a friendship between two persons");
	    System.out.printf("\t%-35s - %s%n", "show <name>", "show info about a person");
	    System.out.printf("\t%-35s - %s%n", "listall", "list all registered persons");
	    System.out.printf("\t%-35s - %s%n", "tofile <filename>", "write data to file");
	    System.out.printf("\t%-35s - %s%n", "fromfile <filename>", "read data from file");
	    System.out.printf("\t%-35s - %s%n", "help", "print this help");
	    System.out.printf("\t%-35s - %s%n", "exit", "exit program");
	} else if (argument[0].equals("add")) { // !add
	    if (argument[1] != null && personlist.addPerson(argument[1], argument[2])) {
		System.out.printf("Success! %s was added!%n", argument[1]);
	    } else System.out.printf("Something went wrong!%n");
	} else if (argument[0].equals("phone")) { // !phone
	    if (personlist.isPerson(argument[1])) {
		personlist.getPerson(argument[1]).addPhone(argument[2]);
		System.out.printf("Phone number was changed!%n");
	    } else System.out.printf("Person does not exist!%n");
	} else if (argument[0].equals("remove")) { // !remove
	    if (personlist.isPerson(argument[1])) {
		Person p = personlist.firstPerson;
		while (p != null) {
		    personlist.removeFriend(p.getName(), argument[1]);
		    p = p.nextPerson;
		}
		personlist.removePerson(argument[1]);
		System.out.printf("%s was removed.%n", argument[1]);
	    } else {
		System.out.printf("Something went wrong!%n");
	    }
	} else if (argument[0].equals("friends")) { // !friends
	    if (personlist.addFriend(argument[1], argument[2])) {
		System.out.printf("Success! %s is now friend with %s.%n", argument[2], argument[1]);
	    } else System.out.printf("Something went wrong!%n");
	} else if (argument[0].equals("unfriends")) { // !unfriends
	    if (personlist.removeFriend(argument[1], argument[2])) {
		System.out.printf("Success! %s is no longer friend with %s.%n", argument[2], argument[1]);
	    } else System.out.printf("Something went wrong!%n");
	} else if (argument[0].equals("show")) { // !show
	    if (personlist.isPerson(argument[1])) {
		System.out.printf("%-10s%s%n%-10s%s%n%-10s", "Name:", personlist.getPerson(argument[1]).getName(), "Phone:", personlist.getPerson(argument[1]).getPhone(), "Friends:");
		if (personlist.getPerson(argument[1]).getNumberOfFriends() == 0) {
		    System.out.printf("Sadly, I have no friends..%n");
		} else {
		    for (int i = 0; i < personlist.getPerson(argument[1]).getFriends().length; i++) {
			System.out.printf("%s, ", personlist.getPerson(argument[1]).getFriends()[i].getName());
		    }
		    System.out.printf("%n");
		}
	    } else System.out.printf("Person does not exist!%n");
	} else if (argument[0].equals("listall")) {
	    if (personlist.getSize() == 0) {
		System.out.printf("List is empty!%n");
	    } else {
		Person p = personlist.firstPerson;
		while (p != null) {
		    System.out.printf("\t%s%n", p.getName());
		    p = p.nextPerson;
		}
	    }
	} else if (argument[0].equals("tofile")) { // !tofile
	    try{
		FileWriter fstream = new FileWriter(argument[1]);
		PrintWriter out = new PrintWriter(fstream);

		if (personlist.getSize() == 0) {
		    out.printf("");
		} else {
		    for (int i = 0; i < personlist.getPersons().length; i++) {
			out.printf("add %s %s%n", personlist.getPersons()[i].getName(), personlist.getPersons()[i].getPhone());
			if (personlist.getPersons()[i].getNumberOfFriends() == 0) {
			    out.printf("");
			} else {
			    for (int j = 0; j < personlist.getPersons()[i].getFriends().length; j++) {
				out.printf("friends %s %s%n", personlist.getPersons()[i].getName(), personlist.getPersons()[i].getFriends()[j].getName());
			    }
			}
		    }
		    out.close();
		}
	    }catch (Exception e) {
		System.err.printf("Something went wrong!%n");
	    }
	} else if (argument[0].equals("fromfile")) { // !fromfile
	    try {
		Scanner scan = new Scanner(new File(argument[1]));
		String read;
		while (scan.hasNextLine()) {
		    read = scan.nextLine();
		    readInput(read, personlist);
		}
	    } catch (Exception e) {
		System.out.printf("Something went wrong!%n");
	    }
	}
    }
} // End of LinkedList.



class Person {

    private String name, phone;
    Person nextPerson;
    FriendList firstFriend;

    Person(String name, String phone) {
	this.name = name;
	this.phone = phone;
    }

    public String getName() {
	return name;
    }

    public String getPhone() {
	return phone;
    }

    public void addPhone(String phone) {
	this.phone = phone;
    }

    /**
     * Get all the persons in the persons friend list.
     * @return friends of this person.
     */
    public Person[] getFriends() {
	Person[] friends = new Person[getNumberOfFriends()];
	FriendList f = firstFriend;
	if (getNumberOfFriends() != 0){
	    for (int i = 0; i < friends.length; i++) {
		friends[i] = f.friend;
		f = f.nextFriend;
	    }
	    return friends;
	} else return null;
    }

    /**
     * Get number of friends in the persons friend list.
     * @return number of friends.
     */
    public int getNumberOfFriends() {
	int count = 0;
	FriendList f = firstFriend;
	while (f != null) {
	    count++;
	    f = f.nextFriend;
	}
	return count;
    }
} // End of Person.

class PersonList {

    Person firstPerson;

    /**
     * Add a new person to the list
     * @param name name of the person
     * @param phone phone number of the person
     * @return true if person was added successfully.
     * false if the person is already in the list
     */
    public boolean addPerson(String name, String phone) {
	if (firstPerson == null) {
	    firstPerson = new Person(name, phone);
	    return true;
	} else if (!isPerson(name)) {
	    for (Person p = firstPerson; ;p = p.nextPerson) {
		if (p.nextPerson == null) {
		    p.nextPerson = new Person(name, phone);
		    return true;
		}
	    }
	}
	return false;
    }

    /**
     * Remove a person from the list.
     * @param name name of the person
     * @return true if person was successfully removed
     * false if the person doesn't exist.
     */
    public boolean removePerson(String name) {
	if (isPerson(name) && firstPerson.getName().equals(name)) {
	    firstPerson = firstPerson.nextPerson;
	    return true;
	} else {
	    for (Person p = firstPerson; p != null ;p = p.nextPerson) {
		if (isPerson(name) && p.nextPerson.getName().equals(name)) {
		    p.nextPerson = p.nextPerson.nextPerson;
		    return true;
		}
	    }
	}
	return false;
    }

    /**
     * Add a person as a friend to another person
     * @param name name of the person to gain a friend
     * @param friendName name of the new friend
     * @return
     */
    public boolean addFriend(String name, String friendName) {
	if (isFriend(name, friendName)) return false;
	else if (getPerson(name).firstFriend == null && isPerson(name) && isPerson(friendName)) {
	    getPerson(name).firstFriend = new FriendList(getPerson(friendName));
	    return true;
	} else if (isPerson(name) && isPerson(friendName)) {
	    for (FriendList f = getPerson(name).firstFriend; ; f = f.nextFriend) {
		if (f.nextFriend == null) {
		    f.nextFriend = new FriendList(getPerson(friendName));
		    return true;
		}
	    }
	}
	return false;
    }

    /**
     * Remove a friend from a person
     * @param name name of the person to lose a friend
     * @param friendName name of the friend to be removed
     * @return
     */
    public boolean removeFriend(String name, String friendName) {
	if (!isFriend(name, friendName)) return false;
	if (getPerson(name).firstFriend == null) {
	    return false;
	} else if (isPerson(name) && isPerson(friendName) && getPerson(name).firstFriend.friend.getName().equals(friendName)) {
	    getPerson(name).firstFriend = getPerson(name).firstFriend.nextFriend;
	    return true;
	} else if (isPerson(name) && isPerson(friendName)) {
	    for (FriendList f = getPerson(name).firstFriend; ; f = f.nextFriend) {
		if (f.nextFriend.friend.getName().equals(friendName)) {
		    f.nextFriend = f.nextFriend.nextFriend;
		    return true;
		}
	    }
	}
	return false;
    }

    /**
     * Get the current number of persons in the list.
     * @return
     */
    public int getSize() {
	Person p = firstPerson;
	int count = 0;
	while (p != null) {
	    count++;
	    p = p.nextPerson;
	}
	return count;
    }

    /**
     * Get an array of persons currently in the list.
     * @return
     */
    public Person[] getPersons() {
	Person[] persons = new Person[getSize()];
	Person p = firstPerson;
	for (int i = 0; i < persons.length; i++) {
	    persons[i] = p;
	    p = p.nextPerson;
	}
	return persons;
    }

    /**
     * Get a person.
     * @param name name of the person to get
     * @return
     */
    public Person getPerson(String name) {
	for (Person p = firstPerson;p != null ;p = p.nextPerson) {
	    if (p.getName().equals(name)) return p;
	}
	return null;
    }

    /**
     * Cheks if person excists.
     * @param name name of person to check.
     * @return boolean value.
     */
    public boolean isPerson(String name) {
	for (Person p = firstPerson; p != null ; p = p.nextPerson) {
	    if (p.getName().equals(name)) return true;
	}
	return false;
    }

    /**
     * Cheks if friend relation already excists.
     * @param name name of person.
     * @param friendName name of friend.
     * @return bolean value.
     */
    public boolean isFriend(String name, String friendName) {
	if (isPerson(name) && isPerson(friendName)) {
	    for (int i = 0; i < getPerson(name).getNumberOfFriends(); i++) {
		if (getPerson(name).getFriends()[i].getName().equals(friendName)) return true;
	    }
	}
	return false;
    }
} // End of PersonList.

class FriendList {

    Person friend;
    FriendList nextFriend;

    FriendList(Person friendName) {
	friend = friendName;
    }
} // End of FriendList.
