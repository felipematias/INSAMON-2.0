package insamon2.insa.amerinsa;

import insamon2.insa.amerinsa.pokemon.Pokemon;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class PokInitActivity extends Activity implements OnItemClickListener{

	
	private ListView listView;
    private AdapterListView adapterListView;
    private ArrayList<PokInitListView> itens;
    private static Personnage personnage=new Personnage("Ash", 64*9, 64*5);
    private Pokemon pokemonDresseur;
    private Enigme enigme=new Enigme("","","","","");
    private int x=47;
    private int y=29;
    private boolean donjonWin=false;
    private ArrayList<Integer> coord =new ArrayList <Integer>(); // ce nombre donne le nombre d'énigmes résolues, et les valeurs qu'on ajoutera � cette liste seront les cooord des �nigmes r�solues
    private ArrayList<Pokemon> listPok =new ArrayList <Pokemon>(6);
    
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pok_init);
		
		
		
		listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(this);
        
        
        createListView();
      
		
	}
	
	private void createListView() {
        //La liste qui va remplir le ListView
        itens = new ArrayList<PokInitListView>();
        
        
        
        
        PokInitListView item1 = new PokInitListView("Charmander", R.drawable.charmander_ico);
        PokInitListView item2 = new PokInitListView("Bulbasaur", R.drawable.bulbasaur_ico);
        PokInitListView item3 = new PokInitListView("Squirtle", R.drawable.squirtle_ico);
        PokInitListView item4 = new PokInitListView("Pikachu", R.drawable.pikachu_ico);
 
        itens.add(item1);
        itens.add(item2);
        itens.add(item3);
        itens.add(item4);
        
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        donjonWin=preferences.getBoolean("donjonWin", false);
		if(donjonWin==true){
			PokInitListView item5 = new PokInitListView("Cyndaquil", R.drawable.cyndaquil_ico);
	        PokInitListView item6 = new PokInitListView("Chikorita", R.drawable.chikorita_ico);
	        PokInitListView item7 = new PokInitListView("Totodile", R.drawable.totodile_ico);
	        itens.add(item5);
	        itens.add(item6);
	        itens.add(item7);
        }
 
        //Crie l'adapter
        adapterListView = new AdapterListView(this, itens);
        listView.setOnItemClickListener(this);
        //Défine l'Adapter
        listView.setAdapter(adapterListView);
        
        listView.setCacheColorHint(Color.TRANSPARENT);
    }
	
	
	 public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	        //Pren l'item sélectionné
	        PokInitListView item = adapterListView.getItem(arg2);
	      
	        //On crie un Toast qui affiche le pokemon selectionné
	        Toast.makeText(this, "Tu as selectionné: " + item.getText(), Toast.LENGTH_SHORT).show();
			
			Class<?> c;
			try {
				c = Class.forName("insamon2.insa.amerinsa.pokemon."+item.getText());
				Constructor<?> cons = c.getConstructor();
				Pokemon object = (Pokemon) cons.newInstance();
				pokemonDresseur=object;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Pokemon pok1=pokemonDresseur;
			listPok.add(pok1);
	        
	        // il faut stocker le pokemon choisi dans l'attribut pokemonDresseur.
	        //  pokemonDresseur=
	        //On va dans la map
	        //MapJeu jeu = new MapJeu(MapActivity.getContext(), personnage); //if faut verifier comment marche le context
	        Intent myintent2 = new Intent(PokInitActivity.this,MapActivity.class);
	       // myintent2.putExtra(getString(R.string.extraMessage), item.getText());
	        myintent2.putExtra("pokeDresseur",pokemonDresseur);
	        myintent2.putExtra("perso", personnage);
	        myintent2.putExtra("x",x);
        	myintent2.putExtra("y",y);
        	myintent2.putExtra("enigme", enigme);
        	myintent2.putExtra("coord", coord); 
            myintent2.putExtra("listPok",listPok);
        	
        	startActivity(myintent2);
            Toast.makeText(this, "Bon Jeu! (:", Toast.LENGTH_LONG).show();
	    }
	 
	 

	public static Personnage getPersonnage() {
		return personnage;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pok_init, menu);
		return true;
	}
	
	 
}
