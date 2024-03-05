package cycling;

import java.util.Arrays;

public class Team {

    private Team[] Teams;

    private static int lastUsedTeamID; // Variable to keep track of the last used team ID
    
    private String Name;
    private String Description;

    private int TeamId;
    private ArrayList<Integer> RiderIDs;



    //getters
    public String getName(){return Name;}
    public String getDescription(){return Description;}
    public int getTeamId(){return TeamId;}
    public ArrayList<Integer> getRiderIDs(){return RiderIDs;}

    //setters
    public void setName(String Name){this.Name = Name;}
    public void setDescription(String Description){this.Description = Description;}
    public void setTeamId(int TeamId){this.TeamId = TeamId;}
    public void addRider(int riderID){this.RiderIDs.add(riderID);}

    
    public Team(){

        CyclingPortalImpl Portal = new CyclingPortalImpl();
        lastUsedTeamID = Portal.lastUsedTeamID;

    }

    public int createTeam(String name, String description) throws IllegalNameException, InvalidNameException {
        int teamID = generateTeamID();

        RiderIDs = new int[20];
        this.Name = name;
        this.Description = description;
        this.TeamId = teamID;

        ++lastUsedTeamID;
        return teamID;
    }

    public int generateID(){

        int id = this.Name.hashCode();
        if (id < 0){
            id = id * -1;
        }

        return id;
    }

    private int getLastUsedTeamID() {
        // Find the maximum value in the teamIDs array
        // Note: This assumes that the teamIDs array is not sparse (i.e., no gaps in the IDs)
        int maxID = Arrays.stream(teamIDs).max().orElse(0);

        // Update the lastUsedTeamID
        lastUsedTeamID = maxID;

        return lastUsedTeamID;
    }

    @Override
    public String toString() {
        return "Team "+Name+": "+ Description+ ": "+TeamId;
    }


}
