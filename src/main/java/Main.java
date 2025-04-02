import model.*;

public class Main {
    private static final String PATH_TO_EXTENT = "extent.txt";

    public static void main(String[] args) {
        ObjectPlus.readExtents(PATH_TO_EXTENT);

//        new Food(10, "fruits");
//        new Food(10, "meat");

        Food.printFoodExtent();
        Food.refillFood(50);
        Food.printFoodExtent();

//        new Monkey(List.of("Banana", "Kiwi"), LocalDateTime.now(), 10f, 8);
//        new Monkey("Cebus capucinus", List.of("Banana", "Kiwi"), LocalDateTime.now(), 10f, 10);
//
//        new Tiger(List.of("Poultry"), LocalDateTime.now(), 50f, 16);
//        new Tiger("Panthera tigris", List.of("Poultry"), LocalDateTime.now(), 50f, 16);

        Animal.printAnimalExtent();
        try {
            Iterable<Animal> animals = ObjectPlus.getExtent(Animal.class);
            animals.forEach(Animal::feed);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        ObjectPlus.writeExtents(PATH_TO_EXTENT);
    }
}
