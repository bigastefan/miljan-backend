package hustlebuddy.repositories;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import hustlebuddy.models.AccountData;
import hustlebuddy.models.Client;
import hustlebuddy.models.PersonalData;

@Repository
public class ClientCustomRepositoryImpl implements ClientCustomRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Collection<Client> searchClients(String name, String lastName, String email, String username) {
		
		Session session = entityManager.unwrap(Session.class);
		CriteriaBuilder builder = session.getCriteriaBuilder();

		CriteriaQuery<Client> criteria = builder.createQuery(Client.class);

		Root<Client> myObjectRoot = criteria.from(Client.class);
		ArrayList<Predicate> predicates = new ArrayList<Predicate>();
		
		if (name != null && name != "") {
			Join<Client, PersonalData> joinPersonalData = myObjectRoot.join("personalData");
			Predicate likeRestrictionName = builder.and(builder.like(joinPersonalData.get("name"), "%" + name + "%"));
			predicates.add(likeRestrictionName);
		};
		
		if (lastName != null && lastName != "") {
			Join<Client, PersonalData> joinPersonalData = myObjectRoot.join("personalData");
			Predicate likeRestrictionLastName = builder.and(builder.like(joinPersonalData.get("lastName"), "%" + lastName + "%"));
			predicates.add(likeRestrictionLastName);
		};
		
		if (email != null && email!= "") {
			Join<Client, AccountData> joinAccountData = myObjectRoot.join("accountData");
			Predicate likeRestrictionEmail = builder.and(builder.like(joinAccountData.get("email"), "%" + email + "%"));
			predicates.add(likeRestrictionEmail);
		};
		
		if (username != null && username != "") {
			Join<Client, AccountData> joinAccountData = myObjectRoot.join("accountData");
			Predicate likeRestrictionUsername = builder.and(builder.like(joinAccountData.get("username"), "%" + username + "%"));
			predicates.add(likeRestrictionUsername);
		};

		return null;
	}

	

}
