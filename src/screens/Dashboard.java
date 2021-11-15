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

import exceptions.ExceptionInvalid;
import users.Users;

public class Dashboard extends Screens {
    private Users user;
    private static Scanner in = new Scanner(System.in);
    private String separator = "BDE@BV";

    public Dashboard(Users user) {
        this.user = user;
    }

    public int showScreen() {
        while (true) {
            System.out.println("1-Criar novo texto");
            System.out.println("2-Editar meu(s) texto(s)");
            System.out.println("3-Apagar texto");
            System.out.println("4-Ver todos os textos");
            System.out.println("5-Desconectar");

            String menuOpt = in.nextLine();
            System.out.println();

            switch (menuOpt) {
            case "1":
                createPost();
                break;
            case "2":
                editPost();
                break;

            case "3":
                deletePost();
                break;
            case "4":
                showPosts();
                break;
            case "5":
                return 1;

            default:
                throw new ExceptionInvalid("Opção Inválida!");
            }
        }
    }

    private void createPost() {
        System.out.println("Qual o título do post:");
        String title = in.nextLine();
        System.out.println();

        System.out.println("Digite o conteúdo:");
        String content = in.nextLine();
        System.out.println();

        String authorName = "";
        try {

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream("data/users.txt"), "UTF-8"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(separator);
                if (user.getUserId() == Integer.parseInt(data[0])) {
                    authorName = data[1];
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Erro de leitura de dados");
            e.printStackTrace();
        }

        addToFIle(user.getUserId() + separator + authorName + separator + title + separator + content + "\n",
                "data/texts.txt");
        System.out.println("Post adicionado!");
        System.out.println();
    }

    private void showPosts() {
        try {

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream("data/texts.txt"), "UTF-8"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(separator);
                System.out.println(data[1] + " - " + data[2]);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Erro de leitura de dados");
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("Deseja ler qual texto");
        String text = in.nextLine();
        System.out.println();
        boolean textFound = false;

        try {

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream("data/texts.txt"), "UTF-8"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(separator);
                if (data[2].equals(text)) {
                    System.out.println(data[2] + "\n" + data[1] + "\n" + data[3]);
                    textFound = true;
                }
            }
            br.close();
            if(!textFound){
                throw new ExceptionInvalid("Texto não encontrado");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }catch(ExceptionInvalid e){
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    private void editPost() {
        try {

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream("data/texts.txt"), "UTF-8"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(separator);
                if (Integer.parseInt(data[0]) == user.getUserId()) {
                    System.out.println(data[2]);
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Erro de leitura");
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("Deseja editar qual texto?");
        String text = in.nextLine();
        System.out.println();
        boolean textFound = false;

        try {

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream("data/texts.txt"), "UTF-8"));
            String inputBuffer = "";
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(separator);
                if (Integer.parseInt(data[0]) == user.getUserId() && data[2].equals(text)) {
                    System.out.println(data[3]);
                    System.out.println();
                    System.out.println("Novo texto:");
                    System.out.println();
                    inputBuffer += data[0] + separator + data[1] + separator + data[2] + separator + in.nextLine();
                    System.out.println("Texto editado!");
                    System.out.println();
                    textFound = true;
                    break;
                } else {
                    inputBuffer += line;
                }
                inputBuffer += '\n';
            }
            br.close();

            if(!textFound){
                throw new ExceptionInvalid("Texto não encontrado!");
            }
            Writer bw = new BufferedWriter(new FileWriter("data/texts.txt"));
            bw.write(inputBuffer);
            bw.close();
        } catch (IOException e) {
            System.out.println("Erro de leitura");
            e.printStackTrace();
        }catch(ExceptionInvalid e){
            System.out.println(e.getMessage());
        }

    }

    private void deletePost() {
        try {

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream("data/texts.txt"), "UTF-8"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(separator);
                if (Integer.parseInt(data[0]) == user.getUserId()) {
                    System.out.println(data[2]);
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("Deseja apagar qual texto?");
        String text = in.nextLine();
        System.out.println();
        boolean textFound = false;

        try {

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream("data/texts.txt"), "UTF-8"));
            String inputBuffer = "";
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(separator);
                if (Integer.parseInt(data[0]) == user.getUserId() && data[2].equals(text)) {
                    System.out.println("Post deletado");
                    System.out.println();
                    textFound = true;
                } else {
                    inputBuffer += line;
                    inputBuffer += '\n';
                }
            }
            br.close();
            if(!textFound){
                throw new ExceptionInvalid("Texto não encontrado!");
            }
            Writer bw = new BufferedWriter(new FileWriter("data/texts.txt"));
            bw.write(inputBuffer);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }catch(ExceptionInvalid e){
            e.getMessage();
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
}
