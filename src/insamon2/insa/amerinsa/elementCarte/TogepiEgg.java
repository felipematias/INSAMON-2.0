package insamon2.insa.amerinsa.elementCarte;

import insamon2.insa.amerinsa.Personnage;
import insamon2.insa.amerinsa.R;
import insamon2.insa.amerinsa.pokemon.Togepi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

/**
 * @author Felipe
 *Cet élement sera mis dans le jeu juste quand on vient de finir le donjon, si on resoudre un enigme ou sion sauvegarde 
 *et on retourne apres il disparait et on doit finir le donjon de nouveau.
 */
public class TogepiEgg extends Elements implements Traversable{

	/**
	 * @param type
	 * @param nomImage
	 */
	public TogepiEgg() {
		super("TogepiEgg","TogepiEgg");
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see insamon.insa.amerinsa.elementCarte.Elements#chargerImage(android.content.res.Resources)
	 */
	@Override
	public Bitmap chargerImage(Resources r) {
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.togepi_egg);
		return b;
	}

	public boolean action(Personnage p, Context context) {

			Togepi togepi= new Togepi();
			togepi.setLvl(5);
			togepi.setpExperience(1500);
			this.setPokemonAttaquant(togepi);

			Toast.makeText(context, "Tu as trouvé un oeuf de Togepi (:", Toast.LENGTH_SHORT).show();
			return true;
	}

}
