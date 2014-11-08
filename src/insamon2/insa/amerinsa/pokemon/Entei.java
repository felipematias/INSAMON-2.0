package insamon2.insa.amerinsa.pokemon;

import insamon2.insa.amerinsa.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author Felipe
 *
 */
public class Entei extends Pokemon {

	/**
	 * @param nomPokemon
	 * @param pointsDeVieMax
	 * @param pAttaque
	 * @param pDefense
	 * @param nomAttaque
	 * @param nomImage
	 */
	public Entei() {
		super("Entei", 755, 86, 60, "Flamethrower",
				"entei");
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see insamon.insa.amerinsa.pokemon.Pokemon#chargerImage(android.content.res.Resources)
	 */
	@Override
	public Bitmap chargerImage(Resources r) {
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.entei);
		return b;
	}
}
