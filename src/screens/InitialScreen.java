package screens;

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

public class InitialScreen extends Screens {
    private static Scanner in = new Scanner(System.in);
    private String separator = "BDE@BV";

    public int showScreen() {
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

        while (true) {
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
                int id = SignIn();
                if (id == -2) {
                    System.out.println("Login ou senha incoretos!");
                } else {
                    return id;
                }
                break;
            case "3":
                return -1;
            case "4":
            return -2;
            default:
                System.out.println("Opção Inválida!");
                break;
            }
        }
    }

    // Adicao de usuário ao Stuff
    public void addUser(String data) {
        addToFIle(data + '\n', "data/users.txt");
        Users.addOneUserId();

        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream("data/stuff.txt"), "UTF-8"));
            String inputBuffer = "";
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                if (lineNumber == 1) {
                    inputBuffer += Integer.parseInt(line) + 1;
                } else {
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

    // Cadastro
    public void signUp() {
        String nameUp, emailUp, passUp;
        while (true) {
            System.out.println("Nome:");
            nameUp = in.nextLine();
            System.out.println();
            if (nameUp.length() != 0) {
                break;
            }
        }
        while (true) {
            System.out.println("E-mail:");
            emailUp = in.nextLine();
            System.out.println();
            if (emailUp.length() != 0) {
                break;
            }
        }
        while (true) {
            System.out.println("Senha:");
            passUp = in.nextLine();
            System.out.println();
            if (passUp.length() != 0) {
                break;
            }
        }
        passUp = Hash.getSecurePassword(passUp);
        String userData = Users.getnumUsers() + separator + nameUp + separator + emailUp + separator + passUp;

        addUser(userData);

        System.out.println("Cadastro Realizado com sucesso");
        System.out.println();
    }

    // Login
    public int SignIn() {
        String nameIn, passIn;
        while (true) {
            System.out.println("Nome:");
            nameIn = in.nextLine();
            System.out.println();
            if (nameIn.length() != 0) {
                break;
            }
        }
        while (true) {
            System.out.println("Senha:");
            passIn = in.nextLine();
            System.out.println();
            if (passIn.length() != 0) {
                break;
            }
        }

        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream("data/users.txt"), "UTF-8"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(separator);
                if (data[1].equals(nameIn)) {
                    String passTest = Hash.getSecurePassword(passIn);
                    if (data[3].equals(passTest)) {
                        br.close();
                        return Integer.parseInt(data[0]);
                    }
                }
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return -2;

    };

    // Adicioanr ao fim de um arquivo
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
