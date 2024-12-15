package com.shepeliev.ksdp

/**
 * Interface for objects that have attributes.
 */
public interface Attributed {
    public var attributes: MutableList<Attribute>
}

/**
 * Get all attributes with the given name.
 */
public fun Attributed.getAttributes(name: String): List<Attribute> = attributes.filter { it.name == name }

/**
 * Get all attributes with the given name.
 */
public operator fun Attributed.get(name: String): List<Attribute> = getAttributes(name)

/**
 * Add an attribute to the object.
 */
public fun Attributed.addAttribute(attribute: Attribute) {
    attributes.add(attribute)
}
