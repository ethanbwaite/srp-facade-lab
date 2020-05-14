package srpfacadelab;

public class PlayerFacade {
  private Inventory inventory;
  private DamageControl damageControl;

  public PlayerFacade(IGameEngine gameEngine, RpgPlayer player) {
    inventory = new Inventory(gameEngine, player);
    damageControl = new DamageControl(gameEngine, player);
  }

  public void useItem(Item item) {
    inventory.useItem(item);
  }

  public void pickUpItem(Item item) {
    inventory.useItem(item);
  }

  public void takeDamage(int damage) {
    damageControl.takeDamage(damage);
  }
}