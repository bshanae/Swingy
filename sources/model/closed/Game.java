package model.closed;

import application.patterns.SingletonMap;
import application.service.Debug;
import application.service.LogGroup;
import controller.open.Commands;
import model.closed.artefacts.armor.ArmorStorage;
import model.closed.artefacts.helm.HelmStorage;
import model.closed.artefacts.weapon.WeaponStorage;
import model.closed.creatures.enemy.EnemyStorage;
import model.closed.creatures.hero.heroStorage.HeroStorageFactory;
import model.closed.creatures.hero.heroTemplate.HeroTemplateStorage;
import model.closed.delegates.abstractDelegate.AbstractDelegate;
import model.closed.delegates.concreteDelegates.common.ErrorDelegate;
import model.closed.delegates.concreteDelegates.core.CoreDelegate;

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
		Debug.log(LogGroup.GAME, "[Model/Game] Running");

		state = State.RUNNING;

		HelmStorage.getInstance().download();
		ArmorStorage.getInstance().download();
		WeaponStorage.getInstance().download();

		HeroTemplateStorage.getInstance().download();
		HeroStorageFactory.buildInstance().download();
		EnemyStorage.getInstance().download();

		coreDelegate.run();
	}

	public void						terminate()
	{
		Debug.log(LogGroup.GAME, "[Model/Game] Terminating");

		state = State.TERMINATED;

		HeroStorageFactory.buildInstance().upload();
	}

	public void						update()
	{
		lock.lock();
		coreDelegate.update();
		lock.unlock();
	}

	public void						executeCommand(Commands.Abstract command)
	{
		lock.lock();
		coreDelegate.tryToExecuteCommand(command);
		lock.unlock();
	}
}
