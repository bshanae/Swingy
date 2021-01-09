package swingy.model.closed;

import swingy.application.patterns.SingletonMap;
import swingy.application.service.Debug;
import swingy.application.service.LogGroup;
import swingy.controller.open.Commands;
import swingy.model.closed.artefacts.armor.ArmorStorage;
import swingy.model.closed.artefacts.helm.HelmStorage;
import swingy.model.closed.artefacts.weapon.WeaponStorage;
import swingy.model.closed.creatures.enemy.EnemyStorage;
import swingy.model.closed.creatures.hero.storage.HeroStorageFactory;
import swingy.model.closed.creatures.hero.template.HeroTemplateStorage;
import swingy.model.closed.delegates.concrete.core.CoreDelegate;

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
