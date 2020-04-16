package srpfacadelab;

public class RpgPlayer {
    public static final int MAX_CARRYING_CAPACITY = 1000;

    private final IGameEngine gameEngine;

    private int health;

    private int maxHealth;

    private int armour;

    private Inventory inventory;

    // How much the player can carry in pounds
    private int carryingCapacity;

    public RpgPlayer(IGameEngine gameEngine) {
        this.gameEngine = gameEngine;
        carryingCapacity = MAX_CARRYING_CAPACITY;
        inventory = new Inventory(gameEngine, this);
    }

    public boolean pickUpItem(Item item) {
        return inventory.pickUpItem(item);
    }

    public void useItem(Item item) {
        inventory.useItem(item);
    }

    public void takeDamage(int damage) {
        if (damage < armour) {
            gameEngine.playSpecialEffect("parry");
        }

        int damageToDeal = damage - armour;

        if (inventory.size() < carryingCapacity/2) {
            damageToDeal -= damageToDeal*.25;
        }

        health -= damageToDeal;

        gameEngine.playSpecialEffect("lots_of_gore");
    }

    public void heal(int amount){
        health = health + amount;

          if (health > maxHealth)
              health = maxHealth;

          if (amount > 500) {
              gameEngine.playSpecialEffect("green_swirly");
          }
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

    private void setCarryingCapacity(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }
}
