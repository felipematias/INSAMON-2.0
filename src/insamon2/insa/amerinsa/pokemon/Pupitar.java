package insamon2.insa.amerinsa.pokemon;

import insamon2.insa.amerinsa.PeutEvoluer;
import insamon2.insa.amerinsa.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class Pupitar extends Pokemon implements PeutEvoluer{
	/**
	 * @param nomPokemon
	 * @param pointsDeVieMax
	 * @param pAttaque
	 * @param pDefense
	 * @param rayonAction
	 * @param nomAttaque
	 * @param nomImage
	 */
	public Pupitar() {
		super("Pupitar",600, 25, 60,
				"Earth Power", "pupitar");
	}

	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.pupitar);
		return b;
	}


	public Pokemon evoluer(Pokemon p) {
		if (this.getpExperience()>=7500){

			p=new Tyranitar();
			p.setpExperience(this.getpExperience());
			}
		return p;
		}

}