package insamon2.insa.amerinsa.pokemon;

import insamon2.insa.amerinsa.PeutEvoluer;
import insamon2.insa.amerinsa.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class Phanpy extends Pokemon implements PeutEvoluer{
	/**
	 * @param nomPokemon
	 * @param pointsDeVieMax
	 * @param pAttaque
	 * @param pDefense
	 * @param rayonAction
	 * @param nomAttaque
	 * @param nomImage
	 */
	public Phanpy() {
		super("Phanpy",350, 40, 33,
				"Rollout", "phanpy");
		this.setLvl(5);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.phanpy);
		return b;
	}
	
	public Pokemon evoluer(Pokemon p) {
		if (p.getpExperience()>=5000){
					
				p=new Donphan();
				p.setpExperience(this.getpExperience());
			}
			return p;
	}

}
