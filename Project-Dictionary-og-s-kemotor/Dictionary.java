
public class Dictionary{
	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree();
		ReadFromFile rff = new ReadFromFile();
		Interface in = new Interface();
		rff.readFromFile(bst);
		in.Interface(bst);
	}
}
