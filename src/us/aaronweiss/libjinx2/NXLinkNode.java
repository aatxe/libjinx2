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
 * An <code>NXNode</code> representing a linkage to another node.
 * @author Aaron Weiss
 * @version 1.0
 * @since 1.0
 */
public class NXLinkNode extends NXNode {
	private final int linkId;
	
	/**
	 * Creates an <code>NXNode</code> linking to another node.
	 * @param name the name of the node
	 * @param linkId the id of the link
	 * @param file the file of the node
	 * @param parent the parent of the node
	 */
	public NXLinkNode(String name, int linkId, NXFile file, INXNode parent) {
		super(name, null, file, parent);
		this.linkId = linkId;
	}

	@Override
	public NXNode getValue() {
		if (this.value == null) {
			value = file.nodeTable[linkId];
		}
		return (NXNode) this.value;
	}
	
	@Override
	public NXNodeType getType() {
		return NXNodeType.Link;
	}
	
	/**
	 * Resolves this link node completely to its true value.
	 * @return the true value of this link node
	 */
	public NXNode resolve() {
        NXNode ret = this;
        while (ret instanceof NXLinkNode)
            ret = ((NXLinkNode) ret).getValue();
        return ret;
	}
}
