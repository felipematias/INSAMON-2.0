package insamon2.insa.amerinsa.pokemon;

import insamon2.insa.amerinsa.PeutEvoluer;
import insamon2.insa.amerinsa.PokemonInit;
import insamon2.insa.amerinsa.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author Felipe
 *Chikorita est un pokemon initial de la nouvelle geration
 */
public class Chikorita extends Pokemon implements PokemonInit, PeutEvoluer {

	/**
	 * @param nomPokemon
	 * @param pointsDeVieMax
	 * @param pAttaque
	 * @param pDefense
	 * @param rayonAction
	 * @param nomAttaque
	 * @param nomImage
	 */
	public Chikorita() {
		super("Chikorita",260, 27, 21,
				"Leaf Storm", "chikorita");
		// TODO Auto-generated constructor stub
	}
	
	
	public Pokemon evoluer(Pokemon p){
		if (p.getpExperience()>=2000){
			
			p=new Bayleef();
			p.setpExperience(this.getpExperience());
		}
		return p;
	}
	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.chikorita);
		return b;
	}


}
