package model;

import exceptions.IllegalInvokeTimeException;

import java.time.LocalDateTime;
import java.util.List;

public final class Tiger extends Animal {
    private static int feedEveryXHours;

    public Tiger(String species, List<String> favouriteFood, LocalDateTime dateOfLastFeeding, float foodEatenPerFeeding,
                 int feedEveryXHours) {
        super(species, favouriteFood, dateOfLastFeeding, foodEatenPerFeeding);
        setFeedEveryXHours(feedEveryXHours);
        ObjectPlus.addExtent(this);
    }

    public Tiger(List<String> favouriteFood, LocalDateTime dateOfLastFeeding, float foodEatenPerFeeding,
                 int feedEveryXHours) {
        super(favouriteFood, dateOfLastFeeding, foodEatenPerFeeding);
        setFeedEveryXHours(feedEveryXHours);
        ObjectPlus.addExtent(this);
    }

    @Override
    public void feed() {
        if (getDateOfNextFeeding().isBefore(LocalDateTime.now())) {
            throw new IllegalInvokeTimeException("Feeding cannot be executed before:" + getDateOfNextFeeding());
        }
        setDateOfLastFeeding(LocalDateTime.now());
        System.out.println("Eating " + getFoodEatenPerFeeding() + "Kg of feed.");
    }

    @Override
    public LocalDateTime getDateOfNextFeeding() {
        return getDateOfLastFeeding().plusHours(getFeedEveryXHours());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        return sb.replace(sb.length() - 1, sb.length(), "")
                .append(", feeding occurs every: ")
                .append(this.getDateOfNextFeeding())
                .append("h}")
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return this.getClass().equals(obj.getClass());
        }
        return false;
    }

    public static int getFeedEveryXHours() {
        return feedEveryXHours;
    }

    public static void setFeedEveryXHours(int feedEveryXHours) {
        if (feedEveryXHours <= 0) {
            throw new IllegalArgumentException("Feeding cannot be more frequent than every hour.");
        } else {
            Tiger.feedEveryXHours = feedEveryXHours;
        }
    }
}
