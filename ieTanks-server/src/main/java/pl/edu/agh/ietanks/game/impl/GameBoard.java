package pl.edu.agh.ietanks.game.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.edu.agh.ietanks.game.BoardInterface;
import pl.edu.agh.ietanks.game.ProjectileInterface;
import pl.edu.agh.ietanks.game.TankInterface;

public class GameBoard implements BoardInterface {

	private int height;
	private int width;
	private Map<Integer, TankInterface> tanks = new HashMap<Integer, TankInterface>();
	private List<ProjectileInterface> bullets = new ArrayList<ProjectileInterface>();
	
	@Override
	public List<TankInterface> getTanks() {
		List<TankInterface> result = new ArrayList<TankInterface>();
		result.addAll(tanks.values());
		return result;
	}

	@Override
	public TankInterface getTank(int id) {
		return tanks.get(id);
	}

	@Override
	public List<ProjectileInterface> getBullets() {
		return bullets;
	}

	@Override
	public void addTank(TankInterface tank) {
		tanks.put(tank.getId(), tank);
	}

	@Override
	public void addBullet(ProjectileInterface bullet) {
		bullets.add(bullet);
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public Object getBoard() {
		char gamefield[][] = new char[height][width];
		for(int i = 0; i < height; ++i){
			for(int j = 0; j < width; ++j){
				gamefield[i][j] = '.';
			}
		}
		
		for(TankInterface tank : tanks.values()){
			int cX = tank.getX(), cY = tank.getY();
			int tsizeY = tank.getHeight(), tsizeX = tank.getWidth();
			
			int sX = cX - tsizeX / 2, sY = cY - tsizeY / 2;
			int eX = sX + tsizeX, eY = sY + tsizeY;
			for(int i = sY; i < eY; ++i){
				for(int j = sX; j < eX; ++j){
					gamefield[i][j] = 'T';
				}
			}
			
		}
		
		for(ProjectileInterface bullet : bullets){
			gamefield[bullet.getY()][bullet.getX()] = 'B';
		}
		
		
		return Arrays.deepToString(gamefield);
	}

	
	public GameBoard(int width, int height){
		this.width = width;
		this.height = height;
	}
}
