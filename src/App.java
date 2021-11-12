import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Scanner;
import users.*;
import utility.*;

public class App{
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        App app = new App();
        app.run();
    }

    private void run(){
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
            printMenu();
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

    //Print do menu Inicial
    public void printMenu(){
        System.out.println("1-Cadastrar");
        System.out.println("2-Entrar com Login e senha");
        System.out.println("3-Entrar como Convidado");
        System.out.println("4-Derrubar Servidor");
    }

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
        addToFIle(userData+'\n', "data/users.txt");
        Users.addOneUserId();
    }

    public void endProgram(){
        in.close();
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
    
}
