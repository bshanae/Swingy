package swingy.model.closed.delegates.concrete.common;

import swingy.model.closed.delegates.abstract_.AbstractDelegate;
import swingy.model.closed.delegates.abstract_.AbstractResolutionObject;

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
