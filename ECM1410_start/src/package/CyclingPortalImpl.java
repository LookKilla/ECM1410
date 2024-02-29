package cycling;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
public class CyclingPortalImpl implements CyclingPortal {


    //make getter and setters

    public int[] teamIDs;
    public Team[] Teams;
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
        rTeam createdTeam = new Team();
        int TeamID = createdTeam.createTeam(name, description);

        Teams = Arrays.copyOf(Teams, Teams.length + 1);
        Teams[Teams.length - 1] = createdTeam;
        return TeamID;
    }

    @Override
    public void removeTeam(int teamId) throws IDNotRecognisedException {

        // Unfinished
        
        if (!containsTeam(teamId)) {
            throw new IDNotRecognisedException("ID not recognized: " + teamId);
        }

        // Find the index of the team ID in the array
        int index = indexOfTeam(teamId);

        // Remove the team ID from the teamIDs array
        int[] newTeamIDs = new int[teamIDs.length - 1];
        System.arraycopy(teamIDs, 0, newTeamIDs, 0, index);
        System.arraycopy(teamIDs, index + 1, newTeamIDs, index, teamIDs.length - index - 1);
        teamIDs = newTeamIDs;

        // Remove the corresponding Team from the teams array
        Team[] newTeams = new Team[Teams.length - 1];
        System.arraycopy(Teams, 0, newTeams, 0, index);
        System.arraycopy(Teams, index + 1, newTeams, index, Teams.length - index - 1);
        Teams = newTeams;
    }

    private boolean containsTeam(int teamId) {
        for (int id : teamIDs) {
            if (id == teamId) {
                return true;
            }
        }
        return false;
    }

    private int indexOfTeam(int teamId) {
        for (int i = 0; i < teamIDs.length; i++) {
            if (teamIDs[i] == teamId) {
                return i;
            }
        }
        return -1; // Team ID not found
    }

    @Override
    public int[] getTeams() {
        return new int[0];
    }

    @Override
    public int[] getTeamRiders(int teamId) throws IDNotRecognisedException {
        return new int[0];
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
