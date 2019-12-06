
package acme.features.sponsor.nonCommercialBanner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.NonCommercialBanner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorNonCommercialBannerRepository extends AbstractRepository {

	@Query("select nc from NonCommercialBanner nc where nc.id = ?1")
	NonCommercialBanner findOneNonCommercialBannerById(int id);

	@Query("select nc from NonCommercialBanner nc where nc.sponsor.id = ?1")
	Collection<NonCommercialBanner> findManyNonCommercialBannersBySponsorId(int sponsorId);
}
