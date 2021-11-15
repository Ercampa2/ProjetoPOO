package users;

public class Users {
    private static int numUsers = 0;

    //VALUES:
    // -2 -> NENHUM/DESCONECTADO
    // -1 -> CONVIDADO
    // >=0 -> USUÁRIO
    private int userID = -2;

    public static int getnumUsers() {
        return numUsers;
    }

    public static void setnumUsers(int numUsers) {
        Users.numUsers = numUsers;
    }

    public static void addOneUserId(){
        numUsers++;
    }

    public void setUserId(int userId){
        this.userID = userId;
    }
    public int getUserId(){
        return this.userID;
    }
}
