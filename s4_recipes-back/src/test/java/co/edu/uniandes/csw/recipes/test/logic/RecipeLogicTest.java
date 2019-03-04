/*
MIT License

Copyright (c) 2019 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.recipes.test.logic;
import co.edu.uniandes.csw.recipes.ejb.RecipeLogic;
import co.edu.uniandes.csw.recipes.entities.IngredienteEntity;
import co.edu.uniandes.csw.recipes.entities.RecipeEntity;
import co.edu.uniandes.csw.recipes.persistence.RecipePersistence;

import co.edu.uniandes.csw.recipes.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
/**
 * Pruebas de logica para la cascara.
 *
 * @author ISIS2603
 */
@RunWith(Arquillian.class)

public class RecipeLogicTest {
     private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private RecipeLogic recipeLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<RecipeEntity> data = new ArrayList<RecipeEntity>();
    private List<IngredienteEntity> ingData = new ArrayList();

    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RecipeEntity.class.getPackage())
                .addPackage(RecipeLogic.class.getPackage())
                .addPackage(RecipePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    
    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    
    private void clearData() {
        em.createQuery("delete from RecipeEntity").executeUpdate();
        em.createQuery("delete from IngredienteEntity").executeUpdate();
        
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            IngredienteEntity  iing = factory.manufacturePojo(IngredienteEntity.class);
            em.persist(iing);
            ingData.add(iing);
        }
        for (int i = 0; i < 3; i++) {
            RecipeEntity entity = factory.manufacturePojo(RecipeEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                ingData.get(i).setReceta(entity);
            }
        }
        
        
    }

    
    @Test
    public void createRecipeTest() throws BusinessLogicException {
        RecipeEntity newEntity = factory.manufacturePojo(RecipeEntity.class);
        RecipeEntity result = recipeLogic.createRecipe(newEntity);
        Assert.assertNotNull(result);
        RecipeEntity entity = em.find(RecipeEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getDescription(), entity.getDescription());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createRecipeTestConNombreInvalido1() throws BusinessLogicException {
        RecipeEntity newEntity = factory.manufacturePojo(RecipeEntity.class);
        newEntity.setName(null);
        recipeLogic.createRecipe(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createRecipeTestConNombreInvalido2() throws BusinessLogicException {
        RecipeEntity newEntity = factory.manufacturePojo(RecipeEntity.class);
        newEntity.setName("");
        recipeLogic.createRecipe(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createRecipeTestConNombreInvalido3() throws BusinessLogicException {
        RecipeEntity newEntity = factory.manufacturePojo(RecipeEntity.class);
        newEntity.setName("asdfghjkljhgftykhjbjnkgdnklndklkjjnvglfasdfghjkljhgftykhjbjnkgdnklndklkjjnvglfdznkasdfghjkljhgftykhjbjnkgdnklndklkjjnvglfdznkasdfghjkljhgftykhjbjnkgdnklndklkjjnvglfdznkasdfghjkljhgftykhjbjnkgdnklndklkjjnvglfdznkasdfghjkljhgftykhjbjnkgdnklndklkjjnvglfdznkdznk");
        recipeLogic.createRecipe(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createRecipeTestSinRecetas() throws BusinessLogicException {
        RecipeEntity newEntity = factory.manufacturePojo(RecipeEntity.class);
        newEntity.setIngredientes(new ArrayList<IngredienteEntity>());
        recipeLogic.createRecipe(newEntity);
    }
    
}
