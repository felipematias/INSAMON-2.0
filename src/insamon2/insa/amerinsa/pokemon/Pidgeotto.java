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
public class Pidgeotto extends Pokemon implements PeutEvoluer {

	/**
	 * @param nomPokemon
	 * @param pointsDeVieMax
	 * @param pAttaque
	 * @param pDefense
	 * @param rayonAction
	 * @param nomAttaque
	 * @param nomImage
	 */
	public Pidgeotto() {
		super("Pidgeotto", 170, 28, 32,
				"Air Slash", "pidgeotto");
		this.setLvl(4);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see insamon.insa.amerinsa.PeutEvoluer#evoluer(insamon.insa.amerinsa.Personnage)
	 */
	public Pokemon evoluer(Pokemon p) {
		if (this.getpExperience()>=2800){
			
			p=new Pidgeot();
			p.setpExperience(this.getpExperience());
		}
		return p;
	}

	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.pidgeotto);
		return b;
	}
}
