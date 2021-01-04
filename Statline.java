package nbaProject;

public class Statline {
	
	private Player player;
	private int points;
	private int rebounds;
	private int assists;
	private int steals;
	private int blocks;
	
	public Statline(Player player, int points, int rebounds, int assists, int steals, int blocks) {
		this.player = player;
		this.points = points;
		this.rebounds = rebounds;
		this.assists = assists;
		this.steals = steals;
		this.blocks = blocks;
		//player.addGame(this); 
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getPoints() {
		return points;
	}

	public void addPoints(int points) {
		this.points += points;
	}

	public int getRebounds() {
		return rebounds;
	}

	public void addRebounds(int rebounds) {
		this.rebounds += rebounds;
	}

	public int getAssists() {
		return assists;
	}

	public void addAssists(int assists) {
		this.assists += assists;
	}

	public int getSteals() {
		return steals;
	}

	public void addSteals(int steals) {
		this.steals += steals;
	}

	public int getBlocks() {
		return blocks;
	}

	public void addBlocks(int blocks) {
		this.blocks += blocks;
	}

}
