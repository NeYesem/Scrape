import java.util.List;

public class Meals{
	  String img;
	  String name;
	  List<String> ingredients;
	  List<String> recipe;
	  
	  public Meals(String img, String name, List<String> ingredients, List<String> recipe) {
		  this.img = img;
		  this.name = name;
		  this.ingredients = ingredients;
		  this.recipe = recipe;				  
	  }
}
