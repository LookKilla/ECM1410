package (Enter Package Name);

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class Team {
    private Map<String, String> teams; // Map to store team names and descriptions
    private int[] teamIDs; // Array to store team IDs
    private int lastUsedTeamID; // Variable to keep track of the last used team ID

    public Team() {
        teamIDs = new int[100];
        teams = new HashMap<>();
        lastUsedTeamID = getLastUsedTeamID(); // Initialize lastUsedTeamID from your data source
    }

    public int createTeam(String name, String description) throws IllegalNameException, InvalidNameException {
        // Validate input parameters
        validateTeamName(name);
        validateTeamDescription(description);

        // Check if the team name already exists
        if (teams.containsKey(name)) {
            throw new IllegalNameException("Team name already exists in the platform");
        }


        // Generate a unique team ID
        int newTeamID = generateTeamID();

        // Add the team to the map
        teams.put(name, description);

        // Increment the last used team ID
        lastUsedTeamID++;

        return newTeamID;
    }

    private int generateTeamID() {
        // Increment the last used team ID to generate a new unique ID
        return lastUsedTeamID + 1;
    }

    private void validateTeamName(String name) throws InvalidNameException {
        // Validate team name
        if (name == null || name.isEmpty() || name.length() > 30 || name.contains(" ")) {
            throw new InvalidNameException("Invalid team name");
        }
    }


    private void validateTeamDescription(String description) throws InvalidNameException {
        // Validate team description (you can add additional validation if needed)
        if (description == null) {
            throw new InvalidNameException("Invalid team description");
        }
    }

    private int getLastUsedTeamID() {
        // Find the maximum value in the teamIDs array
        // Note: This assumes that the teamIDs array is not sparse (i.e., no gaps in the IDs)
        int maxID = Arrays.stream(teamIDs).max().orElse(0);

        // Update the lastUsedTeamID
        lastUsedTeamID = maxID;

        return lastUsedTeamID;
    }

    private void updateLastUsedTeamID(int newTeamID) {
        // Check if the array needs to be resized
        if (lastUsedTeamID >= teamIDs.length) {
            // Resize the array (you can use a more sophisticated resizing strategy)
            teamIDs = Arrays.copyOf(teamIDs, teamIDs.length * 2);
        }

        // Add the new team ID to the array
        teamIDs[lastUsedTeamID] = newTeamID;

        // Increment the last used team ID
        lastUsedTeamID++;
    }
}

// I've tested it and it 'seems' to be working.
