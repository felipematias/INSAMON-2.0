package insamon2.insa.amerinsa.pokemon;

import insamon2.insa.amerinsa.PeutEvoluer;
import insamon2.insa.amerinsa.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class Ledyba extends Pokemon implements PeutEvoluer{
	/**
	 * @param nomPokemon
	 * @param pointsDeVieMax
	 * @param pAttaque
	 * @param pDefense
	 * @param rayonAction
	 * @param nomAttaque
	 * @param nomImage
	 */
	public Ledyba() {
		super("Ledyba",200, 29, 33,
				"Comet Punch", "ledyba");
		this.setLvl(3);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.ledyba);
		return b;
	}
	
	public Pokemon evoluer(Pokemon p) {
		if (this.getpExperience()>=3200){

			p=new Ledian();
			p.setpExperience(this.getpExperience());
			}
		return p;
		}

}