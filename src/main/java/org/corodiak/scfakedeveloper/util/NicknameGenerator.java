package org.corodiak.scfakedeveloper.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.springframework.core.io.ClassPathResource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NicknameGenerator {

	private List<String> adjList;
	private List<String> nounList;
	private Random random;

	public NicknameGenerator() throws IOException {
		ClassPathResource c1 = new ClassPathResource("static/adj.csv");
		if(!c1.exists()) {
			System.out.println(c1);
		}
		InputStream inputStreamAdj = c1.getInputStream();
		InputStream inputStreamNoun = new ClassPathResource("static/noun.csv").getInputStream();
		adjList = new ArrayList<>();
		nounList = new ArrayList<>();

		Scanner adjScanner = new Scanner(inputStreamAdj);
		Scanner nounScanner = new Scanner(inputStreamNoun);

		while (adjScanner.hasNext()) {
			adjList.add(adjScanner.nextLine());
		}
		while (nounScanner.hasNext()) {
			nounList.add(nounScanner.nextLine());
		}
		random = new Random();
	}

	public String generate() {
		return adjList.get(random.nextInt(adjList.size())) + nounList.get(random.nextInt(nounList.size()))
			+ String.format("%04d", random.nextInt(10000));
	}
}
