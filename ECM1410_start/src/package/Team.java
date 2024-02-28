package cycling;

import java.util.Arrays;

public class NewTeam {

    private Team[] Teams;
    private int[] teamIDs; // Array to store team IDs
    private static int lastUsedTeamID; // Variable to keep track of the last used team ID
    
    private String Name;
    private String Description;

    private int TeamId;
    private int[] RiderIDs;



    public NewTeam(){

        CyclingPortalImpl Portal = new CyclingPortalImpl();
        teamIDs = Portal.TeamIDs;
        lastUsedTeamID = Portal.lastUsedTeamID;

    }

    public int createTeam(String name, String description) throws IllegalNameException, InvalidNameException {
        int[] RiderIDs = new int[20];
        this.RiderIDs = RiderIDs;

        this.Name =name;
        this.Description = description;

        this.TeamId = generateTeamID(); // make generateID
        lastUsedTeamID++;
        return 0;
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
