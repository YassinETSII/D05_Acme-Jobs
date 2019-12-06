
package acme.features.auditor.auditRecord;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuditorAuditRecordListService implements AbstractListService<Auditor, AuditRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuditorAuditRecordRepository repository;


	// AbstractListService<Auditor, AuditRecord> interface --------------

	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment");
	}

	//An auditor principal can not list a not finalMode audit record from another auditor
	@Override
	public Collection<AuditRecord> findMany(final Request<AuditRecord> request) {
		assert request != null;

		Collection<AuditRecord> col1;
		Collection<AuditRecord> col2;
		Collection<AuditRecord> result;

		Principal principal;
		principal = request.getPrincipal();
		int id1 = request.getModel().getInteger("idJob");
		int id2 = principal.getActiveRoleId();

		col1 = this.repository.findManyNotFinalModeAuditRecordsByJobIdAndAuditorId(id1, id2);

		col2 = this.repository.findManyFinalModeAuditRecordsByJobId(id1);

		result = Stream.concat(col1.stream(), col2.stream()).collect(Collectors.toList());

		return result;

	}

}
