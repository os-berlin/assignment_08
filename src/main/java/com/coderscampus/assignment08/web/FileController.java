package com.coderscampus.assignment08.web;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderscampus.assignment08.dto.Recipe;
import com.coderscampus.assignment08.service.FileService;

@RestController
public class FileController {
	
	@Autowired
	private FileService fileService;	

	@GetMapping("")
	public String RecipesBook () {
		return ("Please visit one of the following endpoints: <br>"
				+ "<a href=\"/gluten-free\">/gluten-free</a><br>" 
				+ "<a href=\"/vegan\">/vegan</a><br>" 
				+ "<a href=\"/vegan-and-gluten-free\">/vegan-and-gluten-free</a><br>" 
				+ "<a href=\"/vegetarian\">/vegetarian</a><br>" 
				+ "<a href=\"/all-recipes\">/all-recipes</a><br>");
	}
	
	@GetMapping("/gluten-free")
	public List<Recipe> filterGlutenFree () throws IOException {
		 return fileService.filterRecipes("glutenFree");
	}

	@GetMapping("/vegan")
	public List<Recipe> filterVegan () throws IOException {
		 return fileService.filterRecipes("vegan");
	}

	@GetMapping("/vegan-and-gluten-free")
	public List<Recipe> filterVeganGlutenFree () throws IOException {
		 return fileService.filterRecipes("veganGlutenFree");
	}

	@GetMapping("/vegetarian")
	public List<Recipe> filterVegetarian () throws IOException {
		 return fileService.filterRecipes("vegetarian");
	}

	@GetMapping("/all-recipes")
	public List<Recipe> filterAllRecipes () throws IOException {
		 return fileService.filterRecipes("all");
	}
}
