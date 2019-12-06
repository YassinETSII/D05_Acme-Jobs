
package acme.features.administrator.customisation;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisation.Customisation;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorCustomisationUpdateService implements AbstractUpdateService<Administrator, Customisation> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorCustomisationRepository repository;


	// AbstractUpdateService<Administrator, Customisation> interface --------------

	@Override
	public boolean authorise(final Request<Customisation> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Customisation> request, final Customisation entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "spamWords", "spamThreshold");
	}

	@Override
	public void bind(final Request<Customisation> request, final Customisation entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<Customisation> request, final Customisation entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//Check that a repeated spam word is not entered
		if (!errors.hasErrors("spamWords")) { //Check if spamWords has no errors
			Set<String> duplicates = new HashSet<>();
			String[] words = entity.getSpamWords().split("\\s*,\\s*");
			Set<String> set = new HashSet<>();
			for (String word : words) {
				if (!set.add(word)) {
					duplicates.add(word);
				}
			}
			errors.state(request, duplicates.isEmpty(), "spamWords", "administrator.customisation.error.existingWord");
		}
	}

	@Override
	public Customisation findOne(final Request<Customisation> request) {
		assert request != null;
		Customisation result;

		result = this.repository.findOne();

		return result;
	}

	@Override
	public void update(final Request<Customisation> request, final Customisation entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
