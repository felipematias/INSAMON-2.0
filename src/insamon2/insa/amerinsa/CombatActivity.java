
package insamon2.insa.amerinsa;

import insamon2.insa.amerinsa.pokemon.Azumarill;
import insamon2.insa.amerinsa.pokemon.Donphan;
import insamon2.insa.amerinsa.pokemon.Pidgey;
import insamon2.insa.amerinsa.pokemon.Pokemon;
import insamon2.insa.amerinsa.pokemon.Poliwrath;
import insamon2.insa.amerinsa.pokemon.Steelix;
import insamon2.insa.amerinsa.pokemon.Tyranitar;
import insamon2.insa.amerinsa.pokemon.Ursaring;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author Felipe
 *Cette activity sera responsable pour l'ecran du combat
 */

public class CombatActivity extends Activity {
	
	/*
	 * attributs.
	 */
	private CombatView combatView;
	private int x;
	private int y;
	private int compteur;
	private ArrayList<Integer> coord;
	private String direction;
	private Pokemon pokemonAttaquant;
	private Pokemon pokemonDresseur;
	private Enigme enigme;
	private ProgressBar viePokeDresseur;
	private ProgressBar viePokeAttaquant;
	private TextView stringPok;
	private TextView nomPokeDresseur;
	private TextView nomPokeAttaquant;
	private TextView textVieDresseur;
	private TextView textVieAttaquant;
	private TextView lvlPokemonAttaquant;
    private Intent myintent2;
    private ArrayList<Pokemon> listPok =new ArrayList <Pokemon>(6);
    
    
    
    
    
	@Override
	/**
	 * main de la classe.
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);   
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, //pour faire l'ecran fullscreen
	                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    
	    setContentView(R.layout.activity_combat);
	    combatView=(CombatView)findViewById(R.id.combatView1);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		// récupere les attributs de l'activité précédente et les stocke dans les attributs.
		recupererIntent();
		
		
		
		// récupere les boutons pour traiter les listeners après
		Button attaqueA=(Button)findViewById(R.id.button1);
		attaqueA.setText(pokemonDresseur.getNomAttaque());
		Button attaqueB=(Button)findViewById(R.id.button2);
		attaqueB.setText("Attaque Aléatoire");
		Button fuire=(Button)findViewById(R.id.button3);
		ImageButton pokeball=(ImageButton)findViewById(R.id.pokeball);
		stringPok = (TextView)findViewById(R.id.textToString);
		// texte d'arrivée dans le combat
		stringPok.setText(""+pokemonAttaquant.getNomPokemon() + " vous défie pour un combat, quelle attaque voulez vous exécuter? "); // phrase du d�but
		
		
		// pour les barres de vie qui vont représenter la vie des pokemon.
		viePokeDresseur= (ProgressBar)findViewById(R.id.viePokeDress);
		viePokeAttaquant= (ProgressBar)findViewById(R.id.viePokeAtt);
		nomPokeDresseur= (TextView)findViewById(R.id.textView1);
		nomPokeAttaquant= (TextView)findViewById(R.id.textView2);
		textVieDresseur= (TextView)findViewById(R.id.textVieDresseur);
		textVieAttaquant= (TextView)findViewById(R.id.textVieAttaquant);
		lvlPokemonAttaquant= (TextView)findViewById(R.id.lvlPokAttaq);

		//initialisation du nom des Pokemons et des point de vie en text
		nomPokeDresseur.setText(pokemonDresseur.getNomPokemon());
		nomPokeAttaquant.setText(pokemonAttaquant.getNomPokemon());
		textVieDresseur.setText(""+pokemonDresseur.getPdv()+"/"+""+pokemonDresseur.getPointsDeVieMax());
		textVieAttaquant.setText(""+pokemonAttaquant.getPdv()+"/"+""+pokemonAttaquant.getPointsDeVieMax());
		
		lvlPokemonAttaquant.setText("Nv."+pokemonAttaquant.getLvl());
		
		//initialisation des barres de vie
		viePokeDresseur.setMax(pokemonDresseur.getPointsDeVieMax());
		viePokeDresseur.setProgress(pokemonDresseur.getPdv());
		viePokeAttaquant.setMax(pokemonAttaquant.getPointsDeVieMax());
		viePokeAttaquant.setProgress(pokemonAttaquant.getPdv());
		
		afficherViePoke();  
		combatView.invalidate(); // mise à jour des barres de vie, etiquettes, etc.
		
	
		
		/*
		 * début des écouteurs
		 */
		
		 attaqueA.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	
//>	            	// personne n'est mort, le combat peur se dérouler
	            	if(pokemonDresseur.getPdv()!= 0 || pokemonAttaquant.getPdv()!= 0){
		            	attaquer(); // appelle la méthode que j'ai crée ou chaque pokemon fait une attaque classique sur l'autre.
		            	afficherViePoke(); // actualise les progress bar
		            	boiteText(pokemonDresseur, pokemonAttaquant);	
	            	}
	            }
	        });
		 
		 attaqueB.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
//>	            	// personne n'est mort, le combat peur se dérouler	            	
	            	if(pokemonDresseur.getPdv()!= 0 || pokemonAttaquant.getPdv()!= 0){
		            	attaquerAleatoirement(); // appelle la méthode que j'ai crée ou chaque pokemon fait une attaque classique sur l'autre.
		            	afficherViePoke(); // actualise les progress bar   	
	            	}
	            }
	        });
		 
		 fuire.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
//>	            	// on est pas dans le donjon, fuite aléatoire (faible taux d'échec)
	            	if(!pokemonDresseur.isDonjonEntre()){
	            		int random=(int)(Math.random()*10);
	            		if (random>5 || pokemonDresseur.getLvl()>=pokemonAttaquant.getLvl()){ // si on est dans on niveau plus eleve on peut fuire!
	            			Intent myintent2 = new Intent(CombatActivity.this,MapActivity.class);  // si non il y a la possibilité de ne pas pouvoir fuire
			            	remplirIntent(myintent2);
			            	startActivity(myintent2);
		                	Toast.makeText(getApplicationContext(), "Tu t'es enfui!", Toast.LENGTH_SHORT).show();
		            	} else{
		            		Toast.makeText(getApplicationContext(), "Ce pokemon est trop rapide pour vous, la fuite est une mauvaise option", Toast.LENGTH_SHORT).show();}
	            			pokemonAttaquant.attaqueClassique(pokemonDresseur);
//>					// on est dans le donjon, fuite toujours impossible
	            		}else{
	                	Toast.makeText(getApplicationContext(), "Un mur s'est refermé derrière vous à votre entrée. Vous êtes pris au piège!!", Toast.LENGTH_SHORT).show();	
	            	}
	            }
	        });
		 pokeball.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	pokemonCapture();
	            }		
	        });
	}

	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.combat, menu);
		
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    int id=item.getItemId();
	    if(id==R.id.pokedex){
	    	Intent myintent2 = new Intent(CombatActivity.this,MesPokemonsListe.class);
	    	remplirIntent(myintent2);
	    	myintent2.putExtra("pokAtt", pokemonAttaquant);
	    	myintent2.putExtra("combatEntre", true);
        	if(pokemonDresseur.isDonjonEntre()){
	        	myintent2.putExtra("compteur", compteur);
	            direction="aucune";
	            myintent2.putExtra("dansDonjon",true);
	            myintent2.putExtra("direction",direction);
	            
        	}
	    	startActivity(myintent2);
	    	Toast.makeText(this, "Sélectionnez un autre Pokémon pour engager le combat!", Toast.LENGTH_SHORT).show();
            return true;
	    }
	    return true;
	}
	
	/**
	 * afficher le texte de l'attaque
	 * @param pokemonDresseur pokemon du dresseur
	 * @param pokemonAttaquant pokemon de l'attaquant
	 */
	 public void boiteText(Pokemon pokemonDresseur, Pokemon pokemonAttaquant){
		 Resources res = getResources();
			String chaine = res.getString(R.string.toStringCombat, pokemonDresseur.getNomPokemon(), pokemonAttaquant.getNomPokemon(),
					pokemonDresseur.getNomAttaque());
			TextView stringPok = (TextView)findViewById(R.id.textToString);
			stringPok.setText(chaine+"\n"+"Attention une contre attaque t'enlève des points de vie !!");
	 }
	
	 
	 /**
	  * permet de recuperer les attributs présents dans l'intent et d'initialiser les attributs de cette activity
	  */
	public void recupererIntent(){
		Intent i = getIntent();
		pokemonAttaquant= (Pokemon)i.getSerializableExtra("pokAtt");
		pokemonDresseur= (Pokemon)i.getSerializableExtra("pokeDresseur");
		x=(Integer) i.getSerializableExtra("x");
		y=(Integer) i.getSerializableExtra("y");
		combatView.setPokemonAttaquant(pokemonAttaquant);
		combatView.setPokemonDresseur(pokemonDresseur);
		enigme=(Enigme)i.getSerializableExtra("enigme");
		coord=(ArrayList<Integer>)i.getSerializableExtra("coord");
		listPok=(ArrayList<Pokemon>)i.getSerializableExtra("listPok");

		// si on est dans le donjon, il faut ajouter d'autres variables
		if(pokemonDresseur.isDonjonEntre()){
			compteur=(Integer)i.getSerializableExtra("compteur"); 
			direction=(String)i.getSerializableExtra("direction");
			System.out.println(""+compteur);
		}
	}

	
	
	
	@Override

	/**
	 * action performed du bouton back
	 */

	public void onBackPressed() {
		//super.onBackPressed();
	    AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    builder.setMessage("Tu veux vraiment sortir?")
	            .setCancelable(false)
	            .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	                	final Intent intent = new Intent(getBaseContext(), InsamonActivity.class);
	                	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                	startActivity(intent);

	                }
	            })
	            .setNegativeButton("Non", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	                    dialog.cancel();
	                }
	            });
	    AlertDialog alert = builder.create();
	    alert.show();
	}
	
	

	
	/**
	 * met à jour l'affichage des éléments graphiques relatifs à la vie des pokemons.
	 */
	public void afficherViePoke(){
		viePokeDresseur.setProgress(pokemonDresseur.getPdv());
		viePokeAttaquant.setProgress(pokemonAttaquant.getPdv());
		textVieDresseur.setText(""+pokemonDresseur.getPdv()+"/"+""+pokemonDresseur.getPointsDeVieMax());
		textVieAttaquant.setText(""+pokemonAttaquant.getPdv()+"/"+""+pokemonAttaquant.getPointsDeVieMax());
		//System.out.println("barres de vie actualisées à "+pokemonDresseur.getPdv());
		combatView.invalidate();
	}
	
	
	

	/**
	 * attaque normale (standard)
	 */
	public void attaquer (){
		pokemonDresseur.attaqueClassique(pokemonAttaquant);
		//System.out.println(""+pokemonDresseur.getpExperience());

		if(pokemonDresseur.getPdv()!= 0 || pokemonAttaquant.getPdv()!= 0){
			pokemonAttaquant.attaqueClassique(pokemonDresseur);
			
			System.out.println("pdv pokeDresseur : "+ pokemonDresseur.getPdv()+", pdv pokeAttaquant : "+ pokemonAttaquant.getPdv());
		}
			
		mortPokemonAttaquant();
		mortPokemonDresseur();
	}
	
	
	
	/**
	 * attaque ayant des dégats aléatoires
	 */
	public void attaquerAleatoirement (){
		int vieAvant=pokemonAttaquant.getPdv();
		
		stringPok.setText("Tu as reussi l'attaque aléatoire!"+"\n"+
		"Attention une contre attaque t'enlève des points de vie !!");
		
        pokemonDresseur.attaqueAleatoire(pokemonAttaquant);

        
        attendre(300);// on met une tempo
        afficherViePoke(); // actualise tout
        
        if(pokemonDresseur.getPdv()!= 0 || pokemonAttaquant.getPdv()!= 0){
            pokemonAttaquant.attaqueClassique(pokemonDresseur);
            System.out.println("Attaque aléatoire:pdv pokeDresseur : "+ pokemonDresseur.getPdv()+", pdv pokeAttaquant : "+ pokemonAttaquant.getPdv());
        } 
        mortPokemonAttaquant();
        mortPokemonDresseur();
        // résultats de l'attaque aléatoire.
        if(vieAvant==pokemonAttaquant.getPdv()){	
        	stringPok.setText("Tu n'as pas reussi l'attaque aléatoire!"+"\n"+
        	"Attention une contre attaque t'enlève des points de vie !!");
    	}else{	
    		stringPok.setText("Tu as reussi l'attaque aléatoire!"+"\n"+
            "Attention une contre attaque t'enlève des points de vie !!");
    	}
    }
	
	
	/**
	 * transférer les attributs à l'intent pour les transmettre à la prochaine activité
	 * @param myintent2 l'intent dans laquelle on va.
	 */
	public void remplirIntent(Intent myintent2){
    	myintent2.putExtra("x",x);
    	myintent2.putExtra("y",y);
    	//renvoie le pokemon du dresseur dans la map, meme s'il s'est fait blessé.
    	myintent2.putExtra("pokeDresseur", pokemonDresseur);
    	enigme.setSolved(false);
    	myintent2.putExtra("enigme",enigme); // obligatoire sinon nullPointerException lors du passage � la map
    	myintent2.putExtra("coord", coord);
    	myintent2.putExtra("listPok",listPok);
	}
	
	/**
	 * permet d'interrompre le processus
	 * @param temps temps d'interruption en ms
	 */
	public void attendre (int temps){
		try {
			Thread.sleep(temps);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/**
	 * En fonction de l'étape du donjon, on impose la prochaine énigme
	 * @param myintent2
	 */
	public void chargerEnigmeDonjon(Intent myintent2){
		if(compteur==2){enigme=new Enigme("Vous arrivez au Donjon! Ici c'est la dernière étape du jeu et vos adversaires sont partout! Il y a même des familles entières! La famille Dubois occupe ce passage vers l'entrée du Donjon. Il vous faut donc répondre au challenge lancé par Yvan Dubois: 'J'ai deux fois plus de frères que de soeurs. Anne Dubois, sa soeur dit: 'J'ai cinq fois plus de frères que de soeurs.' Combien y a-t-il d'enfants dans cette famille ?","10","8","5","7");}
		else if(compteur==4){enigme=new Enigme("Cher joueur, votre parcours arrive ver la fin, mais les obstacles sont toujours présents ! Vous devez envoyer un pokemon pour vous représenter à l'arène de combat, mais pour cela il doit traverser le lac magique. Les pokemons ne savent malheureusement pas nager, par contre vous disposez d'un bloc cubique sur lequel il pourra flotter! Sachant que ce bloc, de volume total égal à 0,4m3, est  immergé à 90% dans le fluide, déterminez sa densité.Donnée : la densité du lac magique est de 1200 kg/m3.","1030 kg/m3","1600 kg/m3","950 kg/m3","1080 kg/m3");}
		else if(compteur==6){enigme = new Enigme("Bien ! Mais ce n'est pas encore fini ! Tous les pokemons ne peuvent pas se réfiguer sur le bloc sans mourir noyés dans d'attroces souffrances ! Quel est le pokemon le plus lourd que vous pourriez sauver, sachant que le bloc peut aller jusqu'à la limite de l'immersion totale.","Isachu : 84 kg","Felichu : 36 kg","Leochu : 72 kg","Guiichu : 48 kg");}
		else if(compteur==8){enigme=new Enigme("Dernière énigme : C'est le moment décisif !!!! Ils vous manque une énigme pour que vous soyez champion ! Répondez soigneusement à la question suivante :L'Insamon, a-t-il été bien fait et mérite-t-il des reconnaissances ?","Non, pas du tout !","Pas terrible !","Plus ou moins !","Oui, très bien fait !");}
		else if(compteur==10){
			// si le donjon est fini => on retourne dans la map pour enjoy le monde =)
			System.out.println("on est à 10");
    		Toast.makeText(getApplicationContext(), "Il semble que ce monde n'ait plus de secret pour vous. Vous éteignez donc votre téléphone, et allez au bar!!", Toast.LENGTH_LONG).show();

    		}
		myintent2.putExtra("enigme",enigme);	
	}
	
	
	
	// méthode pour alleger le code qui prend le superflu du code
	/**
	 * traite les différents cas de la mort du pokemon adverse
	 */
	public void mortPokemonAttaquant(){
		
		SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		boolean donjonWin=preferences.getBoolean("donjonWin", false);
		
		if(pokemonAttaquant.getPdv()== 0){
			
//>			// en combat normal
			if(!pokemonDresseur.isDonjonEntre()){
        		Intent myintent2 = new Intent(CombatActivity.this,MapActivity.class);
        		System.out.println("niveau avant gain niveau "+pokemonDresseur.getLvl());
        		int lvlIni= pokemonDresseur.getLvl() ;
        		evolution();
        		
        		
        		System.out.println("niveau après gain niveau "+pokemonDresseur.getLvl());
        		if(lvlIni != pokemonDresseur.getLvl()){
        			lvlUp();
        		}
                Toast.makeText(getApplicationContext(), "Tu as su dompter cette bête féroce. Ce combat a fait mûrir ton pokemon qui a gagné "+ pokemonDresseur.win(pokemonAttaquant) +" xp!", Toast.LENGTH_SHORT).show();
                remplirIntent(myintent2);
                startActivity(myintent2);
        	}
			
        	
//>        	// on est dans le donjon > go aux énigmes
        	else if(pokemonDresseur.isDonjonEntre() && !donjonWin){	
        		System.out.println("on est dans le donjon (en combat)> on sort dans une énigme");
        		
//>>     		// il faut distinguer le cas ou l'on va sortir du donjon (fin) pour anticiper le intent
        		if(compteur==9){myintent2 = new Intent(CombatActivity.this,MapActivity.class);
        			pokemonDresseur.setDonjonEntre(false);
        			pokemonDresseur.setCompteur(10);
        			preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            		SharedPreferences.Editor editor = preferences.edit();
            		editor.putBoolean("donjonWin",true);
            		editor.commit();
            		System.out.println("donjonWin=true");}
        		
        		else{myintent2 = new Intent(CombatActivity.this,LanceEnigmeTest.class);}
     
        		int lvlIni= pokemonDresseur.getLvl() ;
        		System.out.println("niveau avant gain niveau "+pokemonDresseur.getLvl());
        		evolution();
        		System.out.println("niveau après gain niveau "+pokemonDresseur.getLvl());
        		if(lvlIni != pokemonDresseur.getLvl()){
        			lvlUp();
        			}
      
                // intent spécifique au cas où on est dans le donjon.
                remplirIntent(myintent2);
                compteur++;
                myintent2.putExtra("compteur", compteur);
                direction="aucune";
               // System.out.println(direction);
                myintent2.putExtra("direction",direction);
                //System.out.println("compteur Combat "+compteur);
                chargerEnigmeDonjon(myintent2);
                Toast.makeText(getApplicationContext(), "Vous avez dominé ce monstre sorti des abîmes du donjon lugubre, mais vous vous interroger encore sur le chemin à suivre....", Toast.LENGTH_SHORT).show();
                startActivity(myintent2);
        	}
        	else if(pokemonDresseur.isDonjonEntre() && donjonWin){
        		// il faut distinguer le cas ou l'on va sortir du donjon (fin) pour anticiper le intent
        		if(compteur==7){myintent2 = new Intent(CombatActivity.this,MapActivity.class);
        			pokemonDresseur.setDonjonEntre(false);
        			for(int i=0;i<listPok.size();i++){
    					listPok.get(i).setDonjonEntre(false);
    					}
        			preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            		SharedPreferences.Editor editor = preferences.edit();
            		editor.putBoolean("donjonWin2",true);
            		editor.commit();
            		System.out.println("donjonWin2=true");
            		Toast.makeText(getApplicationContext(), "Il semble que ce monde n'ait plus de secret pour vous. Vous éteignez donc votre téléphone, et allez au bar!!", Toast.LENGTH_LONG).show();
        		}
        		
        		else{myintent2 = new Intent(CombatActivity.this,CombatActivity.class);}
     
        		int lvlIni= pokemonDresseur.getLvl() ;
        		System.out.println("niveau avant gain niveau "+pokemonDresseur.getLvl());
        		evolution();
        		System.out.println("niveau après gain niveau "+pokemonDresseur.getLvl());
        		if(lvlIni != pokemonDresseur.getLvl()){
        			lvlUp();
        			}
        		
        		Pokemon pokeDonjon =new Pidgey();
        		
        		if(compteur==1){pokeDonjon=new Donphan();
        		pokeDonjon.setLvl(7);}
        		else if(compteur==2){pokeDonjon=new Azumarill();
        		pokeDonjon.setLvl(8);}
        		else if(compteur==3){pokeDonjon=new Poliwrath();
        		pokeDonjon.setLvl(9);}
        		else if(compteur==4){pokeDonjon=new Ursaring();
        		pokeDonjon.setLvl(10);}
        		else if(compteur==5){pokeDonjon=new Steelix();
        		pokeDonjon.setLvl(11);}
        		else if(compteur==6){pokeDonjon=new Tyranitar();
        		pokeDonjon.setLvl(12);}
        
        		// dans le cas du donjon, il faut ajouter qqs attributs spécifiques pour la suite.
        		remplirIntent(myintent2);
        		compteur++;
        		myintent2.putExtra("pokAtt", pokeDonjon); 
        		myintent2.putExtra("compteur", compteur);
                startActivity(myintent2);
        	}
			
		}
	}

	/**
	 * 
	 */
	public void lvlUp() {
		pokemonDresseur.setPointsDeVieMax(pokemonDresseur.getPointsDeVieMax()+15); //augmente la vie
		pokemonDresseur.setpAttaque(pokemonDresseur.getPAttaque()+2); //augmente l'attaque
		pokemonDresseur.setpDefense(pokemonDresseur.getpDefense()+2); //augmente la defense
		Toast.makeText(getApplicationContext(), "Lvl up! "+pokemonDresseur.getNomPokemon()+ " est désormais niveau "+pokemonDresseur.getLvl(), Toast.LENGTH_LONG).show();
	}
	
	/**
	 * traite la mort du pokemon dresseur
	 */
	public void mortPokemonDresseur(){
		if(pokemonDresseur.getPdv()== 0){
					Intent myintent2 = new Intent(CombatActivity.this,MesPokemonsListe.class);
		        	remplirIntent(myintent2);
		        	myintent2.putExtra("combatEntre", true);
		        	myintent2.putExtra("pokAtt", pokemonAttaquant);
		        	if(pokemonDresseur.isDonjonEntre()){
			        	myintent2.putExtra("compteur", compteur);
			            direction="aucune";
			            myintent2.putExtra("dansDonjon",true);
			            myintent2.putExtra("direction",direction);   
		        	
			}
			Toast.makeText(getApplicationContext(), pokemonDresseur.getNomPokemon()+ " não pode mais combater, escolha um outro pokemon!", Toast.LENGTH_SHORT).show();
        	startActivity(myintent2);
		}
	}
	
	
	/**
	 * le pokemon peut-il évoluer?
	 */
	public void evolution(){
		
		pokemonDresseur.evoluerNiveau();
		if (pokemonDresseur instanceof PeutEvoluer){
			removeFromArrayList(pokemonDresseur.getNomPokemon());
			pokemonDresseur=((PeutEvoluer) pokemonDresseur).evoluer(pokemonDresseur);
			listPok.add(pokemonDresseur);
			pokemonDresseur.evoluerNiveau();  //si on change de pokemon
			System.out.println(pokemonDresseur.getNomPokemon());}
		
 
        if(!(pokemonDresseur.equals(getIntent().getSerializableExtra("pokeDresseur")))){
            Toast.makeText(getApplicationContext(), "Ton pokemon à evolué et est maintenant un "+pokemonDresseur.getNomPokemon(), Toast.LENGTH_LONG).show();
        }
	}
	
	public void pokemonCapture() {
		boolean equalPokemon = false;
		if(!pokemonDresseur.isDonjonEntre()){
    		for(int i=0;i<listPok.size();i++){
    	        if(listPok.get(i).getNomPokemon().equals(pokemonAttaquant.getNomPokemon())){
    	        	equalPokemon=true;
    	        	break;
    	        }
    		} 
    	if(!equalPokemon && captureChance(pokemonAttaquant)){
    	listPok.add(pokemonAttaquant);
    	Toast.makeText(getApplicationContext(), "Tu as capturé "+pokemonAttaquant.getNomPokemon()+"!", Toast.LENGTH_SHORT).show();
    	Intent myintent2 = new Intent(CombatActivity.this,MapActivity.class);  // si non il y a la possibilité de ne pas pouvoir fuire
    	remplirIntent(myintent2);
    	startActivity(myintent2); 	
    	}else if(equalPokemon){
    		Toast.makeText(getApplicationContext(), "Tu as déjà ce pokemon!", Toast.LENGTH_SHORT).show();
    	}else if(!captureChance(pokemonAttaquant) && !equalPokemon){
        	Toast.makeText(getApplicationContext(), pokemonAttaquant.getNomPokemon()+" ne se laissera pas capturer!!", Toast.LENGTH_SHORT).show();
        	}
    	}else if(pokemonDresseur.isDonjonEntre()){
    		Toast.makeText(getApplicationContext(), "C'est pokemon est déjà a quelqu'un!!", Toast.LENGTH_SHORT).show();	
    	}
	}
	
	public void removeFromArrayList(String nomPok){
		for(int i=0;i<listPok.size();i++){
			if(listPok.get(i).getNomPokemon().equals(nomPok)){
				listPok.remove(listPok.get(i));
				
			}
		}

	}	
		
	public boolean captureChance(Pokemon pokemonAttaquant){

		if(pokemonAttaquant.getPdv()<=(int)(pokemonAttaquant.getPointsDeVieMax()/20)){
			return true;
		}else if(pokemonAttaquant.getPdv()<=(int)(pokemonAttaquant.getPointsDeVieMax()/8) && Math.random()<0.7){
			return true;
		}else if(pokemonAttaquant.getPdv()<=(int)(pokemonAttaquant.getPointsDeVieMax()/4) && Math.random()<0.3){
			return true;
		}else if(Math.random()<0.2 && pokemonAttaquant.getLvl()<6){
			return true;
		}else{
			return false;
		}
		}
		
	}
	

