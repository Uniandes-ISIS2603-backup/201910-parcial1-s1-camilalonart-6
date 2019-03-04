/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipes.ejb;

import co.edu.uniandes.csw.recipes.entities.RecipeEntity;
import co.edu.uniandes.csw.recipes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.recipes.persistence.RecipePersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author CesarF
 */
@Stateless
public class RecipeLogic {
    @Inject
    private RecipePersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
    
    private static final Logger LOGGER = Logger.getLogger(RecipeLogic.class.getName());

    public RecipeEntity getRecipe(Long id) {
        return persistence.find(id);
    }

    //TODO crear el método createRecipe

    public RecipeEntity createRecipe (RecipeEntity recipeEntity) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la receta");
        if (recipeEntity != null) {
            throw new BusinessLogicException("No se puede crear la receta con id = " + recipeEntity.getId() + " porque ya existe");
        }
        if (recipeEntity.getName() != null || recipeEntity.getName().equals("")) {
            throw new BusinessLogicException("Ingrese un nombre valido para crear la receta");
        }
        if (recipeEntity.getName().length()>30) {
            throw new BusinessLogicException("El nombre de la receta no puede superar los 30 caracteres");
        }
        if (persistence.findByName(recipeEntity.getName()) != null) {
            throw new BusinessLogicException("Ya existe una receta con este nombre");
        }
        if (recipeEntity.getDescription() != null || recipeEntity.getName().equals("")) {
            throw new BusinessLogicException("La descripcion no puede estar vacia");
        }
        if (recipeEntity.getDescription().length()>150) {
            throw new BusinessLogicException("La descripcion de la receta no puede superar los 150 caracteres");
        }
        if (recipeEntity.getIngredientes().size()<1) {
            throw new BusinessLogicException("La receta debe tener 1 ingrediente al menos");
        }
        RecipeEntity newRecipeEntity = persistence.createRecipe(recipeEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la receta");
        return newRecipeEntity;
        
    }
}
