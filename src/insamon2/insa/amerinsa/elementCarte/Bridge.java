package insamon2.insa.amerinsa.elementCarte;


import insamon2.insa.amerinsa.Personnage;
import insamon2.insa.amerinsa.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author Guillaume
 *
 */
public class Bridge extends Elements implements Traversable {
		

	public Bridge() {
		super("Bridge","Bridge");
		
	}
	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.bridge);
		return b;
	}

	public boolean action(Personnage p, Context context) { 
		
		return false;
	}




}
