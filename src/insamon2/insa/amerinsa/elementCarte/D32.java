package insamon2.insa.amerinsa.elementCarte;


import insamon2.insa.amerinsa.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author Guillaume
 *
 */
public class D32 extends Elements{
		
	
	public D32() {
		super("Donjon","Donjon");
		
		
	}
	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.d32);
		return b;
	}


}
