package srpfacadelab;

import java.util.List;
import java.util.ArrayList;

public class Inventory {
  private final IGameEngine gameEngine;
  private final RpgPlayer player;
  private List<Item> inventory;

  public Inventory(IGameEngine gameEngine, RpgPlayer player) {
    this.gameEngine = gameEngine;
    this.player = player;
    inventory = new ArrayList<Item>();
  }

  public void useItem(Item item) {
    if (item.getName().equals("Stink Bomb"))
    {
        List<IEnemy> enemies = gameEngine.getEnemiesNear(player);

        for (IEnemy enemy: enemies){
            enemy.takeDamage(100);
        }
    }
  }

  public boolean pickUpItem(Item item) {
      int weight = calculateInventoryWeight();
      if (weight + item.getWeight() > player.getCarryingCapacity())
          return false;

      if (item.isUnique() && checkIfItemExistsInInventory(item))
          return false;

      // Don't pick up items that give health, just consume them.
      if (item.getHeal() > 0) {
          player.setHealth(player.getHealth() + item.getHeal());

          if (player.getHealth() > player.getMaxHealth())
              player.setHealth(player.getMaxHealth());

          if (item.getHeal() > 500) {
              gameEngine.playSpecialEffect("green_swirly");
          }

          return true;
      }

      if (item.isRare()) {
        if (item.isUnique())
          gameEngine.playSpecialEffect("blue_swirly");
        gameEngine.playSpecialEffect("cool_swirly_particles");
      }

      inventory.add(item);
      calculateStats();
      return true;
  }

  private void calculateStats() {
      for (Item i: inventory) {
          player.setArmour( player.getArmour() + i.getArmour());
      }
      player.setInventoryWeight(calculateInventoryWeight());
  }

  private boolean checkIfItemExistsInInventory(Item item) {
      for (Item i: inventory) {
          if (i.getId() == item.getId())
              return true;
      }
      return false;
  }

  private int calculateInventoryWeight() {
      int sum = 0;
      for (Item i: inventory) {
          sum += i.getWeight();
      }
      return sum;
  }
}