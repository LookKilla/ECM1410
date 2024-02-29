package cycling;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CyclingPortalImpl implements CyclingPortal {


    //make getter and setters

    // public int[] teamIDs;
    public ArrayList<Team> Teams;
    public int lastUsedTeamID;
    
    public int NumberOfRiders;
    public int[] RiderIDs;
    public Riders.Rider[] Riders;

    public CyclingPortalImpl(){
        TeamIDs = new int[100];
        RiderIDs = new int[100];
        Riders = new Riders.Rider[100];

        Teams = new Team[1];
        lastUsedTeamID = 1;

        NumberOfRiders = RiderIDs.length;
    }

    @Override
    public void removeRaceByName(String name) throws NameNotRecognisedException {

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
        return new int[0];
    }

    @Override
    public int createRace(String name, String description) throws IllegalNameException, InvalidNameException {
        return 0;
    }

    @Override
    public String viewRaceDetails(int raceId) throws IDNotRecognisedException {
        return null;
    }

    @Override
    public void removeRaceById(int raceId) throws IDNotRecognisedException {

    }

    @Override
    public int getNumberOfStages(int raceId) throws IDNotRecognisedException {
        return 0;
    }

    @Override
    public int addStageToRace(int raceId, String stageName, String description, double length, LocalDateTime startTime, StageType type) throws IDNotRecognisedException, IllegalNameException, InvalidNameException, InvalidLengthException {
        return 0;
    }

    @Override
    public int[] getRaceStages(int raceId) throws IDNotRecognisedException {
        return new int[0];
    }

    @Override
    public double getStageLength(int stageId) throws IDNotRecognisedException {
        return 0;
    }

    @Override
    public void removeStageById(int stageId) throws IDNotRecognisedException {

    }

    @Override
    public int addCategorizedClimbToStage(int stageId, Double location, CheckpointType type, Double averageGradient, Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
        return 0;
    }

    @Override
    public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
        return 0;
    }

    @Override
    public void removeCheckpoint(int checkpointId) throws IDNotRecognisedException, InvalidStageStateException {

    }

    @Override
    public void concludeStagePreparation(int stageId) throws IDNotRecognisedException, InvalidStageStateException {

    }

    @Override
    public int[] getStageCheckpoints(int stageId) throws IDNotRecognisedException {
        return new int[0];
    }

    @Override
    public int createTeam(String name, String description) throws IllegalNameException, InvalidNameException {
        Team createdTeam = new Team();
        int TeamID = createdTeam.createTeam(name, description);

        Teams = Arrays.copyOf(Teams, Teams.length + 1);
        Teams[Teams.length - 1] = createdTeam;
        return TeamID;
    }

    @Override
    public void removeTeam(int teamId) throws IDNotRecognisedException {

        // Unfinished
        
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

    @Override
    public int[] getTeams() {
        return teamIDs;
    }

    @Override
    public int[] getTeamRiders(int teamId) throws IDNotRecognisedException {
        Team team = findTeamById(teamId);

        if (team == null) {
            throw new IDNotRecognisedException("ID not recognized: " + teamId);
        }

        return Team.getRiders();
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
        return 0;
    }

    @Override
    public void removeRider(int riderId) throws IDNotRecognisedException {

    }

    @Override
    public void registerRiderResultsInStage(int stageId, int riderId, LocalTime... checkpointTimes) throws IDNotRecognisedException, DuplicatedResultException, InvalidCheckpointTimesException, InvalidStageStateException {

    }

    @Override
    public LocalTime[] getRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
        return new LocalTime[0];
    }

    @Override
    public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {
        return null;
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
