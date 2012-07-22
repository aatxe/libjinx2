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

import java.util.HashMap;
import java.util.Iterator;

/**
 * The basis of all nodes in libjinx2.
 * @author Aaron Weiss
 * @version 2.0
 * @since 1.0
 */
public abstract class NXNode implements INXNode {
	protected HashMap<String, INXNode> parsedChildren = null;
	protected int childCount;
	protected long childOffset;
	protected final String name;
	protected Object value;
	protected final NXFile file;
	protected final INXNode parent;
	
	/**
	 * Creates a generic <code>NXNode</code>.
	 * @param name the name of the node
	 * @param value the value of the node
	 * @param file the file of the node
	 * @param parent the parent of the node
	 */
	public NXNode(final String name, final Object value, final NXFile file, final INXNode parent) {
		this.name = name;
		this.value = value;
		this.file = file;
		this.parent = parent;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public INXNode getParent() {
		return this.parent;
	}

	@Override
	public Object getValue() throws NXException {
		return this.value;
	}

	@Override
	public NXNodeType getType() {
		return NXNodeType.Unknown;
	}

	@Override
	public String getPath() {
		StringBuilder sb = new StringBuilder(this.getName());
		INXNode node = this;
		while ((node = node.getParent()) != null)
			sb.insert(0, "/").insert(0, node.getName());
		return sb.toString();
	}

	@Override
	public void addParsedChild(INXNode child) {
		if (parsedChildren == null)
			parsedChildren = new HashMap<String, INXNode>();
		parsedChildren.put(child.getName(), child);
	}
	
	@Override
	public int getChildCount() {
		return this.childCount;
	}
	
	@Override
	public long getChildOffset() {
		return this.childOffset;
	}
	
	@Override
	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}
	
	@Override
	public void setChildOffset(long childOffset) {
		this.childOffset = childOffset;
	}

	@Override
	public INXNode getChild(String name) {
		if (parsedChildren != null && parsedChildren.containsKey(name)) {
			parsedChildren.get(name);
		} else {
			try {
				INXNode[] children = file.parseNodeChildren(this);
				for (INXNode child : children) {
					this.addParsedChild(child);
				}
			} catch (NXException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				// do nothing (?)
			}
			return parsedChildren.get(name);
		}
		return null;
	}

	@Override
	public boolean hasChild(INXNode node) {
		if (parsedChildren != null) {
			return parsedChildren.containsValue(node);
		} else {
			try {
				INXNode[] children = file.parseNodeChildren(this);
				for (INXNode child : children) {
					this.addParsedChild(child);
				}
			} catch (NXException e) {
				e.printStackTrace();
			}
			return parsedChildren.containsValue(node);
		}
	}
	
	@Override
	public Iterator<INXNode> iterator() {
		return (parsedChildren == null) ? null : parsedChildren.values().iterator();
	}
}
