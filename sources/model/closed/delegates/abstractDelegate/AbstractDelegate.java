package model.closed.delegates.abstractDelegate;

import controller.open.Commands;
import lombok.Getter;
import model.closed.delegates.abstractDelegate.commands.ExecutableCommand;
import model.closed.delegates.abstractDelegate.commands.LostCommand;
import model.open.Model;
import model.open.Requests;

public abstract class AbstractDelegate
{
// -----------------------------------> Attributes

	@Getter
	private boolean						isActivated;
	@Getter
	private boolean						isResolved;

	@Getter
	private AbstractDelegate			parent;
	@Getter
	private AbstractDelegate			child;

	private boolean						shouldStackChild;
	private AbstractDelegate			newChild;

	private boolean						shouldResolve;
	private AbstractResolutionObject	resolutionMessage;

// -----------------------------------> Constructor

	protected							AbstractDelegate()
	{
		isActivated = false;
		isResolved = false;

		shouldResolve = false;
		resolutionMessage = null;
	}

// -----------------------------------> Properties

	public boolean						hasParent()
	{
		return parent != null;
	}

	public boolean						hasChild()
	{
		return child != null;
	}

	public boolean						waitingToStackChild()
	{
		return shouldStackChild;
	}

	public boolean						waitingToResolve()
	{
		return shouldResolve;
	}

// -----------------------------------> Hierarchy methods

	public void 						stackChildLater(AbstractDelegate child)
	{
		shouldStackChild = true;
		newChild = child;
	}

	private void						stackChild()
	{
		AbstractDelegate				iterator;

		iterator = this;
		while (iterator.hasChild())
			iterator = iterator.child;

		DelegateLogger.logChildStacking(iterator, newChild);

		iterator.child = newChild;
		newChild.parent = iterator;

		iterator.deactivate();
		newChild.activate();

		shouldStackChild = false;
		iterator.newChild = null;
	}

// -----------------------------------> Control methods

	protected void						activate()
	{
		DelegateVerifier.checkNotResolved(this);
		DelegateLogger.logActivating(this);

		isActivated = true;
		whenActivated();
	}

	protected void						deactivate()
	{
		DelegateVerifier.checkIfActivated(this);
		DelegateVerifier.checkNotResolved(this);

		DelegateLogger.logDeactivating(this);

		isActivated = false;
		whenDeactivated();
	}

	public final void					update()
	{
		DelegateVerifier.checkNotResolved(this);

		if (shouldStackChild)
			stackChild();

		if (shouldResolve)
		{
			resolve();
			return;
		}

		if (child != null)
			child.update();

		if (isActivated)
			whenUpdated();
	}

	public void							tryToExecuteCommand(Commands.Abstract command)
	{
		DelegateCommandExecutor.tryToExecuteCommandRecursively(this, command);
	}

// -----------------------------------> Resolution methods

	protected final void 				resolveLater(AbstractResolutionObject object)
	{
		shouldResolve = true;
		resolutionMessage = object;
	}

	private void 						resolve()
	{
		AbstractDelegate				parentCopy;

		DelegateLogger.logResolving(this);

		DelegateVerifier.checkHasNoChild(this);
		DelegateVerifier.checkIfActivated(this);

		if (hasParent())
		{
			parentCopy = parent;

			this.deactivate();

			parent.child = null;
			parent = null;

			parentCopy.whenChildResolved(resolutionMessage);
			parentCopy.activate();
		}

		isResolved = true;

		shouldResolve = false;
		resolutionMessage = null;
	}

// -----------------------------------> Callback methods

	// TODO GUI<->Console switching
	protected void						whenActivated() {}
	protected void						whenDeactivated() {}

	protected void						whenUpdated() {}

	protected void						whenChildResolved(AbstractResolutionObject object) {}

	protected void						tryToExecuteCommand(ExecutableCommand command) {}
	protected void						tryToExecuteCommandSilently(ExecutableCommand command) {}

	public void 						tryCatchLostCommand(LostCommand command) {}

// -----------------------------------> Helper methods

	protected void						sendRequest(Requests.Abstract request)
	{
		Model.getInstance().notifyListener(request);
	}
}
