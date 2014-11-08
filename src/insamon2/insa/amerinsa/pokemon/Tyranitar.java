package insamon2.insa.amerinsa.pokemon;

import insamon2.insa.amerinsa.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class Tyranitar extends Pokemon{
	/**
	 * @param nomPokemon
	 * @param pointsDeVieMax
	 * @param pAttaque
	 * @param pDefense
	 * @param rayonAction
	 * @param nomAttaque
	 * @param nomImage
	 */
	public Tyranitar() {
		super("Tyranitar",650,120,80,
				"Giga Impact", "tyranitar");
	}

	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.tyranitar);
		return b;
	}

}