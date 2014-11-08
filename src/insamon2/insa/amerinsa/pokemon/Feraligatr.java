package insamon2.insa.amerinsa.pokemon;

import insamon2.insa.amerinsa.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author Felipe
 */
public class Feraligatr extends Pokemon{

	/**
	 * @param nomPokemon
	 * @param pointsDeVieMax
	 * @param pAttaque
	 * @param pDefense
	 * @param rayonAction
	 * @param nomAttaque
	 * @param nomImage
	 */
	public Feraligatr() {
		super("Feraligatr",800, 105, 105,
				"Hydro Pump", "feraligatr");
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.feraligatr);
		return b;
	}


}