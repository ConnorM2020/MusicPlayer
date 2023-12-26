package part01;

public interface iPlayer {
	public String[] getTuneInfo();
	public String[] getTuneInfo(String artist);
	public String[] getTuneInfo(Genre gen);
	public String play(int tuneId);
	public boolean addtune(String tite, String artist, int duration, Genre gen);
	public boolean switchOn();
	public boolean switchOff();
}
