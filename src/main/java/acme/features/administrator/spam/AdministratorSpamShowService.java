
package acme.features.administrator.spam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.Spam;
import acme.entities.words.Word;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorSpamShowService implements AbstractShowService<Administrator, Spam> {

	@Autowired
	protected AdministratorSpamRepository spamRepo;


	@Override
	public void unbind(final Request<Spam> request, final Spam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		StringBuilder s = new StringBuilder(500);
		s = s.append(entity.getSpamWordsList().get(0).getWord());
		for (final Word w : entity.getSpamWordsList().subList(1, entity.getSpamWordsList().size())) {
			s = s.append(", " + w.getWord());
		}
		model.setAttribute("lista", s);
		request.unbind(entity, model, "threshold", "spamWordsList");
	}
	@Override
	public boolean authorise(final Request<Spam> request) {
		assert request != null;
		boolean result= false;
		if(request.getPrincipal().hasRole("Administrator")) {
			result= true;
			
		}

		return result;
	}

	@Override
	public Spam findOne(final Request<Spam> request) {
		assert request != null;

		Spam result;

		result = this.spamRepo.searchOne().iterator().next();
		
		return result;

	}

}
