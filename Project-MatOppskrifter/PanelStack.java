import javax.swing.*;

import java.util.*;

public class PanelStack {
	private LinkedList<JPanel> backList;
	private LinkedList<JPanel> forList;
	private ListIterator<JPanel> backIter;
	private ListIterator<JPanel> forIter;
	private JPanel cur;
	private boolean clear = true;
	
	public PanelStack(JPanel start){
		backList = new LinkedList<JPanel>();
		backList.addFirst(start);
	}
	
	public void add(JPanel in){
		cur = in;
		backList.addFirst(in);
		backIter = backList.listIterator();
		backIter.next();
		if(clear)
			forList.clear();
		clear = true;
	}
	
	public void clear(){
		backList.clear();
		forList.clear();
		backIter = backList.listIterator();
		forIter = forList.listIterator();
	}
	
	public boolean hasBack(){
		if(backIter.hasNext() )
			return true;
		return false;
	}
	
	public boolean hasForward(){
		if(forIter == null)
			return false;
		return forIter.hasNext();
	}
	
	public JPanel forward() {
		clear = false;
		JPanel res = forIter.next();
		return res;
	}
	
	public JPanel back(){
		backIter.remove();
		JPanel res = backIter.next();
		backIter.remove();
		if(!forList.contains(cur) )
			forList.addFirst(cur);
		forIter = forList.listIterator();
		clear = false;
		return res;
	}
}
