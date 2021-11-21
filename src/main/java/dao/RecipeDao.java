package dao;

import models.Medications;
import models.Recipe;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtils;
import java.util.List;

public class RecipeDao {
    public void saveRecipe(Recipe recipe) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(recipe);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateRecipe(Recipe recipe) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(recipe);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Recipe getRecipeById(int id) {
        Transaction transaction = null;
        Recipe recipe = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            recipe = session.get(Recipe.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return recipe;
    }

    public List<Recipe> getAllRecipe() {
        List<Recipe> recipes = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            recipes = session.createQuery("from models.Recipe", Recipe.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipes;
    }

    public void deleteRecipe(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Recipe recipe = session.get(Recipe.class, id);
            if (recipe != null) {
                session.delete(recipe);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Recipe addMedicationsToRecipe(Recipe recipe, List<Medications> medications) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Medications> oldMedications = recipe.getMedications();
            medications.forEach(x -> {
                if (!oldMedications.contains(x)) {
                    oldMedications.add(x);
                }
            });
            recipe.setMedications(oldMedications);
            session.update(recipe);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return recipe;
    }
}
