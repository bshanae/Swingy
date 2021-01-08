package model.closed.delegates.concreteDelegates.common;

import model.closed.delegates.abstractDelegate.AbstractDelegate;
import model.closed.delegates.abstractDelegate.AbstractResolutionObject;

public class				AutoResolvableDelegate extends AbstractDelegate
{
// -----------------------> Resolution object

	public static class		ResolutionObject implements AbstractResolutionObject {}

// -----------------------> Implementations

	@Override
	protected void			whenActivated()
	{
		resolveLater(new ResolutionObject());
	}
}
