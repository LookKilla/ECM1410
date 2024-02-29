package cycling;

import java.util.Arrays;

public class Team {

    private Team[] Teams;

    private static int lastUsedTeamID; // Variable to keep track of the last used team ID
    
    private String Name;
    private String Description;

    private int TeamId;
    private static int[] RiderIDs;


    public static int[] getRiders(){
        return RiderIDs;
    }

    public int getTeamId(){
        return TeamId;
    }

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
