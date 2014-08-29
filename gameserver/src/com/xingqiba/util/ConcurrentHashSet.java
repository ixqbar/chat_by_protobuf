package com.xingqiba.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author ixqbar@gmail.com
 *
 * @param <Q>
 */
public class ConcurrentHashSet<Q> implements Set<Q> {
	
	private ConcurrentHashMap<Q, Object> map = new ConcurrentHashMap<Q, Object>();
	
	public ConcurrentHashSet() {
		this.map = new ConcurrentHashMap<Q, Object>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#add(E)
	 */
	public boolean add(Q item) {
		boolean containsObj = this.map.containsKey(item);
		if (containsObj == false) {
			/*
			 * ConcurrentHashMap doesn't allow null keys or values so we simply
			 * use the item added to the collection as the key and the Boolean
			 * TRUE object as the value.
			 */
			this.map.put(item, Boolean.TRUE);
		}
		return !containsObj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#addAll(java.util.Collection)
	 */
	public boolean addAll(Collection<? extends Q> items) {
		boolean changed = false;
		for (Q item : items) {
			/* update flag determining whether set has changed or not */
			changed = add(item) || changed;
		}
		return changed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#clear()
	 */
	public void clear() {
		this.map.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#contains(java.lang.Object)
	 */
	public boolean contains(Object item) {
		return this.map.containsKey(item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#containsAll(java.util.Collection)
	 */
	public boolean containsAll(Collection<?> items) {
		return this.map.keySet().containsAll(items);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#isEmpty()
	 */
	public boolean isEmpty() {
		return this.map.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#iterator()
	 */
	public Iterator<Q> iterator() {
		return this.map.keySet().iterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#remove(java.lang.Object)
	 */
	public boolean remove(Object item) {
		/* we double up argument as both key and value */
		return map.remove(item, Boolean.TRUE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#removeAll(java.util.Collection)
	 */
	public boolean removeAll(Collection<?> items) {
		return this.map.keySet().removeAll(items);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#retainAll(java.util.Collection)
	 */
	public boolean retainAll(Collection<?> items) {
		return map.keySet().retainAll(items);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#size()
	 */
	public int size() {
		return this.map.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#toArray()
	 */
	public Object[] toArray() {
		return this.map.keySet().toArray();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Set#toArray(T[])
	 */
	public <T> T[] toArray(T[] array) {
		return this.map.keySet().toArray(array);
	}

}
