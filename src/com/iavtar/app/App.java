package com.iavtar.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Optional;

import com.iavtar.app.Processor.KnapsackProcessor;
import com.iavtar.app.exception.MaxWeightOutOfBoundException;
import com.iavtar.app.model.Bundle;
import com.iavtar.app.util.AppUtil;

/**
 * @author indra_singh
 */
public class App {
	
	public static void main(String[] args) {

		File file = new File(args[0]);

		try (var reader = new BufferedReader(new FileReader(file))) {
			String str = "";
			while (!((str = reader.readLine()) == null)) {
				if (str.isBlank()) {
					continue;
				}
				String[] arr1 = str.split(" : ");
				int maxWeight = Integer.parseInt(arr1[0]);
				if(maxWeight > 100) {
					throw new MaxWeightOutOfBoundException("Max Weight is out of bound");
				}
				Optional<String[]> arr2 = Optional.ofNullable(arr1[1].split(" "));
				if (arr2.isPresent()) {
					AppUtil util = new AppUtil();
					List<Bundle> packages = util.FileToObjectList(arr2.get());
					KnapsackProcessor kskProcessor = new KnapsackProcessor();
					String response = kskProcessor.process(maxWeight, packages);
					System.out.println(response);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
