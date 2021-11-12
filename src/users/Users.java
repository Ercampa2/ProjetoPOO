package users;

public class Users {
    private static int userID = 0;

    public static int getUserID() {
        return userID;
    }

    public static void setUserID(int userID) {
        Users.userID = userID;
    }

    public static void addOneUserId(){
        userID++;
    }
}
