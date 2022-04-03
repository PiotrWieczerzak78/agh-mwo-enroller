package com.company.enroller.persistence;

import java.util.Collection;

import org.hibernate.Transaction;
import org.hibernate.engine.transaction.internal.TransactionImpl;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.company.enroller.model.Participant;

@Component("participantService")
public class ParticipantService {

	DatabaseConnector connector;

	public ParticipantService() {
		connector = DatabaseConnector.getInstance();
	}

	public Collection<Participant> getAll() {
		String hql = "FROM Participant";
		Query query = connector.getSession().createQuery(hql);
		return query.list();
	}

	//jesli jest szukanie tylko po id to jest ponizszy kod, nie trzeba robic hql
	public Participant findByLogin(String login){
//		String hql = "FROM Participant WHERE Participant.login ="+login;
//		Query query = connector.getSession().createQuery(hql);
//		return query.;
		return  (Participant)  connector.getSession().get(Participant.class, login);
	}

	public void delete(Participant participant){
		Transaction transaction= connector.getSession().beginTransaction();
		connector.getSession().delete(participant);
		transaction.commit();
	}
	public void add(Participant participant){
		Transaction transaction= connector.getSession().beginTransaction();
		connector.getSession().save(participant);
		transaction.commit();
	}

	public void update(Participant participant) {
		Transaction transaction= connector.getSession().beginTransaction();
		connector.getSession().update(participant);
		transaction.commit();
	}
}


