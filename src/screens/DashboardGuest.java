package screens;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import exceptions.ExceptionInvalid;

public class DashboardGuest extends Screens {
    private static Scanner in = new Scanner(System.in);
    private String separator = "BDE@BV";

    public int showScreen() {
        while (true) {
            System.out.println("1-Ver todos os textos");
            System.out.println("2-Desconectar");

            String menuOpt = in.nextLine();
            System.out.println();

            switch (menuOpt) {
            case "1":
                showPosts();
                break;
            case "2":
                return 1;

            default:
                throw new ExceptionInvalid("Opção invália!");
            }
        }
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
        } catch (Exception e) {
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
                throw new ExceptionInvalid("texto não encontrado!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }catch(ExceptionInvalid e){
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

}
