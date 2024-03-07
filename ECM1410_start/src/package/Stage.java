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
}
