package participants;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ParticipantDao {
	public final SessionFactory sessionFactory;

	public ParticipantDao(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	public void saveparticipant(ParticipantEntity participant) {
		Session session=sessionFactory.openSession();
		Transaction tx=null;
		try {
			tx=session.beginTransaction();
			session.save(participant);
			tx.commit();
		} catch (Exception e) {
			if (tx!=null) {
				tx.rollback();
				System.out.println("getting exception");
			}
			e.printStackTrace();
			
		}
	}

	public ParticipantEntity findById(int id) {
		
		Session session=sessionFactory.openSession();
		ParticipantEntity participant =null;
		
		try {
			participant=session.get(ParticipantEntity.class,id);
			session.close();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return participant;
		}
	public List<ParticipantEntity> getAllParticipants() {
	    Session session = sessionFactory.openSession();
	    List<ParticipantEntity> participantList = null;
	    try {
	        participantList = session.createQuery("FROM ParticipantEntity", ParticipantEntity.class).list();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	    return participantList;
	}

	

	    public void updateParticipant(ParticipantEntity participant) {
	        Session session = sessionFactory.openSession();
	        Transaction tx = null;
	        try {
	            tx = session.beginTransaction();
	            session.update(participant);
	            tx.commit();
	        } catch (Exception e) {
	            if (tx != null) {
	                tx.rollback();
	                System.out.println("Exception occurred while updating participant. Rolling back transaction.");
	            }
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	    }

	    public void deleteParticipant(int id) {
	        Session session = sessionFactory.openSession();
	        Transaction tx = null;
	        try {
	            tx = session.beginTransaction();
	            ParticipantEntity participant = session.get(ParticipantEntity.class, id);
	            session.delete(participant);
	            tx.commit();
	        } catch (Exception e) {
	            if (tx != null) {
	                tx.rollback();
	                System.out.println("Exception occurred while deleting participant. Rolling back transaction.");
	            }
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	    }
	}