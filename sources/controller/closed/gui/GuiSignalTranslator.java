package controller.closed.gui;

import controller.closed.SignalTranslator;
import controller.open.Commands;
import view.open.Signals;

public class					GuiSignalTranslator extends SignalTranslator
{
	@Override
	protected Commands.Abstract	translateImplementation(Signals.Abstract signal)
	{
		Signals.Gui				guiSignal;

		guiSignal = (Signals.Gui)signal;
		switch (guiSignal.getButtonId())
		{
			case INFO_OK:
				return new Commands.Ok();

			case QUESTION_ANSWER_A:
				return new Commands.AnswerA();

			case QUESTION_ANSWER_B:
				return new Commands.AnswerB();

			case HERO_SELECTOR_CREATE_0:
			case HERO_SELECTOR_CREATE_1:
			case HERO_SELECTOR_CREATE_2:
			case HERO_SELECTOR_CREATE_3:
				return new Commands.Create();

			case HERO_SELECTOR_SELECT_0:
				return new Commands.Select(0);
			case HERO_SELECTOR_SELECT_1:
				return new Commands.Select(1);
			case HERO_SELECTOR_SELECT_2:
				return new Commands.Select(2);
			case HERO_SELECTOR_SELECT_3:
				return new Commands.Select(3);

			case HERO_SELECTOR_DELETE_0:
				return new Commands.Delete(0);
			case HERO_SELECTOR_DELETE_1:
				return new Commands.Delete(1);
			case HERO_SELECTOR_DELETE_2:
				return new Commands.Delete(2);
			case HERO_SELECTOR_DELETE_3:
				return new Commands.Delete(3);

			case HERO_SELECTOR_INFO_0:
				return new Commands.Info(0);
			case HERO_SELECTOR_INFO_1:
				return new Commands.Info(1);
			case HERO_SELECTOR_INFO_2:
				return new Commands.Info(2);
			case HERO_SELECTOR_INFO_3:
				return new Commands.Info(3);

			case HERO_INFO_OK:
				return new Commands.Ok();

			case NAME_ENTRY_ENTER:
				return new Commands.Enter((String)guiSignal.getLinkedData());

			case MAP_ARROW_UP:
				return new Commands.GoNorth();

			case MAP_ARROW_LEFT:
				return new Commands.GoWest();

			case MAP_ARROW_RIGHT:
				return new Commands.GoEast();

			case MAP_ARROW_DOWN:
				return new Commands.GoSouth();

			case MAP_HERO_STATS:
				return new Commands.Stats();

			case MAP_HERO_INVENTORY:
				return new Commands.Inventory();

			case HERO_STATS_OK:
				return new Commands.Ok();

			case HERO_INVENTORY_OK:
				return new Commands.Ok();

			case BATTLE_PROCEED:
				return new Commands.Ok();

			default:
				// TODO Error
				return null;
		}
	}
}
