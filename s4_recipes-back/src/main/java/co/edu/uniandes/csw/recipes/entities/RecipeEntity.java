/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipes.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author CesarF
 */
@Entity
public class RecipeEntity extends BaseEntity {
    private String name;
    private String description;
    
    @PodamExclude
    @OneToMany(mappedBy = "recipe")
    private List<IngredienteEntity> ingredientes = new ArrayList<IngredienteEntity>();
            
    public RecipeEntity(){
    
    }

    public List<IngredienteEntity> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<IngredienteEntity> ingredientes) {
        this.ingredientes = ingredientes;
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
    
    
}
