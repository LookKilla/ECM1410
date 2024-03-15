package cycling;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;


public class CyclingPortalImpl implements CyclingPortal{

    public ArrayList<Team> Teams;
    public ArrayList<Race> Races;
    public ArrayList<Integer> raceIDs;

    public ArrayList<Integer> riderIDs;
    public ArrayList<Riders> Riders;
    public int lastUsedRiderID;


    public CyclingPortalImpl(){
        riderIDs = new ArrayList<>();
        Riders = new ArrayList<>();

        raceIDs = new ArrayList<>();
        Races = new ArrayList<>();

        Teams = new ArrayList<>();
    }

    public void removeRaceByName(String name) throws NameNotRecognisedException {
        int index = -1;

        for (int i = 0; i < Races.size(); i++) {
            Race temp = Races.get(i);
            if (Objects.equals(temp.getName(), name)) {
                Races.remove(i);
                break;
            }
        }
    }

    @Override
    public int[] getRidersGeneralClassificationRank(int raceId) throws IDNotRecognisedException {
        return new int[0];
    }

    @Override
    public LocalTime[] getGeneralClassificationTimesInRace(int raceId) throws IDNotRecognisedException {
        return new LocalTime[0];
    }

    @Override
    public int[] getRidersPointsInRace(int raceId) throws IDNotRecognisedException {
        return new int[0];
    }

    @Override
    public int[] getRidersMountainPointsInRace(int raceId) throws IDNotRecognisedException {
        return new int[0];
    }

    @Override
    public int[] getRidersPointClassificationRank(int raceId) throws IDNotRecognisedException {
        return new int[0];
    }

    @Override
    public int[] getRidersMountainPointClassificationRank(int raceId) throws IDNotRecognisedException {
        return new int[0];
    }

    @Override
    public int[] getRaceIds() {
        int n = Races.size();
        int[] arrRaceIDs = new int[n];

        for (int i=0; i<n;i++){
            arrRaceIDs[i] = Races.get(i).getRaceID();
        }

        return arrRaceIDs;
    }

    @Override
    public int createRace(String name, String description) throws IllegalNameException, InvalidNameException {
        Race createdRace = new Race();
        int RaceID = createdRace.createRace(name, description);

        raceIDs.add(RaceID);
        Races.add(createdRace);
        return RaceID;
    }

    @Override
    public String viewRaceDetails(int raceId) throws IDNotRecognisedException {
        int index = -1;

        for (int i = 0; i < Races.size(); i++) {
            Race temp = Races.get(i);
            if (temp.getRaceID() == raceId) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new IDNotRecognisedException("ID not recognized: " + raceId);
        }

        Race slectRace = Races.get(index);

        String formatString = slectRace.getDetails();

        return formatString;
    }

    @Override
    public void removeRaceById(int raceId) throws IDNotRecognisedException {
        int index = -1;

        for (int i = 0; i < Races.size(); i++) {
            Race temp = Races.get(i);
            if (temp.getRaceID() == raceId) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new IDNotRecognisedException("ID not recognized: " + raceId);
        }

        // Remove the race at the found index
        Races.remove(index);
    }

    @Override
    public int getNumberOfStages(int raceId) throws IDNotRecognisedException {
        int index = -1;

        for (int i = 0; i < Races.size(); i++) {
            Race temp = Races.get(i);
            if (temp.getRaceID() == raceId) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new IDNotRecognisedException("ID not recognized: " + raceId);
        }

        Race slectRace = Races.get(index);

        return slectRace.getNoOfStages();
    }

    @Override
    public int addStageToRace(int raceId, String stageName, String description, double length, LocalDateTime startTime, StageType type) throws IDNotRecognisedException, IllegalNameException, InvalidNameException, InvalidLengthException {
        int index = -1;

        for (int i = 0; i < Races.size(); i++) {
            Race temp = Races.get(i);
            if (temp.getRaceID() == raceId) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new IDNotRecognisedException("ID not recognized: " + raceId);
        }

        Race temp = Races.get(index);

        int StageId = temp.addStage(stageName, description, length, startTime, type);

        return StageId;
    }

    @Override
    public int[] getRaceStages(int raceId) throws IDNotRecognisedException {
        int index = -1;

        for (int i = 0; i < Races.size(); i++) {
            Race temp = Races.get(i);
            if (temp.getRaceID() == raceId) {
                index = i;
                break;
            }
        }

        Race selectedrace = Races.get(index);


        int n = selectedrace.getStages().size();
        int[] arrStages = new int[n];

        for (int i=0; i<n-1;i++){
            arrStages[i] = selectedrace
                    .getStages().get(i)
                    .getStageID();
        }

        return arrStages;
    }

    @Override
    public double getStageLength(int stageId) throws IDNotRecognisedException {
        int index = -1;
        double length = 0;

        // Incrementing through Race Objects
        for (Race selectRace : Races) {
            // Incrementing through Stage Objects
            for (int j = 0; j < selectRace.getStages().size(); j++) {
                Stage selectStage = selectRace.getStages().get(j);

                if (selectStage.getStageID() == stageId) {
                    length = selectStage.getLength();
                    index = j;
                    break;
                }
            }


        }

        if (index == -1) {
            throw new IDNotRecognisedException("ID not recognized: " + stageId);
        }

        return length;
    }

    @Override
    public void removeStageById(int stageId) throws IDNotRecognisedException {
        int index = -1;

        // Incrementing through Race Objects
        for (Race selectRace : Races) {
            // Incrementing through Stage Objects
            for (int j = 0; j < selectRace.getStages().size(); j++) {
                Stage selectStage = selectRace.getStages().get(j);

                if (selectStage.getStageID() == stageId) {
                    selectRace.removeStage(stageId);
                    break;
                }
            }
        }

        if (index == -1) {
            throw new IDNotRecognisedException("ID not recognized: " + stageId);
        }

    }

    @Override
    public int addCategorizedClimbToStage(int stageId, Double location, CheckpointType type, Double averageGradient, Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
        int index = -1;
        int id = 0;

        // Incrementing through Race Objects
        for (Race selectRace : Races) {
            // Incrementing through Stage Objects
            for (int j = 0; j < selectRace.getStages().size(); j++) {
                Stage selectStage = selectRace.getStages().get(j);

                if (selectStage.getStageID() == stageId) {
                    id = selectStage.addCheckpoint(location, type, averageGradient, length);
                    break;
                }
            }
        }

        if (index == -1) {
            throw new IDNotRecognisedException("ID not recognized: " + stageId);
        }



        return id;
    }

    @Override
    public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
        int index = -1;
        int id = 0;

        // Incrementing through Race Objects
        for (Race selectRace : Races) {
            // Incrementing through Stage Objects
            for (int j = 0; j < selectRace.getStages().size(); j++) {
                Stage selectStage = selectRace.getStages().get(j);

                if (selectStage.getStageID() == stageId) {
                    id = selectStage.addCheckpoint(location, CheckpointType.SPRINT, (double) 0, (double) 0);
                    break;
                }
            }
        }

        if (index == -1) {
            throw new IDNotRecognisedException("ID not recognized: " + stageId);
        }



        return id;
    }

    @Override
    public void removeCheckpoint(int checkpointId) throws IDNotRecognisedException, InvalidStageStateException {
        int index = -1;

        // Incrementing through Race Objects
        for (Race selectRace : Races) {
            // Incrementing through Stage Objects
            for (Stage stage : selectRace.getStages()) {
                // Incrementing through checkpoints
                for(int i = 0; i < stage.getCheckpoints().size(); i++){
                    if(stage.getCheckpoints().get(i).getCheckID() == checkpointId){
                        index = i;
                        stage.getCheckpoints().remove(i);
                    }

                }

            }

        }

        if (index == -1) {
            throw new IDNotRecognisedException("ID not recognized: " + checkpointId);
        }
    }

    @Override
    public void concludeStagePreparation(int stageId) throws IDNotRecognisedException, InvalidStageStateException {

    }

    @Override
    public int[] getStageCheckpoints(int stageId) throws IDNotRecognisedException {
        int index = -1;
        ArrayList<Checkpoint> ChList = null;

        // Incrementing through Race Objects
        for (Race selectRace : Races) {
            // Incrementing through Stage Objects
            for (int j = 0; j < selectRace.getStages().size(); j++) {
                Stage selectStage = selectRace.getStages().get(j);

                if (selectStage.getStageID() == stageId) {
                    ChList = selectStage.getCheckpoints();
                    index = j;
                    break;
                }
            }
        }

        if (index == -1) {
            throw new IDNotRecognisedException("ID not recognized: " + stageId);
        }

        int n = ChList.size();
        int[] arrStCh = new int[n];

        //Collections.sort();

        for (int i=0; i<n-1;i++){
            arrStCh[i] = ChList.get(i).getCheckID();
        }

        // Rewrite to include stream, to order the elements.

        return arrStCh;
    }
    @Override
    public int createTeam(String name, String description) throws IllegalNameException, InvalidNameException {
        Team createdTeam = new Team();

        createdTeam.createTeam(name, description);

        Teams.add(createdTeam);

        int TeamID = createdTeam.generateID();
        createdTeam.setTeamId(TeamID);

        if (name.contains(" ")){
            throw new InvalidNameException();
        }

        if (ArrayCheck(name)){throw new IllegalNameException();}
        

        return TeamID;
    }

     public boolean ArrayCheck(String name) {
        boolean s = false;

        for (int i = 0; i < Teams.size()-1; i++){
            if (Teams.get(i).getName().equals(name)){
                s = true;
                break;
            }
        }

        return s;
    }

    @Override
    public void removeTeam(int teamId) throws IDNotRecognisedException {
        int index = -1;

        for (int i = 0; i < Teams.size(); i++) {
            Team team = Teams.get(i);
            if (team.getTeamId() == teamId) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new IDNotRecognisedException("ID not recognized: " + teamId);
        }

        // Remove the team at the found index
        Teams.remove(index);
    }

    private Team findTeamById(int teamId) {
        for (Team team : Teams) {
            if (team.getTeamId() == teamId) {
                return team;
            }
        }
        return null; // Team ID not found
    }

    @Override
    public int[] getTeams() {
        public int[] getTeams() {

        int[] IDs = new int[Teams.size()];

        for(int i = 0; i< Teams.size(); i++){
            IDs[i]=Teams.get(i).getTeamId();
        }
        return IDs;
    }
    }

    @Override
    public int[] getTeamRiders(int teamId) throws IDNotRecognisedException {
        Team team = findTeamById(teamId);

        if (team == null) {
            throw new IDNotRecognisedException("ID not recognized: " + teamId);
        }

        return team.getRiders();
    }

    private Team findTeamById(int teamId) {
        for (Team team : Teams) {
            if (team.getTeamId() == teamId) {
                return team;
            }
        }
        return null; // Team ID not found
    }

    @Override
    public int createRider(int teamID, String name, int yearOfBirth) throws IDNotRecognisedException, IllegalArgumentException {
        Riders createdRider = new Riders();
        int RiderID = createdRider.createRider(name, yearOfBirth);

        Riders.add(createdRider);

        Team team = findTeamById(teamID);

        assert team != null;
        team.addRider(RiderID);

        
        return RiderID;
    }

    @Override
    public void removeRider(int riderId) throws IDNotRecognisedException {
        int index = -1;

        for (int i = 0; i < Riders.size(); i++) {
            Riders rider = Riders.get(i);
            if (rider.getRiderId() == riderId) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new IDNotRecognisedException("ID not recognized: " + riderId);
        }

        // Remove the rider at the found index
        Riders.remove(index);
    }

     @Override
    public void registerRiderResultsInStage(int stageId, int riderId, LocalTime... checkpointTimes) throws IDNotRecognisedException, DuplicatedResultException, InvalidCheckpointTimesException, InvalidStageStateException {
        int index = -1;
        Riders rider = null;

        for (int i = 0; i < Riders.size(); i++) {
            rider = Riders.get(i);
            if (rider.getRiderId() == riderId) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new IDNotRecognisedException("ID not recognized: " + riderId);
        }

        int index2 = -1;
        int id = 0;

        // Incrementing through Race Objects
        for (Race selectRace : Races) {
            // Incrementing through Stage Objects
            for (int j = 0; j < selectRace.getStages().size(); j++) {
                Stage selectStage = selectRace.getStages().get(j);
                if (selectStage.getStageID() == stageId){
                    index2 = j;
                    break;
                }
            }
        }

        if (index2 == -1) {
            throw new IDNotRecognisedException("ID not recognized: " + stageId);
        }

        rider.addResult(stageId, checkpointTimes);
        selectStage.getLeaderBoard().add(riderId);
        selectStage.getRiderTimes().put(riderId, temp.plusMinutes(checkpointTimes[0].until(checkpointTimes[checkpointTimes.length-1], ChronoUnit.SECONDS)));


    }

    @Override
    public LocalTime[] getRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
        return new LocalTime[0];
    }

    @Override
    public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {
        Riders rider;
        LocalTime[] riderCheckpointTimes;
        LocalTime temp = LocalTime.MIN;
        boolean riderfound = false;
        boolean stagefound = false;
        LocalTime elpasedTime;
        Stage selectStage = null;
        int PositionOfRider = -1;


        for (int i = 0; i < Riders.size() - 1; i++) {
            if (riderId == Riders.get(i).getRiderId()) {   //find riderID
                riderfound = true;
                rider = Riders.get(i);
                break;
            }
        }

        //find the stage
        for (Stage stage : Stages) {
            if (stage.getStageID() == stageId) {
                selectStage = stage;
                break;
            }
        }

        // find position of rider in the stage

        for (int j = 0; j < selectStage.getLeaderBoard().size() - 1; j++) {
            if (selectStage.getLeaderBoard().get(j) == riderId) {
                PositionOfRider = j;
                break;
            }
        }

        //set real elapsed time
        elpasedTime = selectStage.getRiderTimes().get(riderId);

        //first adjusted time is the same as that riders real elapsed time
        selectStage.getAdjustedTimes().add(selectStage.getRiderTimes().get(selectStage.getLeaderBoard().get(1)));



        //populate adjusted time Arraylist in stage class with either adjusted time or real time
        // depending on the time difference between riders
        for (int pos = 1; pos < selectStage.getLeaderBoard().size() - 1; pos++) {
            if (selectStage.getRiderTimes().get(selectStage.getLeaderBoard().get(pos - 1)).until(selectStage.getRiderTimes().get(selectStage.getLeaderBoard().get(pos)), ChronoUnit.SECONDS) < 1) {
                selectStage.getAdjustedTimes().add(selectStage.getAdjustedTimes().get(pos - 1));
            } else {
                selectStage.getAdjustedTimes().add(selectStage.getRiderTimes().get(selectStage.getLeaderBoard().get(pos)));
            }
        }

        //return Adjusted time of the rider
        return selectStage.getAdjustedTimes().get(selectStage.getLeaderBoard().get(PositionOfRider));

    }

    @Override
    public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {

    }

    @Override
    public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException {
        return new int[0];
    }

    @Override
    public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId) throws IDNotRecognisedException {
        return new LocalTime[0];
    }

    @Override
    public int[] getRidersPointsInStage(int stageId) throws IDNotRecognisedException {
        return new int[0];
    }

    @Override
    public int[] getRidersMountainPointsInStage(int stageId) throws IDNotRecognisedException {
        return new int[0];
    }

    @Override
    public void eraseCyclingPortal() {

    }

    @Override
    public void saveCyclingPortal(String filename) throws IOException {

    }

    @Override
    public void loadCyclingPortal(String filename) throws IOException, ClassNotFoundException {

    }
}
