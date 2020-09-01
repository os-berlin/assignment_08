package com.coderscampus.assignment08.service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.assignment08.dto.Recipe;
import com.coderscampus.assignment08.repository.RecipeList;

@Service
public class FileService {
	
	@Autowired
	private RecipeList recipeList;

	public List<Recipe> readRecipes() throws IOException {
		
		Reader in = new FileReader("recipes.txt");

		Iterable<CSVRecord> csvRecipes = CSVFormat.DEFAULT.withFirstRecordAsHeader()
												  		  .withDelimiter(',')
												  		  .withEscape('\\')
												  		  .withIgnoreSurroundingSpaces()
												  		  .parse(in);

		for (CSVRecord recipe : csvRecipes) {
			Integer cookingMinutes = Integer.parseInt(recipe.get(0));
			Boolean dairyFree = Boolean.parseBoolean(recipe.get(1));
			Boolean glutenFree = Boolean.parseBoolean(recipe.get(2));
			String instructions = recipe.get(3);
			Double preparationMinutes = Double.parseDouble(recipe.get(4));
			Double pricePerServing = Double.parseDouble(recipe.get(5));
			Integer readyInMinutes = Integer.parseInt(recipe.get(6));
			Integer servings = Integer.parseInt(recipe.get(7));
			Double spoonacularScore = Double.parseDouble(recipe.get(8));
			String title = recipe.get(9);
			Boolean vegan = Boolean.parseBoolean(recipe.get(10));
			Boolean vegetarian = Boolean.parseBoolean(recipe.get(11));

			recipeList.getRecipes().add(new Recipe(cookingMinutes, dairyFree, glutenFree, instructions, preparationMinutes,
					pricePerServing, readyInMinutes, servings, spoonacularScore, title, vegan, vegetarian));
			
		}

		return recipeList.getRecipes();
	}

	public List<Recipe> filterRecipes(String filter) throws IOException {
		
		if (recipeList.getRecipes().isEmpty()) {
			readRecipes();
		}
		
		switch (filter) {
		case "glutenFree": 
			return recipeList.getRecipes()
							 .stream()
							 .filter(Recipe::getGlutenFree)
							 .collect(Collectors.toList());
		
		case "vegan":
			return recipeList.getRecipes()
							 .stream()
							 .filter(Recipe::getVegan)
							 .collect(Collectors.toList());
		
		case "veganGlutenFree":
			return recipeList.getRecipes()
							 .stream()
							 .filter(Recipe::getVegan)
							 .filter(Recipe::getGlutenFree)
							 .collect(Collectors.toList());
		
		case "vegetarian":
			return recipeList.getRecipes()
							 .stream()
							 .filter(Recipe::getVegetarian)
							 .collect(Collectors.toList());
			
		case "all":
			return recipeList.getRecipes();
			
		default: return null;
		
		}		
		
	}

}
