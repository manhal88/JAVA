class Oblig2 {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Use : java Oblig2 <projectName.txt>");
		} else {
			DataManager dm = new DataManager();
			dm.run(args[0]);
		}
	}
}
