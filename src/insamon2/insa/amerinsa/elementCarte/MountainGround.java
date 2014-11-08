package insamon2.insa.amerinsa.elementCarte;


import insamon2.insa.amerinsa.Personnage;
import insamon2.insa.amerinsa.R;
import insamon2.insa.amerinsa.pokemon.Celebi;
import insamon2.insa.amerinsa.pokemon.Donphan;
import insamon2.insa.amerinsa.pokemon.Eevee;
import insamon2.insa.amerinsa.pokemon.Growlithe;
import insamon2.insa.amerinsa.pokemon.Marill;
import insamon2.insa.amerinsa.pokemon.Onix;
import insamon2.insa.amerinsa.pokemon.Pikachu;
import insamon2.insa.amerinsa.pokemon.Pokemon;
import insamon2.insa.amerinsa.pokemon.Rattata;
import insamon2.insa.amerinsa.pokemon.Scizor;
import insamon2.insa.amerinsa.pokemon.Sentret;
import insamon2.insa.amerinsa.pokemon.Shuckle;
import insamon2.insa.amerinsa.pokemon.Steelix;
import insamon2.insa.amerinsa.pokemon.Suicune;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;

/**
 * @author Guillaume
 *
 */
public class MountainGround extends Elements implements Traversable {
		

	public MountainGround() {
		super("MountainGround","MountainGround");
		
	}
	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.mountain_ground);
		return b;
	}

	public boolean action(Personnage p,Context context) {  
		boolean res=false;
		
		SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
	    boolean donjonWin=preferences.getBoolean("donjonWin", false);
		
	    if(donjonWin==false){
			int random = (int) (Math.random()*20);
			
			if(random<2){
				Pikachu pikachu= new Pikachu();
				setLvl(pikachu);
				pikachu.setPdvEvolution();
				this.setPokemonAttaquant(pikachu);
				res=true;
			}
			else if(random==4){
				Onix onix = new Onix();
				setLvl(onix);
				onix.setPdvEvolution();
				this.setPokemonAttaquant(onix);
				res=true;
			}
			else if(random==6){
				Growlithe growlithe = new Growlithe();
				setLvl(growlithe);
				growlithe.setPdvEvolution();
				this.setPokemonAttaquant(growlithe);
				res=true;
			}
			
			else if(random>18){
				Eevee eevee = new Eevee();
				setLvl(eevee);
				eevee.setPdvEvolution();
				this.setPokemonAttaquant(eevee);
				res=true;
			}
	    }else if(donjonWin==true){
	    	
			int random = (int) (Math.random()*1000);
			System.out.println("donjonWin true");
			if(random<=100){
				Donphan donphan= new Donphan();
				setLvl(donphan);
				donphan.setPdvEvolution();
				this.setPokemonAttaquant(donphan);
				res=true;
			}
			else if(100<random && random<=160){
				Scizor scizor= new Scizor();
				setLvl(scizor);
				scizor.setPdvEvolution();
				this.setPokemonAttaquant(scizor);
				res=true;
			}
			else if(160<random && random<=220){
				Shuckle shuckle= new Shuckle();
				setLvl(shuckle);
				shuckle.setPdvEvolution();
				this.setPokemonAttaquant(shuckle);
				res=true;
			}
			else if(220<random && random<=300){
				Steelix steelix= new Steelix();
				setLvl(steelix);
				steelix.setPdvEvolution();
				this.setPokemonAttaquant(steelix);
				res=true;
			}
			else if(random==998){
				Suicune suicune= new Suicune();
				suicune.setLvl(12);
				suicune.setPdvEvolution();
				this.setPokemonAttaquant(suicune);
				res=true;
			}
			else if(random==999){
				Celebi celebi= new Celebi();
				celebi.setpExperience(100000);
				celebi.setPdvEvolution();
				this.setPokemonAttaquant(celebi);
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
