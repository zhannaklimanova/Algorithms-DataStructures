package assignment2;
import java.util.Arrays;
import java.util.Random;

public class TrainLine {

	private TrainStation leftTerminus;
	private TrainStation rightTerminus;
	private String lineName;
	public boolean goingRight;
	public TrainStation[] lineMap;
	public static Random rand;

	public TrainLine(TrainStation leftTerminus, TrainStation rightTerminus, String name, boolean goingRight) {
		this.leftTerminus = leftTerminus;
		this.rightTerminus = rightTerminus;
		this.leftTerminus.setLeftTerminal();
		this.rightTerminus.setRightTerminal();
		this.leftTerminus.setTrainLine(this);
		this.rightTerminus.setTrainLine(this);
		this.lineName = name;
		this.goingRight = goingRight; 

		this.lineMap = this.getLineArray();
	}

	public TrainLine(TrainStation[] stationList, String name, boolean goingRight)
	/*
	 * Constructor for TrainStation input: stationList - An array of TrainStation
	 * containing the stations to be placed in the line name - Name of the line
	 * goingRight - boolean indicating the direction of travel
	 */
	{
		TrainStation leftT = stationList[0];
		TrainStation rightT = stationList[stationList.length - 1];

		stationList[0].setRight(stationList[stationList.length - 1]);
		stationList[stationList.length - 1].setLeft(stationList[0]);

		this.leftTerminus = stationList[0];
		this.rightTerminus = stationList[stationList.length - 1];
		this.leftTerminus.setLeftTerminal();
		this.rightTerminus.setRightTerminal();
		this.leftTerminus.setTrainLine(this);
		this.rightTerminus.setTrainLine(this);
		this.lineName = name;
		this.goingRight = goingRight;

		for (int i = 1; i < stationList.length - 1; i++) {
			this.addStation(stationList[i]);
		}

		this.lineMap = this.getLineArray();
	}

	public TrainLine(String[] stationNames, String name,
			boolean goingRight) {/*
									 * Constructor for TrainStation. input: stationNames - An array of String
									 * containing the name of the stations to be placed in the line name - Name of
									 * the line goingRight - boolean indicating the direction of travel
									 */
		TrainStation leftTerminus = new TrainStation(stationNames[0]);
		TrainStation rightTerminus = new TrainStation(stationNames[stationNames.length - 1]);

		leftTerminus.setRight(rightTerminus);
		rightTerminus.setLeft(leftTerminus);

		this.leftTerminus = leftTerminus;
		this.rightTerminus = rightTerminus;
		this.leftTerminus.setLeftTerminal();
		this.rightTerminus.setRightTerminal();
		this.leftTerminus.setTrainLine(this);
		this.rightTerminus.setTrainLine(this);
		this.lineName = name;
		this.goingRight = goingRight;
		for (int i = 1; i < stationNames.length - 1; i++) {
			this.addStation(new TrainStation(stationNames[i]));
		}

		this.lineMap = this.getLineArray();

	}

	// adds a station at the last position before the right terminus
	public void addStation(TrainStation stationToAdd) {
		TrainStation rTer = this.rightTerminus;
		TrainStation beforeTer = rTer.getLeft();
		rTer.setLeft(stationToAdd);
		stationToAdd.setRight(rTer);
		beforeTer.setRight(stationToAdd);
		stationToAdd.setLeft(beforeTer);

		stationToAdd.setTrainLine(this);

		this.lineMap = this.getLineArray();
	}

	public String getName() {
		return this.lineName;
	}

	public int getSize() {
		int counter = 0;
		TrainStation currentStation = getLeftTerminus();
		
		while(currentStation.getRight() != null) {
			currentStation = currentStation.getRight();
			counter++;
		}
		counter++;
		return counter; 
	}

	public void reverseDirection() {
		this.goingRight = !this.goingRight;
	}

	
	public TrainStation travelOneStation(TrainStation current, TrainStation previous) {	
		TrainStation stationCurrent = findStation(current.getName());
		
		if ((stationCurrent.hasConnection == true) && (!stationCurrent.getTransferStation().equals(previous))) {
//			stationCurrent.getTransferStation().setTrainLine(stationCurrent.getTransferStation().getTransferLine());
			return stationCurrent.getTransferStation(); 
		}
		else {
			return getNext(current); 
		}
	}

	
	public TrainStation getNext(TrainStation station) {
		TrainStation startStation = findStation(station.getName());
		
		// cases 
		/* case 1: (not an edge case) goingRight == true so we go right; else goingRight == false so we go left 
		   case 2: (edge cases) goingRight == true but we're at rightTerminus so we go left and goingRight becomes false 
		                        goingRight == false but we're at the rightTermins so we go left and goingRight remains false
		   case 3: (edge cases) goingRight == false but we're at the leftTermins so we go right and goingRight becomes true
		                        goingRight == true but we're at the leftTerminus so we go right and goingRight remains true  	
		*/
		if (startStation != null) {
			if (this.goingRight == true) {
				if (startStation.isRightTerminal()) {
					this.reverseDirection();
					return startStation.getLeft();
				}
				
//				else if (startStation.isLeftTerminal()) {
//					return startStation.getRight();
//				}
				
				else {
					return startStation.getRight();
				}
			}
			else { // this.goingRight == false
				if (startStation.isLeftTerminal()) {
					this.reverseDirection();
					return startStation.getRight();
				}
				
//				else if (startStation.isRightTerminal()) {
//					return startStation.getLeft();
//				}
				
				else {
					return startStation.getLeft();
				}
	 		} 
		}
		throw new StationNotFoundException("The station you requested cannot be found!");
	}

	public TrainStation findStation(String name) {
		TrainStation currentStation = this.getLeftTerminus();
		
		while(currentStation != null) {
			if (currentStation.getName().equals(name)) {
				return currentStation;
			}
			currentStation = currentStation.getRight();
		}
		throw new StationNotFoundException("The station you have requested cannot be found on this line!");  
	}

	public void sortLine() {
		this.lineMap = insertionSort(this.getLineArray());
		adaptFields(this.lineMap);
//		printLine(); // for testing linkedlist purposes
	}
	
	public TrainStation[] insertionSort(TrainStation[] stations) {
		for (int k = 1; k < stations.length; k++) {
			TrainStation elementK = stations[k];
			int i = k; 
			
			
			while((i > 0) && (elementK.getName().charAt(0) < stations[i-1].getName().charAt(0))) {
				stations[i] = stations[i-1];
				i--;
			}
			stations[i] = elementK;
		}
		return stations;
	}

	public TrainStation[] getLineArray() {
		this.lineMap = new TrainStation[this.getSize()];
		TrainStation currentStation = this.getLeftTerminus();
		
		for (int i = 0; i < this.lineMap.length; i++) {
			this.lineMap[i] = currentStation;
			currentStation = currentStation.getRight();
		}
		return this.lineMap; 
	}

	private TrainStation[] shuffleArray(TrainStation[] array) {
		int seed = 11;
		Random rand = new Random(seed);

		for (int i = 0; i < array.length; i++) {
			int randomIndexToSwap = rand.nextInt(array.length);
			TrainStation temp = array[randomIndexToSwap];
			array[randomIndexToSwap] = array[i];
			array[i] = temp;
		}
		this.lineMap = array;
		return array;
	}

	public void shuffleLine() {

		TrainStation[] lineArray = this.getLineArray(); // lineArray/shuffledArray points to the same thing as the lineMap array
		TrainStation[] shuffledArray = shuffleArray(lineArray);
		adaptFields(this.lineMap);

	}

	public void adaptFields(TrainStation[] stations) {

		for (int i = 0; i < stations.length; i++) {
			if (i == 0) {
				stations[0].setNonTerminal();
				stations[0].setLeftTerminal();
				stations[0].setLeft(null);
				stations[0].setRight(stations[1]);
			}
			else if (i == stations.length-1) {
				stations[stations.length-1].setNonTerminal();
				stations[stations.length-1].setRightTerminal();
				stations[stations.length-1].setRight(null);
				stations[stations.length-1].setLeft(stations[stations.length-2]);
			}
			else {
				stations[i].setNonTerminal();
				stations[i].setLeft(stations[i-1]);
				stations[i].setRight(stations[i+1]);
			}
		}
		this.leftTerminus = stations[0];
		this.rightTerminus = stations[stations.length-1];
	}
	
	public String toString() {
		TrainStation[] lineArr = this.getLineArray();
		String[] nameArr = new String[lineArr.length];
		for (int i = 0; i < lineArr.length; i++) {
			nameArr[i] = lineArr[i].getName();
		}
		return Arrays.deepToString(nameArr);
	}

	public void printLine() {
		System.out.println("1 + leftTerminus" + this.getLeftTerminus().getName());
		System.out.println("2" + this.getLeftTerminus().getRight().getName());
		System.out.println("3" + this.getLeftTerminus().getRight().getRight().getName());
		System.out.println("4" + this.getLeftTerminus().getRight().getRight().getRight().getName());
		System.out.println("5 + rightTerminus" + this.getRightTerminus().getName());
	}
	public boolean equals(TrainLine line2) {

		// check for equality of each station
		TrainStation current = this.leftTerminus;
		TrainStation curr2 = line2.leftTerminus;

		try {
			while (current != null) {
				if (!current.equals(curr2))
					return false;
				else {
					current = current.getRight();
					curr2 = curr2.getRight();
				}
			}

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public TrainStation getLeftTerminus() {
		return this.leftTerminus;
	}

	public TrainStation getRightTerminus() {
		return this.rightTerminus;
	}
}

//Exception for when searching a line for a station and not finding any station of the right name.
class StationNotFoundException extends RuntimeException {
	String name;

	public StationNotFoundException(String n) {
		name = n;
	}

	public String toString() {
		return "StationNotFoundException[" + name + "]";
	}
}
