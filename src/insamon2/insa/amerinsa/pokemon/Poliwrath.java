package insamon2.insa.amerinsa.pokemon;

import insamon2.insa.amerinsa.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Poliwrath extends Pokemon {

	/**
	 * @param nomPokemon
	 * @param pointsDeVieMax
	 * @param pAttaque
	 * @param pDefense
	 * @param rayonAction
	 * @param nomAttaque
	 * @param nomImage
	 */
	public Poliwrath() {
		super("Poliwrath", 750, 90, 100,
				"Circle Throw", "poliwrath");
		// TODO Auto-generated constructor stub
	}
	
	

	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.poliwrath);
		return b;
	}


}