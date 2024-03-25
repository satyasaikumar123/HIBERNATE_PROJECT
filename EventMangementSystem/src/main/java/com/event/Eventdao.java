package com.event;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class Eventdao {
	private final SessionFactory sessionFactory;

public Eventdao(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	public void saveEvents(EventEntity event) {
		
		Session session = sessionFactory.openSession();
		Transaction tx=null;
		try {
			tx=session.beginTransaction();
			session.save(event);
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
	public EventEntity findbyid(int id) {
	    EventEntity event = null;
	    try (Session session = sessionFactory.openSession()) {
	        Query<EventEntity> query = session.createQuery("SELECT e FROM EventEntity e LEFT JOIN FETCH e.organizer o LEFT JOIN FETCH o.events WHERE e.eventId = :eventId", EventEntity.class);
	        query.setParameter("eventId", id);
	        event = query.uniqueResult();
	    } catch (Exception e) {
	        // Handle exception appropriately
	        e.printStackTrace();
	    }
	    return event;
	}


	public List<EventEntity> findall(){
		Session session=sessionFactory.openSession();
		List<EventEntity> event=null;
		try {
			event=session.createQuery("From EventEntity",EventEntity.class).list();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return event;
		
	}
	public void updateById(EventEntity event) {
		Session session=sessionFactory.openSession();
		 Transaction tx=null;
		 
		try {
			tx=session.beginTransaction();
			session.update(event);
			tx.commit();
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteById(EventEntity event) {
		Session session =sessionFactory.openSession();
		Transaction tx=null;
		try {
			tx=session.beginTransaction();
			session.delete(event);
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			session.close();
		}
		}
}
