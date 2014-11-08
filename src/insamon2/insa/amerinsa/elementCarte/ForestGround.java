package insamon2.insa.amerinsa.elementCarte;


import insamon2.insa.amerinsa.Personnage;
import insamon2.insa.amerinsa.R;
import insamon2.insa.amerinsa.pokemon.Butterfree;
import insamon2.insa.amerinsa.pokemon.Entei;
import insamon2.insa.amerinsa.pokemon.Haunter;
import insamon2.insa.amerinsa.pokemon.Larvitar;
import insamon2.insa.amerinsa.pokemon.Ledyba;
import insamon2.insa.amerinsa.pokemon.Marill;
import insamon2.insa.amerinsa.pokemon.Metapod;
import insamon2.insa.amerinsa.pokemon.Phanpy;
import insamon2.insa.amerinsa.pokemon.Pokemon;
import insamon2.insa.amerinsa.pokemon.Raikou;
import insamon2.insa.amerinsa.pokemon.Sentret;
import insamon2.insa.amerinsa.pokemon.Teddiursa;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;

/**
 * @author Guillaume Felipe
 *
 */
public class ForestGround extends Elements implements Traversable {
		

	public ForestGround() {
		super("ForestGround","ForestGround");
		
	}
	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.forest_ground);
		return b;
	}

	public boolean action(Personnage p, Context context) {
		boolean res = false;

		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		boolean donjonWin = preferences.getBoolean("donjonWin", false);

		if (donjonWin == false) {
			int random = (int) (Math.random() * 20);

			if (random < 2) {
				Haunter haunter = new Haunter();
				setLvl(haunter);
				haunter.setPdvEvolution();
				this.setPokemonAttaquant(haunter);
				res = true;
			} else if (random > 18) {
				Butterfree butterfree = new Butterfree();
				setLvl(butterfree);
				butterfree.setPdvEvolution();
				this.setPokemonAttaquant(butterfree);
				res = true;
			} else if (random == 5) {
				Metapod metapod = new Metapod();
				setLvl(metapod);
				metapod.setPdvEvolution();
				this.setPokemonAttaquant(metapod);
				res = true;
			}

		} else if (donjonWin == true) {
			int random = (int) (Math.random() * 1000);
			System.out.println("donjonWin true");
			if (random <= 100) {
				Larvitar larvitar = new Larvitar();
				setLvl(larvitar);
				larvitar.setPdvEvolution();
				this.setPokemonAttaquant(larvitar);
				res = true;
			} else if (100 < random && random <= 160) {
				Teddiursa teddiursa = new Teddiursa();
				setLvl(teddiursa);
				teddiursa.setPdvEvolution();
				this.setPokemonAttaquant(teddiursa);
				res = true;
			} else if (160 < random && random <= 220) {
				Phanpy phanpy = new Phanpy();
				setLvl(phanpy);
				phanpy.setPdvEvolution();
				this.setPokemonAttaquant(phanpy);
				res = true;
			} else if (random == 998) {
				Raikou raikou = new Raikou();
				raikou.setLvl(12);
				raikou.setPdvEvolution();
				this.setPokemonAttaquant(raikou);
				res = true;
			}
		}

		return res;
	}

	/**
	 * Cette méthode sert a mettre des niveaux aletoires dans les pokemons
	 * @param p
	 */
	public void setLvl(Pokemon p){
		int random = (int) (Math.random()*30);
		
		
		if(random<=10){
			p.setLvl(5);
		}
		else if(random>10 && random<=20){
			p.setLvl(6);
		}
		else if(random>20 && random<=30){
			p.setLvl(7);
		}
	}


}
