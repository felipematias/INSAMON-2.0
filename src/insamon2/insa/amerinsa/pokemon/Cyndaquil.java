package insamon2.insa.amerinsa.pokemon;

import android.content.res.Resources;
import android.graphics.Bitmap;

import insamon2.insa.amerinsa.PeutEvoluer;
import insamon2.insa.amerinsa.PokemonInit;
import insamon2.insa.amerinsa.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author Felipe
 *Cyndaquil est un pokemon initial de la nouvelle geration
 */
public class Cyndaquil extends Pokemon implements PokemonInit, PeutEvoluer {

	/**
	 * @param nomPokemon
	 * @param pointsDeVieMax
	 * @param pAttaque
	 * @param pDefense
	 * @param rayonAction
	 * @param nomAttaque
	 * @param nomImage
	 */
	public Cyndaquil() {
		super("Cyndaquil", 250, 30, 20,
				"Ember", "cyndaquil");
		// TODO Auto-generated constructor stub
	}
	
	
	public Pokemon evoluer(Pokemon p){
		if (p.getpExperience()>=2000){
			
			p=new Quilava();
			p.setpExperience(this.getpExperience());
		}
		return p;
	}
	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.cyndaquil);
		return b;
	}


}
