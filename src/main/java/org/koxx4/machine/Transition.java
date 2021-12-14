package org.koxx4.machine;

public record Transition<StateType extends State, EdgeType extends Edge>(StateType state, EdgeType edge) {
}
