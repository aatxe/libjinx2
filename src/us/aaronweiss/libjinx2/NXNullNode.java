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
 * An <code>NXNode</code> containing, and representing nothing.
 * @author Aaron Weiss
 * @version 1.0
 * @since 1.0
 */
public class NXNullNode extends NXNode {
	/**
	 * Creates a null <code>NXNode</code>.
	 * @param name the name of the node
	 * @param file the file of the node
	 * @param parent the parent of the node
	 */
	public NXNullNode(String name, NXFile file, INXNode parent) {
		super(name, null, file, parent);
	}

	@Override
	public NXNodeType getType() {
		return NXNodeType.Null;
	}
}
