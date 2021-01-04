package application.service;

public abstract class	Exceptions
{
	public static class UnexpectedCodeBranch extends RuntimeException {}

	public static class ObjectNotFound extends RuntimeException {}
}
