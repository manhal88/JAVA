import java.util.LinkedList;


class Task {
	int id , time , manpower , earliestStart , latestStart , status;
	String name ;

	int[] edges;
	LinkedList<Task> children;
	
	// cycle Status
	boolean collected;
	final int inProcess = -2;
	final int unChecked = -1;
	final int finished = 0;

	int totalTime;
	int totalSlack; // time from 0 til task end
	int edgeLength;

	public Task(int id, String name, int time, int manpower, int[] edges){
		this.id = id;
		this.name = name;
		this.time = time;
		this.manpower = manpower;
		this.edges = edges;
		this.children = new LinkedList<Task>();
		this.earliestStart = -1;
		this.status = unChecked;
		this.collected = false;

		this.edgeLength = edges.length;
	}

	// calculate Earliest StartTime
	public void calculateEarliestStartTime(){
		calculateEarliestStartTime(0);
	}

	private void calculateEarliestStartTime(int startTime) {
		if (startTime > earliestStart){
			earliestStart = startTime;
		} else {
			return; // longest path
		}
		for (Task task : children) {
			task.calculateEarliestStartTime(earliestStart + time);
		}
		if (earliestStart + time > totalSlack) {
			totalSlack = earliestStart + time;
		}
	}

	// calulate Latest StartTime
	public int calulateLatestStartTime() {
		latestStart = totalTime - time;
		for (Task task : children) {
			int latestTime = task.calulateLatestStartTime() - time;
			if (latestTime < latestStart) {
				latestStart = latestTime;
			}
		}
		return latestStart;
	}

	// find cycles, returnd id, Status
	public int findCycles() {
		if (status == inProcess) {
			return id;
		}
		if (status == finished) {
			return finished;
		}
		for (Task task : children) {
			status = inProcess;
			status = task.findCycles();
			if (status > finished) {
				return status;
			}
		}
		return finished;
	}

	// Adding a child to task
	public boolean addChildToTask(Task task) {
		return children.add(task);
	}

	// check if task is Critical
	public boolean isCriticalTask() {
		if (latestStart - earliestStart == 0) {
			return true;
		} else {
			return false;
		}
	}
}
