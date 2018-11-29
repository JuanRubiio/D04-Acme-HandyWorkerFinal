package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.UtilitiesRepository;
import domain.Message;
import domain.Spam;

@Service
@Transactional
public class UtilitiesService {

	@Autowired
	private UtilitiesRepository	utilitiesRepository;

	@Autowired
	private SpamService			spamService;


	public String generateTicker() {

		final ArrayList<String> ticker = new ArrayList<>();
		ticker.addAll(this.utilitiesRepository.obtenerTickerComplaint());
		ticker.addAll(this.utilitiesRepository.obtenerTickerCurriculum());
		ticker.addAll(this.utilitiesRepository.obtenerTickerFixUpTask());

		String res = "";
		final Random rdn = new Random();
		final String[] a = {
			"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
		};
		final String letra1 = a[rdn.nextInt(a.length - 1)];
		final String letra2 = a[rdn.nextInt(a.length - 1)];
		final String letra3 = a[rdn.nextInt(a.length - 1)];
		final String letra4 = a[rdn.nextInt(a.length - 1)];
		final String division = "-";
		final String dia = Integer.toString(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		String mes = Integer.toString(Calendar.getInstance().get(Calendar.MONTH) + 1);
		if (mes.length() == 1)
			mes = "0" + mes;
		final String annio = Integer.toString(Calendar.getInstance().get(Calendar.YEAR)).substring(2);

		res = annio + mes + dia + division + letra1 + letra2 + letra3 + letra4;
		while (ticker.contains(res)) {
			final Random rdn2 = new Random();
			final String[] a2 = {
				"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
			};
			final String letra12 = a[rdn.nextInt(a.length - 1)];
			final String letra22 = a[rdn.nextInt(a.length - 1)];
			final String letra32 = a[rdn.nextInt(a.length - 1)];
			final String letra42 = a[rdn.nextInt(a.length - 1)];
			final String division2 = "-";
			final String dia2 = Integer.toString(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
			String mes2 = Integer.toString(Calendar.getInstance().get(Calendar.MONTH) + 1);
			if (mes2.length() == 1)
				mes2 = "0" + mes2;
			final String annio2 = Integer.toString(Calendar.getInstance().get(Calendar.YEAR)).substring(2);

			res = annio2 + mes2 + dia2 + division2 + letra12 + letra22 + letra32 + letra42;

		}

		return res;

	}
	public Boolean checkSpam(final String s) {
		Boolean res = false;

		Collection<Spam> spamList;
		final Message mes = new Message();

		spamList = this.spamService.findAll();

		for (final Spam sp : spamList)
			if (!"".equals(s) && s != null)
				if (s.toLowerCase().contains(sp.getSpamWords().toLowerCase())) {
					res = true;
					break;
				}
		return res;

	}
}