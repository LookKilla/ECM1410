package cycling;
import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;


public class CyclingPortalImpl implements CyclingPortal, Serializable {

    public ArrayList<Team> Teams;
    public ArrayList<Integer> RiderIDs; // del
    public ArrayList<Riders> Riders;
    public ArrayList<Race> Races;


    public CyclingPortalImpl(){


        Teams = new ArrayList<>();
        RiderIDs = new ArrayList<>();

        Riders = new ArrayList<>();

        Races = new ArrayList<>();

    }

    @Override
    public void removeRaceByName(String name) throws NameNotRecognisedException {
        int index = -1;
        boolean namefound = false;

        for (int i = 0; i < Races.size(); i++) {
            Race temp = Races.get(i);
            if (Objects.equals(temp.getName(), name)) {
                Races.remove(i);
                namefound = true;
                break;
            }
        }

        if (!namefound){
            throw new NameNotRecognisedException("Name not recognised");
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

        Race selectRace = null;
        boolean racefound = false;
        for (Race race: Races){ //find the race
            if(race.getRaceID() == raceId) {
                selectRace = race;
                racefound=true;
                break;
            }
        }

        if (!racefound){
            throw new IDNotRecognisedException("id not recognised");
        }


        //making the hashmap
        //0 points for everyone

        for(int i = 0; i<selectRace.getStages().getFirst().getLeaderBoard().size(); i++){

            selectRace.getAllPoints().put(selectRace.getStages().getFirst().getLeaderBoard().get(i), 0);
        }


        //make arraylis of total elapsed rider times --> every time is set to 0
        for(int i =0; i<selectRace.getStages().getFirst().getLeaderBoard().size(); i++){

            selectRace.getTotalTimes().put(selectRace.getStages().getFirst().getLeaderBoard().get(i),LocalTime.MIN);

        }


        //for every stage --> add points and times
        for(int i =0; i< selectRace.getStages().size(); i++) {
            ArrayList<Integer> Leaderboard = selectRace.getStages().get(i).getLeaderBoard();
            int[] StagePoints;

            StagePoints = getRidersPointsInStage(selectRace.getStages().get(i).getStageID());

            //adding points
            for (int index = 0; index < Leaderboard.size(); index++) {
                selectRace.getAllPoints().put(Leaderboard.get(index), selectRace.getAllPoints().get(Leaderboard.get(index)) + StagePoints[index]);

            }

            //gets the total time for every rider --> same order as Leaderboard


            for(int index =0; index< selectRace.getStages().size(); index++) {
                int riderid = selectRace.getStages().getFirst().getLeaderBoard().get(index);
                LocalTime riderTime = selectRace.getStages().getFirst().getRiderTimes().get(riderid);
                LocalTime temp = LocalTime.MIN;
                long timeBetween = temp.until(riderTime, ChronoUnit.SECONDS);

                selectRace.getTotalTimes().put(riderid, selectRace.getTotalTimes().get(selectRace.getStages().getFirst().getLeaderBoard().get(index)).plusSeconds(timeBetween));
            }

        }


        //Arraylist of total times

        ArrayList<LocalTime> Times = new ArrayList<>();
        ArrayList<Integer> sortedRiderIDs = new ArrayList<>();


        Times.addAll(selectRace.getTotalTimes().values());
        LocalTimeComparator comparator = new LocalTimeComparator();
        Times.sort(comparator);


        for(int i =0; i<Times.size();i++){
            for(Integer id : selectRace.getTotalTimes().keySet()){
                if(Times.get(i) == selectRace.getTotalTimes().get(id))
                    sortedRiderIDs.add(id);
            }
        }

        int[] sortedPoints = new int[sortedRiderIDs.size()];

        for(int i =0; i<sortedRiderIDs.size(); i++){
            sortedPoints[i] = selectRace.getAllPoints().get(sortedRiderIDs.get(i));
        }

        return sortedPoints;

    }


    public class LocalTimeComparator implements Comparator<LocalTime> {

        @Override
        public int compare(LocalTime time1, LocalTime time2) {
            return time1.compareTo(time2);
        }
    }


    @Override
    public int[] getRidersMountainPointsInRace(int raceId) throws IDNotRecognisedException {
        Race selectRace = null;
        HashMap<Integer, Integer> MountainPointsHash = new HashMap<>(); //rider IDs to points
        boolean racefound = false;

        for(Race race : Races){
            if(race.getRaceID() == raceId){
                selectRace = race;
                racefound = true;
            }
        }

        if(!racefound){
            throw new IDNotRecognisedException("race ID not found");
        }

        //initiating a Hashmap
        for(int i =0; i< selectRace.getStages().getFirst().getLeaderBoard().size(); i++){
            Stage stage = selectRace.getStages().getFirst();
            MountainPointsHash.put(stage.getLeaderBoard().get(i), 0);
        }


        //adding points to the hashmap
        for(Stage stage : selectRace.getStages()){
            if(stage.getType() == StageType.MEDIUM_MOUNTAIN){
                for(int index = 0; index < stage.getLeaderBoard().size(); index++){
                    MountainPointsHash.put(stage.getLeaderBoard().get(index), MountainPointsHash.get(stage.getLeaderBoard().get(index))+getRidersPointsInStage(stage.getStageID())[index]);
                }
            }
        }

        // making an array for the points
        int[] MountainPoints = new int[MountainPointsHash.values().size()];

        int i = 0;
        for(Integer point : MountainPointsHash.values()){

            MountainPoints[i] += point;
            i = i+1;
        }

        //sorting the array
        Arrays.sort(MountainPoints);


        return MountainPoints;
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

        if(RaceArrayCheck(name)){
            throw new IllegalNameException();
        }
        if (name.contains(" ") || name.length() > 32 || name.isEmpty()) {
            throw new InvalidNameException();
        }

        Race createdRace= new Race();
        int RaceId = createdRace.createRace(name, description);

        Races.add(createdRace);
        return RaceId;

    }

    public boolean RaceArrayCheck(String name) {
        boolean s = false;

        for (Race race : Races) {
            if (race.getName().equals(name)) {
                s = true;
                break;
            }
        }

        return s;
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
        boolean stageFound = false;

        // Incrementing through Race Objects
        for (Race selectRace : Races) {
            // Incrementing through Stage Objects
            for (int j = 0; j < selectRace.getStages().size(); j++) {
                Stage selectStage = selectRace.getStages().get(j);

                if (selectStage.getStageID() == stageId) {
                    selectRace.removeStage(stageId);
                    stageFound = true;
                    break;
                }
            }
        }

        if (!stageFound) {
            throw new IDNotRecognisedException("ID not recognized: " + stageId);
        }

    }

    @Override
    public int addCategorizedClimbToStage(int stageId, Double location, CheckpointType type, Double averageGradient, Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
        //int index = -1;
        boolean stageFound = false;
        int id = 0;

        // Incrementing through Race Objects
        for (Race selectRace : Races) {
            // Incrementing through Stage Objects
            for (int j = 0; j < selectRace.getStages().size(); j++) {
                Stage selectStage = selectRace.getStages().get(j);

                if (selectStage.getStageID() == stageId) {
                    id = selectStage.addCheckpoint(location, type, averageGradient, length);
                    stageFound = true;
                    break;
                }
            }
        }

        if (!stageFound) {
            throw new IDNotRecognisedException("ID not recognized: " + stageId);
        }



        return id;
    }

    @Override
    public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
        //int index = -1;
        boolean stagefound = false;
        int id = 0;

        // Incrementing through Race Objects
        for (Race selectRace : Races) {
            // Incrementing through Stage Objects
            for (int j = 0; j < selectRace.getStages().size(); j++) {
                Stage selectStage = selectRace.getStages().get(j);

                if (selectStage.getStageID() == stageId) {
                    id = selectStage.addCheckpoint(location, CheckpointType.SPRINT, (double) 0, (double) 0);
                    stagefound =true;
                    break;
                }
            }
        }

        if (!stagefound) {
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

        int[] arr = null;
        boolean found = false;
        Stage selectedStage = null;

        for(Race race : Races){
            for (Stage stage : race.getStages()){
                if (stage.getStageID() == stageId){
                    found = true;
                    selectedStage = stage;
                    break;
                }
            }
        }


        if (found){
            for (int j = 0; j < selectedStage.getCheckpoints().size(); j++){
                arr = new int[selectedStage.getCheckpoints().size()];
                arr[j] = selectedStage.getCheckpoints().get(j).getCheckID();
            }
            return arr;
        }

        else {
            throw new IDNotRecognisedException("ID not recognised: " + stageId);
        }
    }



    @Override
    public int createTeam(String name, String description) throws IllegalNameException, InvalidNameException {
        Team createdTeam = new Team();

        createdTeam.createTeam(name, description);

        Teams.add(createdTeam);

        int TeamID = createdTeam.generateID();
        createdTeam.setTeamId(TeamID);

        if (name.contains(" ") || name.length()>32 || name.isEmpty()){
            throw new InvalidNameException();
        }

        if (TeamArrayCheck(name)){throw new IllegalNameException();}


        return TeamID;
    }

    public boolean TeamArrayCheck(String name) {
        boolean s = false;

        for (int i = 0; i < Teams.size(); i++){
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

    @Override
    public int[] getTeams() {

        int[] IDs = new int[Teams.size()];

        for(int i = 0; i< Teams.size(); i++){
            IDs[i]=Teams.get(i).getTeamId();
        }
        return IDs;
    }

    @Override
    public int[] getTeamRiders(int teamId) throws IDNotRecognisedException {
        Team team = findTeamById(teamId);

        if (team == null) {
            throw new IDNotRecognisedException("ID not recognized: " + teamId);
        }

        return team.getRiders();
    }


    public Team findTeamById(int teamId) {
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
        Stage selectStage = null;
        int index = -1;
        boolean stagefound = false;
        Riders rider = null;
        LocalTime temp = LocalTime.MIN;


        for (Race race : Races){
            for (Stage stage : race.getStages()){
                if (stage.getStageID() == stageId){
                    selectStage = stage;
                    stagefound = true;
                    break;
                }
            }
            if(stagefound)
                break;
        }


        if (!stagefound) {
            throw new IDNotRecognisedException("ID not recognized: " + riderId);
        }




        selectStage.addResults(stageId, checkpointTimes);
        selectStage.getRiderTimes().put(riderId, temp.plusSeconds(checkpointTimes[0].until(checkpointTimes[checkpointTimes.length-1], ChronoUnit.SECONDS)));
        selectStage.getSortedRiderIDsByElapsedTime();
    }

    @Override
    public LocalTime[] getRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {

        Stage selectstage = null;
        int index = -1;
        Riders rider = null;
        boolean stagefound = false;

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

        for(Race race : Races){
            for(Stage stage : race.getStages()){
                if (stage.getStageID() == stageId){
                    selectstage = stage;
                    stagefound = true;
                    break;
                }
            }
            if (stagefound)
                break;
        }

        for (Results result: selectstage.getResults()){
            if(result.getRiderID() == riderId){
                return result.getCheckpointTimes();
         }
        }

        return null;

    }

    @Override
    public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {
        boolean riderfound = false;
        boolean stagefound = false;
        Stage selectStage = null;
        int PositionOfRider = -1;


        //find the stage
        for(Race race : Races){
            for(Stage stage : race.getStages()){
                if (stage.getStageID() == stageId){
                    stagefound = true;
                    selectStage = stage;
                    break;
                }
            }
            if (stagefound)
                break;
        }


        if(!stagefound){
            throw new IDNotRecognisedException("stage ID not found");
        }

        // find position of rider in the stage

        for (int j = 0; j < selectStage.getLeaderBoard().size(); j++) {
            if (selectStage.getLeaderBoard().get(j) == riderId) {
                riderfound = true;
                PositionOfRider = j;
                break;
            }
        }

        if (!riderfound){
            throw new IDNotRecognisedException("rider ID not found");
        }

        //set real elapsed time
        //elapsedTime = selectStage.getRiderTimes().get(riderId);

        //first adjusted time is the same as that riders real elapsed time
        selectStage.getAdjustedTimes().clear();
        selectStage.getAdjustedTimes().add(selectStage.getRiderTimes().get(selectStage.getLeaderBoard().get(1)));



        //populate adjusted time Arraylist in stage class with either adjusted time or real time
        // depending on the time difference between riders
        for (int pos = 1; pos < selectStage.getLeaderBoard().size(); pos++) {
            if (selectStage.getRiderTimes().get(selectStage.getLeaderBoard()
                    .get(pos - 1)).until(selectStage.getRiderTimes()
                    .get(selectStage.getLeaderBoard().get(pos)), ChronoUnit.SECONDS) < 1) {
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

        Stage selectStage = null;
        int index = -1;
        Riders rider = null;
        int index2 = 0;
        boolean stagefound = false;
        int position;

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



        for(Race race : Races){
            for(Stage stage : race.getStages()){
                if (stage.getStageID() == stageId) {
                    stagefound = true;
                    selectStage = stage;
                    break;
                }
            }
            if (stagefound)
                break;
        }

        for (Results result : selectStage.getResults()) {
            if (result.getRiderID() == riderId) {
                index2 = selectStage.getResults().indexOf(result);
                selectStage.removeresults(index2);
            }
        }

        if (stagefound){
            for (int i = 0; i< selectStage.getLeaderBoard().size(); i++) {
                if (selectStage.getLeaderBoard().get(i) == riderId){
                    selectStage.getLeaderBoard().remove(i);
                    position = i;
                    selectStage.getAdjustedTimes().remove(position);
                    selectStage.getRiderTimes().remove(riderId);
                    break;
                }
            }


        }


    }

    @Override
    public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException {

        int[] arrLeader;
        int index = -1;
        boolean stagefound = false;
        Stage selectstage = null;


        for(Race race : Races){
            for(Stage stage : race.getStages()){
                if(stage.getStageID() == stageId){
                    stagefound = true;
                    selectstage = stage;
                    break;
                }
            }
        }


        if (!stagefound)
            throw new IDNotRecognisedException("Id not recognised");


        if (stagefound){
            arrLeader = new int[selectstage.getLeaderBoard().size()];
            for(int i = 0; i<selectstage.getLeaderBoard().size(); i++){
                arrLeader[i] = selectstage.getLeaderBoard().get(i);
            }
            return arrLeader;
        }

        return null;
    }

    @Override
    public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId) throws IDNotRecognisedException {

        return new LocalTime[0];
    }

    @Override
    public int[] getRidersPointsInStage(int stageId) throws IDNotRecognisedException {

        Stage selectStage = null;
        boolean stagefund = false;
        int numberOfRiders;

        for (Race race : Races){
            for(Stage stage : race.getStages()){
                if (stage.getStageID() == stageId){
                    selectStage = stage;
                    stagefund = true;
                }
            }
        }

        if (!stagefund){
            throw new IDNotRecognisedException("ID not recognised");
        }

        numberOfRiders = selectStage.getLeaderBoard().size()-1;

        if(selectStage.getType() == StageType.MEDIUM_MOUNTAIN){
            return generatePointsMediumMountain(numberOfRiders);

        }
        else if (selectStage.getType() == StageType.FLAT) {

            return generatePointsFlat(numberOfRiders);

        }
        else if (selectStage.getType() ==StageType.TT) {
            return generatePointsTT(numberOfRiders);

        }
        else if (selectStage.getType() == StageType.HIGH_MOUNTAIN) {
            return generatePointsHighMountain(numberOfRiders);
        }


        return null;
    }

    public int[] generatePointsMediumMountain(int length){
        int[] Points = new int[length];
        for(int i =0; i< length; i++){
            Points[i] = 60/(i+2);
        }
        return Points;
    }

    public int[] generatePointsFlat(int length){
        int[] Points = new int[length];
        for(int i =0; i< length; i++){
            Points[i] = 100/(i+2);
        }
        return Points;
    }
    public int[] generatePointsHighMountain(int length){
        int[] Points = new int[length];
        for(int i =0; i< length; i++){
            Points[i] = 40/(i+2);
        }
        return Points;
    }

    public int[] generatePointsTT(int length){
        int[] Points = new int[length];
        for(int i =0; i< length; i++){
            Points[i] = 40/(i+2);
        }
        return Points;
    }


    @Override
    public int[] getRidersMountainPointsInStage(int stageId) throws IDNotRecognisedException {
        Stage selectstage = null;
        boolean stageFound = false;
        int[] MountainPointsInStage;

        for(Race race : Races){
            for (Stage stage : race.getStages()){
                if(stage.getStageID() == stageId){
                    selectstage = stage;
                    stageFound = true;
                    break;
                }
            }

            if(stageFound)
                break;
        }

        if (!stageFound)
            throw new IDNotRecognisedException("stage not found");


        MountainPointsInStage = getRidersPointsInStage(selectstage.getStageID());

        return MountainPointsInStage;
    }

    @Override
    public void eraseCyclingPortal() {

        this.RiderIDs.clear();
        this.Races.clear();
        this.Teams.clear();
        this.Riders.clear();

    }

    @Override
    public void saveCyclingPortal(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            // Write the number of ArrayLists to the file
            oos.writeInt(5); // Assuming there are 5 ArrayLists

            // Write each ArrayList to the file
            oos.writeObject(Teams);
            oos.writeObject(Races);
            oos.writeObject(raceIDs);
            oos.writeObject(riderIDs);
            oos.writeObject(Riders);

            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving data to file: " + e.getMessage());
        }
    }

    @Override
    public void loadCyclingPortal(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            // Read the number of ArrayLists from the file
            int numberOfLists = ois.readInt();

            // Read each ArrayList from the file
            for (int i = 0; i < numberOfLists; i++) {
                Object obj = ois.readObject();
                if (obj instanceof ArrayList) {
                    switch (i) {
                        case 0:
                            Teams = (ArrayList<Team>) obj;
                            break;
                        case 1:
                            Races = (ArrayList<Race>) obj;
                            break;
                        case 2:
                            raceIDs = (ArrayList<Integer>) obj;
                            break;
                        case 3:
                            riderIDs = (ArrayList<Integer>) obj;
                            break;
                        case 4:
                            Riders = (ArrayList<Riders>) obj;
                            break;
                        // Add more cases if needed for additional ArrayLists
                    }
                }
            }

            System.out.println("Data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Error loading data from file: " + e.getMessage());
        }
    }
}
