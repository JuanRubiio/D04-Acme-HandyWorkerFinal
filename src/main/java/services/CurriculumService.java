
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CurriculumRepository;
import domain.Curriculum;

@Service
@Transactional
public class CurriculumService {

	@Autowired
	private CurriculumRepository	curriculumRepository;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private UtilitiesService		utilitiesService;


	public CurriculumService() {
		super();
	}

	/*
	 * 21. Tickers must adhere to the following pattern: yymmdd-xxxxxx, where yymmdd refers to
	 * the year, month, and day when the corresponding entity is registered, and xxxxxx to a
	 * random uppercase alpha-numeric string. No two entities may have the same ticker since its
	 * assumed to be a unique external identifier.
	 */
	public Curriculum create() {
		final Curriculum result = new Curriculum();

		result.setTicker(this.utilitiesService.generateTicker());
		return result;
	}
	//	private String generateTicker() {
	//		String res;
	//		final Random rdn = new Random();
	//		final String[] a = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
	//				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
	//				"W", "X", "Y", "Z" };
	//		String letra1 = a[rdn.nextInt(a.length - 1)];
	//		String letra2 = a[rdn.nextInt(a.length - 1)];
	//		String letra3 = a[rdn.nextInt(a.length - 1)];
	//		String letra4 = a[rdn.nextInt(a.length - 1)];
	//		String letra5 = a[rdn.nextInt(a.length - 1)];
	//		String letra6 = a[rdn.nextInt(a.length - 1)];
	//		String division = "-";
	//		String dia = Integer.toString(Calendar.getInstance().get(
	//				Calendar.DAY_OF_MONTH));
	//		String mes = Integer.toString(Calendar.getInstance().get(
	//				Calendar.MONTH)+1);
	//		if(mes.length()==1){
	//			mes = "0"+mes;
	//		}
	//		String annio = Integer.toString(Calendar.getInstance().get(
	//				Calendar.YEAR)).substring(2);
	//
	//		res =  annio + mes + dia + division + letra1 + letra2 + letra3 + letra4 + letra5 + letra6;
	//
	//		Curriculum curriculum = this.curriculumRepository.findByTicket(res);
	//		if (curriculum != null)
	//			res = this.generateTicker();
	//		return res;
	//	}

	public Collection<Curriculum> findAll() {
		Collection<Curriculum> result;

		Assert.notNull(this.curriculumRepository);

		result = this.curriculumRepository.findAll();

		Assert.notNull(result);

		return result;
	}

	public Curriculum findOne(final Integer curriculumId) {
		Curriculum result;

		Assert.notNull(curriculumId);

		result = this.curriculumRepository.findOne(curriculumId);

		Assert.notNull(result);

		return result;
	}

	public Curriculum save(final Curriculum curriculum) {
		Curriculum result;

		Assert.notNull(curriculum);
		Assert.notNull(curriculum.getPersonalRecord());

		result = this.curriculumRepository.save(curriculum);

		Assert.notNull(result);

		return result;

	}
	public void delete(final Curriculum curriculum) {
		Assert.notNull(curriculum);

		this.curriculumRepository.delete(curriculum);

	}

}
