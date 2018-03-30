package org.afc.challenge.sort;

public class FubarSort {

	private int[] array;

	public FubarSort(int[] array) {
		this.array = array;
	}

	public void sort() {
		split(0, array.length - 1);
	}

	private void split(int s, int e) {
		if (s == e) {
			return;
		} else if (e - s == 1) {
			if (array[s] > array[e]) {
				swap(s, e);
			}
			return;
		}
		int l = e - s;
		int ls = s;
		int le = s + l / 2;
		int rs = s + l / 2 + 1;
		int re = e;
		split(ls, le);
		split(rs, re);
		merge(ls, le, rs, re);
	}

	private void merge(int ls, int le, int rs, int re) {
		for(int i = ls; i <= le; i++) {
			for(int j = rs; j <= re; ) {
				if (array[i] > array[j]) {
					swap(i, j);
					bubble(j, re);
				} else {
					j++;
				}
			}
		}
	}

	private void swap(int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}

	private void bubble(int s, int e) {
		for (int i = s; i < e; i++) {
			if (array[i] > array[i + 1]) {
				swap(i, i + 1);
			} else {
				break;
			}
		}
	}

//	private void dump(int ls, int le, int rs, int re) {
//	    System.out.print("r - ls:" + ls + ",\tle:" + le + ",\trs:" + rs + ",\tre:" + re + "\t");
//		for (int i : array) {
//			System.out.print(i + "\t");
//		}
//		System.out.println();
//	}
}
