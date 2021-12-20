import java.util.Arrays;

/**
 * The song class is used to initialize a song, 
 * whether that be by manually assigning the the artist, song, etc., 
 * or by passing in an info String and then parsing it in order to assign the values.
 * 
 * The song class also has a few helper methods that can help for other classes. 
 * The song class allows us to get the title, artist, and time of a song
 * 
 * @author Diego Cifuentes
 * @version 1
 */
public class Song 
{
	private String title;
	private String artist;
	private int[] time;
	private static final String INFO_DELIMITER = "; ";
	private static final String TIME_DELIMITER = ":";
	private static final int IDX_TITLE = 0;
	private static final int IDX_ARTIST = 1;
	private static final int IDX_TIME = 2;
	
	/**
	 * Initializes a song by assigning the title, the artist, and the time of a song, manually
	 * 
	 * @param title		The title of the song
	 * @param artist	The artist of the song
	 * @param time		The length of the song
	 */
	public Song(String title, String artist, int[] time) 
	{
		this.title = title;
		this.artist = artist;
		this.time = Arrays.copyOf(time, time.length);
	}
	
	/**
	 * Initializes a song by parsing a string containing the title, artist, 
	 * and the time of a song with a semicolon and a space using the static delimiter variables
	 * and then assigning it
	 * 
	 * @param info		File that contains the title, artist, and the time of the song
	 */
	//Help from Braden
	public Song(String info)
	{
		String[] data = info.split(INFO_DELIMITER, -2);
		
		this.title = data[IDX_TITLE];
		this.artist = data[IDX_ARTIST];
		String time = data[IDX_TIME];
		
		String[] splitTime = time.split(TIME_DELIMITER, -2);
		int[] intTime = new int[splitTime.length];
		
		for(int i = 0; i < splitTime.length; i++)
		{
			if (splitTime[i] != null)
			{
				intTime[i] = Integer.parseInt(splitTime[i]);
			}
		}
		this.time = Arrays.copyOf(intTime, intTime.length);
	}
	
	/**
	 * Gets the title of a song
	 * 
	 * @return Title of a song
	 */
	public String getTitle() 
	{
		return title;
	}
	
	/**
	 * Gets the artist of a song
	 * 
	 * @return Artist of a song
	 */
	public String getArtist() 
	{
		return artist;
	}
	
	/**
	 * Gets the play time of a song
	 * 
	 * @return A copy of an array containing the time and the play time of a song
	 */
	public int[] getTime() 
	{
		return Arrays.copyOf(time, time.length);
	}
	
	/**
	 * Overrides the toString method in order to print out the output correctly
	 * Returns a String representation of the Song in the same format as given in the info file
	 */
	//Help from Discord discussion
	//Help from Braden 
	@Override
	public String toString()
	{
		if (time.length == 1)
		{
			return title + INFO_DELIMITER + artist + INFO_DELIMITER + time[0];
		}
		else if (time.length == 2)
		{
			return title + INFO_DELIMITER + artist + INFO_DELIMITER + String.format("%d:%02d", time[0], time[1]);
		}
		else
		{
			return title + INFO_DELIMITER + artist + INFO_DELIMITER + String.format("%d:%02d:%02d", time[0], time[1], time[2]);
		}
	}
}
