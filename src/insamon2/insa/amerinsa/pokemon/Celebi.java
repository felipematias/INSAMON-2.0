package insamon2.insa.amerinsa.pokemon;

import insamon2.insa.amerinsa.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Celebi extends Pokemon {

	/**
	 * @param nomPokemon
	 * @param pointsDeVieMax
	 * @param pAttaque
	 * @param pDefense
	 * @param rayonAction
	 * @param nomAttaque
	 * @param nomImage
	 */
	public Celebi() {
		super("Celebi", 900, 150, 130,
				"Leaf Storm", "celebi" );
		// TODO Auto-generated constructor stub
	}

	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.celebi);
		return b;
	}
}
