package controller.closed.gui;

import controller.closed.SignalTranslator;
import controller.open.Commands;
import view.open.ButtonId;
import view.open.Signals;

public class					GuiSignalTranslator extends SignalTranslator
{
// ---------------------------> Exceptions

	public static class 		UnrecognizedSignalException extends RuntimeException
	{
		public 					UnrecognizedSignalException(Signals.Abstract signal)
		{
			super(String.format("Can't recognize signal of type '%s'", signal.getClass()));
		}
	}

// ---------------------------> Implementations

	@Override
	protected Commands.Abstract	translateImplementation(Signals.Abstract signal)
	{
		Signals.Gui				guiSignal;
		Commands.Abstract		command;

		guiSignal = (Signals.Gui)signal;

		if ((command = tryTranslateCommonSignals(guiSignal)) != null)
			return command;
		if ((command = tryTranslateHeroSelectorSignals(guiSignal)) != null)
			return command;
		if ((command = tryTranslateHeroCreatorSignals(guiSignal)) != null)
			return command;
		if ((command = tryTranslateMapSignals(guiSignal)) != null)
			return command;

		throw new UnrecognizedSignalException(signal);
	}

// ---------------------------> Private methods

	private Commands.Abstract	tryTranslateCommonSignals(Signals.Gui signal)
	{
		if (signal.getButtonId() == ButtonId.EXIT)
			return new Commands.Exit();
		if (signal.getButtonId() == ButtonId.INFO_OK)
			return new Commands.Ok();
		if (signal.getButtonId() == ButtonId.QUESTION_ANSWER_A)
			return new Commands.AnswerA();
		if (signal.getButtonId() == ButtonId.QUESTION_ANSWER_B)
			return new Commands.AnswerB();

		return null;
	}

	private Commands.Abstract	tryTranslateHeroSelectorSignals(Signals.Gui signal)
	{
		if (signal.getButtonId() == ButtonId.HERO_SELECTOR_CREATE)
			return new Commands.Create();
		if (signal.getButtonId() == ButtonId.HERO_SELECTOR_SELECT)
			return new Commands.Select(signal.getString());
		if (signal.getButtonId() == ButtonId.HERO_SELECTOR_DELETE)
			return new Commands.Delete(signal.getString());
		if (signal.getButtonId() == ButtonId.HERO_SELECTOR_INFO)
			return new Commands.Info(signal.getString());
		if (signal.getButtonId() == ButtonId.HERO_INFO_OK)
			return new Commands.Ok();

		return null;
	}

	private Commands.Abstract	tryTranslateHeroCreatorSignals(Signals.Gui signal)
	{
		if (signal.getButtonId() == ButtonId.NAME_ENTRY_ENTER)
			return new Commands.Enter(signal.getString());
		if (signal.getButtonId() == ButtonId.CLASS_SELECTOR_SELECT)
			return new Commands.Select(signal.getString());

		return null;
	}

	private Commands.Abstract	tryTranslateMapSignals(Signals.Gui signal)
	{
		// Arrows
		if (signal.getButtonId() == ButtonId.MAP_ARROW_UP)
			return new Commands.GoNorth();
		if (signal.getButtonId() == ButtonId.MAP_ARROW_LEFT)
			return new Commands.GoWest();
		if (signal.getButtonId() == ButtonId.MAP_ARROW_RIGHT)
			return new Commands.GoEast();
		if (signal.getButtonId() == ButtonId.MAP_ARROW_DOWN)
			return new Commands.GoSouth();

		// Stats
		if (signal.getButtonId() == ButtonId.MAP_HERO_STATS)
			return new Commands.Stats();
		if (signal.getButtonId() == ButtonId.HERO_STATS_OK)
			return new Commands.Ok();

		// Inventory
		if (signal.getButtonId() == ButtonId.MAP_HERO_INVENTORY)
			return new Commands.Inventory();
		if (signal.getButtonId() == ButtonId.HERO_INVENTORY_OK)
			return new Commands.Ok();

		// Battle
		if (signal.getButtonId() == ButtonId.BATTLE_PROCEED)
			return new Commands.Ok();

		return null;
	}
}
