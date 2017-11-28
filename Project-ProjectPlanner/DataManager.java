import java.util.LinkedList;

class DataManager {
	ReadProject rp = new ReadProject();;
	Task[] tasks;
	int time = 0;
	int currentStaff = 0;

	public void run(String fileName) {
		rp.startProject(fileName);
		tasks = rp.getTasks();
		printProcess();
	}
	private void printProcess(){

		System.out.println("\n\t *** Suggested output ***\n");
		LinkedList<Task> criticalTask = new LinkedList<Task>();
		LinkedList<Task> slackTask = new LinkedList<Task>();
		LinkedList<Task> inProcess = new LinkedList<Task>(); // tasks in Process
		LinkedList<String> outPutString = new LinkedList<String>(); //inhold strings of (suggested output process), only for printing

		do {
			String currStat = ("Time:  "+ time+"\n");
			int processLength = inProcess.size();
			for (int y = 0; y < processLength; y++) {
				Task task = inProcess.pollFirst();
				if (task.time <= 0) {
					currStat += ("\t\t Finished:\t"+ task.id +"\n");
					currentStaff += task.manpower;
					for(Task c : task.children) c.edgeLength--;
				} else {
					inProcess.add(task);
				}
			}

			// add task to criticalTask or slackTask if collected and have no edge
			for (Task task : tasks) {
				if (task.edgeLength == 0 && !task.collected) {
					task.collected = true;
					if (task.isCriticalTask()) {
						criticalTask.add(task);
					} else {
						slackTask.add(task);
					}
				}
			}

			// starting task back from criticalTask to inProcess
			int criticalLength = criticalTask.size();
			for (int z = 0; z < criticalLength; z++) {
				Task task = criticalTask.pollFirst();
				inProcess.add(task);
				currStat += ("\t\t Started:\t"+ task.id +"\n");
				currentStaff += task.manpower;
			}

			// starting task back from slackTask to inProcess
			int slackLength = slackTask.size();
			for (int x = 0; x < slackLength; x++) {
				Task task = slackTask.pollFirst();
				currStat += ("\t\t Started:\t"+ task.id +"\n");
				currentStaff += task.manpower;
				inProcess.add(task);
			}

			// updats all tasks in Process
			for (Task tasks : inProcess) {
				tasks.time--;
			}
			
			// add currStat to outPutString if there is some change in currStat. ( 'Time:  (num)' have length == 11)
			if (currStat.length() > 11) {
				currStat += ("\t Total used staff:\t"+ currentStaff +"\n");
				outPutString.add(currStat);
				currentStaff = 0;
			}
			time++;
		} while (inProcess.size() != 0);

		// Printing all suggested output process
		for (String info : outPutString) {
			System.out.println(info);
		}
		System.out.printf("\n**** Shortest possible project execution is " + (time-1) +" ****\n");
	}
}
