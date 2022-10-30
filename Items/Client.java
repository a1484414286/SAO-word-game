// package Items;

// import java.util.ArrayList;

// import Bags.BagScanner;
// import Bags.BagVisitor;
// import Items.ItemTypes.Crystal;
// import Items.ItemTypes.Potion;

// public class Client {
//     public static void main(String[] args) {
//         ArrayList<ItemElement> bag = new ArrayList<>();
//         bag.add(new Crystal(0, "anti teleport", null, 10, 20, 1));
//         bag.add(new Potion(1, "red potion", null, 5, 3, 1));
//         calculate(bag);
// 	}

//     public static void calculate(ArrayList<ItemElement> bag)
//     {
//         int totalValue = 0;
//         int totalWeight = 0;
//         int itemCount = 0;
//         BagVisitor visitor = new BagScanner();
//         for(ItemElement i : bag)
//         {
//            totalValue += i.accept(visitor)[0];
//            totalWeight += i.accept(visitor)[1];
//            itemCount ++;
//         }
//         System.out.println("背包总价值 : " + totalValue + " 背包总重量 : " + totalWeight + " 背包物品数量 : " + itemCount);
//     }
// 	// private static int calculatePrice(ItemElement[] items) {
// 	// 	ShoppingCartVisitor visitor = new ShoppingCartVisitorImpl();
// 	// 	int sum=0;
// 	// 	for(ItemElement item : items){
// 	// 		sum = sum + item.accept(visitor);
// 	// 	}
// 	// 	return sum;
// 	// }
// }
