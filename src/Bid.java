public class Bid {
	final public String name;
	final public int bid;

	public Bid(String name, int bid) {
		this.name = name;
		this.bid = bid;
	}

	public String getName() {
		return this.name;
	}

	public int getBid() {
		return this.bid;
	}

	public int hashCode() {
		return 1 + 23*bid + 31*name.hashCode();
	}

	public boolean equals(Object obj){
		if (obj == null || !(obj instanceof Bid)) return false;

		Bid other = (Bid) obj;
		return this.bid == other.getBid() && this.name.equals(other.name);
	}

	@Override
	public String toString(){
		/*// TODO: return a description of the bid DONE
		throw new UnsupportedOperationException();*/
		return getName() + " " + getBid();
	}
}

