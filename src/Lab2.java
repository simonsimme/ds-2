
import java.io.*;
import java.util.*;

public class Lab2 {
	public static String pureMain(String[] commands) {
		// TODO: declaration of two priority queues DONE
		PriorityQueue<Integer> buyOffers = new PriorityQueue<>(new Comparator<Integer>() {
			public int compare(Integer i1, Integer i2) {
				return i2 - i1;
			}});
		PriorityQueue<Integer> sellOffers = new PriorityQueue<>(new Comparator<Integer>() {
			public int compare(Integer i1, Integer i2) {
				return i2 - i1;
			}});

		PriorityQueue<Bid> buyerQueue = new PriorityQueue<>(new AscendingBidComparator());
		PriorityQueue<Bid> sellerQueue = new PriorityQueue<>(new DescendingBidComparator());

		ArrayList<Touple<String, Integer>> nameList = new ArrayList<>();

		StringBuilder sb = new StringBuilder();

		for(int line_no=0;line_no<commands.length;line_no++){
			String line = commands[line_no];
			if( line.equals("") )continue;

			String[] parts = line.split("\\s+");
			if( parts.length != 3 && parts.length != 4)
				throw new RuntimeException("line " + line_no + ": " + parts.length + " words");
			String name = parts[0];
			if( name.charAt(0) == '\0' )
				throw new RuntimeException("line " + line_no + ": invalid name");
			String action = parts[1];
			int price;
			try {
				price = Integer.parseInt(parts[2]);
			} catch(NumberFormatException e){
				throw new RuntimeException(
						"line " + line_no + ": invalid price");
			}

			nameList.add(new Touple<>(name, price));

			if( action.equals("K") ) {
				// TODO: add new buy bid DONE
				buyOffers.add(price);
			} else if( action.equals("S") ) {
				sellOffers.add(price);
				// TODO: add new sell bid DONE
			} else if( action.equals("NK") ){
				for (int i = 0; i < buyOffers.size(); i++) {
					if(buyOffers.getHeap().get(i).equals(price))
					{
						try
						{
							buyOffers.replaceAtIndex(i, Integer.parseInt(parts[3]));
						}catch (NumberFormatException e){
							throw new RuntimeException(
									"line " + line_no + ": invalid changing price");
						}
					}
				} // bruh hur uploadar man till gitt, eller kopiar du till din?
				// TODO: update existing buy bid. use parts[3]. DONE
			} else if( action.equals("NS") ){
				for (int i = 0; i < sellOffers.size(); i++) {
					if(sellOffers.getHeap().get(i).equals(price))
					{
						try
						{
							sellOffers.replaceAtIndex(i, Integer.parseInt(parts[3]));
						}catch (NumberFormatException e){
							throw new RuntimeException(
									"line " + line_no + ": invalid changing price");
						}
					}
				}
				// TODO: update existing sell bid. use parts[3].
			} else {
				throw new RuntimeException(
						"line " + line_no + ": invalid action");
			}

			if( buyOffers.size() == 0 || sellOffers.size() == 0 )continue;

			// TODO: Done
			// compare the bids of highest priority from each of
			// each priority queues.
			// if the lowest seller price is lower than or equal to
			// the highest buyer price, then remove one bid from
			// each priority queue and add a description of the
			// transaction to the output.

			while (buyOffers.minimum() >= sellOffers.minimum())
			{
				String buyName = new String();
				int buyerIndex = -1;
				for (int i = 0; i < nameList.size(); i++) {
					if(nameList.get(i).getSecond().equals(buyOffers.minimum()))
					{
						buyName = nameList.get(i).getFirst();
						buyerIndex=i;
					}
				}
				String sellName = new String();
				int sellerIndex = -1;
				for (int i = 0; i < nameList.size(); i++) {
					if(nameList.get(i).getSecond().equals(sellOffers.minimum()))
					{
						sellName = nameList.get(i).getFirst();
						sellerIndex =i;
					}
				}
				printPurchase(buyName, sellName, sellOffers.minimum());
				buyOffers.deleteMinimum();
				sellOffers.deleteMinimum();
				nameList.remove(buyerIndex);
				nameList.remove(sellerIndex);
			}
		}

		sb.append("Order book:\n");

		sb.append("Sellers: ");
		// TODO: print remaining sellers.
		//       can remove from priority queue until it is empty.

		sb.append("Buyers: ");
		// TODO: print remaining buyers
		//       can remove from priority queue until it is empty.

		return sb.toString();
	}
	private static void printPurchase(String name1, String name2, int price){
		System.out.println(name1 + " buys from " + name2 + " for " + price);
	}

	public static void main(String[] args) throws IOException {
		final BufferedReader actions;
		if( args.length != 1 ){
			actions = new BufferedReader(new InputStreamReader(System.in));
		} else {
			actions = new BufferedReader(new FileReader(args[0]));
		}

		List<String> lines = new LinkedList<String>();
		while(true){
			System.out.println("here");

			String line = actions.readLine();
			if( line == null || line.equals("break"))break;
			lines.add(line);
		}
		actions.close();

		System.out.println(pureMain(lines.toArray(new String[lines.size()])));
	}
}
