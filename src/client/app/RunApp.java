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
            command.setNetworkDiscoverable();
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
                        catch(LoginFailedException ex){System.out.println(ex.getMsg());}
                        break;
                    case "addOrg":System.out.println("Enter Organization name:"); String name=System.console().readLine();
                        System.out.println("Enter IP Address:"); String ip=System.console().readLine();
                        command.addOrganization(name,ip);
                        break;
                    case "retrieve":System.out.println("Enter Organization name:"); String n=System.console().readLine();
                        command.search(n);
                        break;
                    case "createEvent":
                        System.out.println("Enter Event Name:"); String id=System.console().readLine();
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
                        try{
                            command.createHostedEvent(dur,id,desc);
                        }catch(ElementNotFoundException e){}
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
