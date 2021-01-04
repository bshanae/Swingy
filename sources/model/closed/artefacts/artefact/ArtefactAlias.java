package model.closed.artefacts.artefact;

import application.service.Exceptions;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import model.closed.artefacts.armor.ArmorStorage;
import model.closed.artefacts.helm.HelmStorage;
import model.closed.artefacts.weapon.WeaponStorage;
import model.closed.utils.AbstractStorage;

public class						ArtefactAlias
{
// -------------------------------> Attributes

	@Getter
	public final String				name;

// -------------------------------> Constructors

	@JsonCreator
	public 							ArtefactAlias(@JsonProperty("name") String name)
	{
		this.name = name;
	}

// -------------------------------> Public methods

	public Artefact					get()
	{
		Artefact					temporary;

		if ((temporary = lookupForArtefact(HelmStorage.getInstance())) != null)
			return temporary;

		if ((temporary = lookupForArtefact(ArmorStorage.getInstance())) != null)
			return temporary;

		if ((temporary = lookupForArtefact(WeaponStorage.getInstance())) != null)
			return temporary;

		throw new Exceptions.ObjectNotFound();
	}

// -------------------------------> Private methods

	private <T extends Artefact> T	lookupForArtefact(AbstractStorage<T> storage)
	{
		for (T object : storage)
		{
			if (object.getName().equals(name))
				return object;
		}

		return null;
	}
}
