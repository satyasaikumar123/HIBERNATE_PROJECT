package categories;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.event.EventEntity;

import java.util.List;

public class CategoryDao {
    private SessionFactory sessionFactory;

    public CategoryDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    // Create operation
    public void saveCategory(CategoryEntity category) {
        Session session = sessionFactory.openSession();
        Transaction tx=null;
        try {
        	tx=session.beginTransaction();
        	session.save(category);
        	tx.commit();
			
		} catch (Exception e) {
			if(tx!=null) {
				System.out.println("getting exception");
				tx.rollback();
			}
			e.printStackTrace();
		}
        finally {
			session.close();
		}
    }
    // Read operation
    public CategoryEntity getCategoryById(int id) {
        Session session = sessionFactory.openSession();
        CategoryEntity category = null;
        try {
            category = session.get(CategoryEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return category;
    }

    // Update operation
    public void updateCategory(CategoryEntity category) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(category);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("Exception occurred while updating category. Rolling back transaction.");
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Delete operation
    public void deleteCategory(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            CategoryEntity category = session.get(CategoryEntity.class, id);
            session.delete(category);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("Exception occurred while deleting category. Rolling back transaction.");
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Retrieve all categories
    public List<CategoryEntity> getAllCategories() {
        Session session = sessionFactory.openSession();
        List<CategoryEntity> categories = null;
        try {
            categories = session.createQuery("FROM CategoryEntity", CategoryEntity.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return categories;
    }

    public List<EventEntity> getEventsByCategoryName(String categoryName) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT e FROM EventEntity e JOIN e.categories c WHERE c.name = :categoryName";
            Query<EventEntity> query = session.createQuery(hql, EventEntity.class);
            query.setParameter("categoryName", categoryName);
            return query.getResultList();
        }
    }
}