package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {
	public static List<Integer> fibonacci() {
		int limit = 350;
		int i = 0;
		ArrayList<Integer> ListFibonacci = new ArrayList<>();
		ListFibonacci.add(0);
		ListFibonacci.add(1);
		while(i < limit) {
			i = Integer.sum(ListFibonacci.get(ListFibonacci.size() - 1), ListFibonacci.get(ListFibonacci.size() - 2));
			ListFibonacci.add(i);
		}
		return ListFibonacci;
	}
	public static Boolean isFibonacci(Integer a) {
		return fibonacci().contains(a);
	}

}