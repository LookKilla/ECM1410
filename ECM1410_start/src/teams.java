public class teams{
  private int teamID;
  private String name;
  private String description;
  private int[] riders;

  public int createTeam(String name, String description){
    this.name = name;
    this.description = description;

    return teamID;
  }

  public int[] getTeamRiders(int teamID){
    return riders;
  }

  
  
}
