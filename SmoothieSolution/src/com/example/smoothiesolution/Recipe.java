package com.example.smoothiesolution;

public class Recipe {
	private String recipe_id;
	private String recipe_name;
	private String category;
	private String prep_time;

	public Recipe(String recipe_id, String recipe_name, String category, String prep_time) {
		this.recipe_id = recipe_id;
	    this.recipe_name = recipe_name;
		this.category = category;
		this.prep_time = prep_time;
	}
	
	public String getRecipeID() {
		return recipe_id;
	}
	
	public String getName() {
		return recipe_name;
	}
	
	public String getCategory() {
		return category;
	}
	
	public String getPrepTime() {
		return prep_time;
	}
	
	
}