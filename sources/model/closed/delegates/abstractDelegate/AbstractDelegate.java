package model.closed.delegates.abstractDelegate;

import controller.open.Commands;
import lombok.Getter;
import model.closed.delegates.concreteDelegates.common.ErrorDelegate;
import model.open.Model;
import model.open.Requests;

public abstract class AbstractDelegate
{
// -----------------------------------> Exceptions

	public static class					CommandIsNotExecuted extends RuntimeException
	{
		public							CommandIsNotExecuted(Commands.Abstract command)
		{
			super("Command of type '" + command.getClass() + "' was not executed");
		}
	}

// -----------------------------------> Fields

	@Getter
	private boolean						isActivated;
	@Getter
	private boolean						isResolved;

	private boolean						isFirstTime;
	private boolean						isFirstTimeDeactivated;

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

		isFirstTime = true;
		isFirstTimeDeactivated = true;

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

// -----------------------------------> Hierarchy methods

	public void 						stackChildLater(AbstractDelegate child)
	{
		shouldStackChild = true;
		newChild = child;
	}

	private void						stackChild()
	{
		DelegateVerifier.checkHasNoChild(this);
		DelegateVerifier.checkHasNoParent(newChild);

		DelegateLogger.logChildStacking(this, newChild);

		this.child = newChild;
		newChild.parent = this;

		this.deactivate();
		newChild.activate();

		shouldStackChild = false;
		this.newChild = null;
	}

	private void						dropParent()
	{
		DelegateVerifier.checkHasParent(this);
		DelegateVerifier.checkHasChild(parent);

		this.deactivate();
		parent.activate();

		parent.child = null;
		parent = null;
	}

// -----------------------------------> Control methods

	protected void						activate()
	{
		DelegateVerifier.checkNotResolved(this);
		DelegateLogger.logActivating(this);

		isActivated = true;
		whenActivated(isFirstTime);

		isFirstTime = false;
	}

	protected void						deactivate()
	{
		DelegateVerifier.checkIfActivated(this);
		DelegateVerifier.checkNotResolved(this);

		DelegateLogger.logDeactivating(this);

		isActivated = false;
		whenDeactivated(isFirstTimeDeactivated);

		isFirstTimeDeactivated = false;
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
		AbstractDelegate parentCopy;

		DelegateLogger.logResolving(this);

		DelegateVerifier.checkHasNoChild(this);
		DelegateVerifier.checkIfActivated(this);

		if (hasParent())
		{
			parentCopy = parent;

			dropParent();
			parentCopy.whenChildResolved(resolutionMessage);
		}

		isResolved = true;

		shouldResolve = false;
		resolutionMessage = null;
	}

// -----------------------------------> Callback methods

	// TODO GUI<->Console switching
	protected void						whenActivated(boolean isFirstTime) {}
	protected void						whenDeactivated(boolean isFirstTime) {}

	protected void						whenUpdated() {}

	protected void						whenChildResolved(AbstractResolutionObject object) {}

	protected void						tryToExecuteCommand(ExecutableCommand command) {}

	protected void						tryToExecuteCommandSilently(ExecutableCommand command) {}

// -----------------------------------> Helper methods

	protected void						sendRequest(Requests.Abstract request)
	{
		Model.getInstance().notifyListener(request);
	}
}
