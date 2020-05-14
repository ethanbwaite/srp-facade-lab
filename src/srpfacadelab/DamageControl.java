package srpfacadelab;

public class DamageControl {
  private final IGameEngine gameEngine;
  private final RpgPlayer player;

  public DamageControl(IGameEngine gameEngine, RpgPlayer player) {
    this.gameEngine = gameEngine;
    this.player = player;
  }

  public void takeDamage(int damage) {
    if (damage < player.getArmour()) {
        gameEngine.playSpecialEffect("parry");
    }

    int damageToDeal = damage - player.getArmour();

    if (player.getInventoryWeight() < player.getCarryingCapacity() * 0.5)
      damageToDeal *= 0.75;

    player.setHealth(player.getHealth() - damageToDeal);

    gameEngine.playSpecialEffect("lots_of_gore");
  }
  
}