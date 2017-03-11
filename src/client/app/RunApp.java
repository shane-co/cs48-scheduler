package client.app;
import client.view.UserInterface;
import client.view.ScheduleDisplay;
import client.commander.BGCommander;
import client.app.obj.*;
import client.app.exceptions.*;
import java.util.ArrayList;

public class RunApp{
    private static boolean end;
    public static void main(String[] args){
        if(args.length == 0){
            UserInterface main = UserInterface.getUserInterface();
            main.launch();
        }
        else if(args[0].equals("--no-ui")){
            BGCommander command = BGCommander.getBGCommander();
            System.out.println("New BGCommander loaded. No User signed in.");
            while(!end){
                System.out.println("Enter command: (\"help\" to display all commands)");
                String input = System.console().readLine();
                switch(input){
                    case "help": System.out.println("addUser, login, showMyEvents, exit");
                        break;
                    case "addUser": System.out.println("Enter Username:"); String uname=System.console().readLine();
                        System.out.println("Enter password:"); String pword=System.console().readLine();
                            command.addUser(uname, pword);
                        break;
                    case "login": System.out.println("Enter Username:"); uname=System.console().readLine();
                        System.out.println("Enter password:"); pword=System.console().readLine();
                        try{
                            command.login(uname,pword);
                        }catch(ElementNotFoundException ex){System.out.println(ex.getMsg());}
                        catch(UserLoggedInException uex){System.out.println(uex.getMsg());}
                        break;
                    case "showMyEvents":
                        break;
                    /*case "subscribe":
                        System.out.println("Enter name of ScheduleEvent:"); String id=System.console().readLine();
                        System.out.println("Enter day of the week (1-7):"); String day=System.console().readLine();
                        System.out.println("Enter start hour (0-23):"); String start=System.console().readLine();
                        System.out.println("Enter end hour (0-23):"); String end=System.console().readLine();
                        System.out.println("Enter description:"); String desc = System.console().readLine();
                        try{
                            command.subscribeEvent(id,day,start,end,desc);
                        }catch(FormatException f){System.out.println(f.getMsg());}
                        catch(ElementNotFoundException e){System.out.println(e.getMsg());}
                        break;*/
                    case "unsubscribe":
                        System.out.println("Enter Schedule identifier:"); String id=System.console().readLine();
                        command.unsubscribe(id);
                    case "createEvent":
                        System.out.println("Enter Event Name:"); id=System.console().readLine();
                        System.out.println("Enter Event Description:"); String desc=System.console().readLine();
                        ArrayList<ArrayList<Integer>> dur = new ArrayList<ArrayList<Integer>>();
                        ArrayList<Integer> d;
                        for(int i=0; i<7; i++){
                            d = new ArrayList<Integer>(0);
                            int hr = 0;
                            while(hr>=0){
                                System.out.println("Enter hrs for day "+i+ ". -1 to go to next day"); hr=Integer.parseInt(System.console().readLine());
                                d.add(hr);
                            }
                            dur.add(i,d);
                        }
                        command.createHostedEvent(dur,id,desc);
                        break;
                    case "exit":
                        command.exitApp();
                        System.exit(0);
                        break;

                }
            }
        }
        else{
            System.out.println("Usage: java runapp [--no-ui]");
        }
    }
}
