package cycling;

import java.util.Arrays;

public class Team {

    private Team[] Teams;
    private int[] teamIDs; // Array to store team IDs
    private static int lastUsedTeamID; // Variable to keep track of the last used team ID
    
    private String Name;
    private String Description;

    private int TeamId;
    private int[] RiderIDs;



    public Team(){

        CyclingPortalImpl Portal = new CyclingPortalImpl();
        teamIDs = Portal.teamIDs;
        lastUsedTeamID = Portal.lastUsedTeamID;

    }

    public int[] getRiders(){
        return this.RiderIDs;
    }

    public int createTeam(String name, String description) throws IllegalNameException, InvalidNameException {
        int teamID = generateTeamID();

        this.RiderIDs = new int[20];
        this.Name = name;
        this.Description = description;
        this.TeamId = teamID;

        lastUsedTeamID++;
        return teamID;
    }

    private static int generateTeamID() {
        // Increment the last used team ID to generate a new unique ID
        return lastUsedTeamID + 1;
    }

    private int getLastUsedTeamID() {
        // Find the maximum value in the teamIDs array
        // Note: This assumes that the teamIDs array is not sparse (i.e., no gaps in the IDs)
        int maxID = Arrays.stream(teamIDs).max().orElse(0);

        // Update the lastUsedTeamID
        lastUsedTeamID = maxID;

        return lastUsedTeamID;
    }
    
    
    
}
