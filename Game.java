package nbaProject;

import java.util.ArrayList;
import java.util.Random;

public class Game {
	
	private static Random rnd = new Random();
	private Team homeTeam;
	private Team awayTeam;
	private int homeTeamScore = 0;
	private int awayTeamScore = 0;
	private ArrayList<Statline> homeStatlines = new ArrayList<>(); 
	private ArrayList<Statline> roadStatlines = new ArrayList<>(); 
	
	public Game(Team homeTeam, Team awayTeam) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
	}
	
	public void playGame() {
		for (Player player: homeTeam.getPlayers()) {
			Statline statline = new Statline(player,rnd.nextInt(40),rnd.nextInt(15),rnd.nextInt(15),rnd.nextInt(5),rnd.nextInt(5));
			homeTeamScore += statline.getPoints(); 
			homeStatlines.add(statline);
			player.addGame(statline); 
		}
		for (Player player: awayTeam.getPlayers()) {
			Statline statline = new Statline(player,rnd.nextInt(40),rnd.nextInt(15),rnd.nextInt(15),rnd.nextInt(5),rnd.nextInt(5));
			awayTeamScore += statline.getPoints();
			roadStatlines.add(statline);
			player.addGame(statline); 
		}
		while (homeTeamScore == awayTeamScore) {
			playOvertime();
		}
		if (homeTeamScore > awayTeamScore) {
			System.out.println(homeTeam.getName()+" wins. Score: "+homeTeam.getName()+" "+homeTeamScore+" "+awayTeam.getName()+" "+awayTeamScore);
			homeTeam.addWin();
			awayTeam.addLoss();
		} else {
			System.out.println(awayTeam.getName()+" wins. Score: "+homeTeam.getName()+" "+homeTeamScore+" "+awayTeam.getName()+" "+awayTeamScore);
			awayTeam.addWin();
			homeTeam.addLoss();
		}
	}
	
	public void showBoxScore() {
		System.out.println(homeTeam.getName());
		for (Statline homeStatline: homeStatlines) {
			System.out.println("  " + homeStatline.getPlayer().getName() + ": Points: " + homeStatline.getPoints() + " Rebounds: " 
					+ homeStatline.getRebounds() + " Assists: " + homeStatline.getAssists() + " Steals: " + homeStatline.getSteals() 
					+ " Blocks: " + homeStatline.getBlocks());
		}
		System.out.println(awayTeam.getName());
		for (Statline roadStatline: roadStatlines) {
			System.out.println("  " + roadStatline.getPlayer().getName() + ": Points: " + roadStatline.getPoints() + " Rebounds: " 
					+ roadStatline.getRebounds() + " Assists: " + roadStatline.getAssists() + " Steals: " + roadStatline.getSteals() 
					+ " Blocks: " + roadStatline.getBlocks());
		}
	}
	
	private void playOvertime() {
		generateOvertimeStats(homeTeam, true);
		generateOvertimeStats(awayTeam, false);
	}
	
	private void generateOvertimeStats(Team team, boolean homeTeam) {
		for (Player player: team.getPlayers()) {
			Statline lastStatline = player.getLastStatline();
			int addedPoints = rnd.nextInt(10); 
			int addedRebounds = rnd.nextInt(3); 
			int addedAssists = rnd.nextInt(3); 
			int addedsSteals = rnd.nextInt(1); 
			int addedBlocks = rnd.nextInt(1); 
			lastStatline.addPoints(addedPoints);
			lastStatline.addRebounds(addedRebounds);
			lastStatline.addAssists(addedAssists);
			lastStatline.addSteals(addedsSteals);
			lastStatline.addBlocks(addedBlocks);
			if (homeTeam) {
				homeTeamScore += addedPoints;
			} else {
				awayTeamScore += addedPoints;
			}
		}
	}
}
