package com.company.enroller.persistence;

import java.util.Collection;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import com.company.enroller.model.Meeting;

@Component("meetingService")
public class MeetingService {

	DatabaseConnector connector;

	public MeetingService() {
		connector = DatabaseConnector.getInstance();
	}

	public Collection<Meeting> getAll() {
		String hql = "FROM Meeting";
		Query query = connector.getSession().createQuery(hql);
		return query.list();
	}

	public Collection<Meeting> getAllSortedByTitle(String order) {
		String hql="";
		if (order.equals("ASC")){
			hql = "FROM Meeting m ORDER BY m.title ASC";
		} else {
			hql = "FROM Meeting m ORDER BY m.title DESC";
		}
		Query query = connector.getSession().createQuery(hql);
		return query.list();
	}

	public Collection<Meeting> getAllContains(String word) {
		String hql = "FROM Meeting m where m.title like :word or m.description like :word";
		Query query = connector.getSession().createQuery(hql);
		query.setParameter("word","%"+word+"%");
		return query.list();
	}

	public Meeting findById(long id){
		return  (Meeting)  connector.getSession().get(Meeting.class, id);
	}
	public void delete(Meeting meeting){
		Transaction transaction= connector.getSession().beginTransaction();
		connector.getSession().delete(meeting);
		transaction.commit();
	}
	public void add(Meeting meeting){
		Transaction transaction= connector.getSession().beginTransaction();
		connector.getSession().save(meeting);
		transaction.commit();
	}

	public void update(Meeting meeting) {
		Transaction transaction= connector.getSession().beginTransaction();
		connector.getSession().update(meeting);
		transaction.commit();
	}

//	public Meeting getMeetingParticipants(long id) {
//		String hql = "FROM Meeting M WHERE M.id = :meeting_id ";
//		Query query = connector.getSession().createQuery(hql);
//		query.setParameter("meeting_id",id);
//		return query.;
//	}
}
