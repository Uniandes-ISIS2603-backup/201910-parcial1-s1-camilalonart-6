/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipes.dtos;

import co.edu.uniandes.csw.recipes.entities.IngredienteEntity;
import co.edu.uniandes.csw.recipes.entities.RecipeEntity;

/**
 *
 * @author CesarF
 */
public class IngredientDTO {
    
    private String nombre;
    private Long calorias;
    private Long id;
    
    public IngredientDTO(){
    
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    
    public IngredientDTO(IngredienteEntity entity) {
        this.id = entity.getId();
        this.nombre = entity.getNombre();
        this.calorias = entity.getCalorias();
    }
    
    public IngredienteEntity toEntity() {
        IngredienteEntity entity = new IngredienteEntity();
        entity.setId(this.id);
        entity.setNombre(this.nombre);    
        entity.setCalorias(this.calorias);
        return entity;
    }
    
   
    
}
