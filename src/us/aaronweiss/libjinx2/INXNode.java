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
 * An interface describing the basis of all iterable NX nodes.
 * @author Aaron Weiss
 * @version 2.0
 * @since 1.0
 */
public interface INXNode extends Iterable<INXNode> {
	/**
	 * Gets the name of this node.
	 * @return the name of this node
	 */
	public String getName();
	
	/**
	 * Gets the parent of this node.
	 * @return the parent of this node
	 */
	public INXNode getParent();
	
	/**
	 * Gets the value of this node.
	 * @return the value of this node
	 * @throws NXException if something goes wrong at the NX level.
	 */
	public Object getValue() throws NXException;
	
	/**
	 * Gets the type of this node.
	 * @return the type of this node
	 */
	public NXNodeType getType();
	
	/**
	 * Gets the path of this node.
	 * @return the path of this node
	 */
	public String getPath();
	
	/**
	 * Adds a child to this node.
	 * @param child the child to add
	 */
	public void addParsedChild(INXNode child);
	
	/**
	 * Gets the number of direct children that this node possesses.
	 * @return the number of direct children of this node
	 */
	public int getChildCount();
	
	/**
	 * Gets the offset of the direct children that this node possesses.
	 * @return the offset of the direct children of this node
	 */
	public long getChildOffset();
	
	/**
	 * Sets the number of direct children that this node possesses.
	 * @param childCount the number of direct children of this node
	 */
	public void setChildCount(int childCount);
	
	/**
	 * Sets the offset of the direct children that this node possesses.
	 * @param childOffset the offset of the direct children of this node
	 */
	public void setChildOffset(long childOffset);
	
	/**
	 * Gets the child node of this node corresponding to the specified <code>name</code>.
	 * @param name the name of the desired child node
	 * @return the desired child node
	 */
	public INXNode getChild(String name);
	
	/**
	 * Gets whether or not the specified <code>node</code> is a child of this node.
	 * @param node the child node to be tested
	 * @return whether or not the specified <code>node</code> is a child of this node
	 */
	public boolean hasChild(INXNode node);
}
