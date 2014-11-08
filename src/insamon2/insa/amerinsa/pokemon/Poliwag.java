package insamon2.insa.amerinsa.pokemon;

import insamon2.insa.amerinsa.PeutEvoluer;
import insamon2.insa.amerinsa.PokemonInit;
import insamon2.insa.amerinsa.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author Felipe
 */
public class Poliwag extends Pokemon implements PeutEvoluer {

	/**
	 * @param nomPokemon
	 * @param pointsDeVieMax
	 * @param pAttaque
	 * @param pDefense
	 * @param rayonAction
	 * @param nomAttaque
	 * @param nomImage
	 */
	public Poliwag() {
		super("Poliwag", 260, 24, 18,
				"Water Gun", "poliwag");
		// TODO Auto-generated constructor stub
	}
	
	
	public Pokemon evoluer(Pokemon p){
		if (p.getpExperience()>=2200){
			
			p=new Poliwhirl();
			p.setpExperience(this.getpExperience());
		}
		return p;
	}
	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.poliwag);
		return b;
	}


}