package cycling;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Race implements java.io.Serializable{
    private int RaceID;
    private String Name;
    private String description;
    private double totalLength;
    private int noOfStages;
    private ArrayList<Stage> Stages;


    public int getRaceID(){return this.RaceID;};
    public String getName(){return this.Name;};
    public String getDescription(){return this.description;};
    public double getTotalLength(){return this.totalLength;};
    public int getNoOfStages(){return noOfStages;};
    public ArrayList<Stage> getStages(){return Stages;};


    public int createRace(String name, String description){

        this.Name = name;
        int raceID = generateRaceID();
        this.description = description;
        this.RaceID = generateRaceID();
        this.totalLength = 0;
        this.noOfStages = 0;

        return raceID;
    }

    public String getDetails(){
        String formattedString = String.format("Race ID: %d%nName: %s%nDescription: %s%nNumber of Stages: %d%nTotal Length: %.2f",
                RaceID, Name, description, Stages.size(), totalLength);

        return formattedString;
    }

    public int addStage(String name, String description,
                        double length, LocalDateTime startTime,
                        StageType type){
        Stage newStage = new Stage();
        int id = newStage.createStage(name, description, length, startTime, type);

        this.noOfStages = noOfStages + 1;
        this.totalLength = (int) (totalLength + newStage.getLength());

        Stages.add(newStage);

        return id;
    }

    public void removeStage(int stageID){
        int index = -1;

        for (int i = 0; i < Stages.size(); i++) {
            Stage temp = Stages.get(i);
            if (temp.getStageID() == stageID) {
                index = i;
                break;
            }
        }

        Stage selectStage = Stages.get(index);

        double length = selectStage.getLength();
        this.totalLength = totalLength - length;
        this.noOfStages = noOfStages - 1;

        Stages.remove(index);
    }



    private int generateRaceID() {
        int id = this.Name.hashCode();

        if (id < 0){
            id = id * -1;
        }

        return id;
    }
}
