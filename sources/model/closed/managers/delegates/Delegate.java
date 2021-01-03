package model.closed.managers.delegates;

import application.service.Debug;
import application.service.Exceptions;
import application.service.LogGroup;
import controller.open.Commands;
import model.closed.Game;
import model.closed.managers.delegates.common.ErrorDelegate;
import model.open.Model;
import model.open.Requests;

public abstract class				Delegate
{
// -------------------------------> Nested types

	protected static abstract class	ResolutionMessage {}

	public static class				UnrecognizedCommandException extends RuntimeException
	{
		public						UnrecognizedCommandException(Commands.Abstract command)
		{
			super("Can't recognize command of type '" + command.getClass() + "'");
		}
	}

// -------------------------------> Fields

	private boolean					isActivated;
	private boolean					isResolved;

	private boolean					isFirstTimeActivated;
	private boolean					isFirstTimeDeactivated;

	private Delegate				parentDelegate;
	private Delegate				childDelegate;

	private boolean					shouldResolve;
	private ResolutionMessage		resolutionMessage;

// -------------------------------> Constructor

	protected						Delegate()
	{
		isActivated = false;
		isResolved = false;

		isFirstTimeActivated = true;
		isFirstTimeDeactivated = true;

		shouldResolve = false;
		resolutionMessage = null;
	}

// -------------------------------> Properties

	public boolean					isActivated()
	{
		return isActivated;
	}

	public boolean					isResolved()
	{
		return isResolved;
	}

	public boolean					hasParent()
	{
		return parentDelegate != null;
	}

	public boolean					hasChild()
	{
		return childDelegate != null;
	}

// -------------------------------> Hierarchy methods

	public void						linkParent(Delegate parentDelegate)
	{
		parentDelegate.checkHasNoChild();
		checkHasNoParent();

		parentDelegate.childDelegate = this;
		this.parentDelegate = parentDelegate;
	}

	protected void					unlinkParent()
	{
		checkHasParent();
		parentDelegate.checkHasChild();

		this.deactivate();
		parentDelegate.activate();

		parentDelegate.childDelegate = null;
		parentDelegate = null;
	}

	public void						linkChild(Delegate childDelegate)
	{
		logChildLinking(childDelegate);

		checkHasNoChild();
		childDelegate.checkHasNoParent();

		this.childDelegate = childDelegate;
		childDelegate.parentDelegate = this;

		this.deactivate();
		childDelegate.activate();
	}

	protected void					unlinkChild()
	{
		logChildUnlinking();

		checkHasChild();
		childDelegate.checkHasParent();

		childDelegate.deactivate();
		this.activate();

		childDelegate.parentDelegate = null;
		this.childDelegate = null;
	}

// -------------------------------> Control methods

	protected void					activate()
	{
		checkNotResolved();

		isActivated = true;
		whenActivated(isFirstTimeActivated);

		isFirstTimeActivated = false;
	}

	protected void					deactivate()
	{
		checkIfActivated();
		checkNotResolved();

		isActivated = false;
		whenDeactivated(isFirstTimeDeactivated);

		isFirstTimeDeactivated = false;
	}

	public final void				update()
	{
		checkNotResolved();

		if (shouldResolve)
		{
			resolve();
			return;
		}

		if (childDelegate != null)
			childDelegate.update();

		if (isActivated)
			whenUpdated();
	}

	public final void				respond(Commands.Abstract command)
	{
		checkNotResolved();

		if (childDelegate != null)
			childDelegate.respond(command);
		else if (isActivated)
			whenResponded(command);
		else
			throw new Exceptions.UnexpectedCodeBranch();
	}

// -------------------------------> Resolution methods

	protected final void			requestResolution()
	{
		shouldResolve = true;
	}

	protected final void 			requestResolution(ResolutionMessage message)
	{
		shouldResolve = true;
		resolutionMessage = message;
	}

	private void 					resolve()
	{
		Delegate					parentCopy;

		checkHasNoChild();
		checkIfActivated();

		if (hasParent())
		{
			parentCopy = parentDelegate;

			unlinkParent();
			parentCopy.whenChildResolved(resolutionMessage);
		}

		isResolved = true;

		shouldResolve = false;
		resolutionMessage = null;
	}

// -------------------------------> Verification methods

	private void					checkHasParent()
	{
		assert hasParent();
	}

	private void					checkHasNoParent()
	{
		assert !hasParent();
	}

	private void					checkHasChild()
	{
		assert hasChild();
	}

	private void					checkHasNoChild()
	{
		assert !hasChild();
	}

	private void					checkIfActivated()
	{
		assert isActivated;
	}

	private void					checkIfDeactivated()
	{
		assert !isActivated;
	}

	private void					checkNotResolved()
	{
		assert !isResolved;
	}

// -------------------------------> Callback methods

	protected void 					whenActivated(boolean isFirstTime) {}
	protected void 					whenDeactivated(boolean isFirstTime) {}

	protected void 					whenUpdated() {}

	protected void					whenResponded(Commands.Abstract command) {}

	protected void					whenChildResolved(ResolutionMessage message) {}

// -------------------------------> Helper methods

	protected boolean				validateCommand(Commands.Abstract command, Class<?> expectedClass)
	{
		if (!expectedClass.isAssignableFrom(command.getClass()))
		{
			linkChild(new ErrorDelegate("Unexpected command"));
			return false;
		}

		return true;
	}

	public boolean					tryRespondToCommonCommands(Commands.Abstract command)
	{
		if (command instanceof Commands.Exit)
			Game.getInstance().terminate();
		else
			return false;

		return true;
	}

	protected void					sendRequest(Requests.Abstract request)
	{
		Model.getInstance().notifyListener(request);
	}

// -------------------------------> Logging

	private void					logChildLinking(Delegate delegate)
	{
		Debug.logFormat
		(
			LogGroup.DELEGATE,
			"[Model/Delegate] Linking child delegate of type '%s'",
			delegate.getClass()
		);
	}

	private void					logChildUnlinking()
	{
		Debug.logFormat
		(
			LogGroup.DELEGATE,
			"[Model/Delegate] Unlinking child delegate of type '%s'",
			childDelegate.getClass()
		);
	}
}
