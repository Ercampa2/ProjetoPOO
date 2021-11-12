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
import utility.Hash;

public class App{
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        App app = new App();
        app.run();
    }

    private void run(){
        setup();
        System.out.println();
        System.out.println("##=====#===============#====");
        System.out.println("||     |               |    ");
        System.out.println("||     |               |    ");
        System.out.println("||     |               |    ");
        System.out.println("||  <=-#-=-=-=-=-=-=-=-#-=> ");
        System.out.println("||   |    Blog-do-Enzo   |  ");
        System.out.println("||   |     Bem-Vindo     |  ");
        System.out.println("||  <=-=-=-=-=-=-=-=-=-=-=> ");
        System.out.println("||                          ");
        System.out.println("||                          ");
        System.out.println("||                          ");
        System.out.println("||                          ");
        
        System.out.println();

        loop:while (true) {
            System.out.println("1-Cadastrar");
            System.out.println("2-Entrar com Login e senha");
            System.out.println("3-Entrar como Convidado");
            System.out.println("4-Derrubar Servidor");

            String menuOpt = in.nextLine(); 
            System.out.println();

            switch (menuOpt) {
                case "1":
                    signUp();
                    break;
                case "2":
                    // SignIn();
                    break;
                case "3":
                    // SignGuest();
                    break;
                case "4":
                    break loop;
                default:
                System.out.println("Opção Inválida!");
                    break;
            }
        }
        endProgram();
    }

    //Cadastro
    public void signUp(){
        String separator = "BDE@BV";

        System.out.println("Nome:");
        String nameUp = in.nextLine(); 
        System.out.println();
        System.out.println("E-mail:");
        String emailUp = in.nextLine(); 
        System.out.println();
        System.out.println("Senha:");
        String passUp = in.nextLine(); 
        System.out.println();

        passUp = Hash.getSecurePassword(passUp);

        String userData = Users.getUserID() + separator + nameUp +separator + emailUp + separator + passUp;

        userData = Hash.getSecurePassword(userData);

        addUser(userData);
    }

    public void endProgram(){
        in.close();
    }

    public void addUser(String data){
        addToFIle(data+'\n', "data/users.txt");
        Users.addOneUserId();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data/stuff.txt"), "UTF-8"));
            String inputBuffer = "";
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                if(lineNumber == 1){
                    inputBuffer += Integer.parseInt(line) + 1;
                }else{
                    inputBuffer += line;
                }
                inputBuffer += '\n';
                lineNumber++;
            }
            br.close();
            Writer bw = new BufferedWriter(new FileWriter("data/stuff.txt"));			
			bw.write(inputBuffer);
			bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

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
}
