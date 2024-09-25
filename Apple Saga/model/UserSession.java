package model;

public class UserSession {
    private static UserSession instance;
    private int currentUserId;

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void setCurrentUserId(int userId) {
        this.currentUserId = userId;
    }

    public int getCurrentUserId() {
        return currentUserId;
    }

    public void clearSession() {
        currentUserId = 0;
    }
}