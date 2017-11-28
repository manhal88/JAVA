
public class StringFinder {
	public static void main(String[] args) {

		if(args.length != 2){
			System.out.println("Wrong number of arguments, use: javac WildCards.java <needle>.txt <haystack>.txt");
			System.exit(0);
		} else {
			BoyerMooreHorspool bmh = new BoyerMooreHorspool();
			bmh.start(args[0], args[1]);
		}
	}
}
