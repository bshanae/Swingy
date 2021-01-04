package model.closed;

import application.patterns.SingletonMap;
import controller.open.Commands;
import model.closed.artefacts.armor.ArmorStorage;
import model.closed.artefacts.helm.HelmStorage;
import model.closed.artefacts.weapon.WeaponStorage;
import model.closed.creatures.enemy.EnemyStorage;
import model.closed.creatures.hero.HeroStorage;
import model.closed.creatures.hero.heroTemplate.HeroTemplateStorage;
import model.closed.delegates.core.CoreDelegate;

import java.util.concurrent.locks.ReentrantLock;

public class						Game
{
	private enum					State
	{
		WAITING,
		RUNNING,
		TERMINATED
	}

	private State					state;
	private final CoreDelegate		coreDelegate;

	ReentrantLock					lock;

	public static Game				getInstance()
	{
		return SingletonMap.getInstanceOf(Game.class);
	}

	public boolean					isTerminated()
	{
		return state == State.TERMINATED;
	}

// -------------------------------> Constructor

	public							Game()
	{
		lock = new ReentrantLock();

		coreDelegate = new CoreDelegate();
		state = State.WAITING;
	}

// -------------------------------> Control methods

	public void						run()
	{
		state = State.RUNNING;

		HelmStorage.getInstance().download();
		ArmorStorage.getInstance().download();
		WeaponStorage.getInstance().download();

		HeroTemplateStorage.getInstance().download();
		HeroStorage.getInstance().download();
		EnemyStorage.getInstance().download();

		coreDelegate.run();
	}

	public void						terminate()
	{
		state = State.TERMINATED;

		HeroStorage.getInstance().upload();
		coreDelegate.terminate();
	}

	public void						update()
	{
		lock.lock();
		coreDelegate.update();
		lock.unlock();
	}

	public void						respondToCommand(Commands.Abstract command)
	{
		lock.lock();
		coreDelegate.respond(command);
		lock.unlock();
	}
}
