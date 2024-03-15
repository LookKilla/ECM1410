package cycling;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Stage implements java.io.Serializable{
    private String Name;
    private String Description;
    private double Length;
    private LocalDateTime startTime;
    private int stageID;
    private StageType Type;
    private ArrayList<Checkpoint> Checkpoints;
    private ArrayList<Integer> LeaderBoard;
    private ArrayList<LocalTime> AdjustedTimes;
    private HashMap<Integer, LocalTime> RiderTimes;


    public int createStage(String name, String description,
                           double length, LocalDateTime startTime,
                           StageType type){
        this.Name = name;

        int id = generateID(name);
        this.stageID = id;

        this.Description = description;
        this.Length = length;
        this.startTime = startTime;
        this.Type = type;

        this.Checkpoints = new ArrayList<>();

        return id;
    }

    public int addCheckpoint(Double location, CheckpointType type, Double averageGradient,
                             Double length){
        Checkpoint newCheck = new Checkpoint();
        int id = newCheck.createCheckpoint(location, type, averageGradient, length);

        Checkpoints.add(newCheck);

        return id;
    }


    private int generateID(String name){
        int id = name.hashCode();

        if(id < 0){
            id = id * -1;
        }

        return id;
    }

    //getters


    public ArrayList<LocalTime> getAdjustedTimes() {return AdjustedTimes;}

    public HashMap<Integer, LocalTime> getRiderTimes() {return RiderTimes;}

    public String getName(){
        return Name;
    };

    public double getLength() {
        return Length;
    }

    public String getDescription() {
        return Description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public int getStageID() {
        return stageID;
    }

    public StageType getType() {
        return Type;
    }

    public ArrayList<Checkpoint> getCheckpoints(){
        return Checkpoints;
    }

    public ArrayList<Integer> getLeaderBoard() {return LeaderBoard; }

    public LocalDateTime getEndTime() {return endTime;}



    //setters
    public void setCheckpoints(ArrayList<Checkpoint> checkpoints) {
        Checkpoints = checkpoints;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public void setEndTime(LocalDateTime endTime) {this.endTime = endTime;}

    public void setLeaderBoard(ArrayList<Integer> leaderBoard) {
        this.LeaderBoard = leaderBoard;
    }

    public void setLength(double length) {
        this.Length = length;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setStageID(int stageID) {
        this.stageID = stageID;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}
