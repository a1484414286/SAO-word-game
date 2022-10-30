package Player;

import Bags.GeneralBag;
import Items.ItemTypes.Potion;
import Status.BaseTemplate;

public class Player {
    private String name;
    private BaseTemplate stats;
    private GeneralBag bag;
    
    public Player(int id, String name)
    {
        this.stats = new BaseTemplate(id, name);
        this.bag = new GeneralBag();
    }

    public BaseTemplate getStats() {
        return stats;
    }

    public GeneralBag getBag() {
        return bag;
    }

    public static void main(String[] args) {
        Player player1 = new Player(0, "晓铜");
        System.out.println(player1.getStats());
        player1.getBag().addChild(new Potion(0, "红药水", " +10 HP", 20, 5, 1));
        System.out.println(player1.getBag());
        //查看药水背包:
        player1.getBag().getCategoryBag(0).print();;
        
    }
}
