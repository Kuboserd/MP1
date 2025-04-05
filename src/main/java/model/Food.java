package model;

public class Food extends ObjectPlus {
    private float amountInKg;
    private String type;

    public Food(float amountInKg, String type) {
        setAmountInKg(amountInKg);
        setType(type);
        ObjectPlus.addExtent(this);
    }

    public static void refillFood(float amountInKg) throws ClassNotFoundException {

        Iterable<Food> foods = (Iterable<Food>) ObjectPlus.getExtent(Food.class);
        foods.forEach(s -> s.setAmountInKg(s.getAmountInKg() + amountInKg));

        System.out.println("Food stocks were refilled");

    }

    public static void printFoodExtent() throws ClassNotFoundException {
        ObjectPlus.showExtent(Food.class);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName())
                .append("{type:")
                .append(this.getType())
                .append(", amount in Kg:")
                .append(this.getAmountInKg())
                .append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (this.getClass().equals(obj.getClass())) {
            Food temp = (Food) obj;
            if (this.getType().equals(temp.getType())
                    && this.getAmountInKg() == temp.getAmountInKg()) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public float getAmountInKg() {
        return amountInKg;
    }

    public void setAmountInKg(float amountInKg) {
        if (amountInKg >= 0) {
            this.amountInKg = amountInKg;
        } else throw new IllegalArgumentException("Food amount can't be negative.");
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type.equals("meat") || type.equals("vegetables") || type.equals("fruits")) {
            this.type = type;
        } else throw new IllegalArgumentException("Invalid food type:" + type);
    }
}
