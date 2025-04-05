package model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

public abstract class Animal extends ObjectPlus {
    private String species;
    private List<String> favouriteFood;
    private LocalDateTime dateOfLastFeeding;
    private float foodEatenPerFeeding;

    public Animal(String species, List<String> favouriteFood, LocalDateTime dateOfLastFeeding, float foodEatenPerFeeding) {
        super();
        setSpecies(species);
        setFavouriteFood(favouriteFood);
        setDateOfLastFeeding(dateOfLastFeeding);
        setFoodEatenPerFeeding(foodEatenPerFeeding);
    }

    public Animal(List<String> favouriteFood, LocalDateTime dateOfLastFeeding, float foodEatenPerFeeding) {
        super();
        setSpecies(null);
        setFavouriteFood(favouriteFood);
        setDateOfLastFeeding(dateOfLastFeeding);
        setFoodEatenPerFeeding(foodEatenPerFeeding);
    }

    public abstract void feed();

    public abstract LocalDateTime getDateOfNextFeeding();

    public static void printAnimalExtent() throws ClassNotFoundException {
        ObjectPlus.showExtent(Animal.class);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append(this.getClass().getSimpleName())
                .append("{species:")
                .append(this.getSpecies())
                .append(", favourite food:")
                .append(this.getFavouriteFood())
                .append(", food eaten per feeding:")
                .append(this.getFoodEatenPerFeeding())
                .append(", date of last feeding:")
                .append(this.getDateOfLastFeeding())
                .append("}")
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (this.getClass().equals(obj.getClass())) {
            Animal temp = (Animal) obj;
            if (this.getDateOfLastFeeding().equals(temp.getDateOfLastFeeding())
                    && this.getFavouriteFood().equals(temp.getFavouriteFood())
                    && this.getSpecies().equals(temp.getSpecies())
                    && this.getFoodEatenPerFeeding() == temp.getFoodEatenPerFeeding()) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        Pattern pattern = Pattern.compile("^[A-Za-z ]+$");
        if (species == null) {
            this.species = null;
            return;
        }
        if (pattern.matcher(species).matches()) {
            this.species = species;
        } else throw new IllegalArgumentException("Illegal species.");
    }

    public List<String> getFavouriteFood() {
        return favouriteFood;
    }

    public void setFavouriteFood(List<String> favouriteFood) {
        if (!favouriteFood.isEmpty()) {
            this.favouriteFood = favouriteFood;
        } else throw new IllegalArgumentException("Provided list is empty.");
    }

    public LocalDateTime getDateOfLastFeeding() {
        return dateOfLastFeeding;
    }

    public void setDateOfLastFeeding(LocalDateTime dateOfLastFeeding) {
        this.dateOfLastFeeding = dateOfLastFeeding;
    }

    public float getFoodEatenPerFeeding() {
        return foodEatenPerFeeding;
    }

    public void setFoodEatenPerFeeding(float foodEatenPerFeeding) {
        if (foodEatenPerFeeding > 0) {
            this.foodEatenPerFeeding = foodEatenPerFeeding;
        } else throw new IllegalArgumentException("Food rations can't be equal or less than 0.");
    }
}
