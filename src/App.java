import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import users.Users;
import screens.*;

public class App{
    private static Scanner in = new Scanner(System.in);
    private Users user = new Users();
    private Screens initial = new InitialScreen();
    public static void main(String[] args) throws Exception {
        App app = new App();
        app.run();
    }

    private void run(){
        setup();
        loop:while(true){
            int id = initial.showScreen();
            user.setUserId(id);
            if(user.getUserId() >= 0){
                Screens dash = new Dashboard(user);
                dash.showScreen();
                user.setUserId(-2);
            }else if(user.getUserId() == -1){
                Screens dash = new DashboardGuest();
                dash.showScreen();
                user.setUserId(-2);
            }else{
                break loop;
            }
        }
        endProgram();
    }

    //Configs de encerramento
    public void endProgram(){
        in.close();
    }
  
    //Setup inicial
    public void setup(){
        try{

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data/stuff.txt"), "UTF-8"));
            String line;
            int lineNumber = 0;
            
            while ((line = br.readLine()) != null) {
                if(lineNumber == 1){
                    //
                   Users.setnumUsers(Integer.parseInt(line));
                }
            lineNumber++;
            }
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
