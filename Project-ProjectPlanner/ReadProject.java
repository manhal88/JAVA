import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


class ReadProject {
	//private String file;
	File file;
	Scanner scanner;
	
	private Task[] tasks;
	
	public void startProject(String filename) {
		readFromFile(filename); // readFromFile
		addToParents(); // add the node to their Parents
		findCyclesAndCalculateTime();// find cycles and calculate Earlig and Latest Time
		printTasks();
	}

	private void findCyclesAndCalculateTime() {
		
		// find Cycles
		for (Task task : tasks) {
			if (task.edgeLength == 0) {
				int taskStatus = task.findCycles();
				if (taskStatus > 0) {
					System.out.println("Cycles found at " + taskStatus);
					System.exit(0);
				}
				
				// find Earliest StartTime
				task.calculateEarliestStartTime();
			}
		}
		
		// update all tasks totalTime
		int totTime = 0;
		for (Task task : tasks) totTime = task.totalSlack;
		for (Task task : tasks) task.totalTime = totTime;
		
		
		// find Latest StartTime
		for (Task task : tasks) { 
			if (task.edgeLength == 0) {
				task.calulateLatestStartTime();

			}
		}
	}
	private void printTasks() {
		System.out.println("\n\t*** Tasks Info ***\n");
		System.out.println("----------------------------------------------");
		for (Task task : tasks) {
			System.out.println("Task ID:\t" + task.id);
			System.out.println("\t Name:\t" + task.name);
			System.out.println("\t Time:\t\t" + task.time);
			System.out.println("\t Manpower:\t" + task.manpower);
			System.out.println("\t EarliestStart:\t" + task.earliestStart);
			System.out.println("\t LatestStart:\t" + task.latestStart);
			System.out.println("\t Slack:\t" + (task.latestStart - task.earliestStart));
			System.out.println("----------------------------------------------");
		}
	}

	private void readFromFile(String filename) {
		//file = new File("buildhouse1.txt");
		file = new File(filename);
		String thisLine;
		String[] word;
		String[] temp;
		int[] edges;
		int teller = 0;
		try {
			if ( !file.exists()) {
				System.out.println("Du mangler ");//<<<<<<<<<<<<<<<<<<
				System.exit(0);
			} else {
				scanner = new Scanner(file);
				thisLine = scanner.nextLine();
				tasks = new Task[Integer.parseInt(thisLine)];
				while (scanner.hasNextLine()) {
					thisLine = scanner.nextLine();
					if(!thisLine.equals("")){
						word = thisLine.split("\\s+");

						int id = Integer.parseInt(word[0]);
						String name = word[1];
						int time = Integer.parseInt(word[2]);
						int manpower = Integer.parseInt(word[3]);

						temp = new String[word.length -5];
						System.arraycopy(word, 4, temp, 0, word.length-5);
						edges = new int[temp.length];
						for(int i = 0 ; i < temp.length; i++){
							edges[i] = Integer.parseInt(temp[i]);
						}
			
						Task t = new Task(id, name, time, manpower, edges);
						tasks[teller++] = t;
					}
				}
				scanner.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// add a task to their Parents
	private void addToParents() {
		for (Task task : tasks) {
			for (int i = 0; i < task.edgeLength; i++) {
				tasks[task.edges[i]-1].addChildToTask(task);
			}
		}
	}

	public Task[] getTasks() {
		return tasks;
	}
}
