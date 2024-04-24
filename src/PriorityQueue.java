import java.util.*;

// A priority queue.
public class PriorityQueue<E> {
	private ArrayList<E> heap = new ArrayList<E>(); // Arraylist of elements
	private HashMap<Integer, E> map = new HashMap<>(); //Map <position, item> for efficency when searching through the heap
	private Comparator<E> comparator; // the comparetor

	public PriorityQueue(Comparator<E> comparator) {
		this.comparator = comparator;
	}

	// Returns the size of the priority queue.
	public int size() {
		return map.size();
	}

	// Add element to heap
	public void add(E x) { // Complexity : O()
		heap.add(x);  // Add element to the end
		int index = heap.size() - 1;  // Get the correct index of the newly added element
		map.put(index, x);
		siftUp(index);  // Correctly sift it up to maintain heap property
	}


	public java.util.ArrayList<E> getHeap() {
		return heap;
	}
	//gets the element of index
	public E getElementOfIndex(int index) // Complexity : O()
	{
	return map.get(index);
	}
	// compares the size of the map collection to zero
	public boolean isEmpty()
	{
		return map.size() == 0;
	}

	//replace the changed price then shifts it into place
	public void replaceAtIndex(int index, E i) { // Complexity :
		if (index < 0 || index >= map.size()) return; // check valid index

		E oldvalue = map.get(index);
		heap.set(index, i);
		map.put(index, i);


		//check sift up || down
		if (comparator.compare(i, oldvalue) > 0) {
			siftDown(index);
		} else {
			siftUp(index);
		}
	}

	// Returns the smallest item in the priority queue.
	// Throws NoSuchElementException if empty.
	public E minimum() {// Complexity : O()
		if (size() == 0)
			throw new NoSuchElementException();

		return map.get(0);
	}

	// Removes the smallest item in the priority queue.
	// Throws NoSuchElementException if empty.h
	public void deleteMinimum() {// Complexity : O()
		if (size() == 0)
			throw new NoSuchElementException();

		heap.set(0, map.get(map.size()-1));
		heap.remove(map.size()-1);
		map.remove(map.get(0));
		if (map.size() > 0) siftDown(0);
	}

	// Sifts a node up.
	// siftUp(index) fixes the invariant if the element at 'index' may
	// be less than its parent, but all other elements are correct.
	private void siftUp(int index) {// Complexity :
		E value = map.get(index);

		while (index > 0) {
			int parentIndex = parent(index);
			E parentValue = map.get(parentIndex);

			if (comparator.compare(value, parentValue) < 0) {
				heap.set(index, parentValue);
				map.put(index, parentValue);
				index = parentIndex;
			} else {
				break;
			}
		}
		heap.set(index, value);
		map.put(index, value);

	}

	// Sifts a node down.
	// siftDown(index) fixes the invariant if the element at 'index' may
	// be greater than its children, but all other elements are correct.
	private void siftDown(int index) {// Complexity : O()
		E value = map.get(index);

		// Stop when the node is a leaf.
		while (leftChild(index) < map.size()) {
			int left    = leftChild(index);
			int right   = rightChild(index);

			// Work out whether the left or right child is smaller.
			// Start out by assuming the left child is smaller...
			int child = left;
			E childValue = map.get(left);

			// ...but then check in case the right child is smaller.
			// (We do it like this because maybe there's no right child.)
			if (right < map.size()) {
				E rightValue = map.get(right);

				if (comparator.compare(childValue, rightValue) > 0) {
					child = right;
					childValue = rightValue;
				}
			}

			// If the child is smaller than the parent,
			// carry on downwards.
			if (comparator.compare(value, childValue) > 0) {
				heap.set(index, childValue);
				map.put(index, childValue);

				index = child;
			} else break;
		}

		heap.set(index, value);
		map.put(index, value);

	}

	// Helper functions for calculating the children and parent of an index.
	private final int leftChild(int index) {
		return 2*index+1;
	}

	private final int rightChild(int index) {
		return 2*index+2;
	}

	private final int parent(int index) {
		return (index-1)/2;
	}
}