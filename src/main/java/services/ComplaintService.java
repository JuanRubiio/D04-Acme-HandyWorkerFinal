
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ComplaintRepository;
import domain.Complaint;

@Service
@Transactional
public class ComplaintService {

	//Managed repo
	@Autowired
	private ComplaintRepository	complaintRepository;


	//Supporting services
	public Complaint create() {
		final Complaint res;
		res = new Complaint();
		res.setMoment(new Date());
		res.setTicker(this.creaTicker(this.creaDosUltimos()));

		return res;
	}
	public Complaint findOne(final Integer complaintId) {
		Complaint res;
		Assert.notNull(complaintId);
		res = this.complaintRepository.findOne(complaintId);
		Assert.notNull(res);

		return res;
	}

	public Collection<Complaint> findAll() {
		Collection<Complaint> res;
		res = this.complaintRepository.findAll();
		Assert.notNull(res);

		return res;
	}

	public Complaint save(final Complaint complaint) {
		Complaint res;
		Assert.notNull(complaint);
		res = this.complaintRepository.save(complaint);
		Assert.notNull(res);

		return res;
	}

	private List<String> creaDosUltimos() {
		final List<String> ultimo = new ArrayList<String>();
		ultimo.add("A");
		ultimo.add("B");
		ultimo.add("C");
		ultimo.add("D");
		ultimo.add("E");
		ultimo.add("F");
		ultimo.add("G");
		ultimo.add("H");
		ultimo.add("I");
		ultimo.add("J");
		ultimo.add("K");
		ultimo.add("L");
		ultimo.add("M");
		ultimo.add("N");
		ultimo.add("O");
		ultimo.add("P");
		ultimo.add("Q");
		ultimo.add("R");
		ultimo.add("S");
		ultimo.add("T");
		ultimo.add("U");
		ultimo.add("V");
		ultimo.add("W");
		ultimo.add("X");
		ultimo.add("Y");
		ultimo.add("Z");

		final List<String> penultimo = new ArrayList<String>();
		penultimo.addAll(ultimo);

		final List<String> ultimosDos = new ArrayList<String>();

		int i = 0;
		int j = 0;

		while (i <= penultimo.size() - 1) {
			final String primero = penultimo.get(i);
			while (j <= ultimo.size() - 1) {
				final String segundo = ultimo.get(j);
				final String resultante = primero + segundo;
				ultimosDos.add(resultante);
				j++;
			}
			i++;
		}
		return ultimosDos;
	}

	private String creaTicker(final List<String> ultimosDos) {
		final Date dateOfNow = new Date();
		final int year = dateOfNow.getYear();
		final int month = dateOfNow.getMonth();
		final int day = dateOfNow.getDay();

		final Integer aleatorio = (int) Math.floor(Math.random() * (728));
		final String ultimas = ultimosDos.get(aleatorio);
		ultimosDos.remove(aleatorio);

		final Integer hour = dateOfNow.getHours();
		String primeraLetra;

		switch (hour) {
		case 00:
			primeraLetra = "A";
			break;
		case 01:
			primeraLetra = "B";
			break;
		case 02:
			primeraLetra = "C";
			break;
		case 03:
			primeraLetra = "D";
			break;
		case 04:
			primeraLetra = "E";
			break;
		case 05:
			primeraLetra = "F";
			break;
		case 06:
			primeraLetra = "G";
			break;
		case 07:
			primeraLetra = "H";
			break;

		/*
		 * case 08:
		 * primeraLetra = "I";
		 * break;
		 * case 09:
		 * primeraLetra = "J";
		 * break;
		 */

		case 10:
			primeraLetra = "K";
			break;
		case 11:
			primeraLetra = "L";
			break;
		case 12:
			primeraLetra = "M";
			break;
		case 13:
			primeraLetra = "N";
			break;
		case 14:
			primeraLetra = "O";
			break;
		case 15:
			primeraLetra = "P";
			break;
		case 16:
			primeraLetra = "Q";
			break;
		case 17:
			primeraLetra = "R";
			break;
		case 18:
			primeraLetra = "S";
			break;
		case 19:
			primeraLetra = "T";
			break;
		case 20:
			primeraLetra = "U";
			break;
		case 21:
			primeraLetra = "V";
			break;
		case 22:
			primeraLetra = "W";
			break;
		case 23:
			primeraLetra = "X";
			break;
		default:
			primeraLetra = "Z";
		}
		final String[] abecedario = {
			"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
		};
		final int enteroAleatorio = (int) Math.floor(Math.random() * (27));
		final String ticker = year + month + day + "-" + primeraLetra + abecedario[enteroAleatorio] + ultimas;

		return ticker;

	}

}
