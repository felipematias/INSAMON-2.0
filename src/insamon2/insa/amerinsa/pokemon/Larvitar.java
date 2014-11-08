package insamon2.insa.amerinsa.pokemon;

import insamon2.insa.amerinsa.PeutEvoluer;
import insamon2.insa.amerinsa.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class Larvitar extends Pokemon implements PeutEvoluer{
	/**
	 * @param nomPokemon
	 * @param pointsDeVieMax
	 * @param pAttaque
	 * @param pDefense
	 * @param rayonAction
	 * @param nomAttaque
	 * @param nomImage
	 */
	public Larvitar() {
		super("Larvitar",360, 32, 30,
				"Screech", "larvitar");
		this.setLvl(5);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.larvitar);
		return b;
	}


	public Pokemon evoluer(Pokemon p) {
		if (this.getpExperience()>=5000){

			p=new Pupitar();
			p.setpExperience(this.getpExperience());
			}
		return p;
		}

}