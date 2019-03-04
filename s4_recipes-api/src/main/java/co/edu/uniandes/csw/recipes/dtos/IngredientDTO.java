/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipes.dtos;

import co.edu.uniandes.csw.recipes.entities.RecipeEntity;

/**
 *
 * @author CesarF
 */
public class IngredientDTO {
    
    private String name;
    private String description;
    private Long id;
    
    public IngredientDTO(){
    
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    public IngredientDTO(RecipeEntity recipe) {
        this.id = recipe.getId();
        this.name = recipe.getName();
        this.description = recipe.getDescription();
    }
    
    public RecipeEntity toEntity() {
        RecipeEntity entity = new RecipeEntity();
        entity.setId(this.id);
        entity.setName(this.name);    
        entity.setDescription(this.description);
        return entity;
    }
    
   
    
}
