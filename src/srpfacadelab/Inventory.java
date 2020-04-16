package srpfacadelab;

import java.util.List;
import java.util.ArrayList;

public class Inventory {

  private final IGameEngine gameEngine;

  private RpgPlayer player;

  private List<Item> inventory;

  public Inventory(IGameEngine gameEngine, RpgPlayer player){
    this.gameEngine = gameEngine;
    this.player = player;
    inventory = new ArrayList<Item>();
  }

  public void useItem(Item item) {
    List<IEnemy> enemies = gameEngine.getEnemiesNear(player);
    if (item.getName().equals("Stink Bomb"))
        for (IEnemy enemy: enemies){
            enemy.takeDamage(100);
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
          player.heal(item.getHeal());
          return true;
      }

      if (item.isRare()) {
          if (item.isUnique()) {
              gameEngine.playSpecialEffect("blue_swirly");
          } else {
              gameEngine.playSpecialEffect("cool_swirly_particles");
          }
      }

      inventory.add(item);

      calculateStats();

      return true;
  }

  private void calculateStats() {
    int totalArmour = 0;
    for (Item i: inventory) {
        totalArmour += i.getArmour();
    }
    player.setArmour(totalArmour);
  }

  private boolean checkIfItemExistsInInventory(Item item) {
    for (Item i: inventory) {
        if (i.getId() == item.getId())
            return true;
    }
    return false;
  } 

  private int calculateInventoryWeight() {
    int sum=0;
    for (Item i: inventory) {
        sum += i.getWeight();
    }
    return sum;
  }

  public int size(){
    return inventory.size();
  }
}