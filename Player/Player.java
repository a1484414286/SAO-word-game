package Player;

import Status.BaseTemplate;

public class Player {
    private BaseTemplate stats;
    
    public Player(BaseTemplate base)
    {
        this.stats = base;
    }

    public BaseTemplate getStats() {
        return stats;
    }

    public static void main(String[] args) {
        Player player1 = new Player(new BaseTemplate(0, "晓铜"));
        System.out.println(player1.getStats());
    }
}
