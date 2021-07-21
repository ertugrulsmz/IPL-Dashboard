package io.javabrains.ipldashboard.exception;

public class TeamNotFoundException extends RuntimeException {
    public TeamNotFoundException(String team_does_not_exists) {
        super(team_does_not_exists);
    }
}
