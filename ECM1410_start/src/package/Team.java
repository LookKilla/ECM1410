package cycling;

public class Team {

    private Team[] Teams;
    private int[] teamIDs; // Array to store team IDs

    private int lastUsedTeamID = 0; // Variable to keep track of the last used team ID

    private String Name;
    private String Description;

    private int TeamId;
    private int[] RiderIDs;



    public Team(){

        CyclingPortalImpl Portal = new CyclingPortalImpl();
        teamIDs = Portal.TeamIDs;

    }

    public int createTeam(String name, String description) throws IllegalNameException, InvalidNameException {
        int[] RiderIDs = new int[20];
        this.RiderIDs = RiderIDs;

        this.Name =name;
        this.Description = description;

        this.TeamId = generateId(); // make generateID
        return 0;
    }




}

