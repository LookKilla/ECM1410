package cycling;


import javax.sound.sampled.Port;
import java.io.IOException;
import java.sql.Array;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class Main{
    public static void main(String[] args) {

        int[] arry = {56,4,78,2,3,4};

        Arrays.sort(arry);
        System.out.println(Arrays.toString(arry));
        MiniCyclingPortal portal1 = new CyclingPortalImpl();


        LocalTime[] arr = new LocalTime[2];
        LocalTime s;
        LocalTime d;



        s = LocalTime.now();

        d = LocalTime.MIN;
        arr[0]= s;
        arr[1] = d;



        System.out.println(arr[arr.length-1]);

        CyclingPortalImpl Portal = new CyclingPortalImpl();

        try {
            Portal.createTeam("ireland", "best");
            Portal.createTeam("New_Zeland", "worst");
            Portal.createTeam("Hungary", "middle");


        }
        catch (IllegalNameException e){
            System.out.println("name already taken");
        }
        catch (InvalidNameException e){
            System.out.println("Invalid name: it contains a whitespace");
        }


        try{
            Portal.saveCyclingPortal("data.dat");
        }
        catch (IOException e){
            System.out.println("not saved");
        }


//        try{
//            Portal.loadCyclingPortal("data.ser");
//        }
//        catch (IOException e ){
//
//            System.out.println("daata not loaded");
//
//        }
//        catch (ClassNotFoundException e){
//
//            System.out.println("data not loaded");
//        }
//        System.out.println(Portal.Teams);


//        System.out.println(Portal.Teams.get(0).getTeamId());
//        System.out.println(Portal.Teams.get(1).getTeamId());
//        System.out.println(Portal.Teams.get(2).getTeamId());

//        Team a = new Team();
//        int Id = a.createTeam("team ireland", "best");
//
//        Team b = new Team();
//        int Id2 = b.createTeam("Ukrain", "worst");
//
//        System.out.println(b.toString()+ Id2);
//
//        System.out.println(a.toString()+Id);
    }



}
