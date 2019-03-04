/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipes.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author CesarF
 */
@Entity
public class IngredienteEntity extends BaseEntity {
    private String nombre;
    private Long calorias;
    @PodamExclude
    @ManyToOne
    private RecipeEntity receta;

    public RecipeEntity getReceta() {
        return receta;
    }

    public void setReceta(RecipeEntity receta) {
        this.receta = receta;
    }
    public IngredienteEntity(){
    
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getCalorias() {
        return calorias;
    }

    public void setCalorias(Long calorias) {
        this.calorias = calorias;
    }
   
    
    
}
