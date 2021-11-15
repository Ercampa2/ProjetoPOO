import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Scanner;

import users.Users;
import screens.*;

public class App{
    private static Scanner in = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        App app = new App();
        app.run();
    }

    private void run(){
        setup();
        Screens initial = new InitialScreen();
        initial.showScreen();
        endProgram();
    }

    //Configs de encerramento
    public void endProgram(){
        in.close();
    }

    //Adicioanr ao fim de um arquivo
    public static void addToFIle(String content, String path) {
		try {
			Writer bw = new BufferedWriter(new FileWriter(path, true));			
			bw.write(content);
			bw.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
   
    //Setup inicial
    public void setup(){
        try{

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data/stuff.txt"), "UTF-8"));
            String line;
            int lineNumber = 0;
            
            while ((line = br.readLine()) != null) {
                if(lineNumber == 1){
                   Users.setUserID(Integer.parseInt(line));
                }
            lineNumber++;
            }
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    //Tela dashboard
}
