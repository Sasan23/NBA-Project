package nbaProject;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Player {
	
	private static final int RIM_HEIGHT = 120;
	private String name;
	private int height; 
	private int standingReach;
	private int verticalLeap;
	private int yearlySalary; 
	private Team team;
	private double pointsPerGame;
	private double reboundsPerGame;
	private double assistsPerGame;
	private double stealsPerGame;
	private double blocksPerGame;
	private ArrayList<Statline> gamesPlayed = new ArrayList<>();
	
	public Player(String name, int height, int standingReach, int verticalLeap) {
		this.name = name;
		this.height = height;
		this.standingReach = standingReach;
		this.verticalLeap = verticalLeap;
		pointsPerGame = 0;
		reboundsPerGame = 0;
		assistsPerGame = 0;
		stealsPerGame = 0;
		blocksPerGame = 0;
		this.yearlySalary = 0; 
	}

	public boolean canPlayerDunk() {
		int heightReached = standingReach + verticalLeap;
		if (heightReached >= RIM_HEIGHT) {
			return true;
		}
		return false;
	}
	
	public void addGame(Statline statline) {
		setPointsPerGame(statline.getPoints());
		setReboundsPerGame(statline.getRebounds());
		setAssistsPerGame(statline.getAssists());
		setStealsPerGame(statline.getSteals());
		setBlocksPerGame(statline.getBlocks());
		gamesPlayed.add(statline);
	}
	
	public void showStats() {
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println(name + " averages: ");
		System.out.println("Points/rebounds/assists/steals/blocks");
		String points = df.format(pointsPerGame);
		String rebounds = df.format(reboundsPerGame);
		String assists = df.format(assistsPerGame);
		String steals = df.format(stealsPerGame);
		String blocks = df.format(blocksPerGame);
		System.out.println(points +"/"+ rebounds +"/"+ assists +"/"+ steals +"/"+ blocks);
	}

	private void setPointsPerGame(int points) {
		int numberOfGames = gamesPlayed.size();
		int totalPoints = (int) (numberOfGames * pointsPerGame);
		int newTotalPoints = totalPoints + points;
		int newNumberOfGames = numberOfGames+1;
		pointsPerGame = ((double) newTotalPoints / (double) newNumberOfGames);
	}

	private void setReboundsPerGame(int rebounds) {
		int numberOfGames = gamesPlayed.size();
		int totalRebounds = (int) (numberOfGames * reboundsPerGame);
		int newTotalRebounds = totalRebounds + rebounds;
		int newNumberOfGames = numberOfGames+1;
		reboundsPerGame = ((double) newTotalRebounds / (double) newNumberOfGames);
	}

	private void setAssistsPerGame(int assists) {
		int numberOfGames = gamesPlayed.size();
		int totalAssists = (int) (numberOfGames * assistsPerGame);
		int newTotalAssists = totalAssists + assists;
		int newNumberOfGames = numberOfGames+1;
		assistsPerGame = ((double) newTotalAssists / (double) newNumberOfGames);
	}

	private void setStealsPerGame(int steals) {
		int numberOfGames = gamesPlayed.size();
		int totalSteals = (int) (numberOfGames * stealsPerGame);
		int newTotalSteals = totalSteals + steals;
		int newNumberOfGames = numberOfGames+1;
		stealsPerGame = ((double) newTotalSteals / (double) newNumberOfGames);
	}

	private void setBlocksPerGame(int blocks) {
		int numberOfGames = gamesPlayed.size();
		int totalBlocks = (int) (numberOfGames * blocksPerGame);
		int newTotalBlocks = totalBlocks + blocks;
		int newNumberOfGames = numberOfGames+1;
		blocksPerGame = ((double) newTotalBlocks / (double) newNumberOfGames);
	}
	
	public Statline getLastStatline() {
		Statline lastGameStatline = gamesPlayed.get(gamesPlayed.size()-1);
		return lastGameStatline;
	}
	
	public void setYearlySalary(int yearlySalary) {
		this.yearlySalary = yearlySalary;
	}
	
	public int getNumberOfGames() {
		return gamesPlayed.size();
	}
	
	public String getName() {
		return name;
	}
	
	public int getYearlySalary() {
		return yearlySalary;
	}

	public int getHeight() {
		return height;
	}
	
	public int getStandingReach() {
		return standingReach;
	}
	
	public int getVerticalLeap() {
		return verticalLeap;
	}
	
	public Team getTeam() {
		return team;
	}
	
	public double getPointsPerGame() {
		return pointsPerGame;
	}

	public double getReboundsPerGame() {
		return reboundsPerGame;
	}

	public double getAssistsPerGame() {
		return assistsPerGame;
	}

	public double getStealsPerGame() {
		return stealsPerGame;
	}

	public double getBlocksPerGame() {
		return blocksPerGame;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return name;
	}	
}
