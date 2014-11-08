package insamon2.insa.amerinsa.pokemon;

import insamon2.insa.amerinsa.PeutEvoluer;
import insamon2.insa.amerinsa.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class Teddiursa extends Pokemon implements PeutEvoluer{
	/**
	 * @param nomPokemon
	 * @param pointsDeVieMax
	 * @param pAttaque
	 * @param pDefense
	 * @param rayonAction
	 * @param nomAttaque
	 * @param nomImage
	 */
	public Teddiursa() {
		super("Teddiursa",320, 35, 33,
				"Scratch", "teddiursa");
		this.setLvl(5);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.teddiursa);
		return b;
	}
	
	public Pokemon evoluer(Pokemon p) {
		if (p.getpExperience()>=6200){
					
				p=new Ursaring();
				p.setpExperience(this.getpExperience());
			}
			return p;
	}

}
