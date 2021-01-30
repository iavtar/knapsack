package com.iavtar.app.Processor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.iavtar.app.model.Bundle;

/**
 * @author indra_singh
 */
public class KnapsackProcessor {

	/**
	 * @param maxWeight
	 * @param packages
	 */
	public String process(int maxWeight, List<Bundle> packages) {

		List<Bundle> usableBundle = packages.stream().filter(bd -> bd.getWeight() <= maxWeight)
				.collect(Collectors.toList());

		if (usableBundle.isEmpty()) {
			return "-";
		}
		String res1 = "";
		double mxCost = 0;
		double mxWeight = 0;
		for (int i = 1; i <= usableBundle.size(); i++) {
			String res = getOtimizedFor(usableBundle, i, maxWeight);
			String[] strArr = res.split(",");
			double ct = Double.parseDouble(strArr[strArr.length - 2]);
			double wt = Double.parseDouble(strArr[strArr.length - 1]);
			if (ct == mxCost) {
				if (wt < mxWeight) {
					mxCost = ct;
					mxWeight = wt;
					res1 = res;
				}
			}
			if (ct > mxCost) {
				mxCost = ct;
				mxWeight = wt;
				res1 = res;
			}
		}
		String[] strArr1 = res1.split(",");
		String s2 = "";
		for (int j = 0; j < strArr1.length - 2; j++) {
			s2 += usableBundle.get(Integer.parseInt(strArr1[j])).getId() + ",";
		}
		if (s2.equals("")) {
			return ("-");
		} else {
			return s2.substring(0, s2.length() - 1);
		}
	}

	/**
	 * @param usablePackages
	 * @param currentIteration
	 * @param maxWeight
	 */
	private String getOtimizedFor(List<Bundle> usableBundle, int ci, int maxWeight) {

		int idx = 0;
		String rData = "";
		double mxWt = 0;
		double mxCt = 0;
		int[] data = new int[ci];
		List<Integer> res = new ArrayList<>();
		int[] arr = new int[usableBundle.size()];
		for (int i = 0; i < usableBundle.size(); i++) {
			arr[i] = i;
		}
		getCombo(arr, data, res, 0, 0);
		for (int i = 0; i <= res.size() - ci; i += ci) {
			double sWeight = 0;
			double sCost = 0;
			for (int j = 0; j < ci; j++) {
				sWeight += usableBundle.get(res.get(i + j)).getWeight();
				sCost += usableBundle.get(res.get(i + j)).getCost();
			}
			if (sWeight <= maxWeight) {
				if ((sCost > mxCt) || ((sCost == mxCt) && (sWeight <= maxWeight))) {
					idx = i;
					mxWt = sWeight;
					mxCt = sCost;
				}
			}
		}
		for (int i = idx; i < ci + idx; i++) {
			rData += res.get(i) + ",";
		}
		return rData + mxCt + "," + mxWt;
	}

	private void getCombo(int[] arr, int[] data, List<Integer> res, int st, int idx) {

		if (idx == data.length) {
			for (int i = 0; i < data.length; i++) {
				res.add(data[i]);
			}
			return;
		}
		for (int i = st; i < arr.length && arr.length - i >= data.length - idx; i++) {
			data[idx] = arr[i];
			getCombo(arr, data, res, i + 1, idx + 1);
		}

	}

}
