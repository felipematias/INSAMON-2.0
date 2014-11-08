/**
 * 
 */
package insamon2.insa.amerinsa;

import insamon2.insa.amerinsa.pokemon.Pokemon;

/**
 * @author Felipe
 *Certaines pokemons initiales peuvent evoluer dans un autre pokemon
 */
public interface PeutEvoluer {

	public Pokemon evoluer(Pokemon p);
	
}
