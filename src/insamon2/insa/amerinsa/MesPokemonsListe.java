package insamon2.insa.amerinsa;

import insamon2.insa.amerinsa.pokemon.Pokemon;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MesPokemonsListe extends Activity implements OnItemClickListener{

	
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
    private Pokemon pokemonAttaquant;
    boolean combatEntre;
	private int compteur;
	private String direction;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pok_init);
		
		
		recupererIntent();
		
		
		listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(this);

        createListView();
      
        listView.setLongClickable(true);
        listView.setOnItemLongClickListener(new OnItemLongClickListener() {
             public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            	 
            	PokInitListView item = adapterListView.getItem(arg2);
            	final String NOMPOK=item.getText();
            	final int POSITION=arg2;
            	
            	AlertDialog.Builder builder = new AlertDialog.Builder(MesPokemonsListe.this);

     		    builder.setMessage("Você quer mesmo abandonar esse Pokemon?")
     		            .setCancelable(false)
     		            .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
     		                public void onClick(DialogInterface dialog, int id) {
     		                	for(int i=0;i<listPok.size();i++){
     		    					if(listPok.get(i).getNomPokemon()==NOMPOK){
     		    						PokInitListView pok = itens.get(POSITION);
     		    						itens.remove(pok);
     		    						listPok.remove(POSITION);
     		    						adapterListView = new AdapterListView(MesPokemonsListe.this, itens);
     		    						listView.setAdapter(adapterListView);
     		    						}
     		                	}
     		                }
     		            })
     		            .setNegativeButton("Non", new DialogInterface.OnClickListener() {
     		                public void onClick(DialogInterface dialog, int id) {
     		                    dialog.cancel();
     		                }
     		            });
     		    AlertDialog alert = builder.create();
     		    alert.show();
                return true;
             }
         });
	}
	
	private void createListView() {
        //La liste qui va remplir le ListView
        itens = new ArrayList<PokInitListView>();
        
        
        for(int i=0;i<listPok.size();i++){
	        if(listPok.get(i).getNomPokemon().equals(pokemonDresseur.getNomPokemon())==true){
	        	listPok.remove(listPok.get(i));
	        	listPok.add(pokemonDresseur);
        }
//	        if(listPok.indexOf(pokemonDresseur)==-1 && listPok.get(i).getNomPokemon().equals(pokemonDresseur.getNomPokemon())==false){
//	        	listPok.add(pokemonDresseur);
//        }
        Pokemon pok=listPok.get(i);
        int id = getResources().getIdentifier(pok.getNomImage()+"_ico", "drawable", getApplicationContext().getPackageName());
        itens.add(new PokInitListView(pok.getNomPokemon(), id));
 
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
			
				for(int i=0;i<listPok.size();i++){
					if(listPok.get(i).getNomPokemon()==item.getText()){
						pokemonDresseur=listPok.get(i);
						}
					}
			Intent myintent2=null;
			
			if(combatEntre){
				if(pokemonDresseur.getPdv()>0){
				Boolean dansDonjon=(boolean)getIntent().getBooleanExtra("dansDonjon", false);
				myintent2 = new Intent(MesPokemonsListe.this,CombatActivity.class);
				remplirIntent(myintent2);
				
				if(dansDonjon){
					compteur=(Integer)getIntent().getSerializableExtra("compteur"); 
					direction=(String)getIntent().getSerializableExtra("direction");
					System.out.println(""+compteur);
					myintent2.putExtra("combatEntre", true);
		        	myintent2.putExtra("compteur", compteur);
		        	pokemonDresseur.setDonjonEntre(true);
		        	myintent2.putExtra("pokeDresseur", pokemonDresseur);
					
				}
		        Toast.makeText(this, pokemonDresseur.getNomPokemon()+" vaaai!", Toast.LENGTH_SHORT).show();
		        startActivity(myintent2);
				}else{
					Toast.makeText(this, "Esse pokemon esta muito fraco para o combate", Toast.LENGTH_SHORT).show();
					int j=0;
					for(int i=0;i<listPok.size();i++){
						if(listPok.get(i).getPdv()!=0){
							j++;
						}
					}
					if(j==0){
						myintent2 = new Intent(MesPokemonsListe.this,MapActivity.class);
			        	Toast.makeText(getApplicationContext(), "Ce monstre sauvage qui vous a attaqué a profité de vos blessures. Dans un ultime coup, il a achevé "+pokemonDresseur.getNomPokemon()+ ". Vous êtes donc retourné le soigner en toute urgence.", Toast.LENGTH_LONG).show();
			        	pokemonDresseur.setPdv(pokemonDresseur.getPointsDeVieMax()); // on remet les pv au max
			        	recupererPdvListe();
			        	pokemonDresseur.setDonjonEntre(false); // on sort du donjon
			        	remplirIntent(myintent2);
			        	myintent2.putExtra("x",47);
			        	myintent2.putExtra("y",29);
			        	startActivity(myintent2);
					}
			}
			}else if(!combatEntre){
				if(pokemonDresseur.getPdv()>0){
				myintent2 = new Intent(MesPokemonsListe.this,MapActivity.class);
				remplirIntent(myintent2);
		        Toast.makeText(this, "Bon Jeu! (:", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(this, "Esse pokemon esta muito fraco!", Toast.LENGTH_SHORT).show();
				}
			}
	       
	    }

	/**
	 * 
	 */
	public void recupererPdvListe() {
		for(int i=0;i<listPok.size();i++){
			listPok.get(i).setPdv(listPok.get(i).getPointsDeVieMax());
		}
	}

	/**
	 * @param myintent2
	 */
	public void remplirIntent(Intent myintent2) {
		myintent2.putExtra("pokeDresseur",pokemonDresseur);
		myintent2.putExtra("pokAtt",pokemonAttaquant);
		myintent2.putExtra("perso", personnage);
		myintent2.putExtra("x",x);
		myintent2.putExtra("y",y);
		myintent2.putExtra("enigme", enigme);
		myintent2.putExtra("coord", coord); 
		myintent2.putExtra("listPok",listPok);	
		//myintent2.putExtra("combatEntre", true);
		startActivity(myintent2);
	}
	 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pok_init, menu);
		return true;
	}
	
	public void recupererIntent(){
		Intent i = getIntent();
		pokemonAttaquant= (Pokemon)i.getSerializableExtra("pokAtt");
		pokemonDresseur= (Pokemon)i.getSerializableExtra("pokeDresseur");
		x=(Integer) i.getSerializableExtra("x");
		y=(Integer) i.getSerializableExtra("y");
		enigme=(Enigme)i.getSerializableExtra("enigme");
		coord=(ArrayList<Integer>)i.getSerializableExtra("coord");
		listPok=(ArrayList<Pokemon>)i.getSerializableExtra("listPok");
		combatEntre=(boolean)i.getBooleanExtra("combatEntre", false);
		
	}
	
	
	 
}
