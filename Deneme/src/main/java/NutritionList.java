import java.util.List;

public class NutritionList {
	String nutrition_name;
	List<Nutrition> nutritions;
	
	public NutritionList(String nutrition_name, List<Nutrition> nutritions) {
		this.nutrition_name = nutrition_name;
		this.nutritions = nutritions;
	}
}
