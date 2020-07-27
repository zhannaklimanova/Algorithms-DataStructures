package assignment2;
public class TrainNetwork {
    final int swapFreq = 2;
    TrainLine[] networkLines;

    public TrainNetwork(int nLines) {
        this.networkLines = new TrainLine[nLines];
    }
    
    public void addLines(TrainLine[] lines) {
        this.networkLines = lines;
    }
    
    public TrainLine[] getLines() {
        return this.networkLines;
    }
    
    public void dance() {
        System.out.println("The tracks are moving!");
        for (TrainLine line: this.getLines()) {
            line.shuffleLine();
            
            // Testing Printout
//          System.out.println("SHUFFLING"); 
//          line.printLine();
        }
    }
    
    public void undance() {
        for (TrainLine line: this.getLines()) {
            line.sortLine();
            
            // Testing Printout
//          System.out.println("SORTING");
//          line.printLine();
        }
        
    }
    
    public int travel(String startStation, String startLine, String endStation, String endLine) {
        
        TrainLine curLine = this.getLineByName(startLine); // stores the current line.
        TrainStation curStation = curLine.findStation(startStation); // stores the current station. 
        TrainStation previousStation = null; 
        TrainLine arrivalLine = this.getLineByName(endLine);
        TrainStation arrivalStation = arrivalLine.findStation(endStation);
        int hoursCount = 0;
        
        System.out.println("Departing from "+startStation);
        
        while (!((curLine.equals(arrivalLine)) && (curStation.equals(arrivalStation)))) {


            if (((hoursCount % 2) == 0) && (hoursCount != 0)) {
                dance();
            }
            
            TrainStation storeCurStation = curStation;
            curStation = curLine.travelOneStation(curStation, previousStation);
            hoursCount++;
            previousStation = storeCurStation;
            curLine = curStation.getLine();
            System.out.println("PENISSSS      "  + curLine.goingRight);

            
            if(hoursCount == 168) {
                System.out.println("Jumped off after spending a full week on the train. Might as well walk.");
                return hoursCount;
            }
            
            //prints an update on train's current location in the network.
            System.out.println("Traveling on line "+curLine.getName()+":"+curLine.toString());
            System.out.println("Hour "+hoursCount+". Current station: "+curStation.getName()+" on line "+curLine.getName());
            System.out.println("=============================================");
            
            }
            
            System.out.println("Arrived at destination after "+hoursCount+" hours!");
            
            return hoursCount;

    }
    
    
    //you can extend the method header if needed to include an exception. You cannot make any other change to the header.
    public TrainLine getLineByName(String lineName){
        for (TrainLine line: this.getLines()) {
            if (lineName.contentEquals(line.getName())) {
                return line;
            }
        }
        throw new LineNotFoundException("The line you have requested cannot be found!");
    }
    
  //prints a plan of the network for you.
    public void printPlan() {
        System.out.println("CURRENT TRAIN NETWORK PLAN");
        System.out.println("----------------------------");
        for(int i=0;i<this.networkLines.length;i++) {
            System.out.println(this.networkLines[i].getName()+":"+this.networkLines[i].toString());
            }
        System.out.println("----------------------------");
    }
}

//exception when searching a network for a LineName and not finding any matching Line object.
class LineNotFoundException extends RuntimeException {
       String name;

       public LineNotFoundException(String n) {
          name = n;
       }

       public String toString() {
          return "LineNotFoundException[" + name + "]";
       }
    }