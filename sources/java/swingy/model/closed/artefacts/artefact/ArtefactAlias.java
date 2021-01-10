package swingy.model.closed.artefacts.artefact;

import swingy.application.service.Exceptions;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import swingy.model.closed.artefacts.armor.ArmorStorage;
import swingy.model.closed.artefacts.helm.HelmStorage;
import swingy.model.closed.artefacts.weapon.WeaponStorage;
import swingy.model.closed.utils.AbstractStorage;

public class						ArtefactAlias
{
// -------------------------------> Attributes

	private final Artefact			artefact;

// -------------------------------> Constructors

	@JsonCreator
	public 							ArtefactAlias(@JsonProperty("name") String name)
	{
		artefact = find(name);
	}

// -------------------------------> Public methods

	public Artefact					get()
	{
		return artefact;
	}

// -------------------------------> Private methods

	private Artefact				find(String name)
	{
		Artefact					temporary;

		if ((temporary = lookupForArtefact(HelmStorage.getInstance(), name)) != null)
			return temporary;
		else if ((temporary = lookupForArtefact(ArmorStorage.getInstance(), name)) != null)
			return temporary;
		else if ((temporary = lookupForArtefact(WeaponStorage.getInstance(), name)) != null)
			return temporary;

		throw new Exceptions.ObjectNotFound();
	}

	private <T extends Artefact> T	lookupForArtefact(AbstractStorage<T> storage, String artefactName)
	{
		for (T object : storage)
		{
			if (object.getName().equals(artefactName))
				return object;
		}

		return null;
	}
}
