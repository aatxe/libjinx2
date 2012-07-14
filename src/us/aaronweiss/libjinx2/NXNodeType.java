/**
 * libjinx2: a library for loading the NX file format
 * Copyright (C) 2012 Aaron Weiss <aaronweiss74@gmail.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package us.aaronweiss.libjinx2;

/**
 * An enumeration representing all the various types of nodes.
 * @author Aaron Weiss
 * @version 1.0
 * @since 1.0
 */
public enum NXNodeType {
	Unknown(-1), 
	Null(0),
	Integer(1),
	Double(2),
	String(3),
	Point(4),
	Canvas(5),
	MP3(6),
	Link(7);
	private int typeId;
	
	/**
	 * Creates a node type.
	 * @param typeId the id of the type
	 */
	private NXNodeType(int typeId) {
		this.typeId = typeId;
	}
	
	/**
	 * Gets the identifier of the node type
	 * @return the id of the node type
	 */
	public int getTypeId() {
		return typeId;
	}
	
	/**
	 * Gets an <code>NXNodeType</code> from its type id.
	 * @param typeId the id to get the type of
	 * @return the node type
	 */
	public static NXNodeType fromTypeId(int typeId) {
		switch (typeId) {
			default:
				return Unknown;
			case 0:
				return Null;
			case 1:
				return Integer;
			case 2:
				return Double;
			case 3:
				return String;
			case 4:
				return Point;
			case 5:
				return Canvas;
			case 6:
				return MP3;
			case 7:
				return Link;
		}
	}
}
