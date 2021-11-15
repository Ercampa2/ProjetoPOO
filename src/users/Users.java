package users;

public class Users {
    private static int numUsers = 0;

    public static int getUserID() {
        return numUsers;
    }

    public static void setUserID(int userID) {
        Users.numUsers = userID;
    }

    public static void addOneUserId(){
        numUsers++;
    }
}
