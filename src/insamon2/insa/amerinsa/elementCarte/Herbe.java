/**
 * 
 */
package insamon2.insa.amerinsa.elementCarte;


import insamon2.insa.amerinsa.Personnage;
import insamon2.insa.amerinsa.R;
import insamon2.insa.amerinsa.pokemon.Articuno;
import insamon2.insa.amerinsa.pokemon.Caterpie;
import insamon2.insa.amerinsa.pokemon.Entei;
import insamon2.insa.amerinsa.pokemon.Jigglypuff;
import insamon2.insa.amerinsa.pokemon.Ledyba;
import insamon2.insa.amerinsa.pokemon.Marill;
import insamon2.insa.amerinsa.pokemon.Metapod;
import insamon2.insa.amerinsa.pokemon.Mewtwo;
import insamon2.insa.amerinsa.pokemon.Pidgey;
import insamon2.insa.amerinsa.pokemon.Pokemon;
import insamon2.insa.amerinsa.pokemon.Rattata;
import insamon2.insa.amerinsa.pokemon.Sentret;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;

/**
 * @author Felipe
 *
 */
public class Herbe extends Elements implements Traversable {
		
	private boolean donjonWin=false;
	
	
	public Herbe() {
		super("Herbe","Herbe");
		
	}
	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.herbe);
		return b;
	}

	public boolean action(Personnage p, Context context) {
		boolean res=false;
		
		SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
	    donjonWin=preferences.getBoolean("donjonWin", false);
		
	    if(donjonWin==false){
			int random = (int) (Math.random()*20);
			System.out.println("donjonWin false");
			if(random==17){
				System.out.println("random_rattata");
				Rattata rattata= new Rattata();
				setLvl(rattata);
				rattata.setPdvEvolution();
				this.setPokemonAttaquant(rattata);
				res=true;
			}
			else if(random==4){
				System.out.println("random_rondoudou");
				Jigglypuff jigglypuff= new Jigglypuff();
				setLvl(jigglypuff);
				jigglypuff.setPdvEvolution();
				this.setPokemonAttaquant(jigglypuff);
				res=true;
			}
			else if(random==5){
				System.out.println("random_caterpie");
				Caterpie caterpie= new Caterpie();
				setLvl(caterpie);
				caterpie.setPdvEvolution();
				this.setPokemonAttaquant(caterpie);
				res=true;
			}
			else if(random==3){
				System.out.println("random_roucoul");
				Pidgey pidgey= new Pidgey();
				setLvl(pidgey);
				pidgey.setPdvEvolution();
				this.setPokemonAttaquant(pidgey);
				res=true;
			}
	    }else if(donjonWin==true){
			int random = (int) (Math.random()*1000);
			System.out.println("donjonWin true");
			if(random<=100){
				Ledyba ledyba= new Ledyba();
				setLvl(ledyba);
				ledyba.setPdvEvolution();
				this.setPokemonAttaquant(ledyba);
				res=true;
			}
			else if(100<random && random<=160){
				Sentret sentret= new Sentret();
				setLvl(sentret);
				sentret.setPdvEvolution();
				this.setPokemonAttaquant(sentret);
				res=true;
			}
			else if(160<random && random<=220){
				Marill marill= new Marill();
				setLvl(marill);
				marill.setPdvEvolution();
				this.setPokemonAttaquant(marill);
				res=true;
			}
			else if(220<random && random<=300){
				Rattata rattata= new Rattata();
				setLvl(rattata);
				rattata.setPdvEvolution();
				this.setPokemonAttaquant(rattata);
				res=true;
			}
			else if(random==998){
				Entei entei= new Entei();
				entei.setLvl(12);
				entei.setPdvEvolution();
				this.setPokemonAttaquant(entei);
				res=true;
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
			p.setLvl(2);
		}
		else if(random>10 && random<=20){
			p.setLvl(3);
		}
		else if(random>20 && random<=30){
			p.setLvl(4);
		}
	}
	
}
