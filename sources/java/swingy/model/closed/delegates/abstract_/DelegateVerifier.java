package swingy.model.closed.delegates.abstract_;

abstract class			DelegateVerifier
{
// -------------------> Exceptions

	public static class	VerificationErrorException extends RuntimeException {}

// -------------------> Public methods

	public static void	checkHasParent(AbstractDelegate delegate)
	{
		check(delegate.hasParent());

	}

	public static void	checkHasNoParent(AbstractDelegate delegate)
	{
		check(!delegate.hasParent());
	}

	public static void	checkHasChild(AbstractDelegate delegate)
	{
		check(delegate.hasChild());
	}

	public static void	checkHasNoChild(AbstractDelegate delegate)
	{
		check(!delegate.hasChild());
	}

	public static void	checkIfActivated(AbstractDelegate delegate)
	{
		check(delegate.isActivated());
	}

	public static void	checkIfDeactivated(AbstractDelegate delegate)
	{
		check(!delegate.isActivated());
	}

	public static void	checkNotResolved(AbstractDelegate delegate)
	{
		check(!delegate.isResolved());
	}

// -------------------> Private methods

	private static void	check(boolean statement)
	{
		if (!statement)
			throw new VerificationErrorException();
	}
}
