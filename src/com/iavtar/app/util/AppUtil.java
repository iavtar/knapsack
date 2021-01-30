package com.iavtar.app.util;

import java.util.ArrayList;
import java.util.List;

import com.iavtar.app.exception.ItemCostOutOfBoundException;
import com.iavtar.app.exception.ItemWeightOutOfBoundsException;
import com.iavtar.app.exception.ItemsOutOfBoundException;
import com.iavtar.app.model.Bundle;

/**
 * @author indra_singh
 */
public class AppUtil {
	
	/**
	 * @param arr
	 * @return pakageList
	 * @throws ItemWeightOutOfBoundsException, ItemCostOutOfBoundException, ItemsOutOfBoundException
	 * */
	public List<Bundle> FileToObjectList(String[] arr)
			throws ItemWeightOutOfBoundsException, ItemCostOutOfBoundException, ItemsOutOfBoundException {
		List<Bundle> pakageList = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			String[] newArr = (arr[i].substring(1, arr[i].length() - 1)).split(",");
			var id = Integer.parseInt(newArr[0]);
			var weight = Double.parseDouble(newArr[1]);
			if (weight > 100) {
				throw new ItemWeightOutOfBoundsException("The item weight is out of bound");
			}
			var cost = Double.parseDouble(newArr[2].substring(3, newArr[2].length()));
			if (cost > 100) {
				throw new ItemCostOutOfBoundException("The item cost is out of bound");
			}
			Bundle pkg = new Bundle(id, weight, cost);
			pakageList.add(pkg);
		}
		if(pakageList.size() > 15) {
			throw new ItemsOutOfBoundException("Items out of bound exception");
		}
		return pakageList;
	}

}
