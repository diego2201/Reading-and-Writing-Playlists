import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;

/**
 * The Playlist class constructs a playlist using an ArrayList 
 * containing the songs and their various attributes, such as time, artist, etc. 
 * It can construct using a default constructor leaving the ArrayList empty
 * or it can call a readFile method and create a Playlist with the songs from the file
 * 
 * The Playlist class also allows you to get songs from the playlist, 
 * get the amount of songs in a playlist, add songs and remove songs,
 * as well as finding songs by their artists
 * 
 * @author Diego Cifuentes
 * @version 1
 */
public class Playlist 
{
	private ArrayList<Song> songs;
	
	/**
	 * Reads in the given file, can be called upon when constructing a playlist 
	 * or writing a new file
	 * 
	 * @param fileName		Is the file containing the songs, artists, and time
	 * @throws IOException when an error occurs when trying to read from an input stream
	 */
	public void readFile(String fileName) throws IOException
	{
		//From Guru99
		BufferedReader br = null;
		try
		{
			String strCurrentLine;
			br = new BufferedReader(new FileReader(fileName));
			while ((strCurrentLine = br.readLine()) != null)
			{
				Song song = new Song(strCurrentLine);
				songs.add(song);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(br != null)
				{
					br.close();
				}
			}
				catch (IOException ex)
				{
					ex.printStackTrace();
				}
			}
	}
		
	/**
	 * Default constructor for the Playlist, 
	 * initializes a Playlist with an empty ArrayList Song
	 */
	public Playlist() 
	{
		songs = new ArrayList<Song>();
	}
	
	/**
	 * Constructor for a Playlist, 
	 * calls the readFile method in order to parse the text file of info Strings,
	 * initialize the ArrayList with what is read in from the file
	 * 
	 * @param fileName		File containing the songs, artists, and time
	 * @throws IOException when an error occurs when trying to read from an input stream
	 */
	public Playlist(String fileName) throws IOException
	{
		this();
		readFile(fileName);
	}
	
	/**
	 * Gets the number of songs in the playlist
	 * 
	 * @return The size of the ArrayList
	 */
	public int getNumSongs() 
	{
		return songs.size();
	}
	
	/**
	 * Returns the Song at the given index with the given index
	 * 
	 * @param index		Is the index containing the song to be searched for 
	 * @return The song at a given index
	 */
	public Song getSong(int index) 
	{
		if (index < 0 || index >= getNumSongs()) 
		{
			return null;
		}
		return songs.get(index);
	}
	
	/**
	 * Returns a new song array containing the songs from the playlist
	 * 
	 * @return A new song array 
	 */
	public Song[] getSongs() 
	{
		return songs.toArray(new Song[0]);
	}
	
	/**
	 * Adds a given song the playlist
	 * 
	 * @param song		The song that is to be added to the playlist
	 * @return The playlist with the new song added
	 */
	public boolean addSong(Song song) 
	{
		return addSong(getNumSongs(), song);
	}
	
	/**
	 * If the Song reference is null or the index is out of bounds then the Playlist is left unchanged
	 * Otherwise it adds a Song to the playlist to a given index 
	 * 
	 * @param index		The index where the song will be added
	 * @param song		The song to be added to the playlist
	 * @return True if the song is added, if the song reference is null or the index is out of bounds returns false
	 */
	//Help from Discord discussion
	//Help from Ethan Ho
	public boolean addSong(int index, Song song) 
	{
		if (song == null || index < 0 || index > songs.size())
		{
			return false;
		}
		else
		{
			songs.add(index, song);
			return true;
		}
	}
	
	/**
	 * Adds a given song to the end of a playlist
	 * 
	 * @param playlist		Where the given song will be added to 
	 * @return The number of added songs, if the playlist reference is null returns 0
	 */
	public int addSongs(Playlist playlist) 
	{
		if (playlist == null)
		{
			return 0;
		}
		else
		{
			int songsAdded = playlist.getNumSongs();
			
			for (int i = 0; i < songsAdded; i++)
			{
				songs.add(playlist.getSong(i));
			}
			return songsAdded;
		}
	}
	
	/**
	 * Reads in a file of info Strings. For each line of the file read, 
	 * creates a Song and then adds it to the end of the Playlist ArrayList
	 * 
	 * @param filename		The info String containing the songs and its values such as time
	 * @return 0
	 * @throws IOException when an error occurs when trying to read from an input stream
	 */
	public int addSongs(String filename) throws IOException
	{
		readFile(filename);
		return 0;
	}
	
	/**
	 * Removes a song from the playlist
	 * 
	 * @return Playlist using the removeSong method with one less song in the ArrayList
	 */
	public Song removeSong() 
	{
		return removeSong(getNumSongs() - 1);
	}
	
	/**
	 * Removes a song at the given index and returns the Song with that given index
	 * If index is out of bounds then returns null
	 * 
	 * @param index		The index containing the song to be removed
	 * @return The given song with its given index, if the index is out of bounds returns null
	 */
	public Song removeSong(int index) 
	{
		if (index > songs.size() - 1 || index < 0)
		{
			return null;
		}
		else
		{
			Song result = songs.get(index);
			songs.remove(index);
			return result;
		}
	}
	
	/**
	 * Saves the output of the toString method to a file with a given name
	 * If a file exists it overwrites that file, if not a new file is created
	 * 
	 * @param filename		The file contains the info used to create a playlist
	 * @throws IOException when an error occurs when trying to read from an input stream
	 */
	public void saveSongs(String filename) throws IOException
	{
		//From Guru99
		try
		{
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			bw.write(this.toString());
			bw.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Overrides the toString method in order to correctly print the output
	 * Returns the String representation of the Songs that are joined with line separators.
	 */
	@Override
	//Help from Discord Discussion
	//Help from Office Hours
	public String toString()
	{
		String result = "";
		StringJoiner sj = new StringJoiner(System.lineSeparator());
		for (int i = 0; i < songs.size(); ++i)
		{
			sj.add(songs.get(i).toString());
		}
		result = sj.toString();
		return result;
	}
	
	/**
	 * Returns a Song array containing all of the songs from the playlist 
	 * with the artist passed in the parameters
	 * 
	 * @param artist	Is the artist that we are looking for 
	 * @return An array of Songs containing songs by a given artist
	 */
	//Help from office hours
	//Help from Braden
	public Song[] findSongsByArtist(String artist)
	{
		int counter = 0;
		Song[] findArtist = new Song[1];
		for (int i = 0; i < songs.size(); i++)
		{
			String line = songs.get(i).getArtist();
			if (line.equals(artist))
			{
				if (counter > findArtist.length - 1)
				{
					findArtist = Arrays.copyOf(findArtist, findArtist.length + 1);
				}
				findArtist[counter] = songs.get(i);
				counter++;
			}
		}
		if (findArtist[0] == null)
		{
			findArtist = Arrays.copyOf(findArtist, 0);
			return findArtist;
		}
		else
		{
			return findArtist;
		}
	}
}
