
import java.io.*;
import java.util.*;

public class Lab2 {
	public static String pureMain(String[] commands) {
		// TODO: declaration of two priority queues DONE
		// Our two Queues for the stock
		PriorityQueue<Bid> buyQueue = new PriorityQueue<>(new DescendingBidComparator());
		PriorityQueue<Bid> sellQueue = new PriorityQueue<>(new AscendingBidComparator());


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


			if( action.equals("K") ) {
				// TODO: add new buy bid DONE
				buyQueue.add(new Bid(name, price));
			} else if( action.equals("S") ) {
				sellQueue.add(new Bid(name, price));
				// TODO: add new sell bid DONE

			} else if( action.equals("NK") ){
				//for changing the initial price, we search through the map to find the bid which match with name and price
				// , then we change the price to the new value
				int newPrice = Integer.parseInt(parts[3]);
				Bid temp = new Bid(name, price);
				for (int i = 0; i < buyQueue.size(); i++) {
					if(buyQueue.getElementOfIndex(i).equals(temp))
					{
						try
						{
							Bid updatedBid = new Bid(name,newPrice);
							buyQueue.replaceAtIndex(i, updatedBid);
						}catch (NumberFormatException e){
							throw new RuntimeException(
									"line " + line_no + ": invalid changing price");
						}
					}
				}
				// TODO: update existing buy bid. use parts[3]. DONE
			} else if( action.equals("NS") ){
				//for changing the initial price, we search through the map to find the bid which match with name and price
				// , then we change the price to the new value
				int newPrice = Integer.parseInt(parts[3]);
				Bid temp = new Bid(name, price);
				for (int i = 0; i < sellQueue.size(); i++) {
					if(sellQueue.getElementOfIndex(i).equals(temp))
					{
						try
						{
							Bid updatedbid = new Bid(name, newPrice);
							sellQueue.replaceAtIndex(i, updatedbid);
						}catch (NumberFormatException e){
							throw new RuntimeException(
									"line " + line_no + ": invalid changing price");
						}
					}
				}
				// TODO: update existing sell bid. use parts[3]. DONE
			} else {
				throw new RuntimeException(
						"line " + line_no + ": invalid action");
			}

			if( buyQueue.size() == 0 || sellQueue.size() == 0 ) continue;

			// TODO: DONE
			// compare the bids of highest priority from each of
			// each priority queues.
			// if the lowest seller price is lower than or equal to
			// the highest buyer price, then remove one bid from
			// each priority queue and add a description of the
			// transaction to the output.


			// If queues not empty and transaction possible, prints the deal, then deletes both bids of the queues
			while (!buyQueue.isEmpty() && !sellQueue.isEmpty()
					&& buyQueue.minimum().getBid() >= sellQueue.minimum().getBid()) {

				String buyer = buyQueue.minimum().getName();
				String seller = sellQueue.minimum().getName();
				int soldFor = sellQueue.minimum().getBid();

				printPurchase(buyer, seller, soldFor);
				buyQueue.deleteMinimum();
				sellQueue.deleteMinimum();
			}
		}

		sb.append("Order book:\n");

		sb.append("Sellers: ");
		// TODO: print remaining sellers. DONE
		// here we print all the sellers left
		while (sellQueue.size() > 0) {
			sb.append(sellQueue.minimum().toString());
			if (sellQueue.size() > 1) {
				sb.append(", ");
			}
			sellQueue.deleteMinimum();
		}


		sb.append("Buyers: ");
		// TODO: print remaining buyers
		// here we print all the buyers left
		while (buyQueue.size() > 0) {
			sb.append(buyQueue.minimum().toString());
			if (buyQueue.size() > 1) {
				sb.append(", ");
			}
			buyQueue.deleteMinimum();
		}

		return sb.toString();
	}

	//method that prints when a purchase has been made
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
			String line = actions.readLine();
			if( line == null)break;
			lines.add(line);
		}
		actions.close();

		System.out.println(pureMain(lines.toArray(new String[lines.size()])));
	}
}