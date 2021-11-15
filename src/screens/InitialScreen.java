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
import exceptions.*;

public class InitialScreen extends Screens {
    private static Scanner in = new Scanner(System.in);
    private String separator = "BDE@BV";

    public int showScreen() throws ExceptionInvalid{
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
                try {
                    signUp();
                } catch (ExceptionSignUp e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "2":
                try {
                    int id = SignIn();
                    if (id == -2) {
                        throw new ExceptionAuthentication("Login ou senha incoretos!");
                    } else {
                        return id;
                    }
                } catch (ExceptionSignIn e) {
                    System.out.println("Erro de Login: " + e.getMessage());
                } catch (ExceptionAuthentication e) {
                    System.out.println("Erro de autenticação: " + e.getMessage());
                }
                break;
            case "3":
                return -1;
            case "4":
                return -2;
            default:
                throw new ExceptionInvalid("Opção Inválida!");
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
            System.out.println("Erro interno");
            e.printStackTrace();
        }

    }

    // Cadastro
    public void signUp() throws ExceptionSignUp{
        String nameUp, emailUp, passUp;
        while (true) {
            System.out.println("Nome:");
            nameUp = in.nextLine();
            System.out.println();
            if (nameUp.length() != 0) {
                break;
            }
            throw new ExceptionSignUp("Insira um nome válido!");
        }
        while (true) {
            System.out.println("E-mail:");
            emailUp = in.nextLine();
            System.out.println();
            if (emailUp.length() != 0) {
                break;
            }
            throw new ExceptionSignUp("Insira um email válido!");
        }
        while (true) {
            System.out.println("Senha:");
            passUp = in.nextLine();
            System.out.println();
            if (passUp.length() != 0) {
                break;
            }
            throw new ExceptionSignUp("Insira uma senha válida!");
        }
        passUp = Hash.getSecurePassword(passUp);
        String userData = Users.getnumUsers() + separator + nameUp + separator + emailUp + separator + passUp;

        addUser(userData);

        System.out.println("Cadastro Realizado com sucesso");
        System.out.println();
    }

    // Login
    public int SignIn()  throws ExceptionSignIn{
        String nameIn, passIn;
        while (true) {
            System.out.println("Nome:");
            nameIn = in.nextLine();
            System.out.println();
            if (nameIn.length() != 0) {
                break;
            }
            throw new ExceptionSignIn("Insira um nome válido!");
        }
        while (true) {
            System.out.println("Senha:");
            passIn = in.nextLine();
            System.out.println();
            if (passIn.length() != 0) {
                break;
            }
            throw new ExceptionSignIn("Insira uma senha válido");
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
            System.out.println("Erro interno");
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
            System.out.println("Erro interno");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erro interno");
            e.printStackTrace();
        }
    }

}
