package br.com.codenation;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StatisticUtil {

	public static int average(int[] elements) {
		return Arrays.stream(elements).sum() / elements.length;
	}

	public static int mode(int[] elements) {
		Map<Integer, Long> nums = Arrays.stream(elements).boxed().collect(
				Collectors.groupingBy(Function.identity(), Collectors.counting())
		);
		Long maxValue = Collections.max(nums.values());
		for(Map.Entry<Integer, Long> entry: nums.entrySet()){
			if(entry.getValue().equals(maxValue)){
				return entry.getKey();
			}
		}
		return 0;
	}

	public static int median(int[] elements) {
		Arrays.sort(elements);
		if(elements.length % 2 != 0){
			return elements[(elements.length -1)/2];
		}
		return (elements[elements.length/2 - 1] + elements[elements.length/2]) / 2;
	}
}