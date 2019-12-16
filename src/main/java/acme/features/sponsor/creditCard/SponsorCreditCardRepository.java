
package acme.features.sponsor.creditCard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.creditCards.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorCreditCardRepository extends AbstractRepository {

	@Query("select c from CreditCard c where c.id = ?1")
	CreditCard findOneById(int id);

	@Query("select s from Sponsor s where s.id = ?1")
	Sponsor findOneSponsorById(int id);

	@Query("select s from Sponsor s where s.userAccount.id = ?1")
	Sponsor findOneSponsorByUserAccountId(int accountId);

	@Query("select s from Sponsor s where s.creditCard.id = ?1")
	Sponsor findOneSponsorByCreditCardId(int ccId);

}
