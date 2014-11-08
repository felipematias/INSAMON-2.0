/**
 * 
 */
package insamon2.insa.amerinsa.elementCarte;

import android.content.Context;
import insamon2.insa.amerinsa.Personnage;


public interface Traversable {

	// cas généraux mais pas exploité partout
	boolean action(Personnage personnage, Context context);

	
}
