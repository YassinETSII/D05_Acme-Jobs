
package acme.features.anonymous.announcement;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.announcements.Announcement;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousAnnouncementListService implements AbstractListService<Anonymous, Announcement> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousAnnouncementRepository repository;


	// AbstractListService<Anonymous, Announcement> interface --------------

	@Override
	public boolean authorise(final Request<Announcement> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "moment", "title");
	}

	@Override
	public Collection<Announcement> findMany(final Request<Announcement> request) {
		assert request != null;

		Calendar c1 = new GregorianCalendar();
		c1.add(Calendar.MONTH, -1);
		Date d1 = c1.getTime();

		Calendar c2 = new GregorianCalendar();
		Date d2 = c2.getTime();

		Collection<Announcement> result;
		result = this.repository.findManyAll(d1, d2);

		return result;

	}

}
