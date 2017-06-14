package generic;

/**
 * C# has spoiled me and I wanted to use a tuple
 * but apparently java doesn't have a Tuple class
 * built in so I made my own.
 * This is used for the results of each conversion.
 * @author Matthew Corless
 *
 * @param <T>
 * @param <U>
 */
public class Tuple<T, U, V, W> 
{
	  public final T _t; // decimal
	  public final U _u; // binary
	  public final V _v; // hex
	  public final W _w; // octal
	  
	  public Tuple(T t, U u, V v, W w) 
	  {
	    this._t = t;
	    this._u = u;
	    this._v = v;
	    this._w = w;
	  }
	  
	  @Override
	  public String toString() 
	  {
	    return String.format("(%s, %s, %s, %s)", _t, _u, _v, _w);
	  }
}