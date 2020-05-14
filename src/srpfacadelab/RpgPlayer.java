package srpfacadelab;

public class RpgPlayer {
    public static final int MAX_CARRYING_CAPACITY = 1000;

    private int health;

    private int maxHealth;

    private int armour;

    // How much the player can carry in pounds
    private int carryingCapacity;

    private int inventoryWeight;

    public RpgPlayer() {
        carryingCapacity = MAX_CARRYING_CAPACITY;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getArmour() {
        return armour;
    }

    public void setArmour(int armour) {
        this.armour = armour;
    }

    public int getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public int getInventoryWeight() {
        return inventoryWeight;
    }

    public void setInventoryWeight(int inventoryWeight) {
        this.inventoryWeight = inventoryWeight;
    }
}
