import model.*;

import java.time.LocalDateTime;
import java.util.*;

public class Main {
    private static final String PATH_TO_EXTENT = "extent.txt";

    public static void main(String[] args) {
        boolean doContinue = true;
        while (doContinue) {
            try {
                for (int i = 0; i < 20; i++) {
                    System.out.println();
                }
                System.out.println("Enter action number:");
                System.out.println("1.Load Extent");
                System.out.println("2.Add food(add to extent)");
                System.out.println("3.List available food(list extent)");
                System.out.println("4.Restock all food products(using extent)");
                System.out.println("5.Add animal(add to extent)");
                System.out.println("6.List animals in zoo(list extent)");
                System.out.println("7.Get scheduled time for animal feeding(enumerable attribute)");
                System.out.println("8.Feed all the animals(method override)");
                System.out.println("9.Save Extent and exit");

                Scanner in = new Scanner(System.in);

                switch (in.nextLine()) {
                    case "1": {
                        ObjectPlus.readExtents(PATH_TO_EXTENT);
                    }
                    break;
                    case "2": {
                        System.out.println("Enter food type[meat/vegetables/fruits]:");
                        String type = in.nextLine().trim();
                        System.out.println("Enter food amount in Kg:");
                        float amount = in.nextFloat();
                        in.nextLine();
                        new Food(amount, type);
                    }
                    break;
                    case "3": {
                        try {
                            Food.printFoodExtent();
                            System.out.println("Press anything to continue");
                            in.nextLine();
                        } catch (ClassNotFoundException e) {
                            System.out.println(e.getMessage());
                            System.out.println("Press anything to continue");
                            in.nextLine();
                        }
                    }
                    break;
                    case "4": {
                        try {
                            System.out.println("Insert amount of food to restock in Kg");
                            float amountInKg = in.nextFloat();
                            in.nextLine();
                            Food.refillFood(amountInKg);
                        } catch (ClassNotFoundException e) {
                            System.out.println(e.getMessage());
                            System.out.println("Press anything to continue");
                            in.nextLine();
                        }
                    }
                    break;
                    case "5": {
                        System.out.println("Select Animal type to add:");
                        System.out.println("1.Tiger");
                        System.out.println("2.Monkey");
                        int animalSelected = in.nextInt();
                        in.nextLine();

                        System.out.println("Enter favourite food list(seperated with ,) For default enter '0'");
                        String favouriteFoodIn = in.nextLine().trim();
                        List<String> favouriteFoodList = new ArrayList<>();
                        if (favouriteFoodIn.equals("0")) {
                            favouriteFoodList = List.of("Banana", "Kiwi");
                        } else {
                            Collections.addAll(favouriteFoodList, favouriteFoodIn.split(","));
                        }

                        System.out.println("Enter date of last feeding[yyyy-MM-ddTHH:mm:ss]. For current date&time enter '0'");
                        String lastFeedDateIn = in.nextLine().trim();
                        LocalDateTime lastFeedDate;
                        if (lastFeedDateIn.equals("0")) {
                            lastFeedDate = LocalDateTime.now();
                        } else {
                            lastFeedDate = LocalDateTime.parse(lastFeedDateIn);
                        }

                        System.out.println("Enter how much food is eaten(in Kg)by this animal.");
                        float feedingAmount = in.nextFloat();
                        in.nextLine();

                        System.out.println("Enter how many hours will pass before next feeding of this animal.");
                        int hours = in.nextInt();
                        in.nextLine();

                        System.out.println("Enter species[OPTIONAL]. For none enter '0'");
                        String species = in.nextLine().trim();
                        if (favouriteFoodIn.equals("0")) {
                            if (animalSelected == 1) {
                                new Tiger(favouriteFoodList, lastFeedDate, feedingAmount, hours);
                            } else if (animalSelected == 2) {
                                new Monkey(favouriteFoodList, lastFeedDate, feedingAmount, hours);
                            }
                        } else {
                            if (animalSelected == 1) {
                                new Tiger(species, favouriteFoodList, lastFeedDate, feedingAmount, hours);
                            } else if (animalSelected == 2) {
                                new Monkey(species, favouriteFoodList, lastFeedDate, feedingAmount, hours);
                            }
                        }
                    }
                    break;
                    case "6": {
                        try {
                            Animal.printAnimalExtent();
                            System.out.println("Press anything to continue");
                            in.nextLine();
                        } catch (ClassNotFoundException e) {
                            System.out.println(e.getMessage());
                            System.out.println("Press anything to continue");
                            in.nextLine();
                        }
                    }
                    break;
                    case "7": {
                        try {
                            Iterable<Animal> animals = ObjectPlus.getExtent(Animal.class);
                            animals.forEach(a -> {
                                System.out.println(a.toString());
                                System.out.println(a.getDateOfNextFeeding());
                            });
                            System.out.println("Press anything to continue");
                            in.nextLine();
                        } catch (ClassNotFoundException e) {
                            System.out.println(e.getMessage());
                            System.out.println("Press anything to continue");
                            in.nextLine();
                        }
                    }
                    break;
                    case "8": {
                        try {
                            Iterable<Animal> animals = ObjectPlus.getExtent(Animal.class);
                            animals.forEach(Animal::feed);
                            System.out.println("Press anything to continue");
                            in.nextLine();
                        } catch (ClassNotFoundException e) {
                            System.out.println(e.getMessage());
                            System.out.println("Press anything to continue");
                            in.nextLine();
                        }
                    }
                    break;
                    case "9": {
                        ObjectPlus.writeExtents(PATH_TO_EXTENT);
                        doContinue = false;
                    }
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Press anything to continue");
                Scanner in = new Scanner(System.in);
                in.nextLine();
            }
        }
    }
}
