/**
 * 
 */
package insamon2.insa.amerinsa.pokemon;

import insamon2.insa.amerinsa.PeutEvoluer;
import insamon2.insa.amerinsa.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author Felipe
 *
 */
public class Haunter extends Pokemon implements PeutEvoluer {

	/**
	 * @param nomPokemon
	 * @param pointsDeVieMax
	 * @param pAttaque
	 * @param pDefense
	 * @param rayonAction
	 * @param nomAttaque
	 * @param nomImage
	 */
	public Haunter() {
		super("Haunter", 140, 26, 24,
				"Terroriser", "haunter");
		this.setLvl(3);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.haunter);
		return b;
	}

	public Pokemon evoluer(Pokemon p) {
		if (p.getpExperience()>=3500){
			
			p=new Gengar();
			p.setpExperience(this.getpExperience());
		}
		return p;
	}

}
