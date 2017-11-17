import java.util.ArrayList;
import java.util.Arrays;


/**
 * e-postadr -> epostadr 
 * Obligretterne bruker disse testene for godkjenning
 */
class Oblig1Test {

	
	PersonListe personliste;
	
	Oblig1Test(PersonListe personliste) {
		this.personliste = personliste;
	}
	
	
	/**
	 * Tests the leggTilPerson method in class PersonListe
         * @param name             name of the person to add
	 * @param phone            phone number of the person
         * @param emailadr         e-mail addres(es) of the person 
	 * @param expectedResult   expected return value from leggTilPerson
	 * @throws Exception       if the return value from leggTilPerson does not 
	 *                         equal the expected.
	 */
	void leggTilPersonTest(String name, String phone, String[] emailadr, boolean expectedResult) 
	throws Exception {

		if (personliste.leggTilPerson(name, phone, emailadr) != expectedResult) {

			if (expectedResult)
				throw new Exception("leggTilPerson returned false when adding person \"" 
						+ name + "\" even though the person doesn't exist in the list");
			else
				throw new Exception("leggTilPerson returned true when adding person \"" 
						+ name + "\" even though the person exists in the list");
		}
	}

	/**
	 * Tests the fjernPerson method in class PersonListe
	 * @param name              name of the person to be removed
	 * @param expectedResult    expected return value from fjernPerson
	 * @throws Exception        if the return value from fjernPerson does not
	 *                          equal the expected
	 */
	void fjernPersonTest(String name, boolean expectedResult) throws Exception {

		if (personliste.fjernPerson(name) != expectedResult) {

			if (expectedResult)
				throw new Exception("fjernPerson returned false when removing person \"" 
						+ name + "\" even though the person exist in the list");
			else
				throw new Exception("fjernPerson returned true when removing person \"" 
						+ name + "\" even though the person shouldn't exists in the list");
		}
		
		
		// Checking that the removed person is no longer referenced in any persons friend list
		Person [] all = personliste.hentPersoner();
		
		if (all == null) {
			throw new NullPointerException("hentPersoner() should never return null. Return an empty array instead.");
		}
		
		for (Person p : all) {
			Person [] friends = p.hentVenner();
			
			if (friends == null) {
				throw new NullPointerException("hentVenner() should never return null. Return an empty array instead.");
			}

			for (Person f : friends) {
				if (name.equals(f.hentNavn())) {
					throw new Exception("The removed person "+name+" is still referenced in person " + p.hentNavn() + "s friend list.");
				}
			}
		}			
	}

	/**
	 * Tests the nyVenn method in class PersonListe
	 * @param name             name of the person to gain a friend
	 * @param nameFriend       name of the friend to add
	 * @param expectedResult   expected return value from nyVenn
	 * @throws Exception       if the return value from nyVenn does not 
	 *                         equal the expected
	 */
	void nyVennTest(String name, String nameFriend, boolean expectedResult) throws Exception {

		if (personliste.nyVenn(name, nameFriend) != expectedResult) {

			if (expectedResult)
				throw new Exception("nyVenn returned false when adding friend \""
						+nameFriend+"\" to person \"" + name + 
				"\" even though they aren't friends");
			else
				throw new Exception("nyVenn returned true when adding friend \""
						+nameFriend+"\" to person \"" + name + 
				"\" even though they are already friends");
		}
	}


	/**
	 * Tests the fjernVenn method in class PersonListe.
	 * @param name             name of the person to lose a friend
	 * @param friendName       name of the friend to remove
	 * @param expectedResult   expected return value from fjernVenn
	 * @throws Exception       if the return value does not equal the expected.
	 */
	void fjernVennTest(String name, String friendName, boolean expectedResult) throws Exception {

		if (personliste.fjernVenn(name, friendName) != expectedResult) {

			if (expectedResult)
				throw new Exception("fjernVenn returned false when removing \""
						+friendName+"\" as friend form person \"" + name +
				"\" even though they are friends");
			else
				throw new Exception("fjernVenn returned true when removing \""
						+friendName+"\" as friend from person \"" + name + 
				"\" even though they aren't friends");
		}
	}


	/**
	 * Tests the hentAntall method in class PersonListe
	 * @param extectedSize   the expected size of the list
	 * @throws Exception     if the size of the list is not equal the expected.
	 */
	void sizeTest(int extectedSize) throws Exception {

		if (personliste.hentAntall() != extectedSize) {
			throw new Exception("Expected size " + extectedSize + " but was "
					+ personliste.hentAntall());
		}
	}

	/**
	 * Tests that the persons returned by hentPersoner() in PersonListe correspond
	 * to the expected names in the variable sized array taken as argument. 
	 * @param names        the expected names in the list
	 * @throws Exception   if a person in the expected array is not found in 
	 *                     the returned array, or vice versa.
	 */
	void hentPersonerTest(String ... names) throws Exception {

		Person[] persons = personliste.hentPersoner();

		if (persons == null) {
			throw new NullPointerException("hentPersoner() should never return null. Return an empty array instead.");
		}
		
		ArrayList<Person> personList = new ArrayList<Person>(Arrays.asList(persons));

		for (String name : names) {

			boolean found = false;

			for (Person p : persons) {
				if (name.equals(p.hentNavn())) {
					personList.remove(p);
					found = true;
					break;
				}
			}

			if (!found) {
				throw new Exception("Person \"" + name + 
				"\" was not found in the list of persons.");
			}
		}

		// Test if there are names in the list that does not exist in the expected
		if (personList.size() > 0) {

			String nameList = "";

			for (Person p : personList) {
				nameList += "\"" + p.hentNavn() + "\" ";
			}

			throw new Exception("The following persons should not exist in the person list:" + nameList);
		}
	}

	/**
	 * Tests that the persons returned by hentPersoner() in PersonListe correspond
	 * to the expected names in the variable sized array taken as argument. 
	 * @param names        the expected names in the list
	 * @throws Exception   if a person in the expected array is not found in 
	 *                     the returned array, or vice versa.
	 */
	void hentVennerTest(String name, String ... friendNames) throws Exception {

		Person person = personliste.hentPerson(name);
		
		if (person == null)
			throw new Exception("Person " + name + " should exist, but was not found in the list");
		
		Person [] friendslist = person.hentVenner();
		
		if (friendslist == null) {
			throw new NullPointerException("hentVenner() should never return null. Return an empty array instead.");
		}
		
		ArrayList<Person> friendList = new ArrayList<Person>(Arrays.asList(friendslist));

		for (String friendName : friendNames) {

			boolean found = false;

			for (Person p : friendList) {
				if (friendName.equals(p.hentNavn())) {
					friendList.remove(p);
					found = true;
					break;
				}
			}

			if (!found) {
				throw new Exception("Person \"" + friendName + 
				"\" was not found in the list of friends for person " + name);
			}
		}

		// Test if there are names in the list that does not exist in the expected
		if (friendList.size() > 0) {

			String nameList = "";

			for (Person p : friendList) {
				nameList += "\"" + p.hentNavn() + "\" ";
			}

			throw new Exception("The following persons should not exist in the friend list:" + nameList);
		}
	}
	

	
	/**
	 * Runs some tests on the PersonListe class
	 * If the PersonListe does not behave the expected way, an exception is thrown
	 * The boolean value sent to the methods are the expected return values.
	 * @throws Exception   if any of the tests fail
	 */
	void testivei() throws Exception {

		

		leggTilPersonTest("Tuva",    "9293847", new String[] {"tuva@uio.no", "tuva@ifi.no"}, true);
		leggTilPersonTest("Magnus",  "3547257", new String[] {"magbus@uio.no", "mg@ifi.no"}, true);
		leggTilPersonTest("Erlend",  "4568382", new String[] {"ep@uio.no", "erl@ifi.no"}, true);
		leggTilPersonTest("Karoline","7567575", new String[] {"krl@uio.no", "karol@ifi.no"}, true);
		leggTilPersonTest("Jose",    "8765343", new String[] {"jose@uio.no", "js@ifi.no"}, true);
		leggTilPersonTest("Simen",   "2562344", new String[] {"sh@uio.no", "smn@ifi.no"}, true);
		leggTilPersonTest("Mikael",  "4563613", new String[] {"mst@uio.no", "mikke@ifi.no"}, true);
				
		// These are the expected persons in the person list

		hentPersonerTest("Tuva", "Magnus", "Erlend", "Karoline", "Jose", "Simen", "Mikael");

		// Here size should be 7
		sizeTest(7);

		// Test already added
		leggTilPersonTest("Tuva",        "9293847", new String[] {"tuva@uio.no", "tuva@ifi.no"}, false);
		leggTilPersonTest("Karoline",      "7567575", new String[] {"krl@uio.no", "karol@ifi.no"}, false);

		// Here size should be 7
		sizeTest(7);

		// Test removing non-existing person
		fjernPersonTest("nonexistingperson", false);

		// Test removing person
		fjernPersonTest("Karoline", true);

		// Test removing a person that has already been removed
		fjernPersonTest("Karoline", false);

		// Here size should be 6
		sizeTest(6);

		// Add three friends
		nyVennTest("Erlend", "Mikael", true);
		nyVennTest("Erlend", "Magnus", true);
		nyVennTest("Erlend", "Simen", true);

		// Already friends
		nyVennTest("Erlend", "Simen", false);

		// Tests that the person (first argument) has the friends he should have
		hentVennerTest("Erlend", new String[] {"Mikael", "Magnus", "Simen"});
		
		// Remove two friends
		fjernVennTest("Erlend", "Simen", true);
		fjernVennTest("Erlend", "Magnus", true);

		// They are no longer friends, so should return false
		fjernVennTest("Erlend", "Simen", false);
		
		// Should work to add as friend again
		nyVennTest("Erlend", "Simen", true);
		
		// Never were friends in the first place
		fjernVennTest("Karoline", "Mikael", false);
		
		// Tests that the person (first argument) has the friends he should have
		hentVennerTest("Erlend", new String[] {"Mikael", "Simen"});
				
		// Remove person Mikael, he should no longer be in Erlends friend list
		fjernPersonTest("Mikael", true);
				
		// Adding more persons
		leggTilPersonTest("Stein",      "4563613", new String[] {"sg@uio.no", "smg@ifi.no"}, true);
		leggTilPersonTest("Kristian",      "4563613", new String[] {"krstn@uio.no", "kris@ifi.no"}, true);
		
		// These are the expected persons in the person list
		hentPersonerTest("Tuva", "Magnus", "Erlend", "Jose", "Simen", "Stein", "Kristian");

		System.err.println("All tests run with no errors.");
	}
}
