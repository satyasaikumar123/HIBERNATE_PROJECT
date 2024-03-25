package feedback;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import participants.ParticipantEntity;

public class FeedbackDao {
	private final SessionFactory sessionFactory;

	public FeedbackDao(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	public void saveFeedback(FeedbackEntity feedback) {
		Session session=sessionFactory.openSession();
		Transaction tx=null;
		try {
			tx=session.beginTransaction();
			session.save(feedback);
			tx.commit();
		} catch (Exception e) {
			if (tx!=null) {
				System.out.println("getting exception");
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}
	
	public FeedbackEntity getFeedbackById(int id) {	
		Session session=sessionFactory.openSession();
		FeedbackEntity feedback =null;
		
		try {
			feedback=session.get(FeedbackEntity.class,id);
			session.close();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return feedback;
		}
	
	public List<FeedbackEntity> getAllFeedback() {
	    Session session = sessionFactory.openSession();
	    List<FeedbackEntity> feedbackList = null;
	    try {
	        feedbackList = session.createQuery("FROM FeedbackEntity", FeedbackEntity.class).list();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	    return feedbackList;
	}


    public void updateFeedback(FeedbackEntity feedback) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(feedback);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                System.out.println("Exception occurred while updating feedback. Rolling back transaction.");
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteFeedback(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            FeedbackEntity feedback = session.get(FeedbackEntity.class, id);
            session.delete(feedback);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                System.out.println("Exception occurred while deleting feedback. Rolling back transaction.");
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
