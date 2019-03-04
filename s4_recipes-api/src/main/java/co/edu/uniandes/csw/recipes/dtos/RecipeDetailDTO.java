/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipes.dtos;

import co.edu.uniandes.csw.recipes.entities.IngredienteEntity;
import co.edu.uniandes.csw.recipes.entities.RecipeEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CesarF
 */
public class RecipeDetailDTO extends RecipeDTO {
   private List<IngredientDTO> ingredients;

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }

    public RecipeDetailDTO(){
    
    }
    
    public RecipeDetailDTO(RecipeEntity recipeEntity) {
        super(recipeEntity);
        if (recipeEntity != null) {
            if (recipeEntity.getIngredientes() != null) {
                ingredients = new ArrayList<>();
                for (IngredienteEntity entity : recipeEntity.getIngredientes()) {
                    ingredients.add(new IngredientDTO(entity));
                }
            }
        }
    }

    @Override
    public RecipeEntity toEntity() {
        RecipeEntity recipeEntity = super.toEntity();
        if (ingredients != null) {
            List<IngredienteEntity> ingredientesEntity = new ArrayList<>();
            for (IngredientDTO dto : ingredients) {
                ingredientesEntity.add(dto.toEntity());
            }
            recipeEntity.setIngredientes(ingredientesEntity);
        }
        return recipeEntity;
    }
    
}
