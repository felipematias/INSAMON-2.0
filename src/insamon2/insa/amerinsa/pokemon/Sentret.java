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
public class Sentret extends Pokemon implements PeutEvoluer{

	/**
	 * @param nomPokemon
	 * @param pointsDeVieMax
	 * @param pAttaque
	 * @param pDefense
	 * @param rayonAction
	 * @param nomAttaque
	 * @param nomImage
	 */
	public Sentret() {
		super("Sentret",120, 15, 13,
				"Quick Attack", "sentret");
		this.setLvl(2);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.sentret);
		return b;
	}
	
	public Pokemon evoluer(Pokemon p) {
		if (p.getpExperience()>=3200){
					
				p=new Furret();
				p.setpExperience(this.getpExperience());
			}
			return p;
	}

}