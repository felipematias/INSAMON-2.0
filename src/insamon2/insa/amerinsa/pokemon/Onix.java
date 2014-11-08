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
public class Onix extends Pokemon implements PeutEvoluer{

	/**
	 * @param nomPokemon
	 * @param pointsDeVieMax
	 * @param pAttaque
	 * @param pDefense
	 * @param rayonAction
	 * @param nomAttaque
	 * @param nomImage
	 */
	public Onix() {
		super("Onix", 250, 20, 40,
				"Rock Tomb", "onix");
		this.setLvl(4);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.onix);
		return b;
	}


	public Pokemon evoluer(Pokemon p){
		if (p.getpExperience()>=7500){
			
			p=new Steelix();
			p.setpExperience(this.getpExperience());
		}
		return p;
	}
}
